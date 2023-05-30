package com.sdkj.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 简爱微萌
 * @Email zyw205@gmail.com
 * 
 */
public final class WeiXinApp {
    public static String appID = "wx6e407872d76226c0";
    public static String appsecret = "2ea76fa21cba2a31467c243e046bdeeb";

    public static String jgcAppID = "wx354e34f1e820222e";
    public static String jgcAppsecret = "37685e8c3a5ff8fbbf8d6da72b83f995";
    /*gh_e009bb0c341c 公众号
     * 
     * AppID   wx354e34f1e820222e
     * 37685e8c3a5ff8fbbf8d6da72b83f995
     * 
     * 
     * 31_AwhqQZxe2j5w4-zvqwCVf1gWCVy3eKM-r4tGhHWZfWGfrtZBgC72sGypcKHq5KD7VG7Kibmu8Ufm3paeohiIzWpIMuJe23YwPyzO3-mx-fsIHoNM9qecEWerVVgS16TRWV_04hjFUaYe8LHnLAYaABAAPN
     * 
     * 31_vfjPOmozKnSB6bFb2XnlNHTt21sAa2CjVyt27KNqjy_a5tPwaURrPOwGFS6mc5gmsm3o9_eOZa_CONlVwQxhn7FQCxRsANwNgA6XmqTmhZWZ41KBBKDDDY6E6ihv5apyvv1C_E0MMJ1HpddhMUPbAAAZHC
     * 
     * */
    /**
     * 得到AccessToken
     * @param APPID
     * @param APPSECRET
     * @return
     */
    public static String getAccessToken() {

        String AccessToken = "";

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appID + "&secret=" + appsecret;
//System.out.println(url);
        // 强制设置可信任证书
        Protocol myhttps = new Protocol("https",
                new MySSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.getParams().setContentCharset("utf-8");
        // 发送http请求
        String respStr = "";
        try {
            client.executeMethod(post);
            respStr = post.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!"".equals(respStr)) {
            JSONObject json = null;
            try {
                json = new JSONObject(respStr);

                AccessToken = json.getString("access_token");
            } catch (JSONException e) {
                try {
                    AccessToken = json.getString("errcode");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return AccessToken;
    }
    public static String getJgcAccessToken() {

        String AccessToken = "";

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + jgcAppID + "&secret=" + jgcAppsecret;
//System.out.println(url);
        // 强制设置可信任证书
        Protocol myhttps = new Protocol("https",
                new MySSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.getParams().setContentCharset("utf-8");
        // 发送http请求
        String respStr = "";
        try {
            client.executeMethod(post);
            respStr = post.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!"".equals(respStr)) {
            JSONObject json = null;
            try {
                json = new JSONObject(respStr);

                AccessToken = json.getString("access_token");
            } catch (JSONException e) {
                try {
                    AccessToken = json.getString("errcode");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
        System.out.println("AccessToken=="+AccessToken);
        return AccessToken;
    }    
    /**
     * 请求结果认证
     * @param code
     * @return
     */
    public static String getErrMSG(String code){
        String returnStr = "";
        int c = Integer.valueOf(code);
        //System.out.println(c);
        switch (c) {
            case -1:
                returnStr = "系统繁忙";
                break;
            case 0:
                returnStr = "请求成功";
                break;
            case 40001:
                returnStr = "验证失败";
                break;
            case 40002:
                returnStr = "不合法的凭证类型";
                break;
            case 40003:
                returnStr = "不合法的OpenID";
                break;
            case 40004:
                returnStr = "不合法的媒体文件类型";
                break;
            case 40005:
                returnStr = "不合法的文件类型";
                break;
            case 40006:
                returnStr = "不合法的文件大小";
                break;
            case 40007:
                returnStr = "不合法的媒体文件id";
                break;
            case 40008:
                returnStr = "不合法的消息类型";
                break;
            case 40009:
                returnStr = "不合法的图片文件大小";
                break;
            case 40010:
                returnStr = "不合法的语音文件大小";
                break;
            case 40011:
                returnStr = "不合法的视频文件大小";
                break;
            case 40012:
                returnStr = "不合法的缩略图文件大小";
                break;
            case 40013:
                returnStr = "不合法的APPID";
                break;
            case 40014:
                returnStr = "不合法的access_token";
                break;
            case 40015:
                returnStr = "不合法的菜单类型";
                break;
            case 40016:
                returnStr = "不合法的按钮个数";
                break;
            case 40017:
                returnStr = "不合法的按钮个数";
                break;
            case 40018:
                returnStr = "不合法的按钮名字长度";
                break;
            case 40019:
                returnStr = "不合法的按钮KEY长度";
                break;
            case 40020:
                returnStr = "不合法的按钮URL长度";
                break;
            case 40021:
                returnStr = "不合法的菜单版本号";
                break;
            case 40022:
                returnStr = "不合法的子菜单级数";
                break;
            case 40023:
                returnStr = "不合法的子菜单按钮个数";
                break;
            case 40024:
                returnStr = "不合法的子菜单按钮类型";
                break;
            case 40025:
                returnStr = "不合法的子菜单按钮名字长度";
                break;
            case 40026:
                returnStr = "不合法的子菜单按钮KEY长度";
                break;
            case 40027:
                returnStr = "不合法的子菜单按钮URL长度";
                break;
            case 40028:
                returnStr = "不合法的自定义菜单使用用户";
                break;
            case 41001:
                returnStr = "缺少access_token参数";
                break;
            case 41002:
                returnStr = "缺少appid参数";
                break;
            case 41003:
                returnStr = "缺少refresh_token参数";
                break;
            case 41004:
                returnStr = "缺少secret参数";
                break;
            case 41005:
                returnStr = "缺少多媒体文件数据";
                break;
            case 41006:
                returnStr = "缺少media_id参数";
                break;
            case 41007:
                returnStr = "缺少子菜单数据";
                break;
            case 42001:
                returnStr = "access_token超时";
                break;
            case 44001:
                returnStr = "多媒体文件为空";
                break;
            case 44002:
                returnStr = "请求为空";
                break;
            case 44003:
                returnStr = "图文消息内容为空";
                break;
            case 45001:
                returnStr = "多媒体文件大小超过限制";
                break;
            case 45002:
                returnStr = "消息内容超过限制";
                break;
            case 45003:
                returnStr = "标题字段超过限制";
                break;
            case 45004:
                returnStr = "描述字段超过限制";
                break;
            case 45005:
                returnStr = "链接字段超过限制";
                break;
            case 45006:
                returnStr = "图片链接字段超过限制";
                break;
            case 45007:
                returnStr = "语音播放时间超过限制";
                break;
            case 45008:
                returnStr = "图文消息超过限制";
                break;
            case 45009:
                returnStr = "接口调用超过限制";
                break;
            case 45010:
                returnStr = "创建菜单个数超过限制";
                break;
            case 46001:
                returnStr = "不存在媒体数据";
                break;
            case 46002:
                returnStr = "不存在的菜单版本";
                break;
            case 46003:
                returnStr = "不存在的菜单数据";
                break;
            case 47001:
                returnStr = "解析JSON/XML内容错误";
                break;
            case 40164:
                returnStr = "未知返回状态";
                break;
            default:
                returnStr = "请正确操作";
                break;
        }
        return returnStr;
    }
    

}
