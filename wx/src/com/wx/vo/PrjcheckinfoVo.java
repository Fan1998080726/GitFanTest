package com.wx.vo;
/*工程项目检查表*/
public class PrjcheckinfoVo {
	public String id;//主键
	public String state;//状态
	public String Prjname;//项目名称
	public String Prjadress;//地点
	public String BuildCorpName;//建设单位
	public String Prjtype;//类型
	public String ConsCorpName;//施工单位
	public String DesignCorp;//设计单位
	public String SuperCorpName;//监理单位
	public String Planmancount;//计划复工人员数量
	public String Yiqumancount;//其中疫区人员
	public String Arrivemancount;//目前到岗人数
	public String ServerHavecount;//“七有”准备
	public String tenmustcount;//“十必须”措施
	public String Infomanagecount;//信息管理“三到位”
	public String ManThreearrive;//人员“三严禁”
	public String BeginBuildop;//是否符合开工条件
	public String BuilderLicenceOp;//施工许可
	public String Tbtenderop;//招投标（直发包备案）
	public String Contractop;//合同及履约
	public String Peasantworkerop;//农民工管理四项制度
	public String ManArriveop;//人员到岗履职
	public String ConstructionSiteOp;//施工现场管理
	public String otherviolateop;//其他违法违规行为
	public String beginbuilddate;//计划开工日期
	public String rebeginbuilddate;//计划复工日期
	public String mark;//备注
	public String checkdate;//检查日期
	public String inittime;//初始时间
	public String lastupdatetime;//最后修改时间
	public String dept_name;
	
	public String PrjNameBeginDate;//项目开工时间
	public String CurrentDifficulty;//存在困难及需要政府解决问题
	public String BuildCorpNameLxr;//建设单位
	public String ConsCorpNameLxr;//施工单位
	public String SuperCorpNameLxr;//监理单位
	public String BuildCorpNameIphone;//建设单位
	public String ConsCorpNameIphone;//施工单位
	public String SuperCorpNameIphone;//监理单位
	
	public String StateProvincePrjOp;//是否是国家或省重点项目
	public String ConstructionSiteSystem;//施工现场监管平台使用情况
	
	public String subcount;//检查记录表对于数据
	public String personcount;//工程下人员数据
	public String orterCorp;//
	
	public String sysManagerop;
	
	
	
	
	
	public String stateMaxBuildingProject;//国家重点工程、重大项目、重要民生工程
	public String stateMaxProject;//省百大项目
	public String commonBuiding;//一般民生工程、经济类建设工程
	public String stateOther;//发地产等其他类
	
