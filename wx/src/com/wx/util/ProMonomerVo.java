package com.wx.util;
/**
 * 单位数据  
 * PROMONOMER
 * @author Administrator  fcl 2021年7月16日14:11:00
 *
 */
public class ProMonomerVo {
  private  String  pro_id;//
  private  String  id;//
  private  String  inittime;//
  private  String  updatetime;//
  private  String  unitname;//单位名称
  private  String  builarea;//建筑面积
  private  String  layernumber;//层数
  private  String  corpname;//施工单位
  private  String  supername;//监理单位
  private  String  sendtime;//开工日期
  private  String  basisovertime;//基础完工日期
  private  String  mainovertime;//主题完工日期
  private  String  decorateovertime;//装卸完工日期
  private  String  endtime;//竣工日期
  private  String  decoratelevel;//质量等级
  private  String  probreaks;// 项目备注
  private  String  breaks;// 备注
  private  String  addtime;// 备注
  private  String  topfloor;// 地上层数
  private  String  downfloor;// 地下层数
  private  String  fzbh;//  工改分支编号 管理单体表
  
  private  String  state1;//开工状态
  private  String   state2; //基础状态
  private  String   state3; //主题状态
  private  String   state4; //装卸状态
  private  String  	state5;//竣工状态
  
  
  private  String  	downCount;//地下层数
  
  private  String  	topCount;//地上层数
  
  
  
  
  private  String  	xmmc;//项目名称
  private  String  	user_name;//企业名称
  private  String  	proid;//单项工程分配表ID
  
  
  private  String  datatype;
  private  String  name;//房开企业姓名
  
  
  
  
  
  

  
  public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDatatype() {
	return datatype;
}
public void setDatatype(String datatype) {
	this.datatype = datatype;
}
public String getProid() {
	return proid;
}
public void setProid(String proid) {
	this.proid = proid;
}
public String getXmmc() {
	return xmmc;
}
public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getDownCount() {
	return downCount;
}
public void setDownCount(String downCount) {
	this.downCount = downCount;
}
public String getTopCount() {
	return topCount;
}
public void setTopCount(String topCount) {
	this.topCount = topCount;
}
public String getProbreaks() {
	return probreaks;
}
public void setProbreaks(String probreaks) {
	this.probreaks = probreaks;
}
public String getState1() {
	return state1;
}
public void setState1(String state1) {
	this.state1 = state1;
}
public String getState2() {
	return state2;
}
public void setState2(String state2) {
	this.state2 = state2;
}
public String getState3() {
	return state3;
}
public void setState3(String state3) {
	this.state3 = state3;
}
public String getState4() {
	return state4;
}
public void setState4(String state4) {
	this.state4 = state4;
}
public String getState5() {
	return state5;
}
public void setState5(String state5) {
	this.state5 = state5;
}
public String getFzbh() {
	return fzbh;
}
public void setFzbh(String fzbh) {
	this.fzbh = fzbh;
}
public String getTopfloor() {
	return topfloor;
}
public void setTopfloor(String topfloor) {
	this.topfloor = topfloor;
}
public String getDownfloor() {
	return downfloor;
}
public void setDownfloor(String downfloor) {
	this.downfloor = downfloor;
}
public String getAddtime() {
	return addtime;
}
public void setAddtime(String addtime) {
	this.addtime = addtime;
}
public String getPro_id() {
	return pro_id;
}
public void setPro_id(String pro_id) {
	this.pro_id = pro_id;
}
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
public String getUpdatetime() {
	return updatetime;
}
public void setUpdatetime(String updatetime) {
	this.updatetime = updatetime;
}
public String getUnitname() {
	return unitname;
}
public void setUnitname(String unitname) {
	this.unitname = unitname;
}
public String getBuilarea() {
	return builarea;
}
public void setBuilarea(String builarea) {
	this.builarea = builarea;
}
public String getLayernumber() {
	return layernumber;
}
public void setLayernumber(String layernumber) {
	this.layernumber = layernumber;
}
public String getCorpname() {
	return corpname;
}
public void setCorpname(String corpname) {
	this.corpname = corpname;
}
public String getSupername() {
	return supername;
}
public void setSupername(String supername) {
	this.supername = supername;
}
public String getSendtime() {
	return sendtime;
}
public void setSendtime(String sendtime) {
	this.sendtime = sendtime;
}
public String getBasisovertime() {
	return basisovertime;
}
public void setBasisovertime(String basisovertime) {
	this.basisovertime = basisovertime;
}
public String getMainovertime() {
	return mainovertime;
}
public void setMainovertime(String mainovertime) {
	this.mainovertime = mainovertime;
}
public String getDecorateovertime() {
	return decorateovertime;
}
public void setDecorateovertime(String decorateovertime) {
	this.decorateovertime = decorateovertime;
}
public String getEndtime() {
	return endtime;
}
public void setEndtime(String endtime) {
	this.endtime = endtime;
}
public String getDecoratelevel() {
	return decoratelevel;
}
public void setDecoratelevel(String decoratelevel) {
	this.decoratelevel = decoratelevel;
}
public String getBreaks() {
	return breaks;
}
public void setBreaks(String breaks) {
	this.breaks = breaks;
}
@Override
public String toString() {
	return "ProMonomerVo [pro_id=" + pro_id + ", id=" + id + ", inittime=" + inittime + ", updatetime=" + updatetime
			+ ", unitname=" + unitname + ", builarea=" + builarea + ", layernumber=" + layernumber + ", corpname="
			+ corpname + ", supername=" + supername + ", sendtime=" + sendtime + ", basisovertime=" + basisovertime
			+ ", mainovertime=" + mainovertime + ", decorateovertime=" + decorateovertime + ", endtime=" + endtime
			+ ", decoratelevel=" + decoratelevel + ", breaks=" + breaks + "]";
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
