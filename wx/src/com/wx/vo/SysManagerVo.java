package com.wx.vo;


public class SysManagerVo {

	 
	 public String prjName;//  
	 public String Corp;
	 public String name;//
	 public String cardnum;//	
	public String phone;//
	public String username;
	public String iffever;//是否有发热、乏力、干咳等症状
	public String ifaffirm;//是否接触确认或疑惑病例人员
	public String ifbeen;//是否到过疫情严重地区旅居
	
    	public String getIffever() {
		return iffever;
	}
	public void setIffever(String iffever) {
		this.iffever = iffever;
	}
	public String getIfaffirm() {
		return ifaffirm;
	}
	public void setIfaffirm(String ifaffirm) {
		this.ifaffirm = ifaffirm;
	}
	public String getIfbeen() {
		return ifbeen;
	}
	public void setIfbeen(String ifbeen) {
		this.ifbeen = ifbeen;
	}
		public String status;
    	
		public String getPrjName() {
			return prjName;
		}
		public void setPrjName(String prjName) {
			this.prjName = prjName;
		}
		public String getCorp() {
			return Corp;
		}
		public void setCorp(String corp) {
			Corp = corp;
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
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "SysManagerVo [prjName=" + prjName + ", Corp=" + Corp + ", name=" + name + ", cardnum=" + cardnum
					+ ", phone=" + phone + ", username=" + username + ", status=" + status + "]";
		}
    	
    	
}
