package com.gaoqi.rc.service;

import com.gaoqi.rc.domain.QuartzJobVo;
import com.sohu.rc.model.LoginInfo;
import com.gaoqi.rc.model.RcQuartzJob;

import java.util.List;

/**
 * Created by qigao212074 on 2016/9/9.
 */
public interface IQuartzJobService {

    /**
     * 获取指定任务的详情，包含任务详情和定时触发器详情
     *
     * @param jobId
     * @param user
     * @return
     */
    QuartzJobVo getJob(int jobId, LoginInfo user);

    /**
     * 添加一个任务至Quartz
     *
     * @param quartzJob
     * @param user
     */
    int newJob(RcQuartzJob quartzJob, LoginInfo user);

    /**
     * 获取用户，在某个模块下的定时任务
     *
     * @param user
     * @return
     */
    List<RcQuartzJob> getJobsByUser(LoginInfo user, String module);

    /**
     * 创建一个定时任务
     *
     * @param quartzJobVo
     * @param user
     */
    void createScheduleJob(QuartzJobVo quartzJobVo, LoginInfo user);
}
