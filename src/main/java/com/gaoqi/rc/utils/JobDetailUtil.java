package com.gaoqi.rc.utils;

import com.gaoqi.rc.common.Constant;
import com.gaoqi.rc.core.job.BaseJob;
import com.gaoqi.rc.enums.ConcurrentEnum;
import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.core.job.SerialJob;
import com.gaoqi.rc.domain.QuartzJobVo;
import com.gaoqi.rc.model.RcQuartzJob;
import org.quartz.*;

/**
 * jobDetail 是job的一个实例，每次会instance baseJob 然后将我们的配置注入到baseJob里
 *
 * Created by qigao212074 on 2016/9/9.
 */
public class JobDetailUtil {

    /**
     * 构造JobDetail
     *
     * @param job 作业信息
     * @param schedule 调度信息
     * @return JobDetail
     */
    public static JobDetail build(RcQuartzJob job, RcQuartzSchedule schedule) {
        //JobBuilder
        JobBuilder jobBuilder = null;
        String isConcurrent =
                (job.getIsConcurrent().byteValue() == ConcurrentEnum.DISALLOW_CONCURRENT.getCode()) ? "N":"Y";
        if("Y".equals(isConcurrent)){
            jobBuilder = JobBuilder.newJob(BaseJob.class);
        } else {
            jobBuilder = JobBuilder.newJob(SerialJob.class);
        }
        //JobKey
        JobKey jobKey = KeyUtil.jobKey(job);
        jobBuilder.withIdentity(jobKey);
        //数据
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(Constant.KEY_JOB_CONTENT, new QuartzJobVo(job, null, schedule.getId()));
        jobBuilder.usingJobData(dataMap);
        jobBuilder.requestRecovery(true);

        return jobBuilder.build();
    }

    public static void setExecId(JobExecutionContext jobExecutionContext, int execId) {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        dataMap.put(Constant.KEY_JOB_EXEC_ID, execId);
    }

    public static int getExecId(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        return Integer.parseInt(dataMap.get(Constant.KEY_JOB_EXEC_ID).toString());
    }

    public static QuartzJobVo getJobContent(JobExecutionContext context){
        JobDataMap dataMap =  context.getMergedJobDataMap();
        return (QuartzJobVo) dataMap.get(Constant.KEY_JOB_CONTENT);
    }

    /**
     * 设置
     *
     * @param context
     * @param remark
     */
    public static void setJobResultRemark(JobExecutionContext context, String remark){
        JobDataMap dataMap =  context.getMergedJobDataMap();
        dataMap.put(Constant.KEY_JOB_RESULT_REMARK, remark);
    }

    /**
     * 获取
     *
     * @param context
     * @return String
     */
    public static String getJobResultRemark(JobExecutionContext context){
        JobDataMap dataMap =  context.getMergedJobDataMap();
        return (String) dataMap.get(Constant.KEY_JOB_RESULT_REMARK);
    }
}
