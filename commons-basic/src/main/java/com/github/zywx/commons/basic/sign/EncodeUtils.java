package com.github.zywx.commons.basic.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

/**
 * EncodeUtils 编码工具
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午9:17
 */
public class EncodeUtils {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * Base64编码
     * @param input
     * @return
     */
    public static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * Base64编码 URL 安全（URL非法字符如+/=转为其他字符）
     * @param input
     * @return
     */
    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码
     * @param input
     * @return
     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * URL 编码
     * @param input
     * @return
     */
    public static String urlEncode(String input) {
        try {
             return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL 解码
     * @param input
     * @return
     */
    public static String urlDecode(String input) {
        try {
            return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }
}
