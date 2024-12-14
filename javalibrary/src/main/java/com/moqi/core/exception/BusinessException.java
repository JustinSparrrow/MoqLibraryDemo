package com.moqi.core.exception;

import com.moqi.core.model.ReturnNo;

public class BusinessException extends RuntimeException{
    private ReturnNo errno;

    public BusinessException(ReturnNo errno, String message) {
        super(message);
        this.errno = errno;
    }

    public BusinessException(ReturnNo errno) {
        super(errno.getMessage());
        this.errno = errno;
    }

    public ReturnNo getErrno(){
        return this.errno;
    }
}
