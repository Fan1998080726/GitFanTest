package com.sdkj.project.vo;

/**
 * 
 * Describe:三维实景vo
 * @date 2014-5-13
 * @author txb
 */
public class RealViewVo {
	private String realId; //三维实景id
	private String realName; //三维实景name
	private String realUrl; //三维实景路径
	private String realRemark; //三维实景备注
	private String realDate; //三维实景上传时间
	private String proId; //项目id
	private String realUser; //上传人员id
	private String realUserName; //上传人员name
	
	
	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public String getRealUserName() {
		return realUserName;
	}
	public void setRealUserName(String realUserName) {
		this.realUserName = realUserName;
	}
	public String getRealUser() {
		return realUser;
	}
	public void setRealUser(String realUser) {
		this.realUser = realUser;
	}
	public String getRealId() {
		return realId;
	}
	public void setRealId(String realId) {
		this.realId = realId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRealUrl() {
		return realUrl;
	}
	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	public String getRealRemark() {
		return realRemark;
	}
	public void setRealRemark(String realRemark) {
		this.realRemark = realRemark;
	}
	public String getRealDate() {
		return realDate;
	}
	public void setRealDate(String realDate) {
		this.realDate = realDate;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}

}	
