package com.sdkj.project.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.sdkj.project.vo.ChangeApplyVo;
import com.sdkj.project.vo.ProjectChildVo;
import com.sdkj.project.vo.ProjectVo;
import com.sdkj.util.context.Pagination;

public interface IProjectService {

	List queryProjectChildList(int projectId) throws Exception;

	int getProjectChildCount(int projectId) throws Exception;

	ProjectVo getProjectById(int projectId) throws Exception ;

	int addProject(ProjectVo project,String userId) throws Exception;

	void updateProject(ProjectVo project,String userId) throws Exception;

	ProjectChildVo getProjectChildById(String pc_id) throws Exception;

	void updateProChild(ProjectChildVo proChild) throws Exception;

	void addProChild(ProjectChildVo proChild) throws Exception;

	void addProChildDw(ProjectChildVo proChild) throws Exception;

	void delProjectChild(String pc_ids) throws Exception;

	void addProjectFile(String[] fileName, String[] filePath, int id) throws Exception;

	List getFileByProject(int projectId) throws Exception;

	List getProListByRole(int user_id,int dept_id, String pro_name, String pro_state,
			Pagination pagination) throws Exception;

	void delProjectFile(String delFileId) throws Exception;

	int queryProgressByProChild(String pc_id) throws Exception;

	int queryCostByProChild(String pc_id) throws Exception;

	void changeProjectFlag(int projectId,String userId) throws Exception;

	int getProjectByName(String pro_name, int pro_id) throws Exception;
	/**
	 * 
	 * describe:
	 * @param alType 审批类型 14：合同审批 15：工程审批 16： 变更审批 17：竣工审批
	 * @param alRelationId 合同id 或 工程id 等
	 * @param alUser 操作人员的id
	 * @param content 项目名称
	 * @throws Exception
	 * 2014-4-24
	 * @author txb
	 */
//	public void SubmitApproval(String alType,String alRelationId,String alUser,String content) throws Exception;
	/**
	 * 
	 * describe: 发送信息
	 * @param sendUserId 发件人id
	 * @param receiveUserId 收件人id
	 * @param title 标题
	 * @param content 内容
	 * @throws Exception
	 * 2014-4-24
	 * @author txb
	 */
	public void sendMessage(String sendUserId,String receiveUserId,String title,String content) throws Exception;
	/**
	 * 
	 * describe:查询变更请求列表
	 * 
	 * @return 2014-5-4
	 * @author txb
	 * @throws Exception 
	 */
	public List queryChangeApply(Pagination pagination,String userId,String priId) throws Exception;
	/**
	 * 
	 * describe:添加变更请求
	 * 2014-5-4
	 * @author txb
	 */
	public void addChangeApply(ChangeApplyVo vo,String[] fileName,String[] filePath) throws Exception;
	/**
	 * 
	 * describe:查询指定id的变更是否能删除
	 * @return
	 * 2014-5-5
	 * @author txb
	 * @throws Exception 
	 */
	public boolean queryStatus(String ids) throws Exception;
	/**
	 * 
	 * describe:删除变更请求
	 * @param ids
	 * @throws Exception
	 * 2014-5-5
	 * @author txb
	 */
	public void deleteChangeApply(String ids) throws Exception;
	/**
	 * 
	 * describe:查询变更申请详情
	 * @param id
	 * @return
	 * 2014-5-6
	 * @author txb
	 * @throws Exception 
	 */
	public ChangeApplyVo queryChangeApplyDetail(String id) throws Exception;
	/**
	 * 
	 * describe:提交暂存的变更申请
	 * @param id
	 * @param userId
	 * @throws Exception
	 * 2014-5-6
	 * @author txb
	 */
	public void changeApplySubmit(String id,ChangeApplyVo vo) throws Exception;
	/**
	 * 
	 * describe:保存修改的变更申请
	 * @param vo
	 * @param fileName
	 * @param filePath
	 * 2014-5-6
	 * @author txb
	 */
	public void updChangeApply(ChangeApplyVo vo, String[] fileName,String[] filePath)throws Exception;
	/**
	 * 
	 * describe:查询消息 报警统计
	 * @param userId
	 * @return
	 * @throws Exception
	 * 2014-5-16
	 * @author txb
	 */
	public List queryCountNum(String userId) throws Exception;
	/**
	 * 
	 * describe:查询工程状态
	 * @param proId
	 * @return
	 * 2014-5-19
	 * @author txb
	 * @throws Exception 
	 */
	public String queryContractStatus(String proId) throws Exception;
	/**
	 * 
	 * describe:查询工程状态
	 * @param proId
	 * @return
	 * @throws Exception
	 * 2014-5-19
	 * @author txb
	 */
	public Map queryProjectStatus(String proId) throws Exception;
	
	
	/**
	 * 查询菜单列表
	 * @param request 
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	public List queryformenu(int role_id,String pro_id,String strstate,String s_id)  throws Exception;
	public List queryformenu(int role_id,String pro_id)  throws Exception;
	/**
	 * 
	 * describe:查询主页面报警信息
	 * @param userId
	 * @param roleId
	 * @return
	 * 2014-5-21
	 * @throws Exception 
	 */
	public Map queryWarning(String userId,String roleId) throws Exception;
	/**
	 * 
	 * describe:查询子节点实际成本 实际进度 录入情况 没有录入返回true
	 * @param id
	 * @return
	 * @throws Exception
	 * 2014-5-26
	 */
	public boolean queryChildActual(String id) throws Exception;

	void cutPaste(String from_id, String to_id) throws Exception;

	void excelInsertProChild(Map map, int projectId) throws Exception;

	List getUser(String userId)throws Exception;

	List getUserForProject(String userId)throws Exception;
	
	
	
	/*20200305*/
	
String  excelInsertPrjPerson(Map map, String prjId) throws Exception;
}
