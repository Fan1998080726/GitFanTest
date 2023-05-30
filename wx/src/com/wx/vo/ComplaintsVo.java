package com.wx.vo;

/**
 * 农民工投诉
 * 
 * @author fcl 2021年6月23日13:58:27
 */
public class ComplaintsVo {
	private String prjid;// 企业ID
	private String id;
	private String persoNname;// 投诉人
	private String unitName;// 投诉单位
	private String unitTel;// 投诉单位联系电话
	private String personTel;// 投诉人联系电话
	private String complaintsContend;// 投诉内容
	private String openid;// 微信ID

	private String personType;// 工种
	private String idCard;// 身份证号
	private String startime;// 欠薪开始日期
	private String endtime;// 欠薪结束日期
	private double oweMoney;// 欠薪金额
	private String projectPersonName;// 项目经理
	private String projectPersonTel;// 项目经理联系方式
	private String laborUnit;// 劳务单位
	private String laborUnitName;// 劳务单位联系人
	private String laborUnitTel;// 劳务单位联系方式
	private String teamName;// 班组长姓名
	private String teamTel;// 班组长联系方式
	private String dept_name;// 所属住建局
	private String projectName;//项目名称
	
	

	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public double getOweMoney() {
		return oweMoney;
	}

	public void setOweMoney(double oweMoney) {
		this.oweMoney = oweMoney;
	}

	public String getProjectPersonName() {
		return projectPersonName;
	}

	public void setProjectPersonName(String projectPersonName) {
		this.projectPersonName = projectPersonName;
	}

	public String getProjectPersonTel() {
		return projectPersonTel;
	}

	public void setProjectPersonTel(String projectPersonTel) {
		this.projectPersonTel = projectPersonTel;
	}

	public String getLaborUnit() {
		return laborUnit;
	}

	public void setLaborUnit(String laborUnit) {
		this.laborUnit = laborUnit;
	}

	public String getLaborUnitName() {
		return laborUnitName;
	}

	public void setLaborUnitName(String laborUnitName) {
		this.laborUnitName = laborUnitName;
	}

	public String getLaborUnitTel() {
		return laborUnitTel;
	}

	public void setLaborUnitTel(String laborUnitTel) {
		this.laborUnitTel = laborUnitTel;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamTel() {
		return teamTel;
	}

	public void setTeamTel(String teamTel) {
		this.teamTel = teamTel;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPrjid() {
		return prjid;
	}

	public void setPrjid(String prjid) {
		this.prjid = prjid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersoNname() {
		return persoNname;
	}

	public void setPersoNname(String persoNname) {
		this.persoNname = persoNname;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitTel() {
		return unitTel;
	}

	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

	public String getPersonTel() {
		return personTel;
	}

	public void setPersonTel(String personTel) {
		this.personTel = personTel;
	}

	public String getComplaintsContend() {
		return complaintsContend;
	}

	public void setComplaintsContend(String complaintsContend) {
		this.complaintsContend = complaintsContend;
	}

	@Override
	public String toString() {
		return "ComplaintsVo [prjid=" + prjid + ", id=" + id + ", persoNname=" + persoNname + ", unitName=" + unitName
				+ ", unitTel=" + unitTel + ", personTel=" + personTel + ", complaintsContend=" + complaintsContend
				+ ", openid=" + openid + ", personType=" + personType + ", idCard=" + idCard + ", startime=" + startime
				+ ", endtime=" + endtime + ", oweMoney=" + oweMoney + ", projectPersonName=" + projectPersonName
				+ ", projectPersonTel=" + projectPersonTel + ", laborUnit=" + laborUnit + ", laborUnitName="
				+ laborUnitName + ", laborUnitTel=" + laborUnitTel + ", teamName=" + teamName + ", teamTel=" + teamTel
				+ "]";
	}
	
	

}


