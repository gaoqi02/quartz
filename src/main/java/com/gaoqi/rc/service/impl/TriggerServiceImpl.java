package com.gaoqi.rc.service.impl;

import com.gaoqi.rc.dao.RcQuartzJobMapper;
import com.gaoqi.rc.enums.QuartzExceptionEnum;
import com.gaoqi.rc.enums.ValidEnum;
import com.gaoqi.rc.exception.QuartzException;
import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzScheduleCriteria;
import com.gaoqi.rc.utils.JobDetailUtil;
import com.gaoqi.rc.dao.RcQuartzScheduleMapper;
import com.gaoqi.rc.enums.ScheduleEnum;
import com.gaoqi.rc.model.RcQuartzJob;
import com.gaoqi.rc.service.ITriggerService;
import com.gaoqi.rc.utils.KeyUtil;
import com.gaoqi.rc.utils.TriggerUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qigao212074 on 2016/9/9.
 */
@Service
public class TriggerServiceImpl implements ITriggerService {

    @Autowired
    private RcQuartzJobMapper quartzJobMapper;

    @Autowired
    private RcQuartzScheduleMapper quartzScheduleMapper;

    @Autowired
    private Scheduler scheduler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startTrigger(int id) {
        //判断这个任务是否是有效任务
        RcQuartzSchedule schedule = quartzScheduleMapper.selectByPrimaryKey(id);
        RcQuartzJob job = quartzJobMapper.selectByPrimaryKey(schedule.getJobId());

        if (null == job) {
            throw new QuartzException(QuartzExceptionEnum.NONE_JOB);
        } else if (job.getStatus() == ValidEnum.IN_VALID.getCode()) {
            throw new QuartzException(QuartzExceptionEnum.IN_VALID_JOB);
        } else if (schedule.getStatus() == ValidEnum.IN_VALID.getCode()) {
            throw new QuartzException(QuartzExceptionEnum.IN_VALID_SCHEDULE);
        }

        JobKey jobKey = KeyUtil.jobKey(job);
        TriggerKey triggerKey = KeyUtil.triggerKey(job, schedule);

        try {
            if (scheduler.checkExists(jobKey)) {
                //如果有相应的触发任务了，则抛出异常
                if (!scheduler.checkExists(triggerKey)) {
                    Trigger trigger = TriggerUtil.build(job, schedule);
                    scheduler.scheduleJob(trigger);
                } else {
                    throw new QuartzException(QuartzExceptionEnum.JOB_ALREADY_SCHEDULE);
                }
            } else {
                //构建任务的实例和时间触发器
                JobDetail jobDetail = JobDetailUtil.build(job, schedule);
                Trigger trigger = TriggerUtil.build(job, schedule);
                scheduler.scheduleJob(jobDetail, trigger);
            }
            //更新数据库的状态
            schedule.setState(ScheduleEnum.WAITING.getCode());//等待执行的状态
            quartzScheduleMapper.updateByPrimaryKey(schedule);

        } catch (SchedulerException e) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_IN_QUARTZ);
        }
    }

    @Override
    public void deleteTrigger(int scheduleId) {
        RcQuartzSchedule schedule = quartzScheduleMapper.selectByPrimaryKey(scheduleId);
        RcQuartzJob job = quartzJobMapper.selectByPrimaryKey(schedule.getJobId());
        //对应的时间触发器的id
        TriggerKey triggerKey = KeyUtil.triggerKey(job, schedule);
        try {
            if (!scheduler.checkExists(triggerKey)) {
                throw new QuartzException(QuartzExceptionEnum.NONE_TRIGGER);
            }
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_IN_QUARTZ);
        }
        //修改数据库中的schedule的状态
        schedule.setState(ScheduleEnum.WAITING.getCode());
        quartzScheduleMapper.updateByPrimaryKey(schedule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJob(int jobId) {
        RcQuartzJob job = quartzJobMapper.selectByPrimaryKey(jobId);
        //对应的时间触发器的id
        JobKey jobKey = KeyUtil.jobKey(job);
        try {
            if (!scheduler.checkExists(jobKey)) {
                throw new QuartzException(QuartzExceptionEnum.NONE_JOB);
            }
            if (!scheduler.deleteJob(jobKey)) {
                throw new QuartzException(QuartzExceptionEnum.DELETE_JOB_FROM_QUARTZ_ERROR);
            }
        } catch (SchedulerException e) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_IN_QUARTZ);
        }

        //修改数据库中的schedule的状态
        RcQuartzScheduleCriteria scheduleCriteria = new RcQuartzScheduleCriteria();
        scheduleCriteria.createCriteria().andJobIdEqualTo(jobId);
        List<RcQuartzSchedule> schedules = quartzScheduleMapper.selectByExample(scheduleCriteria);

        if (null != schedules && schedules.size() > 0) {
            for (RcQuartzSchedule schedule : schedules) {
                schedule.setStatus(ValidEnum.IN_VALID.getCode());
                schedule.setState(ScheduleEnum.WAITING.getCode());
                quartzScheduleMapper.updateByPrimaryKey(schedule);
            }
        }
    }

    @Override
    public void pauseTrigger(int scheduleId) {
        RcQuartzSchedule schedule = quartzScheduleMapper.selectByPrimaryKey(scheduleId);
        RcQuartzJob job = quartzJobMapper.selectByPrimaryKey(schedule.getJobId());
        //需要停止的trigger
        TriggerKey triggerKey = KeyUtil.triggerKey(job, schedule);

        try {
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_IN_QUARTZ);
        }

        //修改数据库中的schedule的状态
        schedule.setState(ScheduleEnum.PAUSING.getCode());
        quartzScheduleMapper.updateByPrimaryKey(schedule);
    }

    @Override
    public void resumeTrigger(int scheduleId) {
        RcQuartzSchedule schedule = quartzScheduleMapper.selectByPrimaryKey(scheduleId);
        RcQuartzJob job = quartzJobMapper.selectByPrimaryKey(schedule.getJobId());
        //需要停止的trigger
        TriggerKey triggerKey = KeyUtil.triggerKey(job, schedule);

        try {
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_IN_QUARTZ);
        }

        //修改数据库中的schedule的状态
        schedule.setState(ScheduleEnum.RUNNING.getCode());
        quartzScheduleMapper.updateByPrimaryKey(schedule);
    }
}
