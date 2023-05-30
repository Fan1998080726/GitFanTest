package com.sdkj.matters.vo;
/**
 * 用户ID表
 * @author Administrator
 *
 */
public class ParmsVo {
   private  String  id;
   private  String  login_id;
   private  String  role_id;
   private  String  dept_id;
   
   private  String  qiantouIscheck;//是否选中牵头部门
   private  String  peiheIscheck;//是否选中牵头部门
   
   
   
public String getQiantouIscheck() {
	return qiantouIscheck;
}
public void setQiantouIscheck(String qiantouIscheck) {
	this.qiantouIscheck = qiantouIscheck;
}
public String getPeiheIscheck() {
	return peiheIscheck;
}
public void setPeiheIscheck(String peiheIscheck) {
	this.peiheIscheck = peiheIscheck;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLogin_id() {
	return login_id;
}
public void setLogin_id(String login_id) {
	this.login_id = login_id;
}
public String getRole_id() {
	return role_id;
}
public void setRole_id(String role_id) {
	this.role_id = role_id;
}
public String getDept_id() {
	return dept_id;
}
public void setDept_id(String dept_id) {
	this.dept_id = dept_id;
}
@Override
public String toString() {
	return "ParmsVo [id=" + id + ", login_id=" + login_id + ", role_id=" + role_id + ", dept_id=" + dept_id + "]";
}
   
   
}
