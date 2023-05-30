package com.wx.vo;
/*工程项目检查表*/
/*工程项目检查表*/
public class PrjPersonVo {
 
	

	public String id;//主键
	public String name;//
	public String cardnum;//
	public String prjId;//
	public String personType;//
	public String prjType;//
	public String phone;//
	public String corpname;//
	public String inittime;//
	public String lastupdatetime;//
	public String prjname;//
	public String dept_name;//
	public String personOne;//
	public String info;//
	public String openid;//
	public String Managerop;//是否是管理人员
	
	
	public String getManagerop() {
		return Managerop;
	}
	public void setManagerop(String managerop) {
		Managerop = managerop;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getPersonOne() {
		return personOne;
	}
	public void setPersonOne(String personOne) {
		this.personOne = personOne;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getPrjname() {
		return prjname;
	}
	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getPrjType() {
		return prjType;
	}
	public void setPrjType(String prjType) {
		this.prjType = prjType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getInittime() {
		return inittime;
	}
	public void setInittime(String inittime) {
		this.inittime = inittime;
	}
	public String getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	@Override
	public String toString() {
		return "PrjPersonVo [id=" + id + ", name=" + name + ", cardnum=" + cardnum + ", prjId=" + prjId
				+ ", personType=" + personType + ", prjType=" + prjType + ", phone=" + phone + ", corpname=" + corpname
				+ ", inittime=" + inittime + ", lastupdatetime=" + lastupdatetime + ", prjname=" + prjname
				+ ", dept_name=" + dept_name + ", personOne=" + personOne + ", info=" + info + ", openid=" + openid
				+ ", Managerop=" + Managerop + "]";
	}

	
}
