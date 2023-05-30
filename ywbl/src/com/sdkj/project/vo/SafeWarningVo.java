package com.sdkj.project.vo;

/**
 * 
 * Describe:安全报警
 * @date 2014-5-21
 * @author txb
 */
public class SafeWarningVo {
	
	private String scId; //安全id
	private String pcId; //子项目id
	private String scTitle; //标题
	private String scType; //类型
	private String scFileUrl; //文件路径
	private String scFileName; //文件名称
	private String scRemark; //备注
	private String scDate; //日期
	private String pcName; //子项目名称
	private String proName; //项目名称
	
	
	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getScTitle() {
		return scTitle;
	}
	public void setScTitle(String scTitle) {
		this.scTitle = scTitle;
	}
	public String getScType() {
		return scType;
	}
	public void setScType(String scType) {
		this.scType = scType;
	}
	public String getScFileUrl() {
		return scFileUrl;
	}
	public void setScFileUrl(String scFileUrl) {
		this.scFileUrl = scFileUrl;
	}
	public String getScFileName() {
		return scFileName;
	}
	public void setScFileName(String scFileName) {
		this.scFileName = scFileName;
	}
	public String getScRemark() {
		return scRemark;
	}
	public void setScRemark(String scRemark) {
		this.scRemark = scRemark;
	}
	public String getScDate() {
		return scDate;
	}
	public void setScDate(String scDate) {
		this.scDate = scDate;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
}
