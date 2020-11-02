package com.config.server.exception;


import com.config.server.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liwei
 * Date: Created in 2018-12-18 10:25
 * Utils: Intellij Idea
 * Description: 自定义异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 方法名称
     */
    private final String method;


    /**
     * 自定义异常
     *
     * @param resultEnum 返回枚举对象
     * @param method     方法
     */
    public CustomException(ResultEnum resultEnum, String method) {
        super(resultEnum.getValue());
        this.code = resultEnum.getCode();
        this.method = method;
    }

    /**
     * @param code    状态码
     * @param desc 错误信息
     * @param method  方法
     */
    public CustomException(Integer code, String desc, String method) {
        super(desc);
        this.code = code;
        this.method = method;
    }

}
