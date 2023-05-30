package com.wx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.util.CommonFunc;
import com.wx.util.CoreService;
import com.wx.util.GroupUtil;
import com.wx.util.JsonUtil;
import com.wx.util.MessageUtil;
import com.wx.util.ProMonomerVo;
import com.wx.util.Util;
import com.wx.util.XmlUtil;
import com.wx.vo.CreditVo;
import com.wx.vo.LoginVo;
import com.wx.vo.MessageVo;
import com.wx.vo.PersonCheckRecordVo;
import com.wx.vo.PrjAndPersonViewVo;
import com.wx.vo.ProjectcheckViewVo;
import com.wx.vo.ProjectcheckVo;
import com.wx.vo.RegisterVo;
import com.wx.vo.RoadWordCompanyViewVo;
import com.wx.vo.SuperviosrCompanyVIewVo;
import com.wx.vo.SysManagerVo;
import com.wx.vo.UploadFileVo;
import com.wx.vo.User;
import com.wx.vo.UserDemandViewVo;
import com.wx.vo.UserInfo;
import com.wx.vo.YqfkInfoVo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 20200331 txb Created by xdp on 2016/1/25.
 * 使用@WebServlet注解配置WxServlet,urlPatterns属性指明了WxServlet的访问路径
 */
@WebServlet(urlPatterns = "/WxServlet")
public class WxServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
	 * 比如这里我将Token设置为gacl
	 */
	private final String TOKEN = "hashengdong";
