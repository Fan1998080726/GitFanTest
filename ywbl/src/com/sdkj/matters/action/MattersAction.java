package com.sdkj.matters.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sdkj.check.vo.CheckVo;
import com.sdkj.feedback.service.FeedBackService;
import com.sdkj.matters.service.MattersService;
import com.sdkj.matters.vo.CooperateUnitsVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.matters.vo.MattersupdateVo;
import com.sdkj.matters.vo.ParmsVo;
import com.sdkj.matters.vo.SiginTableVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.ExcelExportUtil_CreditView;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;

@Results(value = {
		@Result(name = "queryMattersAdmin", type = "dispatcher", location = "/jsp/system/matters/Test.jsp"),
//		@Result(name = "queryMattersAdmin", type = "dispatcher", location = "/jsp/system/matters/queryMattersAdmin.jsp"),
		@Result(name = "queryDeleteMatters", type = "dispatcher", location = "/jsp/system/matters/queryDeleteMatters.jsp"),
		@Result(name = "queryFormerYearMatters", type = "dispatcher", location = "/jsp/system/matters/queryFormerYearMatters.jsp"),
		@Result(name = "queryAllMattersData", type = "dispatcher", location = "/jsp/system/matters/queryAllMattersData.jsp"),
		@Result(name = "queryMattersUser", type = "dispatcher", location = "/jsp/system/matters/queryMattersUser.jsp"),
		@Result(name = "queryMattersUser_xindao", type = "dispatcher", location = "/jsp/system/matters/queryMattersUser_xindao.jsp"),
		@Result(name = "queryMattersUser_yanqi", type = "dispatcher", location = "/jsp/system/matters/queryMattersUser_yanqi.jsp"),
		@Result(name = "queryMattersUser_All", type = "dispatcher", location = "/jsp/system/matters/queryMattersUser_All.jsp"),
		@Result(name = "updateMattersPage", type = "dispatcher", location = "/jsp/system/matters/updateMattersPage.jsp"),
		@Result(name = "queryMattersById", type = "dispatcher", location = "/jsp/system/matters/queryMattersById.jsp"),
		@Result(name = "queryHeyanMatters", type = "dispatcher", location = "/jsp/system/matters/queryHeyanMatters.jsp"),
		@Result(name = "queryFeedBackMatters", type = "dispatcher", location = "/jsp/system/matters/queryFeedBackMatters.jsp"),
		@Result(name = "UpdateUserPage", type = "dispatcher", location = "/jsp/system/matters/UpdateUserPage.jsp"),
		@Result(name = "IntoSigInPage", type = "dispatcher", location = "/jsp/system/matters/IntoSigInPage.jsp"),
		 

})
@Action("matters")
public class MattersAction {
	
	


	@Autowired
	private MattersService mattersService;

