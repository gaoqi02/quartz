package com.gaoqi.rc.model;

import java.io.Serializable;

/**
 * Created by xubr on 2016/8/31.
 */
public class LoginInfo implements Serializable {

    // 大数据平台用户集群账号关联id
    private Integer loginUserClusterId;

    // 登陆用户名
    private String loginName;

    //登陆id
    private Integer loginId;

    //集群账号名
    private String clusterName;

    //cookie里的session
    private String sessionId;

    public LoginInfo() {
    }

    public LoginInfo(Integer loginUserClusterId, String loginName, Integer loginId, String clusterName, String sessionId) {
        this.loginUserClusterId = loginUserClusterId;
        this.loginName = loginName;
        this.loginId = loginId;
        this.clusterName = clusterName;
        this.sessionId = sessionId;
    }

    public Integer getLoginUserClusterId() {
        return loginUserClusterId;
    }

    public void setLoginUserClusterId(Integer loginUserClusterId) {
        this.loginUserClusterId = loginUserClusterId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "loginUserClusterId=" + loginUserClusterId +
                ", loginName='" + loginName + '\'' +
                ", loginId=" + loginId +
                ", clusterName='" + clusterName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
