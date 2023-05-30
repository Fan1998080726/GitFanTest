package com.sdkj.util;

import javax.net.ssl.*;

//import com.Common.AccessTokenInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sdkj.util.AccessTokenInfo.AccessToken;
//import com.sdkj.util.AccessTokenInfo.WeixinUserInfo;
import com.sdkj.util.AccessTokenInfo.AccessTokenInfo;
import com.sdkj.util.AccessTokenInfo.NetWorkHelper;
import com.sdkj.util.AccessTokenInfo.WeixinUserInfo;
import com.sdkj.util.context.CommonFunc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 用户管理用到的工具类
 * By CLiang
 * https://blog.csdn.net/u011752195/article/details/81782526
 */
public class GroupUtil {


	/**
     * 创建群组
     * @param appId
     * @param appSecret
     * @param groupName 群组名称
     * @return 如{"group": { "id": 107, "name": "test" } }
     */
	public static JSONObject createGroup(String appId, String appSecret, String groupName){
		String url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + WeiXinApp.getAccessToken();
		JSONObject j = new JSONObject();
		JSONObject group = new JSONObject();
		try {
			j.put("name",groupName);
            group.put("group",j);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		String rtn = weixinRequest(url, group.toString(), "POST");
		System.out.println("创建群组："+rtn);
		JSONObject json;
		try {
			json = new JSONObject(JSON.parseObject(rtn));
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return json;
	}
	
	/**
     * 删除群组
     * @param appId
     * @param appSecret
     * @param groupName 群组名称
     * @return 如{"group": { "id": 107, "name": "test" } }
     */
	
	public static JSONObject deleteGroup(String appId, String appSecret, String groupName){
		String url = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=" + WeiXinApp.getAccessToken();
		JSONObject j = new JSONObject();
        JSONObject group = new JSONObject();
		try {
            j.put("id",groupName);
            group.put("group",j);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String rtn = weixinRequest(url, group.toString(), "POST");
		System.out.println("deleteGroup()"+rtn);
		JSONObject json;
		try {
			json = new JSONObject(JSON.parseObject(rtn));
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		return json;
	}

    /**
     * 查询所有分组
     * @param appId
     * @param appSecret
     * @return
     */
    public static JSONObject getAllGroups(String appId, String appSecret){
        String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + WeiXinApp.getAccessToken();
        //https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN
        String rtn = weixinRequest(url, null, "GET");
        System.out.println("分组信息："+rtn);
        JSONObject json;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return json;
    }
    /**
     * 查询所有用户
     * @param appId
     * @param appSecret
     * @return
     */
    public static JSONObject getAllUsers(String appId, String appSecret, String next){
        //String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + WeiXinApp.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+WeiXinApp.getAccessToken()+"&next_openid="+next;
        //https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN
        String rtn = weixinRequest(url, null, "GET");
        System.out.println("用户列表："+rtn);
        JSONObject json;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return json;
    }

    /**
     * 通过用户的OpenID查询其所在的GroupID
     * @param appId
     * @param appSecret
     * @param openId 用户的OpenID
     * @return 如：{ "groupid": 102 }
     */
 
    public static JSONObject getUserGroup(String appId, String appSecret, String openId){
        String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=" + WeiXinApp.getAccessToken();
        JSONObject j = new JSONObject();
        try {
        	j.put("openid", openId);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
       
        String rtn = weixinRequest(url, j.toString(), "POST");
        System.out.println("用户分组："+rtn);
        JSONObject json;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return json;
    }
    
    /**
     * 修改分组名
     * @param appId
     * @param appSecret
     * @param groupId
     * @param newGroupName
     * @return 如 {"errcode": 0, "errmsg": "ok"}
     */
    public static JSONObject updateGroup(String appId, String appSecret, String groupId, String newGroupName){
        String url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=" + WeiXinApp.getAccessToken();
        JSONObject j = new JSONObject();
        JSONObject group = new JSONObject();
        try {
            j.put("id", groupId);
            j.put("name",newGroupName);
            group.put("group",j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       
        String rtn = weixinRequest(url, group.toString(), "POST");
        System.out.println("修改分组名:"+rtn);
        JSONObject json;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return json;
    }
    
    /**
     * 移动用户分组
     * @param appId
     * @param appSecret
     * @param toGroupId 新分组的id
     * @param openId 用户id
     * @return 如 {"errcode": 0, "errmsg": "ok"}
     */
    public static JSONObject updateUserGroup(String appId, String appSecret, String toGroupId, String openId){
        String url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=" + WeiXinApp.getAccessToken();
        JSONObject j = new JSONObject();
        try {
            j.put("openid", openId);
            j.put("to_groupid", toGroupId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       
        String rtn = weixinRequest(url, j.toString(), "POST");
        System.out.println("更新用户组："+rtn);
        JSONObject json;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return json;
    }
    /**
     * 获取分组下粉丝列表
     * @param appId
     * @param appSecret
     * @param id 分组id
     * @return 如 {"errcode": 0, "errmsg": "ok"}
     */
    public static JSONObject ListGroup(String appId, String appSecret, String id){
        String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + WeiXinApp.getAccessToken();
        JSONObject j = new JSONObject();
        try {
        	j.put("tagid",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       
        String rtn = weixinRequest(url, j.toString(), "GET");
        System.out.println("分组粉丝列表："+rtn);
        JSONObject json;
        try {
            json = new JSONObject(JSON.parseObject(rtn));
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return json;
    }

    private static String weixinRequest(String urlStr, String data, String method){
		try {
			System.setProperty("jsse.enableSNIExtension", "false");
			
//			SSLContext ctx = SSLContext.getInstance("TLS");
//			SSLSocketFactory factory = ctx.getSocketFactory();
			
			URL url = new URL(urlStr);
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
//			http.setSSLSocketFactory(factory);
			if(method == null || "".equals(method))method = "GET";
			http.setRequestMethod(method);
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			if(data != null && !"".equals(data)){
				OutputStream os = http.getOutputStream();
				os.write(data.getBytes("UTF-8"));// 传入参数
				os.flush();
				os.close();
			}

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return message;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

    /**
       * 获取用户信息
       * 
       * @param accessToken 接口访问凭证
       * @param openId 用户标识
       * @return WeixinUserInfo
       */
    /**
    * 获取用户信息
    * 
    * @param accessToken 接口访问凭证
    * @param openId 用户标识
    * @return WeixinUserInfo
    */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
	    WeixinUserInfo weixinUserInfo = null;
	    // 拼接请求地址
	    String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	    requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	    // 获取用户信息
	    JSONObject jsonObject = NetWorkHelper.httpsRequest(requestUrl, null, "GET");
	    if (null != jsonObject) {
		    try {
			    weixinUserInfo = new WeixinUserInfo();
			    // 用户的标识
			    weixinUserInfo.setOpenId(jsonObject.getString("openid"));
			    // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
			    weixinUserInfo.setSubscribe(jsonObject.getIntValue("subscribe"));
			    // 用户关注时间
			    weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
			    // 昵称
			    weixinUserInfo.setNickname(jsonObject.getString("nickname"));
			    // 用户的性别（1是男性，2是女性，0是未知）
			    weixinUserInfo.setSex(jsonObject.getIntValue("sex"));
			    // 用户所在国家
			    weixinUserInfo.setCountry(jsonObject.getString("country"));
			    // 用户所在省份
			    weixinUserInfo.setProvince(jsonObject.getString("province"));
			    // 用户所在城市
			    weixinUserInfo.setCity(jsonObject.getString("city"));
			    // 用户的语言，简体中文为zh_CN
			    weixinUserInfo.setLanguage(jsonObject.getString("language"));
			    // 用户所在的分组ID
			    weixinUserInfo.setGroupid(jsonObject.getString("groupid"));
			    // 用户头像
			    weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		    } 
		    catch (Exception e) {
			    if (0 == weixinUserInfo.getSubscribe()) {
			    	System.out.println("用户{"+ weixinUserInfo.getOpenId()+"}已取消关注");
			    }else{
				    int errorCode = jsonObject.getIntValue("errcode");
				    String errorMsg = jsonObject.getString("errmsg");
				    System.out.println("获取用户信息失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
			    }
		    }
	    }
	    System.out.println(weixinUserInfo.toString());
	    return weixinUserInfo;
    }
    public static void main(String[] args) {

 		/*JSONObject json = createGroup("201", "test", "groupName3");
 		System.out.println("json.toString()==="+json.toString());
 		*/
 		/*deleteGroup("106", "name", "106");
 		 */
// 		getAllGroups("","");omCLR1WUbcGF9WOkZRTFvJ0mAmW8
 		//getUserGroup("", "", "okvJGxLTc5Xqut_0ZC5MwR3bVUQ0");
    	System.out.println("111");
    	getUserInfo(WeiXinApp.getJgcAccessToken(), "omCLR1WUbcGF9WOkZRTFvJ0mAmW8");
    	System.out.println("222");
 	}

}