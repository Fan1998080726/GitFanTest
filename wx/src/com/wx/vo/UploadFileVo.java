package com.wx.vo;


public class UploadFileVo {

		private String cf_id;
		private String c_id;
		private String cf_name;
		private String cf_url;
		private String cf_type;
		private String cf_time;
		private String category;
		private String stateid;//20191211 fcl
		
		
		
		public String getCf_time() {
			return cf_time;
		}
		public void setCf_time(String cf_time) {
			this.cf_time = cf_time;
		}
		public String getStateid() {
			return stateid;
		}
		public void setStateid(String stateid) {
			this.stateid = stateid;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getCf_type() {
			return cf_type;
		}
		public void setCf_type(String cf_type) {
			this.cf_type = cf_type;
		}
		public String getCf_id() {
			return cf_id;
		}
		public void setCf_id(String cf_id) {
			this.cf_id = cf_id;
		}
		public String getC_id() {
			return c_id;
		}
		public void setC_id(String c_id) {
			this.c_id = c_id;
		}
		public String getCf_name() {
			return cf_name;
		}
		public void setCf_name(String cf_name) {
			this.cf_name = cf_name;
		}
		public String getCf_url() {
			return cf_url;
		}
		public void setCf_url(String cf_url) {
			this.cf_url = cf_url;
		}
		@Override
		public String toString() {
			return "UploadFileVo [cf_id=" + cf_id + ", c_id=" + c_id + ", cf_name=" + cf_name + ", cf_url=" + cf_url
					+ ", cf_type=" + cf_type + ", category=" + category + ", stateid=" + stateid + "]";
		}

}
