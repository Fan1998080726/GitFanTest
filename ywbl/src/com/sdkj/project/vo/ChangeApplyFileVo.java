package com.sdkj.project.vo;

public class ChangeApplyFileVo {

	private String cafId; //主键
	private String caId; //变更申请id
	private String cafName; //文件名称
	private String cafUrl; //文件路径
	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public String getCafId() {
		return cafId;
	}
	public void setCafId(String cafId) {
		this.cafId = cafId;
	}
	public String getCaId() {
		return caId;
	}
	public void setCaId(String caId) {
		this.caId = caId;
	}
	public String getCafName() {
		return cafName;
	}
	public void setCafName(String cafName) {
		this.cafName = cafName;
	}
	public String getCafUrl() {
		return cafUrl;
	}
	public void setCafUrl(String cafUrl) {
		this.cafUrl = cafUrl;
	}
	

}
