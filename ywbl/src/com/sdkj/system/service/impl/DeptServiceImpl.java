package com.sdkj.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.system.service.IDeptService;
import com.sdkj.system.vo.DeptVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
@Service
public class DeptServiceImpl implements IDeptService{

	@Autowired
	private Dao dao;
	
	/**
	 * 部门管理页
	 * 20150512
	 * txb
	 */
	public List queryDept(String deptName, Pagination pagination) throws Exception {
		String sql = "  select dept_id,dept_name,dept_describe,(SELECT user_name FROM system_user_info WHERE id = dept_app_user	) dept_app_name,dept_app_user " +
				" from system_dept_info where dept_name LIKE '%"+deptName+"%' ";
		pagination.setTotalCount((Integer)dao.queryById("findDeptListCount", sql));
		
		sql = "  select dept_id,dept_name,dept_describe,(SELECT user_name FROM system_user_info WHERE id = dept_app_user	) dept_app_name,dept_app_user " +
				" from system_dept_info where dept_name LIKE '%"+deptName+"%' and ROWNUM<=15 and dept_id not in (select dept_id from system_dept_info  where dept_name LIKE '%"+deptName+"%' and  ROWNUM<="+((pagination.getCurrentPageNo()/15)*15)+")";
		
		return dao.queryForList("findDept", sql);
		
	}

	/**
	 * 部门添加修改功能
	 * 20150512
	 * txb
	 */
	public void updateDept(DeptVo deptVo) throws Exception {
		if(0==deptVo.getDept_id()){//添加功能
			String sql  ="select max(to_number(t.dept_id))+1 from system_dept_info t";
			deptVo.setDept_id((Integer)dao.queryById("querydeptMax", sql));
			deptVo.setDept_app_user(Util.nullToString(deptVo.getDept_app_user()));
			dao.update("addDept", deptVo);
		}else{//修改功能
			dao.update("updateDept", deptVo);
		}
	}

	/**
	 * 获取部门详情
	 * 20140505
	 * txb
	 */
	public DeptVo getDeptById(long dept_id) throws Exception {
		return (DeptVo)dao.queryById("getDeptById",dept_id);
	}

	/**
	 * 查询部门下用户
	 * 20140505
	 * txb
	 */
	public List selectUserByDept(String dept_id, Pagination pagination)
			throws Exception {
		String sql = "  select a.id,a.login_id,a.login_password,a.user_name,a.role_id,a.phone,a.email,a.address,a.memo,b.role_name,c.dept_name " +
				" from system_user_info a,system_role_info b,system_dept_info c" +
				" where a.role_id=b.role_id" +
				" and b.dept_id=c.dept_id and c.dept_id="+dept_id;
		
		
		
		pagination.setTotalCount((Integer)dao.queryById("selectUserByDeptCount", sql));
		
		//sql = Util.getLimitString(sql, pagination);
		
		return dao.queryForList("selectUserByDept", sql);
	}

	/**
	 * 修改部门审批员
	 * 20140505
	 * txb
	 */
	public void updateAppUser(String userId,String deptId) throws Exception {
		Map map=new HashMap();
		map.put("userId", userId);
		map.put("deptId", deptId);
		dao.update("updateAppUser", map);
	}

	/**
	 * 获取部门列表
	 * 20140505
	 * txb
	 */
	public List getDeptList() throws Exception {
		return dao.queryForList("getDeptList");
	}

	/**
	 * 删除部门
	 * 20150620
	 * txb
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delDept(String ids) throws Exception {
		String[] temp=ids.split(",");
		for(int i=0;i<temp.length;i++){
			if(null!=temp[i]&&!"".equals(temp[i])){
				String dept_id=temp[i];
				List list=dao.queryForList("getRoleListByDept",dept_id);
				if(null!=list&&0!=list.size()){//部门下有角色信息
					throw new RuntimeException();
				}else{//直接删除部门
					dao.delete("delDept", dept_id);
				}
			}
			
		}
	}

	/**
	 * 查询用户名是否重复
	 */
	public int queryDeptCount(DeptVo deptVo) throws Exception {
		
		return (Integer) dao.queryById("querydeptcount", deptVo);
	}


}
