package com.sdkj.util;

import java.io.IOException;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;

public class JsonUtil {
    /**
     * VO转化成Json串，返回的字符串带类名，
     * 例如：{"user":{"name":"姓名","sex":"男"}}
     * @param bean 要转化的VO bean
     * @param className 类名，首字母小写
     * @return
     */
    public static <T> String beanToJson(T bean, String className){
        Object jsonObject = JSON.toJSON(bean);
        JSONObject json = new JSONObject();
        json.put(className, jsonObject);
        return json.toJSONString();
    }
 
    /**
     * Json字符串转化为VO，此Json串带类名，
     * 例如：{"user":{"name":"姓名","sex":"男"}}
     * @param jsonString 要转化的Json字符串
     * @param clazz VO类型
     * @param className VO类名，首字母小写
     * @return
     */
    public static <T> T jsonToBean(String jsonString, Class<T> clazz, String className) {
        JSONObject json = JSON.parseObject(jsonString);
        Objects.requireNonNull(json.get(className),"json数据,"+className+"字段为空");
        T bean = JSON.parseObject(json.get(className).toString(), clazz);
        return bean;
    }
    
 

}
