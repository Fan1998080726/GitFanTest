package com.wx.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wx.servlet.TokenThread;
import com.wx.vo.Userof;
import com.wx.vo.tc_Template;
import com.wx.vo.tc_TemplateParam;

/**
 * 用户管理用到的工具类
 * By CLiang
 * https://blog.csdn.net/u011752195/article/details/81782526
 */
public class GroupUtil {
//	static String AccessToken = TokenThread.accessToken.getAccessToken();

	/**
     * 创建群组
     * @param appId
     * @param appSecret
     * @param groupName 群组名称
     * @return 如{"group": { "id": 107, "name": "test" } }
     */
	public static JSONObject createGroup(String groupName){
		String url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + TokenThread.accessToken.getAccessToken() ;
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
		String url = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=" + TokenThread.accessToken.getAccessToken() ;
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
    public static JSONObject getAllGroups(){
//    	String access_token="32_zlhNdGhIzGJC4jlq06AtWPVMskgV-eGckAJYXixe_1sbAvnVS1E3lZ3vQK43Q1TW-7Lcr-M_GN9mLWJT4kslrw0ZQCjMX3Lv3k6cHWi5l4Y69fc6YOfo9lQyV1Ar69uSi2TVoW1-YRw1NQI9VYJcAIABEP";
        String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" +TokenThread.accessToken.getAccessToken() ;
//    	String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" +CommonUtil.getToken(TokenThread.appid, TokenThread.appsecret) ;
        //https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN
        String rtn = weixinRequest(url, null, "GET");
        System.out.println("分组信息："+rtn);
        JSONObject json = new JSONObject(JSON.parseObject(rtn));
        
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
//        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=46_oMlVXQcUIswuyrZHseNPvNEXaYqyJjeySHGIXIh8Q6vbFgWyrJUFy1y4dn6_q1pZf9tlLZO-PwayhogV4fU4o2Lz_fKa9U3-LJfQ6JR_HXaOWN0ja57UqYeYy4Z8KYZQ0IlQFJEo-Q302sCVEZEhACAQLQ&next_openid="+next;
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+TokenThread.accessToken.getAccessToken()+"&next_openid="+next;
        //https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN
        String rtn = weixinRequest(url, null, "GET");
//        System.out.println("用户列表："+rtn);
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
 
    public static JSONObject getUserGroup(String openId){
//    	System.out.println("into 通过用户的OpenID查询其所在的GroupID");
        String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token="+
        		TokenThread.accessToken.getAccessToken();
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
    public static JSONObject getUserInfo(String openId){
//    	System.out.println("into 通过用户的OpenID查询其所在的GroupID");
    	String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
    					+TokenThread.accessToken.getAccessToken()
    					+"&openid="+openId+"&lang=zh_CN";
//    	String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token="+TokenThread.accessToken.getAccessToken();
    	JSONObject j = new JSONObject();
    	try {
//    		j.put("openid", openId);
    	} catch (JSONException e1) {
    		e1.printStackTrace();
    	}
    	
    	String rtn = weixinRequest(url, j.toString(), "GET");
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
    public static JSONObject updateGroup(String groupId, String newGroupName){
        String url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=" 
    + TokenThread.accessToken.getAccessToken() ;
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
    public static JSONObject updateUserGroup(String toGroupId, String openId){
//    	System.out.println("TokenThread.accessToken==="+CommonUtil.getToken(TokenThread.appid, TokenThread.appsecret));
        String url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=" + TokenThread.accessToken.getAccessToken() ;
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
    public static JSONObject ListGroup(String groupid){
//    	String access_token="46_oMlVXQcUIswuyrZHseNPvNEXaYqyJjeySHGIXIh8Q6vbFgWyrJUFy1y4dn6_q1pZf9tlLZO-PwayhogV4fU4o2Lz_fKa9U3-LJfQ6JR_HXaOWN0ja57UqYeYy4Z8KYZQ0IlQFJEo-Q302sCVEZEhACAQLQ";
        String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" +  TokenThread.accessToken.getAccessToken();
//        String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + TokenThread.accessToken.getAccessToken() ;
        					
        System.out.println("=========url===="+url);
        JSONObject j = new JSONObject();
        try {
        	j.put("tagid",groupid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String rtn = weixinRequest(url, j.toString(), "GET");
//        System.out.println("分组粉丝列表："+rtn);
        JSONObject json = JSON.parseObject(rtn);
        
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
			System.out.println("Size=="+size);
			byte[] jsonBytes = new byte[size];
			jsonBytes = readStream(is);
			
			System.out.println("jsonBytes="+jsonBytes);
			
//			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println("message===="+message);
			return message;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
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
 
    public static List getusersummary(String begin_date, String end_date) {
    	List userinfolist = null;
			try {
				DefaultHttpClient client3 = new DefaultHttpClient();
				HttpPost  requestPost = null;
				 requestPost = new HttpPost( "https://api.weixin.qq.com/datacube/getusersummary?access_token="
							+TokenThread.accessToken.getAccessToken());
				JSONObject param= new JSONObject();  
				System.out.println("begin_date="+begin_date+"end_date="+end_date);
			    param.put("begin_date",begin_date);
			    param.put("end_date",end_date);
//			    System.out.println("param.toString()="+param.toString());
				StringEntity stringEntity = new StringEntity(param.toString());
			    stringEntity.setContentType("application/json");
			    requestPost.setEntity(stringEntity);
			    HttpResponse response3 = client3.execute(requestPost);
			    if (response3.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String strResult3 = EntityUtils.toString(response3.getEntity(),HTTP.UTF_8);
					JSONObject jsonResult4 = JSONObject.parseObject(strResult3);
					System.out.println("jsonResult4="+jsonResult4);
					userinfolist = (List)jsonResult4.get("list");
//				request1.setAttribute("userinfolist", userinfolist);
			    }
				
				
				
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return  userinfolist;
    }
    public static List getUserInfo(String accessToken, String openId) {
    	List userinfolist = null;
    	
    	try {
		
//			String getopenid_url2 = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+TokenThread.accessToken.getAccessToken();
			String getopenid_url2 = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
					+ ""+ TokenThread.accessToken.getAccessToken();
			DefaultHttpClient client2 = new DefaultHttpClient();
			HttpGet request2 = new HttpGet(getopenid_url2);
			HttpResponse response2 = client2.execute(request2);
			if (response2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String strResult2 = EntityUtils.toString(response2.getEntity());
				JSONObject jsonResult2 = JSONObject.parseObject(strResult2);
				JSONObject jsonResult3 = JSONObject.parseObject(jsonResult2.get("data").toString());
//				System.out.println("jsonResult3="+jsonResult3);
				List<String> userList = (List<String>) jsonResult3.get("openid");
//				System.out.println("userList="+userList);
				String  count = (String) jsonResult2.get("count").toString();
				String  next_openid = (String) jsonResult2.get("next_openid").toString();
//				System.out.println("next_openid="+next_openid);
				String total = (String)jsonResult3.get("total");
//			request1.setAttribute("total",total);
				List<Userof> userList2 = new ArrayList<Userof>();
				
				
				
				
				for(int i=0;i<=99;i++){
					Userof user = new Userof();
					user.setOpenid(userList.get(i));
//				user.setLanguage("zh_CN");
					userList2.add(user);
				}
 
				
				
				
				
//				System.out.println("userList2="+userList2);
				DefaultHttpClient client3 = new DefaultHttpClient();
				HttpPost  requestPost = null;
				
				
				 requestPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+
				TokenThread.accessToken.getAccessToken());
				JSONObject param= new JSONObject();  
			    param.put("user_list",userList2);  
			    
			    
			    
			    
			    
			    
//			    System.out.println("param.toString()="+param.toString());
				StringEntity stringEntity = new StringEntity(param.toString());
			    stringEntity.setContentType("application/json");
			    requestPost.setEntity(stringEntity);
			    HttpResponse response3 = client3.execute(requestPost);
			    if (response3.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String strResult3 = EntityUtils.toString(response3.getEntity(),HTTP.UTF_8);
					JSONObject jsonResult4 = JSONObject.parseObject(strResult3);
					System.out.println("jsonResult4="+jsonResult4);
					userinfolist = (List)jsonResult4.get("user_info_list");
//				request1.setAttribute("userinfolist", userinfolist);
			    }
//				System.out.println("userinfolist="+userinfolist);
			}
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return userinfolist;
    } 
    /**
     * 		tc 2020年4月8日18:47:22
     * @param groupid
     * @return
     */
    public static String ModSend(String groupid,String first,String keyword1,String keyword2,String keyword3,String remark) {
    	JSONObject json = ListGroup(groupid);

    	System.out.println("json=========="+ json.toString());
		String count = json.get("count").toString();
		System.out.println("count========="+ count);
        JSONObject data = JSON.parseObject(json.get("data").toString());
        JSONArray dataList = JSON.parseArray(data.get("openid").toString());
        String error="";
        System.out.println("dataList.size()===="+dataList.size());//当前分组下共有多少人
        for (Object openId : dataList) {
//        	if("omCLR1ZyXyFCSJyYnTERLOS5uJLM".equals(openId)) {//tc openid
//        		System.out.println(openId);
        	error = sendInfo(openId,first,keyword1,keyword2,keyword3,remark);
        	if(!"ok".equals(error)) {
        		break;
        	}
//        	}
		}
    	return error;
    }
    
    
    /**
     * 查询所有户用户信息  fcl 2021年7月1日16:25:17
     * @return
     */
    public  static JSONObject  queryWxUserInfoOpenId() {
//    	System.out.println("Into   Wx Send");
//    	String  access_token = "46_ng7hQE41zkz9APuMfAeirT-AjmJv1mUyPQNquFlf2_B7xS_RcNeu3_53Usi0_TbLn8bjdbmpm6OaZ5xiFsQoadSjHJuhQDIKC7KE8pnFlKm5TxsiACUbfclO7hiu8T--i1M2Cq652OwRHqe_VGFiAEAKVU";
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+TokenThread.accessToken.getAccessToken();
		String rtn = weixinRequest(url,"", "GET");
//		System.out.println("rtn="+rtn);
        JSONObject json = JSON.parseObject(rtn);
//		System.out.println("6666666666666666666666");
    	return json;
    }
    
    
    
    
    
    
    
    
    
    
    /**
	 * 发送模板消息
	 * @param access_token tc 2020年4月8日18:47:22
	 * @return
	 */
	public static String sendInfo(Object openId,String first,String keyword1,String keyword2,String keyword3,String remark) 
	{
//		String access_token="46_ng7hQE41zkz9APuMfAeirT-AjmJv1mUyPQNquFlf2_B7xS_RcNeu3_53Usi0_TbLn8bjdbmpm6OaZ5xiFsQoadSjHJuhQDIKC7KE8pnFlKm5TxsiACUbfclO7hiu8T--i1M2Cq652OwRHqe_VGFiAEAKVU";
	       
		// 发送模板消息
//		String resultUrl2 = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + AccessToken ;
		String resultUrl2 = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+ TokenThread.accessToken.getAccessToken();
//		String resultUrl2 = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ TokenThread.accessToken.getAccessToken();

        tc_Template tem=new tc_Template();  
        tem.setTemplateId("7atNwZY-STJxHovH6_oNSOh3JX1DNaRCYIfLjlGOWtU");  
        tem.setTopColor("#fff");  
        tem.setAppid("wx354e34f1e820222e");
        String  imageFile =keyword2;
        
//        if(!keyword2.equals("")) {
//        	keyword2="消息通知";
//        }else {
        	keyword2="消息通知";
//        }
                  
        List<tc_TemplateParam> paras=new ArrayList<tc_TemplateParam>();  
        paras.add(new tc_TemplateParam("first",first,"#333"));  
        paras.add(new tc_TemplateParam("keyword1",keyword1,"#333"));
        paras.add(new tc_TemplateParam("keyword2",keyword2,"#333"));
        paras.add(new tc_TemplateParam("keyword3",keyword3,"#333"));
        paras.add(new tc_TemplateParam("remark",remark,"#333"));  
                  
        tem.setTemplateParamList(paras);  
        tem.setToUser(openId.toString());//用户openid
//     //设置超链接
       tem.setUrl("http://hrbshengdong.cn/LoginJZSxypj.jsp");  
        JSONObject jsonObject = JSON.parseObject(weixinRequest(resultUrl2, tem.toJSON(),"GET"));
        System.out.println(jsonObject.toString());
        if(jsonObject.get("errmsg").toString().equals("ok")) {
        	return "ok";
        }else {
        	return jsonObject.toString();
        }
	}
	
	/**
	 * 发送预约通知
	 * @param access_token tc 2020年4月8日18:47:22
	 * @return
	 */
	public static String sendAppointmentInfo(Object openId,String first,String keyword1,
			String keyword2,String keyword3,String remark) 
	{
		// 发送模板消息
//		String resultUrl2 = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+ TokenThread.accessToken.getAccessToken();
		String resultUrl2 = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+TokenThread.accessToken.getAccessToken();

		tc_Template tem=new tc_Template();  
//		tem.setTemplateId("7atNwZY-STJxHovH6_oNSOh3JX1DNaRCYIfLjlGOWtU");  
		tem.setTemplateId("Xvm7o1qbA4Kl0--E6DBDxzCecNo2ZRuk2eYLjrCkAoA");  
		tem.setTopColor("#fff");  
		tem.setAppid("wx354e34f1e820222e");
		List<tc_TemplateParam> paras=new ArrayList<tc_TemplateParam>();  
		paras.add(new tc_TemplateParam("first",first,"#333"));  
		paras.add(new tc_TemplateParam("keyword1",remark,"#333"));
		paras.add(new tc_TemplateParam("keyword2",keyword2,"#333"));
		paras.add(new tc_TemplateParam("keyword3",keyword3,"#333"));
		paras.add(new tc_TemplateParam("remark","成功","#333"));  
		
		tem.setTemplateParamList(paras);  
		tem.setToUser(openId.toString());//用户openid
        //设置超链接
//		tem.setUrl("http://hrbshengdong.cn/LoginJZSxypj.jsp");  
		
		JSONObject jsonObject = JSON.parseObject(weixinRequest(resultUrl2, tem.toJSON(),"GET"));
		System.out.println(jsonObject.toString());
		if(jsonObject.get("errmsg").toString().equals("ok")) {
			return "ok";
		}else {
			return jsonObject.toString();
		}
	}
	
	
	
	
	/**
	 *  群发消息  fcl  2021年7月1日16:29:01
	 */
	  public static   void  SendWxContent(String  type,String titile,String enclosure,String inittime,String noticeinfo) {
		  
//		  JSONObject   list  = queryWxUserInfoOpenId();
////	    	System.out.println("json=========="+ list.toString());
////	    	System.out.println("list==="+list);
//	    	
//	    	   JSONObject data = JSON.parseObject(list.get("data").toString());
//	           JSONArray dataList = JSON.parseArray(data.get("openid").toString());
//	           String error="";
	           
	           
	       	JSONObject json = ListGroup("");

	    	System.out.println("json=========="+ json.toString());
			String count = json.get("count").toString();
			System.out.println("count========="+ count);
	        JSONObject data = JSON.parseObject(json.get("data").toString());
	        JSONArray dataList = JSON.parseArray(data.get("openid").toString());
	        String error="";
	        System.out.println("dataList.size()===="+dataList.size());//当前分组下共有多少人
//omCLR1biIaKE7-OFZ0qJ4ILoj1-g 
	           
	           
//	           System.out.println("dataList.size()===="+dataList.size());//当前分组下共有多少人
	           for (Object openId : dataList) {
//	        	   if(openId=="omCLR1biIaKE7-OFZ0qJ4ILoj1-g") {
	        		   System.out.println("发送消息！！！！！！！！！！");
	   	           	sendInfo(openId, "消息通知：",titile,enclosure,inittime ,noticeinfo);
//	        	   }
//	        	   System.out.println("Openid=="+openId);
//	           System.out.println("enclosure="+enclosure);
	           }
	    	
	  }
	
	
	
	    
	    public	static String getUnionidByOpenid(String openid) {
	    			String  unionid  ="";
	    			
	    
	    				try { 
	    					String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+TokenThread.accessToken.getAccessToken()+"&openid="+openid+"&lang=zh_CN";
	    					String rtn = weixinRequest(url,"", "GET");
	    					System.out.println("创建群组："+rtn);
	    					JSONObject json;
	    			 
	    						json = new JSONObject(JSON.parseObject(rtn));
	    						
	    						System.out.println("json="+json);
	    					
	    				} catch (Exception e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    	      return unionid;
	    	}
	 
	    
	    
	    
	    
	    
	    // 登录凭证校验地址
	    public final static String GetPageAccessTokenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";
	    // 小程序的appId以及appSecret
	    private static final String appId = "wx6e407872d76226c0";
	    private static final String appSecret = "2ea76fa21cba2a31467c243e046bdeeb";


	    /**
	     * 小程序授权获取openID和unionID
	     * @param code 前端通过wx.login获取的wxCode
	     * @return
	     */
	    public static Map<String, String> oauth2GetUnion(String code) {
	        String requestUrl = GetPageAccessTokenUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
	        Map<String, String> result = new HashMap<>();
	        try {
	            /** 
	             * HttpUtil工具类会在下方贴出，此处也可换成自己的写法，只要是get请求就可以
	             * 此处请求返回的json数据包为：
	             * openid	string	用户唯一标识
	             * session_key	string	会话密钥
	             * unionid	string	用户在开放平台的唯一标识符
	             * errcode	number	错误码
	             * errmsg	string	错误信息
	             */
	            String response = HttpUtil.get(requestUrl);
	            JSONObject jsonResult = JSONObject.parseObject(response);           
	            String openid = String.valueOf(jsonResult.get("openid"));
	            // 若用户没有改小程序同主体公众号，则此处unionID为空
	            String unionid = String.valueOf(jsonResult.get("unionid"));
	            System.out.println("unionid======"+unionid);
	            result.put("openid", openid);
	            result.put("unionid",unionid);
	        } catch (Exception e) {
//	            log.info("授权获取unionid出现异常");
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	

public  String sendGet(String url, String param) {
   String result = "";
   BufferedReader in = null;
   try {
      String urlNameString = url + "?" + param;
      URL realUrl = new URL(urlNameString);
      // 打开和URL之间的连接
      URLConnection connection = realUrl.openConnection();
      // 设置通用的请求属性
      connection.setRequestProperty("accept", "*/*");
      connection.setRequestProperty("connection", "Keep-Alive");
      connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      // 建立实际的连接
      connection.connect();
      // 定义 BufferedReader输入流来读取URL的响应
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
         result += line;
      }
   } catch (Exception e) {
      e.printStackTrace();
   } finally {
      try {
         if (in != null) {
            in.close();
         }
      } catch (Exception e2) {
         e2.printStackTrace();
      }
   }
   return result;
}
 
	
	
	
    public static void main(String[] args) {

 		/*JSONObject json = createGroup("201", "test", "groupName3");
 		System.out.println("json.toString()==="+json.toString());
 	 
 		/*deleteGroup("106", "name", "106");
 		 */
//    	System.out.println("111");
//    	JSONObject json =getAllUsers("","","");
//    	System.out.println("Json==="+json);
    	
    	
//    	for (Object object : list) {
//			
//		}
//    	getAllGroups();
//    	ListGroup("0");
//    	ModSend("0");
//    	System.out.println("1111111");
//    	 分组信息：{"id":0,"name":"未分组","count":582},{"id":1,"name":"黑名单","count":0},{"id":2,"name":"星标组","count":0},{"id":100,"name":"检查组","count":2}
//    	System.out.println(ModSend(GroupId,String first,String keyword1,String keyword2,String keyword3,String remark));
//    	sendInfo("omCLR1biIaKE7-OFZ0qJ4ILoj1-g", "审核结果告知：","审核结果告知","审核成功！","2021年6月22日14:11:51" ,"");
//    	String error = sendInfo("omCLR1biIaKE7-OFZ0qJ4ILoj1-g",first,keyword1,keyword2,keyword3,remark);
//    	JSONObject json =updateUserGroup("100","omCLR1WUbcGF9WOkZRTFvJ0mAmW8");
//    	System.out.println("222");
 	}

}