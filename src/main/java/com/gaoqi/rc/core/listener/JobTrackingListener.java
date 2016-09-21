package com.gaoqi.rc.core.listener;

import com.gaoqi.rc.dao.RcQuartzExecutionMapper;
import com.gaoqi.rc.enums.JobExecutionResultEnum;
import com.gaoqi.rc.model.RcQuartzExecution;
import com.gaoqi.rc.utils.JobDetailUtil;
import com.gaoqi.rc.domain.QuartzJobVo;
import com.sohu.rc.util.JsonParser;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 任务执行的开始，结束，否决的时候
 * <p/>
 * Created by qigao212074 on 2016/9/9.
 */
public class JobTrackingListener implements JobListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RcQuartzExecutionMapper executionMapper;

    @Override
    public String getName() {
        return "jobListener";
    }

    /**
     * 任务被执行时
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        QuartzJobVo quartzJobVo = JobDetailUtil.getJobContent(jobExecutionContext);

        logger.info("Task is executed, this task detail is {}, time is {}",
                JsonParser.write(quartzJobVo), new Date());
    }

    /**
     * 任务被否决时
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        //获取日志编号
        int execId = 0;
        try {
            execId = JobDetailUtil.getExecId(jobExecutionContext);
            //获取日志信息
            RcQuartzExecution execution = executionMapper.selectByPrimaryKey(execId);
            //设置信息
            execution.setRemark(JobDetailUtil.getJobResultRemark(jobExecutionContext));
            execution.setEndTime(new Date());
            execution.setExecResult(JobExecutionResultEnum.FAILED.getCode());
            //更新信息
            executionMapper.updateByPrimaryKey(execution);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            logger.warn("JOB被否决[{}]", execId);
        }

    }


    /**
     * 任务执行结束
     *
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        try {
            updateExecutionWhenJobExecuted(jobExecutionContext, e);
        } catch (Exception ex) {
            logger.warn("Quartz Task execute has caused Exception: {}", e.getMessage());
        }
    }

    private void updateExecutionWhenJobExecuted(JobExecutionContext context, JobExecutionException e) {
        int execId = JobDetailUtil.getExecId(context);
        RcQuartzExecution execution = executionMapper.selectByPrimaryKey(execId);
        execution.setEndTime(new Date());
        if (e == null) {
            execution.setExecResult(JobExecutionResultEnum.SUCCESS.getCode());
            logger.info("Task execute result is SUCCESS, detail is {}",
                    JsonParser.write(JobDetailUtil.getJobContent(context)));
        } else {
            execution.setExecResult(JobExecutionResultEnum.EXCEPTION.getCode());
            execution.setRemark(e.getMessage());
            logger.error("Task execute result is FAILED, error msg is {}", e.getMessage());

            //TODO 发邮件？

        }
        executionMapper.updateByPrimaryKey(execution);
    }
}
