package com.sdkj.system.vo;

public class UserInfo {

	private int id;        			//ID
	private String login_id;      		//登陆ID（帐号）
	private String login_password;		//登陆密码
	private String user_name;			//用户姓名	
	private int role_id;   			//用户角色ID
	private int dept_id;//部门id
	private String phone;               //联系电话
	private String email;				//电子邮件
	private String address;				//地址
	private String memo;				//备注
	private String dept_name;//部门名称
	private String role_name;//职位名称

	private  String  mid ;
	private  String  supervision_matter ;
	private  String  state ;
	
	
	
	
	
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getSupervision_matter() {
		return supervision_matter;
	}
	public void setSupervision_matter(String supervision_matter) {
		this.supervision_matter = supervision_matter;
	}
	/*20170218*/
	private String systime;
	/*曹继平创建的是五大主城区的并且需要按省里要求的规则产生编号。付波创建的是五大主城区之外的，不用产生相应编号。*/
	private String createUser;
	
	private String BuildCorpCode;//建设单位组织机构代码证号
	
	public String getBuildCorpCode() {
		return BuildCorpCode;
	}
	public void setBuildCorpCode(String buildCorpCode) {
		BuildCorpCode = buildCorpCode;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", login_id=" + login_id
				+ ", login_password=" + login_password + ", user_name="
				+ user_name + ", role_id=" + role_id + ", dept_id=" + dept_id
				+ ", phone=" + phone + ", email=" + email + ", address="
				+ address + ", memo=" + memo + ", dept_name=" + dept_name
				+ ", role_name=" + role_name + ", systime=" + systime
				+ ", createUser=" + createUser + ", BuildCorpCode="
				+ BuildCorpCode + "]";
	}
	
	
	
	
}
