package com.config.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/14 10:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String name;
    private int age;
    private double score;
}
