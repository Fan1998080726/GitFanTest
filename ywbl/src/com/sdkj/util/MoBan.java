package com.sdkj.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.sdkj.util.context.CommonFunc;

public class MoBan {



	/**
	 * @描述: TODO(发送模板信息给用户)
	 * @param touser
	 *            用户的openid
	 * @param templat_id
	 *            信息模板id
	 * @param url
	 *            用户点击详情时跳转的url
	 * @param topcolor
	 *            模板字体的颜色
	 * @param data
	 *            模板详情变量 Json格式
	 * @return
	 * @添加时间 2016-1-5上午10:38:45 @作者：***
	 */
	public static String sendWechatmsgToUser(String touser, String templat_id, String clickurl, String topcolor,
			JSONObject data) {
		String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		String token = WeiXinApp.getAccessToken(); // 微信凭证，access_token
//		System.out.println("access_token==="+token);
		String url = tmpurl.replace("ACCESS_TOKEN", token);
		JSONObject json = new JSONObject();
		try {
			json.put("touser", touser);
			// json.put("title", "");
			json.put("template_id", templat_id);
			json.put("url", clickurl);
			json.put("topcolor", topcolor);
			json.put("data", data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String result = httpsRequest(url, "POST", json.toString());
		try {
			JSONObject resultJson = new JSONObject(result);
			String errmsg = (String) resultJson.get("errmsg");
			if (!"ok".equals(errmsg)) { // 如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
				return "error";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public static JSONObject packJsonmsg(String first, String ValueOfKeyword1, String ValueOfKeyword2,
			String ValueOfKeyword3, String ValueOfKeyword4, String remark) {
		JSONObject json = new JSONObject();
		try {
			// JSONObject title = new JSONObject();
			// title.put("value", "111");
			// title.put("color", "#173177");
			// json.put("title", title);

			JSONObject jsonFirst = new JSONObject();
			jsonFirst.put("value", first);
			jsonFirst.put("color", "#173177");
			json.put("first", jsonFirst);

			JSONObject keyword1 = new JSONObject();
			keyword1.put("value", ValueOfKeyword1);
			keyword1.put("color", "#173177");
			json.put("keyword1", keyword1);

			JSONObject keyword2 = new JSONObject();
			keyword2.put("value", ValueOfKeyword2);
			keyword2.put("color", "#173177");
			json.put("keyword2", keyword2);

			JSONObject keyword3 = new JSONObject();
			keyword3.put("value", ValueOfKeyword3);
			keyword3.put("color", "#173177");
			json.put("keyword3", keyword3);

			JSONObject keyword4 = new JSONObject();
			keyword4.put("value", ValueOfKeyword4);
			keyword4.put("color", "#173177");
			json.put("keyword4", keyword4);

			JSONObject jsonRemark = new JSONObject();
			jsonRemark.put("value", remark);
			jsonRemark.put("color", "#173177");
			json.put("remark", jsonRemark);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			System.out.println("连接超时：{}");
		} catch (Exception e) {
			System.out.println("https请求异常：{}");
		}
		return null;
	}
	/**
	 * @method packJsonmsg
	 * @描述: TODO(封装微信模板:订单支付成功)
	 * @param first
	 *            头部
	 * @param orderMoneySum
	 *            总金额
	 * @param orderProductName
	 *            商品信息
	 * @param remark
	 *            说明
	 * @return
	 * @返回类型：JSONObject
	 * @添加时间 2016-1-5下午03:38:54 @作者：***
	 */

	public static void main(String[] args) {

		String first = "";// 标题头
		String keyword1 = "污水排放网上审批微信通知功能测试";// 项目名称
		String keyword2 = "污水排放许可核发";// 项目阶段
		String keyword3 = "请审批办领导审批";// 反馈内容
		String keyword4 = CommonFunc.CurrentTime();// 时间
		String remark = "";// 备注
		JSONObject json = packJsonmsg("您有一个项目等待审批", keyword1, keyword2, keyword3, keyword4, remark);

		//  String userID="okvJGxLTc5Xqut_0ZC5MwR3bVUQ0";//用户id lrs
			String userID="okvJGxKuR0rpjwnUZI9qv5bbmp_E";//用户id txb
//		String userID = "okvJGxDs0o_I7Xr3KzSD6AyEqt28";// 用户id cjp

		String userModeId = "6tP7OwbdrLtp2gsTlJZEy7rdnGr1NqJ8ZOjMjFZINnA";// 模板id
		String topColor = "";// 标题头颜色
		String clickurl = "";// 点击跳转链接
		sendWechatmsgToUser(userID, userModeId, clickurl, topColor, json);
		
	}
}
