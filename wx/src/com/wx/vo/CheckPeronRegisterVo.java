package com.wx.vo;
/* 20200331 txb */

public class CheckPeronRegisterVo {
	public String id;//
	public String systime;//
	public String lasttime;//
	public String name;//
	 public String phone;//    
	     public String dept_name;//	
    	public String openid;
    	public String state;

    	
		public String getState() {
			return state;
		}


		public void setState(String state) {
			this.state = state;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getDept_name() {
			return dept_name;
		}


		public void setDept_name(String dept_name) {
			this.dept_name = dept_name;
		}


		public String getOpenid() {
			return openid;
		}


		public void setOpenid(String openid) {
			this.openid = openid;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getSystime() {
			return systime;
		}


		public void setSystime(String systime) {
			this.systime = systime;
		}


		public String getLasttime() {
			return lasttime;
		}


		public void setLasttime(String lasttime) {
			this.lasttime = lasttime;
		}


		@Override
		public String toString() {
			return "CheckPeronRegisterVo [id=" + id + ", systime=" + systime + ", lasttime=" + lasttime + ", name="
					+ name + ", phone=" + phone + ", dept_name=" + dept_name + ", openid=" + openid + ", state=" + state
					+ "]";
		}

	

    	
    	
}
