package com.gaoqi.rc.utils;

import com.gaoqi.rc.enums.QuartzExceptionEnum;
import com.gaoqi.rc.enums.RepeatEnum;
import com.gaoqi.rc.exception.QuartzException;
import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzJob;
import org.quartz.*;

import static org.quartz.SimpleScheduleBuilder.repeatHourlyForever;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 时间触发器创建
 *
 * Created by qigao212074 on 2016/9/9.
 */
public class TriggerUtil {

    /**
     * 构造Quartz 的 时间触发器 Trigger
     *
     * @param job 作业信息
     * @param schedule 调度信息
     * @return Trigger 触发器
     */
    public static Trigger build(RcQuartzJob job, RcQuartzSchedule schedule) {
        //Job&Trigger Key
        JobKey jobKey = KeyUtil.jobKey(job);
        TriggerKey triggerKey = KeyUtil.triggerKey(job, schedule);
        //创建 触发器
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey).forJob(jobKey)
                .startAt(schedule.getStartTime());
        //如果可重复并且有endTime
        /**
         * TODO 需要测试misfire的规则
         * SimpleTrigger的misfire策略
         * withMisfireHandlingInstructionFireNow：会修改调度的时刻
         *          以当前时间为触发频率立即触发执行，以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
         * withMisfireHandlingInstructionIgnoreMisfires： 没必要重做错过的所有周期
         *          以错过的第一个频率时间立刻开始执行，重做错过的所有频率周期
         * withMisfireHandlingInstructionNextWithExistingCount： 可以可以
         *          不触发立即执行，等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
         * withMisfireHandlingInstructionNowWithExistingCount：  修改了调度的时间点
         *          以当前时间为触发频率立即触发执行，执行至FinalTIme的剩余周期次数
         * withMisfireHandlingInstructionNextWithRemainingCount：
         *          不触发立即执行，等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
         */

        /**
         * 调度规则,下面的CronTrigger的构造方法，未来可能会用到。
         *
         * CronTrigger的misfire模式
         * withMisfireHandlingInstructionDoNothing：不触发立即执行，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
         * withMisfireHandlingInstructionIgnoreMisfires： 重做错过的所有频率周期后，当下一次触发频率
         *                                                发生时间大于当前时间后，再按照正常的Cron频率依次执行
         * withMisfireHandlingInstructionFireAndProceed： 以当前时间为触发频率立刻触发一次执行，然后按照Cron频率依次执行
         */
        if (schedule.getIsRepeat() == Integer.valueOf(RepeatEnum.TRUE.getCode()).intValue()) {
            if (schedule.getRepeatTimes() != 0) {
                triggerBuilder.withSchedule(
                        //从0开始。  传5  会运行6次
                        simpleSchedule().withRepeatCount(schedule.getRepeatTimes() - 1)
                                .withIntervalInSeconds(schedule.getRepeatSeconds())
                                .withMisfireHandlingInstructionNextWithRemainingCount());
            } else {
                triggerBuilder.withSchedule(
                        //从0开始。  传5  会运行6次
                        simpleSchedule().repeatForever()
                                .withIntervalInSeconds(schedule.getRepeatSeconds())
                                .withMisfireHandlingInstructionNextWithRemainingCount());
            }
        } else {
            triggerBuilder.withSchedule(simpleSchedule().withMisfireHandlingInstructionNextWithRemainingCount());
        }

        if (null != schedule.getEndTime()) {
            triggerBuilder.endAt(schedule.getEndTime());
        }

        return triggerBuilder.build();

//        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getCronExp())
//                .withMisfireHandlingInstructionFireAndProceed();
//        SimpleTrigger trigger = newTrigger()
//                .withIdentity("triggerFor_longRunningBatchScheduledJob", "MetricsCollectors")
//                .withSchedule(simpleSchedule().withIntervalInMilliseconds(500L))
//                .forJob("gaoqi")
//                .startAt(new Date())
//                .build();
//        triggerBuilder.withSchedule(simpleTrigger);
//
//        //构造并返回
//        return triggerBuilder.build();
    }

    public static void checkTrigger(RcQuartzSchedule schedule) {
        if (null == schedule.getStartTime()) {
            throw new QuartzException(QuartzExceptionEnum.START_TIME_CANT_BE_NULL);
        }
        if (null != schedule.getEndTime()) {
            if (schedule.getStartTime().after(schedule.getEndTime())) {
                throw new QuartzException(QuartzExceptionEnum.END_TIME_BEFORE_START_TIME);
            }
        }
    }
}
