package com.config.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/23 16:32
 */
@RestController
@RequestMapping("api/v1/user")
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam String loginForm) {
        return loginForm + ":登录成功";
    }
}
