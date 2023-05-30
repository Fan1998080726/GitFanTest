package com.sdkj.Delay.service;

import java.util.List;

import com.sdkj.Delay.vo.DelayVo;
import com.sdkj.util.Search.SearchVo;

public interface DelayService {

	/**
	 * 下属部门提交延期申请
	 * @param delayVo
	 */
	void SaveDelay(DelayVo delayVo) throws Exception	;

	/**
	 * 查看延期申请
	 * @param id	延期表ID
	 * @param mattersid  事项ID
	 * @param state 状态
	 * @return
	 * @throws Exception
	 */
	DelayVo queryDelayById(String id, String mattersid, String state) throws Exception;

	/**
	 * 查看申请延期信息
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<DelayVo> queryAllDelay(SearchVo searchVo) throws Exception;

	/**
	 * 延期申请审核
	 * @param delayVo
	 * @param str
	 * @throws Exception
	 */
	void SaveAuditDelay(DelayVo delayVo, String str) throws Exception;

}
