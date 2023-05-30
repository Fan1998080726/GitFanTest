package com.sdkj.system.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.system.service.UserManagerService;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;

@Service
public class UserManagerImpl implements UserManagerService {
	
	@Autowired
	private Dao dao;

	/**
	 * 查询用户信息列表  20190820
	 */
	public List findUser(String user_code, Pagination pagination,String dept_name) throws Exception {
		//////System.out.println("user_name="+user_name);
		String sql = "  select a.id,a.login_id,a.login_password,a.user_name," +
				" a.role_id,a.phone,a.email,a.address,a.memo,b.role_name,c.dept_name " +
				" from system_user_info a,system_role_info b,system_dept_info c " +
				" where a.role_id=b.role_id " +
				" and b.dept_id=c.dept_id  and a.user_name like '%"+user_code+"%' ";
		if(dept_name.equals("审批办"))
			sql = sql +" and a.role_id='105'";
		else if(dept_name.equals("设计处"))
			sql = sql +" and a.role_id='175'";
		//////System.out.println(sql);
		pagination.setTotalCount((Integer)dao.queryById("findUserListCount", sql));
		
		//sql = Util.getLimitString(sql, pagination);
		
		String str="";
		if(dept_name.equals("审批办"))
			str =" and a.role_id='105'";
		else if(dept_name.equals("设计处"))
			str = " and a.role_id='175'";
		
		 sql = "  select * from (select a.id,a.login_id,a.login_password,a.user_name," +
					" a.role_id,a.phone,a.email,a.address,a.memo,b.role_name,c.dept_name " +
					" from system_user_info a,system_role_info b,system_dept_info c " +
					" where a.role_id=b.role_id " +
					" and b.dept_id=c.dept_id  and a.user_name like '%"+user_code+"%' "+str+" order by a.id desc)  where  ROWNUM<=15 and id not in"
					+ "(select  id from (select a.id " +
					" from system_user_info a,system_role_info b,system_dept_info c " +
					" where a.role_id=b.role_id " +
					" and b.dept_id=c.dept_id  and a.user_name like '%"+user_code+"%' "+str+"   order by a.id desc) where  ROWNUM<="+((pagination.getCurrentPageNo()/15)*15)+") ";

//		System.out.println(sql);
			
		return dao.queryForList("findUser", sql);
	}
	
	
	/**
	 * 删除用户信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(String[] selectFlag) throws Exception {
		for(String id : selectFlag){
			dao.delete("deleteuser", id);
		}
		
	}
	
	/**
	 * 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void add(UserInfo userInfo) throws Exception{
		String sql  ="select max(to_number(t.id))+1 from system_user_info t";
		userInfo.setId((Integer)dao.queryById("queryUserMax", sql));
		userInfo.setSystime(CommonFunc.CurrentTime());
		dao.save("adduser", userInfo);
	}

	/**
	 * 增加用户信息
	 */
	public List queryforid(String login_id) throws Exception {
		
		String sql = "SELECT * from system_user_info where login_id = '"+login_id+"'";
	
		return dao.queryForList("queryforid", sql);
	}

	/**
	 * 根据用户ID 查询用户信息
	 */
	public UserInfo editpage(String id) throws Exception {
		
		//String sql = "select * from system_user_info where id = '"+id+"'";
		
		return (UserInfo) dao.queryById("queryuser", id);
	}
	/*查询密码*/
	public String queryPass(String user_id) throws Exception {
		String sql = "select login_password from system_user_info where id='"+user_id+"'";
		return (String) dao.queryById("queryPass", sql);
	}
	/**
	 * 根据用户ID 密码重置
	 */
	public void resetPass(String user_id,String newPass) throws Exception {
		
		String sql = "update system_user_info set login_password='"+newPass+"'  where id = '"+user_id+"'";
		
	  dao.update("resetPass", sql);
	}

	/**
	 * 更新用户信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public void edit(UserInfo userInfo) throws Exception {
		
		dao.update("edituser", userInfo);
	/*	String sql = "update tbprojectinfo set lrr='"+userInfo.getLogin_id()+"'  where id = '"+user_id+"'";
		
		  dao.update("resetPass", sql);*/
	}

	/**
	 * 根据用户登陆名查询列表   （ 防止重复登陆名）
	 */
	public List queryrole() throws Exception {
		
		String sql = " select * from system_role_info ";
		
		return dao.queryForList("queryrolelist", sql);
	}


	public List<UserInfo> querypassword() throws Exception {
		// TODO Auto-generated method stub
		return (List<UserInfo>) dao.queryForList("querypassword");
	}


	public void editpassword(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		dao.update("editpassword", userInfo);
	}
	/**
	 * 根据用户ID 查询用户信息20200304
	 */
	public UserInfo queryCreateUser(String id) throws Exception {
		
		//String sql = "select * from system_user_info where id = '"+id+"'";
		
		return (UserInfo) dao.queryById("queryCreateUser", id);
	}
	/**	/**20200405 txb begin
	 * 20200405 通过createuser（openid）查询uservo
	 */
	public UserInfo queryUserByOpenId(String id) throws Exception {
		
		//String sql = "select * from system_user_info where id = '"+id+"'";queryCreateUser
		
		return (UserInfo) dao.queryById("queryUserByOpenId", id);
	}
	/**
	 * 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void addUserBack(UserInfo userInfo) throws Exception{
		dao.save("adduser_back", userInfo);
	}
	/**
	 * 更新用户信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public void editBack(UserInfo userInfo) throws Exception {
		
		dao.update("edituser", userInfo);
	/*	String sql = "update tbprojectinfo set lrr='"+userInfo.getLogin_id()+"'  where id = '"+user_id+"'";
		
		  dao.update("resetPass", sql);*/
	}
	/**20200405 txb end
	 */

}
