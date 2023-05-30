package com.wx.vo;


public class User {
	String name;
	String lxr;
	String tel;
	
	String sname;
	String slxr;
	String stel;
	String fbname;
	
	public String getFbname() {
		return fbname;
	}

	public void setFbname(String fbname) {
		this.fbname = fbname;
	}

	String jlname;//监理单位
	public String getJlname() {
		return jlname;
	}

	public void setJlname(String jlname) {
		this.jlname = jlname;
	}

	String jname;
	String sjname;//设计单位
	
	
	public String getLwname() {
		return lwname;
	}

	public void setLwname(String lwname) {
		this.lwname = lwname;
	}

	String lwname;
	
	
	public String getSlxr() {
		return slxr;
	}

	public void setSlxr(String slxr) {
		this.slxr = slxr;
	}

	public String getStel() {
		return stel;
	}

	public void setStel(String stel) {
		this.stel = stel;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getSjname() {
		return sjname;
	}

	public void setSjname(String sjname) {
		this.sjname = sjname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", sname=" + sname + ", jname=" + jname + ", sjname=" + sjname + "]";
	}

 
}
