package com.github.zywx.commons.basic.result;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * Result
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午10:08
 */
@Data
public class XResult<T> implements Serializable {

    private static final long serialVersionUID = -7941247872956955826L;

    /**
     * 响应状态
     */
    private boolean success = true;

    /**
     * 响应实体
     */
    private T model;

    /**
     * 错误码
     */
    private Integer msgCode;

    /**
     * 错误信息
     */
    private String msgInfo;

    /**
     * 业务参数
     */
    private Map<String, Object> msgArgs;
}
