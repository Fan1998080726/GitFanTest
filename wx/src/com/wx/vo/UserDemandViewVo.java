package com.wx.vo;
//用户需求
public class UserDemandViewVo {
	
	private String 		NowPopulation;//现有人数
	 private String 		UserNick;//用工数量(缺口)
	 private String 		UserType;//工种
	 private String 		StartTime;//用工开始时间
	 private String 	EndTime;//用工结束时间
	 private String 		Fp;//扶贫
	 private String 		Bd;//百大
	 private String 		Yq;//园区
	 private String 		Qt;//其他
	 private String 		Status;
	public String getNowPopulation() {
		return NowPopulation;
	}
	public void setNowPopulation(String nowPopulation) {
		NowPopulation = nowPopulation;
	}
	public String getUserNick() {
		return UserNick;
	}
	public void setUserNick(String userNick) {
		UserNick = userNick;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getFp() {
		return Fp;
	}
	public void setFp(String fp) {
		Fp = fp;
	}
	public String getBd() {
		return Bd;
	}
	public void setBd(String bd) {
		Bd = bd;
	}
	public String getYq() {
		return Yq;
	}
	public void setYq(String yq) {
		Yq = yq;
	}
	public String getQt() {
		return Qt;
	}
	public void setQt(String qt) {
		Qt = qt;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	 
	 
}
