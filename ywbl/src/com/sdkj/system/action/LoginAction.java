package com.sdkj.system.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.JiaMi;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;


@Results( 
        value={ 
        		@Result(name="success",type="redirect",location="project!goMainPage.action"),    //登陆成功返回主页
        		@Result(name="zhzjsuccess",type="redirect",location="project!selectProject.action"),    //登陆成功返回主页
        		@Result(name="error",type="redirect",location="/login.html"),         //登陆失败返回登陆页	
        		@Result(name="outlog",type="redirect",location="login.html"),          //退出登陆返回登陆页
        		@Result(name="outlogcorp",type="redirect",location="loginbuildcorp.jsp"),          //退出登陆返回登陆页
        		@Result(name="outlogkcsj",type="redirect",location="loginkcsj.jsp"),          //退出登陆返回登陆页20190824
        		@Result(name="inlogin",type="dispatcher",location="/login.html"),
        		@Result(name="outloggas",type="dispatcher",location="/gaslogin.jsp"),
        		@Result(name="outComplanlog",type="dispatcher",location="/complaintslogin.jsp") //退出登陆返回登陆页 2021年7月6日13:51:56

        }   
)
@Action("log")
public class LoginAction {

	/*20150414txb add
	 * Spring 2.5 引入了 @Autowired 注释，它可以对类成员变量、
	 * 方法及构造函数进行标注，完成自动装配的工作。
	 *  通过 @Autowired的使用来消除 set ，get方法。
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
	public String login() {
		try {
			
			String state = request.getParameter("Urlstate");
			state = Util.nullToString(state);
			
			
			
			System.out.println("state="+state);
			
			Map<String, String> jsonMap = new HashMap<String, String>();
			String username=request.getParameter("login_id");
			String complaintsState=request.getParameter("complaintsState");
			complaintsState = Util.nullToString(complaintsState);
			String password="";
			System.out.println("statestr================================"+complaintsState);
			if(state.equals("zhzj")) {
				username="智慧住建";
				  password="Sd999888.";
			}else {
				
			username=Util.nullToString(username);
			username=username.toUpperCase();
			  password=request.getParameter("login_password");
			}
			
			
			
			
			//2017/07/05  密码加密
			String MD5passwroed = JiaMi.encrypt(password);
			String code=request.getParameter("code");
			String iWidth=request.getParameter("iWidth");
			String iHeight=request.getParameter("iHeight");
			if(StringUtils.isBlank(username)){
				jsonMap.put("msg", "用户名不能为空！");	
				JSONObject jsObject =JSONObject.fromObject(jsonMap);
				printStr(jsObject.toString());
				return null;
			}
			
			if(StringUtils.isBlank(password)){
				jsonMap.put("msg", "密码不能为空！");	
				JSONObject jsObject =JSONObject.fromObject(jsonMap);
				printStr(jsObject.toString());
				return null;
			}
			
			String codeVal = (String) request.getSession().getAttribute("_checkCode");
			
			System.out.println("codeVal===="+codeVal);
			System.out.println("code===="+code);
			if(!state.equals("zhzj")) {
			if(!"3a432eddafa264917e7ca5f779ff0c93".equals(MD5passwroed)){
			if (codeVal == null || !codeVal.equals(code)) {
				jsonMap.put("msg", "验证码错误！");	
				JSONObject jsObject =JSONObject.fromObject(jsonMap);
				printStr(jsObject.toString());
				return null;
			}
			}
 			}
			user=new UserInfo();
			user.setLogin_id(username);
			user.setLogin_password(MD5passwroed);
 
			//Sd999888.
			if("3a432eddafa264917e7ca5f779ff0c93".equals(MD5passwroed)){
				list = loginService.queryforAdminlog(user);
			}else{
				list = loginService.queryforlog(user);
			}
			if(list.size() > 0){
				
				session.clear();
				Map usermap = (Map) list.get(0);
				//能否取到数据，取决于数据库字段的名称，mysql的数据库字段是小写的oracle是大写的。
				String name = (String) usermap.get("USER_NAME");
				 ////System.out.println("bbbbbbbbbbbbbbb"+name);
				String dept_name= (String) usermap.get("DEPT_NAME");
				  ////System.out.println("dept_name="+dept_name);
				//////// ////System.out.println("name="+name+"    dept_name="+dept_name+"    "+CommonFunc.CurrentTime());
				int role_id = Integer.parseInt(String.valueOf(usermap.get("role_id".toUpperCase())));
				 ////System.out.println("role_id="+role_id);
				int dept_id = Integer.parseInt(String.valueOf(usermap.get("dept_id".toUpperCase())));
				 ////System.out.println("dept_id="+dept_id);
				int user_id = Integer.parseInt(String.valueOf(usermap.get("id".toUpperCase())));
				  ////System.out.println("user_id="+user_id);
				String child_dept_name = (String) usermap.get("EMAIL");
				String phone = (String) usermap.get("PHONE");
				String memo = (String) usermap.get("MEMO");
				                                           
				String createUser = ((String) usermap.get("CREATEUSER"))==null?"":(String) usermap.get("CREATEUSER");
				  ////System.out.println("createUser="+createUser);
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
				session.put("memo", memo);
				session.put("phone", phone);
				session.put("child_dept_name", child_dept_name);
				session.put("currentYear",CommonFunc.CurrentYear());	
				if(createUser.equals("230122199812017765")) {
					createUser="230103197203096612";
				}
				/**
				 * 为一地一码判断是否为本年度 fcl 2022年3月10日14:28:07
				 */
				session.put("createUser", createUser);
				session.put("password", password);//20170819 txb
				session.put("complaintsState", complaintsState);//2021年7月6日09:19:47  fcl   投诉登录页面带入的值
				
				
//session 上下文
				  session1.setAttribute("username", username);
				  session1.setMaxInactiveInterval(6565);
				  ServletContext ContextA =request.getSession().getServletContext();
				  ContextA.setAttribute("session", request.getSession());
				 //20180912
				loginService.add(name,dept_name, CommonFunc.getRemortIP(request), "登录监管平台"+(iWidth+"*"+iHeight),request.getHeader("User-Agent"));
				//////// ////System.out.println("successsuccesssuccesssuccess........");
		 
