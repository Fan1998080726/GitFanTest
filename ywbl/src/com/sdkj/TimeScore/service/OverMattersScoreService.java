package com.sdkj.TimeScore.service;

import java.util.List;

import com.sdkj.TimeScore.vo.EvaluationTableVo;
import com.sdkj.TimeScore.vo.StandardVo;
import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.Pagination;

import ognl.Evaluation;

public interface OverMattersScoreService {

	/**
	 * 获取超期未反馈的事项用户
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public  List<EvaluationTableVo>  GetOverFeedbackMatters(SearchVo searchVo) throws Exception;

	/**
	 * 超期未反馈的事项用户进行扣分  
	 * @param evaluationTableVo
	 * @return
	 * @throws Exception
	 */
	public String UpdateOverFeedBackScore(EvaluationTableVo evaluationTableVo) throws Exception;

	/**
	 * 插入评价表信息
	 * @param evaluationTableVo
	 * @throws Exception
	 */
	public void UpdateEvalutionData(EvaluationTableVo evaluationTableVo) throws Exception;

	/**
	 * 获取评价指标内容
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public StandardVo queryStandardById(String string) throws Exception;

	/**
	 * 查询用户列表
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryEvaluation(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 查询当前用户的事项
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryUserMattersList(SearchVo searchVo, Pagination pagination) throws Exception; 	
	
	/**
	 * 查询当前牵头用户的事项和分数
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List queryUserMattersScore(SearchVo searchVo ) throws Exception;

	/**
	 * 查询评价指标
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List queryStandList(SearchVo searchVo) throws Exception;

	/**
	 * 
	 * @param daysAfter   十五天后日期
	 * @param currentTime  当前日期
	 * @param str 0预警  1 超期
	 * @return
	 */
	public List<UserInfo> queryOverMattersUserPhone(String daysAfter, String currentTime,String str) throws Exception;

	/**
	 * 保存通知记录
	 * @param userInfo
	 * @throws Exception
	 */
	public void AddSendMsg(UserInfo userInfo) throws Exception;

	/**
	 * 查看用户分
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public String queryAfterScore(String userid) throws Exception;

	
	/**
	 * 查询本周一至周五未反馈的 用户
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<EvaluationTableVo> GetweeklyNotFeedbackUserInfo(SearchVo searchVo) throws Exception;
	
	/**
	 * 保存退回记录表
	 * @param feedbackVo
	 * @throws Exception
	 */
	public String  SaveEvakuaCheck(FeedbackVo feedbackVo) throws Exception; 	
}
