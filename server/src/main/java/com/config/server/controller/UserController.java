package com.config.server.controller;

import com.config.server.annotation.ResponseResult;
import com.config.server.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/23 16:32
 */
@RestController
@RequestMapping("api/v1/user")
@ResponseResult
public class UserController {
    @GetMapping("/user")
    public User getUser() {
        return User.builder().name("liwei").age(23).score(99.9).build();
    }
}
