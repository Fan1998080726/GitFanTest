package com.wx.vo;
/**
 * 监理企业  项目检查人
 * @author Administrator
 *
 */
public class JlCheckPersonVo {
	/**
	 * 检查人
	 */
	private  String  personname;
	
	private  String  phone;//手机号
	
	private  String  id;
	
	private  String openid ;//微信绑定ID
	
	private  String  inittime;
	
	private  String  updatetime;
	
	private  String usercode;//企业信用代码
	
	private String  state;//状态

	private String  idcard;//检查人身份证号
	
	
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getInittime() {
		return inittime;
	}

	public void setInittime(String inittime) {
		this.inittime = inittime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "JlCheckPersonVo [personname=" + personname + ", phone=" + phone + ", id=" + id + ", openid=" + openid
				+ ", inittime=" + inittime + ", updatetime=" + updatetime + ", usercode=" + usercode + ", state="
				+ state + "]";
	}
	
	
	
	
	
}
