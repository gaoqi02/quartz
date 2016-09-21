package com.gaoqi.rc.enums;

/**
 * 当前对象是否有效的enum
 * Created by qigao212074 on 2016/8/26.
 */
public enum ValidEnum {

    VALID((byte) 1, "有效"),
    IN_VALID((byte) 0, "无效");

    byte code;
    String msg;

    ValidEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(int code) {
        ValidEnum[] payTypes = values();
        for (ValidEnum payType : payTypes) {
            if (payType.getCode() == code) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static ValidEnum getByCode(int code) {
        ValidEnum[] payTypes = values();
        for (ValidEnum payType : payTypes) {
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
