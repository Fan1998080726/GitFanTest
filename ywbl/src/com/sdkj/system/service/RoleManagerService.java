package com.sdkj.system.service;

import java.util.List;

import com.sdkj.system.vo.Menus;
import com.sdkj.system.vo.Role;
import com.sdkj.util.context.Pagination;

public interface RoleManagerService {

	/**
	 * 查询角色列表
	 * @param user_code      角色名称
	 * @param pagination     分页类
	 * @return					
	 * @throws Exception
	 */
	List findRole(String user_code, Pagination pagination) throws Exception;

	/**
	 * 角色名称重复判断
	 * @param role_name    角色名称
	 * @return
	 * @throws Exception
	 */
	List queryforid(String role_name) throws Exception;

	/**
	 * 增加角色信息
	 * @param role     角色信息VO
	 * @throws Exception
	 */
	void add(Role role) throws Exception;

	
	/**
	 * 根据角色ID 查询角色信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role queryforrole(String id) throws Exception ;

	/**
	 * 修改角色信息
	 * @param role
	 * @throws Exception
	 */
	void edit(Role role)  throws Exception ;

	
	/**
	 * 删除角色信息
	 * @param ids
	 * @throws Exception
	 */
	void delete(String ids)  throws Exception ;

	/**
	 * 根据角色ID查询角色对应的菜单
	 * @param roleId
	 * @param str 
	 * @return
	 * @throws Exception
	 */
	List findRoleMenuList(String roleId) throws Exception;

	/**
	 * 保存角色对应菜单时  先删除以前对应菜单信息
	 * @param ids          菜单IDs
	 * @param role_id	        角色ID
	 * @throws Exception
	 */
	void insertOrDeleteRoleMenu(String ids, String role_id) throws Exception;

	/**
	 * 修改时 判断是否存在角色名称
	 * @param role_name
	 * @param role_id
	 * @return
	 * @throws Exception 
	 */
	List queryforid(String role_name, int role_id) throws Exception;

	List getRoleListByDept(String dept_id) throws Exception;

	void addRoleMenu(List<Menus> menuList,String roleId) throws Exception;

	Menus getUserRight(String url, int role_id) throws Exception;

}
