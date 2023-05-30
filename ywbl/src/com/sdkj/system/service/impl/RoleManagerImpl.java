package com.sdkj.system.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.system.service.RoleManagerService;
import com.sdkj.system.vo.Menus;
import com.sdkj.system.vo.Role;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;

@Service
public class RoleManagerImpl implements  RoleManagerService {

	@Autowired
	private Dao dao;
	
	/**
	 *  查询角色列表
	 */
	public List findRole(String user_code, Pagination pagination) throws Exception {
		
		
		String sql = "  SELECT role_id,role_name,memo,(select dept_name from system_dept_info t " +
				"  where t.dept_id=a.dept_id) dept_name FROM system_role_info a WHERE role_name like '%"+user_code+"%' ";
		
		pagination.setTotalCount((Integer)dao.queryById("findRoleListCount", sql));
		
		//sql = Util.getLimitString(sql, pagination);
		
		sql = "  SELECT role_id,role_name,memo,(select dept_name from system_dept_info t " +
				"  where t.dept_id=a.dept_id) dept_name FROM system_role_info a WHERE role_name like '%"+user_code+"%' and ROWNUM<=15 and role_id not in(SELECT role_id FROM system_role_info  where  ROWNUM<="+((pagination.getCurrentPageNo()/15)*15)+")";
		
	/*	sql = "  select dept_id,dept_name,dept_describe,(SELECT user_name FROM system_user_info WHERE id = dept_app_user	) dept_app_name,dept_app_user " +
				" from system_dept_info where dept_name LIKE '%"+deptName+"%' and ROWNUM<=15 and dept_id not in (select dept_id from system_dept_info  where  ROWNUM<="+((pagination.getCurrentPageNo()/15)*15)+")";
		*/
		return dao.queryForList("findRole", sql);
		
	}

	/**
	 *  角色名称查询角色列表
	 */
	public List queryforid(String role_name) throws Exception {
		
		String sql = "SELECT * from system_role_info where role_name = '"+role_name+"'";
		
		return dao.queryForList("queryroleid", sql);
	}

	
	/**
	 *  增加角色信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public void add(Role role) throws Exception {
		
		String sql  ="select max(to_number(t.role_id))+1 from system_role_info t";
		role.setRole_id((Integer)dao.queryById("querymaxroleid", sql) );
		
		dao.save("addrole", role);
	}


	/**
	 * 根据角色ID 查询角色信息
	 */
	public Role queryforrole(String id) throws Exception {
		//////////System.out.println("queryforrole(String id)");
		return (Role) dao.queryById("queryrole", id);
	}

	/**
	 * 更新角色信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public void edit(Role role) throws Exception {
		
		dao.update("updaterole", role);
		
	}

	/**
	 * 删除角色信息
	 * 同时删除角色对应的菜单
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(String ids) throws Exception {

		String[] temp=ids.split(",");
		for(int i=0;i<temp.length;i++){
			if(null!=temp[i]&&!"".equals(temp[i])){
				String role_id=temp[i];
				int num=(Integer)dao.queryById("getUserListByRole",role_id);
				if(0!=num){//部门下有角色信息
					throw new RuntimeException();
				}else{//直接删除部门
					dao.delete("deleteRoleMenuByRoleId", role_id);
					dao.delete("deleterole", role_id);
				}
			}
			
		}
	}

	/**
	 * 根据角色信息查询菜单列表
	 */
	public List findRoleMenuList(String roleId) throws Exception {
//		Map map=new HashMap();
//		map.put("roleId", roleId);
//		map.put("pid", pid);
//		List<Menus> list =  (List<Menus>) dao.queryForList("findRoleMenuList",map);
//		return list;
		//System.out.println("AfindRoleMenuList="+roleId);
		List<Menus> list =  null;
		try{
		 list =  (List<Menus>) dao.queryForList("findRoleMenuList",roleId);
		 System.out.println(list.size());
		}catch(Exception ex){
			//////////System.out.println("1111111111111111111111");
			ex.printStackTrace();
		}
		//////////System.out.println("BfindRoleMenuList="+roleId);
		return list;
	}

	
	/**
	 * 保存角色对应菜单时  先删除以前对应菜单信息 在添加新角色菜单
	 */
	@Transactional(rollbackFor=Exception.class)
	public void insertOrDeleteRoleMenu(String ids, String role_id) throws Exception{
				// 先删除ROLE_ID下的权限菜单
				dao.delete("deleteRoleMenuByRoleId", role_id);
				// 如果IDS为空（没有选择任务菜单）,直接返回
				if("".equals(ids)){
					return;
				}
				// 再将选择的菜单插入到对应权限
				String[] menu_ids = ids.split("#");
				for(String menu_id : menu_ids){
					Map roleMap = new HashMap();
					roleMap.put("role_id", role_id);
					roleMap.put("menu_id", menu_id);
					//////////System.out.println("151...");
					dao.save("inserRoleMenus",roleMap);
				}
		
	}

