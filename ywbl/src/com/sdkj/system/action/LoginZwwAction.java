package com.sdkj.system.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.system.service.LoginService;
import com.sdkj.system.service.UserManagerService;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.JiaMi;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Util;


@Results( 
        value={ 
        		@Result(name="success",type="redirect",location="project!goMainPage.action"),    //登陆成功返回主页
        		@Result(name="error",type="redirect",location="/login.jsp"),         //登陆失败返回登陆页	
        		@Result(name="outlog",type="redirect",location="login.jsp"),          //退出登陆返回登陆页
        		@Result(name="outlogcorp",type="redirect",location="loginbuildcorp.jsp"),          //退出登陆返回登陆页
        		@Result(name="outlogkcsj",type="redirect",location="loginkcsj.jsp"),          //退出登陆返回登陆页20190824
        		@Result(name="inlogin",type="dispatcher",location="/login.jsp"),

        }   
)
@Action("zww")
public class LoginZwwAction {

	/*20150414txb add
	 * Spring 2.5 引入了 @Autowired 注释，它可以对类成员变量、
	 * 方法及构造函数进行标注，完成自动装配的工作。
	 *  通过 @Autowired的使用来消除 set ，get方法。
	 *  20191125 create
	 */
	@Autowired
	private LoginService loginService;
	
	private UserInfo user;
	
	//返回登陆失败错误信息
	private String error;
	
	
	private List<?> list ;
	private String codeMsg;
	
