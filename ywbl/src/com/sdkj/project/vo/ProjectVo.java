package com.sdkj.project.vo;

public class ProjectVo {

	private int pro_id;//主键
	private String pro_name;//工程名
	private String pro_type;//工程类型
	private String pro_invest;//工程投资
	private String pro_measure;//工程量
	private String pro_start_date;//开始时间
	private String pro_end_date;//结束时间
	private String pro_place;//工程地址
	private String pro_manager;//注册建造师
	private String pro_manager_name;//注册建造师名
	private String pro_state;//施工状态
	private String pro_describe;//工程描述
	private String pro_flag;//工程标示
	private String pro_assistants;//
	
	private String ml_num;//工程申领材料数量
	

	private String receiveDate;/* 收件日期 */
	private String endDate;/* 办结日期 */
	private String state;/*办理状态*/

	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public String getPro_manager_name() {
		return pro_manager_name;
	}
	public void setPro_manager_name(String pro_manager_name) {
		this.pro_manager_name = pro_manager_name;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_type() {
		return pro_type;
	}
	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}
	public String getPro_invest() {
		return pro_invest;
	}
	public void setPro_invest(String pro_invest) {
		this.pro_invest = pro_invest;
	}
	public String getPro_measure() {
		return pro_measure;
	}
	public void setPro_measure(String pro_measure) {
		this.pro_measure = pro_measure;
	}
	public String getPro_start_date() {
		return pro_start_date;
	}
	public void setPro_start_date(String pro_start_date) {
		this.pro_start_date = pro_start_date;
	}
	public String getPro_end_date() {
		return pro_end_date;
	}
	public void setPro_end_date(String pro_end_date) {
		this.pro_end_date = pro_end_date;
	}
	public String getPro_place() {
		return pro_place;
	}
	public void setPro_place(String pro_place) {
		this.pro_place = pro_place;
	}
	public String getPro_manager() {
		return pro_manager;
	}
	public void setPro_manager(String pro_manager) {
		this.pro_manager = pro_manager;
	}
	public String getPro_state() {
		return pro_state;
	}
	public void setPro_state(String pro_state) {
		this.pro_state = pro_state;
	}
	public String getPro_describe() {
		return pro_describe;
	}
	public void setPro_describe(String pro_describe) {
		this.pro_describe = pro_describe;
	}
	public String getPro_flag() {
		return pro_flag;
	}
	public void setPro_flag(String pro_flag) {
		this.pro_flag = pro_flag;
	}
	public String getPro_assistants() {
		return pro_assistants;
	}
	public void setPro_assistants(String pro_assistants) {
		this.pro_assistants = pro_assistants;
	}
	public String getMl_num() {
		return ml_num;
	}
	public void setMl_num(String ml_num) {
		this.ml_num = ml_num;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
