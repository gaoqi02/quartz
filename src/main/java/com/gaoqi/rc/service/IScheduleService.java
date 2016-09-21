package com.gaoqi.rc.service;

import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.domain.QuartzJobVo;
import com.sohu.rc.model.LoginInfo;

import java.util.List;

/**
 * Created by qigao212074 on 2016/9/9.
 */
public interface IScheduleService {

    /**
     * 新建一个时间触发器
     *
     * @param quartzSchedule
     * @param user
     */
    int newSchedule(RcQuartzSchedule quartzSchedule, LoginInfo user);

    /**
     * 获取调度的信息
     *
     * @param id
     * @param user
     * @return
     */
    QuartzJobVo getScheduleById(int id, LoginInfo user);

    /**
     * 获取某个任务的调度信息
     *
     * @param jobId
     * @param user
     * @return
     */
    List<RcQuartzSchedule> getScheduleByJob(int jobId, LoginInfo user);

}
