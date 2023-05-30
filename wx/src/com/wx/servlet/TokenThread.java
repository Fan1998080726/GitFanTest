package com.wx.servlet;



import com.wx.util.CommonUtil;
import com.wx.vo.AccessToken;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
//import com.wx.vo.Ttoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名: TokenThread </br>
 * 描述: 定时获取微信access_token的线程 </br>
 * 开发人员： 六 </br>
 * 创建时间：  2018-8-9 </br>
 * 发布版本：V1.0  </br>
 */
public class TokenThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);

   /* @Autowired
    TtokenMapper ttokenMapper;
*/
    // 第三方用户唯一凭证			  
    public static String appid = "wx354e34f1e820222e";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "37685e8c3a5ff8fbbf8d6da72b83f995";
    public static AccessToken accessToken = null;
    
    

    public void run() {
        while (true) {
            try {
            	 System.out.println("appid的值===："+appid);
            	 System.out.println("appsecret的值===："+appsecret);
                accessToken = CommonUtil.getToken(appid, appsecret);
                if (null != accessToken) {
                    //调用存储到数据库
                    System.out.println("Token的值===："+accessToken);
//                    Ttoken ttoken = new Ttoken(3,accessToken.getAccessToken(),accessToken.getExpiresin(),null);
//                    System.out.println("Ttoken的值："+ttoken);
                    //ttokenMapper.select();
                    //ttokenMapper.save(ttoken);
                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresin(), accessToken.getAccessToken());
                    // 休眠7000秒
                    sendPost();
                    Thread.sleep((accessToken.getExpiresin() - 200)*100);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);
                }
                log.error("{}", e);
            }
        }
    }
    public String sendPost(){
        HttpClient client = new HttpClient(); 	 //创建Client
        PostMethod method=null;
        String result="";
        try {
            method = new PostMethod("http://218.9.114.205:8888/ljxqgz/user/getToken.do");
            method.setParameter("token",accessToken.getAccessToken() );// 设置参数
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {// 响应成功
                result = method.getResponseBodyAsString();// 调用返回结果
                return result;
            } else {// 不成功组装结果
              return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}