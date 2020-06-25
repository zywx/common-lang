package com.github.zywx.commons.basic.result.error;

import lombok.Getter;

/**
 * A simple class desc
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午10:01
 */
public enum ErrorEnum {
    /**
     * 1-100 系统错误
     */
    COMMONS_ERROR_SYSTERM_EXCEPTION(1, "系统异常"),
    COMMONS_ERROR_INVALID_ARGUMENT(2, "参数无效"),
    COMMONS_ERROR_GATEWAY_INVOKE_ERROR(3, "调用网关接口失败"),
    ;

    @Getter
    private Integer msgCode;

    @Getter
    private String msgInfo;

    ErrorEnum(Integer msgCode, String msgInfo) {
        this.msgCode = msgCode;
        this.msgInfo = msgInfo;
    }
}