	@Autowired
	private FeedBackService feedBackService;
	
	
	/**
	 * 往年事项
	 * @return
	 */
	@LogAnno(operateType="进入往年事项菜单")
	public  String queryFormerYearMatters() {
		try {
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			String yearVal = request.getParameter("yearVal");
			yearVal = Util.nullToString(yearVal);
			searchVo = new SearchVo();
			searchVo.setPrjname(prjname);
			searchVo.setYear(yearVal);
			
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("prjname", prjname);
			map.put("yearVal", yearVal);
			List<MattersVo>  	list = mattersService.queryFormerYearMatters(searchVo, pagination);
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
				mattersVo.setFeedBackContent(Content);
			}
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"matters!queryFormerYearMatters.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			request.setAttribute("yearVal", yearVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "queryFormerYearMatters";
	}
	
	
	/**
	 * 撤销删除的事项
	 */
	@LogAnno(operateType="撤销删除的事项")
	public void UndoMatters() {
		try {
			String  id = request.getParameter("id");
			id = Util.nullToString(id);
			System.out.println("ID===="+id);
			mattersService.UndoMattersFlag(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 往年事项下载
	 * @return
	 * @throws IOException 
	 */
	@LogAnno(operateType="往年事项下载")
	public  void ExcelOldYearMatters() throws IOException {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			String prjname = request.getParameter("prjname");
			String yearVal = request.getParameter("yearVal");
			yearVal = Util.nullToString(yearVal);
			prjname = Util.nullToString(prjname);
			searchVo = new SearchVo();
			searchVo.setPrjname(prjname);
			searchVo.setYear(yearVal);

			List<MattersVo> list = mattersService.ExcelOldYearMatters(searchVo);
			
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
				mattersVo.setFeedBackContent(Content);
			}
			String filepath=request.getRealPath("/").replaceAll("\\\\", "\\\\\\\\")+"\\exceldata\\prjcheckinfo.xls";
			File file =ExcelExportUtil_CreditView.ExcelOldYearMatters(list,filepath);
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
			e.printStackTrace();
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
	}
		
		
	}
	

	
	
	
	
	
	
	
	
	
	@LogAnno(operateType="进入事项管理菜单")
	public  String queryMattersAdmin() {
		try {
//			System.out.println("prjname="+prjname);
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			String handover_person = request.getParameter("handover_person");
			handover_person = Util.nullToString(handover_person);
			searchVo = new SearchVo();
			searchVo.setPrjname(prjname);
			searchVo.setHandover_person(handover_person);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("prjname", prjname);
			List<MattersVo>   	list = mattersService.queryMattersAdmin(searchVo, pagination);
			
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
				mattersVo.setFeedBackContent(Content);
			}
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"matters!queryMattersAdmin.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			request.setAttribute("handover_person", handover_person);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "queryMattersAdmin";
	}
	

	@Autowired
	private OverMattersScoreService mattersScoreService;
	
	
	private StandardVo standardVo;
	
	
	public StandardVo getStandardVo() {
		return standardVo;
	}


	public void setStandardVo(StandardVo standardVo) {
		this.standardVo = standardVo;
	}

	@LogAnno(operateType="保存用户信息")
	public  void   SaveUserInfo() {
		try {
			String   username = request.getParameter("username");
			username  = Util.nullToString(username);
			
			String   memo = request.getParameter("memo");
			memo  = Util.nullToString(memo);
			
			String   phone = request.getParameter("phone");
			phone  = Util.nullToString(phone);
			String   email = request.getParameter("email");
			email  = Util.nullToString(email);
			
			UserInfo  userInfo = new UserInfo();
			Map session = ActionContext.getContext().getSession();
 
			userInfo.setId((Integer)session.get("user_id"));
			userInfo.setMemo(memo);
			userInfo.setPhone(phone);
			userInfo.setUser_name(username);
			userInfo.setEmail(email);
			mattersService.SaveUserInfo(userInfo);
			
 			session.put("phone", phone);
			session.put("memo", memo);
			session.put("username", username);
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	/**
	 * 事项审核
	 */
	@LogAnno(operateType="事项审核")
	public  void AuditMatters() {
		try {
			String  str=request.getParameter("str");
			String  endcontent  = request.getParameter("endcontent");
			endcontent = Util.nullToString(endcontent);
			mattersVo.setState(str);
			mattersVo.setEndcontent(endcontent);
			System.out.println("mattersVo="+mattersVo);
			mattersService.AuditMatters(mattersVo);
			/**
			 *首次退回不扣分    第二次退回扣分
			 */
			if(str.equals("2")) {
				int code = mattersService.BackMatters(mattersVo);
				
				if(code==1) {
		 			standardVo=mattersScoreService.queryStandardById("2");
					searchVo  = new SearchVo();
					searchVo.setMattersId(mattersVo.getMatters_id());
					
					
					List<EvaluationTableVo> list = mattersScoreService.queryUserMattersScore(searchVo);
					for (EvaluationTableVo evaluationTableVo : list) {
						
						evaluationTableVo.setZbid(standardVo.getId());
						evaluationTableVo.setMarkScore(standardVo.getScore());
						String  aflterScore = 	mattersScoreService.UpdateOverFeedBackScore(evaluationTableVo);
						evaluationTableVo.setAlfter_score(aflterScore);
						evaluationTableVo.setScore(standardVo.getScore());
		 				mattersScoreService.UpdateEvalutionData(evaluationTableVo);
						
						
					}
					
					
				}

			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	@LogAnno(operateType="进入编辑用户信息页面")
	public  String  UpdateUserPage() {
		
		try {
			Map session = ActionContext.getContext().getSession();
			int userid =  (Integer)session.get("user_id");
			UserInfo  userinfo =  mattersService.queryUserInfoById(String.valueOf(userid));
			request.setAttribute("userinfo", userinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "UpdateUserPage";
	}
	
	
	
	
	
	//查看事项详情
	public  String  queryMattersById() {
		String  id = Util.nullToString(request.getParameter("id"));
		String  cid = Util.nullToString(request.getParameter("cid"));
		
		String  cstate = Util.nullToString(request.getParameter("cstate"));
		
		
		try {
			searchVo = new SearchVo();
			List<ParmsVo>  parmList = mattersService.queryPermissions(searchVo);
			request.setAttribute("parmList", parmList);
 			searchVo.setCid(cid);
 			mattersVo = mattersService.queryMatterById(id);
	 
	 
			parmList = getList(parmList, mattersVo.getSource_unit(),mattersVo.getCooperate_unit());
 		 
			
			List companyfileList = mattersService.getUpfile(mattersVo.getFid(), "remark");
			request.setAttribute("companyfileList", companyfileList);
			
			/**
			 * 首次查看事项变更事项状态为02
			 */
//			if(cstate.equals("01")) {
				
//				mattersService.UpdateCooperateState(searchVo);
//			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return "queryMattersById";
	}
	
	
	@LogAnno(operateType="进入事项核验处室提交页面")
	public  String  queryHeyanMatters() {
		
		try {
			searchVo  = new SearchVo();
			
			List  list   = mattersService.queryHeyanMatters(searchVo);
			request.setAttribute("list", list);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return "queryHeyanMatters";
	}
	
	
	@LogAnno(operateType="进入处室事项反馈菜单")
	public  String  queryFeedBackMatters() {
			try {
				
				
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			searchVo = new SearchVo();
			searchVo.setPrjname(prjname);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("prjname", prjname);
			
			List  	list = mattersService.queryFeedBackMatters(searchVo, pagination);
			
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"matters!queryFeedBackMatters.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			
			
			
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		
		return "queryFeedBackMatters";
	}
	
	
	
	@LogAnno(operateType="已删除事项菜单")
	public  String  queryDeleteMatters() {
		try {
			
			
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			String handover_person = request.getParameter("handover_person");
			handover_person = Util.nullToString(handover_person);
			searchVo = new SearchVo();
			searchVo.setPrjname(prjname);
			searchVo.setHandover_person(handover_person);
			String offset = request.getParameter("pm.offset");
			Map session = ActionContext.getContext().getSession();
			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			map.put("prjname", prjname);
			map.put("handover_person", handover_person);
			List<MattersVo>  	list = mattersService.queryDeleteMatters(searchVo, pagination);
			
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
//				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
//				mattersVo.setFeedBackContent(Content);
			}
			
			
			
			
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
					pagination.getTotalCount(),"matters!queryDeleteMatters.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			request.setAttribute("handover_person", handover_person);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryDeleteMatters";
	}
	
	
	
	
	
	
	/**
	 * 用户事项管理
	 * @return
	 */
	@LogAnno(operateType="事项菜单")
	public  String queryMattersUser() {
		/**
		 * 菜单状态
		 */
		String str = request.getParameter("str");
		str = Util.nullToString(str);
		
		try {
			Map session = ActionContext.getContext().getSession();
			
			
			String prjname = request.getParameter("prjname");
			prjname = Util.nullToString(prjname);
			
			String handover_person = request.getParameter("handover_person");
			handover_person = Util.nullToString(handover_person);
			
			String state = request.getParameter("state");
			state = Util.nullToString(state);
			
			
			
			
			String offset = request.getParameter("pm.offset");
 			pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
 			
			Map<String, String> map = new HashMap<String, String>();
			map.put("prjname", prjname);
			map.put("str", str);
			map.put("state", state);
			map.put("handover_person", handover_person);
			searchVo = new SearchVo();
			searchVo.setUserId( (Integer)session.get("user_id"));
			searchVo.setStr(str);
			searchVo.setState(state);
			searchVo.setHandover_person(handover_person);
			searchVo.setSupervision_matter(prjname);
			
			List<MattersVo>  	list = mattersService.queryMattersUser(searchVo, pagination);
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
				mattersVo.setFeedBackContent(Content);
			}
			
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"matters!queryMattersUser.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("prjname", prjname);
			request.setAttribute("str", str);
			request.setAttribute("handover_person", handover_person);
			request.setAttribute("state", state);
			/**
			 * 十五天之后日期
			 */
			String daysAfter = CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),15);
			/**
			 * 当前日期
			 */
			String  currentTime = CommonFunc.CurrentDateJianHao();
			
			request.setAttribute("daysAfter", daysAfter);
			
			request.setAttribute("currentTime", currentTime);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String  returnUrl="";
//		if(str.equals("0")) {
			returnUrl="queryMattersUser_All";
//		}else 	if(str.equals("1")) {
//			returnUrl="queryMattersUser_xindao";
//		}else 	if(str.equals("7")) {
//			returnUrl="queryMattersUser_yanqi";
////		}
		
		
		return returnUrl;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	/**
	 * 删除事项
	 */
	@LogAnno(operateType="事项删除")
	public  void DelMatters() {
		try {
			String  id = request.getParameter("id");
			String  matterId = request.getParameter("matterId");
			mattersService.UpdateMattersFlag(id,matterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 删除事项
	 */
	@LogAnno(operateType="撤销发送的事项")
	public  void UndoSendMatters() {
		try {
			System.out.println("UndoSendMatters====");
			String  id = request.getParameter("id");
			mattersService.UndoSendMattersState(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



 
	/**
	 * 事项提前办结
	 */
	@LogAnno(operateType="事项提前办结")
	public  void endState() {
		try {
			String  id = request.getParameter("id");
			String  endcontent = request.getParameter("endcontent");
	 
			mattersService.endState(id,endcontent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	


	@LogAnno(operateType="进入事项添加修改菜单")
	public  String  updateMattersPage() {
			try {
				
				searchVo = new SearchVo();
				/**
				 * 获取所有用户
				 */
				List<ParmsVo>  parmList = mattersService.queryPermissions(searchVo);
				request.setAttribute("parmList", parmList);
				
				if(null==mattersVo) {
					mattersVo =  new MattersVo();
 					mattersVo.setFid(CommonFunc.getGUID());
					mattersVo.setMatters_id(CommonFunc.getGUID());;
				}else {
					/**
					 * 获取事项
					 */
					mattersVo = mattersService.queryMatterById(mattersVo.getId());
			 
					/**
					 *  获取事项关联用户表
					 */
					parmList = getList(parmList, mattersVo.getSource_unit(),
							mattersVo.getCooperate_unit());
		 		 
					
					List companyfileList = mattersService.getUpfile(mattersVo.getFid(), "remark");
					request.setAttribute("companyfileList", companyfileList);
					
					
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "updateMattersPage";	
	}
	
	
	
	
	
	
	/**
	 * 保存事项数据
	 */
	@LogAnno(operateType="事项保存或发送")
	public  void SaveMattersData() {
		try {
			
			System.out.println("mattersVo="+mattersVo);
			
			String[] fileName = request.getParameterValues("fileName");
			String[] filePath = request.getParameterValues("filePath");
			String[] category = request.getParameterValues("category");
			String[] saomiaodate = request.getParameterValues("saomiaodate");
			String[] type = request.getParameterValues("type");
			// 附件删除
			mattersService.delFileByReport(mattersVo.getFid(),"remark");
			// 附件添加
			
			if (null != fileName && 0 != fileName.length)
				mattersService.addReportFile_new(fileName, filePath, type, saomiaodate, mattersVo.getFid(), "prove", "11");
			 
			
			
			/**
			 * 保存事项数据
			 */
			mattersService.SaveMatterData(mattersVo);
			/**
			 * 删除该事项配合单位关联表
			 */
			mattersService.deleteUnitsData(mattersVo.getMatters_id());
			/**
			 * 牵头部门添加
			 */
			mattersService.SaveUnitsStr(mattersVo.getSource_unit(),mattersVo.getMatters_id(),"01");
			/**
			 * 配合部门添加
			 */
			mattersService.SaveUnitsStr(mattersVo.getCooperate_unit(),mattersVo.getMatters_id(),"02");
			
			 
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 综合查询
	 */
	@LogAnno(operateType="综合查询")
	public String  queryAllMattersData() {
 		try {
 			

 			searchVo = new SearchVo();
			Map session = ActionContext.getContext().getSession();
			String supervision_matter =Util.nullToString(request.getParameter("supervision_matter"));
			String main_task =Util.nullToString(request.getParameter("main_task"));
			String matter_source =Util.nullToString(request.getParameter("matter_source"));
			String state =Util.nullToString(request.getParameter("state"));
			String sendtime1 =Util.nullToString(request.getParameter("sendtime1"));
			String sendtime2 =Util.nullToString(request.getParameter("sendtime2"));
			String end_time1 =Util.nullToString(request.getParameter("end_time1"));
			String end_time2 =Util.nullToString(request.getParameter("end_time2"));
			String source_unit =Util.nullToString(request.getParameter("source_unit"));
			String cooperate_unit =Util.nullToString(request.getParameter("cooperate_unit"));
			String charge_lead =Util.nullToString(request.getParameter("charge_lead"));
			String chaosong_lead =Util.nullToString(request.getParameter("chaosong_lead"));
			String handover_person =Util.nullToString(request.getParameter("handover_person"));
			String supervision_code =Util.nullToString(request.getParameter("supervision_code"));
			String banjie1 =Util.nullToString(request.getParameter("banjie1"));
			String banjie2 =Util.nullToString(request.getParameter("banjie2"));
			
			
			String yearValue =Util.nullToString(request.getParameter("yearValue"));
			String quarter =Util.nullToString(request.getParameter("quarter"));
			
			System.out.println("quarter="+quarter);
			if(!quarter.equals("--请选择--")&&!quarter.equals("")) {
				if(yearValue.equals("")) {
					yearValue= CommonFunc.CurrentYear();
				}
			}
			
			String worktime =Util.nullToString(request.getParameter("worktime"));
			
			searchVo.setSupervision_matter(supervision_matter);
			searchVo.setMain_task(main_task);
			searchVo.setMatter_source(matter_source);
			searchVo.setState(state);
			searchVo.setSendtime1(sendtime1);
			searchVo.setSendtime2(sendtime2);
			searchVo.setEnd_time1(end_time1);
			searchVo.setEnd_time2(end_time2);
			searchVo.setSource_unit(source_unit);
			searchVo.setCooperate_unit(cooperate_unit);
			searchVo.setCharge_lead(charge_lead);
			searchVo.setHandover_person(handover_person);
			searchVo.setSupervision_code(supervision_code);
			searchVo.setBanjie1(banjie1);
			searchVo.setBanjie2(banjie2);
			 searchVo.setYear(yearValue);
			 searchVo.setQuarter(quarter);
			 searchVo.setWorktime(worktime);
			 searchVo.setChaosong_lead(chaosong_lead);
			 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(!worktime.equals("")) {
					Date date = sdf.parse(worktime);
					/**
					 * 周一
					 */
					searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
					/**
					 * 周天
					 */
					searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
					
				}else {
					
					searchVo.setOneWeekTime("");
					searchVo.setFiveWeekTime("");
				}
				
			String offset = request.getParameter("pm.offset");
				pagination.setCurrentPageNo((offset==null)?0:Integer.parseInt(offset));
			Map<String, String> map = new HashMap<String, String>();
			 map.put("supervision_matter", supervision_matter);
			 map.put("main_task", main_task);
			 map.put("matter_source", matter_source);
			 map.put("state", state);
			 map.put("sendtime1", sendtime1);
			 map.put("sendtime2", sendtime2);
			 map.put("end_time1", end_time1);
			 map.put("end_time2", end_time2);
			 map.put("source_unit", source_unit);
			 map.put("cooperate_unit", cooperate_unit);
			 map.put("charge_lead", charge_lead);
			 map.put("handover_person", handover_person);
			 map.put("supervision_code", supervision_code);
			 map.put("banjie1", banjie1);
			 map.put("banjie2", banjie2);
			 map.put("yearValue", yearValue);
			 map.put("quarter", quarter);
			 map.put("worktime", worktime);
			 map.put("chaosong_lead", chaosong_lead);
			searchVo.setUserId( (Integer)session.get("user_id"));
			List<MattersVo>  	list = mattersService.queryAllMattersData(searchVo, pagination);
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
				mattersVo.setFeedBackContent(Content);
			}
			pagination  = new Pagination( 15, (offset==null)?0:Integer.parseInt(offset),
			pagination.getTotalCount(),"matters!queryAllMattersData.action",map,list.size());
			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
		 
			 request.setAttribute("supervision_matter", supervision_matter);
			 request.setAttribute("main_task", main_task);
			 request.setAttribute("matter_source", matter_source);
			 request.setAttribute("state", state);
			 request.setAttribute("sendtime1", sendtime1);
			 request.setAttribute("sendtime2", sendtime2);
			 request.setAttribute("end_time1", end_time1);
			 request.setAttribute("end_time2", end_time2);
			 request.setAttribute("source_unit", source_unit);
			 request.setAttribute("cooperate_unit", cooperate_unit);
			 request.setAttribute("charge_lead", charge_lead);
			 request.setAttribute("handover_person", handover_person);
			 request.setAttribute("supervision_code", supervision_code);
			 request.setAttribute("banjie1", banjie1);
			 request.setAttribute("banjie2", banjie2);
			 request.setAttribute("yearValue", yearValue);
			 request.setAttribute("quarter", quarter);
			 request.setAttribute("chaosong_lead", chaosong_lead);
			 request.setAttribute("worktime", worktime);
			
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		return "queryAllMattersData";
	}
	/**
	 * 综合查询导出
	 */
	@LogAnno(operateType="综合查询导出")
	public String  ExcelMatters() throws IOException {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			

 			searchVo = new SearchVo();
			Map session = ActionContext.getContext().getSession();
			String supervision_matter =Util.nullToString(request.getParameter("supervision_matter"));
			String main_task =Util.nullToString(request.getParameter("main_task"));
			String matter_source =Util.nullToString(request.getParameter("matter_source"));
			String state =Util.nullToString(request.getParameter("state"));
			String sendtime1 =Util.nullToString(request.getParameter("sendtime1"));
			String sendtime2 =Util.nullToString(request.getParameter("sendtime2"));
			String end_time1 =Util.nullToString(request.getParameter("end_time1"));
			String end_time2 =Util.nullToString(request.getParameter("end_time2"));
			String source_unit =Util.nullToString(request.getParameter("source_unit"));
			String cooperate_unit =Util.nullToString(request.getParameter("cooperate_unit"));
			String charge_lead =Util.nullToString(request.getParameter("charge_lead"));
			String chaosong_lead =Util.nullToString(request.getParameter("chaosong_lead"));
			String handover_person =Util.nullToString(request.getParameter("handover_person"));
			String supervision_code =Util.nullToString(request.getParameter("supervision_code"));
			String banjie1 =Util.nullToString(request.getParameter("banjie1"));
			String banjie2 =Util.nullToString(request.getParameter("banjie2"));
			
			
			String yearValue =Util.nullToString(request.getParameter("yearValue"));
			String quarter =Util.nullToString(request.getParameter("quarter"));
			
			System.out.println("quarter="+quarter);
			if(!quarter.equals("--请选择--")&&!quarter.equals("")) {
				if(yearValue.equals("")) {
					yearValue= CommonFunc.CurrentYear();
				}
			}
			
			String worktime =Util.nullToString(request.getParameter("worktime"));
			
			searchVo.setSupervision_matter(supervision_matter);
			searchVo.setMain_task(main_task);
			searchVo.setMatter_source(matter_source);
			searchVo.setState(state);
			searchVo.setSendtime1(sendtime1);
			searchVo.setSendtime2(sendtime2);
			searchVo.setEnd_time1(end_time1);
			searchVo.setEnd_time2(end_time2);
			searchVo.setSource_unit(source_unit);
			searchVo.setCooperate_unit(cooperate_unit);
			searchVo.setCharge_lead(charge_lead);
			searchVo.setHandover_person(handover_person);
			searchVo.setSupervision_code(supervision_code);
			searchVo.setBanjie1(banjie1);
			searchVo.setBanjie2(banjie2);
			 searchVo.setYear(yearValue);
			 searchVo.setQuarter(quarter);
			 searchVo.setWorktime(worktime);
			 searchVo.setChaosong_lead(chaosong_lead);
			 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(!worktime.equals("")) {
					Date date = sdf.parse(worktime);
					/**
					 * 周一
					 */
					searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
					/**
					 * 周天
					 */
					searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
					
				}else {
					
					searchVo.setOneWeekTime("");
					searchVo.setFiveWeekTime("");
				}
				
			searchVo.setUserId( (Integer)session.get("user_id"));
			
			List<MattersVo>  	list = mattersService.queryExcelMattersData(searchVo, pagination);
			for (MattersVo mattersVo : list) {
				String  uname = mattersService.queryUserInfoOrById(mattersVo.getSource_unit());
				mattersVo.setSource_unit(uname);
				
				
				if(mattersVo.getCooperate_unit()!=null) {
					String  Cooperate_name = mattersService.queryUserInfoOrById(mattersVo.getCooperate_unit());
					mattersVo.setCooperate_unit(Cooperate_name);
				}
				
				String  Content = feedBackService.queryFeedBackContent(mattersVo.getId());
				mattersVo.setFeedBackContent(Content);
				 
			}
			String filepath=request.getRealPath("/").replaceAll("\\\\", "\\\\\\\\")+"\\exceldata\\prjcheckinfo.xls";
			File file =ExcelExportUtil_CreditView.ExcelMatters(list,filepath);
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
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)  {
			e.printStackTrace();
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
	}
		
		
		
		
		return "queryAllMattersData";
	}
	
	

	
	/**
	 * checkBox选中
	 * @param parmList  所有用户表
	 * @param unitType   1牵头单位 
	 * @param unitType2  2 配合单位
	 * @return
	 */
	public  static	List<ParmsVo> getList(List<ParmsVo>  parmList,String  unitType,String  unitType2){
		
			/**
		 * 牵头单位
		 */
		String[]  qiantoulist = unitType.split(",");
		if(qiantoulist.length!=0) {
			
		for(int j = 0;j<qiantoulist.length;j++) {
			for(int i =0;i<parmList.size();i++) {
				if(qiantoulist[j].equals(parmList.get(i).getId())) {
					/**
					 * 牵头单位
					 */
				 
						parmList.get(i).setQiantouIscheck("1");
				 
				}
			}
		}
	 }
		
		if(unitType2!=null) {
			
		/**
		 * 配合单位
		 */
		String[]  peihelist = unitType2.split(",");
			
			for(int j = 0;j<peihelist.length;j++) {
				for(int i =0;i<parmList.size();i++) {
					if(peihelist[j].equals(parmList.get(i).getId())) {
						/**
						 * 牵头单位
						 */
						parmList.get(i).setPeiheIscheck("1");
						
					}
				}
			}

		}
		
		
		
		
		
		return parmList;
	}
	
	private SiginTableVo siginTableVo;
	
	@LogAnno(operateType="进入事项签收页面")
	public  String  IntoSigInPage() {
		String  mattersid = request.getParameter("mattersid");
		mattersid =  Util.nullToString(mattersid);
		
		siginTableVo = new SiginTableVo();
		siginTableVo.setMatters_id(mattersid);
		
		
		
		return "IntoSigInPage";
	}
	
	
	
	
	
	
	public SiginTableVo getSiginTableVo() {
		return siginTableVo;
	}


	public void setSiginTableVo(SiginTableVo siginTableVo) {
		this.siginTableVo = siginTableVo;
	}


	@LogAnno(operateType="事项签收")
	public void SaveSigIn() {
		
		try {
			
			mattersService.SaveSigInfo(siginTableVo);
			searchVo = new SearchVo();
			searchVo.setMattersId(siginTableVo.getMatters_id());
			/**
			 * 首次查看事项变更事项状态为02 首次反馈也是一样
			 */
			mattersService.UpdateCooperateState(searchVo);
		 
			
			
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
	
  private  CooperateUnitsVo unitsVo;
  private  	MattersupdateVo mattersupdateVo;
  private  	MattersVo mattersVo;
	
  
  
  
	
	public CooperateUnitsVo getUnitsVo() {
	return unitsVo;
}





public void setUnitsVo(CooperateUnitsVo unitsVo) {
	this.unitsVo = unitsVo;
}





public MattersupdateVo getMattersupdateVo() {
	return mattersupdateVo;
}





public void setMattersupdateVo(MattersupdateVo mattersupdateVo) {
	this.mattersupdateVo = mattersupdateVo;
}





public MattersVo getMattersVo() {
	return mattersVo;
}





public void setMattersVo(MattersVo mattersVo) {
	this.mattersVo = mattersVo;
}

private  SearchVo searchVo;

public SearchVo getSearchVo() {
	return searchVo;
}





public void setSearchVo(SearchVo searchVo) {
	this.searchVo = searchVo;
}

	
}
