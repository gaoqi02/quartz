package com.gaoqi.rc.domain;

import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzJob;

import java.util.List;

/**
 * 对象中主要包含
 *              quartzJob 任务的详情
 *
 *              quartzSchedule 任务对应的某个触发器，在调用触发器执行任务时使用
 *
 *              List<quartzSchedule> 时间触发器的详情
 *              由于一个任务可以对应多个时间触发器所以需要用list展示
 *
 * Created by qigao212074 on 2016/9/9.
 */
public class QuartzJobVo {

    private RcQuartzJob quartzJob;                 //任务详情
    private List<RcQuartzSchedule> quartzSchedules;//任务对应的时间触发器
    private int scheduleId;                        //策略的详情id，由于状态不断变化需要根据数据库中信息处理
                                                   // ，不可以根据QuartzContext内信息处理
    private RcQuartzSchedule schedule;

    public QuartzJobVo(){}


    public QuartzJobVo(RcQuartzJob quartzJob, List<RcQuartzSchedule> quartzSchedules) {
        this.quartzJob = quartzJob;
        this.quartzSchedules = quartzSchedules;
    }

    public QuartzJobVo(RcQuartzJob quartzJob, RcQuartzSchedule schedule, int scheduleId) {
        this.quartzJob = quartzJob;
        this.schedule = schedule;
        this.scheduleId = scheduleId;
    }

    public RcQuartzSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(RcQuartzSchedule schedule) {
        this.schedule = schedule;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public RcQuartzJob getQuartzJob() {
        return quartzJob;
    }

    public void setQuartzJob(RcQuartzJob quartzJob) {
        this.quartzJob = quartzJob;
    }

    public List<RcQuartzSchedule> getQuartzSchedules() {
        return quartzSchedules;
    }

    public void setQuartzSchedules(List<RcQuartzSchedule> quartzSchedules) {
        this.quartzSchedules = quartzSchedules;
    }
}
