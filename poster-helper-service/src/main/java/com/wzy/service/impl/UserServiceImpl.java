package com.wzy.service.impl;

import com.wzy.mapper.UsersMapper;
import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.vo.UserVo;
import com.wzy.service.UserService;
import com.wzy.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户service
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public void login(LoginBo bo) throws Exception {
        String username = bo.getUsername();
        String password = bo.getPassword();
        String verifyCode = bo.getVerifyCode();

        // 对用户输入密码进行md5加密
        String md5Password = MD5Utils.getMD5Str(password);
        // 1、验证用户名和密码是否匹配


        // 不匹配返回用户民或者密码错误


        // 生成token将用户信息保存到session


        // 设置cookie返回到浏览器
    }

    @Override
    public UserVo getUserInfo() {
        return null;
    }
}
