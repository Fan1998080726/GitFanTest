package com.wx.vo;

public class ProjectcheckVo {
	private String prjname;
	public String getPrjname() {
		return prjname;
	}
	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}
	private String id ;
	private String updatetime;
	private String systime;
	private String prjId;//20200329 页面调整一下，属性放上面，get、set 放下面
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	private String epidmiccontrol;//疫情防控
	private String roadlicence;//施工许可
	private String bidding;//招投标
	private String contract;//合同及履约
	private String peasang;//农民工管理四项制度
	private String personpostion;//人员到岗履职
	private String sceneadmin;//施工现场管理
	private String safetyadmin;//安全管理
	private String qualityadmin;//质量管理
	private String other;//其他
	
	public String getEpidmiccontrol() {
		return epidmiccontrol;
	}
	public void setEpidmiccontrol(String epidmiccontrol) {
		this.epidmiccontrol = epidmiccontrol;
	}
	public String getRoadlicence() {
		return roadlicence;
	}
	public void setRoadlicence(String roadlicence) {
		this.roadlicence = roadlicence;
	}
	public String getBidding() {
		return bidding;
	}
	public void setBidding(String bidding) {
		this.bidding = bidding;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getPeasang() {
		return peasang;
	}
	public void setPeasang(String peasang) {
		this.peasang = peasang;
	}
	public String getPersonpostion() {
		return personpostion;
	}
	public void setPersonpostion(String personpostion) {
		this.personpostion = personpostion;
	}
	public String getSceneadmin() {
		return sceneadmin;
	}
	public void setSceneadmin(String sceneadmin) {
		this.sceneadmin = sceneadmin;
	}
	public String getSafetyadmin() {
		return safetyadmin;
	}
	public void setSafetyadmin(String safetyadmin) {
		this.safetyadmin = safetyadmin;
	}
	public String getQualityadmin() {
		return qualityadmin;
	}
	public void setQualityadmin(String qualityadmin) {
		this.qualityadmin = qualityadmin;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	
	
}
