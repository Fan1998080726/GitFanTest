package com.sdkj.check.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdkj.check.service.CheckService;
import com.sdkj.check.vo.CheckVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.ExcelExportUtil_CreditView;
import com.sdkj.util.context.Util;
import com.sdkj.util.log.LogAnno;
@Results(value = {
		 
 		@Result(name = "queryCheckStatistics", type = "dispatcher", location = "/jsp/system/check/queryCheckStatistics.jsp"),
 		@Result(name = "queryCheckStatistics1", type = "dispatcher", location = "/jsp/system/check/Test.jsp"),
 		@Result(name = "queryMattersRank", type = "dispatcher", location = "/jsp/system/check/queryMattersRank.jsp"),
 		@Result(name = "queryWorkMassRank", type = "dispatcher", location = "/jsp/system/check/queryWorkMassRank.jsp"),
 		@Result(name = "queryEndMattersRank", type = "dispatcher", location = "/jsp/system/check/queryEndMattersRank.jsp"),
 		@Result(name = "queryCheckStatisticsPhoto", type = "dispatcher", location = "/jsp/system/check/queryCheckStatisticsPhoto.jsp"),
 
 	 

 








})
@Action("check")
public class CheckAction {

	@Autowired
	private  CheckService checkService;
	
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private SearchVo searchVo;
	
