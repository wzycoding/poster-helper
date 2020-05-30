package com.wzy.exception;

import lombok.Data;

/**
 * 海报相关异常类
 */
@Data
public class PosterException extends RuntimeException{

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;


    public PosterException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
}
