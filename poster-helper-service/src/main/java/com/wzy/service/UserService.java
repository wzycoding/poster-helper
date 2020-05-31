package com.wzy.service;

import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.bo.RegisterBo;
import com.wzy.pojo.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

    /**
     * 登录方法
     * @param loginBo 登录入参
     */
    void login(HttpServletRequest request,
               HttpServletResponse response, LoginBo loginBo) throws Exception;

    /**
     * 获取用户信息
     * @return 用户信息
     */
    UserVo getUserInfo();

    /**
     * 获取验证码信息
     */
    void getVerifyCode(HttpServletResponse response) throws IOException;

    /**
     * 注册用户
     * @param registerBo 用户注册入参
     */
    void register(RegisterBo registerBo) throws Exception;
}
