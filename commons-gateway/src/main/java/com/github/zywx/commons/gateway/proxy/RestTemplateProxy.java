package com.github.zywx.commons.gateway.proxy;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP Rest
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午11:16
 */
@Service
@Slf4j
public class RestTemplateProxy {

    @Resource
    @Setter
    private RestTemplate restTemplate;

    /**
     * POST 请求
     * @param url
     * @param request
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) {
        return restTemplate.postForObject(url, request, responseType, uriVariables);
    }

    /**
     * GET 请求
     * @param url
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) {
        return restTemplate.getForObject(url, responseType, uriVariables);
    }
}
