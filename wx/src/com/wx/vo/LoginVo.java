package com.wx.vo;




public class LoginVo {

	 public String prjname;//  2
	 public String cardnum;
	 public String name;//
	 public String tiwen;//	
	public String prjId;//1
	public String personType;
   	public String status;
   	public String corpname;//1
   	public String openid;//1
   	public String phone;//
	public String personOne;//
   	
		public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
		public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPersonOne() {
		return personOne;
	}
	public void setPersonOne(String personOne) {
		this.personOne = personOne;
	}
		public String getCorpname() {
			return corpname;
		}
		public void setCorpname(String corpname) {
			this.corpname = corpname;
		}
		public String getPrjname() {
			return prjname;
		}
		public void setPrjname(String prjname) {
			this.prjname = prjname;
		}
		public String getCardnum() {
			return cardnum;
		}
		public void setCardnum(String cardnum) {
			this.cardnum = cardnum;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTiwen() {
			return tiwen;
		}
		public void setTiwen(String tiwen) {
			this.tiwen = tiwen;
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "LoginVo [prjname=" + prjname + ", cardnum=" + cardnum + ", name=" + name + ", tiwen=" + tiwen
					+ ", prjId=" + prjId + ", personType=" + personType + ", status=" + status + ", corpname="
					+ corpname + "]";
		}
   	
}

