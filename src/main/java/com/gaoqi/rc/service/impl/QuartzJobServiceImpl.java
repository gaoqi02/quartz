package com.gaoqi.rc.service.impl;

import com.gaoqi.rc.dao.RcQuartzJobMapper;
import com.gaoqi.rc.dao.RcQuartzScheduleMapper;
import com.gaoqi.rc.domain.QuartzJobVo;
import com.gaoqi.rc.enums.QuartzExceptionEnum;
import com.gaoqi.rc.enums.ValidEnum;
import com.gaoqi.rc.exception.QuartzException;
import com.gaoqi.rc.model.RcQuartzJob;
import com.gaoqi.rc.model.RcQuartzJobCriteria;
import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzScheduleCriteria;
import com.gaoqi.rc.service.IQuartzJobService;
import com.gaoqi.rc.service.IScheduleService;
import com.gaoqi.rc.service.ITriggerService;
import com.gaoqi.rc.utils.ReflectUtil;
import com.gaoqi.rc.utils.StringUtils;
import com.gaoqi.rc.utils.TriggerUtil;
import com.sohu.rc.util.JsonParser;
import com.sohu.rc.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by qigao212074 on 2016/9/9.
 */
@Service
public class QuartzJobServiceImpl implements IQuartzJobService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RcQuartzJobMapper jobMapper;

    @Autowired
    private RcQuartzScheduleMapper quartzScheduleMapper;

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ITriggerService triggerService;

    @Override
    public QuartzJobVo getJob(int jobId, LoginInfo user) {
        logger.info("user {} get job info : {} from Quartz ", user.getLoginName(), jobId);
        //对应的任务
        RcQuartzJob quartzJob = jobMapper.selectByPrimaryKey(jobId);
        if (null == quartzJob) {
            throw new QuartzException(QuartzExceptionEnum.NONE_JOB);
        } else if (quartzJob.getUserId() != user.getLoginId()) {
            throw new QuartzException(QuartzExceptionEnum.ERROR_JOB_USER);
        }

        //获取对应的任务schedule
        RcQuartzScheduleCriteria scheduleCriteria = new RcQuartzScheduleCriteria();
        scheduleCriteria.createCriteria().andJobIdEqualTo(jobId);
        List<RcQuartzSchedule> quartzSchedules = quartzScheduleMapper.selectByExample(scheduleCriteria);

        return new QuartzJobVo(quartzJob, quartzSchedules);
    }

    /**
     * 创建新的job
     *
     * @param quartzJob
     */
    @Override
    public int newJob(RcQuartzJob quartzJob, LoginInfo user) {
        logger.info("user {} schedule a new job : {} to Quartz ", user.getLoginName(), JsonParser.write(quartzJob));

        // 反射检查对象是否存在
        Object obj = SpringUtils.getBean(quartzJob.getJobObject());
        if (null == obj) {
            throw new QuartzException(QuartzExceptionEnum.NONE_JOB);
        }
        if (!ReflectUtil.checkExist(obj, quartzJob.getJobMethod())) {
            throw new QuartzException(QuartzExceptionEnum.NONE_JOB);
        }

        // 数据库中是否有相应的任务
        RcQuartzJobCriteria jobCriteria = new RcQuartzJobCriteria();
        jobCriteria.createCriteria().andJobObjectEqualTo(quartzJob.getJobObject()).andJobMethodEqualTo(quartzJob.getJobMethod())
                .andTaskGroupEqualTo(quartzJob.getTaskGroup()).andTaskNameEqualTo(quartzJob.getTaskName())
                .andStatusEqualTo(ValidEnum.VALID.getCode());
        List<RcQuartzJob> quartzJobs = jobMapper.selectByExample(jobCriteria);
        if (null != quartzJob && quartzJobs.size() > 0) {
            //若有相同的任务直接返回该任务的对象
            return quartzJobs.get(0).getId();
        }
        // 任务调用的方法和对象不可为空
        if (null == quartzJob.getJobObject() || null == quartzJob.getJobMethod()) {
            throw new QuartzException(QuartzExceptionEnum.NONE_METHOD_OR_OBJECT);
        }
        quartzJob.setUserId(user.getLoginId());
        quartzJob.setCreateTime(new Date());
        quartzJob.setStatus(ValidEnum.VALID.getCode());

        jobMapper.insert(quartzJob);

        return quartzJob.getId();
    }

    @Override
    public List<RcQuartzJob> getJobsByUser(LoginInfo user, String module) {
        RcQuartzJobCriteria jobCriteria = new RcQuartzJobCriteria();
        if (null == module || StringUtils.trimIsNull(module)) {
            jobCriteria.createCriteria().andUserIdEqualTo(user.getLoginId())
                    .andStatusEqualTo(ValidEnum.VALID.getCode());
        }
        if (null != module && !StringUtils.trimIsNull(module)) {
            jobCriteria.createCriteria().andUserIdEqualTo(user.getLoginId())
                    .andStatusEqualTo(ValidEnum.VALID.getCode())
                    .andTaskGroupEqualTo(module);
        }

        return jobMapper.selectByExample(jobCriteria);
    }

    /**
     * 对于某个任务进行启动Trigger,由于需要操作Quartz，所以先对Trigger进行校验
     *
     * @param quartzJobVo
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createScheduleJob(QuartzJobVo quartzJobVo, LoginInfo user) {
        // 事务无法保证Quartz模块，所以先对schedule进行校验，校验成功后对Quartz发送请求
        for (RcQuartzSchedule schedule: quartzJobVo.getQuartzSchedules()) {
            TriggerUtil.checkTrigger(schedule);
        }
        // 若任务存在则直接返回jobId，不存在新建后返回
        int jobId = newJob(quartzJobVo.getQuartzJob(), user);
        // 新建schedule 并且启动
        for (RcQuartzSchedule scheduler: quartzJobVo.getQuartzSchedules()) {
            scheduler.setJobId(jobId);
            //新建schedule
            int scheduleId = scheduleService.newSchedule(scheduler, user);
            triggerService.startTrigger(scheduleId);
        }
    }
}
