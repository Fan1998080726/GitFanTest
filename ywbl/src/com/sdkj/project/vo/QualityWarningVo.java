package com.sdkj.project.vo;

/**
 * 
 * Describe:质量报警
 * @date 2014-5-21
 * @author txb
 */
public class QualityWarningVo {
	
	private String qcId; //质量id
	private String pcId; //子工程id
	private String qcTitle; //标题
	private String qcType; //类型
	private String qcFileUrl; //文件路径
	private String qcFileName; //文件名称
	private String qcRemark; //备注信息
	private String qcDate; //日期
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
	public String getQcId() {
		return qcId;
	}
	public void setQcId(String qcId) {
		this.qcId = qcId;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getQcTitle() {
		return qcTitle;
	}
	public void setQcTitle(String qcTitle) {
		this.qcTitle = qcTitle;
	}
	public String getQcType() {
		return qcType;
	}
	public void setQcType(String qcType) {
		this.qcType = qcType;
	}
	public String getQcFileUrl() {
		return qcFileUrl;
	}
	public void setQcFileUrl(String qcFileUrl) {
		this.qcFileUrl = qcFileUrl;
	}
	public String getQcFileName() {
		return qcFileName;
	}
	public void setQcFileName(String qcFileName) {
		this.qcFileName = qcFileName;
	}
	public String getQcRemark() {
		return qcRemark;
	}
	public void setQcRemark(String qcRemark) {
		this.qcRemark = qcRemark;
	}
	public String getQcDate() {
		return qcDate;
	}
	public void setQcDate(String qcDate) {
		this.qcDate = qcDate;
	}
	
}
