/*
 * @(#) Pagination.java 
 *
 * Copyright 2005 Trust in Belief, Inc. All rights reserved.
 * Trust in Belief PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package  com.sdkj.util.context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页VO 
 *
 */

public class Pagination {

    private static final long serialVersionUID = 1L;
//    public static final String PAGER_KEY = "PAGINATION_PAGER";
//    public static final String PAGE_SIZE_KEY = "PAGINATION_PAGE_SIZE";
//    public static final String PAGE_COUNT_KEY = "PAGINATION_PAGE_COUNT";
//    public static final String CURRENT_PAGE_NO_KEY = "PAGINATION_CURRENT_PAGE_NO";
//    public static final String TOTAL_COUNT_KEY = "PAGINATION_TOTAL_COUNT";
//    public static final String PAGINATOR_FLAG = "PAGINATOR_FLAG";
    private int pageSize;
    private int currentPageNo;
    private int totalCount;
	private String url;
	private Map hiddenMap;
	private Map hiddenArrayMap;//20160515用于查询项目工程页面使用
	private int size;
	
	
	/**
	 * 
	 * @param pageSize  每页多少条
	 * @param currentPageNo 当前页数
	 * @param totalCount 总条数
	 * @param url 分页跳转地址
	 * @param hiddenMap   分页传递参数
	 * @param hiddenArrayMap   
	 * @param size   总数
	 */
	public  Pagination(int pageSize, int currentPageNo, int totalCount, String url, Map hiddenMap, 
			int size) {
		super();
		this.pageSize = pageSize;
		this.currentPageNo = currentPageNo;
		this.totalCount = totalCount;
		this.url = url;
		this.hiddenMap = hiddenMap;
		this.size = size;
	}

	public Pagination()
    {
        pageSize = 15;
        currentPageNo = 0;
        this.totalCount = 0;
    }

    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCurrentPageNo()
    {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo)
    {
        this.currentPageNo = currentPageNo;
    }

//    public int getPageCount()
//    {
//        if(pageSize <= 0)
//            return 0;
//        if(totalCount % pageSize == 0)
//            return totalCount / pageSize;
//        else
//            return totalCount / pageSize + 1;
//    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public int getFirstRownum()
    {
	//        return (currentPageNo - 1) * getPageSize();
        return currentPageNo;
    }

//    public boolean equals(Object obj)
//    {
//        if(obj == null)
//            return false;
//        if(!(obj instanceof Pagination))
//            return false;
//        Pagination pagination = (Pagination)obj;
//        if(pagination.getTotalCount() != totalCount)
//            return false;
//        if(pagination.getCurrentPageNo() != currentPageNo)
//            return false;
//        return pagination.getPageSize() == pageSize;
//    }
//    
//    public String getPageInfo(HttpServletRequest request)
//    {
//    	String returnStr = "";
//    	String path = request.getContextPath();
//		TblSYS_LOGINMSG user1 = (TblSYS_LOGINMSG) request.getSession().getAttribute(TblSYS_LOGINMSG.SESSION_KEY);
//		//String stylePath1 = path + user1.getStylePath();
//		String stylePath1 = path +"/nresources/telcom";
//		Pagination thePage = (Pagination) request.getAttribute(Pagination.PAGER_KEY);
//
//		if (thePage == null){
//			thePage = new Pagination(0);
//		}
//
//		int pageSize = thePage.getPageSize();
//
//		int totalCount = thePage.getTotalCount();
//
//		int pageCount = thePage.getPageCount();
//
//		int curPage = thePage.getCurrentPageNo();
//
//		int prePage = 0;
//		int nextPage = 0;
//		prePage = curPage - 1;
//		nextPage = curPage + 1;
//		prePage = prePage <= 0 ? -1 : prePage;
//		nextPage = nextPage > pageCount ? -1 : nextPage;
//		
//		returnStr = "<div id=\"LeftBar\" align=\"right\" style=\"width:90%\"><nobr>" ;
//		if ( prePage > 0 )
//		{
//			returnStr += " <a href=\"javascript:void(0)\" onclick=\"javascript:gotoPage(1);return false;\"> "
//			           + " <img alt=\"第一页\" align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-first.gif\"/></a>&nbsp; "
//		               + " <a href=\"javascript:void(0)\" onClick=\"javascript:gotoPage("+prePage+");return false;\"> "
//			           + " <img alt=\"上一页\" align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-prev.gif\"/></a>";
//		} 
//		else 
//		{
//			returnStr += " <img  align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-first-disabled.gif\"/>&nbsp;"
//			           + " <img  align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-prev-disabled.gif\"/>";
//		}
//		returnStr += " <select id=\""+CURRENT_PAGE_NO_KEY+"	name=\""+CURRENT_PAGE_NO_KEY+"\" style=\"width=50\" onChange=\"gotoPage(this.value);\">";
//		for (int i = 0; i < pageCount; i++)
//		{
//			returnStr += " <option value=\""+(i+1)+"\"";
//			if(i+1==curPage)
//			{
//				returnStr += " selected ";
//			}
//			returnStr += " >"+(i+1)+"</option>";
//		}
//		returnStr += " </select> 共&nbsp;<b>"+pageCount+"</b>&nbsp;页&nbsp;&nbsp;";
//		if (nextPage > 0)
//		{
//			returnStr += "<a href=\"javascript:void(0)\" onClick=\"javascript:gotoPage("+nextPage+");return false;\">"
//			           + " <img alt=\"下一页\" align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-next.gif\"/></a>&nbsp;"
//			           + " <a href=\"javascript:void(0)\" onclick=\"javascript:gotoPage(<%=pageCount%>);return false;\">"
//			           + " <img  alt=\"最后一页\" align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-last.gif\"/></a>";
//		}
//		else
//		{
//			returnStr += "<img  align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-next-disabled.gif\"/>&nbsp;"
//					   + "<img  align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/page-last-disabled.gif\"/>";
//		}
//		returnStr += "<a href=\"javascript:void(0)\" onclick=\"javascript:reloadFirstPage();\">"
//			       + " <img align=\"middle\" border=\"0\" src=\""+stylePath1+"/images/load.png\"/> "
//			       + "</a>"
//			       + "<input type=\"hidden\" name=\""+PAGINATOR_FLAG+"\" value=\"false\">"
//			       + " &nbsp;共&nbsp;<b>"+totalCount+"</b>&nbsp;条&nbsp;|&nbsp;每页&nbsp;<b>"+pageSize+"</b>&nbsp;条&nbsp;"
//				   + " </nobr>"
//				   + " </div>";
//		return returnStr;
//    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
	public Map getHiddenMap() {
		return hiddenMap;
	}

	public void setHiddenMap(Map hiddenMap) {
		this.hiddenMap = hiddenMap;
	}

	public Map getHiddenArrayMap() {
		return hiddenArrayMap;
	}

	public void setHiddenArrayMap(Map hiddenArrayMap) {
		this.hiddenArrayMap = hiddenArrayMap;
	}

	
}
