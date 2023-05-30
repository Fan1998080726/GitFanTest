package com.sdkj.util.sendMsg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/**
 * xuduo
 * 2020年11月14日
 * 发送短信验证码或通知
*/
public class SendSms {
	
	public static void main(String[] args) {
		System.out.println("发送--------");
//	String  codeFlag= 	sendOneKcsjSmsForNotice("2","1","13089893605");
	String  codeFlag = sendAppointOneSmsForNotice("11","22","333","13089893605");

		System.out.println(codeFlag);
	}
 
	
	 
    /**
     * 松北房展会预约
     * @param username
     * @param time
     * @param code
     * @param phoneNum
     * @return
     */
    public static String sendAppointOneSmsForNotice(String username,String  time ,String code ,
    		String phoneNum){
    	String regExp = "^[1][3,4,5,7,8][0-9]{9}$";
    	Pattern p = Pattern.compile(regExp);
    	Matcher m = p.matcher(phoneNum);
    	String codeFlag = "";
    	if(m.find()){
    		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G8pP3GU2t2r6J2yXdAd", "8GoOKiPIikzFrn4teMeUIm8CT6O8tE");
            IAcsClient client = new DefaultAcsClient(profile);
            
            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phoneNum);//发送手机号
            request.putQueryParameter("SignName", "圣东科技");//签名
            request.putQueryParameter("TemplateCode", "SMS_249840249");//模板code
//            request.putQueryParameter("TemplateParam","{\"companyName\":\""+companyName+"\"}");
//		    request.putQueryParameter("TemplateParam","{\"uname\":\""+uname+"\","+ ",\"state\":\""+state+"\"}");
    		request.putQueryParameter("TemplateParam","{\"username\":\""+username+"\","+ ",\"time\":\""+time+"\","+ ",\"code\":\""+code+"\"}");

            try {
                CommonResponse response = client.getCommonResponse(request);
                JSONObject jo = new JSONObject(new String(response.getData()));
                codeFlag = (String)jo.get("Code");
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
       return codeFlag;
    }
     
    
	
	/**
	 * 
	 * @param  事项发送通知
	 * @param state  状态 预警 或超期
	 * @param phoneNum 手机号
	 * @return
	 */
	public static String sendSendMattersSmsForNotice(String phoneNum){
		String regExp = "^[1][3,4,5,7,8][0-9]{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneNum);
		String codeFlag = "";
		if(m.find()){
			DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G8pP3GU2t2r6J2yXdAd", "8GoOKiPIikzFrn4teMeUIm8CT6O8tE");
			IAcsClient client = new DefaultAcsClient(profile);
			
			CommonRequest request = new CommonRequest();
			request.setSysMethod(MethodType.POST);
			request.setSysDomain("dysmsapi.aliyuncs.com");
			request.setSysVersion("2017-05-25");
			request.setSysAction("SendSms");
			request.putQueryParameter("RegionId", "cn-hangzhou");
			request.putQueryParameter("PhoneNumbers", phoneNum);//发送手机号
			request.putQueryParameter("SignName", "圣东科技");//签名
			request.putQueryParameter("TemplateCode", "SMS_248910521");//模板code
//            request.putQueryParameter("TemplateParam","{\"companyName\":\""+companyName+"\"}");
			
			try {
				CommonResponse response = client.getCommonResponse(request);
				JSONObject jo = new JSONObject(new String(response.getData()));
				codeFlag = (String)jo.get("Code");
			} catch (ServerException e) {
				e.printStackTrace();
			} catch (ClientException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return codeFlag;
	}
	
	/**
	 * 
	 * @param uname 处室名称
	 * @param state  状态 预警 或超期
	 * @param phoneNum 手机号
	 * @return
	 */
    public static String sendOneKcsjSmsForNotice(String uname,String state,String phoneNum){
    	String regExp = "^[1][3,4,5,7,8][0-9]{9}$";
    	Pattern p = Pattern.compile(regExp);
    	Matcher m = p.matcher(phoneNum);
    	String codeFlag = "";
    	if(m.find()){
    		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G8pP3GU2t2r6J2yXdAd", "8GoOKiPIikzFrn4teMeUIm8CT6O8tE");
            IAcsClient client = new DefaultAcsClient(profile);
            
            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phoneNum);//发送手机号
            request.putQueryParameter("SignName", "圣东科技");//签名
            request.putQueryParameter("TemplateCode", "SMS_244765172");//模板code
//            request.putQueryParameter("TemplateParam","{\"companyName\":\""+companyName+"\"}");
		    request.putQueryParameter("TemplateParam","{\"uname\":\""+uname+"\","+ ",\"state\":\""+state+"\"}");

            try {
                CommonResponse response = client.getCommonResponse(request);
                JSONObject jo = new JSONObject(new String(response.getData()));
                codeFlag = (String)jo.get("Code");
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
       return codeFlag;
    }

	
	
	
	
	 
}

