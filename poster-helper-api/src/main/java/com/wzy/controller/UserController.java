package com.wzy.controller;

import com.wzy.pojo.bo.LoginBo;
import com.wzy.pojo.vo.UserVo;
import com.wzy.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 */
@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBo loginBo) {

        return Result.ok();
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {

        UserVo userVo = new UserVo();
        return Result.ok(userVo);
    }
}
