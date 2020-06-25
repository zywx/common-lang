package com.github.zywx.commons.basic.result.util;

import java.util.Map;

import com.github.zywx.commons.basic.result.XResult;
import com.github.zywx.commons.basic.result.error.ErrorEnum;
import com.github.zywx.commons.basic.result.error.ErrorInterface;

/**
 * A simple class desc
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午10:14
 */
public class ResultUtil {

    /**
     * 成功时的返回
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> XResult<T> createResultFromModel(T model) {
        XResult<T> xr = new XResult<T>();
        xr.setSuccess(true);
        xr.setModel(model);
        return xr;
    }

    /**
     * 错误时的返回，没有 model
     *
     * @param error
     * @param <T>
     * @return
     */
    public static <T> XResult<T> createResultFromErrorWithNullModel(ErrorInterface error) {
        XResult<T> xr = new XResult<T>();
        xr.setSuccess(false);
        xr.setModel(null);
        xr.setMsgCode(error.getMsgCode());
        xr.setMsgInfo(error.getMsgInfo());
        return xr;
    }

    /**
     * 错误时的返回，没有 model
     *
     * @param msgCode
     * @param msgInfo
     * @param <T>
     * @return
     */
    public static <T> XResult<T> createResultFromErrorWithNullModel(Integer msgCode, String msgInfo) {
        XResult<T> xr = new XResult<T>();
        xr.setSuccess(false);
        xr.setModel(null);
        xr.setMsgCode(msgCode);
        xr.setMsgInfo(msgInfo);
        return xr;
    }

    /**
     * 错误时的返回，带业务参数
     *
     * @param msgCode
     * @param msgInfo
     * @param msgArgs
     * @param <T>
     * @return
     */
    public static <T> XResult<T> createResultFromErrorWithNullModel(Integer msgCode, String msgInfo, Map<String,
            Object> msgArgs) {
        XResult<T> xr = new XResult<T>();
        xr.setSuccess(false);
        xr.setModel(null);
        xr.setMsgCode(msgCode);
        xr.setMsgInfo(msgInfo);
        xr.setMsgArgs(msgArgs);
        return xr;
    }

    /**
     * 异常时的系统返回
     *
     * @param e
     * @param <T>
     * @return
     */
    public static <T> XResult<T> createResultFromThrowable(Throwable e) {
        XResult<T> xr = new XResult<T>();
        xr.setSuccess(false);
        xr.setMsgCode(ErrorEnum.COMMONS_ERROR_SYSTERM_EXCEPTION.getMsgCode());
        xr.setMsgInfo(e.getMessage());
        return xr;
    }

    /**
     * 返回的 model 是否有效
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> boolean hasValidModel(XResult<T> result) {
        if (result == null) {
            return false;
        }

        if (result.isSuccess()) {
            return false;
        }
        return result.getModel() != null;
    }
}
