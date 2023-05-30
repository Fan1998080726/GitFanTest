package com.sdkj.check.service;

import java.util.List;

import com.sdkj.check.vo.CheckVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;

public interface CheckService {

	/**
	 * 考核统计
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List  queryCheckStatistics(SearchVo searchVo) throws Exception;

	/**
	 * 承办事项排名
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<CheckVo> queryMattersRank(SearchVo searchVo) throws Exception;

	List<CheckVo> queryEndMattersRank(SearchVo searchVo) throws Exception;

	/**
	 * 考核统计
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<CheckVo> queryCheckStatisticsPhoto(SearchVo searchVo)  throws Exception;

	/**
	 * 工作质量排名
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List queryWorkMassRank(SearchVo searchVo) throws Exception;

	/**
	 * 按周查看分值
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<CheckVo> queryPointScore(SearchVo searchVo) throws Exception;

	/**
	 * 导出工作质量排名
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<CheckVo> queryStandCheckList(SearchVo searchVo) throws Exception;

	/**
	 * 查询处室跟直属单位的信息
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<UserInfo> queryDeptUserInfo(SearchVo searchVo) throws Exception;

	/**
	 * 查看指定部门一年的的考核统计图
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	List<CheckVo> queryMonthDeptCheckStatisticsPhoto(SearchVo searchVo) throws Exception;

}
