package com.sdkj.system.action;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.system.service.IDeptService;
import com.sdkj.system.service.RoleManagerService;
import com.sdkj.system.service.UserManagerService;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.JiaMi;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;
import com.sdkj.util.mail.sendmail;

@Results( 
        value={ 
        		@Result(name="queryuser",type="dispatcher",location="/jsp/system/user/queryuser.jsp"),
        		@Result(name="addpage",type="dispatcher",location="/jsp/system/user/adduserpage.jsp"),
        		@Result(name="editpage",type="dispatcher",location="/jsp/system/user/edituserpage.jsp"),
        		@Result(name="requery",type="redirect",location="/usermanager!query.action"),
        		@Result(name="resetPass",type="dispatcher",location="/jsp/system/user/resetPass.jsp"),
        		@Result(name="success",type="dispatcher",location="project!goMainPage.action"),    //登陆成功返回主页
        		@Result(name="queryauditAAA",type="dispatcher",location="/jsp/system/adbit/queryauditTj.jsp"),
        		@Result(name="updateaudit",type="dispatcher",location="/jsp/system/adbit/updateaudit.jsp"),
        }   
)
@Action("usermanager")
public class UserManagerAction {

	
	
	@Autowired
	private UserManagerService managerService;
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private RoleManagerService roleService;
	
	Pagination pagination = new Pagination();
	
	private String[] selectFlag;
	
	private UserInfo userInfo;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	//角色list
	private List roleList;
 
	
	//struts2中response  request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	
 	private Pager pm;
 	
 	private String user_name;
 	private String displayName;//20170218
 	private String displayAddress;
 	private String displayEmail;
	
 	
 	
	public String getDisplayAddress() {
		return displayAddress;
	}

	public void setDisplayAddress(String displayAddress) {
		this.displayAddress = displayAddress;
	}

	public String getDisplayEmail() {
		return displayEmail;
	}

