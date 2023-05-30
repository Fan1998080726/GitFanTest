package com.sdkj.project.vo;

import java.util.List;

/**
 * 
 * Describe:变更请求 vo
 * @date 2014-5-4
 * @author txb
 */
public class ChangeApplyVo {
	private int aaId; //主键
	private String proId; //项目id
	private String aaDate; //请求日期
	private String aaUser; //请求人id
	private String aaUserName; //请求人名称
	private String aaRemark; //备注
	private String aaFlag; //状态 29暂存 30 审批中 31 审批通过
	private String aaFlagName; //状态 29暂存 30 审批中 31 审批通过
	private List<ChangeApplyFileVo> files; //文件详情
	
	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public List<ChangeApplyFileVo> getFiles() {
		return files;
	}
	public void setFiles(List<ChangeApplyFileVo> files) {
		this.files = files;
	}
	public String getAaUserName() {
		return aaUserName;
	}
	public void setAaUserName(String aaUserName) {
		this.aaUserName = aaUserName;
	}
	public String getAaFlagName() {
		return aaFlagName;
	}
	public void setAaFlagName(String aaFlagName) {
		this.aaFlagName = aaFlagName;
	}
	public int getAaId() {
		return aaId;
	}
	public void setAaId(int aaId) {
		this.aaId = aaId;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getAaDate() {
		return aaDate;
	}
	public void setAaDate(String aaDate) {
		this.aaDate = aaDate;
	}
	public String getAaUser() {
		return aaUser;
	}
	public void setAaUser(String aaUser) {
		this.aaUser = aaUser;
	}
	public String getAaRemark() {
		return aaRemark;
	}
	public void setAaRemark(String aaRemark) {
		this.aaRemark = aaRemark;
	}
	public String getAaFlag() {
		return aaFlag;
	}
	public void setAaFlag(String aaFlag) {
		this.aaFlag = aaFlag;
	}
	
}
