package com.wzy.service.impl;

import com.wzy.captcha.Captcha;
import com.wzy.captcha.GifCaptcha;
import com.wzy.holder.RequestHolder;
import com.wzy.mapper.UsersMapper;
import com.wzy.pojo.Users;
import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.bo.RegisterBo;
import com.wzy.pojo.vo.UserVo;
import com.wzy.service.UserService;
import com.wzy.utils.CookieUtils;
import com.wzy.utils.ErrorEnum;
import com.wzy.utils.MD5Utils;
import com.wzy.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户service
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String TOKEN_PREFIX = "_token";
    public static final String VERIFY_PREFIX = "_code";

    @Resource
    private UsersMapper usersMapper;

    @Override
    public void login(HttpServletRequest request,
                      HttpServletResponse response, LoginBo bo) throws Exception {
        String username = bo.getUsername();
        String password = bo.getPassword();
        String verifyCode = bo.getVerifyCode();

        // 校验验证码
        String _code = CookieUtils.getCookieValue(request, VERIFY_PREFIX);
        HttpSession currentSession = request.getSession();
        String realVerifyCode = (String) currentSession.getAttribute(VERIFY_PREFIX + _code);
//        if (!verifyCode.equals(realVerifyCode)) {
//            ErrorEnum.VERIFY_CODE_ERROR.throwException();
//        }

        // 对用户输入密码进行md5加密
        String md5Password = MD5Utils.getMD5Str(password);
        // 1、验证用户名和密码是否匹配
        Users userCondition = new Users();
        userCondition.setUsername(username);
        userCondition.setPassword(md5Password);

        Users users = usersMapper.selectOne(userCondition);
        // 2、不匹配返回用户名或者密码错误
        if (users == null) {
            ErrorEnum.USERNAME_OR_PASSWORD_ERROR.throwException();
        }

        // 3、生成token将用户信息保存到session
        HttpSession session = request.getSession();
        String token = UUIDUtil.uuid();
        session.setAttribute(TOKEN_PREFIX + token , users);
        // 设置六个小时过期
        session.setMaxInactiveInterval(60 * 60 * 6);

        // 4、设置cookie返回到浏览器
        Cookie cookie = new Cookie(TOKEN_PREFIX, token);
        // 设置六个小时过期
        cookie.setMaxAge(60 * 60 * 6);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
    }

    @Override
    public UserVo getUserInfo() {
        Users currentUser = RequestHolder.getCurrentUser();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(currentUser, userVo);
        return userVo;
    }

    @Override
    public void getVerifyCode(HttpServletResponse response) throws IOException {
        Captcha captcha = new GifCaptcha(100,40,4);//   gif格式动画验证码
        String uuid = UUIDUtil.uuid();
        addCookie(response, uuid);
        captcha.out(response.getOutputStream());
        String verifyCode = captcha.text();
        String realKey = VERIFY_PREFIX + uuid;
        HttpServletRequest currentRequest = RequestHolder.getCurrentRequest();
        HttpSession session = currentRequest.getSession();
        session.setAttribute(realKey, verifyCode);
        // 设置三分钟过期
        session.setMaxInactiveInterval(60 * 3);
    }

    @Override
    public void register(RegisterBo registerBo) throws Exception {
        String password = registerBo.getPassword();

        String md5Password = MD5Utils.getMD5Str(password);
        registerBo.setPassword(md5Password);
        Users createUser = new Users();
        BeanUtils.copyProperties(registerBo, createUser);
        // 插入用户数据
        usersMapper.insertSelective(createUser);
    }

    private void addCookie(HttpServletResponse response, String uuid) {
        Cookie cookie = new Cookie(VERIFY_PREFIX, uuid);
        // 设置三分钟过期
        cookie.setMaxAge(60 * 3);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
    }
}