	public void setDisplayEmail(String displayEmail) {
		this.displayEmail = displayEmail;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * 查询用户信息列表
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception{
		////////System.out.println("into query......");
		try {
			Map session = ActionContext.getContext().getSession();
			
			String user_name = (String)session.get("username");
			String dept_name = (String)session.get("dept_name");//20190820
		String user_code=request.getParameter("user_code");
		user_code=Util.nullToString(user_code);
		String offset = request.getParameter("pm.offset");
		
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
		
		pagination.setPageSize(15);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_code", user_code);
		map.put("dept_name", dept_name);
		List list = managerService.findUser(user_code,pagination,dept_name);//20190820
		
		// 设置URL
		pagination.setUrl("usermanager!query.action");
		// 存储参数条件到分页
		pagination.setHiddenMap(map);

		// 所设置返回列表的记录数
		pagination.setSize(list.size());
		

		
		//pagination.setTotalCount(150);
		if(dept_name.equals("审批办")){
			displayName="建设单位全称";
			displayAddress="办公地址";
		 	displayEmail="Email";
		}else if(dept_name.equals("设计处")){
			displayName="勘察设计企业名称";
			displayAddress="办公地址";
		 	displayEmail="Email";
		}else if(dept_name.equals("总经理")){
			displayName="用户名称";
			displayAddress="职务";
		 	displayEmail="业务部门名称";
		}
		request.setAttribute("pm", pagination);
		request.setAttribute("list", list);
		request.setAttribute("user_code", user_code);
		request.setAttribute("dept_name", dept_name);
		request.setAttribute("displayName", displayName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		////////System.out.println("out query......");
		return "queryuser";
	}

	//删除
	public String delete() throws Exception{
		
		
		try {
			managerService.delete(selectFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "requery";
		
	}
	
	/**
	 * 添加页面
	 * @return
	 * @throws Exception
	 */
	public String addPage() throws Exception{
	
		/**
		 * 查询角色
		 */
		Map session = ActionContext.getContext().getSession();
		String user_name = (String)session.get("username");
		String dept_name = (String)session.get("dept_name");
		
		if(dept_name.equals("审批办")){
			displayName="建设单位全称";
			displayAddress="办公地址";
		 	displayEmail="Email";
		}else if(dept_name.equals("设计处")){
			displayName="勘察设计企业名称";
			displayAddress="办公地址";
		 	displayEmail="Email";
		}else if(dept_name.equals("总经理")){
			displayName="用户名称";
			displayAddress="职务";
		 	displayEmail="业务部门名称";
		}else if(dept_name.equals("燃气办")){
			displayName="单位名称";
			displayAddress="联系人";
		 	displayEmail="邮箱";
		}
		
		roleList = managerService.queryrole();
		List deptList=deptService.getDeptList();
		request.setAttribute("deptList", deptList);
		request.setAttribute("user_name", user_name);
		request.setAttribute("dept_name", dept_name);//20190820
		request.setAttribute("displayName", displayName);
		return "addpage";
		
	}
	
	/**
	 * 进入修改页面
	 * @return
	 * @throws Exception
	 */
	public String editPage() throws Exception{
		try {
			Map session = ActionContext.getContext().getSession();
			
			String user_name = (String)session.get("username");
			String dept_name = (String)session.get("dept_name");
			System.out.println("dept_name===="+dept_name);
			if(dept_name.equals("审批办")){
				displayName="建设单位全称";
				displayAddress="办公地址";
			 	displayEmail="Email";
			}else if(dept_name.equals("设计处")){
				displayName="勘察设计企业名称";
				displayAddress="办公地址";
			 	displayEmail="Email";
			}else if(dept_name.equals("总经理")){
				displayName="用户名称";
				displayAddress="职务";
			 	displayEmail="业务部门名称";
			 
			}else if(dept_name.equals("燃气办")){
				displayName="单位名称";
				displayAddress="联系人";
			 	displayEmail="邮箱";
			}
			String id = request.getParameter("id");
			userInfo = managerService.editpage(id);

			List deptList=deptService.getDeptList();
			request.setAttribute("deptList", deptList);
			List roleList=roleService.getRoleListByDept(userInfo.getDept_id()+"");
			request.setAttribute("roleList", roleList);
			request.setAttribute("user_name", user_name);
			request.setAttribute("dept_name", dept_name);//20190820
			request.setAttribute("displayName", displayName);
			/**
			 * 查询角色
			 */
			roleList = managerService.queryrole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editpage";
	}
	/**
	 * 查询密码
	 * @return
	 * @throws Exception
	 */
	@LogAnno(operateType="进入用户密码编辑页面")
	public String queryPass() throws Exception{
		//////////System.out.println("111");
		try {
			Map session = ActionContext.getContext().getSession();
			String user_id=String.valueOf((Integer)session.get("user_id"));
			String passWord = managerService.queryPass(user_id);
			String state = request.getParameter("state");
			request.setAttribute("state", state);
			request.setAttribute("passWord", passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//////////System.out.println("222");
		return "resetPass";
	}
	/**
	 * 密码重置
	 * @return
	 * @throws Exception
	 */
	@LogAnno(operateType="用户密码重置")
	public void resetPass() throws Exception{
		try {
			Map session = ActionContext.getContext().getSession();
			String user_id=String.valueOf((Integer)session.get("user_id"));
			String state = request.getParameter("state");
            	String newpassWord = request.getParameter("newpassWord");
    			newpassWord=Util.nullToString(newpassWord);
    			String MD5newpassWord = JiaMi.encrypt(newpassWord);
    			
    			managerService.resetPass(user_id, MD5newpassWord);
    			if(state.equals("1")){
    				response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");	
    			}else{
    				response.getWriter().print("{\"info\":\"y\",\"status\":\"n\"}");
    			}
    			   			
 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * 增加用户
	 * @throws Exception
	 */
	public void add() throws Exception{
		
		try {
			/**
			 * 登陆名称查询 防止重复
			 */
			Map session = ActionContext.getContext().getSession();
			//////////System.out.println("userInfo.getRole_id()="+userInfo.getRole_id());
			////////System.out.println("userInfo.getBuildCorpCode()========="+userInfo.getBuildCorpCode());
			String parameter = request.getParameter("userInfo.login_id");
			 System.out.println("parameter==="+parameter);
			
			System.out.println("userInfo.getLogin_id()1111111111="+userInfo);
			List userList=managerService.queryforid(userInfo.getLogin_id());
			//////////System.out.println("userList.size()="+userList.size());
			if(null==userList||0==userList.size()){
				userInfo.setCreateUser((String)session.get("username"));
			    String password = JiaMi.encrypt(userInfo.getLogin_password());
				userInfo.setLogin_id(userInfo.getLogin_id().toUpperCase());
				userInfo.setLogin_password(password);
				managerService.add(userInfo);
				response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");
			}else{
				response.getWriter().print("{\"info\":\"n\",\"status\":\"n\"}");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 修改用户
	 * @throws Exception
	 */
	public void edit() throws Exception{
		Map session = ActionContext.getContext().getSession();
		try {
			//给密码加密并修改密码
		/*	List<UserInfo> list = managerService.querypassword();
			for(int i = 0 ; i < list.size();i++){
				int id = list.get(i).getId();
				String str = JiaMi.encrypt(list.get(i).getLogin_password());
				UserInfo userInfo1 = new UserInfo();
				userInfo1.setId(id);
				userInfo1.setLogin_password(str);
				managerService.editpassword(userInfo1);
			}*/
			
			//userInfo.login_password
			String login_id = request.getParameter("userInfo.id");
			String oraclepassword = managerService.queryPass(login_id);	
			String login_password = request.getParameter("login_password");
            if(oraclepassword.equals(login_password)){
            	userInfo.setLogin_password(login_password);
            }else{			
            	userInfo.setLogin_password(JiaMi.encrypt(login_password));
            }
			
		
			managerService.edit(userInfo);
			userInfo.setLogin_id(userInfo.getLogin_id().toUpperCase());
			response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	
	
}
