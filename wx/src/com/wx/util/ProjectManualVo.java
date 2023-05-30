package com.wx.util;

import java.util.List;


/**
 * 项目手册  Bean
 * @author  fcl  2021年7月15日13:31:42
 *
 */
public class ProjectManualVo {
  private  String  id;//主键id
  private  String  qymc;//企业名称1
  private  String  xmmc;//项目名称1
  private  String  yszh;//预售证号
  private  String  xmzl;//项目坐落1
  private  String  qy;//区域1
  private  String  sqrq;//申请日期1
  private  String  slr;//受理人
  private  String  slrq;//受理日期1
  private  String  bjgje;//y项目投资额1
  private  String  xmscbh;//项目手册编号. 从项目手册引入
  private  String  jldwmc;//监理单位名称1
  private  String  xmzjxm;//项目总监姓名1
  private  String  xmzjlxdh;//项目总监联系电话1
  private  String  frdb;//法人代表1
  private  String  frdblxdh;//法人代表联系电话1
  private  String  bgdz;//办公地址1
  private  String  bwtr;//项目负责人1
  private  String  bwtrlxdh;//项目负责人联系电话1
  private  String  qyzz;//企业资质1
  private  String  qyzzbh;//企业资质编号1
  private  String  qyyyzzh;//企业营业执照号1
  private  String  jbrdh;//企业经办人电话1
  private  String  jbrmc;//企业经办人名称1
  private  String  sqzt;//0申请状态 0保存，1提交，2验核
  private  String  yxbz;//有效标志
  private  String  zhgxz;//最后更新者
  private  String  zhgxsj;//最后更新时间
  private  String  ydqdfs;//项目用地取得方式1
  private  String  ydpzwh;//用地批准文号1
  private  String  ydpzrq;//批准日期1
  private  String  gcghxkz;//建筑工程规划许可证1
  private  String  ydghxkz;//建筑用地规划许可证1
  private  String  tbr;//填报人1
  private  String  tbrdh;//填报人电话1
  private  String  ptzzmj;//y普通住宅面积1
  private  String  bsgymj;//y别墅公寓面积1
  private  String  symj;//y商业面积1
  private  String  cfmj;//y厂房面积1
  private  String  wjmj;//y文教面积1
  private  String  qtmj;//y其它面积1
  private  String  xzlmj;//y写字楼面积1
  private  String  jhkgrq;//计划开工日期1
  private  String  jhjgrq;//计划竣工日期1
  private  String  gcsgxkz;//建筑工程施工许可证
  private  String  xmxz;//项目性质1
  private  String  xmzdmj;//y项目占地面积1
  private  String  xmjzmj;//y项目建筑面积1
  private  String  slbh;//
  private  String  funcid;//流程id
  private  String  pro_id;//项目管理id
  private  String  tophouse;//高端住宅
  private  String  villa;//别墅
  private  String  probreaks;//备注 
  private  String fid;
  
  private String djtg;
  private String count1;
  private String count2;
  private String count3;
  private String count4;
  private String count5;
  
  
  
  
  
  
  private String telPerson;//联系人
  private String tel;//电话
  private String tbrtel;//电话

  private String house1;//  普通住宅	
  private String house2;//  别墅公寓	
  private String house3;// 写字楼	
  private String house4;// 商业	
  private String house5;// 厂房	
  private String house6;// 文教
  private String house7;// 其他
  
  private String statetest;//  审核备注
  private String statetime;// 审核日期
  
  
  private String projectnum;// 项目编号
  
  
  private String datatype;// 数据来源  0导入  1 录入
  
  
  
  
  
  
  
  	public String getDatatype() {
	return datatype;
}
public void setDatatype(String datatype) {
	this.datatype = datatype;
}
	public String econcorpname;//勘察单位名称：
	public String econcorpcode;//勘察单位组织机构代码：
	public String designcorpname;//设计单位名称：
	public String designcorpcode;//设计单位组织机构代码：
	public String conscorpname;//施工单位名称：
	public String conscorpcode;//施工单位组织机构代码：
	public String safetycerid;//施工单位安全生产许可证编号：
	public String supercorpname;//监理单位名称：
	public String supercorpcode;//监理单位组织机构代码：
	public String constructorname;//注册注册建造师：
	public String cidcardtypenum;//注册建造师证件类型：
	public String constructoridcard;//注册建造师证件号码：
	public String supervisionname;//总监理工程师姓名：
	public String sidcardtypenum;//总监理工程师证件类型：
	public String supervisionidcard;//总监理工程师证件号码：
  
  
  
  
  
