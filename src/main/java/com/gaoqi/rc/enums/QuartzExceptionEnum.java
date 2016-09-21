package com.gaoqi.rc.enums;

/**
 * 定时任务模块中错误的enums  code 500900--500999
 *
 * Created by qigao212074 on 2016/8/26.
 */
public enum QuartzExceptionEnum {

    NONE_JOB(500901, "没有对应的任务"),
    DUPLICATE_JOB(500902, "数据库中已包含重复任务"),
    NONE_METHOD_OR_OBJECT(500903, "调用的方法和任务名不可为空"),
    IN_VALID_JOB(500904, "无效任务无法操作"),
    IN_VALID_SCHEDULE(500905, "无效定时器无法操作"),
    JOB_ALREADY_SCHEDULE(500906, "该任务的对应定时器正在工作，请勿重复操作"),
    ERROR_IN_QUARTZ(500907, "QUARTZ模块有异常"),
    NONE_TRIGGER(500908, "QUARTZ中无对应的时间触发器"),
    DELETE_JOB_FROM_QUARTZ_ERROR(500909, "从QUARTZ中删除任务失败"),
    CRON_EXP_ERROR(500909, "请按规则确定CRON表达式"),
    ERROR_JOB_USER(500910, "您无权查看该任务详情"),
    END_TIME_BEFORE_START_TIME(500911, "结束时间早于开始时间"),
    START_TIME_CANT_BE_NULL(500912, "开始时间不可为空"),
    Test(500900, "测试错误");

    int code;
    String msg;

    QuartzExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(int code) {
        QuartzExceptionEnum[] payTypes = values();
        for (QuartzExceptionEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static QuartzExceptionEnum getByCode(int code) {
        QuartzExceptionEnum[] payTypes = values();
        for (QuartzExceptionEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
