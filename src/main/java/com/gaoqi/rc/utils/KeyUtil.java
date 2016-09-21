package com.gaoqi.rc.utils;

import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzJob;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * Created by qigao212074 on 2016/9/9.
 */
public class KeyUtil {

    /**
     * 生成JobKey
     *
     * @param quartzJob
     *                  taskName  要调用的任务的id或者名称
     *                  taskGroup 所属于哪个模块
     * @return JobKey
     */
    public static JobKey jobKey(RcQuartzJob quartzJob){
        String taskName = quartzJob.getTaskName();
        String taskGroup = quartzJob.getTaskGroup();

        return JobKey.jobKey(taskName, taskGroup);
    }

    /**
     * 生成TriggerKey
     *
     * @param quartzJob
     *                  taskName   任务的名称或者Id
     *                  taskGroup  任务所属的模块
     * @param quartzSchedule
     *                  id         调度的id
     * @return TriggerKey
     */
    public static TriggerKey triggerKey(RcQuartzJob quartzJob, RcQuartzSchedule quartzSchedule){
        String jobName = quartzJob.getTaskName();
        String scheduleId = quartzSchedule.getId().toString();
        String jobGroup = quartzJob.getTaskGroup();

        String triggerName = jobName + "_" + scheduleId;
        return TriggerKey.triggerKey(triggerName, jobGroup);
    }
}
