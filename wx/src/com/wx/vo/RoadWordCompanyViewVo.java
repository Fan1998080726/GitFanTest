package com.wx.vo;

 
//施工企业月报表
public class RoadWordCompanyViewVo {
	 
	 private String 	RoadMonth;
	 private String 	PersonCount;
	 private String 	RoadMoney;
	 private String 	PoadValue;
	 private String 	RoadProjectCount;
	 private String 	RoadNewArea;
	 private String 	Staus;
	 
	public String getStaus() {
		return Staus;
	}
	public void setStaus(String staus) {
		Staus = staus;
	}
	 
	@Override
	public String toString() {
		return "RoadWordCompanyViewVo [RoadMonth=" + RoadMonth + ", PersonCount=" + PersonCount + ", RoadMoney=" + RoadMoney
				+ ", PoadValue=" + PoadValue + ", RoadProjectCount=" + RoadProjectCount + ", RoadNewArea=" + RoadNewArea
				+ ", Staus=" + Staus + "]";
	}
	public String getRoadMonth() {
		return RoadMonth;
	}
	public void setRoadMonth(String roadMonth) {
		RoadMonth = roadMonth;
	}
	public String getPersonCount() {
		return PersonCount;
	}
	public void setPersonCount(String personCount) {
		PersonCount = personCount;
	}
	public String getRoadMoney() {
		return RoadMoney;
	}
	public void setRoadMoney(String roadMoney) {
		RoadMoney = roadMoney;
	}
	public String getPoadValue() {
		return PoadValue;
	}
	public void setPoadValue(String poadValue) {
		PoadValue = poadValue;
	}
	public String getRoadProjectCount() {
		return RoadProjectCount;
	}
	public void setRoadProjectCount(String roadProjectCount) {
		RoadProjectCount = roadProjectCount;
	}
	public String getRoadNewArea() {
		return RoadNewArea;
	}
	public void setRoadNewArea(String roadNewArea) {
		RoadNewArea = roadNewArea;
	}
	 
	  
		
		
		
}
