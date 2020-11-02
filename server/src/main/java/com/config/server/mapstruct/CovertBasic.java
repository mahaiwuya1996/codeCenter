package com.config.server.mapstruct;
import com.config.server.pojo.ReqLog;
import com.config.server.vo.ReqLogVo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/28 13:51
 */
@Mapper
public interface CovertBasic {

    CovertBasic COVERT_BASIC = Mappers.getMapper(CovertBasic.class);

    @Mapping(source = "requestParam",target = "param")
    ReqLogVo poToVo(ReqLog reqLog);


}
