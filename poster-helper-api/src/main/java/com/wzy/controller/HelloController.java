package com.wzy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 */
@RestController
@RequestMapping("/api/v1.0/hello")
public class HelloController {

    /**
     * 测试接口
     */
    @GetMapping("say")
    public String say() {
        return "say hello!";
    }
}
