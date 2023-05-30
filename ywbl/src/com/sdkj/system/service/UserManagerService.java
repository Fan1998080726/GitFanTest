package com.sdkj.system.service;

import java.util.List;

import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.context.Pagination;


public interface UserManagerService {

	/**
	 * 查询用户信息列表
	 * @param user_code     查询用户姓名
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	List findUser(String user_code, Pagination pagination,String user_name) throws Exception ;

	/**
	 * 删除用户信息
	 * @param selectFlag   用户信息IDs
	 * @throws Exception
	 */
	void delete(String[] selectFlag)  throws Exception ;

	/**
	 * 增加用户信息
	 * @param userInfo    用户信息vo
	 * @throws Exception
	 */
	void add(UserInfo userInfo) throws Exception ;

	/**
	 * 根据用户登陆名查询列表   （ 防止重复登陆名）
	 * @param login_id
	 * @return
	 * @throws Exception
	 */
	List queryforid(String login_id) throws Exception ;

	/**
	 * 根据用户ID 查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserInfo editpage(String id) throws Exception ;
	/*查询密码*/
	String queryPass(String user_id) throws Exception ;
	/*密码重置*/
	void resetPass(String user_id,String newPass) throws Exception ;

	/**
	 * 更新用户信息
	 * @param userInfo
	 * @throws Exception
	 */
	void edit(UserInfo userInfo) throws Exception ;

	/**
	 * 查询角色信息列表
	 * @return
	 * @throws Exception
	 */
	List queryrole() throws Exception ;//20190820 update add roleName 
	//查询密码
	List<UserInfo> querypassword() throws Exception ;
    //把密码加密修改
	void editpassword(UserInfo userInfo) throws Exception;
	
	
	UserInfo queryCreateUser(String id) throws Exception ;
	/**20200405 txb begin
	 *  通过createuser（openid）查询uservo  
	 */
	public UserInfo queryUserByOpenId(String openid) throws Exception ;
	
	void addUserBack(UserInfo userInfo) throws Exception ;
	void editBack(UserInfo userInfo) throws Exception ;
	/**20200405 txb end
	 */
}
