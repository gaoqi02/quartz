package com.gaoqi.rc.enums;

/**
 * 当前对象是否有效的enum
 * Created by qigao212074 on 2016/8/26.
 */
public enum RepeatEnum {

    TRUE((byte) 1, "重复任务"),
    FALSE((byte) 0, "非重复任务");

    byte code;
    String msg;

    RepeatEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(int code) {
        RepeatEnum[] payTypes = values();
        for (RepeatEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static RepeatEnum getByCode(int code) {
        RepeatEnum[] payTypes = values();
        for (RepeatEnum payType : payTypes) {
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
