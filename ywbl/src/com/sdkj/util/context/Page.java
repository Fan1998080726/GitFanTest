package com.sdkj.util.context;

import java.util.List;
import java.util.Map;

public class Page {
    
	
	//当前索引值
    private int indexNumStart;
    //总条数
    private int countNum;
    //每页显示数
    private int  pageSize;
    //查询结束索引值
    private int indexNumEnd;
    //条件
    private String where;
    //查询相关参数
    private Map map;
    //存放结果集
    private List list;
    
	public int getIndexNumStart() {
		return indexNumStart;
	}
	public void setIndexNumStart(int indexNumStart) {
		this.indexNumStart = indexNumStart;
	}
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getIndexNumEnd() {
		return indexNumEnd;
	}
	public void setIndexNumEnd(int indexNumEnd) {
		this.indexNumEnd = indexNumEnd;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
    
    
	
}
