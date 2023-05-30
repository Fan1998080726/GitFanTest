/*
 * @(#) Paginater.java Sep 6, 2005
 *
 * Copyright 2005 Trust in Belief, Inc. All rights reserved.
 * Trust in Belief PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sdkj.util.context;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 处理分页
 *
 * @author 


 * @value
 */

public class Paginater {
	public Log log;

	public Paginater()
	{
		log = LogFactory.getLog(getClass());
	}

	  public Pagination initPagination(HttpServletRequest request)
	    {
	        Pagination pagination = (Pagination)request.getAttribute("PAGINATION_PAGER");
	        if(pagination != null)
	            return pagination;
	        int pageSize = CommonConstant.PAGESIZE;
	        String PGSZIE = request.getParameter("PGSZIE");
	        if(PGSZIE == null){
	        	PGSZIE =(String)request.getAttribute("PGSZIE");
	        }
	        //////System.out.println("============PGSZIE==============" + PGSZIE);
	        int currentPageNo = 1;
	        int totalCount = 0;
	        pagination = new Pagination();
	        try
	        {
	            String flag = request.getParameter("PAGINATOR_FLAG");
	            String pageNoStr = request.getParameter("PAGINATION_CURRENT_PAGE_NO");
	            if(pageNoStr == null){
	            	pageNoStr =(String)request.getAttribute("PAGINATION_CURRENT_PAGE_NO");
		        }
	            currentPageNo = pageNoStr == null ? currentPageNo : Integer.parseInt(pageNoStr);
	            //////System.out.println("============currentPageNo==============" + currentPageNo);
	            String totalCountStr = request.getParameter("PAGINATION_TOTAL_COUNT");
	            if(totalCountStr == null){
	            	totalCountStr =(String)request.getAttribute("PAGINATION_TOTAL_COUNT");
		        }
	            totalCount = totalCountStr == null ? totalCount : Integer.parseInt(totalCountStr);
	           // //////System.out.println("============totalCount==============" + totalCount);
	            if("false".equalsIgnoreCase(flag))
	                currentPageNo = 1;
	        }
	        catch(NumberFormatException nfex)
	        {
	            if(log.isInfoEnabled())
	                log.info("分页参数不是有效数字格式，采用默认值！");
	        }
	        try
	        {
	            pageSize = Integer.parseInt(PGSZIE != null ? PGSZIE : ContextBeanFactory.getContextBean().getPage());
	        }
	        catch(Exception ex)
	        {
	            log.debug("分页参数采用默认值！");
	        }
	        pagination.setTotalCount(totalCount);
	        pagination.setCurrentPageNo(currentPageNo);
	        pagination.setPageSize(pageSize);
	        request.setAttribute("PAGINATION_PAGER", pagination);
	        return pagination;
	    }
}
