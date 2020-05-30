package com.wzy.service;

import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.vo.UserVo;

public interface UserService {

    /**
     * 登录方法
     * @param loginBo 登录入参
     */
    void login(LoginBo loginBo) throws Exception;

    /**
     * 获取用户信息
     * @return 用户信息
     */
    UserVo getUserInfo();
}
