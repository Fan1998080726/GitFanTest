package com.wx.vo;


public class User2 {
		String name;
		String sname;
		String jname;
		String sjname;

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
