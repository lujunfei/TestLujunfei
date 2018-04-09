package com.lingdain.command;

/**
 * Created by User on 2018/3/15.
 */

import java.util.Date;

public class JsonResult<T> {
    public static int NEED_RE_LOGIN = 1;
    public static int NEED_RETRY = 2;

    private int errCode;

    private String message;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}