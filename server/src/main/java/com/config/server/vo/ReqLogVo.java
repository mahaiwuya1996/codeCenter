package com.config.server.vo;

import lombok.Data;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/23 16:51
 */
@Data
public class ReqLogVo {

    private String requestUrl;
    private String requestType;
    private String param;
    private String ip;


}
