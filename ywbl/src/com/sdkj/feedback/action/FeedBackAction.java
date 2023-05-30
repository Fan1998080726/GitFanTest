package com.sdkj.feedback.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *事项反馈
 * @author Administrator
 *
 */

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
import com.sdkj.TimeScore.service.OverMattersScoreService;
import com.sdkj.TimeScore.vo.EvaluationTableVo;
import com.sdkj.TimeScore.vo.StandardVo;
import com.sdkj.feedback.service.FeedBackService;
import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.matters.action.MattersAction;
import com.sdkj.matters.service.MattersService;
import com.sdkj.matters.vo.CooperateUnitsVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.matters.vo.ParmsVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.ExcelExportUtil_CreditView;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;

@Results(value = {
		@Result(name = "queryUserFeedbackMatters", type = "dispatcher", location = "/jsp/system/feedback/queryUserFeedbackMatters.jsp"),
		@Result(name = "queryAdminFeedbackMatters", type = "dispatcher", location = "/jsp/system/feedback/queryAdminFeedbackMatters.jsp"),
		@Result(name = "queryUserFeedbackMattersById", type = "dispatcher", location = "/jsp/system/feedback/queryUserFeedbackMattersById.jsp"),
		@Result(name = "queryPhotoFiles", type = "dispatcher", location = "/jsp/system/feedback/queryPhotoFiles.jsp"),
		@Result(name = "updateUserFeedbackMattersPage", type = "dispatcher", location = "/jsp/system/feedback/updateUserFeedbackMattersPage.jsp"),
		@Result(name = "queryFeedbackById", type = "dispatcher", location = "/jsp/system/feedback/queryFeedbackById.jsp"),
		@Result(name = "againFeedback", type = "dispatcher", location = "/jsp/system/feedback/againFeedback.jsp"),
		@Result(name = "queryAllFeedBack", type = "dispatcher", location = "/jsp/system/feedback/queryAllFeedBack.jsp"),
		@Result(name = "QueryAllFeedbackById", type = "dispatcher", location = "/jsp/system/feedback/QueryAllFeedbackById.jsp"),
		@Result(name = "queryFeedback", type = "dispatcher", location = "/jsp/system/feedback/queryFeedback.jsp"),

})
@Action("feedback")
public class FeedBackAction {

	@Autowired
	private MattersService mattersService;

	@Autowired
	private FeedBackService feedBackService;
	
	
	@Autowired
	private OverMattersScoreService mattersScoreService;
	
	
	

