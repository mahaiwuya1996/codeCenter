package com.config.server.test;

import com.config.server.mapstruct.CovertBasic;
import com.config.server.pojo.ReqLog;
import com.config.server.utils.SnowFlakeUtil;
import com.config.server.vo.ReqLogVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/16 11:04
 */
public class TEST1 {

    public static void main(String[] args) {
        long snowFlakeId = SnowFlakeUtil.getSnowFlakeId();
        System.out.println(snowFlakeId);

        ReqLog get = ReqLog.builder().ip("127.0.0.1").requestParam("123").requestType("GET").requestUrl("/api/user").build();

        ReqLogVo reqLogVo = CovertBasic.COVERT_BASIC.poToVo(get);
       System.out.println(reqLogVo);
        System.out.println(LocalDateTime.now().getDayOfWeek().getValue());
        String string = LocalDate.parse("20201030", DateTimeFormatter.ofPattern("yyyyMMdd")).format(DateTimeFormatter.ofPattern("MM-dd"));
        System.out.println(string);

    }
}
