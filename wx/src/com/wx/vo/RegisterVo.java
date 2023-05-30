package com.wx.vo;


public class RegisterVo {

	
	public String prjId;//
	    
	 public String prjname;//    

	     public String corpname;//	
    	
    	public String name;//
    	
    	public String cardnum;
    	
    	public String phone;
    	
    	public String personType;
    	
    	public String personOne;//	
    	
    	public String info;//	
    	public String prjType;//
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
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getPrjType() {
			return prjType;
		}
		public void setPrjType(String prjType) {
			this.prjType = prjType;
		}
		@Override
		public String toString() {
			return "RegisterVo [prjId=" + prjId + ", prjname=" + prjname + ", corpname=" + corpname + ", name=" + name
					+ ", cardnum=" + cardnum + ", phone=" + phone + ", personType=" + personType + ", personOne="
					+ personOne + ", info=" + info + ", prjType=" + prjType + "]";
		}
    	
    	
}
