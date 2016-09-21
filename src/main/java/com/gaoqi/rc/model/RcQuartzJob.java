package com.gaoqi.rc.model;

import java.io.Serializable;
import java.util.Date;

public class RcQuartzJob implements Serializable {
    private Integer id;

    private String name;

    private String taskGroup;

    private String taskName;

    private String jobObject;

    private String jobMethod;

    private Byte isConcurrent;

    private Integer userId;

    private String description;

    private Byte status;

    private Date createTime;

    private String extendsJson;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup == null ? null : taskGroup.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getJobObject() {
        return jobObject;
    }

    public void setJobObject(String jobObject) {
        this.jobObject = jobObject == null ? null : jobObject.trim();
    }

    public String getJobMethod() {
        return jobMethod;
    }

    public void setJobMethod(String jobMethod) {
        this.jobMethod = jobMethod == null ? null : jobMethod.trim();
    }

    public Byte getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(Byte isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExtendsJson() {
        return extendsJson;
    }

    public void setExtendsJson(String extendsJson) {
        this.extendsJson = extendsJson == null ? null : extendsJson.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", taskGroup=").append(taskGroup);
        sb.append(", taskName=").append(taskName);
        sb.append(", jobObject=").append(jobObject);
        sb.append(", jobMethod=").append(jobMethod);
        sb.append(", isConcurrent=").append(isConcurrent);
        sb.append(", userId=").append(userId);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", extendsJson=").append(extendsJson);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}