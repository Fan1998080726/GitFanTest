package com.wx.vo;

public class PersonCheckRecordVo {

	private  String  id;
	
	private  String inittime;
	
	/**
	 * 项目ID
	 */
	private  String 	proid;  
	/**
	 * 单项工程ID
	 */
	private  String 	monid;
	
	/**
	 * 所属的单项工程状态
	 */
	private  String monstate;
	
	/**
	 * 检查人ID
	 */
	private  String check_uid;
	/**
	 * 备注
	 */
	private  String 	breaks;
	/**
	 * 检查人
	 */
	private  String 	checkpeson;
	/**
	 * 检查人联系电话
	 */
	private  String 	checkphone;
	
	
	private  String fid;
	
	private  String updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInittime() {
		return inittime;
	}

	public void setInittime(String inittime) {
		this.inittime = inittime;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getMonid() {
		return monid;
	}

	public void setMonid(String monid) {
		this.monid = monid;
	}

	public String getMonstate() {
		return monstate;
	}

	public void setMonstate(String monstate) {
		this.monstate = monstate;
	}

	public String getCheck_uid() {
		return check_uid;
	}

	public void setCheck_uid(String check_uid) {
		this.check_uid = check_uid;
	}

	public String getBreaks() {
		return breaks;
	}

	public void setBreaks(String breaks) {
		this.breaks = breaks;
	}

	public String getCheckpeson() {
		return checkpeson;
	}

	public void setCheckpeson(String checkpeson) {
		this.checkpeson = checkpeson;
	}

	public String getCheckphone() {
		return checkphone;
	}

	public void setCheckphone(String checkphone) {
		this.checkphone = checkphone;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "PersonCheckRecordVo [id=" + id + ", inittime=" + inittime + ", proid=" + proid + ", monid=" + monid
				+ ", monstate=" + monstate + ", check_uid=" + check_uid + ", breaks=" + breaks + ", checkpeson="
				+ checkpeson + ", checkphone=" + checkphone + ", fid=" + fid + ", updatetime=" + updatetime + "]";
	}
	
	
	
}
