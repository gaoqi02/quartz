package com.gaoqi.rc.enums;

/**
 * 当前对象是否有效的enum
 * Created by qigao212074 on 2016/8/26.
 */
public enum ScheduleEnum {

    RUNNING((byte) 1, "有效"),
    PAUSING((byte) 0, "无效"),
    WAITING((byte) 2, "停止");

    byte code;
    String msg;

    ScheduleEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(byte code) {
        ScheduleEnum[] payTypes = values();
        for (ScheduleEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static ScheduleEnum getByCode(byte code) {
        ScheduleEnum[] payTypes = values();
        for (ScheduleEnum payType : payTypes) {
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
