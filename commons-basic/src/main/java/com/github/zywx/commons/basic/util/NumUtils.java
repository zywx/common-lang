package com.github.zywx.commons.basic.util;

/**
 * NumUtils
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午7:35
 */
public class NumUtils {
    /**
     * id 是否有效
     * @param id
     * @return
     */
    public static boolean isVaildId(Long id) {
        return id != null && id.longValue() > 0;
    }
}