//	private final String fangkaiUrl = "192.168.110.14/credit";
//	private final String fangkaiUrl = "218.9.114.205:8881/credit";
	private final String fangkaiUrl = "218.9.114.205:8884/creditnew/";
	
	private static PoolingHttpClientConnectionManager connectionManager = null;
	private static HttpClientBuilder httpBuilder = null;
	private static RequestConfig requestConfig = null;

	private static int MAXCONNECTION = 10;

	private static int DEFAULTMAXCONNECTION = 5;

	private static String IP = "cnivi.com.cn";
	private static int PORT = 80;
	static long count = 0;
	// private static Logger log = LoggerFactory.getLogger(WxServlet.class);

	// private static Logger log= Logger.getLogger(WxServlet.class);
	private static Logger log = Logger.getLogger(WxServlet.class);

	static {
		// 设置http的状态参数
		requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();

		HttpHost target = new HttpHost(IP, PORT);
		connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(MAXCONNECTION);// 客户端总并行链接最大数
		connectionManager.setDefaultMaxPerRoute(DEFAULTMAXCONNECTION);// 每个主机的最大并行链接数
		connectionManager.setMaxPerRoute(new HttpRoute(target), 20);
		httpBuilder = HttpClients.custom();
		httpBuilder.setConnectionManager(connectionManager);
	}

	public static CloseableHttpClient getConnection() {
		CloseableHttpClient httpClient = httpBuilder.build();
		return httpClient;
	}

	public static HttpUriRequest getRequestMethod(Map<String, String> map, String url, String method) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		for (Map.Entry<String, String> e : entrySet) {
			String name = e.getKey();
			String value = e.getValue();
			NameValuePair pair = new BasicNameValuePair(name, value);
			params.add(pair);
		}
		HttpUriRequest reqMethod = null;
		if ("post".equals(method)) {
			reqMethod = RequestBuilder.post().setUri(url)
					.addParameters(params.toArray(new BasicNameValuePair[params.size()])).setConfig(requestConfig)
					.build();
		} else if ("get".equals(method)) {
			reqMethod = RequestBuilder.get().setUri(url)
					.addParameters(params.toArray(new BasicNameValuePair[params.size()])).setConfig(requestConfig)
					.build();
		}
		return reqMethod;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String prjId = request.getParameter("prjId");
		request.setAttribute("prjId", prjId);
		String opflag = request.getParameter("opflag");
		opflag = Util.nullToString(opflag);
		System.out.println("Postopflag="+opflag);
		PrintWriter out = response.getWriter();

		try {
			if (opflag.equals("checkPeronRegister")) { // 20200331 txb
				String openid = (String) request.getSession().getAttribute("openid");
				String name = request.getParameter("name");
				name = Util.nullToString(name);

				String phone = request.getParameter("phone");
				phone = Util.nullToString(phone);

				String dept_name = request.getParameter("dept_name");
				dept_name = Util.nullToString(dept_name);

				Map<String, String> map = new HashMap<String, String>();
				map.put("name", name);
				map.put("phone", phone);
				map.put("dept_name", dept_name);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!checkPeronRegister.action",
						"post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						out.print("{\"message\":\"" + message + "\"}");

						out.flush();
						out.close();
					} else {
						// ////////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}   else if(opflag.equals("regJlCheckPerson")) {
					try {
						String openid =request.getParameter("openid");
						openid = Util.nullToString(openid);
						String username =request.getParameter("username");
						username = Util.nullToString(username);
						String idcard =request.getParameter("idcard");
						idcard = Util.nullToString(idcard);
						String phone =request.getParameter("phone");
						phone = Util.nullToString(phone);
						String creditCode =request.getParameter("creditCode");
						creditCode = Util.nullToString(creditCode);
						
						
						System.out.println("username="+username);
						 	
							Map<String, String> map = new HashMap<String, String>();
							map.put("openid", openid);
							map.put("username", username);
							map.put("idcard", idcard);
							map.put("phone", phone);
							map.put("creditCode", creditCode);
							
							
							HttpClient client = getConnection();
							HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!regJlCheckPerson.action",
									"post");
							HttpResponse res = client.execute(post);
							if (res.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = res.getEntity();
								String message = EntityUtils.toString(entity, "utf-8");
						 
									System.out.println("message="+message);
									if(message.equals("1")) {
										out.print("{\"message\":\"" + message + "\"}");
										out.flush();
										out.close();
									}
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 
					
			 
				}    else if(opflag.equals("updatePerson")) {
					try {
						String openid =request.getParameter("openid");
						openid = Util.nullToString(openid);
	 
						String creditCode =request.getParameter("creditCode");
						creditCode = Util.nullToString(creditCode);
						
						 
						 	
							Map<String, String> map = new HashMap<String, String>();
							map.put("openid", openid);
					 
							map.put("creditCode", creditCode);
							
							
							HttpClient client = getConnection();
							HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!UpdateCheckPerson.action",
									"post");
							HttpResponse res = client.execute(post);
							if (res.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = res.getEntity();
								String message = EntityUtils.toString(entity, "utf-8");
						 
									System.out.println("message="+message);
									if(message.equals("1")) {
										out.print("{\"message\":\"" + message + "\"}");
										out.flush();
										out.close();
									}
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 
					
			 
				}    	 else  if(opflag.equals("SendWxPerson")) {
					System.out.println("Into   Send  Wx号");
					String openid = request.getParameter("openid");
					String titleContent = request.getParameter("titleContent");
					String state = request.getParameter("state");
					String inittime = request.getParameter("inittime");
					String content = request.getParameter("content");
					System.out.println("content="+content);
 					String  code = GroupUtil.sendInfo
							(openid,titleContent,titleContent,state, inittime,content);
					System.out.println("Code ===="+code);
				} 	 		 else  if(opflag.equals("sendAppointmentInfo")) {
					System.out.println("Into   Send  Wx号");
					String openid = request.getParameter("openid");
					String titleContent = request.getParameter("titleContent");
					String state = request.getParameter("state");
					String inittime = request.getParameter("inittime");
					String content = request.getParameter("content");
					System.out.println("content="+content);
 					String  code = GroupUtil.sendAppointmentInfo
							(openid,titleContent,titleContent,
									state, inittime,content);
					System.out.println("Code ===="+code);
				} 	
				
				else  if(opflag.equals("SendWxContent")) {
					System.out.println("Into   SendWxContent");
					String title = request.getParameter("title");
					//内容
					String enclosure = request.getParameter("enclosure");
					//附件
					String noticeinfo = request.getParameter("noticeinfo");
					String type = request.getParameter("type");
					String sendtime = request.getParameter("sendtime");
					String content = request.getParameter("content");
 					GroupUtil.SendWxContent(type,title, enclosure, sendtime, noticeinfo);
					
				}
			else if (opflag.equals("wxloginIn")) {

				String prjname = request.getParameter("prjname");
				prjname = Util.nullToString(prjname);

				String corpname = request.getParameter("corpname");
				corpname = Util.nullToString(corpname);
					
				String cardnum = request.getParameter("cardnum");
				cardnum = Util.nullToString(cardnum);

				String tiwen = request.getParameter("tiwen");
				tiwen = Util.nullToString(tiwen);
				/* 20200406 */
				String state = request.getParameter("state");
				state = Util.nullToString(state);
				String personType = request.getParameter("personType");// 20200512
																		// fcl
				personType = Util.nullToString(personType);

				String personOne = request.getParameter("personOne");// 20200512
																		// fcl
				personOne = Util.nullToString(personOne);
				String openid = (String) request.getSession().getAttribute("openid");

				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("personOne", personOne); // 20200512 fcl
				map.put("personType", personType); // 20200512 fcl
				map.put("prjname", prjname);
				// map.put("cardnum", cardnum);
				map.put("tiwen", tiwen);
				map.put("openid", openid);
				map.put("state", state);
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!wxloginInNew.action", "post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						if ("n".equals(message)) {
							out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
							return;
						}
						LoginVo loginVo = JsonUtil.jsonToBean(message, LoginVo.class, "loginVo");
						out.print("{\"prjName\":\"" + loginVo.getPrjname() + "\"" + ",\"name\":\"" + loginVo.getName()
								+ "\"" + ",\"cardnum\":\"" + loginVo.getCardnum() + "\"" + ",\"tiwen\":\"" + tiwen
								+ "\",\"prjId\":\"" + loginVo.getPrjId() + "\"" + ",\"corpname\":\""
								+ loginVo.getCorpname() + "\"" + ",\"phone\":\"" + loginVo.getPhone() + "\""
								+ ",\"personOne\":\"" + loginVo.getPersonOne() + "\"" + ",\"personType\":\""
								+ loginVo.getPersonType() + "\"" + ",\"status\":\"" + loginVo.getStatus() + "\"}");
						out.flush();
						out.close();

					} else {
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			} 	else if (opflag.equals("sysManagerNew")) {
				try {

					String prjName = request.getParameter("prjName");
					prjName = Util.nullToString(prjName);
					String Corp = request.getParameter("Corp");
					Corp = Util.nullToString(Corp);
					String name = request.getParameter("name");
					name = Util.nullToString(name);
					String cardnum = request.getParameter("cardnum");
					cardnum = Util.nullToString(cardnum);
					String phone = request.getParameter("phone");
					phone = Util.nullToString(phone);
					String openid = (String) request.getSession().getAttribute("openid");
					openid = Util.nullToString(openid);
					String personType = request.getParameter("personType");
					personType = Util.nullToString(personType);

					String personOne = request.getParameter("personOne");
					personOne = Util.nullToString(personOne);

					String ifbeen = request.getParameter("ifbeen");
					ifbeen = Util.nullToString(ifbeen);
					String ifaffirm = request.getParameter("ifaffirm");
					ifaffirm = Util.nullToString(ifaffirm);
					String iffever = request.getParameter("iffever");
					iffever = Util.nullToString(iffever);

					Map<String, String> map = new HashMap<String, String>();
					map.put("prjId", prjId);
					map.put("prjName", prjName);
					map.put("Corp", Corp);
					map.put("name", name);
					map.put("cardnum", cardnum);
					map.put("phone", phone);
					map.put("openid", openid);
					map.put("personType", personType);
					map.put("personOne", personOne);
					map.put("ifbeen", ifbeen);
					map.put("ifaffirm", ifaffirm);
					map.put("iffever", iffever);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!sysManagerNew.action",
							"post");
					HttpResponse res = client.execute(post);

					try {
						if (res.getStatusLine().getStatusCode() == 200) {
							HttpEntity entity = res.getEntity();
							String message = EntityUtils.toString(entity, "utf-8");
							SysManagerVo sysManagerVo = JsonUtil.jsonToBean(message, SysManagerVo.class,
									"sysManagerVo");

							out.print("{\"status\":\"" + sysManagerVo.getStatus() + "\",\"prjId\":\"" + prjId
									+ "\",\"username\":\"" + sysManagerVo.getUsername() + "\"}");
							out.flush();
							out.close();

						} else {
							// System.out.println("请求失败");
						}
					} catch (Exception e) {
						out.flush();
						out.close();
						e.printStackTrace();
					}
				} catch (ParseException e) {
					out.flush();
					out.close();
					e.printStackTrace();
				}

			} 	
			else if (opflag.equals("relevanceUserState")) {
				System.out.println("11111111111111111");
					String openid = request.getParameter("openid");
					openid = Util.nullToString(openid);
					System.out.println("11111111111111111"+openid);
					GroupUtil.updateUserGroup("105", openid);		
					
			} 	else if (opflag.equals("relevanceUser")) {
				try {

					String pid = request.getParameter("pid");
					pid = Util.nullToString(pid);
				 
					String openid = request.getParameter("openid");
					openid = Util.nullToString(openid);
					GroupUtil.updateUserGroup("105", openid);		
					
					
//					pid="202203248420085051247432";
//					openid="omCLR1biIaKE7-OFZ0qJ4ILoj1-g";
					System.out.println("pid==="+pid);
					System.out.println("openid==="+openid);
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("openid", openid);
					map.put("pid", pid);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!relevanceUser.action",
							"post");
					HttpResponse res = client.execute(post);
					try {
						if (res.getStatusLine().getStatusCode() == 200) {
							HttpEntity entity = res.getEntity();
							String message = EntityUtils.toString(entity, "utf-8");
							
							System.out.println("message="+message);
							if(message.equals("0")) {
								GroupUtil.updateUserGroup("105", openid);		
								}
							
//							String registerVo = JsonUtil.jsonToBean(message, String.class);
							out.print(message);
							out.flush();
							out.close();
						} else {
							// System.out.println("请求失败");
						}
					} catch (Exception e) {
						out.flush();
						out.close();
						e.printStackTrace();
					}
				} catch (ParseException e) {
					out.flush();
					out.close();
					e.printStackTrace();
				}

			} 
			else if (opflag.equals("updateRegisterPerson")) {
				System.out.println("into    人员注册");
				long startTime = System.currentTimeMillis();
				String prjname = request.getParameter("prjname");
				prjname = Util.nullToString(prjname);
				String corpname = request.getParameter("corpname");
				corpname = Util.nullToString(corpname);
				String name = request.getParameter("name");
				name = Util.nullToString(name);
				String cardnum = request.getParameter("cardnum");
				cardnum = Util.nullToString(cardnum);
				String phone = request.getParameter("phone");
				phone = Util.nullToString(phone);
				String personType = request.getParameter("personType");
				personType = Util.nullToString(personType);
				String personOne = request.getParameter("personOne");
				personOne = Util.nullToString(personOne);
				String openid = (String) request.getSession().getAttribute("openid");
				openid = Util.nullToString(openid);
				String ifbeen = request.getParameter("ifbeen");
				ifbeen = Util.nullToString(ifbeen);
				String ifaffirm = request.getParameter("ifaffirm");
				ifaffirm = Util.nullToString(ifaffirm);
				String iffever = request.getParameter("iffever");
				iffever = Util.nullToString(iffever);

				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("prjname", prjname);
				map.put("corpname", corpname);
				map.put("name", name);
				map.put("cardnum", cardnum);
				map.put("phone", phone);
				map.put("personType", personType);
				map.put("personOne", personOne);
				map.put("openid", openid);
				map.put("ifbeen", ifbeen);
				map.put("ifaffirm", ifaffirm);
				map.put("iffever", iffever);
				HttpClient client = getConnection();
				System.out.println("into  监管平台！！！");
				HttpUriRequest post = getRequestMethod(map,
						"http://221.207.229.44:7777/wx!updateRegisterPersonNew.action", "post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						RegisterVo registerVo = JsonUtil.jsonToBean(message, RegisterVo.class, "registerVo");

						request.setAttribute("prjId", registerVo.getPrjId());
						request.setAttribute("prjName", registerVo.getPrjname());
						request.setAttribute("cardnum", registerVo.getCardnum());
						request.setAttribute("info", registerVo.getInfo());

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " loginVo所占内存=="
								+ Test.Size(registerVo.toString().length()) + "  访问updateRegisterPerson时间： "
								+ (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));

						out.print("{\"status\":\"" + registerVo.getInfo() + "\"}");

						out.flush();
						out.close();
					} else {
						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

			//  施工企业报表 
			else if (opflag.equals("consCorpLogin")) {
				String username = request.getParameter("username");
				username = Util.nullToString(username);

				String password = request.getParameter("password");
				password = Util.nullToString(password);
				String states = request.getParameter("states");
				states = Util.nullToString(states);
				System.out.println("states="+states);
				String openid = (String) request.getSession().getAttribute("openid");
				
				System.out.println("username="+username);
				 
				request.setAttribute("username", username);
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				map.put("password", password);
				map.put("openid", openid);
				map.put("states", states);
				System.out.println("openid="+openid);
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!consCorpLogin.action",
						"post");
				HttpResponse res = client.execute(post);

				try {
					System.out.println("res.getStatusLine().getStatusCode() ="+res.getStatusLine().getStatusCode() );
//					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						System.out.println("message==="+message);
						out.print("{\"message\":\"" + message+ "\",\"username\":\"" + username+ "\"}");
						out.flush();
						out.close();
//					} else {
						// ////System.out.println("请求失败");
//					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			} else if (opflag.equals("roadcompany")) {
				String roadMonth = request.getParameter("roadMonth");
				roadMonth = Util.nullToString(roadMonth);
				String personCount = request.getParameter("personCount");
				personCount = Util.nullToString(personCount);
				String roadMoney = request.getParameter("roadMoney");
				roadMoney = Util.nullToString(roadMoney);
				String poadValue = request.getParameter("poadValue");
				poadValue = Util.nullToString(poadValue);
				String roadProjectCount = request.getParameter("roadProjectCount");
				roadProjectCount = Util.nullToString(roadProjectCount);
				String roadNewArea = request.getParameter("roadNewArea");
				roadNewArea = Util.nullToString(roadNewArea);
				String openid = (String) request.getSession().getAttribute("openid");
				openid = Util.nullToString(openid);
				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("roadMonth", roadMonth);
				map.put("personCount", personCount);
				map.put("roadMoney", roadMoney);
				map.put("poadValue", poadValue);
				map.put("roadProjectCount", roadProjectCount);
				map.put("roadNewArea", roadNewArea);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!updateroadcompany.action",
						"post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						RoadWordCompanyViewVo roadWordCompanyViewVo = JsonUtil.jsonToBean(message,
								RoadWordCompanyViewVo.class, "roadWordCompanyViewVo");

						out.print("{\"status\":\"" + roadWordCompanyViewVo.getStaus() + "\"}");

						out.flush();
						out.close();
					} else {
						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
 					e.printStackTrace();
				}

			} else if (opflag.equals("superviosrcompany")) {
				long startTime = System.currentTimeMillis();
				
				System.out.println("superviosrcompany============");
				
				String SuperviosrMonth = request.getParameter("superviosrMonth");
				SuperviosrMonth = Util.nullToString(SuperviosrMonth);

				String SuperviosrCount = request.getParameter("superviosrCount");
				SuperviosrCount = Util.nullToString(SuperviosrCount);

				String SuperviosrValue = request.getParameter("superviosrValue");
				SuperviosrValue = Util.nullToString(SuperviosrValue);

				String SuperviosrProjectCount = request.getParameter("superviosrProjectCount");
				SuperviosrProjectCount = Util.nullToString(SuperviosrProjectCount);

				String superviosrMoney = request.getParameter("superviosrMoney");
				superviosrMoney = Util.nullToString(superviosrMoney);
				String openid = (String) request.getSession().getAttribute("openid");
				openid = Util.nullToString(openid);
				
				String prjname=request.getParameter("prjname");//工程名称
				prjname=Util.nullToString(prjname);
			    System.out.println("prjname="+prjname);
				String buildcompany=request.getParameter("buildcompany");//建设单位
				buildcompany=Util.nullToString(buildcompany);
			    
				String cbcompany=request.getParameter("cbcompany");//承包单位
				cbcompany=Util.nullToString(cbcompany);
			    
				String bygcgk=request.getParameter("bygcgk");//本月工程概况
				bygcgk=Util.nullToString(bygcgk);
			    
				String bygcxxjd=request.getParameter("bygcxxjd");//本月工程形象进度
				bygcxxjd=Util.nullToString(bygcxxjd);
			    
				String sjwcyjhwcbj=request.getParameter("sjwcyjhwcbj");//实际完成与计划完成比较
				sjwcyjhwcbj=Util.nullToString(sjwcyjhwcbj);
			    
				String jdfx=request.getParameter("jdfx");//进度分析
				jdfx=Util.nullToString(jdfx);
			    
				String zlqkfx=request.getParameter("zlqkfx");//质量情况分析
				zlqkfx=Util.nullToString(zlqkfx);
			    
				String zqcsjxg=request.getParameter("zqcsjxg");//争取措施及效果
				zqcsjxg=Util.nullToString(zqcsjxg);
			    
				String sbgzl=request.getParameter("sbgzl");//申报工作量
				sbgzl=Util.nullToString(sbgzl);
			    
				String spgzl=request.getParameter("spgzl");////审批工作量
				spgzl=Util.nullToString(spgzl);
			    
			    String zjs=request.getParameter("zjs");//增减数
			    zjs=Util.nullToString(zjs);
			    
				String sbgck=request.getParameter("sbgck");//申报工程款
				sbgck=Util.nullToString(sbgck);
			    System.out.println("申报工程款================"+sbgck);
			    String spgck=request.getParameter("spgck");//审批工程款
			    spgck=Util.nullToString(spgck);
			    
			    String ljgck=request.getParameter("ljgck");//累计工程款
			    ljgck=Util.nullToString(ljgck);
			    
			    String tzkzfx=request.getParameter("tzkzfx");//投资控制分析
			    tzkzfx=Util.nullToString(tzkzfx);
//				Map session = ActionContext.getContext().getSession();
				String  username= request.getParameter("username");
			    username=Util.nullToString(username);
			    System.out.println("username="+username);
			    
			    
	
			    
				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("tzkzfx", tzkzfx);
				map.put("ljgck", ljgck);
				map.put("spgck", spgck);
				map.put("zjs", zjs);
				map.put("spgzl",spgzl);
				map.put("sbgzl", sbgzl);
				map.put("zqcsjxg", zqcsjxg);
				map.put("zlqkfx",zlqkfx);
				map.put("username",username);
				map.put("jdfx", jdfx);
				map.put("sjwcyjhwcbj", sjwcyjhwcbj);
				map.put("bygcxxjd", bygcxxjd);
				map.put("bygcgk", bygcgk);
				map.put("cbcompany", cbcompany);
				map.put("buildcompany", buildcompany);
				map.put("prjname", prjname);
				map.put("sbgck", sbgck);
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map,
						"http://221.207.229.44:7777/wx!updatesuperviosrcompany.action", "post");
				HttpResponse res = client.execute(post);
				try {
 					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						SuperviosrCompanyVIewVo superviosrCompanyVIewVo = JsonUtil.jsonToBean(message,
								SuperviosrCompanyVIewVo.class, "superviosrCompanyVIewVo");
						System.out.println(" superviosrCompanyVIewVo.getStaus() ="+ superviosrCompanyVIewVo.getStaus() );
						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " loginVo所占内存=="
								+ Test.Size(superviosrCompanyVIewVo.toString().length())
								+ "  访问updateRegisterPerson时间： " + (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));

						out.print("{\"status\":\"y\"}");

						out.flush();
						out.close();
					} else {
//						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			if (opflag.equals("updateYgxqPage")) {
				long startTime = System.currentTimeMillis();
				String NowPopulation = request.getParameter("nowPopulation");
				NowPopulation = Util.nullToString(NowPopulation);

				String UserNick = request.getParameter("userNick");
				UserNick = Util.nullToString(UserNick);

				String UserType = request.getParameter("userType");
				UserType = Util.nullToString(UserType);

				String StartTime = request.getParameter("startTime");
				StartTime = Util.nullToString(StartTime);

				String EndTime = request.getParameter("endTime");
				EndTime = Util.nullToString(EndTime);

				String bd = request.getParameter("bd");
				bd = Util.nullToString(bd);

				String fp = request.getParameter("fp");
				fp = Util.nullToString(fp);
				String yq = request.getParameter("yq");
				yq = Util.nullToString(yq);

				String qt = request.getParameter("qt");
				qt = Util.nullToString(qt);
				String openid = (String) request.getSession().getAttribute("openid");
				openid = Util.nullToString(openid);
				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("nowPopulation", NowPopulation);
				map.put("userNick", UserNick);
				map.put("startTime", StartTime);
				map.put("endTime", EndTime);
				map.put("userType", UserType);
				map.put("bd", bd);
				map.put("fp", fp);
				map.put("yq", yq);
				map.put("qt", qt);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!updateuserdemand.action",
						"post");

				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						UserDemandViewVo userDemandViewVo = JsonUtil.jsonToBean(message, UserDemandViewVo.class,
								"userDemandViewVo");

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " loginVo所占内存=="
								+ Test.Size(userDemandViewVo.toString().length()) + "  访问updateRegisterPerson时间： "
								+ (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));

						out.print("{\"status\":\"" + userDemandViewVo.getStatus() + "\"}");

						out.flush();
						out.close();
					} else {
						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  else  if(opflag.equals("updateComplaints")) {
				long startTime = System.currentTimeMillis();
				 
				System.out.println("prjid="+prjId);
 				String persoNname = request.getParameter("persoNname");
 				persoNname = Util.nullToString(persoNname);
 				String unitName = request.getParameter("unitName");
 				unitName = Util.nullToString(unitName);
				String unitTel = request.getParameter("unitTel");
				unitTel = Util.nullToString(unitTel);
				String personTel = request.getParameter("personTel");
				personTel = Util.nullToString(personTel);
				String complaintsContend = request.getParameter("complaintsContend");
				complaintsContend = Util.nullToString(complaintsContend);
				String openid = request.getParameter("openid");
				openid = Util.nullToString(openid);
				
				
				String dept_name = request.getParameter("dept_name");
				dept_name = Util.nullToString(dept_name);
				String laborUnit = request.getParameter("laborUnit");
				laborUnit = Util.nullToString(laborUnit);
				String laborUnitName = request.getParameter("laborUnitName");
				laborUnitName = Util.nullToString(laborUnitName);
				String laborUnitTel = request.getParameter("laborUnitTel");
				laborUnitTel = Util.nullToString(laborUnitTel);
				String startime = request.getParameter("startime");
				startime = Util.nullToString(startime);
				String endtime = request.getParameter("endtime");
				endtime = Util.nullToString(endtime);
				String oweMoney = request.getParameter("oweMoney");
				oweMoney = Util.nullToString(oweMoney);
				String projectPersonName = request.getParameter("projectPersonName");
				projectPersonName = Util.nullToString(projectPersonName);
				String projectPersonTel = request.getParameter("projectPersonTel");
				projectPersonTel = Util.nullToString(projectPersonTel);
				
				String teamName = request.getParameter("teamName");
				teamName = Util.nullToString(teamName);
				
				String teamTel = request.getParameter("teamTel");
				teamTel = Util.nullToString(teamTel);
				String cardnum = request.getParameter("cardnum");
				cardnum = Util.nullToString(cardnum);
				String personOne = request.getParameter("personOne");
				personOne = Util.nullToString(personOne);
				String projectName = request.getParameter("projectName");
				projectName = Util.nullToString(projectName);
				
				
				
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("persoNname", persoNname);
				map.put("unitName", unitName);
				map.put("unitTel", unitTel);
				map.put("personTel", personTel);
				map.put("complaintsContend", complaintsContend);
				map.put("personOne", personOne);
				map.put("cardnum", cardnum);
				map.put("teamTel", teamTel);
				map.put("teamName", teamName);
				map.put("projectPersonTel", projectPersonTel);
				map.put("projectPersonName", projectPersonName);
				map.put("oweMoney", oweMoney);
				map.put("endtime", endtime);
				map.put("startime", startime);
				map.put("laborUnitTel", laborUnitTel);
				map.put("laborUnitName", laborUnitName);
				map.put("laborUnit", laborUnit);
				map.put("openid", openid);
				map.put("dept_name", dept_name);
				map.put("projectName", projectName);
				
				
				
				
				
				
				
				
				
				
				
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!updateComplaints.action",
						"post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						ProjectcheckViewVo projectcheckViewVo = JsonUtil.jsonToBean(message, ProjectcheckViewVo.class,
								"projectcheckViewVo");

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " loginVo所占内存=="
								+ Test.Size(projectcheckViewVo.toString().length()) + "  访问updateRegisterPerson时间： "
								+ (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));

						out.print("{\"status\":\"" + projectcheckViewVo.getStauts() + "\"}");

						out.flush();
						out.close();
					} else {
						// ////System.out.println("请求失败");
					}
					
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}   else if (opflag.equals("updateProject")) {
				long startTime = System.currentTimeMillis();
				String epidmiccontrol = request.getParameter("epidmiccontrol");
				epidmiccontrol = Util.nullToString(epidmiccontrol);

				String roadlicence = request.getParameter("roadlicence");
				roadlicence = Util.nullToString(roadlicence);

				String bidding = request.getParameter("bidding");
				bidding = Util.nullToString(bidding);

				String contract = request.getParameter("contract");
				contract = Util.nullToString(contract);

				String peasang = request.getParameter("peasang");
				peasang = Util.nullToString(peasang);

				String personpostion = request.getParameter("personpostion");
				personpostion = Util.nullToString(personpostion);

				String sceneadmin = request.getParameter("sceneadmin");
				sceneadmin = Util.nullToString(sceneadmin);
				String safetyadmin = request.getParameter("safetyadmin");
				safetyadmin = Util.nullToString(safetyadmin);

				String qualityadmin = request.getParameter("qualityadmin");
				qualityadmin = Util.nullToString(qualityadmin);
				String other = request.getParameter("other");
				other = Util.nullToString(other);
				String openid = (String) request.getSession().getAttribute("openid");
				openid = Util.nullToString(openid);
				Map<String, String> map = new HashMap<String, String>();
				map.put("prjId", prjId);
				map.put("epidmiccontrol", epidmiccontrol);
				map.put("roadlicence", roadlicence);
				map.put("bidding", bidding);
				map.put("contract", contract);
				map.put("peasang", peasang);
				map.put("personpostion", personpostion);
				map.put("sceneadmin", sceneadmin);
				map.put("safetyadmin", safetyadmin);
				map.put("qualityadmin", qualityadmin);
				map.put("other", other);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!updateProjectCheck.action",
						"post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						ProjectcheckViewVo projectcheckViewVo = JsonUtil.jsonToBean(message, ProjectcheckViewVo.class,
								"projectcheckViewVo");

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " loginVo所占内存=="
								+ Test.Size(projectcheckViewVo.toString().length()) + "  访问updateRegisterPerson时间： "
								+ (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));

						out.print("{\"status\":\"" + projectcheckViewVo.getStauts() + "\"}");

						out.flush();
						out.close();
					} else {
						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (opflag.equals("ygxqLogin")) {

				String username = request.getParameter("username");
				username = Util.nullToString(username);

				String password = request.getParameter("password");
				password = Util.nullToString(password);

				String openid = (String) request.getSession().getAttribute("openid");

				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				map.put("password", password);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!consCorpLogin.action",
						"post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						out.print("{\"message\":\"" + message + "\"}");

						out.flush();
						out.close();

					} else {
						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (opflag.equals("projectLogin")) {

				String username = request.getParameter("username");
				username = Util.nullToString(username);

				String password = request.getParameter("password");
				password = Util.nullToString(password);

				String openid = (String) request.getSession().getAttribute("openid");

				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				map.put("password", password);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!projectLogin.action", "post");

				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");

						out.print("{\"message\":\"" + message + "\"}");

						out.flush();
						out.close();

					} else {
						// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			} else if (opflag.equals("jianlilogin")) {
				String username = request.getParameter("username");
				username = Util.nullToString(username);

				String password = request.getParameter("password");
				password = Util.nullToString(password);

				String openid = (String) request.getSession().getAttribute("openid");

				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username);
				map.put("password", password);
				map.put("openid", openid);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!jianliLogin.action", "post");
				HttpResponse res = client.execute(post);

				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						out.print("{\"message\":\"" + message + "\"}");
						out.flush();
						out.close();

					} else {
						//// ////System.out.println("请求失败");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 		else if (opflag.equals("serchAdmin")) {// tc 2020年4月5日16:37:14
				String prjName = request.getParameter("prjName");
				Map<String, String> map = new HashMap<String, String>();
				map.put("opflag", "queryProject");
				map.put("prjName", prjName);
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryProject.action", "post");
				HttpResponse res = client.execute(post);

				if (res.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = res.getEntity();
					String message = EntityUtils.toString(entity, "utf-8");
					List mapType = JSON.parseArray(message);
					JSONObject obj = null;
					List<JSON> list = new ArrayList<JSON>();
					for (Object object : mapType) {
						obj = JSONObject.parseObject(object.toString());
						list.add(obj);
					}
					request.setAttribute("list", list);
					request.setAttribute("prjName", prjName);
					request.setAttribute("count", list.size());
					request.getRequestDispatcher("tc_checkPeronRegister.jsp").forward(request, response);
//					 out.flush();
//					 out.close();
				}
			} else if (opflag.equals("updateAdmin")) {// tc 2020年4月6日15:37:31
				String oldopenid = request.getParameter("oldopenid");
				String select = request.getParameter("select");

				JSONObject json = JSON.parseObject(select);
				Map<String, String> map = new HashMap<String, String>();
				map.put("opflag", "changeSysManager");
				map.put("OpenId", json.get("openid").toString());
				map.put("oldOpenId", oldopenid);
				map.put("Name", json.get("name").toString());
				map.put("Cardnum", json.get("cardnum").toString());
				map.put("Phone", json.get("phone").toString());
				map.put("Corpname", json.get("corpname").toString());
				map.put("PrjId", json.get("prjId").toString());

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryProject.action", "post");
				HttpResponse res = client.execute(post);

				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");
				if (message.equals("n")) {// 20200512 fcl 为n则该人员已在其他工程担任管理员
					out.print("n");
				} else {
					out.print("ok");
				}
				out.flush();
				out.close();
			} else if (opflag.equals("getGroup")) {
				String GroupId = request.getParameter("GroupId");
//				System.out.println("GroupId="+GroupId);
				String first = request.getParameter("first");
//				System.out.println("first="+first);
				String keyword1 = request.getParameter("keyword1");
				String keyword2 = request.getParameter("keyword2");
				String keyword3 = request.getParameter("keyword3");
				String remark = request.getParameter("remark");
				String json = "";
				if (GroupId == null || "".equals(GroupId)) {
					out.print("gourpId不能为空");
					out.flush();
					out.close();
//				} else if (GroupId.equals("100")) {
//				}
				}else{
					json = GroupUtil.ModSend(GroupId, first, keyword1, keyword2, keyword3, remark);
				}
				out.print(json);
				out.flush();
				out.close();
			} else if (opflag.equals("getGroupPerson")) {// 获取某个组的所有人员
				String GroupId = request.getParameter("GroupId");
				if (GroupId == null || "".equals(GroupId)) {
					out.print("gourpId不能为空");
					out.flush();
					out.close();
				}
				JSONObject json = GroupUtil.ListGroup(GroupId);
				out.print(json);
				out.flush();
				out.close();
			} else if (opflag.equals("getGroupList")) {// 获取所有组 不包括人员 只能查看
				JSONObject json = GroupUtil.getAllGroups();
				out.print(json);
				out.flush();
				out.close();
			} else if (opflag.equals("creatGroup")) {// 创建组
				String groupname = request.getParameter("groupname");
				JSONObject json = GroupUtil.createGroup(groupname);
				out.print(json);
				out.flush();
				out.close();
			} else if (opflag.equals("updateGroup")) {// 修改组
				String groupId = request.getParameter("groupId");
				String newGroupName = request.getParameter("newGroupName");
				JSONObject json = GroupUtil.updateGroup(groupId, newGroupName);
				out.print(json);
				out.flush();
				out.close();
			} else if (opflag.equals("updateUserGroup")) {// 将某个人调组
				String toGroupId = request.getParameter("toGroupId");
				String openId = request.getParameter("openId");
				JSONObject json = GroupUtil.updateUserGroup(toGroupId, openId);
				out.print(json);
				out.flush();
				out.close();
			} else if (opflag.equals("getUserGroup")) {// 获取某个人的组信息
				String openId = request.getParameter("openId");
				JSONObject json = GroupUtil.getUserGroup(openId);
				out.print(json);
				out.flush();
				out.close();
			} else  if(opflag.equals("getUserInfo")){
				try {
					System.out.println("Into   getUserInfo");
					String openId = request.getParameter("openId");
					List json = GroupUtil.getUserInfo("",openId);
					System.out.println("json==="+json);
					out.print(json);
					out.flush();
					out.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else  if(opflag.equals("getusersummary")){
				try {
					System.out.println("Into   getUserInfo");
					String openId = request.getParameter("openId");
					String begin_date = request.getParameter("begin_date");
					String end_date = request.getParameter("end_date");
					List json = GroupUtil.getusersummary(begin_date,end_date);
					System.out.println("json==="+json);
					out.print(json);
					out.flush();
					out.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				String message = "success";
				// 把微信返回的xml信息转义成map
				Map<String, String> map = XmlUtil.xmlToMap(request);
				String fromUserName = map.get("FromUserName");// 消息来源用户标识
				String toUserName = map.get("ToUserName");// 消息目的用户标识
				String msgType = map.get("MsgType");// 消息类型
				String content = map.get("Content");// 消息内容
				String eventType = map.get("Event");
				// ////////System.out.println("eventType="+eventType);
				if (MessageUtil.MSGTYPE_EVENT.equals(msgType)) {// 如果为事件类型
					if (MessageUtil.MESSAGE_SUBSCIBE.equals(eventType)) {// 处理订阅事件
						message = MessageUtil.subscribeForText(toUserName, fromUserName);
					} else if (MessageUtil.MESSAGE_UNSUBSCIBE.equals(eventType)) {// 处理取消订阅事件
						message = MessageUtil.unsubscribe(toUserName, fromUserName);
					}
				}
				out.println(message);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " Post opflag==" + opflag);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 允许跨域的主机地址 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		/* 重新预检验跨域的缓存时间 (s) */
		response.setHeader("Access-Control-Max-Age", "3600");
		/* 允许跨域的请求头 */
		response.setHeader("Access-Control-Allow-Headers", "*");
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 设置日志输出级别为info，这将覆盖配置文件中设置的级别
		// log.setLevel(Level.INFO);
		String opflag = request.getParameter("opflag");
		opflag = Util.nullToString(opflag);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;setchar=utf-8"); 
		PrintWriter out = response.getWriter();
		String prjId = request.getParameter("prjId");//
		request.setAttribute("prjId", prjId);
		try {

			Calendar now = Calendar.getInstance();
			System.out.println("opflagGet=222222222222222"+opflag);
			System.out.println("2222222222222222222");
 
			
			if (opflag.equals("wxlogin")) {
				long startTime = System.currentTimeMillis();
				String state = request.getParameter("state");
				state = Util.nullToString(state);
				try {
					String code = request.getParameter("code");
					
					
					if (null == code || "null".equals(code) || "".equals(code)) {
						out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");

						return;
					}
					// 通过code获取用户openid
					JSONObject wxUser = CoreService.getOpenid(code);
					String openid = wxUser.getString("openid");
					System.out.println("OpenId="+openid);
					// 注释
					if (null == openid || "null".equals(openid) || "".equals(openid)) {
						out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  openid扫码失败，请重新扫码! ");
						return;
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put("prjId", prjId);
					map.put("openid", openid);
					map.put("state", state);

					request.getSession().setAttribute("openid", openid);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!wxloginNew.action",
							"post");

					HttpResponse res = client.execute(post);
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						if ("n".equals(message)) {
							out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
							return;
						}

						PrjAndPersonViewVo prjAndPersonViewVo = JsonUtil.jsonToBean(message, PrjAndPersonViewVo.class,
								"prjAndPersonViewVo");
						request.setAttribute("prjId", prjAndPersonViewVo.getPrjId());
						
						if(!prjAndPersonViewVo.getSysManagerop().equals("intoexamine")){
						request.setAttribute("prjname", prjAndPersonViewVo.getPrjname());
						
						List UnitEngineeringlists = new ArrayList();
				    	
				    	List UnitEngineeringlists2 = new ArrayList();
					 	List UnitEngineeringlists3 = new ArrayList();
					 	List UnitEngineeringlists4 = new ArrayList();
					 	
					 	List UnitEngineeringlists5 = new ArrayList();
					 	List UnitEngineeringlists6 = new ArrayList();
					 	
					 	
					 	String[]singletonNameArrs = null ;//建设
				    	String[]  consCorpName = null ;//施工
				    	String[]	 superCorpName = null ;//监理
				    	
				    	String[]	subPagUtil = null ;//分包
				    	
				    	String[]	lwunit = null ;//劳务
				    	
				    	String[]	designCorp = null ;//设计
				    	
				    	singletonNameArrs= prjAndPersonViewVo.getBuildCorpName().split(",");
				    	consCorpName= prjAndPersonViewVo.getConsCorpName().split(",");
				    	superCorpName= prjAndPersonViewVo.getSuperCorpName().split(",");
				    	subPagUtil=prjAndPersonViewVo.getSubPagUtil().split(",");
				    	if(prjAndPersonViewVo.getLwunit()==null){
				    		prjAndPersonViewVo.setLwunit("");
				    	}
				    	lwunit=prjAndPersonViewVo.getLwunit().split(",");
				    	designCorp=prjAndPersonViewVo.getDesignCorp().split(",");
				    	
				    	
				    	if(prjAndPersonViewVo.getSubPagUtil()!=null){
				    		subPagUtil=prjAndPersonViewVo.getSubPagUtil().split(",");
				    	}
				    	if(singletonNameArrs.length>=1){
		    			for(int i =0;i<singletonNameArrs.length;i++){
		    				User user = new User();
		    				user.setName(singletonNameArrs[i].replaceAll(" ", ""));
		    				UnitEngineeringlists.add(user);//建设
		    			}
				    	}
				    	if(consCorpName.length>=1){
				    		for(int i =0;i<consCorpName.length;i++){
				    			User user = new User();
				    			user.setSname(consCorpName[i].replaceAll(" ", ""));
				    			UnitEngineeringlists2.add(user);//施工
				    		}
				    	}
				    	if(superCorpName.length>=1){
				    		for(int i =0;i<superCorpName.length;i++){
				    			User user = new User();
				    			user.setJname(superCorpName[i].replaceAll(" ", ""));
				    			UnitEngineeringlists3.add(user);//监理
				    		}
				    	}
				    	if(prjAndPersonViewVo.getSubPagUtil()!=null){
				    	if(subPagUtil.length>=1){
				    		for(int i =0;i<subPagUtil.length;i++){
				    			User user = new User();
				    			user.setFbname(subPagUtil[i].replaceAll(" ", ""));
				    			UnitEngineeringlists4.add(user);//分包
				    		}
				    	}
				    	}
				    	
				    	if(prjAndPersonViewVo.getLwunit()!=null){
					    	if(lwunit.length>=1){
					    		for(int i =0;i<lwunit.length;i++){
					    			User user = new User();
					    			user.setLwname(lwunit[i].replaceAll(" ", ""));
					    			UnitEngineeringlists5.add(user);//劳务
					    		}
					    	}
					    	}
				    	
				    	if(prjAndPersonViewVo.getDesignCorp()!=null){
				    		if(designCorp.length>=1){
				    			for(int i =0;i<designCorp.length;i++){
				    				User user = new User();
				    				user.setSjname(designCorp[i].replaceAll(" ", ""));
				    				UnitEngineeringlists6.add(user);//设计
				    			}
				    		}
				    	}
				    	
				    	
				    	
				    	
				    	request.setAttribute("UnitEngineeringlists", UnitEngineeringlists);
						request.setAttribute("UnitEngineeringlists2", UnitEngineeringlists2);
						request.setAttribute("UnitEngineeringlists3", UnitEngineeringlists3);
						request.setAttribute("UnitEngineeringlists4", UnitEngineeringlists4);
						request.setAttribute("UnitEngineeringlists5", UnitEngineeringlists5);
						request.setAttribute("UnitEngineeringlists6", UnitEngineeringlists6);
						request.setAttribute("consCorpName", prjAndPersonViewVo.getConsCorpName());
						request.setAttribute("superCorpName", prjAndPersonViewVo.getSuperCorpName());
						request.setAttribute("buildCorpName", prjAndPersonViewVo.getBuildCorpName());
						request.setAttribute("name", prjAndPersonViewVo.getName());
						request.setAttribute("cardnum", prjAndPersonViewVo.getCardnum());
						request.setAttribute("phone", prjAndPersonViewVo.getPhone());
						request.setAttribute("personType", prjAndPersonViewVo.getPersonType());// 20200512
																								// fcl
						request.setAttribute("personOne", prjAndPersonViewVo.getPersonOne());// 20200512
																								// fcl

						/* 20200511 txb begin */
						String readonlyOp = "";
						if (!prjAndPersonViewVo.getCardnum().equals(""))
							readonlyOp = " readonly ";
						request.setAttribute("readonlyOp", readonlyOp);
						/* 20200511 txb end */
						/* 20200406 区分入口 */
						request.setAttribute("state", state);
						}
						// 判断工程是否有工地系统管理员20200323
						// 0 没有工程管理员 1 有工程管理员 2 该openid在别的工程担任管理员
						if(prjAndPersonViewVo.getSysManagerop().equals("intoexamine")){
							request.getRequestDispatcher("updateproject.jsp").forward(request, response);
						}else 	if (prjAndPersonViewVo.getSysManagerop().equals("registerPerson")) {
							request.getRequestDispatcher("WEB-INF/jsp/registerPerson.jsp").forward(request, response);
						} else if (prjAndPersonViewVo.getSysManagerop().equals("wxlogin")) {
							request.getRequestDispatcher("WEB-INF/jsp/wxlogin.jsp").forward(request, response);
						} else if (prjAndPersonViewVo.getSysManagerop().equals("registerPersonUpdate")) {
							request.getRequestDispatcher("WEB-INF/jsp/registerPerson.jsp").forward(request, response);
						} else if (prjAndPersonViewVo.getSysManagerop().equals("registerManager")) {
							request.getRequestDispatcher("WEB-INF/jsp/registerManager.jsp").forward(request, response);
						} else {
							out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						}

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime()
								+ " prjAndPersonViewVo所占内存==" + Test.Size(prjAndPersonViewVo.toString().length())
								+ "  访问wxlogin时间： " + (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));
						out.flush();
						out.close();
					} else {
						return;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}    else  if(opflag.equals("TestSendWxPerson")) {
				System.out.println("TestSendWxPerson");
				System.out.println("22222222222222");
//				String uid1 = GroupUtil.getUnionidByOpenid("o_npB433YmPPVpv2vkWzr9Fpninc");
//				String uid2 = GroupUtil.getUnionidByOpenid("omCLR1biIaKE7-OFZ0qJ4ILoj1-g");
//				
				
				String  code = GroupUtil.sendInfo
						("o_npB433YmPPVpv2vkWzr9Fpninc","小程序通知","小程序通知","小程序通知", "小程序通知","小程序通知");
				
				System.out.println("Code ==22222222222=="+code);
			}else  if(opflag.equals("jumpView")) {
				System.out.println("INto  jumpView");
				String code = request.getParameter("code");
				JSONObject wxUser = CoreService.getOpenid(code);
				String openid = wxUser.getString("openid");

				request.setAttribute("openid", openid);
				request.getRequestDispatcher("jumpView.jsp").forward(request, response);

//					request.getRequestDispatcher("http://1.189.64.182/boteli/openidAction!queryIndex.action").forward(request, response);
			
			}else if(opflag.equals("CheckOpenId")) {
				System.out.println("========================");
				String code = request.getParameter("code");
				System.out.println("code========================"+code);
				if (null == code || "null".equals(code) || "".equals(code)) {
					out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
					return;
				}
			//	 通过code获取用户openid
				JSONObject wxUser = CoreService.getOpenid(code);
				String openid = wxUser.getString("openid");
				
				
				
				
				
				
				JSONObject group = GroupUtil.getUserGroup(openid);
				String groupID = group.getString("groupid");
				
				
				
//				HttpSession session = request.getSession();
//				session.setAttribute("openid",openid);
				
				
				
				request.setAttribute("openid", openid);
				System.out.println("openid="+openid+"groupID="+groupID);
				/**
				 * 监理企业检查人员组
				 */
				if(groupID.equals("105")){
					request.setAttribute("str", "111111");
					request.setAttribute("openid", openid);
 					request.getRequestDispatcher("SGEnterpriseService.jsp").forward(request, response);
 					
				}else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("openid",openid);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!queryJLUnitName.action", "post");
					HttpResponse res = client.execute(post);
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						@SuppressWarnings("unchecked")
						List<CreditVo>jlUnList = JsonUtil.jsonToBean(message, List.class,
								"jlUnList");
 						request.setAttribute("jlUnList", jlUnList);
				
 					request.getRequestDispatcher("NotNothing.jsp").forward(request, response);
					
				}
			}
				
//				String openid = wxUser.getString("openid");
				
				
			}   else if(opflag.equals("queryProjectDataList")) {
//				String code = request.getParameter("code");
//				if (null == code || "null".equals(code) || "".equals(code)) {
//					out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
//					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
//					return;
//				}
//				// 通过code获取用户openid
//				JSONObject wxUser = CoreService.getOpenid(code);
				String openid ="";
//				openid  = wxUser.getString("openid");
				
//				openid="omCLR1biIaKE7-OFZ0qJ4ILoj1-g";
				
				
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("openid", openid);
				HttpClient client = getConnection();
				
				
				
//				HttpUriRequest post1 = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!queryJLPersonData.action",
//						"post");
//				HttpResponse res1 = client.execute(post1);
//
//				if (res1.getStatusLine().getStatusCode() == 200) {
//					HttpEntity entity1 = res1.getEntity();
//					String checkPerson = EntityUtils.toString(entity1, "utf-8");
//					 JlCheckPersonVo  jlcheck = JsonUtil.jsonToBean(checkPerson, JlCheckPersonVo.class, "list");
//
//					
//					
//				}
				
				HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!queryJLPersonProject.action",
						"post");
				HttpResponse res = client.execute(post);

				if (res.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = res.getEntity();
					String message = EntityUtils.toString(entity, "utf-8");
					@SuppressWarnings("unchecked")
					List<ProMonomerVo> list = JsonUtil.jsonToBean(message, List.class, "list");
				
					
					
					
					request.setAttribute("list", list);
					request.setAttribute("openid", openid);
					HttpSession session = request.getSession();
					session.setAttribute("openid",openid);
					request.getRequestDispatcher("queryProject.jsp").forward(request, response);

				}
  
				
				
			}       else if(opflag.equals("queryMonomer")) {
//				String code = request.getParameter("code");
//				if (null == code || "null".equals(code) || "".equals(code)) {
//					out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
//					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
//					return;
//				}
//				// 通过code获取用户openid
//				JSONObject wxUser = CoreService.getOpenid(code);
			String openid =request.getParameter("openid");
			String proid =request.getParameter("proid");
			
			System.out.println("openid="+openid);

//			openid="omCLR1biIaKE7-OFZ0qJ4ILoj1-g";
			Map<String, String> map = new HashMap<String, String>();
			map.put("openid", openid);
			map.put("proid", proid);
			HttpClient client = getConnection();
			HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!queryJLPersonMonomer.action",
					"post");
			HttpResponse res = client.execute(post);
			
			if (res.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");
				
				@SuppressWarnings("unchecked")
				List<ProMonomerVo>list = JsonUtil.jsonToBean(message, List.class,
						"list");
				request.setAttribute("list", list);
				request.setAttribute("openid", openid);
				request.setAttribute("proid", proid);
				////System.out.println("into  jsp");
				request.getRequestDispatcher("queryProjectmonomer.jsp").forward(request, response);
				
			}
			
			
			
		}        else if(opflag.equals("queryCheckMonomer")) {
//				String code = request.getParameter("code");
//				if (null == code || "null".equals(code) || "".equals(code)) {
//					out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
//					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
//					return;
//				}
//				// 通过code获取用户openid
//				JSONObject wxUser = CoreService.getOpenid(code);
			String openid =request.getParameter("openid");
			String mid =request.getParameter("mid");
			String proid =request.getParameter("proid");
			
			System.out.println("openid="+openid);
			
//			openid="omCLR1biIaKE7-OFZ0qJ4ILoj1-g";
			Map<String, String> map = new HashMap<String, String>();
			map.put("openid", openid);
			map.put("mid", mid);
			HttpClient client = getConnection();
			HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!queryCheckMonomer.action",
					"post");
			HttpResponse res = client.execute(post);
			
			if (res.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");
				
				@SuppressWarnings("unchecked")
				 Map<String,Object> maps =  JsonUtil.jsonToBean(message, Map.class,
						"map");
//				System.out.println("maps="+maps);
				List<ProMonomerVo> list1 = (List<ProMonomerVo>) maps.get("list1");
				List<ProMonomerVo> list2 = (List<ProMonomerVo>) maps.get("list2");
				List<ProMonomerVo> list3 = (List<ProMonomerVo>) maps.get("list3");
				List<ProMonomerVo> list4 = (List<ProMonomerVo>) maps.get("list4");
				List<ProMonomerVo> list5= (List<ProMonomerVo>) maps.get("list5");				
				
				System.out.println("list5"+list5.get(0));
				 
				request.setAttribute("monomerVo", list5.get(0));
				
				request.setAttribute("proid", proid);
				request.setAttribute("list1", list1);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
				request.setAttribute("list4", list4);
				request.setAttribute("mid", mid);
//				request.setAttribute("openid", openid);
				////System.out.println("into  jsp");
				request.getRequestDispatcher("queryCheckMonomer.jsp").forward(request, response);
				
			}
		}     
		else if(opflag.equals("queryCheckById")) {
//				String code = request.getParameter("code");
//				if (null == code || "null".equals(code) || "".equals(code)) {
//					out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
//					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
//					return;
//				}
//				// 通过code获取用户openid
			
			System.out.println("queryCheckById====");
//				JSONObject wxUser = CoreService.getOpenid(code);
			String openid =request.getParameter("openid");
			String id =request.getParameter("id");
			String mid =request.getParameter("mid");
			String proid =request.getParameter("proid");
			 
			
//			openid="omCLR1biIaKE7-OFZ0qJ4ILoj1-g";
			Map<String, String> map = new HashMap<String, String>();
			map.put("openid", openid);
			map.put("id", id);
			HttpClient client = getConnection();
			HttpUriRequest post = getRequestMethod(map, "http://"+fangkaiUrl+"/jlcheck!queryCheckById.action",
					"post");
			HttpResponse res = client.execute(post);
			
			if (res.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");
				
				@SuppressWarnings("unchecked")
				Map<String,Object> maps =  JsonUtil.jsonToBean(message, Map.class,
						"map");
//				System.out.println("maps="+maps);
				List<UploadFileVo> fileList = (List<UploadFileVo>) maps.get("fileList");
				List<PersonCheckRecordVo> list1 = (List<PersonCheckRecordVo>) maps.get("list1");
				
				
				
				
				System.out.println("list5"+list1.get(0));
				
				request.setAttribute("recordVo", list1.get(0));
				
				request.setAttribute("proid", proid);
				request.setAttribute("mid", mid);
				request.setAttribute("fileList", fileList);
				request.getRequestDispatcher("queryProject2.jsp").forward(request, response);
				
				
			}
		}    
		else if(opflag.equals("updateCheckPage")) {
//				String code = request.getParameter("code");
//				if (null == code || "null".equals(code) || "".equals(code)) {
//					out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
//					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
//					return;
//				}
//				// 通过code获取用户openid
			
 
			String id =request.getParameter("id");
			String mid =request.getParameter("mid");
			String proid =request.getParameter("proid");
			String str =request.getParameter("str");
			 
			
			 String openid = (String)request.getSession().getAttribute("openid");
			  
			 System.out.println("Openid="+openid);
			
				request.setAttribute("str", str);
				request.setAttribute("proid", proid);
				request.setAttribute("mid", mid);
				request.getRequestDispatcher("updateCheckPage.jsp").forward(request, response);
				
			 
		}    
		else if(opflag.equals("updatePhotoPage")) {
			System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111");
			request.getRequestDispatcher("uploadFilesg.jsp").forward(request, response);
		}    

			else	if (opflag.equals("IsCheckUserInfo")) {
				long startTime = System.currentTimeMillis();
				String state = request.getParameter("state");
				state = Util.nullToString(state);
				try {
					String code = request.getParameter("code");
					if (null == code || "null".equals(code) || "".equals(code)) {
						out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");
						
						return;
					}
					// 通过code获取用户openid
					JSONObject wxUser = CoreService.getOpenid(code);
					String openid = wxUser.getString("openid");
					
					// 注释
					if (null == openid || "null".equals(openid) || "".equals(openid)) {
						out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  openid扫码失败，请重新扫码! ");
						return;
					}
					
					
					
					String phone = request.getParameter("phone");//
					phone = Util.nullToString(phone);
					String personname = request.getParameter("personname");//
					personname = Util.nullToString(personname);				
					
					String pid = request.getParameter("pid");//
					pid = Util.nullToString(pid);				
					
					
					
					System.out.println("OpenId="+openid);
					
				
					 
					JSONObject group = GroupUtil.getUserInfo(openid);
			 
 					String isFocus = group.get("subscribe").toString();
				 
					if(isFocus.equals("0")) {
						out.println("<script>alert(\"您未关注哈尔滨建筑业公众号，请先关注公众号再重新扫码!\");</script>");
					}else if(isFocus.equals("1")) {
						
					}
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("prjId", prjId);
					map.put("openid", openid);
					map.put("personname", personname);
					map.put("phone", phone);
					map.put("pid", pid);
					
					request.setAttribute("openid", openid);
					request.setAttribute("pid", pid);
					request.setAttribute("phone", phone);
					request.setAttribute("personname", personname);
					request.setAttribute("isFocus", isFocus);
					request.getRequestDispatcher("CheckWxUserInfo.jsp").forward(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}   else if (opflag.equals("queryWxSendById")) {// fcl  2021年7月2日11:08:18

				String id = request.getParameter("id");
				id = Util.nullToString(id);
				String type = request.getParameter("type");
				type = Util.nullToString(type);
				Map<String, String> map = new HashMap<String, String>();
				map.put("id",id);
				map.put("type",type);
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryWxSendRcordByID.action", "post");
				HttpResponse res = client.execute(post);
				System.out.println("Code=="+res.getStatusLine().getStatusCode());
				if (res.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = res.getEntity();
					String message = EntityUtils.toString(entity, "utf-8");
					YqfkInfoVo infoVo = JsonUtil.jsonToBean(message, YqfkInfoVo.class, "yqfkInfoVo");

					System.out.println("infoVo="+infoVo);
					request.setAttribute("infoVo",infoVo);
					request.getRequestDispatcher("querySendWxById.jsp").forward(request, response);
					}
				
				
			}else if (opflag.equals("serchWxSendRecord")) {// fcl  2021年7月2日11:08:18
			System.out.println("  into   serchWxSendRecord");
				
				HttpClient client = getConnection();
				Map<String, String> map = new HashMap<String, String>();

				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryWxSendRcord.action", "post");
				HttpResponse res = client.execute(post);
				System.out.println("Code=="+res.getStatusLine().getStatusCode());
				if (res.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = res.getEntity();
					String message = EntityUtils.toString(entity, "utf-8");
					List mapType = JSON.parseArray(message);
					JSONObject obj = null;
					List<JSON> list = new ArrayList<JSON>();
					for (Object object : mapType) {
						obj = JSONObject.parseObject(object.toString());
						list.add(obj);
					}
					
					System.out.println("List  ==="+list);
					
					request.setAttribute("list", list);
					request.setAttribute("count", list.size());
					request.getRequestDispatcher("serchWxSendRecord.jsp").forward(request, response);
					}
			}
			
			
			else if (opflag.equals("wxQGZlogin")) {
				System.out.println("Into  wxQGZlogin");
				long startTime = System.currentTimeMillis();
				String state = request.getParameter("state");
				state = Util.nullToString(state);
				try {
					String code = request.getParameter("code");
					
			
					if (null == code || "null".equals(code) || "".equals(code)) {
						out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + "  code扫码失败，请重新扫码! ");

						return;
					}
					// 通过code获取用户openid
					JSONObject wxUser = CoreService.getOpenid(code);
					String openid = wxUser.getString("openid");
					System.out.println("OpenId="+openid);
					

//					String  openid="omCLR1aI_3ps-vYBLQELxJysZ-JU";
					// 注释
					if (null == openid || "null".equals(openid) || "".equals(openid)) {
						out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
						log.info(request.getRemoteAddr() + "   "
								+ "" + CommonFunc.CurrentTime() + "  openid扫码失败，请重新扫码! ");
						return;
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put("prjId", prjId);
					map.put("openid", openid);
					map.put("state", state);

					request.getSession().setAttribute("openid", openid);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map,
							"http://221.207.229.44:7777/wx!querqgzWxPerson.action",
							"post");

					HttpResponse res = client.execute(post);
					System.out.println("res.getStatusLine().getStatusCode()===="+res.getStatusLine().getStatusCode());
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						if ("n".equals(message)) {
							out.println("<script>alert(\"您未扫描注册过工地二维码，不可进行投诉！\");</script>");
							return;
						}else if ("m".equals(message)) {
							out.println("<script>alert(\"您未在此工地扫描过二维码，不可进行投诉！\");</script>");
							return;
						}

						PrjAndPersonViewVo prjAndPersonViewVo = JsonUtil.jsonToBean(message, PrjAndPersonViewVo.class,
								"prjAndPersonViewVo");
						
						System.out.println("prjAndPersonViewVo.getPrjId()="+prjAndPersonViewVo.getPrjId());
						System.out.println("prjAndPersonViewVo.getName()="+prjAndPersonViewVo.getName());
						System.out.println("prjAndPersonViewVo.getPhone()="+prjAndPersonViewVo.getPhone());
						
						request.setAttribute("prjId", prjAndPersonViewVo.getPrjId());
						
						request.setAttribute("prjname", prjAndPersonViewVo.getPrjname());
						
						System.out.println("getBuildCorpName()="+prjAndPersonViewVo.getBuildCorpName());
						List UnitEngineeringlists = new ArrayList();
						List UnitEngineeringlist1 = new ArrayList();//劳务单位
						List UnitEngineeringlist2 = new ArrayList();//劳务单位联系人
						List UnitEngineeringlist3 = new ArrayList();//劳务单位联系电话
				    	 
					 	
					 	
					 	String[]singletonNameArrs = null ;//建设
					 	
					 	String [] laowuUnit = null;//劳务单位
					 	String [] laowuUnitName = null;//劳务单位联系人
					 	String [] laowuUnitTel = null;//劳务单位联系电话
				    	
				    	singletonNameArrs= prjAndPersonViewVo.getBuildCorpName().split(",");
				    	laowuUnit=prjAndPersonViewVo.getLwunit().split(",");
				    	laowuUnitName=prjAndPersonViewVo.getLwunitName().split(",");
				    	laowuUnitTel=prjAndPersonViewVo.getLwunitTel().split(",");
				    	if(singletonNameArrs.length>=1){
		    			for(int i =0;i<singletonNameArrs.length;i++){
		    				User user = new User();
		    				user.setName(singletonNameArrs[i].replaceAll(" ", ""));
		    				UnitEngineeringlists.add(user);//建设
		    			}
				    	}
			 
				    	if(laowuUnit.length>=1){
				    		for(int i =0;i<laowuUnit.length;i++){
				    			User user = new User();
				    			user.setName(laowuUnit[i].replaceAll(" ", ""));
				    			UnitEngineeringlist1.add(user);//建设
				    		}
				    	}
				    	
				    	if(laowuUnitName.length>=1){
				    		for(int i =0;i<laowuUnitName.length;i++){
				    			User user = new User();
				    			user.setName(laowuUnitName[i].replaceAll(" ", ""));
				    			UnitEngineeringlist2.add(user);//建设
				    		}
				    	}
				    	
				    	if(laowuUnitTel.length>=1){
				    		for(int i =0;i<laowuUnitTel.length;i++){
				    			User user = new User();
				    			user.setName(laowuUnitTel[i].replaceAll(" ", ""));
				    			UnitEngineeringlist3.add(user);//建设
				    		}
				    	}
				    	
				    	
				    	
				    	
				    	
				    	request.setAttribute("UnitEngineeringlists", UnitEngineeringlists);
				    	request.setAttribute("UnitEngineeringlist1", UnitEngineeringlist1);
				    	request.setAttribute("UnitEngineeringlist2", UnitEngineeringlist2);
				    	request.setAttribute("UnitEngineeringlist3", UnitEngineeringlist3);
						request.setAttribute("consCorpName", prjAndPersonViewVo.getConsCorpName());
						request.setAttribute("superCorpName", prjAndPersonViewVo.getSuperCorpName());
						request.setAttribute("buildCorpName", prjAndPersonViewVo.getBuildCorpName());
						request.setAttribute("name", prjAndPersonViewVo.getName());
						request.setAttribute("cardnum", prjAndPersonViewVo.getCardnum());
						request.setAttribute("phone", prjAndPersonViewVo.getPhone());
						request.setAttribute("personType", prjAndPersonViewVo.getPersonType());// 20200512
						request.setAttribute("dept_name", prjAndPersonViewVo.getDept_name());// 20200512
																								// fcl
						request.setAttribute("personOne", prjAndPersonViewVo.getPersonOne());// 20200512

						/* 20200511 txb begin */
						String readonlyOp = "";
					 
						
						// 判断工程是否有工地系统管理员20200323
						// 0 没有工程管理员 1 有工程管理员 2 该openid在别的工程担任管理员
//						if(prjAndPersonViewVo.getSysManagerop()==null){
							request.getRequestDispatcher("queryComplaints.jsp").forward(request, response);
					 
//						} else {
//							out.println("<script>alert(\"扫码失败，请重新扫码!\");</script>");
//						}

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime()
								+ " prjAndPersonViewVo所占内存==" + Test.Size(prjAndPersonViewVo.toString().length())
								+ "  访问wxlogin时间： " + (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));
						out.flush();
						out.close();
					} else {
						return;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}  else if (opflag.equals("queryById")) {
				String id =request.getParameter("id");
				id=Util.nullToString(id);
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", id);
				HttpClient client =getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryById.action", "post");
				HttpResponse res = client.execute(post);
				try {
					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						
						ProjectcheckVo	 projectcheckVo = JsonUtil.jsonToBean(message, ProjectcheckVo.class, "projectcheckVo");
						request.setAttribute("projectcheckVo",projectcheckVo);
						request.getRequestDispatcher("queryById.jsp").forward(request, response);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			else if (opflag.equals("updateQueryAdmin")) {// tc
															// 2020年4月6日15:39:19
				String code = request.getParameter("code");
				JSONObject wxUser = CoreService.getOpenid(code);
				String OpenId = wxUser.getString("openid");
				// 注释
				// String OpenId="omCLR1aXiOjhEbCxi1pd-pg1zk8c";
				Map<String, String> map = new HashMap<String, String>();
				map.put("opflag", "queryOpenId");
				map.put("OpenId", OpenId);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryProject.action", "post");
				HttpResponse res = client.execute(post);

				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");
				if (message.equals("n")) {
					request.setAttribute("massage", "n");
					request.getRequestDispatcher("tc_fild.jsp").forward(request, response);
				} else {

					List mapType = JSON.parseArray(message);
					JSONObject obj = null;
					List<JSON> list = new ArrayList<JSON>();
					for (Object object : mapType) {
						obj = JSONObject.parseObject(object.toString());
						list.add(obj);
					}
					request.setAttribute("oldOpenId", OpenId);
					request.setAttribute("list", list);
					request.getRequestDispatcher("tc_updateAdmin.jsp").forward(request, response);
				}
			} else if (opflag.equals("queryMyList")) {// tc 2020年4月7日15:39:19    我的账户信息
				String code = request.getParameter("code");
				JSONObject wxUser = CoreService.getOpenid(code);
				String OpenId = wxUser.getString("openid");

				Map<String, String> map = new HashMap<String, String>();
				map.put("opflag", "querySysManagerInfo");
				map.put("OpenId", OpenId);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryProject.action", "post");
				HttpResponse res = client.execute(post);

				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");

				if (message.equals("n")) {
					request.setAttribute("massage", "n");
					request.getRequestDispatcher("tc_fild.jsp").forward(request, response);

				} else {
					UserInfo userInfo = JsonUtil.jsonToBean(message, UserInfo.class, "userInfo");
					request.setAttribute("userInfo", userInfo);
					request.getRequestDispatcher("tc_queryMyList.jsp").forward(request, response);
				}
			} else if (opflag.equals("resetPass")) {// tc 2020年4月7日15:39:19
													// 初始化密码
				String code = request.getParameter("code");
				JSONObject wxUser = CoreService.getOpenid(code);
				String OpenId = wxUser.getString("openid");

				Map<String, String> map = new HashMap<String, String>();
				map.put("opflag", "InitPass");
				map.put("OpenId", OpenId);

				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryProject.action", "post");
				HttpResponse res = client.execute(post);

				HttpEntity entity = res.getEntity();
				String message = EntityUtils.toString(entity, "utf-8");
				request.setAttribute("massage", message);
				request.getRequestDispatcher("tc_fild.jsp").forward(request, response);
			} else if (opflag.equals("sysManagerView")) {
				long startTime = System.currentTimeMillis();
				try {
					String cardnum = request.getParameter("cardnum");
					cardnum = Util.nullToString(cardnum);
					String username = request.getParameter("username");
					username = Util.nullToString(username);

					Map<String, String> map = new HashMap<String, String>();
					map.put("prjId", prjId);
					map.put("cardnum", cardnum);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!sysManagerView.action",
							"post");
					HttpResponse res = client.execute(post);

					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						// // ////////System.out.println("message=="+message);
						SysManagerVo sysManagerVo = JsonUtil.jsonToBean(message, SysManagerVo.class, "sysManagerVo");

						sysManagerVo.setUsername(username);
						request.setAttribute("sysManagerVo", sysManagerVo);

						long endTime = System.currentTimeMillis(); // 获取结束时间
						log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " loginVo所占内存=="
								+ Test.Size(sysManagerVo.toString().length()) + "  访问updateRegisterPerson时间： "
								+ (endTime - startTime) + "ms。    request 大小："
								+ Test.Size(request.toString().length()));

					} else {
						// System.out.println("请求失败");
						return;
					}
					request.getRequestDispatcher("WEB-INF/jsp/registerManagerView.jsp").forward(request, response);
					out.flush();
					out.close();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			/*
			 * 20200511 txb 此方法经过查询，暂时没有用到 else
			 * if(opflag.equals("updateRegisterPersonPage")){ try { String
			 * prjName=request.getParameter("prjName");
			 * prjName=Util.nullToString(prjName); String
			 * cardnum=request.getParameter("cardnum");
			 * cardnum=Util.nullToString(cardnum); String
			 * tiwen=request.getParameter("tiwen");
			 * tiwen=Util.nullToString(tiwen);
			 * 
			 * String buildCorpName=request.getParameter("buildCorpName");
			 * buildCorpName=Util.nullToString(buildCorpName); String
			 * consCorpName=request.getParameter("consCorpName");
			 * consCorpName=Util.nullToString(consCorpName); String
			 * superCorpName=request.getParameter("superCorpName");
			 * superCorpName=Util.nullToString(superCorpName);
			 * 
			 * request.setAttribute("prjname", prjName);
			 * request.setAttribute("cardnum", cardnum);
			 * request.setAttribute("tiwen", tiwen);
			 * 
			 * request.setAttribute("buildCorpName", buildCorpName);
			 * request.setAttribute("consCorpName", consCorpName);
			 * request.setAttribute("superCorpName", superCorpName);
			 * 
			 * request.getRequestDispatcher("WEB-INF/jsp/registerPerson.jsp").
			 * forward(request, response); } catch (ParseException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } }
			 */
			else if (opflag.equals("queryexamine")) {//fcl 2020年6月8日13:33:11 查看项目名称信息
					prjId = request.getParameter("prjId");
					prjId = Util.nullToString(prjId);
//					 通过code获取用户openid
					String openid = (String) request.getSession().getAttribute("openid");
					Map<String, String> map = new HashMap<String, String>();
					map.put("prjId", prjId);
					map.put("openid", openid);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryexamine.action",
							"post");
					HttpResponse res = client.execute(post);

					if (res.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						
						@SuppressWarnings("unchecked")
						List<ProjectcheckVo> list = JsonUtil.jsonToBean(message, List.class,
								"list");
						request.setAttribute("list", list);
						////System.out.println("into  jsp");
						request.getRequestDispatcher("queryExamine.jsp").forward(request, response);

					}
				
				
			}else if (opflag.equals("queryTable")) {//fcl 2020年6月8日13:33:11 查看检查信息
			String 	pid = request.getParameter("pid");
				pid = Util.nullToString(pid);
				String openid = (String) request.getSession().getAttribute("openid");
				Map<String, String> map = new HashMap<String, String>();
				map.put("pid", pid);
				map.put("openid", openid);
				HttpClient client = getConnection();
				HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryTable.action",
						"post");
				HttpResponse res = client.execute(post);

				if (res.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = res.getEntity();
					String message = EntityUtils.toString(entity, "utf-8");
					@SuppressWarnings("unchecked")
					List<ProjectcheckVo> list = JsonUtil.jsonToBean(message, List.class,"list");
					request.setAttribute("list", list);
					request.setAttribute("pid", pid);
					request.getRequestDispatcher("queryTable.jsp").forward(request, response);
				}
		}
			else if (opflag.equals("wxregisterFinished")) {
				try {
					String tiwen = request.getParameter("tiwen");
					tiwen = Util.nullToString(tiwen);
					String prjName = request.getParameter("prjName");
					prjName = Util.nullToString(prjName);
					String cardnum = request.getParameter("cardnum");
					cardnum = Util.nullToString(cardnum);
					String corpname = request.getParameter("corpname");
					corpname = Util.nullToString(corpname);
					String personOne = request.getParameter("personOne");// 20200512   fcl
					personOne = Util.nullToString(personOne);
					String personType = request.getParameter("personType");// 20200512   fcl
					personType = Util.nullToString(personType);

					String state = request.getParameter("state");
					state = Util.nullToString(state);

					request.setAttribute("prjName", prjName);
					request.setAttribute("cardnum", cardnum);
					request.setAttribute("corpname", corpname);
					request.setAttribute("tiwen", tiwen);
					request.setAttribute("state", state);
					request.setAttribute("personType", personType);//20200512 fcl
					request.setAttribute("personOne", personOne);// 20200512  fcl
					request.getRequestDispatcher("WEB-INF/jsp/wxlogin.jsp").forward(request, response);

				} catch (ParseException e) {
					e.printStackTrace();
				}

			} else if (opflag.equals("passA")) {
				long startTime = System.currentTimeMillis();
				try {
					String prjName = request.getParameter("prjName");
					prjName = Util.nullToString(prjName);
					String name = request.getParameter("name");
					name = Util.nullToString(name);
					String tiwen = request.getParameter("tiwen");
					tiwen = Util.nullToString(tiwen);
					String corpname = request.getParameter("corpname");
					corpname = Util.nullToString(corpname);
					String personType = request.getParameter("personType");
					personType = Util.nullToString(personType);
					String cardnum = request.getParameter("cardnum");
					cardnum = Util.nullToString(cardnum);
					String phone = request.getParameter("phone");
					phone = Util.nullToString(phone);
					String personOne = request.getParameter("personOne");
					personOne = Util.nullToString(personOne);

					request.setAttribute("prjName", prjName);
					request.setAttribute("name", name);
					request.setAttribute("tiwen", tiwen);
					request.setAttribute("corpname", corpname);

					request.setAttribute("personType", personType);
					request.setAttribute("cardnum", cardnum);

					request.setAttribute("phone", phone);
					request.setAttribute("personOne", personOne);
					if (Float.valueOf(tiwen) >= 37.3)
						request.setAttribute("info", "禁止通过");
					else
						request.setAttribute("info", "可以通过");
					long endTime = System.currentTimeMillis(); // 获取结束时间
					log.info(request.getRemoteAddr() + "   " + CommonFunc.CurrentTime() + " " + prjName + "   " + name
							+ "   " + cardnum + "  " + "  访问时间： " + (endTime - startTime) + "ms。    request 大小："
							+ Test.Size(request.toString().length()));

					request.getRequestDispatcher("WEB-INF/jsp/passA.jsp").forward(request, response);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if(opflag.equals("queryRegcode")){
				 String code = request.getParameter("code");
//				 通过code获取用户openid
				 JSONObject wxUser = CoreService.getOpenid(code);
				 String openid = wxUser.getString("openid");
				 request.getSession().setAttribute("openid", openid);
				 Map<String, String> map = new HashMap<String, String>();
					map.put("openid", openid);
					HttpClient client = getConnection();
					HttpUriRequest post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryRegcode.action",
							"post");
					HttpResponse res = client.execute(post);

					if (res.getStatusLine().getStatusCode() == 200) {//fcl  20200608
						HttpEntity entity = res.getEntity();
						String message = EntityUtils.toString(entity, "utf-8");
						MessageVo state = JsonUtil.jsonToBean(message, MessageVo.class,
								"messageVo");
						if(state.getMessage().equals("0")){
							request.getRequestDispatcher("checkPeronRegister.jsp").forward(request, response);
						}else{
							prjId = request.getParameter("prjId");
							prjId = Util.nullToString(prjId);
//							 通过code获取用户openid
							map = new HashMap<String, String>();
							map.put("prjId", prjId);
							map.put("openid", openid);
							 client = getConnection();
							 post = getRequestMethod(map, "http://221.207.229.44:7777/wx!queryexamine.action",
									"post");
							 res = client.execute(post);

							if (res.getStatusLine().getStatusCode() == 200) {
								 entity = res.getEntity();
								 message = EntityUtils.toString(entity, "utf-8");
								 @SuppressWarnings("unchecked")
								List<ProjectcheckVo> list = JsonUtil.jsonToBean(message, List.class,"list");
								request.setAttribute("list", list);
								request.getRequestDispatcher("queryExamine.jsp").forward(request, response);

							}
						
						}
							out.print("{\"status\":\"" +state.getMessage() + "\"}");
							out.flush();
							out.close();
					}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (out != null) {
				out.close();
			}
		}
		/**
		 * 接收微信服务器发送请求时传递过来的4个参数
		 */
		/*
		 * String signature = request.getParameter("signature");//
		 * 微信加密签名signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 String
		 * timestamp = request.getParameter("timestamp");//时间戳 String nonce =
		 * request.getParameter("nonce");//随机数 String echostr =
		 * request.getParameter("echostr");//随机字符串 //
		 * ////////System.out.println("signature=="+signature); //
		 * ////////System.out.println("timestamp=="+timestamp); //
		 * ////////System.out.println("nonce=="+nonce); //
		 * ////////System.out.println("echostr=="+echostr); //排序 String sortString =
		 * sort(TOKEN, timestamp, nonce); //
		 * ////////System.out.println("sortString=="+sortString); //加密 String
		 * mySignature = sha1(sortString); //
		 * ////////System.out.println("mySignature=="+mySignature); //校验签名 if
		 * (mySignature != null && mySignature != "" &&
		 * mySignature.equals(signature)) { // //
		 * ////////System.out.println("签名校验通过。");
		 * //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
		 * //response.getWriter().println(echostr); //
		 * ////////System.out.println("=======请求校验成功======" + echostr);
		 * response.getWriter().write(echostr); } else { //
		 * ////////System.out.println("签名校验失败."); }
		 */

	}

	
	
	/**
	 * 最多上传的5个文件
	 */
	public File myFile_1;
	public File myFile_2;
	public File myFile_3;
	public File myFile_4;
	public File myFile_5;
	/**
	 * 最多上传的5个文件的名称
	 */
	public String myFile_1FileName;
	public String myFile_2FileName;
	public String myFile_3FileName;
	public String myFile_4FileName;
	public String myFile_5FileName;
	 

	
	public File getMyFile_1() {
		return myFile_1;
	}
	public void setMyFile_1(File myFile_1) {
		this.myFile_1 = myFile_1;
	}
	public File getMyFile_2() {
		return myFile_2;
	}
	public void setMyFile_2(File myFile_2) {
		this.myFile_2 = myFile_2;
	}
	public File getMyFile_3() {
		return myFile_3;
	}
	public void setMyFile_3(File myFile_3) {
		this.myFile_3 = myFile_3;
	}
	public File getMyFile_4() {
		return myFile_4;
	}
	public void setMyFile_4(File myFile_4) {
		this.myFile_4 = myFile_4;
	}
	public File getMyFile_5() {
		return myFile_5;
	}
	public void setMyFile_5(File myFile_5) {
		this.myFile_5 = myFile_5;
	}
	public String getMyFile_1FileName() {
		return myFile_1FileName;
	}
	public void setMyFile_1FileName(String myFile_1FileName) {
		this.myFile_1FileName = myFile_1FileName;
	}
	public String getMyFile_2FileName() {
		return myFile_2FileName;
	}
	public void setMyFile_2FileName(String myFile_2FileName) {
		this.myFile_2FileName = myFile_2FileName;
	}
	public String getMyFile_3FileName() {
		return myFile_3FileName;
	}
	public void setMyFile_3FileName(String myFile_3FileName) {
		this.myFile_3FileName = myFile_3FileName;
	}
	public String getMyFile_4FileName() {
		return myFile_4FileName;
	}
	public void setMyFile_4FileName(String myFile_4FileName) {
		this.myFile_4FileName = myFile_4FileName;
	}
	public String getMyFile_5FileName() {
		return myFile_5FileName;
	}
	public void setMyFile_5FileName(String myFile_5FileName) {
		this.myFile_5FileName = myFile_5FileName;
	}
	
	
	
	
	/**
	 * 
	 * describe:文件上传
	 * @return Map 
	 * @throws Exception
	 * 2013-12-9
	 * @author txb
	 * @param request 
	 * @param obj 保存每个文件时传的参数
	 */
	public Map fileUpload(String dirPath,int nameLength,int fileLength){
		Map map=new HashMap();
		try {
			System.out.println("111111111111"+myFile_1+"2222"+myFile_2+"333"+"myFile_3"+"myFile_4"+myFile_4);
			String temp="0";
			map.put("flag", "0"); //0:表示上传失败 1:表示成功
			map.put("message", "没有上传的文件！");
			////System.out.println(CommonFunc.getNowTime()+"  myFile_1FileName==="+myFile_1FileName);
			//2017-11-17yby修改
			//myFile_1FileName = CommonFunc.getGUID()+myFile_1FileName.substring(myFile_1FileName.lastIndexOf("."),myFile_1FileName.length());
			
			if (myFile_1 != null) {
				//2017-11-17yby修改
				myFile_1FileName = CommonFunc.getGUID()+myFile_1FileName.substring(myFile_1FileName.lastIndexOf("."),myFile_1FileName.length());
				temp="1";
				if(myFile_1FileName.length()>nameLength){
					map.put("message", myFile_1FileName+"文件名称过长！");
					return map;
				}else if (myFile_1.length() > fileLength){
					map.put("message", myFile_1FileName+"文件过大，最大为10M！");
					return map;
				}
					
			}
			if (myFile_2 != null) {
				//2017-11-17yby修改
				myFile_2FileName = CommonFunc.getGUID()+myFile_2FileName.substring(myFile_2FileName.lastIndexOf("."),myFile_2FileName.length());
				temp="1";
				if(myFile_2FileName.length()>nameLength){
					map.put("message", myFile_2FileName+"文件名称过长！");
					return map;
				}else if (myFile_2.length() > fileLength){
					map.put("message", myFile_2FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
			if (myFile_3 != null) {
				//2017-11-17yby修改
				myFile_3FileName = CommonFunc.getGUID()+myFile_3FileName.substring(myFile_3FileName.lastIndexOf("."),myFile_3FileName.length());
				temp="1";
				if(myFile_3FileName.length()>nameLength){
					map.put("message", myFile_3FileName+"文件名称过长！");
					return map;
				}else if (myFile_3.length() > fileLength){
					map.put("message", myFile_3FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
			if (myFile_4 != null) {
				//2017-11-17yby修改
				myFile_4FileName = CommonFunc.getGUID()+myFile_4FileName.substring(myFile_4FileName.lastIndexOf("."),myFile_4FileName.length());
				temp="1";
				if(myFile_4FileName.length()>nameLength){
					map.put("message", myFile_4FileName+"文件名称过长！");
					return map;
				}else if (myFile_4.length() > fileLength){
					map.put("message", myFile_4FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
			if (myFile_5 != null) {
				//2017-11-17yby修改
				myFile_5FileName = CommonFunc.getGUID()+myFile_5FileName.substring(myFile_5FileName.lastIndexOf("."),myFile_5FileName.length());
				temp="1";
				if(myFile_5FileName.length()>nameLength){
					map.put("message", myFile_5FileName+"文件名称过长！");
					return map;
				}else if (myFile_5.length() > fileLength){
					map.put("message", myFile_5FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
				
					List list = new ArrayList();
					if(myFile_1 != null){
						copyFile(myFile_1, myFile_1FileName,dirPath,list);
					}
					if(myFile_2 != null){
						copyFile(myFile_2, myFile_2FileName,dirPath,list);
					}
					if(myFile_3 != null){
						copyFile(myFile_3, myFile_3FileName,dirPath,list);
					}
					if(myFile_4 != null){
						copyFile(myFile_4, myFile_4FileName,dirPath,list);
					}
					if(myFile_5 != null){
						copyFile(myFile_5, myFile_5FileName,dirPath,list);
					}
					map.put("flag", "1"); 
					map.put("files", list);
					System.out.println("1111");
				} catch (Exception e) {
					System.out.println("Into  Error");
					map.put("flag", "0");
					map.put("message", "上传文件出错！");
					e.printStackTrace();
				}
			System.out.println("map="+map);
			return map;
	}
	/**
	 * 
	 * describe:文件保存
	 * @param myFile 文件
	 * @param myFileFileName 文件名称
	 * @param realpath 文件路径
	 * @param id 文件存储外键
	 * @author txb
	 * 2013-03-10 修改 txb 添加参数obj
	 * @throws Exception 
	 */
	public void copyFile(File myFile, String myFileFileName,String dirPath,List list)   {
			try {
				System.out.println("Into   ZIP");
				String name = myFileFileName;
				//20160808 myFile_1FileName的文件名称已经是有时间元素了
				//myFileFileName = System.currentTimeMillis() + "_" + myFileFileName; // 增加时间戳的文件名
				File savedir = new File(dirPath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}

				File savefile = new File(savedir, myFileFileName);
				FileUtils.copyFile(myFile, savefile);
				
				//zipWidthHeightImageFile(myFile,new File("D:\\sgqyxypjdownload\\"+myFileFileName),1500,2000,0.7f);
				Map map = new HashMap();
				map.put("realPath", dirPath+myFileFileName);
				map.put("name", name);
				list.add(map);
				System.out.println("end   ZIP");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * 排序方法
	 *
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * 将字符串进行sha1加密
	 *
	 * @param str
	 *            需要加密的字符串
	 * @return 加密后的内容
	 */
	public String sha1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}