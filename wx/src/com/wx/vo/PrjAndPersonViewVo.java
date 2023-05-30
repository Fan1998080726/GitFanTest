package com.wx.vo;
public class PrjAndPersonViewVo {
	
	
	public String prjId;//
	public String prjname;//
	public String corpname;//
	public String name;//
	public String cardnum;//
	public String phone;//
	public String personType;//
	public String personOne;//
	public String openid;//
	public String SysManagerop;//
	public String buildCorpName;//
	public String consCorpName;//
	public String superCorpName;//
	
	private String lwunitName;//劳务企业联系人
	private String lwunitTel;//劳务企业联系电话
	private String dept_name;//劳务企业联系电话
	
	
	
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getLwunitName() {
		return lwunitName;
	}
	public void setLwunitName(String lwunitName) {
		this.lwunitName = lwunitName;
	}
	public String getLwunitTel() {
		return lwunitTel;
	}
	public void setLwunitTel(String lwunitTel) {
		this.lwunitTel = lwunitTel;
	}
	public String getDesignCorp() {
		return designCorp;
	}
	public void setDesignCorp(String designCorp) {
		this.designCorp = designCorp;
	}
	public  String designCorp;//
	public  String lwunit;
	
	public String getLwunit() {
		return lwunit;
	}
	public void setLwunit(String lwunit) {
		this.lwunit = lwunit;
	}
	public String subPagUtil;
	
	public String getSubPagUtil() {
		return subPagUtil;
	}
	public void setSubPagUtil(String subPagUtil) {
		this.subPagUtil = subPagUtil;
	}
	public String getBuildCorpName() {
		return buildCorpName;
	}
	public void setBuildCorpName(String buildCorpName) {
		this.buildCorpName = buildCorpName;
	}
	public String getConsCorpName() {
		return consCorpName;
	}
	public void setConsCorpName(String consCorpName) {
		this.consCorpName = consCorpName;
	}
	public String getSuperCorpName() {
		return superCorpName;
	}
	public void setSuperCorpName(String superCorpName) {
		this.superCorpName = superCorpName;
	}
	public String getSysManagerop() {
		return SysManagerop;
	}
	public void setSysManagerop(String sysManagerop) {
		SysManagerop = sysManagerop;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getPrjname() {
		return prjname;
	}
	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getPersonOne() {
		return personOne;
	}
	public void setPersonOne(String personOne) {
		this.personOne = personOne;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "PrjAndPersonViewVo [prjId=" + prjId + ", prjname=" + prjname + ", corpname=" + corpname + ", name="
				+ name + ", cardnum=" + cardnum + ", phone=" + phone + ", personType=" + personType + ", personOne="
				+ personOne + ", openid=" + openid + ", SysManagerop=" + SysManagerop + "]";
	}
	
	
}



