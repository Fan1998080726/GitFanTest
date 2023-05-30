package com.sdkj.system.service;

import java.util.List;

import com.sdkj.system.vo.DeptVo;
import com.sdkj.util.context.Pagination;

public interface IDeptService {
	
	public List queryDept(String deptName, Pagination pagination) throws Exception;
	
	public void updateDept(DeptVo deptVo) throws Exception;
	
	public DeptVo getDeptById(long dept_id) throws Exception;

	public List selectUserByDept(String dept_id, Pagination pagination) throws Exception;

	public void updateAppUser(String userId, String deptId) throws Exception;

	public List getDeptList() throws Exception;

	public void delDept(String ids) throws Exception;

	public int queryDeptCount(DeptVo deptVo)  throws Exception;

}