	public Integer login_id;
	 
	
	public Integer getLogin_id() {
		return login_id;
	}
	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}
	public String getStateMaxBuildingProject() {
		return stateMaxBuildingProject;
	}
	public void setStateMaxBuildingProject(String stateMaxBuildingProject) {
		this.stateMaxBuildingProject = stateMaxBuildingProject;
	}
	public String getStateMaxProject() {
		return stateMaxProject;
	}
	public void setStateMaxProject(String stateMaxProject) {
		this.stateMaxProject = stateMaxProject;
	}
	public String getCommonBuiding() {
		return commonBuiding;
	}
	public void setCommonBuiding(String commonBuiding) {
		this.commonBuiding = commonBuiding;
	}
	public String getStateOther() {
		return stateOther;
	}
	public void setStateOther(String stateOther) {
		this.stateOther = stateOther;
	}
	public String getPersoncount() {
		return personcount;
	}
	public void setPersoncount(String personcount) {
		this.personcount = personcount;
	}
	public String getSysManagerop() {
		return sysManagerop;
	}
	public void setSysManagerop(String sysManagerop) {
		this.sysManagerop = sysManagerop;
	}
	public String getOrterCorp() {
		return orterCorp;
	}
	public void setOrterCorp(String orterCorp) {
		this.orterCorp = orterCorp;
	}
	public String getSubcount() {
		return subcount;
	}
	public void setSubcount(String subcount) {
		this.subcount = subcount;
	}
	public String getStateProvincePrjOp() {
		return StateProvincePrjOp;
	}
	public void setStateProvincePrjOp(String stateProvincePrjOp) {
		StateProvincePrjOp = stateProvincePrjOp;
	}
	public String getConstructionSiteSystem() {
		return ConstructionSiteSystem;
	}
	public void setConstructionSiteSystem(String constructionSiteSystem) {
		ConstructionSiteSystem = constructionSiteSystem;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
public void setPrjadress(String Prjadress) {
this.Prjadress = Prjadress;
}
public String getPrjadress() {
return Prjadress;
}
public void setBuildCorpName(String BuildCorpName) {
this.BuildCorpName = BuildCorpName;
}
public String getBuildCorpName() {
return BuildCorpName;
}
public void setPrjtype(String Prjtype) {
this.Prjtype = Prjtype;
}
public String getPrjtype() {
return Prjtype;
}
public void setConsCorpName(String ConsCorpName) {
this.ConsCorpName = ConsCorpName;
}
public String getConsCorpName() {
return ConsCorpName;
}
public void setDesignCorp(String DesignCorp) {
this.DesignCorp = DesignCorp;
}
public String getDesignCorp() {
return DesignCorp;
}
public void setSuperCorpName(String SuperCorpName) {
this.SuperCorpName = SuperCorpName;
}
public String getSuperCorpName() {
return SuperCorpName;
}
public void setPlanmancount(String Planmancount) {
this.Planmancount = Planmancount;
}
public String getPlanmancount() {
return Planmancount;
}
public void setYiqumancount(String Yiqumancount) {
this.Yiqumancount = Yiqumancount;
}
public String getYiqumancount() {
return Yiqumancount;
}
public void setArrivemancount(String Arrivemancount) {
this.Arrivemancount = Arrivemancount;
}
public String getArrivemancount() {
return Arrivemancount;
}
public void setServerHavecount(String ServerHavecount) {
this.ServerHavecount = ServerHavecount;
}
public String getServerHavecount() {
return ServerHavecount;
}
public void setTenmustcount(String tenmustcount) {
this.tenmustcount = tenmustcount;
}
public String getTenmustcount() {
return tenmustcount;
}
public void setInfomanagecount(String Infomanagecount) {
this.Infomanagecount = Infomanagecount;
}
public String getInfomanagecount() {
return Infomanagecount;
}
public void setManThreearrive(String ManThreearrive) {
this.ManThreearrive = ManThreearrive;
}
public String getManThreearrive() {
return ManThreearrive;
}
public void setBeginBuildop(String BeginBuildop) {
this.BeginBuildop = BeginBuildop;
}
public String getBeginBuildop() {
return BeginBuildop;
}
public void setBuilderLicenceOp(String BuilderLicenceOp) {
this.BuilderLicenceOp = BuilderLicenceOp;
}
public String getBuilderLicenceOp() {
return BuilderLicenceOp;
}

public String getTbtenderop() {
	return Tbtenderop;
}
public void setTbtenderop(String tbtenderop) {
	Tbtenderop = tbtenderop;
}
public void setContractop(String Contractop) {
this.Contractop = Contractop;
}
public String getContractop() {
return Contractop;
}
public void setPeasantworkerop(String Peasantworkerop) {
this.Peasantworkerop = Peasantworkerop;
}
public String getPeasantworkerop() {
return Peasantworkerop;
}
public void setManArriveop(String ManArriveop) {
this.ManArriveop = ManArriveop;
}
public String getManArriveop() {
return ManArriveop;
}
public void setConstructionSiteOp(String ConstructionSiteOp) {
this.ConstructionSiteOp = ConstructionSiteOp;
}
public String getConstructionSiteOp() {
return ConstructionSiteOp;
}
public void setOtherviolateop(String otherviolateop) {
this.otherviolateop = otherviolateop;
}
public String getOtherviolateop() {
return otherviolateop;
}
public void setBeginbuilddate(String beginbuilddate) {
this.beginbuilddate = beginbuilddate;
}
public String getBeginbuilddate() {
return beginbuilddate;
}
public void setRebeginbuilddate(String rebeginbuilddate) {
this.rebeginbuilddate = rebeginbuilddate;
}
public String getRebeginbuilddate() {
return rebeginbuilddate;
}
public void setMark(String mark) {
this.mark = mark;
}
public String getMark() {
return mark;
}
public void setCheckdate(String checkdate) {
this.checkdate = checkdate;
}
public String getCheckdate() {
return checkdate;
}
public void setInittime(String inittime) {
this.inittime = inittime;
}
public String getInittime() {
return inittime;
}
public void setLastupdatetime(String lastupdatetime) {
this.lastupdatetime = lastupdatetime;
}
public String getLastupdatetime() {
return lastupdatetime;
}
public String getPrjname() {
	return Prjname;
}
public void setPrjname(String prjname) {
	Prjname = prjname;
}
public String getPrjNameBeginDate() {
	return PrjNameBeginDate;
}
public void setPrjNameBeginDate(String prjNameBeginDate) {
	PrjNameBeginDate = prjNameBeginDate;
}
public String getCurrentDifficulty() {
	return CurrentDifficulty;
}
public void setCurrentDifficulty(String currentDifficulty) {
	CurrentDifficulty = currentDifficulty;
}
public String getBuildCorpNameLxr() {
	return BuildCorpNameLxr;
}
public void setBuildCorpNameLxr(String buildCorpNameLxr) {
	BuildCorpNameLxr = buildCorpNameLxr;
}
public String getConsCorpNameLxr() {
	return ConsCorpNameLxr;
}
public void setConsCorpNameLxr(String consCorpNameLxr) {
	ConsCorpNameLxr = consCorpNameLxr;
}
public String getSuperCorpNameLxr() {
	return SuperCorpNameLxr;
}
public void setSuperCorpNameLxr(String superCorpNameLxr) {
	SuperCorpNameLxr = superCorpNameLxr;
}
public String getBuildCorpNameIphone() {
	return BuildCorpNameIphone;
}
public void setBuildCorpNameIphone(String buildCorpNameIphone) {
	BuildCorpNameIphone = buildCorpNameIphone;
}
public String getConsCorpNameIphone() {
	return ConsCorpNameIphone;
}
public void setConsCorpNameIphone(String consCorpNameIphone) {
	ConsCorpNameIphone = consCorpNameIphone;
}
public String getSuperCorpNameIphone() {
	return SuperCorpNameIphone;
}
public void setSuperCorpNameIphone(String superCorpNameIphone) {
	SuperCorpNameIphone = superCorpNameIphone;
}
@Override
public String toString() {
	return "PrjcheckinfoVo [id=" + id + ", state=" + state + ", Prjname=" + Prjname + ", Prjadress=" + Prjadress
			+ ", BuildCorpName=" + BuildCorpName + ", Prjtype=" + Prjtype + ", ConsCorpName=" + ConsCorpName
			+ ", DesignCorp=" + DesignCorp + ", SuperCorpName=" + SuperCorpName + ", Planmancount=" + Planmancount
			+ ", Yiqumancount=" + Yiqumancount + ", Arrivemancount=" + Arrivemancount + ", ServerHavecount="
			+ ServerHavecount + ", tenmustcount=" + tenmustcount + ", Infomanagecount=" + Infomanagecount
			+ ", ManThreearrive=" + ManThreearrive + ", BeginBuildop=" + BeginBuildop + ", BuilderLicenceOp="
			+ BuilderLicenceOp + ", Tbtenderop=" + Tbtenderop + ", Contractop=" + Contractop + ", Peasantworkerop="
			+ Peasantworkerop + ", ManArriveop=" + ManArriveop + ", ConstructionSiteOp=" + ConstructionSiteOp
			+ ", otherviolateop=" + otherviolateop + ", beginbuilddate=" + beginbuilddate + ", rebeginbuilddate="
			+ rebeginbuilddate + ", mark=" + mark + ", checkdate=" + checkdate + ", inittime=" + inittime
			+ ", lastupdatetime=" + lastupdatetime + ", dept_name=" + dept_name + ", PrjNameBeginDate="
			+ PrjNameBeginDate + ", CurrentDifficulty=" + CurrentDifficulty + ", BuildCorpNameLxr=" + BuildCorpNameLxr
			+ ", ConsCorpNameLxr=" + ConsCorpNameLxr + ", SuperCorpNameLxr=" + SuperCorpNameLxr
			+ ", BuildCorpNameIphone=" + BuildCorpNameIphone + ", ConsCorpNameIphone=" + ConsCorpNameIphone
			+ ", SuperCorpNameIphone=" + SuperCorpNameIphone + ", StateProvincePrjOp=" + StateProvincePrjOp
			+ ", ConstructionSiteSystem=" + ConstructionSiteSystem + ", subcount=" + subcount + ", personcount="
			+ personcount + ", orterCorp=" + orterCorp + ", sysManagerop=" + sysManagerop + ", stateMaxBuildingProject="
			+ stateMaxBuildingProject + ", stateMaxProject=" + stateMaxProject + ", commonBuiding=" + commonBuiding
			+ ", stateOther=" + stateOther + ", login_id=" + login_id + "]";
}

}

