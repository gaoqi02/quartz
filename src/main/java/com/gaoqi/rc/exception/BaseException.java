package com.gaoqi.rc.exception;

/**
 * Created by qigao212074 on 2016/8/25.
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 8002283590289218255L;

    public String msg;
    public int code;

    public BaseException(){}

    protected BaseException(int code, String arg0, Throwable arg1) {
        super(arg0, arg1);
        this.code = code;
        this.msg = arg0;
    }

    public BaseException(int code) {
        this.code = code;
        this.msg = BaseExceptionEnum.getDescStrByCode(code);
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

}
