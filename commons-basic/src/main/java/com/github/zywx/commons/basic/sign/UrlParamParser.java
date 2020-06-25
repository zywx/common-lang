package com.github.zywx.commons.basic.sign;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * UrlParamParser
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午7:38
 */
public class UrlParamParser {
    /**
     * 将 map 转 url 参数串
     * @param paramMap
     * @return
     */
    public static String buildUrlParamsString(Map<String, String> paramMap) {
        if(paramMap == null || paramMap.isEmpty()) {
            return null;
        }

        Set<String> keySet = paramMap.keySet();
        TreeSet<String> treeSet = new TreeSet<String>();
        for (String key: keySet) {
            if(paramMap.get(key) != null) {
                treeSet.add(key);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : treeSet) {
            stringBuilder.append(s).append("=").append(paramMap.get(s)).append("&");
        }
        String url = stringBuilder.toString();
        return url.substring(0, url.length() - 1);
    }
}
