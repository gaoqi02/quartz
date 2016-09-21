package com.gaoqi.rc.core.listener;

import com.gaoqi.rc.dao.RcQuartzExecutionMapper;
import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.dao.RcQuartzScheduleMapper;
import com.gaoqi.rc.domain.QuartzJobVo;
import com.gaoqi.rc.enums.ScheduleEnum;
import com.gaoqi.rc.model.RcQuartzExecution;
import com.sohu.rc.util.JsonParser;
import com.sohu.rc.util.SpringUtils;
import com.gaoqi.rc.utils.JobDetailUtil;
import com.gaoqi.rc.utils.ReflectUtil;
import com.gaoqi.rc.utils.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qigao212074 on 2016/9/9.
 */
public class TriggerTrackingListener implements TriggerListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RcQuartzExecutionMapper executionMapper;

    @Autowired
    private RcQuartzScheduleMapper scheduleMapper;

    @Override
    public String getName() {
        return "triggerListener";
    }

    /**
     * 开始执行时
     *
     * @param trigger
     * @param jobExecutionContext
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {

        try {
            //1.创建日志并放入整体的环境下
            int execId = createExecutionLog(jobExecutionContext);
            JobDetailUtil.setExecId(jobExecutionContext, execId);

            //2.更新调度信息
            updateSchedule(jobExecutionContext);
            logger.info("Trigger is running and exec_id is :{}", execId);
        } catch (Exception ex) {
            logger.warn("Quartz has FAILED By Exception :{}", ex.getMessage());
        }

    }

    /**
     * 更新下次执行的时间点，方便前端进行展示
     *
     * @param jobExecutionContext
     * @return
     */
    private void updateSchedule(JobExecutionContext jobExecutionContext) {
        QuartzJobVo quartzJobVo = JobDetailUtil.getJobContent(jobExecutionContext);
        RcQuartzSchedule schedule = scheduleMapper.selectByPrimaryKey(quartzJobVo.getScheduleId());
        schedule.setNextTime(jobExecutionContext.getNextFireTime());
        scheduleMapper.updateByPrimaryKey(schedule);

    }

    /**
     * 创建执行的execution信息，也就是执行的日志
     *
     * @param jobExecutionContext
     * @return
     */
    private int createExecutionLog(JobExecutionContext jobExecutionContext) {
        QuartzJobVo quartzJobVo = JobDetailUtil.getJobContent(jobExecutionContext);

        RcQuartzExecution execution = new RcQuartzExecution();
        //任务执行的调度id
        execution.setScheduleId(quartzJobVo.getScheduleId());
        //任务的id
        execution.setJobId(quartzJobVo.getQuartzJob().getId());
        //下次触发的时间
        execution.setNextFireTime(jobExecutionContext.getNextFireTime());
        //本次触发的时间
        execution.setFireTime(jobExecutionContext.getFireTime());
        //上次触发的时间
        execution.setPreFireTime(jobExecutionContext.getPreviousFireTime());

        executionMapper.insert(execution);
        return execution.getId();
    }

    /**
     * 在 Trigger触发后，Job将要被执行时由 Scheduler 调用这个方法。
     * TriggerListener 给了一个选择去否决 Job 的执行。
     * 假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
     * 根据context判断任务是否要执行
     *
     * @param trigger
     * @param jobExecutionContext
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        logger.info("vetoJobExecution...");

        QuartzJobVo quartzJobVo = JobDetailUtil.getJobContent(jobExecutionContext);
        String jobObject = quartzJobVo.getQuartzJob().getJobObject();
        Object targetObject = SpringUtils.getBean(jobObject);

        //对象是否存在
        if (StringUtils.trimToNull(jobObject) == null) {
            logger.warn("QUARTZ TASK VETO, TASK:{} is NULL . ", jobObject);
            StringBuffer sb = new StringBuffer();
            sb.append("作业[" + jobObject +"]的执行对象未配置");
            JobDetailUtil.setJobResultRemark(jobExecutionContext, sb.toString());
            return true;
        }
        if (!SpringUtils.containsBean(jobObject)) {
            logger.warn("QUARTZ TASK VETO, TASK:{} is NONE", jobObject);
            StringBuffer sb = new StringBuffer();
            sb.append("作业对象[" + jobObject + "]不存在");
            JobDetailUtil.setJobResultRemark(jobExecutionContext, sb.toString());
            return true;
        }

        //方法是否存在
        String methodName = quartzJobVo.getQuartzJob().getJobMethod();

        if (StringUtils.trimToNull(methodName) == null) {
            logger.warn("QUARTZ TASK VETO, TASK:{}'s method {} is NULL . ", jobObject, methodName);
            StringBuffer sb = new StringBuffer();
            sb.append("作业[" + jobObject + "," + methodName + "]的执行方法未配置");
            JobDetailUtil.setJobResultRemark(jobExecutionContext, sb.toString());
            return true;
        }
        //此处需要放入实例化的Object对象，不然用String  查询的String.class的方法
        if (!ReflectUtil.checkExist(targetObject, methodName)) {
            logger.warn("QUARTZ TASK VETO, TASK:{}'s method {} is NONE . ", jobObject, methodName);
            StringBuffer sb = new StringBuffer();
            sb.append("作业[" + jobObject + "," + methodName + "]的执行方法未配置");
            JobDetailUtil.setJobResultRemark(jobExecutionContext, sb.toString());

            return true;
        }

        //需要从数据库中判断任务是否在执行
        RcQuartzSchedule quartzSchedule =
                scheduleMapper.selectByPrimaryKey(quartzJobVo.getScheduleId());
        if (quartzSchedule.getState().intValue() == ScheduleEnum.RUNNING.getCode()) {
            logger.warn("QUARTZ TASK VETO, TASK:{} is NULL . ", jobObject);
            StringBuffer sb = new StringBuffer();
            sb.append("任务[" + JsonParser.write(quartzSchedule) +"]正在执行无法处理");
            JobDetailUtil.setJobResultRemark(jobExecutionContext, sb.toString());
            return true;
        } else {
            quartzSchedule.setState(ScheduleEnum.RUNNING.getCode());
            scheduleMapper.updateByPrimaryKey(quartzSchedule);
        }

        return false;
    }

    /**
     * 时间触发器/任务 错过时
     *
     * @param trigger
     */
    @Override
    public void triggerMisfired(Trigger trigger) {
        //获取TriggerKey
        TriggerKey triggerKey = trigger.getKey();
        logger.warn("Trigger is Miss ,the detail is , group: {}, name :{}",
                triggerKey.getGroup(), triggerKey.getName());
    }

    /**
     * 执行结束时
     *
     * @param trigger
     * @param jobExecutionContext
     * @param completedExecutionInstruction
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        //获取日志编号
        int execId = JobDetailUtil.getExecId(jobExecutionContext);
        QuartzJobVo quartzJobVo = JobDetailUtil.getJobContent(jobExecutionContext);
        RcQuartzSchedule schedule = scheduleMapper.selectByPrimaryKey(quartzJobVo.getScheduleId());
        schedule.setState(ScheduleEnum.WAITING.getCode());
        scheduleMapper.updateByPrimaryKey(schedule);
        logger.info("Trigger is complete and execId is: {}", execId);
    }
}
