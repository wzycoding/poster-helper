package com.wzy.controller;

import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.bo.RegisterBo;
import com.wzy.service.UserService;
import com.wzy.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 用户
 */
@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {



    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestBody @Valid LoginBo loginBo) throws Exception {
        userService.login(request, response, loginBo);
        return Result.success();
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        return Result.success(userService.getUserInfo());
    }

    /**
     * 获取验证码
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletResponse response) throws IOException {
        userService.getVerifyCode(response);
    }

    /**
     * 用户注册
     */
    @PostMapping("register")
    public Result register(@RequestBody @Valid RegisterBo registerBo) throws Exception {
        userService.register(registerBo);
        return Result.success();
    }


}
