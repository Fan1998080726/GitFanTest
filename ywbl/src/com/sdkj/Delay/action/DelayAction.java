package com.sdkj.Delay.action;

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
import com.sdkj.Delay.vo.DelayVo;
import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.matters.service.MattersService;
import com.sdkj.matters.vo.CooperateUnitsVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;

@Results(value = {
 
 		@Result(name = "ApplyDelay", type = "dispatcher", location = "/jsp/system/dealy/ApplyDelay.jsp"),
 		@Result(name = "queryDelayById", type = "dispatcher", location = "/jsp/system/dealy/queryDelayById.jsp"),
 		@Result(name = "AudtiDelay", type = "dispatcher", location = "/jsp/system/dealy/AudtiDelay.jsp"),
 		@Result(name = "queryAllDelay", type = "dispatcher", location = "/jsp/system/dealy/queryAllDelay.jsp"),

 
		 

})
@Action("delay")
public class DelayAction {


	
	@Autowired
	private MattersService mattersService;
	
	@Autowired
	private DelayService  delayService;
	
	
	/**
	 * 进入申请延期
	 * @return
	 */
	@LogAnno(operateType="进入申请延期页面")
	public String ApplyDelay() {

		try {
			String mattersid = request.getParameter("mattersid");
			mattersid = Util.nullToString(mattersid);
			delayVo = new DelayVo();
			delayVo.setMatter_id(mattersid);
			delayVo.setFid(CommonFunc.getGUID());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ApplyDelay";
	}
	
	/**
	 * 查看延期申请信息
	 * @return
	 */
	@LogAnno(operateType="查看延期申请信息")
	public String queryDelayById() {
		
		try {
			String mattersid = request.getParameter("mattersid");
			mattersid = Util.nullToString(mattersid);
			delayVo   = delayService.queryDelayById("",mattersid,"01");
			System.out.println("delayVo="+delayVo);
			List companyfileList = mattersService.getUpfile(delayVo.getFid(), "delayFile");
			request.setAttribute("companyfileList", companyfileList);

			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryDelayById";
	}
	
	/**
	 * 延期申请审核
	 * @return
	 */
	@LogAnno(operateType="进入延期申请审核页面")
	public String AudtiDelay() {
		
		try {
			String id = request.getParameter("id");
			id = Util.nullToString(id);
			delayVo   = delayService.queryDelayById(id,"","01");
			List companyfileList = mattersService.getUpfile(delayVo.getFid(), "delayFile");
			request.setAttribute("companyfileList", companyfileList);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AudtiDelay";
	}
	
	/**
	 * 查看所有的延期申请
	 * @return
	 */
	@LogAnno(operateType="查看所有的延期申请")
	public  String  queryAllDelay() {
		try {
			SearchVo searchVo = new SearchVo();
			List<DelayVo>  list    =  delayService.queryAllDelay(searchVo);	
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryAllDelay";
	}
	
	
	/**
	 * 提交延期申请
	 */
	@LogAnno(operateType="提交延期申请")
	public void Requestdelay() {
		try {
			
			String[] fileName = request.getParameterValues("fileName");
			// 附件删除remark
			mattersService.delFileByReport(delayVo.getFid(), "delayFile");
			// 附件添加
			if (null != fileName && 0 != fileName.length)
				mattersService.addReportFile_new(fileName, request.getParameterValues("filePath"),
						request.getParameterValues("type"), request.getParameterValues("type"), delayVo.getFid(),
						"prove", "11");
			delayService.SaveDelay(delayVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * 延期申请审核
	 */
	@LogAnno(operateType="延期申请审核")
	public void  SaveDelayState() {
		try {
			String  str = request.getParameter("str");
			str = Util.nullToString(str);
			delayService.SaveAuditDelay(delayVo,str);
			
			/**
			 * 审核通过
			 */
//			if(str.equals("1")) {
				mattersService.updateMattersState(delayVo.getMatter_id(),str,delayVo.getStatecontent(),delayVo.getInittime());
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

	
	private  DelayVo delayVo;





	public DelayVo getDelayVo() {
		return delayVo;
	}





	public void setDelayVo(DelayVo delayVo) {
		this.delayVo = delayVo;
	}
	
	
	
}
