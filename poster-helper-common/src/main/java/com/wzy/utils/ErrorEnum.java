package com.wzy.utils;

import com.wzy.exception.PosterException;

public enum ErrorEnum {
    /****************通用错误码*********************/
    SERVER_ERROR(10001, "服务器错误"),
    PARAM_ERROR(10002, "参数错误"),
    VERIFY_CODE_ERROR(10003, "验证码错误"),
    UPLOAD_IMG_ERROR(10004, "图片格式错误"),
    UPLOAD_NOT_BLANK(10005, "上传文件不能为空"),


    /****************用户错误码*********************/
    NOT_LOGIN(20101, "请您登录后进行操作！"),
    LOGIN_ILLEGAL(20102, "请您进行合法登录！"),
    USERNAME_OR_PASSWORD_ERROR(20103, "用户名或密码错误！");


    /****************海报错误码*********************/
//    NOT_LOGIN(20201, "请您登录后进行操作！");



    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void throwException() {
        throw new PosterException(this.getCode(), this.msg);
    }

}
