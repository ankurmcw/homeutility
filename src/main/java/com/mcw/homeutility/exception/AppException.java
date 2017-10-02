package com.mcw.homeutility.exception;

/**
 * Created by renuka on 2/10/17.
 */
public class AppException extends Exception {

    private int status;

    private String message;

    public AppException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public AppException(int status, String message, Throwable e) {
        super(message, e);
        this.status = status;
        this.message = message;
    }
}
