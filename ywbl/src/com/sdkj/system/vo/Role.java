package com.sdkj.system.vo;


/**
 * 角色信息
 * @author 
 *
 */
public class Role {

	
	private int role_id;
	
	private String role_name;
	
	
	private String memo;
	
	private String dept_name;//部门名
	private String dept_id;//部门主键
	

	public String getDept_id() {
		return dept_id;
	}


	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}


	public String getDept_name() {
		return dept_name;
	}


	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}






	public int getRole_id() {
		return role_id;
	}


	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	
}