	public String constructionperson;//施工单位项目负责人
	public String econcorpperson;//勘察单位项目负责人
	public String designcorpperson;//设计单位项目负责人 
	public String sgprojectleadertel;//施工单位项目负责人电话
	
	
	public String jl_userid;//监理UseID







	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getYszh() {
		return yszh;
	}
	public void setYszh(String yszh) {
		this.yszh = yszh;
	}
	public String getXmzl() {
		return xmzl;
	}
	public void setXmzl(String xmzl) {
		this.xmzl = xmzl;
	}
	public String getQy() {
		return qy;
	}
	public void setQy(String qy) {
		this.qy = qy;
	}
	public String getSqrq() {
		return sqrq;
	}
	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}
	public String getSlr() {
		return slr;
	}
	public void setSlr(String slr) {
		this.slr = slr;
	}
	public String getSlrq() {
		return slrq;
	}
	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}
	public String getBjgje() {
		return bjgje;
	}
	public void setBjgje(String bjgje) {
		this.bjgje = bjgje;
	}
	public String getXmscbh() {
		return xmscbh;
	}
	public void setXmscbh(String xmscbh) {
		this.xmscbh = xmscbh;
	}
	public String getJldwmc() {
		return jldwmc;
	}
	public void setJldwmc(String jldwmc) {
		this.jldwmc = jldwmc;
	}
	public String getXmzjxm() {
		return xmzjxm;
	}
	public void setXmzjxm(String xmzjxm) {
		this.xmzjxm = xmzjxm;
	}
	public String getXmzjlxdh() {
		return xmzjlxdh;
	}
	public void setXmzjlxdh(String xmzjlxdh) {
		this.xmzjlxdh = xmzjlxdh;
	}
	public String getFrdb() {
		return frdb;
	}
	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}
	public String getFrdblxdh() {
		return frdblxdh;
	}
	public void setFrdblxdh(String frdblxdh) {
		this.frdblxdh = frdblxdh;
	}
	public String getBgdz() {
		return bgdz;
	}
	public void setBgdz(String bgdz) {
		this.bgdz = bgdz;
	}
	public String getBwtr() {
		return bwtr;
	}
	public void setBwtr(String bwtr) {
		this.bwtr = bwtr;
	}
	public String getBwtrlxdh() {
		return bwtrlxdh;
	}
	public void setBwtrlxdh(String bwtrlxdh) {
		this.bwtrlxdh = bwtrlxdh;
	}
	public String getQyzz() {
		return qyzz;
	}
	public void setQyzz(String qyzz) {
		this.qyzz = qyzz;
	}
	public String getQyzzbh() {
		return qyzzbh;
	}
	public void setQyzzbh(String qyzzbh) {
		this.qyzzbh = qyzzbh;
	}
	public String getQyyyzzh() {
		return qyyyzzh;
	}
	public void setQyyyzzh(String qyyyzzh) {
		this.qyyyzzh = qyyyzzh;
	}
	public String getJbrdh() {
		return jbrdh;
	}
	public void setJbrdh(String jbrdh) {
		this.jbrdh = jbrdh;
	}
	public String getJbrmc() {
		return jbrmc;
	}
	public void setJbrmc(String jbrmc) {
		this.jbrmc = jbrmc;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getZhgxz() {
		return zhgxz;
	}
	public void setZhgxz(String zhgxz) {
		this.zhgxz = zhgxz;
	}
	public String getZhgxsj() {
		return zhgxsj;
	}
	public void setZhgxsj(String zhgxsj) {
		this.zhgxsj = zhgxsj;
	}
	public String getYdqdfs() {
		return ydqdfs;
	}
	public void setYdqdfs(String ydqdfs) {
		this.ydqdfs = ydqdfs;
	}
	public String getYdpzwh() {
		return ydpzwh;
	}
	public void setYdpzwh(String ydpzwh) {
		this.ydpzwh = ydpzwh;
	}
	public String getYdpzrq() {
		return ydpzrq;
	}
	public void setYdpzrq(String ydpzrq) {
		this.ydpzrq = ydpzrq;
	}
	public String getGcghxkz() {
		return gcghxkz;
	}
	public void setGcghxkz(String gcghxkz) {
		this.gcghxkz = gcghxkz;
	}
	public String getYdghxkz() {
		return ydghxkz;
	}
	public void setYdghxkz(String ydghxkz) {
		this.ydghxkz = ydghxkz;
	}
	public String getTbr() {
		return tbr;
	}
	public void setTbr(String tbr) {
		this.tbr = tbr;
	}
	public String getTbrdh() {
		return tbrdh;
	}
	public void setTbrdh(String tbrdh) {
		this.tbrdh = tbrdh;
	}
	public String getPtzzmj() {
		return ptzzmj;
	}
	public void setPtzzmj(String ptzzmj) {
		this.ptzzmj = ptzzmj;
	}
	public String getBsgymj() {
		return bsgymj;
	}
	public void setBsgymj(String bsgymj) {
		this.bsgymj = bsgymj;
	}
	public String getSymj() {
		return symj;
	}
	public void setSymj(String symj) {
		this.symj = symj;
	}
	public String getCfmj() {
		return cfmj;
	}
	public void setCfmj(String cfmj) {
		this.cfmj = cfmj;
	}
	public String getWjmj() {
		return wjmj;
	}
	public void setWjmj(String wjmj) {
		this.wjmj = wjmj;
	}
	public String getQtmj() {
		return qtmj;
	}
	public void setQtmj(String qtmj) {
		this.qtmj = qtmj;
	}
	public String getXzlmj() {
		return xzlmj;
	}
	public void setXzlmj(String xzlmj) {
		this.xzlmj = xzlmj;
	}
	public String getJhkgrq() {
		return jhkgrq;
	}
	public void setJhkgrq(String jhkgrq) {
		this.jhkgrq = jhkgrq;
	}
	public String getJhjgrq() {
		return jhjgrq;
	}
	public void setJhjgrq(String jhjgrq) {
		this.jhjgrq = jhjgrq;
	}
	public String getGcsgxkz() {
		return gcsgxkz;
	}
	public void setGcsgxkz(String gcsgxkz) {
		this.gcsgxkz = gcsgxkz;
	}
	public String getXmxz() {
		return xmxz;
	}
	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}
	public String getXmzdmj() {
		return xmzdmj;
	}
	public void setXmzdmj(String xmzdmj) {
		this.xmzdmj = xmzdmj;
	}
	public String getXmjzmj() {
		return xmjzmj;
	}
	public void setXmjzmj(String xmjzmj) {
		this.xmjzmj = xmjzmj;
	}
	public String getSlbh() {
		return slbh;
	}
	public void setSlbh(String slbh) {
		this.slbh = slbh;
	}
	public String getFuncid() {
		return funcid;
	}
	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getTophouse() {
		return tophouse;
	}
	public void setTophouse(String tophouse) {
		this.tophouse = tophouse;
	}
	public String getVilla() {
		return villa;
	}
	public void setVilla(String villa) {
		this.villa = villa;
	}
	public String getProbreaks() {
		return probreaks;
	}
	public void setProbreaks(String probreaks) {
		this.probreaks = probreaks;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getDjtg() {
		return djtg;
	}
	public void setDjtg(String djtg) {
		this.djtg = djtg;
	}
	public String getCount1() {
		return count1;
	}
	public void setCount1(String count1) {
		this.count1 = count1;
	}
	public String getCount2() {
		return count2;
	}
	public void setCount2(String count2) {
		this.count2 = count2;
	}
	public String getCount3() {
		return count3;
	}
	public void setCount3(String count3) {
		this.count3 = count3;
	}
	public String getCount4() {
		return count4;
	}
	public void setCount4(String count4) {
		this.count4 = count4;
	}
	public String getCount5() {
		return count5;
	}
	public void setCount5(String count5) {
		this.count5 = count5;
	}
	public String getTelPerson() {
		return telPerson;
	}
	public void setTelPerson(String telPerson) {
		this.telPerson = telPerson;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTbrtel() {
		return tbrtel;
	}
	public void setTbrtel(String tbrtel) {
		this.tbrtel = tbrtel;
	}
	public String getHouse1() {
		return house1;
	}
	public void setHouse1(String house1) {
		this.house1 = house1;
	}
	public String getHouse2() {
		return house2;
	}
	public void setHouse2(String house2) {
		this.house2 = house2;
	}
	public String getHouse3() {
		return house3;
	}
	public void setHouse3(String house3) {
		this.house3 = house3;
	}
	public String getHouse4() {
		return house4;
	}
	public void setHouse4(String house4) {
		this.house4 = house4;
	}
	public String getHouse5() {
		return house5;
	}
	public void setHouse5(String house5) {
		this.house5 = house5;
	}
	public String getHouse6() {
		return house6;
	}
	public void setHouse6(String house6) {
		this.house6 = house6;
	}
	public String getHouse7() {
		return house7;
	}
	public void setHouse7(String house7) {
		this.house7 = house7;
	}
	public String getStatetest() {
		return statetest;
	}
	public void setStatetest(String statetest) {
		this.statetest = statetest;
	}
	public String getStatetime() {
		return statetime;
	}
	public void setStatetime(String statetime) {
		this.statetime = statetime;
	}
	public String getProjectnum() {
		return projectnum;
	}
	public void setProjectnum(String projectnum) {
		this.projectnum = projectnum;
	}
	public String getEconcorpname() {
		return econcorpname;
	}
	public void setEconcorpname(String econcorpname) {
		this.econcorpname = econcorpname;
	}
	public String getEconcorpcode() {
		return econcorpcode;
	}
	public void setEconcorpcode(String econcorpcode) {
		this.econcorpcode = econcorpcode;
	}
	public String getDesigncorpname() {
		return designcorpname;
	}
	public void setDesigncorpname(String designcorpname) {
		this.designcorpname = designcorpname;
	}
	public String getDesigncorpcode() {
		return designcorpcode;
	}
	public void setDesigncorpcode(String designcorpcode) {
		this.designcorpcode = designcorpcode;
	}
	public String getConscorpname() {
		return conscorpname;
	}
	public void setConscorpname(String conscorpname) {
		this.conscorpname = conscorpname;
	}
	public String getConscorpcode() {
		return conscorpcode;
	}
	public void setConscorpcode(String conscorpcode) {
		this.conscorpcode = conscorpcode;
	}
	public String getSafetycerid() {
		return safetycerid;
	}
	public void setSafetycerid(String safetycerid) {
		this.safetycerid = safetycerid;
	}
	public String getSupercorpname() {
		return supercorpname;
	}
	public void setSupercorpname(String supercorpname) {
		this.supercorpname = supercorpname;
	}
	public String getSupercorpcode() {
		return supercorpcode;
	}
	public void setSupercorpcode(String supercorpcode) {
		this.supercorpcode = supercorpcode;
	}
	public String getConstructorname() {
		return constructorname;
	}
	public void setConstructorname(String constructorname) {
		this.constructorname = constructorname;
	}
	public String getCidcardtypenum() {
		return cidcardtypenum;
	}
	public void setCidcardtypenum(String cidcardtypenum) {
		this.cidcardtypenum = cidcardtypenum;
	}
	public String getConstructoridcard() {
		return constructoridcard;
	}
	public void setConstructoridcard(String constructoridcard) {
		this.constructoridcard = constructoridcard;
	}
	public String getSupervisionname() {
		return supervisionname;
	}
	public void setSupervisionname(String supervisionname) {
		this.supervisionname = supervisionname;
	}
	public String getSidcardtypenum() {
		return sidcardtypenum;
	}
	public void setSidcardtypenum(String sidcardtypenum) {
		this.sidcardtypenum = sidcardtypenum;
	}
	public String getSupervisionidcard() {
		return supervisionidcard;
	}
	public void setSupervisionidcard(String supervisionidcard) {
		this.supervisionidcard = supervisionidcard;
	}
	public String getConstructionperson() {
		return constructionperson;
	}
	public void setConstructionperson(String constructionperson) {
		this.constructionperson = constructionperson;
	}
	public String getEconcorpperson() {
		return econcorpperson;
	}
	public void setEconcorpperson(String econcorpperson) {
		this.econcorpperson = econcorpperson;
	}
	public String getDesigncorpperson() {
		return designcorpperson;
	}
	public void setDesigncorpperson(String designcorpperson) {
		this.designcorpperson = designcorpperson;
	}
	public String getSgprojectleadertel() {
		return sgprojectleadertel;
	}
	public void setSgprojectleadertel(String sgprojectleadertel) {
		this.sgprojectleadertel = sgprojectleadertel;
	}
	public String getJl_userid() {
		return jl_userid;
	}
	public void setJl_userid(String jl_userid) {
		this.jl_userid = jl_userid;
	}
	@Override
	public String toString() {
		return "ProjectManualVo [id=" + id + ", qymc=" + qymc + ", xmmc=" + xmmc + ", yszh=" + yszh + ", xmzl=" + xmzl
				+ ", qy=" + qy + ", sqrq=" + sqrq + ", slr=" + slr + ", slrq=" + slrq + ", bjgje=" + bjgje + ", xmscbh="
				+ xmscbh + ", jldwmc=" + jldwmc + ", xmzjxm=" + xmzjxm + ", xmzjlxdh=" + xmzjlxdh + ", frdb=" + frdb
				+ ", frdblxdh=" + frdblxdh + ", bgdz=" + bgdz + ", bwtr=" + bwtr + ", bwtrlxdh=" + bwtrlxdh + ", qyzz="
				+ qyzz + ", qyzzbh=" + qyzzbh + ", qyyyzzh=" + qyyyzzh + ", jbrdh=" + jbrdh + ", jbrmc=" + jbrmc
				+ ", sqzt=" + sqzt + ", yxbz=" + yxbz + ", zhgxz=" + zhgxz + ", zhgxsj=" + zhgxsj + ", ydqdfs=" + ydqdfs
				+ ", ydpzwh=" + ydpzwh + ", ydpzrq=" + ydpzrq + ", gcghxkz=" + gcghxkz + ", ydghxkz=" + ydghxkz
				+ ", tbr=" + tbr + ", tbrdh=" + tbrdh + ", ptzzmj=" + ptzzmj + ", bsgymj=" + bsgymj + ", symj=" + symj
				+ ", cfmj=" + cfmj + ", wjmj=" + wjmj + ", qtmj=" + qtmj + ", xzlmj=" + xzlmj + ", jhkgrq=" + jhkgrq
				+ ", jhjgrq=" + jhjgrq + ", gcsgxkz=" + gcsgxkz + ", xmxz=" + xmxz + ", xmzdmj=" + xmzdmj + ", xmjzmj="
				+ xmjzmj + ", slbh=" + slbh + ", funcid=" + funcid + ", pro_id=" + pro_id + ", tophouse=" + tophouse
				+ ", villa=" + villa + ", probreaks=" + probreaks + ", fid=" + fid + ", djtg=" + djtg + ", count1="
				+ count1 + ", count2=" + count2 + ", count3=" + count3 + ", count4=" + count4 + ", count5=" + count5
				+ ", telPerson=" + telPerson + ", tel=" + tel + ", tbrtel=" + tbrtel + ", house1=" + house1
				+ ", house2=" + house2 + ", house3=" + house3 + ", house4=" + house4 + ", house5=" + house5
				+ ", house6=" + house6 + ", house7=" + house7 + ", statetest=" + statetest + ", statetime=" + statetime
				+ ", projectnum=" + projectnum + ", datatype=" + datatype + ", econcorpname=" + econcorpname
				+ ", econcorpcode=" + econcorpcode + ", designcorpname=" + designcorpname + ", designcorpcode="
				+ designcorpcode + ", conscorpname=" + conscorpname + ", conscorpcode=" + conscorpcode
				+ ", safetycerid=" + safetycerid + ", supercorpname=" + supercorpname + ", supercorpcode="
				+ supercorpcode + ", constructorname=" + constructorname + ", cidcardtypenum=" + cidcardtypenum
				+ ", constructoridcard=" + constructoridcard + ", supervisionname=" + supervisionname
				+ ", sidcardtypenum=" + sidcardtypenum + ", supervisionidcard=" + supervisionidcard
				+ ", constructionperson=" + constructionperson + ", econcorpperson=" + econcorpperson
				+ ", designcorpperson=" + designcorpperson + ", sgprojectleadertel=" + sgprojectleadertel
				+ ", jl_userid=" + jl_userid + "]";
	}
  
  
  
  
  
   
  
  
  
  
  
  
}
