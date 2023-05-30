package com.sdkj.TimeScore.action;

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
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;
@Results(value = {
		@Result(name = "queryEvaluation", type = "dispatcher", location = "/jsp/system/Evalua/queryEvaluation.jsp"),
		@Result(name = "queryUserEvaluaScorePage", type = "dispatcher", location = "/jsp/system/Evalua/queryUserEvaluaScorePage.jsp"),
		@Result(name = "queryUserMattersList", type = "dispatcher", location = "/jsp/system/Evalua/queryUserMattersList.jsp"),
		 
		 

})
@Action("Evalua")
public class EvaluaAction {

	
	@Autowired
	private OverMattersScoreService mattersScoreService;

	@LogAnno(operateType="评价打分")
	public  String queryEvaluation() {
		try {
//			System.out.println("prjname="+prjname);
			String username = request.getParameter("username");
			username = Util.nullToString(username);
			System.out.println("username===="+username);
			searchVo = new SearchVo();
			searchVo.setUsername(username); 
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			System.out.println("searchVo="+searchVo);
			List  	list = mattersScoreService.queryEvaluation(searchVo, pagination);
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"Evalua!queryEvaluation.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("username", username);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "queryEvaluation";
	}
	
	@LogAnno(operateType="选择事项进入打分页面")
	public  String queryUserEvaluaScorePage() {
		
		try {
			
			String  mid = request.getParameter("mid");
			mid = Util.nullToString(mid);
			String  userid = request.getParameter("userid");
			userid = Util.nullToString(userid);
			
			searchVo = new SearchVo();
			List  standList =mattersScoreService.queryStandList(searchVo);
			request.setAttribute("standList", standList);
			
			request.setAttribute("mid", mid);
			request.setAttribute("userid", userid);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "queryUserEvaluaScorePage";
	}
	
	@LogAnno(operateType="选择处室查看处室未办结的事项")
	public  String queryUserMattersList() {
		try {
//			System.out.println("prjname="+prjname);
			String id = request.getParameter("id");
			id = Util.nullToString(id);
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
		 
			searchVo = new SearchVo();
			searchVo.setUserId(Integer.parseInt(id));
			searchVo.setPrjname(prjname);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("prjname",prjname);
			List  	list = mattersScoreService.queryUserMattersList(searchVo, pagination);
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
					pagination.getTotalCount(),"Evalua!queryUserMattersList.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			request.setAttribute("id", id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryUserMattersList";
	}
	
	@LogAnno(operateType="确认评价打分")
	public   void  SaveEvualScore() {
		try {
			String  checkIds  = request.getParameter("checkIds");
			checkIds = Util.nullToString(checkIds);
			System.out.println("checkId====="+checkIds);
			
			String  mid = request.getParameter("mid");
			mid = Util.nullToString(mid);
			String  userid = request.getParameter("userid");
			userid = Util.nullToString(userid);
			
			System.out.println("mid="+mid);
			System.out.println("userid="+userid);
			
			String  score ="";
			String  beforeScore ="";
			EvaluationTableVo  evaluationTableVo = new EvaluationTableVo();
			String [] checkid = checkIds.split(",");
			for (String id : checkid) {
				score = request.getParameter("score"+id);
				/**
				 * 之前分数
				 */
				beforeScore = mattersScoreService.queryAfterScore(userid);
				
				evaluationTableVo.setZbid(id);
				evaluationTableVo.setMarkScore(score);
				evaluationTableVo.setUser_id(userid);
				evaluationTableVo.setMatters_id(mid);
				evaluationTableVo.setBefore_score(beforeScore);
				/**
				 * 扣分后分数
				 */
				String  aflterScore = 	mattersScoreService.UpdateOverFeedBackScore(evaluationTableVo);
				evaluationTableVo.setAlfter_score(aflterScore);
				evaluationTableVo.setScore(score);
 				mattersScoreService.UpdateEvalutionData(evaluationTableVo);
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

		private  SearchVo searchVo;
		
		public SearchVo getSearchVo() {
			return searchVo;
		}
		
		
		
		
		
		public void setSearchVo(SearchVo searchVo) {
			this.searchVo = searchVo;
		}
	
	
	
	Pagination pagination = new Pagination();
	// struts2中response request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	private Pager pm;
	private String PrjNum;
	
	
}
