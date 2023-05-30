package com.sdkj.util.context;

import java.util.List;
import java.util.ArrayList;

public class PainationList {
	// 当前页
	private int currentPage;
	// 一共多少页
	private int totalPages;
	// 每页多少行
	private int pageRows = 10;
	// 一共多少行
	private int totalRows;
	// 开始页行数
	private int pageStartRow;
	// 结束页行数
	private int pageEndRow;
	// 是否有前一页
	private boolean hasPreviousPage;
	// 是否有后一页
	private boolean hasNextPage;
	// 要分页的List
	private List totalList;

	//初始化分页
	public void initPageBean(List totalList, int pageRows) {
		this.totalList = totalList;
		this.pageRows = pageRows;
		this.totalRows = totalList.size();
		this.currentPage = 1;
		if (totalRows % pageRows == 0) {
			totalPages = totalRows / pageRows;
			if (this.totalRows == 0) {
				 this.pageRows=0;
			}
		} else {
			totalPages = totalRows / pageRows + 1;
		}
		this.hasPreviousPage = false;
		if (currentPage == totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		this.pageStartRow = 1;
		if (totalRows < pageRows) {
			this.pageEndRow = totalRows;
		} else {
			this.pageEndRow = pageRows;
		}

	}
	// 当前页的方法
	public List  getCurrentPageList() {
		if (currentPage * pageRows < totalRows) {
			pageEndRow = currentPage * pageRows;
			pageStartRow = pageEndRow - pageRows;
		} else {
			pageEndRow = totalRows;
			pageStartRow = pageRows * (totalPages - 1);
		}
		List pageList = new ArrayList(pageEndRow - pageStartRow + 1);
		if (totalRows != 0) {
			for (int i = pageStartRow; i < pageEndRow; i++) {
				pageList.add(totalList.get(i));
			}
		}
		return pageList;
	}

	// 上一页的一个方法
	public List getPreviousPageList() {
		currentPage = currentPage - 1;
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
		List pageList = this.getCurrentPageList();
		return pageList;
	}

	// 下一页的一个方法
	public List getNextPageList() {
		currentPage = currentPage + 1;
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}

		List pageList = this.getCurrentPageList();
		return pageList;
	}
    //跳转到某一页
	public List AppointPageList(int currentPage) {
		this.currentPage = currentPage;
		if (currentPage > this.totalPages) {
			this.currentPage = this.totalPages;
		}
		if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			hasPreviousPage = false;
		}
		if (currentPage > 1) {
			this.hasPreviousPage = true;
		} else {
			this.hasPreviousPage = false;
		}
		if (this.currentPage < this.totalPages) {
			this.hasNextPage = true;
		} else {
			this.hasNextPage = false;
		}
		List pageList = this.getCurrentPageList();
		return pageList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public int getPageRows() {
		return pageRows = 1;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public List getTotalList() {
		return totalList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

}
