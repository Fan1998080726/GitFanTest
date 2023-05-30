/**
 * 用户登陆服务接口，用于查询用户信息，包括用户基本信息、权限信息、对应菜单信息等
 * 
 */
package com.sdkj.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.system.service.LoginService;
import com.sdkj.system.vo.ExtTreeChildrenVo;
import com.sdkj.system.vo.ExtTreeVo;
import com.sdkj.system.vo.LogVo;
import com.sdkj.system.vo.Menus;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Util;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private Dao dao;

	/**
	 * 登陆
	 */
	public List<?> queryforlog(UserInfo user) throws Exception {
		return dao.queryForList("querylogin", user);
	}

	public void addLog(LogVo logtable) throws Exception {
		String sql ="insert into log(id,username,deptname,userip,doWhat,currentTime,logstate,methodname"
				+ " )	values('"+CommonFunc.getGUID()+"'" +
				",'"+logtable.getUsername()+"','"+logtable.getDeptname()+"','"+logtable.getUserip()+"',"
						+ "'"+logtable.getDoWhat()+"','"+CommonFunc.CurrentTime()+"',"
								+ "'"+logtable.getLogState()+"','"+logtable.getMethodName()+"')";
	dao.update("addProcheckLog",sql);
	}
		public void add(String username,String deptname,String userip,String doWhat) throws Exception {
		String sql ="insert into log(id,username,deptname,userip,doWhat,currentTime)values('"+CommonFunc.getGUID()+"'" +
				",'"+username+"','"+deptname+"','"+userip+"','"+doWhat+"','"+CommonFunc.CurrentTime()+"')";
		dao.update("addLog", sql);
		
		
		
		/*
		 * 日志管理20160502
		 */
		if(doWhat.equals("登录监管平台")){
		Map session = ActionContext.getContext().getSession();
		String dept_name = (String)session.get("dept_name");
		String user_name = (String)session.get("username");
		/*LogManagementVO logManagementVO = new LogManagementVO();
		logManagementVO.setLogManDepartmentName(dept_name);
			logManagementVO.setLogManUserName(user_name);
			Date date = new Date();
 

			
		       formater.applyPattern("yyyy-MM-dd HH:mm:ss");  
		       String  LogManOperationTime = formater.format(date);
			logManagementVO.setLogManOperationTime(LogManOperationTime);
			logManagementVO.setLogManOperationType(doWhat);
			logManagementVO.setLogManOperationContent("");//这里暂时用不到，没有增加项目或工程
			HttpServletRequest request=ServletActionContext.getRequest();
			String IP = request.getRemoteAddr();
			logManagementVO.setLogManOperationIP(IP);
			   date = new Date();
			   formater = new SimpleDateFormat();  
		       formater.applyPattern("yyyy-MM-dd HH:mm:ss");  
		       String  logManGenerateTime = formater.format(date);
			logManagementVO.setLogManGenerateTime(logManGenerateTime);
			dao.update("InsertlogManagementVO", logManagementVO);*/
		/*20180912
		 * if(dept_name.equals("第一组")||dept_name.equals("第二组")||dept_name.equals("第三组")||dept_name.equals("第四组"))
			dao.update("InsertlogManagementVO",LogManagementUtil.LogManagUtil(dept_name, user_name, "登录监管平台", ""));
		else if(dept_name.equals("市政站")&&user_name.equals("董俊国"))
			dao.update("InsertlogManagementVO",LogManagementUtil.LogManagUtil("第四组", user_name, "登录监管平台", ""));
		else if(dept_name.equals("监察大队")){
			if(user_name.equals("徐金龙")||user_name.equals("王海学")||user_name.equals("张宇宁"))
			dao.update("InsertlogManagementVO",LogManagementUtil.LogManagUtil("第三组", user_name, "登录监管平台", ""));
		}*/
		}
	}


	public void add(String username,String deptname,String userip,String doWhat,String userAgent) throws Exception {
		String sql ="insert into log(id,username,deptname,userip,doWhat,currentTime,userAgent)values('"+CommonFunc.getGUID()+"'" +
				",'"+username+"','"+deptname+"','"+userip+"','"+doWhat+"','"+CommonFunc.CurrentTime()+"','"+userAgent+"')";
		dao.update("addLog", sql);
		
			
		
		/*
		 * 日志管理20160502
		 */
		if(doWhat.equals("登录监管平台")){
		Map session = ActionContext.getContext().getSession();
		String dept_name = (String)session.get("dept_name");
		String user_name = (String)session.get("username");
		}
	}

	public void add(String username,String deptname,String userip,String doWhat,String userAgent,String state,String op,String opid) throws Exception {
		String sql ="insert into log(id,username,deptname,userip,doWhat,currentTime,userAgent,state,op,opid)values('"+CommonFunc.getGUID()+"'" +
				",'"+username+"','"+deptname+"','"+userip+"','"+doWhat+"','"+CommonFunc.CurrentTimeEn()+"','"+userAgent+"','"+state+"','"+op+"','"+opid+"')";
		dao.update("addLog", sql);
		
	}
	public void addlog(String TbBuilderLicenceManageId, String state, String AuditOpinion,String Person) throws Exception {
		String sql = " insert into examinationlog(id,TbBuilderLicenceManageId,STATE,EXAMINATIONDATE,AUDITOPINION,PERSON) " +
			         " values('"+CommonFunc.getGUID()+"','"+TbBuilderLicenceManageId+"','"+state+"','"+CommonFunc.CurrentTime()+"','"+AuditOpinion+"','"+Person+"') ";
//		20190412  dao.update("addExaminationlog",sql);
	}
	/*20190516 txb*/
	public void addBigCheckLog(String dept_name,String user_name, String logmanoperationtype, String logmanoperationcontent) throws Exception {
//		if(!dept_name.equals("建管处"))
//		dao.update("InsertlogManagementVO2019",LogManagementUtil.LogManagUtil(dept_name, user_name, logmanoperationtype, logmanoperationcontent));
	}
//20200511 fcl 日志
	public void adds(String username, String deptname, String userip, String doWhat,   String op) throws Exception {
		String sql ="insert into log(id,username,deptname,userip,doWhat,currentTime,op)values('"+CommonFunc.getGUID()+"'" +
				",'"+username+"','"+deptname+"','"+userip+"','"+doWhat+"','"+CommonFunc.CurrentTime()+"','"+op+"')";
		System.out.println("SQL==="+sql);
		dao.update("addLog", sql);
	}

	
	// 20200529 燃气日志
	public void gasaddlog(String id, String username, String deptname, String dowhat, String currenttime) throws Exception {
		String sql ="insert into log(id,username,deptname,doWhat,currentTime)values('"+id+"'" +
				",'"+username+"','"+deptname+"','"+dowhat+"','"+CommonFunc.CurrentTime()+"')";
				dao.update("gasaddlog",sql);
	}

	/***
	 * 超级密码  验证   Sd999888.
	 */
	public List<?> queryforAdminlog(UserInfo user) throws Exception {
		return dao.queryForList("queryforAdminlog", user);
	}
}
