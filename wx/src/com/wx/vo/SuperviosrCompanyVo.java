package com.wx.vo;

 

public class SuperviosrCompanyVo {
	 private String 	id;
	 private String 	usid;
	 private String 	system;
	 private String 	updateTime;
	 private String 	lastTime;
	 private String 	 SuperviosrCount;
	 private String 	SuperviosrMonth;
	 private String 	SuperviosrValue;
	 private String 	 SuperviosrProjectCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getSuperviosrCount() {
		return SuperviosrCount;
	}
	public void setSuperviosrCount(String superviosrCount) {
		SuperviosrCount = superviosrCount;
	}
	public String getSuperviosrMonth() {
		return SuperviosrMonth;
	}
	public void setSuperviosrMonth(String superviosrMonth) {
		SuperviosrMonth = superviosrMonth;
	}
	public String getSuperviosrValue() {
		return SuperviosrValue;
	}
	public void setSuperviosrValue(String superviosrValue) {
		SuperviosrValue = superviosrValue;
	}
	public String getSuperviosrProjectCount() {
		return SuperviosrProjectCount;
	}
	public void setSuperviosrProjectCount(String superviosrProjectCount) {
		SuperviosrProjectCount = superviosrProjectCount;
	}
	@Override
	public String toString() {
		return "SuperviosrCompanyVo [id=" + id + ", usid=" + usid + ", system=" + system + ", updateTime=" + updateTime
				+ ", lastTime=" + lastTime + ", SuperviosrCount=" + SuperviosrCount + ", SuperviosrMonth="
				+ SuperviosrMonth + ", SuperviosrValue=" + SuperviosrValue + ", SuperviosrProjectCount="
				+ SuperviosrProjectCount + "]";
	}
	 
}
