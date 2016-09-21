package com.gaoqi.rc.core.job;

import com.gaoqi.rc.common.Constant;
import com.gaoqi.rc.domain.QuartzJobVo;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 基础Job 将信息放入到kafka中
 * <p/>
 * Created by qigao212074 on 2016/9/9.
 */
public class BaseJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 利用反射执行对象，以下三类对象均从kafka中读取来
     * @param context
     *  object 执行的对象在spring中的bean id例如执行service,若没有确定service的名称则会类名的小写
     *               e.g: projectServiceImpl implements IProjectService 则object为projectServiceImpl
     *               注意驼峰
     *  method 需要调用的method
     *  args   方法的参数，比较建议传入id或者任务名一个参数，方便管理。
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            JobDataMap dataMap = context.getMergedJobDataMap();
            QuartzJobVo quartzJobVo = (QuartzJobVo) dataMap.get(Constant.KEY_JOB_CONTENT);
            //调用的类
            String object = quartzJobVo.getQuartzJob().getJobObject();
            //调用的方法
            String method = quartzJobVo.getQuartzJob().getJobMethod();
            //调用的任务标志
            String jobName = quartzJobVo.getQuartzJob().getTaskName();
            //调用哪个module
            String jobGroup = quartzJobVo.getQuartzJob().getTaskGroup();

            logger.info("start task: {} from group:{},this execute msg object: {},method: {} , time is {}",
                    jobName, jobGroup, object, method, new Date());
            MethodHandler methodHandler = new Mirror().on(object).invoke().method(method);
            //执行
            if (null != jobName && !jobName.equals("")) {
                methodHandler.withArgs(jobName);
            } else {
                methodHandler.withoutArgs();
            }
        } catch (Exception ex) {
            JobExecutionException jobEx = new JobExecutionException(ex);
            throw jobEx;
        }
    }
}
