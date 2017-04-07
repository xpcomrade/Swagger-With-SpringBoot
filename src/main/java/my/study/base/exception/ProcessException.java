package my.study.base.exception;

/**
 * Created by xpcomrade on 2016/9/12.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (运行时异常). <br/>
 */
public class ProcessException extends RuntimeException {

    public static final int CODE_UNKNOWN_ERROR = 500;

    protected int code;

    public ProcessException(int code) {
        this.code = code;
    }

    public ProcessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ProcessException(String message, Throwable e) {
        super(message, e);
        this.code = CODE_UNKNOWN_ERROR;
    }

    public ProcessException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public ProcessException(int code, Throwable e) {
        super(e);
        this.code = code;
    }

    public ProcessException(Throwable e) {
        super(e);
        this.code = CODE_UNKNOWN_ERROR;
    }

    public ProcessException(String message) {
        super(message);
        this.code = CODE_UNKNOWN_ERROR;
    }

    public int getCode() {
        return code;
    }
}
