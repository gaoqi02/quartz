package com.gaoqi.rc.model;

import java.io.Serializable;
import java.util.Date;

public class RcQuartzSchedule implements Serializable {
    private Integer id;

    private Integer jobId;

    private Date createTime;

    private Byte state;

    private Date startTime;

    private Byte isRepeat;

    private Integer repeatTimes;

    private Integer repeatSeconds;

    private Date endTime;

    private Date nextTime;

    private String description;

    private Byte status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Byte getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(Byte isRepeat) {
        this.isRepeat = isRepeat;
    }

    public Integer getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(Integer repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    public Integer getRepeatSeconds() {
        return repeatSeconds;
    }

    public void setRepeatSeconds(Integer repeatSeconds) {
        this.repeatSeconds = repeatSeconds;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", jobId=").append(jobId);
        sb.append(", createTime=").append(createTime);
        sb.append(", state=").append(state);
        sb.append(", startTime=").append(startTime);
        sb.append(", isRepeat=").append(isRepeat);
        sb.append(", repeatTimes=").append(repeatTimes);
        sb.append(", repeatSeconds=").append(repeatSeconds);
        sb.append(", endTime=").append(endTime);
        sb.append(", nextTime=").append(nextTime);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}