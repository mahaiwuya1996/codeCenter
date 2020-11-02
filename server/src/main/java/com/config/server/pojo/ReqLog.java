package com.config.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/23 16:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqLog {

    private String requestUrl;
    private String requestType;
    private String requestParam;
    private String ip;


}
