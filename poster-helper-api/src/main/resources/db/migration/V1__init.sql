CREATE TABLE if not exists  `users` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id 用户id',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(128) DEFAULT NULL COMMENT '真实姓名',
  `face` varchar(1024) NOT NULL COMMENT '头像',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱地址 邮箱地址',
  `sex` int(11) DEFAULT NULL COMMENT '性别 性别 1:男  0:女  2:保密',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `login_count` int(11) default 0 COMMENT '用户登录次数',
  `operator_count` int(11) default 0 COMMENT '生成海报次数',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表 ';

CREATE TABLE if not exists  `poster` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id 用户id',
  `name` varchar(32) NOT NULL COMMENT '商品名称',
  `price_discount` int(11) NOT NULL COMMENT '福利价',
  `price_normal` int(11) NOT NULL COMMENT '原价',
  `description`  varchar(255) NOT NULL default '' COMMENT '商品描述',
  `img_url`  varchar(255) NOT NULL COMMENT '商品描述',
  `create_user_id` bigint NOT NULL COMMENT '创建用户id',
  `discount_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '特价日期',
  `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表 ';