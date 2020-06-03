package com.wzy.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class UserVo {
    /**
     * 主键id 用户id
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

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
    private String face;

    /**
     * 手机号
     */
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

    /**
     * 用户登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 生成海报次数
     */
    @Column(name = "operator_count")
    private Integer operatorCount;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_time")
    private String lastLoginTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
