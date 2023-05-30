package com.sdkj.project.vo;

public class ProjectChildVo {
	private int pc_id;//主键
	private int pro_id;//工程主键
	private String pc_name;//子工程名
	private String pc_level;//子工程层级
	private String _parentId;//父节点
	private String pc_is_child;//是否最底层
	private String pc_describe;//工程描述
	
	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public int getPc_id() {
		return pc_id;
	}
	public void setPc_id(int pc_id) {
		this.pc_id = pc_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPc_name() {
		return pc_name;
	}
	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}
	public String getPc_level() {
		return pc_level;
	}
	public void setPc_level(String pc_level) {
		this.pc_level = pc_level;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
	public String getPc_is_child() {
		return pc_is_child;
	}
	public void setPc_is_child(String pc_is_child) {
		this.pc_is_child = pc_is_child;
	}
	public String getPc_describe() {
		return pc_describe;
	}
	public void setPc_describe(String pc_describe) {
		this.pc_describe = pc_describe;
	}

}
