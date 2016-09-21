package com.gaoqi.rc.service;

/**
 * Created by qigao212074 on 2016/9/9.
 */
public interface ITriggerService {

    /**
     * 启动某个任务
     *
     * @param scheduleId
     */
    void startTrigger(int scheduleId);

    /**
     * 删除某个触发器的任务，也就是停止这个触发器的执行
     *
     * @param scheduleId
     */
    void deleteTrigger(int scheduleId);

    /**
     * 在Quartz中将所有有关这个任务的触发都删除
     *
     * @param jobId
     */
    void deleteJob(int jobId);

    /**
     * 暂停触发器
     *
     * @param scheduleId 触发器编号
     */
    void pauseTrigger(int scheduleId);

    /**
     * 恢复触发器
     *
     * @param scheduleId 触发器编号
     */
    void resumeTrigger(int scheduleId);
}
