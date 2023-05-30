package com.sdkj.project.vo;

public class ProjectFileVo {
	private int pf_id;
	private int pro_id;
	private String pf_name;
	private String pf_url;
	
	/*20150910*/
	private String SysTime;/*¼����ݵ�ϵͳʱ��*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public int getPf_id() {
		return pf_id;
	}
	public void setPf_id(int pf_id) {
		this.pf_id = pf_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPf_name() {
		return pf_name;
	}
	public void setPf_name(String pf_name) {
		this.pf_name = pf_name;
	}
	public String getPf_url() {
		return pf_url;
	}
	public void setPf_url(String pf_url) {
		this.pf_url = pf_url;
	}

}
