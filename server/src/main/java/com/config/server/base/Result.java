package com.config.server.base;

import com.config.server.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/11/2 13:47
 */
@Data
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public  Result(){

    }

//    public Result(ResultEnum resultEnum, Object data) {
//        this.code = resultEnum.getCode();
//        this.message = resultEnum.getValue();
//        this.data = data;
//    }

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getValue());
        return result;
    }

    public static Result success(Object data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result failure(Integer code,String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result failure(String message) {
        Result result = new Result();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMessage(message);
        return result;
    }

    public static Result failure(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getValue());
        return result;
    }

    public static Result failure(ResultEnum resultEnum, Object data) {
        Result result = failure(resultEnum);
        result.setData(data);
        return result;

    }
}
