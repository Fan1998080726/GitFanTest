package com.sdkj.TimeScore.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdkj.TimeScore.service.OverMattersScoreService;
import com.sdkj.TimeScore.vo.EvaluationTableVo;
import com.sdkj.TimeScore.vo.StandardVo;
import com.sdkj.matters.vo.ParmsVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.sendMsg.SendSms;

@Component
public class MattersScoreTiming {
	
	@Autowired
	private OverMattersScoreService mattersScoreService;
	
	
	private StandardVo standardVo;
	
	
	public StandardVo getStandardVo() {
		return standardVo;
	}


	public void setStandardVo(StandardVo standardVo) {
		this.standardVo = standardVo;
	}


	/**
	 * 超期未提交核验
	 */
	@Scheduled(cron = "0 0 13 * * ?")
	@PostConstruct
	public  void  OverFeedBackMatters() {
		try {
			System.out.println(" 超期未提交验核=============");
 			standardVo=mattersScoreService.queryStandardById("1");
			List<EvaluationTableVo> list = mattersScoreService.GetOverFeedbackMatters(null);
			for (EvaluationTableVo evaluationTableVo : list) {
				evaluationTableVo.setZbid(standardVo.getId());
				evaluationTableVo.setMarkScore(standardVo.getScore());
				String  aflterScore = 	mattersScoreService.UpdateOverFeedBackScore(evaluationTableVo);
				evaluationTableVo.setAlfter_score(aflterScore);
				evaluationTableVo.setScore(standardVo.getScore());
 				mattersScoreService.UpdateEvalutionData(evaluationTableVo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 每周五晚 23:50未牵头单位未反馈事项的 处室进行扣分
	 */
	@Scheduled(cron = "0 58 9 * * ?")
	public  void weeklyNotFeedback() {
		try {
			System.out.println("每周五晚 23:50未反馈事项的 处室进行扣分");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(CommonFunc.CurrentDateJianHao());
			 SearchVo  searchVo  = new SearchVo();
			searchVo.setOneWeekTime(sdf.format(getThisWeekMonday(date)));
			searchVo.setFiveWeekTime(sdf.format(gefirstWeekMonday(date)));
			String  beforeScore="";
 			standardVo=mattersScoreService.queryStandardById("6");
			List<EvaluationTableVo> list = mattersScoreService.GetweeklyNotFeedbackUserInfo(searchVo);
			for (EvaluationTableVo evaluationTableVo : list) {
				/**
				 * 之前分数
				 */
				beforeScore = mattersScoreService.queryAfterScore(evaluationTableVo.getUser_id());
				
				evaluationTableVo.setZbid(standardVo.getId());
				evaluationTableVo.setMarkScore(standardVo.getScore());
				evaluationTableVo.setUser_id(evaluationTableVo.getUser_id());
				evaluationTableVo.setMatters_id(evaluationTableVo.getMatters_id());
				evaluationTableVo.setBefore_score(beforeScore);
				/**
				 * 扣分后分数
				 */
				String  aflterScore = 	mattersScoreService.UpdateOverFeedBackScore(evaluationTableVo);
				evaluationTableVo.setAlfter_score(aflterScore);
				evaluationTableVo.setScore(standardVo.getScore());
				mattersScoreService.UpdateEvalutionData(evaluationTableVo);
				
				System.out.println("扣分。。。。。。。。。。");
			}
			System.out.println("扣分完毕！");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
	/**
	 * 短信预警
	 */
	@Scheduled(cron = "0 0 13 * * ?")
	public  void  OverMattersSendMsg() {
		try {
			System.out.println("短信预警=============");
			/**
			 * 十五天之后日期
			 */
			String daysAfter = CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),15);
			/**
			 * 当前日期
			 */
			String  currentTime = CommonFunc.CurrentDateJianHao();
			
			
			/**
			 * 预警通知
			 */
			List<UserInfo>  userInfo1 = mattersScoreService.queryOverMattersUserPhone(daysAfter,currentTime,"0");
			for (UserInfo userInfo : userInfo1) {
				
				if(userInfo.getPhone()==null) {
					userInfo.setPhone("");
				}
				if(!userInfo.getPhone().equals("")) {
					SendSms.sendOneKcsjSmsForNotice(userInfo.getUser_name(),"进入预警阶段",userInfo.getPhone());
				}
 				userInfo.setState("0");
 				mattersScoreService.AddSendMsg(userInfo);
  			}
			
			/**
			 * 超期通知
			 */
			List<UserInfo>  userInfo2 = mattersScoreService.queryOverMattersUserPhone(daysAfter,currentTime,"1");
			for (UserInfo userInfo : userInfo2) {
				if(userInfo.getPhone()==null) {
					userInfo.setPhone("");
				}
				if(!userInfo.getPhone().equals("")) {
				SendSms.sendOneKcsjSmsForNotice(userInfo.getUser_name(),"超期",userInfo.getPhone());
				}
				userInfo.setState("1");
				mattersScoreService.AddSendMsg(userInfo);
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
