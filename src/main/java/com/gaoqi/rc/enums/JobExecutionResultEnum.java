package com.gaoqi.rc.enums;

/**
 * 当前对象是否有效的enum
 * Created by qigao212074 on 2016/8/26.
 */
public enum JobExecutionResultEnum {

    SUCCESS((byte)1, "有效"),
    FAILED((byte)0, "无效"),
    EXCEPTION((byte)2, "停止");

    byte code;
    String msg;

    JobExecutionResultEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(byte code) {
        JobExecutionResultEnum[] payTypes = values();
        for (JobExecutionResultEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static JobExecutionResultEnum getByCode(byte code) {
        JobExecutionResultEnum[] payTypes = values();
        for (JobExecutionResultEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType;
            }
        }
        return null;
    }

    public byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
