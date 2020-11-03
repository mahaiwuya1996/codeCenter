package com.config.server.controller;

import com.config.server.annotation.ResponseResult;
import com.config.server.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        if (userId == 0) {
            int i = 1 / 0;
        }
        return User.builder().userId(userId).name("liwei").age(23).score(99.9).build();
    }
}
