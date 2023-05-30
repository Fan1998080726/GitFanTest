package com.wx.util;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static int DATA_JSON = 1;

    public static int DATA_FORM = 2;

    private static String JSON_CONTENT_TYPE = "application/json";

    private static String CHARACTER = "UTF-8";

    private static String CONTENT_TYPE_NAME = "Content-Type";

    public static String get(String url) {
        return send(new HttpGet(url));
    }

    public static String post(String url, Map<String, Object> param, Integer dataType) {
        return post(url, param, dataType, null);
    }

    public static String post(String url, Map<String, Object> param, Integer dataType, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);
        if (dataType == DATA_FORM) {
            Iterator localIterator;
            Map.Entry<String, String> entries;
            if (headers != null)
                for (localIterator = headers.entrySet().iterator(); localIterator.hasNext(); ) {
                    entries = (Map.Entry) localIterator.next();
                    post.setHeader((String) entries.getKey(), (String) entries.getValue());
                }

            Object paramList = new ArrayList();
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                ((List) paramList).add(new BasicNameValuePair((String) entry.getKey(), entry.getValue().toString()));
            }
            try {
                post.setEntity(new org.apache.http.client.entity.UrlEncodedFormEntity((List) paramList));
            } catch (UnsupportedEncodingException e) {
                LOGGER.info(e.getMessage());
            }
        } else if (dataType.intValue() == DATA_JSON) {
            post.setHeader(CONTENT_TYPE_NAME, JSON_CONTENT_TYPE);
            post.setEntity(new StringEntity(JSON.toJSONString(param), CHARACTER));
        }
        return send(post);
    }

    private static String send(HttpRequestBase request) {
        CloseableHttpClient client = org.apache.http.impl.client.HttpClients.createDefault();
        String result = null;
        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } else {
                result = String.valueOf(status);
            }
            LOGGER.info("http response ------------" + result);
            response.close();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return result;
    }

}

