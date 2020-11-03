package com.config.server.enums;
/**
 *
  * @描述:数据操作枚举
  * @Author:杨新宇
 *  @Date: 2020/8/31 15:14
 */
public enum ResultEnum {

    SUCCESS(00,"请求成功"),

    FAILURE(01,"请求失败"),

    UPDATE_ERROR(-102,"更新失败"),

    DELETE_ERROR(-103,"删除失败"),

    UNKNOWN_ERROR(-104,"未知异常"),

    PARAMS_ERROR(-105,"参数异常"),

    THIRD_ERROR(-106,"第三方接口异常"),


    /**
     * 参数类型不匹配
     */
    ARGUMENT_TYPE_MISMATCH(-107, "参数类型不匹配"),
    /**
     * 请求方式不支持
     */
    REQ_METHOD_NOT_SUPPORT(-108,"请求方式不支持");

    /**错误码*/
    private Integer code;
    /**错误详情*/
    private String value;

    ResultEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


    /**
     * 通过状态码获取枚举对象
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnum getByCode(int code){
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if(code == resultEnum.getCode()){
                return resultEnum;
            }
        }
        return null;
    }


}