	/**
	 * 进入反馈页面
	 * 
	 * @return
	 */
	@LogAnno(operateType="进入反馈页面")
	public String queryUserFeedbackMatters() {
		String id = Util.nullToString(request.getParameter("id"));
		String cid = Util.nullToString(request.getParameter("cid"));
		String cstate = Util.nullToString(request.getParameter("cstate"));
		try {
			searchVo = new SearchVo();
			List<ParmsVo> parmList = mattersService.queryPermissions(searchVo);
			request.setAttribute("parmList", parmList);
			searchVo.setCid(cid);
			mattersVo = mattersService.queryMatterById(id);
			/**
			 * checkbox选中
			 */
			parmList = MattersAction.getList(parmList, mattersVo.getSource_unit(), mattersVo.getCooperate_unit());

			List companyfileList = mattersService.getUpfile(mattersVo.getFid(), "remark");
			request.setAttribute("companyfileList", companyfileList);

		
			/**
			 * 查看反馈信息
			 */

			List<FeedbackVo> feedList = feedBackService.queryFeedBackList(mattersVo.getId());
			for (FeedbackVo feedbackVo : feedList) {
				System.out.println("Time ==== "+feedbackVo.getInittime());
			}
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//	 		 String  worktime =;
//	 		 System.out.println("worktime="+worktime);
//			Date date = sdf.parse(worktime);
	    	int hoursState  = CommonFunc.getHours(feedList.get(0).getInittime());
//			System.out.println("hoursState===="+hoursState);
	    	request.setAttribute("hoursState", hoursState);
	    	
	    	
	    	
			request.setAttribute("feedList", feedList);
			/**
			 * 查看反馈的图片
			 */
			List FeedbackList = mattersService.getUpfiles(mattersVo.getId(), "feedbackFile");
			request.setAttribute("FeedbackList", FeedbackList);
			/**
			 * 当前用户的事项是否进行延期申请
			 */
			int delayState = feedBackService.queryRequestDelayCount(id,"01");
			request.setAttribute("delayState", delayState);
			/**
			 * 查看签收信息
			 */
			List SiginList = mattersService.querySigInList(mattersVo.getMatters_id());
			request.setAttribute("SiginList", SiginList);
			
			request.setAttribute("cstate", cstate);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "queryUserFeedbackMatters";
	}
	
	
	public  String  queryFeedback() {
		
		try {
			searchVo = new SearchVo();
			String queryFeedback   = request.getParameter("queryFeedback");
			queryFeedback = Util.nullToString(queryFeedback);
			List list = feedBackService.queryFeedback(searchVo);
			request.setAttribute("queryFeedback", queryFeedback);
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryFeedback";
	}
	
	/**
	 * 管理部门进行事项核验
	 * @return
	 */
	@LogAnno(operateType="事项核验审核页面")
	public String queryAdminFeedbackMatters() {
		String id = Util.nullToString(request.getParameter("id"));
		String cid = Util.nullToString(request.getParameter("cid"));
		String cstate = Util.nullToString(request.getParameter("cstate"));
		try {
			searchVo = new SearchVo();
			List<ParmsVo> parmList = mattersService.queryPermissions(searchVo);
			request.setAttribute("parmList", parmList);
			searchVo.setCid(cid);
			mattersVo = mattersService.queryMatterById(id);
			/**
			 * checkbox选中
			 */
			parmList = MattersAction.getList(parmList, mattersVo.getSource_unit(), mattersVo.getCooperate_unit());
			
			List companyfileList = mattersService.getUpfile(mattersVo.getFid(), "remark");
			request.setAttribute("companyfileList", companyfileList);
			
			/**
			 * 首次查看事项变更事项状态为02 首次反馈也是一样
			 */
			if (cstate.equals("01")) {
				mattersService.UpdateCooperateState(searchVo);
			}
			/**
			 * 查看反馈信息
			 */
			
			List<FeedbackVo> feedList = feedBackService.queryFeedBackList(mattersVo.getId());
			request.setAttribute("feedList", feedList);
			/**
			 * 查看反馈的图片
			 */
			List FeedbackList = mattersService.getUpfiles(mattersVo.getId(), "feedbackFile");
			request.setAttribute("FeedbackList", FeedbackList);
			/**
			 * 当前用户的事项是否进行延期申请
			 */
			int delayState = feedBackService.queryRequestDelayCount(id,"01");
			request.setAttribute("delayState", delayState);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "queryAdminFeedbackMatters";
	}
	
	
	
	/**
	 * 进入反馈页面
	 * 
	 * @return
	 */
	@LogAnno(operateType="事项查看")
	public String queryUserFeedbackMattersById() {
		String id = Util.nullToString(request.getParameter("id"));
		String cid = Util.nullToString(request.getParameter("cid"));
		String cstate = Util.nullToString(request.getParameter("cstate"));
		try {
			searchVo = new SearchVo();
			List<ParmsVo> parmList = mattersService.queryPermissions(searchVo);
			request.setAttribute("parmList", parmList);
			searchVo.setCid(cid);
			mattersVo = mattersService.queryMatterById(id);
			/**
			 * checkbox选中
			 */
			parmList = MattersAction.getList(parmList, mattersVo.getSource_unit(), mattersVo.getCooperate_unit());
			
			List companyfileList = mattersService.getUpfile(mattersVo.getFid(), "remark");
			request.setAttribute("companyfileList", companyfileList);
			
			/**
			 * 首次查看事项变更事项状态为02 首次反馈也是一样
			 */
			if (cstate.equals("01")) {
				mattersService.UpdateCooperateState(searchVo);
			}
			/**
			 * 查看反馈信息
			 */
			
			List<FeedbackVo> feedList = feedBackService.queryFeedBackList(mattersVo.getId());
			request.setAttribute("feedList", feedList);
			/**
			 * 查看反馈的图片
			 */
			List FeedbackList = mattersService.getUpfiles(mattersVo.getId(), "feedbackFile");
			request.setAttribute("FeedbackList", FeedbackList);
			/**
			 * 查看签收信息
			 */
			List SiginList = mattersService.querySigInList(mattersVo.getMatters_id());
			request.setAttribute("SiginList", SiginList);
			/**
			 * 当前用户的事项是否进行延期申请
			 */
			int delayState = feedBackService.queryRequestDelayCount(id,"01");
			request.setAttribute("delayState", delayState);
			 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "queryUserFeedbackMattersById";
	}

	
	
	/**
	 * 核验申请
	 */
	@LogAnno(operateType="提交核验申请")
	public  void RequestCheck() {
		 
		try {
			
			mattersService.SaveUnitsState(mattersVo);
			
//			int  heyanCount = feedBackService.queryHeyanCount(mattersVo);
			/**
			 * 当该事项的所有牵头部门都进行了核验申请后 修改事项表状态
			 */
			int heyanCount=0;
			if(heyanCount==0) {
					mattersService.SaveFeedBackCheck(mattersVo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@LogAnno(operateType="查看事项反馈信息")
	public String queryFeedbackById() {
		try {
			String id = request.getParameter("id");
			id = Util.nullToString(id);
			feedBackService.UpdateFeedBackState(id,"1","");
			feedbackVo = feedBackService.queryFeedbackById(id);
			/**
			 * 查看反馈的图片
			 */
			List feedbackfileList = mattersService.getUpfile(feedbackVo.getFid(), "feedbackFile");
			request.setAttribute("feedbackfileList", feedbackfileList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryFeedbackById";
	}
	
	
	public  String againFeedback() {
		try {
			String id = request.getParameter("id");
			id = Util.nullToString(id);
			feedbackVo = feedBackService.queryFeedbackById(id);
			/**
			 * 查看反馈的图片
			 */
			List feedbackfileList = mattersService.getUpfile(feedbackVo.getFid(), "feedbackFile");
			request.setAttribute("feedbackfileList", feedbackfileList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "againFeedback";
		
	}
	
	
	
	
	
	
	
	
	
	
	private StandardVo standardVo;
	
	
	public StandardVo getStandardVo() {
		return standardVo;
	}


	public void setStandardVo(StandardVo standardVo) {
		this.standardVo = standardVo;
	}

	
	@LogAnno(operateType="退回事项反馈")
	public  void SendBackFeedBack() {
		try {
			System.out.println("FeedBack====="+feedbackVo);
			/**
			 * 修改为退回状态
			 */
			feedBackService.UpdateFeedBackState(feedbackVo.getId(),"2",feedbackVo.getStatecontent());
			String code = mattersScoreService.SaveEvakuaCheck(feedbackVo);
			if(code.equals("1")) {
	 			standardVo=mattersScoreService.queryStandardById("7");
	 			EvaluationTableVo  evaluationTableVo = new EvaluationTableVo();
				/**
				 * 之前分数
				 */
				String beforeScore = mattersScoreService.queryAfterScore(feedbackVo.getFeedback_id());
				evaluationTableVo.setZbid(standardVo.getId());
				evaluationTableVo.setMarkScore(standardVo.getScore());
				evaluationTableVo.setUser_id(feedbackVo.getFeedback_id());
				evaluationTableVo.setMatters_id(feedbackVo.getMatter_id());
				evaluationTableVo.setBefore_score(beforeScore);
				/**
				 * 扣分后分数
				 */
				String  aflterScore = 	mattersScoreService.UpdateOverFeedBackScore(evaluationTableVo);
				evaluationTableVo.setAlfter_score(aflterScore);
				evaluationTableVo.setScore(standardVo.getScore());
				mattersScoreService.UpdateEvalutionData(evaluationTableVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	/**
	 * 事项反馈页面
	 * 
	 * @return
	 */
	@LogAnno(operateType="进入反馈页面")
	public String updateUserFeedbackMattersPage() {

		String mattersId = Util.nullToString(request.getParameter("mattersid"));

		feedbackVo = new FeedbackVo();
		feedbackVo.setFid(CommonFunc.getGUID());
		feedbackVo.setMatter_id(mattersId);

		return "updateUserFeedbackMattersPage";
	}

	/**
	 * 保存反馈记录
	 */
	@LogAnno(operateType="保存反馈信息")
	public void SaveFeedBack() {
		try {
			String[] fileName = request.getParameterValues("fileName");
			// 附件删除remark
			mattersService.delFileByReport(feedbackVo.getFid(), "feedbackFile");
			// 附件添加
			if (null != fileName && 0 != fileName.length)
				mattersService.addReportFile_new(fileName, request.getParameterValues("filePath"),
						request.getParameterValues("type"), request.getParameterValues("type"), feedbackVo.getFid(),
						"prove", "11");
			feedBackService.SaveFeedBack(feedbackVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 保存重新反馈记录
	 */
	public void SaveAgainBackFeedBack() {
		try {
			String[] fileName = request.getParameterValues("fileName");
			// 附件删除remark
			mattersService.delFileByReport(feedbackVo.getFid(), "feedbackFile");
			// 附件添加
			if (null != fileName && 0 != fileName.length)
				mattersService.addReportFile_new(fileName, request.getParameterValues("filePath"),
						request.getParameterValues("type"), request.getParameterValues("type"), feedbackVo.getFid(),
						"prove", "11");
			feedBackService.SaveFeedBack(feedbackVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 删除反馈记录
	 */
	@LogAnno(operateType="删除反馈信息")
	public void delFeedBack() {
		try {
			String ids = request.getParameter("ids");
			feedBackService.delFeedBack(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * 查看全部的反馈记录
	 * @return
	 */
	@LogAnno(operateType="查看所有事项反馈记录")
	public String queryAllFeedBack() {

		try {
			String personName = request.getParameter("personName");
			personName = Util.nullToString(personName);
			String mattersid = request.getParameter("mattersid");
			mattersid = Util.nullToString(mattersid);

			System.out.println("mattersid=" + mattersid);

			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset == null) ? 0 : Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			searchVo = new SearchVo();
			searchVo.setMattersId(mattersid);
			searchVo.setPersonName(personName);
			map.put("personName", personName);
			map.put("mattersid", mattersid);
			List list = feedBackService.queryAllFeedBack(searchVo, pagination);

			pagination = new Pagination(15, (offset == null) ? 0 : Integer.parseInt(offset), pagination.getTotalCount(),
					"feedback!queryAllFeedBack.action", map, list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("personName", personName);
			request.setAttribute("mattersid", mattersid);
			
			
			mattersVo= mattersService.queryMatterById(mattersid);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "queryAllFeedBack";
	}
	
	
	public  String QueryAllFeedbackById() {
		try {
			String id = request.getParameter("id");
			id = Util.nullToString(id);
			String worktime = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			System.out.println("worktime===="+worktime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			if(worktime.equals("")) {
				worktime=CommonFunc.CurrentDateJianHao();
			}
			Date date = sdf.parse(worktime);
			
			searchVo = new SearchVo();

			searchVo.setOneWeekTime(sdf.format(getThisWeekMonday(date)));
			searchVo.setFiveWeekTime(sdf.format(gefirstWeekMonday(date)));
			searchVo.setId(id);
			String  uname =   feedBackService.queryUserName(id);
			List  list  = feedBackService.QueryAllFeedback(searchVo);
			request.setAttribute("list", list);
			
			request.setAttribute("id",id);
			request.setAttribute("uname", uname);
			request.setAttribute("worktime", worktime);
			System.out.println("worktime===="+worktime);
			request.setAttribute("feedTime", CommonFunc.CurrentDateJianHao());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "QueryAllFeedbackById";
	}
	
	public  void  ExcelFeedBack() {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			String  id = request.getParameter("id");
			id =Util.nullToString(id);
			String worktime = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			if(worktime.equals("")) {
				worktime=CommonFunc.CurrentDateJianHao();
			}
			Date date = sdf.parse(worktime);
			
			searchVo = new SearchVo();

			searchVo.setOneWeekTime(sdf.format(getThisWeekMonday(date)));
			searchVo.setFiveWeekTime(sdf.format(gefirstWeekMonday(date)));
			searchVo.setId(id);
			List  list  = feedBackService.QueryAllFeedback(searchVo);
			String filepath=request.getRealPath("/").replaceAll("\\\\", "\\\\\\\\")+"\\exceldata\\prjcheckinfo.xls";
			File file =ExcelExportUtil_CreditView.exportFeedBack(list,filepath );
			//头信息
		 	response.reset();
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Disposition", "attachment;filename=\"exceldata\\projectinfo.xls\"");
		    response.setHeader("Connection", "close");
		    
			if (file.exists()) {
				in = new FileInputStream(filepath);
				out = response.getOutputStream();
				byte[] b = new byte[1024];
				for (int len = 0; (len = in.read(b)) != -1;) {
					out.write(b, 0, len);
				}
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

	@LogAnno(operateType="查看反馈附件")
	public String queryPhotoFiles() {
		try {
			String c_id = request.getParameter("c_id");
			c_id = Util.nullToString(c_id);

			List companyfileList = mattersService.getUpfile(c_id, "feedbackFile");
			request.setAttribute("companyfileList", companyfileList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "queryPhotoFiles";
	}

	Pagination pagination = new Pagination();
	// struts2中response request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	private Pager pm;
	private String PrjNum;

	private CooperateUnitsVo unitsVo;

	private MattersVo mattersVo;

	public MattersVo getMattersVo() {
		return mattersVo;
	}

	public void setMattersVo(MattersVo mattersVo) {
		this.mattersVo = mattersVo;
	}

	private FeedbackVo feedbackVo;

	public FeedbackVo getFeedbackVo() {
		return feedbackVo;
	}

	public void setFeedbackVo(FeedbackVo feedbackVo) {
		this.feedbackVo = feedbackVo;
	}

	private SearchVo searchVo;

	public SearchVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}

	

	
	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	public static Date gefirstWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE,4);
		return cal.getTime();
	}
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	
	
	
	
	
	
	
	
	
	
}
