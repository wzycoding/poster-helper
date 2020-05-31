package com.wzy.pojo.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class RegisterBo {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String face;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birthday;
}
