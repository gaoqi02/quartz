package com.gaoqi.rc.exception;

import com.gaoqi.rc.enums.QuartzExceptionEnum;

/**
 * 抛出的异常定义
 *
 * Created by qigao212074 on 2016/8/26.
 */
public class QuartzException extends BaseException {

    public QuartzException(int code, String msg) {
        if (msg == null) {
            msg = QuartzExceptionEnum.getDescStrByCode(code);
        }
        super.code = code;
        super.msg = msg;
    }

    public QuartzException(QuartzExceptionEnum jobDesignExceptionEnum) {
        super.code = jobDesignExceptionEnum.getCode();
        super.msg = jobDesignExceptionEnum.getMsg();
    }

    public QuartzException(int code) {
        super.code = code;
        QuartzExceptionEnum jobDesignExceptionEnum = QuartzExceptionEnum.getByCode(code);
        if (jobDesignExceptionEnum != null)  {
            super.msg = jobDesignExceptionEnum.getMsg();
        } else {
            super.msg = BaseExceptionEnum.getDescStrByCode(code);
        }
    }

}
