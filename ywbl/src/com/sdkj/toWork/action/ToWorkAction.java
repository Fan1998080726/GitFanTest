package com.sdkj.toWork.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.Delay.service.DelayService;
import com.sdkj.feedback.service.FeedBackService;
import com.sdkj.matters.service.MattersService;
import com.sdkj.toWork.service.SendNoticeService;
import com.sdkj.toWork.vo.SendNoticeVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;
@Results(value = {
		 
 		@Result(name = "queryPendingWork", type = "dispatcher", location = "/jsp/system/toWork/queryPendingWork.jsp"),
 		@Result(name = "queryUserInfo", type = "dispatcher", location = "/jsp/system/toWork/queryUserInfo.jsp"),
 		@Result(name = "querySendNotices", type = "dispatcher", location = "/jsp/system/toWork/querySendNotices.jsp"),
 		@Result(name = "querySendUserNotices", type = "dispatcher", location = "/jsp/system/toWork/querySendUserNotices.jsp"),
 		@Result(name = "UpdateSendNoticesPage", type = "dispatcher", location = "/jsp/system/toWork/UpdateSendNoticesPage.jsp"),
 		@Result(name = "querySendNoticesByID", type = "dispatcher", location = "/jsp/system/toWork/querySendNoticesByID.jsp"),
 
		 

})
@Action("toWork")
public class ToWorkAction {

	Pagination pagination = new Pagination();
	// struts2中response request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	
	@Autowired
	private MattersService mattersService;
	
	@Autowired
	private DelayService  delayService;

	@Autowired
	private FeedBackService feedBackService;
	
	@LogAnno(operateType="查看待工作内容")
	public String queryPendingWork() {
		
		try {
			/**
			 * 延期申请数
			 */
			Integer  DelayCount = feedBackService.queryRequestDelayCount("","01");
			request.setAttribute("DelayCount", DelayCount);
			
			/**
			 * 核验申请数
			 */
			Integer  heyanCount = mattersService.queryRequestHeyanCount("","01");
			request.setAttribute("heyanCount", heyanCount);
			
			/**
			 * 核验申请数
			 */
			Integer  feedbackCount = mattersService.queryFeedbackCountCount("","01");
			request.setAttribute("feedbackCount", feedbackCount);
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryPendingWork";
	}
	
	@LogAnno(operateType="查看通讯录")
	public  String queryUserInfo() {
		try {
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			String personname = request.getParameter("personname");
			personname = Util.nullToString(personname);
			System.out.println("personname===="+personname);
			searchVo = new SearchVo();
			searchVo.setPrjname(prjname);
			searchVo.setUsername(personname);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("prjname", prjname);
			List  	list = mattersService.queryUserInfo(searchVo, pagination);
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"toWork!queryUserInfo.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			request.setAttribute("personname", personname);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryUserInfo";
	}
	
	@Autowired
	private SendNoticeService noticeService;
	@LogAnno(operateType="管理端进入通知公告管理页面")
	public  String querySendNotices() {
		try {
			String titlecontent = request.getParameter("titlecontent");
			titlecontent = Util.nullToString(titlecontent);
			String personname = request.getParameter("personname");
			personname = Util.nullToString(personname);
			
			searchVo = new SearchVo();
			searchVo.setTitlecontent(titlecontent);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("titlecontent", titlecontent);
			List  	list = noticeService.querySendNotices(searchVo, pagination);
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"toWork!querySendNotices.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("titlecontent", titlecontent);
			request.setAttribute("personname", personname);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "querySendNotices";
	}
	
	
	
	@LogAnno(operateType="处室通知公告管理页面")
	public  String querySendUserNotices() {
		try {
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			String personname = request.getParameter("personname");
			personname = Util.nullToString(personname);
			String titlecontent = request.getParameter("titlecontent");
			titlecontent = Util.nullToString(titlecontent);
			
			
			System.out.println("titlecontent===="+titlecontent);
			
			
			
			searchVo = new SearchVo();
			searchVo.setTitlecontent(titlecontent);
			searchVo.setUsername(personname);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("titlecontent", titlecontent);
			List  	list = noticeService.querySendUserNotices(searchVo, pagination);
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
					pagination.getTotalCount(),"toWork!querySendUserNotices.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("titlecontent", titlecontent);
			request.setAttribute("personname", personname);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "querySendUserNotices";
	}
	
	
	
	
	private SendNoticeVo noticeVo;
	
	
	
	public SendNoticeVo getNoticeVo() {
		return noticeVo;
	}

	public void setNoticeVo(SendNoticeVo noticeVo) {
		this.noticeVo = noticeVo;
	}

	@LogAnno(operateType="进入通知公告编辑页面")
	public  String  UpdateSendNoticesPage() {
		
		try {
			if(noticeVo==null) {
				noticeVo  = new SendNoticeVo();
				noticeVo.setFid(CommonFunc.getGUID());
			}else {
				noticeVo =  noticeService.queryNoticeById(noticeVo.getId());

				List companyfileList = mattersService.getUpfile(noticeVo.getFid(), "notice");
				request.setAttribute("companyfileList", companyfileList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "UpdateSendNoticesPage";
	}
	
	@LogAnno(operateType="查看通知公告")
	public  String  querySendNoticesByID() {
		try {
				noticeVo =  noticeService.queryNoticeById(noticeVo.getId());
				noticeService.updateNoticeState(noticeVo.getId());
				List companyfileList = mattersService.getUpfile(noticeVo.getFid(), "notice");
				request.setAttribute("companyfileList", companyfileList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "querySendNoticesByID";
	}
	
	
	
	
	
	
	
	@LogAnno(operateType="保存通知公告信息")
	public void SaveSendNotice() {
		
		try {
			System.out.println("noticeVo==="+noticeVo);
			String[] fileName = request.getParameterValues("fileName");
			String[] filePath = request.getParameterValues("filePath");
			String[] category = request.getParameterValues("category");
			String[] saomiaodate = request.getParameterValues("saomiaodate");
			String[] type = request.getParameterValues("type");
			// 附件删除
			mattersService.delFileByReport(noticeVo.getFid(),"notice");
			// 附件添加
			
			
			
			String  state=  request.getParameter("state");
			state= Util.nullToString(state);
			noticeVo.setState(state);
			if (null != fileName && 0 != fileName.length)
				mattersService.addReportFile_new(fileName, filePath, type, saomiaodate, noticeVo.getFid(), "prove", "11");
	
			noticeService.SaveSendNotice(noticeVo);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
	}
	
	
	/**
	 * 删除未发送的通知公告
	 */
	public  void SendNoticesDel() {
		
		try {
			String  id = request.getParameter("id");
			id  = Util.nullToString(id);
			noticeService.SendNoticeDel(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  撤销通知公告
	 */
	public  void UndoNoticesState() {
		
		try {
			String  id = request.getParameter("id");
			id  = Util.nullToString(id);
			noticeService.UndoNoticesState(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private SearchVo searchVo;
	
	public SearchVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}

	
	
}
