package com.sdkj.feedback.service;

import java.util.List;

import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.Pagination;

public interface FeedBackService {

	/**
	 * 保存反馈事项
	 * @param feedbackVo
	 * @throws Exception
	 */
	void SaveFeedBack(FeedbackVo feedbackVo) throws Exception;

	/**
	 * 获取该事项的反馈记录
	 * @param id  事项ID
	 * @return 
	 * @throws Exception
	 */
	List<FeedbackVo> queryFeedBackList(String id) throws Exception;

	/**
	 * 删除反馈记录
	 * @param ids  反馈表ID
	 * @throws Exception
	 */
	void delFeedBack(String ids) throws Exception;

	/**
	 * 查看全部的反馈内容
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	List queryAllFeedBack(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 查看当前事项用户是否提交了 延期申请
	 * @param id  事项ID
	 * @param state  状态
	 * @return
	 * @throws Exception
	 */
	Integer queryRequestDelayCount(String id,String state) throws Exception;

	/**
	 * 查询是否所有用户都进行提交   为0时是都进行提交了
	 * @param mattersVo
	 * @return
	 * @throws Exception
	 */
	int queryHeyanCount(MattersVo mattersVo) throws Exception;

	/**
	 * 反馈信息查看
	 * @param id
	 * @return
	 * @throws Exception
	 */
	FeedbackVo queryFeedbackById(String id) throws Exception;
	/**
	 * 更改反馈信息状态
	 * @param id
	 * @param  str  1查看  2退回 
	 * @param content 退回意见
	 * @throws Exception 
	 */
	void UpdateFeedBackState(String id,String  str, String content) throws Exception;

	/**
	 * 查看退回的反馈信息
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List queryFeedback(SearchVo searchVo) throws Exception;
	
	/**
	 * 查看用户的反馈记录
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List QueryAllFeedback(SearchVo searchVo) throws Exception;

	/**
	 * 查询用户名
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String queryUserName(String id) throws Exception;

	/**
	 * 查看最新反馈内容
	 * @param matters_id
	 * @throws Exception
	 */
	String queryFeedBackContent(String matters_id) throws Exception;


}
