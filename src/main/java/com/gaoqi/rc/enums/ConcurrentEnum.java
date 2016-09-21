package com.gaoqi.rc.enums;

/**
 * 当前对象是否有效的enum
 * Created by qigao212074 on 2016/8/26.
 */
public enum ConcurrentEnum {

    ALLOW_CONCURRENT((byte) 1, "可以并行"),
    DISALLOW_CONCURRENT((byte) 0, "不可并行");

    byte code;
    String msg;

    ConcurrentEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(int code) {
        ConcurrentEnum[] payTypes = values();
        for (ConcurrentEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static ConcurrentEnum getByCode(int code) {
        ConcurrentEnum[] payTypes = values();
        for (ConcurrentEnum payType : payTypes) {
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
