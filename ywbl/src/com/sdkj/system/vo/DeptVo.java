package com.sdkj.system.vo;

public class DeptVo {
	public int dept_id;
	public String dept_name;//部门名称
	public String dept_describe;//部门描述
	public String dept_app_user;//审批员
	public String dept_app_name;//审批员名称
	
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_describe() {
		return dept_describe;
	}
	public void setDept_describe(String dept_describe) {
		this.dept_describe = dept_describe;
	}
	public String getDept_app_user() {
		return dept_app_user;
	}
	public void setDept_app_user(String dept_app_user) {
		this.dept_app_user = dept_app_user;
	}
	public String getDept_app_name() {
		return dept_app_name;
	}
	public void setDept_app_name(String dept_app_name) {
		this.dept_app_name = dept_app_name;
	}

}