	public SearchVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}

	/**
	 * 考核统计
	 * @return
	 */
	@LogAnno(operateType="考核统计")
	public  String  queryCheckStatistics() {
		try {
 			String  startdate = request.getParameter("startdate");
			startdate = Util.nullToString(startdate);
			
			
			String  enddate = request.getParameter("enddate");
			enddate = Util.nullToString(enddate);
			String  worktime = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			String  quarter = request.getParameter("quarter");
			quarter = Util.nullToString(quarter);
			
			
			String  yearValue = request.getParameter("yearValue");
			yearValue = Util.nullToString(yearValue);
		 
			
			
			
			
			
			searchVo = new SearchVo();
			searchVo.setStartdate(startdate);
			searchVo.setEnddate(enddate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!worktime.equals("")) {
				Date date = sdf.parse(worktime);
				searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
				searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
				
			}else {
				
				searchVo.setOneWeekTime("");
				searchVo.setFiveWeekTime("");
			}
			
			searchVo.setYear(yearValue);
			searchVo.setWorktime(worktime);
			searchVo.setQuarter(quarter);
			
			
			
			List<CheckVo> list = checkService.queryCheckStatistics(searchVo);
			request.setAttribute("list", list);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			request.setAttribute("yearValue", yearValue);
			request.setAttribute("quarter", quarter);
			request.setAttribute("worktime", worktime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Into   jsp");
		return "queryCheckStatistics1";
	}
	/**
	 * 下载
	 * @return
	 * @throws IOException 
	 */
	@LogAnno(operateType="导出考核统计Excel")
	public  void  ExcelReport() throws IOException {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			
			String  startdate = request.getParameter("startdate");
			startdate = Util.nullToString(startdate);
			String  enddate = request.getParameter("enddate");
			enddate = Util.nullToString(enddate);
			
			
			String  worktime = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			String  quarter = request.getParameter("quarter");
			quarter = Util.nullToString(quarter);
			
			
			String  yearValue = request.getParameter("yearValue");
			yearValue = Util.nullToString(yearValue);
		 
			
			
			
			
			
			searchVo = new SearchVo();
			searchVo.setStartdate(startdate);
			searchVo.setEnddate(enddate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 
			if(!worktime.equals("")) {
				Date date = sdf.parse(worktime);
				searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
				searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
				
			}else {
				
				searchVo.setOneWeekTime("");
				searchVo.setFiveWeekTime("");
			}
			
			
			searchVo.setYear(yearValue);
			searchVo.setWorktime(worktime);
			searchVo.setQuarter(quarter);
			
			searchVo.setStartdate(startdate);
			searchVo.setEnddate(enddate);
			List<CheckVo> list = checkService.queryCheckStatistics(searchVo);
			
			
			
			
			request.setAttribute("list", list);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			 ////////System.out.println(list.size()+"...................");
			String filepath=request.getRealPath("/").replaceAll("\\\\", "\\\\\\\\")+"\\exceldata\\prjcheckinfo.xls";
			////////System.out.println("filepath="+filepath);
			File file =ExcelExportUtil_CreditView.export(list,filepath );
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
	/**
	 *	承办事项排名
	 * @return
	 */
	@LogAnno(operateType="承办事项排名")
	public  String  queryMattersRank() {
		try {
			String  worktime = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			String  quarter = request.getParameter("quarter");
			quarter = Util.nullToString(quarter);
			
			
			String  yearValue = request.getParameter("yearValue");
			yearValue = Util.nullToString(yearValue);
			
			String  startdate = request.getParameter("startdate");
			startdate = Util.nullToString(startdate);
			String  enddate = request.getParameter("enddate");
			enddate = Util.nullToString(enddate);
			searchVo = new SearchVo();
			searchVo.setStartdate(startdate);
			searchVo.setEnddate(enddate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!worktime.equals("")) {
				Date date = sdf.parse(worktime);
				searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
				searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
				
			}else {
				
				searchVo.setOneWeekTime("");
				searchVo.setFiveWeekTime("");
			}
			
			searchVo.setYear(yearValue);
			searchVo.setWorktime(worktime);
			searchVo.setQuarter(quarter);
			
			
			List<CheckVo> list = checkService.queryMattersRank(searchVo);
			request.setAttribute("list", list);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			request.setAttribute("yearValue", yearValue);
			request.setAttribute("quarter", quarter);
			request.setAttribute("worktime", worktime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryMattersRank";
	}
	
	/**
	 * 工作质量排名
	 * @return
	 */
	@LogAnno(operateType="工作质量排名")
	public  String queryWorkMassRank() {
		try {
			String  worktime   = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			String  yearValue   = request.getParameter("yearValue");
			yearValue = Util.nullToString(yearValue);
			String  timeType   = request.getParameter("timeType");
			timeType = Util.nullToString(timeType);
			if(timeType.equals("")) {
				timeType="0";
			}
			 
			System.out.println("yearValue===="+yearValue);
			searchVo = new SearchVo();
			searchVo.setYear(yearValue);
			searchVo.setTimeType(timeType);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(worktime.equals("")) {
				worktime=CommonFunc.CurrentDateJianHao();
			}
			Date date = sdf.parse(worktime);
			searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
			searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
			List<CheckVo>   list  = checkService.queryWorkMassRank(searchVo);
			List<CheckVo>  ScoreList = checkService.queryPointScore(searchVo);
			double  scoreValue=30.0;
			
			for(int i=0;i<list.size();i++) {
				scoreValue=30.0;
				list.get(i).setScore(String.valueOf(scoreValue));
				for(int j =0;j<ScoreList.size();j++) {
					if(list.get(i).getUserid().equals(ScoreList.get(j).getUserid())) {
						System.out.println("ScoreList.get(j).getUserid()====="+ScoreList.get(j).getUserid());
						scoreValue-=Double.parseDouble(ScoreList.get(j).getScore());
						list.get(i).setScore(String.valueOf(scoreValue));
					}
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("worktime", worktime);
			request.setAttribute("timeType", timeType);
			request.setAttribute("yearValue", yearValue);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryWorkMassRank";
	}
	
	
	
	
	/**
	 * 导出工作质量排名
	 * @return
	 */
	@LogAnno(operateType="导出工作质量排名")
	public  void ExcelWorkMassRank() throws Exception {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			String  worktime   = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			String  yearValue   = request.getParameter("yearValue");
			yearValue = Util.nullToString(yearValue);
			String  timeType   = request.getParameter("timeType");
			timeType = Util.nullToString(timeType);
			if(timeType.equals("")) {
				timeType="0";
			}
			searchVo = new SearchVo();
			searchVo.setYear(yearValue);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			searchVo.setTimeType(timeType);
			
			if(worktime.equals("")) {
				worktime=CommonFunc.CurrentDateJianHao();
			}
			Date date = sdf.parse(worktime);
			
			searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
			searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
			
			
			@SuppressWarnings("unchecked")
			List<CheckVo>   list  = checkService.queryWorkMassRank(searchVo);
			
			
			List<CheckVo>  ScoreList = checkService.queryPointScore(searchVo);

			List<CheckVo>  StandList =checkService.queryStandCheckList(searchVo);
			
			
			double  scoreValue=30.0;
			
			for(int i=0;i<list.size();i++) {
				scoreValue=30.0;
				list.get(i).setScore(String.valueOf(scoreValue));
				for(int j =0;j<ScoreList.size();j++) {
					if(list.get(i).getUserid().equals(ScoreList.get(j).getUserid())) {
						scoreValue-=Double.parseDouble(ScoreList.get(j).getScore());
						list.get(i).setScore(String.valueOf(scoreValue));
					}
				}
			}
			for(int i=0;i<list.size();i++) {
 				for(int j =0;j<StandList.size();j++) {
 					if(list.get(i).getUserid().equals(StandList.get(j).getUserid())) {
						if(StandList.get(j).getZbid().equals("1")) {
							list.get(i).setStand1(StandList.get(j).getKfScore());
						}else  	if(StandList.get(j).getZbid().equals("2")) {
							list.get(i).setStand2(StandList.get(j).getKfScore());
						}else  	if(StandList.get(j).getZbid().equals("3")) {
							list.get(i).setStand3(StandList.get(j).getKfScore());
						}else  	if(StandList.get(j).getZbid().equals("4")) {
							list.get(i).setStand4(StandList.get(j).getKfScore());
						}else  	if(StandList.get(j).getZbid().equals("5")) {
							list.get(i).setStand5(StandList.get(j).getKfScore());
						}else  	if(StandList.get(j).getZbid().equals("6")) {
							list.get(i).setStand6(StandList.get(j).getKfScore());
						}  	if(StandList.get(j).getZbid().equals("7")) {
							list.get(i).setStand7(StandList.get(j).getKfScore());
						}   
					}
				}
			} 
			request.setAttribute("list", list);
			request.setAttribute("worktime", worktime);
			
			String filepath=request.getRealPath("/").replaceAll("\\\\", "\\\\\\\\")+"\\exceldata\\prjcheckinfo啊啊.xls";
			////////System.out.println("filepath="+filepath);
			File file =ExcelExportUtil_CreditView.ExcelWorkMassRank(list,filepath );
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
	
	
	
	
	
	/**
	 *	办结率排名
	 * @return
	 */
	@LogAnno(operateType="办结率排名")
	public  String  queryEndMattersRank() {
		try {
			
			
			
			
			String  startdate = request.getParameter("startdate");
			startdate = Util.nullToString(startdate);
			
			
			String  enddate = request.getParameter("enddate");
			enddate = Util.nullToString(enddate);
			String  worktime = request.getParameter("worktime");
			worktime = Util.nullToString(worktime);
			String  quarter = request.getParameter("quarter");
			quarter = Util.nullToString(quarter);
			
			
			String  yearValue = request.getParameter("yearValue");
			yearValue = Util.nullToString(yearValue);
			
			searchVo = new SearchVo();
			searchVo.setStartdate(startdate);
			searchVo.setEnddate(enddate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!worktime.equals("")) {
				Date date = sdf.parse(worktime);
				searchVo.setOneWeekTime(sdf.format(CommonFunc.getThisWeekMonday(date)));
				searchVo.setFiveWeekTime(sdf.format(CommonFunc.gefirstWeekMonday(date)));
				
			}else {
				
				searchVo.setOneWeekTime("");
				searchVo.setFiveWeekTime("");
			}
			
			searchVo.setYear(yearValue);
			searchVo.setWorktime(worktime);
			searchVo.setQuarter(quarter);
			
			
			
			
			
			List<CheckVo> list = checkService.queryEndMattersRank(searchVo);
			request.setAttribute("list", list);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			request.setAttribute("yearValue", yearValue);
			request.setAttribute("quarter", quarter);
			request.setAttribute("worktime", worktime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryEndMattersRank";
	}
	/**
	 *	考核统计图
	 * @return
	 */
	@LogAnno(operateType="考核统计图")
	public  String  queryCheckStatisticsPhoto() {
		try {
			
			String year = request.getParameter("year");
			year = Util.nullToString(year);
			
			String deptState = request.getParameter("deptState");
			deptState = Util.nullToString(deptState);
			if(deptState.equals("")) {
				deptState="0";
			}
			searchVo = new SearchVo();
 			
			searchVo.setYear(year);
			searchVo.setDeptState(deptState);
			
 			List<CheckVo> list = checkService.queryCheckStatisticsPhoto(searchVo);
 			
 			
 			List<UserInfo>  userlist = checkService.queryDeptUserInfo(searchVo);
 			request.setAttribute("userlist", userlist);
 			
			List<String> deptlist = new ArrayList();
			List<String>	listcount1 = new ArrayList();
			List<String>	listcount2 = new ArrayList();
			for (CheckVo checkVo : list) {
				deptlist.add("'"+checkVo.getUser_name()+"'");
				listcount1.add("'"+checkVo.getCount1()+"'");
				listcount2.add("'"+checkVo.getCount2()+"'");
			}
			request.setAttribute("list", list);
			
			

	 
			System.out.println("Dept---"+deptlist);
			request.setAttribute("deptlist", deptlist);
			request.setAttribute("listcount1", listcount1);
			request.setAttribute("listcount2", listcount2);
			request.setAttribute("year", year);
			request.setAttribute("deptState", deptState);
			
//			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryCheckStatisticsPhoto";
	}
	
	
	
	/**
	 *	考核统计图
	 * @return
	 */
	@LogAnno(operateType="年度部门考核统计图")
	public  String  queryMonthDeptCheckStatisticsPhoto() {
		try {
			
			String year = request.getParameter("year");
			year = Util.nullToString(year);
			
			String deptState = request.getParameter("deptState");
			deptState = Util.nullToString(deptState);
			
			String modules = request.getParameter("modules");
			modules = Util.nullToString(modules);
			
			System.out.println("modules="+modules);
			
			
			
			searchVo = new SearchVo();
			searchVo.setYear(year);
			searchVo.setUsername(modules);
			
			List<CheckVo>  checkList = checkService.queryMonthDeptCheckStatisticsPhoto(searchVo);
			request.setAttribute("checkList", checkList);

 			List<UserInfo>  userlist = checkService.queryDeptUserInfo(searchVo);
 			request.setAttribute("userlist", userlist);
			List<String> deptlist = new ArrayList();
			List<String>	listcount1 = new ArrayList();
			List<String>	listcount2 = new ArrayList();
			for (CheckVo checkVo : checkList) {
				deptlist.add("'"+checkVo.getUser_name()+"'");
				listcount1.add("'"+checkVo.getCount1()+"'");
				listcount2.add("'"+checkVo.getCount2()+"'");
			}
//			request.setAttribute("list", list);
//			
			
			System.out.println("modules==="+modules);
			
//			System.out.println("Dept---"+deptlist);
			request.setAttribute("deptlist", deptlist);
			request.setAttribute("listcount1", listcount1);
			request.setAttribute("listcount2", listcount2);
			request.setAttribute("year", year);
//			request.setAttribute("str", "2");
			request.setAttribute("deptState", deptState);
			request.setAttribute("modules", modules);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryCheckStatisticsPhoto";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
