package com.wzy.controller;

import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.vo.UserVo;
import com.wzy.service.UserService;
import com.wzy.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public Result login(@RequestBody @Valid LoginBo loginBo) throws Exception {
        userService.login(loginBo);
        return Result.success();
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {

        UserVo userVo = new UserVo();
        return Result.success(userVo);
    }
}