	public List queryforid(String role_name, int role_id) throws Exception {
		String sql = "SELECT * from system_role_info where role_name = '"+role_name+"' and role_id != "+role_id;
		
		return dao.queryForList("queryroleid", sql);
	}

	/**
	 * 用户添加修改页调用获取角色列表方法
	 * 20140505
	 * txb
	 */
	public List getRoleListByDept(String dept_id) throws Exception {
		return dao.queryForList("getRoleListByDept",dept_id);
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	public void addRoleMenu(List<Menus> menuList,String roleId) throws Exception {
		////////System.out.println("addRoleMenu(List<Menus> menuList,String roleId)");
		// 先删除ROLE_ID下的权限菜单
		dao.delete("deleteRoleMenuByRoleId", roleId);
		////////System.out.println("存放一级菜单");
		Map pmap=new HashMap();//存放一级菜单
		for(int i=0;i<menuList.size();i++){
			//
			Menus menu=menuList.get(i);
			//
			if(null!=menu&&null!=menu.getRole_id()&&!"".equals(menu.getRole_id())){
				////////System.out.println("bbbbbb");
				////////System.out.println("menu.getMenu_id()==="+menu.getMenu_id());
				Map<String, String> roleMap = new HashMap();
				////////System.out.println(roleId);
				////////System.out.println(menu.getMenu_id());
				////////System.out.println(menu.getRm_add());
				////////System.out.println(menu.getRm_update());
				////////System.out.println(menu.getRm_del());
				roleMap.put("role_id", roleId);
				roleMap.put("menu_id", menu.getMenu_id());
				roleMap.put("rm_add", menu.getRm_add()==null?"0":menu.getRm_add());
				roleMap.put("rm_update", menu.getRm_update()==null?"0":menu.getRm_update());
				roleMap.put("rm_del", menu.getRm_del()==null?"0":menu.getRm_del());
				////////System.out.println("190...");
				dao.save("inserRoleMenus",roleMap);
				/*20160329针对审批办*/
				if(!menu.getMenu_id().contains("10051"))
				pmap.put(menu.getMenu_id().substring(0,4), 1);
				else
					pmap.put(menu.getMenu_id().substring(0,5), 1);
				////////System.out.println("ccccccccccc");
			}
		}
		////////System.out.println("2222222222222");
		Iterator<Map.Entry<Integer, String>> iterator = pmap.entrySet().iterator();  
        while (iterator.hasNext()) {  
            Map.Entry<Integer, String> entry = iterator.next();  
              
            Map roleMap = new HashMap();
			roleMap.put("role_id", roleId);
			roleMap.put("menu_id",entry.getKey()+"");
			roleMap.put("rm_add", "");
			roleMap.put("rm_update", "");
			roleMap.put("rm_del", "");
			////////System.out.println("205...");
			dao.save("inserRoleMenus",roleMap);
        }
	}

	public Menus getUserRight(String url, int role_id) throws Exception {
		Map map=new HashMap();
		map.put("url", url);
		map.put("role_id", role_id);
		//System.out.println("url="+url);
		//System.out.println("role_id="+role_id);
		return (Menus)dao.queryById("getUserRight", map);
	}


}