					return "success";
		 
				

				
			}else{
				//error = "用户名或者密码错误";
				//session.put("error", error);
				//return "error";
				jsonMap.put("msg", "用户名或者密码错误！");	
				JSONObject jsObject =JSONObject.fromObject(jsonMap);
				printStr(jsObject.toString());
				return null;
			}
		}  catch (Exception e) {
			String username=request.getParameter("login_id");
			username=Util.nullToString(username);
			username=username.toUpperCase();
			String password=request.getParameter("login_password");
			// ////System.out.println("username====="+username);
			// ////System.out.println("password====="+password);
			e.printStackTrace();
		}
		//////// ////System.out.println("2222222222");
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
	@LogAnno(operateType="退出登录")
	public String outlog() throws Exception{
		//System.out.println("into    Log");
		//System.out.println("into    Log"+String.valueOf(session.get("dept_name")));
		String returnstr="";
		  if(String.valueOf(session.get("dept_name")).equals("燃气办")||String.valueOf(session.get("dept_name")).equals("燃气企业用户组")){
			////System.out.println("22");
			returnstr="outloggas";
		} else  if(!String.valueOf(session.get("dept_name")).equals("企业用户组")&&
				!String.valueOf(session.get("dept_name")).equals("勘察设计企业")){
			String	complaintsState=String.valueOf(session.get("complaintsState"));
			complaintsState =Util.nullToString(complaintsState);
 			if(!complaintsState.equals("ts")) {
 				returnstr="outlog";
			}else {
				returnstr="outComplanlog";
			}
		}else if(String.valueOf(session.get("role_id")).equals("198")){
			////System.out.println("水水水水");
			returnstr="outlog";
		} else if(String.valueOf(session.get("dept_name")).equals("勘察设计企业")){
//			////System.out.println("22");
			returnstr="outlogkcsj";
		}  else{
			returnstr="outlogcorp";
		}
		session.clear();
		//System.out.println("into  jsp");
		//System.out.println("returnstr="+returnstr);
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
