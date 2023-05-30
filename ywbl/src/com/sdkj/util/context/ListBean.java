package com.sdkj.util.context;

import java.util.List;



public class ListBean {
	private int intPageSize; 	//一页显示的记录数  
	private int intRowCount; 	//记录总数  
	private int intPageCount; 	//总页数  
	private int intPage; 		//待显示页码  
	private List list;			//数据列表
	
	public ListBean(String strPage, int intRowCount)
	{
		this.intRowCount = intRowCount;
		this.intPageSize = Config.IntPageSize;
		
		if(strPage == null || strPage.equals(""))
			intPage = 1;
		else
			intPage = Integer.parseInt(strPage); 
		if(intPage < 1) intPage = 1;
		
		intPageCount = (intRowCount + Config.IntPageSize - 1) / Config.IntPageSize;
		if(intPage > intPageCount) intPage = intPageCount;	
	}
	
	public ListBean()
	{
		intPageSize = Config.IntPageSize;		
	}

	public int getIntPage() {
		return intPage;
	}

	public void setIntPage(int intPage) {
		this.intPage = intPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getIntPageCount() {
		return intPageCount;
	}

	public void setIntPageCount(int intPageCount) {
		this.intPageCount = intPageCount;
	}

	public int getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(int intPageSize) {
		this.intPageSize = intPageSize;
	}

	public int getIntRowCount() {
		return intRowCount;
	}

	public void setIntRowCount(int intRowCount) {
		this.intRowCount = intRowCount;
	}
}