	Map session = ActionContext.getContext().getSession();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session1 = request.getSession();
	@Autowired
	private UserManagerService managerService;
public String inlogin() {
		
		try {
			request.setAttribute("codeMsg22", "inlogin()");
			
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "success";
		
	}
	
	/**
	 * 登陆
	 * @return
	 * @throws Exception
	 */
	public String getRemortIP(HttpServletRequest request) {   
		  if (request.getHeader("x-forwarded-for") == null) {  
		   return request.getRemoteAddr();   
		  }   
		  return request.getHeader("x-forwarded-for");   
		 } 
	/**
     * 发起http请求并获取结果
     * @param requestUrl 请求地址
     */
    public static JSONObject getXpath(String requestUrl){
        String res="";
        JSONObject object = null;
        StringBuffer buffer = new StringBuffer();
        try{
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon= (HttpURLConnection)url.openConnection();
            if(200==urlCon.getResponseCode()){
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"utf-8");
                BufferedReader br = new BufferedReader(isr);

                String str = null;
                while((str = br.readLine())!=null){
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                object = JSONObject.fromObject(res);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return object;
    }
	public String loginZww() {
		

		try {
			 //System.out.println("loginloginloginloginloginloginloginlogin");
			// 20191112
			Map<String, String> jsonMap = new HashMap<String, String>();
			//////////// //System.out.println("--------------------------");
			String tokenID=request.getParameter("tokenID");
			tokenID=Util.nullToString(tokenID);
			//System.out.println("tokenID====="+tokenID);
			  if(!tokenID.equals("")){
			JSONObject json = null;
			json = getXpath("http://113.4.248.58:8083/am/oauth2/tokeninfo?access_token="+tokenID);
			  if(null!=json){
        	//System.out.println(json.toString());
        	JSONObject jsonUserInfo = JSONObject.fromObject(json.getString("userInfo"));
        	String idcardNum = jsonUserInfo.getString("idcardNum");
     	    //System.out.println("idcardNum:" + idcardNum);
     		String enterpriseName = jsonUserInfo.getString("enterpriseName");
     	    //System.out.println("enterpriseName:" + enterpriseName);
			
			String username=idcardNum.toUpperCase();
		
			String password="123456";
			//2017/07/05  密码加密
			String MD5passwroed = JiaMi.encrypt(password);
			String iWidth=request.getParameter("iWidth");
			String iHeight=request.getParameter("iHeight");
			/*////////// //System.out.println("username--------------------------"+username);
			////////// //System.out.println("password--------------------------"+password);
			////////// //System.out.println("code--------------------------"+code);
			*/
		
		
			user=new UserInfo();
			user.setLogin_id(username);
			user.setLogin_password(MD5passwroed);
			// //System.out.println("username==="+username);
			// //System.out.println("MD5passwroed==="+MD5passwroed);
			list = loginService.queryforlog(user);
			// //System.out.println("list.size()==="+list.size());
			if(list.size() > 0){
				
				session.clear();
				Map usermap = (Map) list.get(0);
				//能否取到数据，取决于数据库字段的名称，mysql的数据库字段是小写的oracle是大写的。
				String name = (String) usermap.get("USER_NAME");
				//////// //System.out.println("bbbbbbbbbbbbbbb"+name);
				String dept_name= (String) usermap.get("DEPT_NAME");
				//////// //System.out.println("dept_name="+dept_name);
				//////// //System.out.println("name="+name+"    dept_name="+dept_name+"    "+CommonFunc.CurrentTime());
				int role_id = Integer.parseInt(String.valueOf(usermap.get("role_id".toUpperCase())));
				//////// //System.out.println("role_id="+role_id);
				int dept_id = Integer.parseInt(String.valueOf(usermap.get("dept_id".toUpperCase())));
				//////// //System.out.println("dept_id="+dept_id);
				int user_id = Integer.parseInt(String.valueOf(usermap.get("id".toUpperCase())));
				//////// //System.out.println("user_id="+user_id);
				String child_dept_name = (String) usermap.get("EMAIL");
				                                           
				String createUser = ((String) usermap.get("CREATEUSER"))==null?"":(String) usermap.get("CREATEUSER");
				//////// //System.out.println("createUser="+createUser);
				/**
				 * 保存用户权限菜单列表
				 */
				//session.put("usermenu", usermenu);
				if(role_id==105)
					if(name==null)
						name="";
				session.put("username", username);
				session.put("user", name);
				session.put("dept_name", dept_name);
				session.put("role_id", role_id);
				session.put("dept_id", dept_id);
				session.put("user_id", user_id);
				session.put("child_dept_name", child_dept_name);
				session.put("createUser", createUser);
				session.put("password", password);//20170819 txb
				
				
//session 上下文
				  session1.setAttribute("username", username);
				  session1.setMaxInactiveInterval(6565);
				  ServletContext ContextA =request.getSession().getServletContext();
				  ContextA.setAttribute("session", request.getSession());
				 //20180912
				loginService.add(name,dept_name, CommonFunc.getRemortIP(request), "登录监管平台"+(iWidth+"*"+iHeight),request.getHeader("User-Agent"));
				//////// //System.out.println("successsuccesssuccesssuccess........");
				return "success";
			}else{
					
				  
				session.clear();
				 UserInfo userInfo = new  UserInfo();
					userInfo.setCreateUser("admin");
				    password = "123456";
					userInfo.setLogin_id(username);
					userInfo.setLogin_password(password);
					userInfo.setUser_name(enterpriseName);
					userInfo.setRole_id(105);
					userInfo.setPhone("");
					userInfo.setAddress("");
					userInfo.setMemo("");
					userInfo.setSystime(CommonFunc.CurrentTime());
					userInfo.setBuildCorpCode(username);
					userInfo.setEmail("");
					managerService.add(userInfo);
					response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");
				
				//////// //System.out.println("createUser="+createUser);
				/**
				 * 保存用户权限菜单列表
				 */
				//session.put("usermenu", usermenu);
				session.put("username", username);
				session.put("user", enterpriseName);
				session.put("dept_name", "企业用户组");
				session.put("role_id", 105);
				session.put("dept_id", 26);
				/*session.put("user_id", user_id);
				session.put("child_dept_name", child_dept_name);*/
				session.put("createUser", "admin");
				session.put("password", password);//20170819 txb
				
				
//session 上下文
				  session1.setAttribute("username", username);
				  session1.setMaxInactiveInterval(6565);
				  ServletContext ContextA =request.getSession().getServletContext();
				  ContextA.setAttribute("session", request.getSession());
				 //20180912
				loginService.add(enterpriseName,"企业用户组", CommonFunc.getRemortIP(request), "登录监管平台"+(iWidth+"*"+iHeight),request.getHeader("User-Agent"));
				//////// //System.out.println("successsuccesssuccesssuccess........");
				return "success";
			
			}
			  }else{
				  //System.out.println("end..........11111111");
				 response.sendRedirect("http://113.4.248.58:8083/am/oauth2/authorize?service=initService&response_type=code&client_id=zwfwzj&scope=all&client_secret=zwfwzj&redirect_uri=http://http://221.207.229.44:8880/rqsp//log!login.action");
			  }
			  }
			  else{
				  String code=request.getParameter("code");
				  code=Util.nullToString(code);
					//System.out.println("code====="+code);
					 if(!code.equals("")){
					JSONObject json = null;
					//
					json = getXpath("http://113.4.248.58:8083/am/oauth2/access_token?client_id=zwfw&client_secret=zwfw&scope=al&redirect_uri=http://http://221.207.229.44:8880/rqsp//log!login.action&code="+code);
					  if(null!=json){
		        	//System.out.println(json.toString());
		        	
		        	tokenID = json.getString("access_token");
		     	    //System.out.println("code==="+code+"。。。。tokenID===:" + tokenID);
		     	   json = null;
		     	   json = getXpath("http://113.4.248.58:8083/am/oauth2/tokeninfo?access_token="+tokenID);
		     	  if(null!=json){
		        	JSONObject jsonUserInfo = JSONObject.fromObject(json.getString("userInfo"));
		        	String idcardNum = jsonUserInfo.getString("idcardNum");
		     	    //System.out.println("idcardNum:" + idcardNum);
		     		String enterpriseName = jsonUserInfo.getString("enterpriseName");
		     	    //System.out.println("enterpriseName:" + enterpriseName);
					
					String username=idcardNum.toUpperCase();
				
					String password="123456";
					//2017/07/05  密码加密
					String MD5passwroed = JiaMi.encrypt(password);
					String iWidth=request.getParameter("iWidth");
					String iHeight=request.getParameter("iHeight");
					/*////////// //System.out.println("username--------------------------"+username);
					////////// //System.out.println("password--------------------------"+password);
					////////// //System.out.println("code--------------------------"+code);
					*/
				
				
					user=new UserInfo();
					user.setLogin_id(username);
					user.setLogin_password(MD5passwroed);
					// //System.out.println("username==="+username);
					// //System.out.println("MD5passwroed==="+MD5passwroed);
					list = loginService.queryforlog(user);
					// //System.out.println("list.size()==="+list.size());
					if(list.size() > 0){
						
						session.clear();
						Map usermap = (Map) list.get(0);
						//能否取到数据，取决于数据库字段的名称，mysql的数据库字段是小写的oracle是大写的。
						String name = (String) usermap.get("USER_NAME");
						//////// //System.out.println("bbbbbbbbbbbbbbb"+name);
						String dept_name= (String) usermap.get("DEPT_NAME");
						//////// //System.out.println("dept_name="+dept_name);
						//////// //System.out.println("name="+name+"    dept_name="+dept_name+"    "+CommonFunc.CurrentTime());
						int role_id = Integer.parseInt(String.valueOf(usermap.get("role_id".toUpperCase())));
						//////// //System.out.println("role_id="+role_id);
						int dept_id = Integer.parseInt(String.valueOf(usermap.get("dept_id".toUpperCase())));
						//////// //System.out.println("dept_id="+dept_id);
						int user_id = Integer.parseInt(String.valueOf(usermap.get("id".toUpperCase())));
						//////// //System.out.println("user_id="+user_id);
						String child_dept_name = (String) usermap.get("EMAIL");
						                                           
						String createUser = ((String) usermap.get("CREATEUSER"))==null?"":(String) usermap.get("CREATEUSER");
						//////// //System.out.println("createUser="+createUser);
						/**
						 * 保存用户权限菜单列表
						 */
						//session.put("usermenu", usermenu);
						if(role_id==105)
							if(name==null)
								name="";
						session.put("username", username);
						session.put("user", name);
						session.put("dept_name", dept_name);
						session.put("role_id", role_id);
						session.put("dept_id", dept_id);
						session.put("user_id", user_id);
						session.put("child_dept_name", child_dept_name);
						session.put("createUser", createUser);
						session.put("password", password);//20170819 txb
						
						
		//session 上下文
						  session1.setAttribute("username", username);
						  session1.setMaxInactiveInterval(6565);
						  ServletContext ContextA =request.getSession().getServletContext();
						  ContextA.setAttribute("session", request.getSession());
						 //20180912
						loginService.add(name,dept_name, CommonFunc.getRemortIP(request), "登录监管平台"+(iWidth+"*"+iHeight),request.getHeader("User-Agent"));
						//////// //System.out.println("successsuccesssuccesssuccess........");
						return "success";
					}else{
							
						  
						session.clear();
						 UserInfo userInfo = new  UserInfo();
							userInfo.setCreateUser("admin");
						    password = "123456";
							userInfo.setLogin_id(username);
							userInfo.setLogin_password(password);
							userInfo.setUser_name(enterpriseName);
							userInfo.setRole_id(105);
							userInfo.setPhone("");
							userInfo.setAddress("");
							userInfo.setMemo("");
							userInfo.setSystime(CommonFunc.CurrentTime());
							userInfo.setBuildCorpCode(username);
							userInfo.setEmail("");
							managerService.add(userInfo);
							response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");
						
						//////// //System.out.println("createUser="+createUser);
						/**
						 * 保存用户权限菜单列表
						 */
						//session.put("usermenu", usermenu);
						session.put("username", username);
						session.put("user", enterpriseName);
						session.put("dept_name", "企业用户组");
						session.put("role_id", 105);
						session.put("dept_id", 26);
						/*session.put("user_id", user_id);
						session.put("child_dept_name", child_dept_name);*/
						session.put("createUser", "admin");
						session.put("password", password);//20170819 txb
						
						
		//session 上下文
						  session1.setAttribute("username", username);
						  session1.setMaxInactiveInterval(6565);
						  ServletContext ContextA =request.getSession().getServletContext();
						  ContextA.setAttribute("session", request.getSession());
						 //20180912
						loginService.add(enterpriseName,"企业用户组", CommonFunc.getRemortIP(request), "登录监管平台"+(iWidth+"*"+iHeight),request.getHeader("User-Agent"));
						//////// //System.out.println("successsuccesssuccesssuccess........");
						return "success";
					
					}
					  }else{
						  //System.out.println("end..........3333");
						 response.sendRedirect("http://113.4.248.58:8083/am/oauth2/authorize?service=initService&response_type=code&client_id=zwfwzj&scope=all&client_secret=zwfwzj&redirect_uri=http://http://221.207.229.44:8880/rqsp//log!login.action");
					  }
					  }else{
						  //System.out.println("end..........6666666");
						 response.sendRedirect("http://113.4.248.58:8083/am/oauth2/authorize?service=initService&response_type=code&client_id=zwfwzj&scope=all&client_secret=zwfwzj&redirect_uri=http://http://221.207.229.44:8880/rqsp//log!login.action");
					  }
					 }else{
				  
				  //System.out.println("end..........55555");
				 response.sendRedirect("http://113.4.248.58:8083/am/oauth2/authorize?service=initService&response_type=code&client_id=zwfwzj&scope=all&client_secret=zwfwzj&redirect_uri=http://http://221.207.229.44:8880/rqsp//log!login.action");
					 }	
				  }
		}  catch (Exception e) {
			String username=request.getParameter("login_id");
			username=Util.nullToString(username);
			username=username.toUpperCase();
			String password=request.getParameter("login_password");
			// //System.out.println("username====="+username);
			// //System.out.println("password====="+password);
			e.printStackTrace();
		}
		//////// //System.out.println("2222222222");
		return null;
		
	
	}

	public static void printStr(String jsonStr) throws IOException{
		HttpServletResponse  httpServletResponse  = ServletActionContext.getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/x-json;charset=UTF-8");
		PrintWriter pw = httpServletResponse.getWriter();
		pw.print(jsonStr);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 生成验证码
	 * 
	 * @return
	 * @throws IOException
	 */
	/**此处的request，response根据你的具体项目情况获取
	 */
	public String imageCode() throws IOException {
		String key = "_checkCode";
		//request.getSession().removeAttribute(key);
		/*request.setAttribute("codeMsg22", "1111111111111111111");
		*/
		// 在内存中创建图象
		int width = 65, height = 25;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(230, 255));
		g.fillRect(0, 0, 100, 25);
		// 设定字体
		g.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18));
		// 产生0条干扰线，
		g.drawLine(0, 0, 0, 0);
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(getRandColor(100, 150));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 15 * i + 6, 16);
		}
		  for(int i=0;i<(random.nextInt(5)+5);i++){  
		        g.setColor(new Color(random.nextInt(255)+1,random.nextInt(255)+1,random.nextInt(255)+1));  
		        g.drawLine(random.nextInt(100),random.nextInt(30),random.nextInt(100),random.nextInt(30));  
	    }   
		
		// 将验证码存入页面KEY值的SESSION里面
		request.getSession().setAttribute(key, sRand);
	/*	request.setAttribute("test", "2222222222222222222222222222222222222");
		*/
		this.codeMsg=sRand;
		response.setContentType("image/jpeg");
		// 图象生效
		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", responseOutputStream);
		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
		// 获得页面key值
		
		return null;
	}

	
	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	/**
	 * 退出登陆  清空session
	 * @return
	 * @throws Exception
	 */
	public String outlog() throws Exception{
		String returnstr="";
		System.out.println("into  corpLog");
		if(!String.valueOf(session.get("dept_name")).equals("企业用户组")&&!String.valueOf(session.get("dept_name")).equals("勘察设计企业")){
			returnstr="outlog";
		}else if(String.valueOf(session.get("dept_name")).equals("勘察设计企业")){
			returnstr="outlogkcsj";
		}
		else{
			returnstr="outlogcorp";
		}
		session.clear();
		return returnstr;
	}

	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	public String getCodeMsg() {
		return codeMsg;
	}
	public void setCodeMsg(String codeMsg) {
		this.codeMsg = codeMsg;
	}
	
	
}
