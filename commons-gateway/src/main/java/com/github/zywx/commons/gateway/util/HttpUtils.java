package com.github.zywx.commons.gateway.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * http util
 *
 * @author fsnail.wang@gmail.com
 * @date 2020/6/25 下午11:26
 */
@Slf4j
public class HttpUtils {
    private static final String HTTP_RESPONSE_CHARSET = "UTF-8";

    /**
     * Get 请求
     * @param url
     * @param timeoutConn
     * @param timeoutSocket
     * @param charSet
     * @return
     */
    public static String doGet(String url, int timeoutConn, int timeoutSocket, String charSet) {
        String responseString = null;
        try {
            BasicHttpParams httpParams = new BasicHttpParams();
            HttpClientParams.setRedirecting(httpParams, true);

            String userAgent =
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83"
                            + ".0.4103.97 Safari/537.36";
            HttpProtocolParams.setUserAgent(httpParams, userAgent);

            HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConn);
            HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);

            DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpGet httpGet = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() == 200 && entity != null) {
                if(charSet == null) {
                    charSet = HTTP_RESPONSE_CHARSET;
                }
                responseString = EntityUtils.toString(entity, charSet);
            }
        } catch (IOException e) {
            log.warn("{}|{}|{}", "doGet", url, e.getMessage());
        }
        return  responseString;
    }

    /**
     * Post from params
     * @param url
     * @param params
     * @param timeoutConn
     * @param timeoutSocket
     * @return
     */
    public static String doPostFromParams(String url, Map<String, String> params, int timeoutConn, int timeoutSocket) {
        String responseString = null;
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpClientParams.setRedirecting(httpParams, true);

        String userAgent =
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83"
                        + ".0.4103.97 Safari/537.36";
        HttpProtocolParams.setUserAgent(httpParams, userAgent);

        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConn);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);

        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(String key : params.keySet()) {
            pairs.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP_RESPONSE_CHARSET));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseString = EntityUtils.toString(entity, HTTP_RESPONSE_CHARSET);
            }
        } catch (Exception e) {
            log.warn("{}|{}|{}", "doPostFromParams", url, e.getMessage());
        }

        return responseString;
    }

    /**
     * Post from body
     * @param url
     * @param jsonParams
     * @param timeoutConn
     * @param timeoutSocket
     * @return
     */
    public static String doPostFromBody(String url, String jsonParams, int timeoutConn, int timeoutSocket) {
        String responseString = null;
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpClientParams.setRedirecting(httpParams, true);

        String userAgent =
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83"
                        + ".0.4103.97 Safari/537.36";
        HttpProtocolParams.setUserAgent(httpParams, userAgent);

        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConn);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);

        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(jsonParams, HTTP_RESPONSE_CHARSET));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseString = EntityUtils.toString(entity, HTTP_RESPONSE_CHARSET);
            }
        } catch (Exception e) {
            log.warn("{}|{}|{}", "doPostFromBody", url, e.getMessage());
        }

        return responseString;
    }
}
