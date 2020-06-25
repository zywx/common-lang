package com.github.zywx.commons.basic.result.auto;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.zywx.commons.basic.result.XResult;
import com.github.zywx.commons.basic.result.error.ErrorEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 结果自动填充
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午10:36
 */
@Component
@Aspect
@Slf4j
public class ResultFill {

    private static final Logger monitorLogger = LoggerFactory.getLogger("monitor-result");

    @Around("execution(public com.github.zywx.commons.basic.result.XResult *(..))")
    public Object autoFill(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        XResult rs = null;
        try {
            rs = (XResult) joinPoint.proceed();
        } catch (Throwable e) {
            rs = new XResult();
            rs.setSuccess(false);
            rs.setMsgCode(ErrorEnum.COMMONS_ERROR_SYSTERM_EXCEPTION.getMsgCode());
            rs.setMsgInfo(e.getMessage());
        } finally {
            String name = joinPoint.getTarget().getClass().getSimpleName();
            long cost = System.currentTimeMillis() - start;
            Integer code = null;
            boolean success = false;
            if (rs != null) {
                code = rs.getMsgCode();
                success = rs.isSuccess();
            }
            monitorLogger.error("{}|{}|{}|{}", name, success, cost, code);
        }

        return rs;
    }
}
