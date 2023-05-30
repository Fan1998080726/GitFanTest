package com.sdkj.matters.service;

import java.util.List;

import com.sdkj.check.vo.CheckVo;
import com.sdkj.matters.vo.CooperateUnitsVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.matters.vo.SiginTableVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.context.Pagination;

public interface MattersService {
	
	/**
	 * 事项管理
	 * @param searchVo 查询工具类
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public  List  queryMattersAdmin(SearchVo searchVo,Pagination pagination) throws Exception;

	/**
	 * 查询事项详情信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MattersVo queryMatterById(String id) throws Exception;
	
	/**
	 * 查询发送处室
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List queryPermissions(SearchVo searchVo) throws Exception;

	/**
	 * 保存前 删除 之前配合单位关联表
	 * @param matters_id 事项表中的关联ID
	 * @throws Exception
	 */
	public void deleteUnitsData(String matters_id) throws Exception;

	/**
	 * 保存配合单位关联表
	 * @param unitsVo
	 * @throws Exception
	 */
	public void SaveUnits(CooperateUnitsVo unitsVo) throws Exception;

	/**保存配合单位关联表
	 * 
	 * @param source_unit  勾选内容
	 * @param matters_id  关联事项ID
	 * @param  str  01  牵头单位   02 配合部门
	 * @throws Exception
	 */
	public void SaveUnitsStr(String source_unit, String matters_id,String  str) throws Exception;

	
	/**
	 * 保存事项表
	 * @param mattersVo
	 * @throws Exception
	 */
	public void SaveMatterData(MattersVo mattersVo) throws Exception;

	/**
	 * 删除图片
	 * @param fid   图片表C_ID
	 * @param string  图片类型
	 * @throws Exception
	 */
	public void delFileByReport(String fid, String string) throws Exception;
	/**
	 *	添加图片
	 * @param fileName  图片名
	 * @param filePath  图片路径
	 * @param type  图片类型
	 * @param saomiaodate  添加日期
	 * @param fid   图片关联表ID
	 * @param string  
	 * @param string2
	 */
	public void addReportFile_new(String[] fileName, String[] filePath, String[] type, String[] saomiaodate, String fid,
			String string, String string2) throws Exception;

	/**
	 * 获取图片数据
	 * @param fid   图片表C_ID  
	 * @param string  图片表类型
	 * @return
	 * 
	 * @throws Exception
	 */
	public List getUpfile(String fid, String string) throws Exception;
	
	/**
	 * 逻辑删除事项
	 * @param id
	 * @param matterId 事项关联ID
	 * @throws Exception
	 */
	public void UpdateMattersFlag(String id, String matterId) throws Exception;

	/**
	 * 若事项发生变动 提前办结
	 * @param id  事项ID
	 * @param endcontent  备注
	 * @throws Exception
	 */
	public void endState(String id, String endcontent) throws Exception;

		/**
		 * 用户事项管理
		 * @param searchVo
		 * @param pagination
		 * @return
		 * @throws Exception
		 */
	public List queryMattersUser(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 事项通知人查看事项时  修改事项关联表状态
	 * @param searchVo
	 */
	public void UpdateCooperateState(SearchVo searchVo) throws Exception;

		/**
		 * 查看反馈的图片
		 * @param id  事项ID
		 * @param string  图片类型
		 * @return
		 * @throws Exception
		 */
	public List getUpfiles(String id, String string) throws Exception;

	/**
	 * 修改事项状态
	 * @param matter_id
	 * @param state
	 * @param strtype   1  延期修改状态
	 * @param time  延期时间
	 *  @throws Exception
	 */
	public void updateMattersState(String matter_id, String state,String strtype, String time) throws Exception;

	
	/**
	 *  提交核验申请 当所有的去牵头部门都提交才能提交申请
	 * @param mattersVo
	 * @throws Exception
	 */
	public void SaveFeedBackCheck(MattersVo mattersVo) throws Exception;

	/**
	 * 修改用户提交表中的状态
	 * @param mattersVo
	 * @throws Exception
	 */
	public void SaveUnitsState(MattersVo mattersVo) throws Exception;

	/**
	 * 待核验审批数
	 * @param string
	 * @param string2
	 * @return
	 * @throws Exception
	 */
	public Integer queryRequestHeyanCount(String string, String string2) throws Exception;

	
	/**
	 * 查询带核验的事项
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List queryHeyanMatters(SearchVo searchVo) throws Exception;

	/**
	 * 事项审核
	 * @param mattersVo
	 * @throws Exception
	 */
	public void AuditMatters(MattersVo mattersVo) throws Exception;

	/**
	 * 综合查询
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryAllMattersData(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 数字状态
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public int queryCount(String str) throws Exception;

	/**
	 * 事项图片
	 * @param mattersVo
	 * @return  code为1  进行扣分
	 * @throws Exception
	 */
	public int BackMatters(MattersVo mattersVo) throws Exception;

	/**
	 * 保存用户信息
	 * @param userInfo
	 * @throws Exception
	 */
	public void SaveUserInfo(UserInfo userInfo) throws Exception;

	/**
	 * 保存签收信息
	 * @param siginTableVo
	 * @throws Exception
	 */
	public void SaveSigInfo(SiginTableVo siginTableVo) throws Exception;

	
	/**
	 * 查看事项的签收信息
	 * @param matters_id
	 * @return
	 */
	public List querySigInList(String matters_id) throws Exception;

	/**
	 * 查看反馈条数
	 * @param string
	 * @param string2
	 * @return
	 * @throws Exception
	 */
	public Integer queryFeedbackCountCount(String string, String string2) throws Exception;

	/**
	 * 反馈信息查看
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryFeedBackMatters(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 反馈退回数
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public int queryFeedbackCount(String string) throws Exception;

	/**
	 * 查询用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public UserInfo queryUserInfoById(String userid) throws Exception;
	
	/**
	 * 查询用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public String queryUserInfoOrById(String userid) throws Exception;

	/**
	 * 通讯录
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryUserInfo(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 查看事项
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public int queryMatterCount(String str) throws Exception;

	
	/**
	 * 查看公告数
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public int querySendCount(String str) throws Exception;

	/**
	 * 往年事项
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryFormerYearMatters(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 导出往年事项
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List  ExcelOldYearMatters(SearchVo searchVo) throws Exception;

	/**
	 * 下载综合查询的内容
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryExcelMattersData(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 已删除事项查看
	 * @param searchVo
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List queryDeleteMatters(SearchVo searchVo, Pagination pagination) throws Exception;

	/**
	 * 撤销已删除的事项
	 * @param id
	 */
	public void UndoMattersFlag(String id) throws Exception;

	/**
	 * 撤销已发送的事项
	 * @param id
	 * @throws Exception
	 */
	public void UndoSendMattersState(String id) throws Exception;
}
