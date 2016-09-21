package com.gaoqi.rc.service.impl;

import com.gaoqi.rc.dao.RcQuartzJobMapper;
import com.gaoqi.rc.dao.RcQuartzScheduleMapper;
import com.gaoqi.rc.domain.QuartzJobVo;
import com.gaoqi.rc.enums.QuartzExceptionEnum;
import com.gaoqi.rc.enums.ValidEnum;
import com.gaoqi.rc.exception.QuartzException;
import com.gaoqi.rc.model.RcQuartzJob;
import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzScheduleCriteria;
import com.gaoqi.rc.service.IScheduleService;
import com.gaoqi.rc.model.LoginInfo;
import com.gaoqi.rc.utils.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by qigao212074 on 2016/9/9.
 */
@Service
public class ScheduleServiceImpl implements IScheduleService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RcQuartzJobMapper quartzJobMapper;

    @Autowired
    private RcQuartzScheduleMapper quartzScheduleMapper;

    @Override
    public int newSchedule(RcQuartzSchedule quartzSchedule, LoginInfo user) {
        logger.info("user {} created a new schedule: {} into Quartz",
                user.getLoginName(), JsonParser.write(quartzSchedule));

        //检测需要定时的任务是否存在
        if (null == quartzJobMapper.selectByPrimaryKey(quartzSchedule.getJobId())) {
            throw new QuartzException(QuartzExceptionEnum.NONE_JOB);
        }

        quartzSchedule.setCreateTime(new Date());
        quartzSchedule.setStatus(ValidEnum.VALID.getCode());

        quartzScheduleMapper.insert(quartzSchedule);
        return quartzSchedule.getId();
    }

    @Override
    public QuartzJobVo getScheduleById(int id, LoginInfo user) {
        RcQuartzSchedule schedule = quartzScheduleMapper.selectByPrimaryKey(id);
        if (schedule == null) {
            throw new QuartzException(QuartzExceptionEnum.IN_VALID_JOB);
        }
        RcQuartzJob quartzJob = quartzJobMapper.selectByPrimaryKey(schedule.getJobId());
        if (null == quartzJob || quartzJob.getUserId() != user.getLoginId()) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_JOB_USER);
        }
        return new QuartzJobVo(quartzJob, schedule, id);
    }

    @Override
    public List<RcQuartzSchedule> getScheduleByJob(int jobId, LoginInfo user) {
        logger.info("user {} get job schedule info from Quartz,job id is {}", user.getLoginName(), jobId);

        //检查该用户是否有权限
        RcQuartzJob job = quartzJobMapper.selectByPrimaryKey(jobId);
        if (job.getUserId() != user.getLoginId()) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_JOB_USER);
        }
        RcQuartzScheduleCriteria quartzScheduleCriteria = new RcQuartzScheduleCriteria();
        quartzScheduleCriteria.createCriteria().andJobIdEqualTo(jobId).andStateEqualTo(ValidEnum.VALID.getCode());

        return quartzScheduleMapper.selectByExample(quartzScheduleCriteria);
    }

}
