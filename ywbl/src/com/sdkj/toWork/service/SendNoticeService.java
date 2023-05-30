package com.sdkj.toWork.service;

import java.util.List;

import com.sdkj.toWork.vo.SendNoticeVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.Pagination;

public interface SendNoticeService {

	/**
	 * 通知公告
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	List querySendNotices(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 
	 * 发送通知公告
	 * @param noticeVo
	 * @throws Exception
	 */
	void SaveSendNotice(SendNoticeVo noticeVo) throws Exception;

	/**
	 * 公告信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SendNoticeVo queryNoticeById(String id) throws Exception;

	/**
	 * 处室查看公告信息
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	List querySendUserNotices(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 修改通知公告记录状态
	 * @param id
	 * @throws Exception
	 */
	void updateNoticeState(String id) throws Exception;

	/**
	 * 删除通知公告
	 * @param id
	 * @throws Exception
	 */
	void SendNoticeDel(String id) throws Exception;

	/**
	 * 撤销已发送的通知公告
	 * @param id
	 * @throws Exception
	 */
	void UndoNoticesState(String id) throws Exception;

}
