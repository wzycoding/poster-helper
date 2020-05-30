package com.wzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 */
@SpringBootApplication
// 这里一定要导入tk开头的包否则就会报错
@MapperScan(basePackages = "com.wzy.mapper")
@EnableScheduling // 开启定时任务
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
