/**
 * 工程基本信息控制类
 * 2014-03-03
 * txb
 */
package com.sdkj.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.sdkj.project.service.IProjectService;
import com.sdkj.project.vo.ChangeApplyFileVo;
import com.sdkj.project.vo.ChangeApplyVo;
import com.sdkj.project.vo.ProjectChildVo;
import com.sdkj.project.vo.ProjectFileVo;
import com.sdkj.project.vo.ProjectVo;
import com.sdkj.system.vo.ExtTreeChildrenVo;
import com.sdkj.system.vo.ExtTreeVo;
import com.sdkj.system.vo.Menus;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;

@Service
public class ProjectServiceImpl implements IProjectService {
	@Autowired
	private Dao dao;
 
	
	/**
	 * 查询工程下子工程列表 2014-03-05 txb projectId:工程主键
	 */
	public List queryProjectChildList(int projectId) throws Exception {
		return dao.queryForList("queryProjectChildList", projectId);
	}

	/**
	 * 查询工程下子工程数 2014-03-05 txb projectId:工程主键
	 */
	public int getProjectChildCount(int projectId) throws Exception {
		return (Integer) dao.queryById("getProjectChildCount", projectId);
	}

	/**
	 * 通过工程主键查询工程信息 2014-03-05 txb projectId:工程主键
	 */
	public ProjectVo getProjectById(int projectId) throws Exception {
		return (ProjectVo) dao.queryById("getProjectById", projectId);
	}

	/**
	 * 添加工程基本信息 2014-03-05 txb project:工程vo
	 */
	@Transactional
	public int addProject(ProjectVo project, String userId) throws Exception {
		int pId = dao.save("addProject", project, "pro_id");

		// 工程进入待审批状态
		if ("8".equals(project.getPro_flag())) {
			SubmitApproval("15", String.valueOf(pId), userId, "",String.valueOf(project.getPro_id()));
		}
		return pId;
	}

	/**
	 * 修改工程基本信息 2014-03-05 txb project:工程vo
	 */
	@Transactional
	public void updateProject(ProjectVo project, String userId)
			throws Exception {
		dao.update("updateProject", project);

		// 工程进入待审批状态
		if ("8".equals(project.getPro_flag())) {
			SubmitApproval("15", String.valueOf(project.getPro_id()), userId,"",String.valueOf(project.getPro_id()));
		}
	}

	/**
	 * 通过主键获取子工程 2014-03-05 txb pc_id:子工程id
	 */
	public ProjectChildVo getProjectChildById(String pc_id) throws Exception {
		return (ProjectChildVo) dao.queryById("getProjectChildById", pc_id);
	}

	/**
	 * 修改子工程 2014-03-05 txb proChild:子工程vo
	 */
	public void updateProChild(ProjectChildVo proChild) throws Exception {
		dao.update("updateProChild", proChild);
	}

	/**
	 * 添加子工程 2014-03-05 txb proChild:子工程vo
	 */
	public void addProChild(ProjectChildVo proChild) throws Exception {
		dao.update("addProChild", proChild);
	}

	/**
	 * 添加单位工程 2014-03-05 txb proChild:子工程vo
	 */
	public void addProChildDw(ProjectChildVo proChild) throws Exception {
		dao.update("addProChildDw", proChild);
	}

	/**
	 * 删除子工程 2014-03-05 txb pc_id:子工程id
	 */
	@Transactional
	public void delProjectChild(String pc_ids) throws Exception {
		
		String[] temp=pc_ids.split(",");
		for(int i=0;i<temp.length;i++){
			if(null!=temp[i]&&!"".equals(temp[i])){
				String pc_id=temp[i];
				// 获取同级子工程数
				int count = (Integer) dao.queryById("getProjectChildByPid", pc_id);
				if (count == 1) {// 如果子工程下仅有当前工程则修改标示为0
					dao.delete("updatePrjectChildParent", pc_id);
				}
				
				/**
				 * 删除计划进度 计划成本
				 */
				dao.delete("deleteChildCostPlan", pc_id);
				dao.delete("deleteChildProgressPlan", pc_id);
				
				dao.delete("delProjectChild", pc_id);
			}
			
		}
		
		
		
	}

	/**
	 * 添加工程文件 2014-03-05 txb fileName:文件名 id:工程主键
	 */
	public void addProjectFile(String[] fileName, String[] filePath, int id)
			throws Exception {
		for (int i = 0; i < fileName.length; i++) {
			ProjectFileVo pf = new ProjectFileVo();
			pf.setPro_id(id);
			pf.setPf_name(fileName[i]);
			pf.setPf_url(filePath[i]);

			dao.save("addProjectFile", pf);
		}

	}

	/**
	 * 获取文件信息 2014-03-05 txb projectId:工程id
	 */
	public List getFileByProject(int projectId) throws Exception {
		return dao.queryForList("getFileByProject", projectId);
	}

	/**
	 * 工程选择工程获取工程列表 2014-03-05 txb user_id:用户id role_id:角色名 pro_name:工程名关键字
	 * pro_state:施工状态 pagination:分页
	 */
	public List getProListByRole(int user_id, int dept_id, String pro_name,
			String pro_state, Pagination pagination) throws Exception {

		StringBuffer str = new StringBuffer();
		str.append("select pro_id,pro_name,pro_type,pro_invest,pro_measure,pro_start_date,");
		str.append("pro_end_date,pro_place,pro_manager,pro_state,pro_describe,pro_flag from project");
		str.append(" where 1=1");
		if (null != pro_name && !"".equals(pro_name)) {
			str.append(" and pro_name like '%" + pro_name + "%'");
		}
		if (null != pro_state && !"".equals(pro_state)) {
			str.append(" and pro_state =" + pro_state + "");
		}
		if (2 != dept_id && 7 != dept_id && 8 != dept_id) {// 用户不是工程技术部 只能查看已审核
			str.append(" and pro_flag =19");
		}
		if (5 == dept_id) {// 用户是施工项目部 只能看到所属项目
			str.append(" and ( pro_manager =" + user_id +" or pro_assistants like '%,"+user_id+",%' )");
		}
		str.append(" ORDER BY pro_id desc");
		pagination.setTotalCount((Integer) dao.queryById(
				"getProListCountByRole", str.toString()));

		String sql = Util.getLimitString(str.toString(), pagination);

		return dao.queryForList("getProListByRole", sql);
	}
	/**
	 * 
	 * describe:查询主页面报警信息
	 * @param userId
	 * @param roleId
	 * @return
	 * 2014-5-21
	 * @author txb
	 * @throws Exception 
	 */
	public Map queryWarning(String userId,String deptId) throws Exception{
		Map map = new HashMap();
		
		if("5".equals(deptId)){
			/**
			 * 施工部只显示 自己负责项目的前4条 质量安全报警
			 */
			List qclist = dao.queryForList("queryQualityWarningByUser",userId);
			List sclist = dao.queryForList("querySafeWarningByUser",userId);
			
			map.put("qclist", qclist);
			map.put("sclist", sclist);
			
		}else{
			/**
			 * 其他部门显示所有项目前4条 质量安全报警
			 */
			List qclist = dao.queryForList("queryAllQualityWarning");
			List sclist = dao.queryForList("queryAllSafeWarning");
			
			map.put("qclist", qclist);
			map.put("sclist", sclist);
		}
		
		return map;
	}
	/**
	 * 删除文件信息 2014-03-05 txb projectId:工程id
	 */
	public void delProjectFile(String delFileId) throws Exception {
		if (null != delFileId && !"".equals(delFileId)) {
			delFileId = delFileId.substring(0, delFileId.length() - 1);
			dao.update("delProjectFile", delFileId);
		}
	}

	/**
	 * 获取子工程下进度数 2014-03-05 txb pc_id:子工程id
	 */
	public int queryProgressByProChild(String pc_id) throws Exception {
		return (Integer) dao.queryById("queryProgressByProChild", pc_id);
	}

	/**
	 * 获取子工程下成本数 2014-03-05 txb pc_id:子工程id
	 */
	public int queryCostByProChild(String pc_id) throws Exception {
		return (Integer) dao.queryById("queryCostByProChild", pc_id);
	}

	/**
	 * 更改工程标示 2014-03-05 txb projectId:工程id
	 */
	@Transactional
	public void changeProjectFlag(int projectId, String userId)
			throws Exception {
		dao.update("changeProjectFlag", projectId);
		// 提交

		// 工程进入待审批状态
		SubmitApproval("15", String.valueOf(projectId), userId, "",String.valueOf(projectId));
	}
	/**
	 * 
	 * describe:查询合同状态
	 * @param proId
	 * @return
	 * 2014-5-19
	 * @author txb
	 * @throws Exception 
	 */
	public String queryContractStatus(String proId) throws Exception{
		return (String) dao.queryById("queryContractStatus", proId);
	}
	/**
	 * 
	 * describe:查询工程状态
	 * @param proId
	 * @return
	 * @throws Exception
	 * 2014-5-19
	 * @author txb
	 */
	public Map queryProjectStatus(String proId) throws Exception{
		return (Map) dao.queryById("queryProjectStatus", proId);
	}
	/**
	 * 通过名称查询工程 2014-03-05 txb projectId:工程id
	 */
	public int getProjectByName(String pro_name, int pro_id) throws Exception {
		String sql = "select count(*) from project where pro_name='" + pro_name
				+ "'";
		if (!"".equals(pro_id) && 0 != pro_id) {
			sql += " and pro_id !=" + pro_id;
		}
		return (Integer) dao.queryById("getProjectByName", sql);
	}

	/**
	 * 
	 * describe: 发送信息
	 * 
	 * @param sendUserId
	 *            发件人id
	 * @param receiveUserId
	 *            收件人id
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @throws Exception
	 *             2014-4-24
	 * @author txb
	 */
	public void sendMessage(String sendUserId, String receiveUserId,
			String title, String content) throws Exception { }

	/**
	 * 
	 * describe:
	 * 
	 * @param alType
	 *            审批类型 14：合同审批 15：工程审批 16： 变更审批 17：竣工审批
	 * @param alRelationId
	 *            合同id 或 工程id 等
	 * @param alUser
	 *            操作人员的id
	 * @param remark
	 *            备注信息
	 * @throws Exception
	 *             2014-4-24
	 * @author txb
	 */
	public void SubmitApproval(String alType, String alRelationId,
			String alUser, String remark,String proId) throws Exception { }

	/**
	 * 
	 * describe:查询变更请求列表
	 * 
	 * @return 2014-5-4
	 * @author txb
	 * @throws Exception
	 */
	public List queryChangeApply(Pagination pagination,String userId,String proId) throws Exception {
		StringBuffer sql = new StringBuffer(256);
		sql.append("SELECT  ");
		sql.append("t1.aa_id 'aaId',  ");
		sql.append("t1.pro_id 'proId',  ");
		sql.append("cast(date_format(t1.aa_date,'%Y-%m-%d %H:%i:%s') as char(19)) 'aaDate',  ");
		sql.append("t1.aa_user 'aaUser',  ");
		sql.append("t1.aa_remark 'aaRemark',  ");
		sql.append("t1.aa_flag 'aaFlag' , ");
		sql.append("t2.sc_name 'aaFlagName',");
		sql.append("t3.user_name 'aaUserName' ");
		sql.append("FROM  ");
		sql.append("alter_apply t1,system_code t2,system_user_info t3 where t1.pro_id = "+proId+" and t1.aa_flag = t2.sc_id and t1.aa_user = t3.id ");

		pagination.setTotalCount((Integer) dao.queryById(
				"getProListCountByRole", sql.toString()));

		String sql2 = Util.getLimitString(sql.toString(), pagination);
		
		return dao.queryForList("queryChangeApply", sql2);
	}

	/**
	 * 
	 * describe:添加变更请求 2014-5-4
	 * 
	 * @author txb
	 */
	@Transactional
	public void addChangeApply(ChangeApplyVo vo, String[] fileName,
			String[] filePath) throws Exception {
		int id = dao.save("addChangeApply", vo, "aaId");
		
		for(int i =0;i<fileName.length;i++){
			Map map = new HashMap();
			map.put("caId", id);
			map.put("cafName", fileName[i]);
			map.put("cafUrl", filePath[i]);
			dao.save("addChangeApplyFile", map);
		}
		
		if("30".equals(vo.getAaFlag())){ //表示提交
			SubmitApproval("16", String.valueOf(id), vo.getAaUser(), vo.getAaRemark(),vo.getProId());
		}
		
	}
	/**
	 * 
	 * describe:保存修改的变更申请
	 * @param vo
	 * @param fileName
	 * @param filePath
	 * 2014-5-6
	 * @author txb
	 * @throws Exception 
	 */
	@Transactional
	public void updChangeApply(ChangeApplyVo vo, String[] fileName,String[] filePath) throws Exception{
//		int id = dao.save("addChangeApply", vo, "aaId");
		dao.update("updChangeApply", vo);
		
		dao.delete("deleteChangeApplyFile",String.valueOf(vo.getAaId()));
		
		for(int i =0;i<fileName.length;i++){
			Map map = new HashMap();
			map.put("caId", vo.getAaId());
			map.put("cafName", fileName[i]);
			map.put("cafUrl", filePath[i]);
			dao.save("addChangeApplyFile", map);
		}
		
		if("30".equals(vo.getAaFlag())){ //表示提交
			SubmitApproval("16", String.valueOf(vo.getAaId()), vo.getAaUser(),vo.getAaRemark(),vo.getProId());
		}
	}
	/**
	 * 
	 * describe:提交暂存的变更申请
	 * @param id
	 * @param userId
	 * @throws Exception
	 * 2014-5-6
	 * @author txb
	 */
	@Transactional
	public void changeApplySubmit(String id,ChangeApplyVo vo) throws Exception{
		dao.update("updateChangeApplyStatus", id);
		SubmitApproval("16", id, vo.getAaUser(), vo.getAaRemark(),vo.getProId());
	}
	/**
	 * 
	 * describe:查询指定id的变更是否能删除
	 * @return
	 * 2014-5-5
	 * @author txb
	 * @throws Exception 
	 */
	public boolean queryStatus(String ids) throws Exception{
		String count = (String) dao.queryById("queryChangeApplyStatus", ids);
		if(Integer.valueOf(count)==0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * describe:删除变更请求
	 * @param ids
	 * @throws Exception
	 * 2014-5-5
	 * @author txb
	 */
	@Transactional
	public void deleteChangeApply(String ids) throws Exception{
		dao.delete("deleteChangeApply", ids);
		dao.delete("deleteChangeApplyFile", ids);
	}
	/**
	 * 
	 * describe:查询变更申请详情
	 * @param id
	 * @return
	 * 2014-5-6
	 * @author txb
	 * @throws Exception 
	 */
	public ChangeApplyVo queryChangeApplyDetail(String id) throws Exception{
		ChangeApplyVo vo = (ChangeApplyVo) dao.queryById("queryChangeApplyDetail", id); 
		List<ChangeApplyFileVo> files = (List<ChangeApplyFileVo>) dao.queryForList("queryChangeApplyFileDetail", id);
		vo.setFiles(files);
		return vo;
	}
	/**
	 * 
	 * describe:查询消息 报警统计
	 * @param userId
	 * @return
	 * @throws Exception
	 * 2014-5-16
	 * @author txb
	 */
	public List queryCountNum(String receiveDept) throws Exception{
	////System.out.println("receiveDept............"+receiveDept);
		String sql = "select count(*) as count,NotificationType as type  from jointnotification t 	where t.receivedept='"+receiveDept+"' and t.receiveop='未读' group by t.NotificationType";
	////System.out.println(sql);
		return dao.queryForList("queryCountNum", sql);
	}
	
	/**
	 * 获取用户权限菜单
	 */
	public List<ExtTreeVo> queryformenu(int role_id,String pro_id,String strstate,String s_id)
			throws Exception {
		List<ExtTreeVo> newMenuList = new ArrayList();
		// 临时保存主菜单项（一级菜单）
		Map<String, ExtTreeVo> mainMap = new LinkedHashMap<String, ExtTreeVo>();
		// 临时保存子菜单项（二级及以下有子节点的菜单）
		Map<String, ExtTreeChildrenVo> childMap = new HashMap<String, ExtTreeChildrenVo>();
		try {
			// 根据用户权限查询对应菜单，注：此处必须按菜单ID升序查询，否则将不能实现
			Map map=new HashMap();
			map.put("role_id", role_id);
			map.put("pro_id", pro_id);
			List<Menus> menuList = (List<Menus>) dao
					.queryForList("queryUserMenuById", map);
			// 对查询的菜单进行处理，处理成EXT识别的形式
			for (int i = 0; i < menuList.size(); i++) {
				Menus menu = menuList.get(i);
				// IS_CHILD为0时有子菜单
				if (0 == menu.getIs_child()) {
					if ("0".equals(menu.getMenu_pid())) {
						// 父ID为0时为一级主菜单，并放到主菜单MAP中
						ExtTreeVo mainTree = new ExtTreeVo();
						mainTree.setTitle(menu.getMenu_name());
						mainTree.setIconCls(menu.getMenu_image());
						mainTree.setMenuId(menu.getMenu_id());
						
						// 声明空的子菜单集合
						List<ExtTreeChildrenVo> childrenList = new ArrayList<ExtTreeChildrenVo>();
						mainTree.setChildren(childrenList);
						mainMap.put(menu.getMenu_id(), mainTree);
					} else {
						// 带有下级菜单的非主菜单，并存放到子菜单MAP中
						ExtTreeChildrenVo childTree = new ExtTreeChildrenVo();
						childTree.setText(menu.getMenu_name());
						childTree.setLeaf(false); // 是否为叶子节点
						childTree.setMenuId(menu.getMenu_id());
						childTree.setIconCls(menu.getMenu_image());
						
						// 声明空的子菜单集合
						List<ExtTreeChildrenVo> childrenList = new ArrayList<ExtTreeChildrenVo>();
						childTree.setChildren(childrenList);
						childMap.put(menu.getMenu_id(), childTree);
						// 获取父菜单对象,并将自己存放到父菜单的集合中
						ExtTreeVo fatherNode = mainMap.get(menu.getMenu_pid());
						fatherNode.getChildren().add(childTree);
					}
				} else {
					// 叶子节点
					ExtTreeChildrenVo childTree = new ExtTreeChildrenVo();
					childTree.setText(menu.getMenu_name());
					childTree.setLeaf(true);
					childTree.setHrefTarget("main");
					childTree.setMenuId(menu.getMenu_id());
					childTree.setIconCls(menu.getMenu_image());
					//childTree.setHref(request.getContextPath() + menu.getMenu_url());
					childTree.setHref(menu.getMenu_url());
					// 获取父菜单对象，并将自己存放到父菜单的集合中
					ExtTreeChildrenVo fatherNode = childMap.get(menu
							.getMenu_pid());
					ExtTreeVo mainNode = mainMap.get(menu.getMenu_pid());
					if (null != fatherNode)
						fatherNode.getChildren().add(childTree);
					else if (null != mainNode)
						mainNode.getChildren().add(childTree);

				}
			}
			// 将主菜单循环存放到LIST中
			Iterator it = mainMap.keySet().iterator();
			String[]	subPag = null ;
			subPag= String.valueOf(s_id).split(",");
			if(strstate.equals("1")){
				for(int i =0;i<subPag.length;i++){
//				//System.out.println("subPag[i]="+subPag[i]);
				newMenuList.add(mainMap.get(subPag[i].replaceAll(" ", "")));
				}
			}else{
				while (it.hasNext()) {
					String key = (String) it.next();
					newMenuList.add(mainMap.get(key));
				}
			}
		} catch (Exception e) {
			//////////System.out.println("获取用户权限菜单SERVICE错误：" + e);
			e.printStackTrace();
		}
		//JSONArray jsonArray = JSONArray.fromObject(newMenuList); // 得到JSON数组
		return newMenuList;
	}
	/**
	 * 
	 * describe:查询子节点实际成本 实际进度 录入情况 没有录入返回true
	 * @param id
	 * @return
	 * @throws Exception
	 * 2014-5-26
	 */
	public boolean queryChildActual(String ids) throws Exception{
		String[] temp=ids.split(",");
		for(int i=0;i<temp.length;i++){
			if(null!=temp[i]&&!"".equals(temp[i])){
				String id=temp[i];
				int cost = (Integer) dao.queryById("queryChildCostActual", id);
				int progress = (Integer) dao.queryById("queryChildProgressActual", id);
				
				if(cost != 0 || progress != 0){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 剪切粘贴子工程节点
	 * 2014-05-28
	 * txb
	 */
	@Transactional
	public void cutPaste(String from_id, String to_id) throws Exception {
		Map map=new HashMap();
		map.put("from_id", from_id);
		map.put("to_id", to_id);
		//原父节点下有多少子节点
		int count=(Integer)dao.queryById("getChildCount", from_id);
		//如果有1个，则变父节点is_child为0
		if(1==count){
			dao.update("changeIsChild", from_id);
		}
		
		//如果有多个则不变
		dao.update("cutPaste", map);
	}

	/**
	 * excel导入
	 * 20200305
	 * txb
	 */
	@Transactional
	public void excelInsertProChild(Map map, int projectId) throws Exception {
		Map idMap=new HashMap();
		for (Object key : map.keySet()) {
			Map proMap=(Map)map.get(key);
			//String pc_id="";
			if(1==(Integer)proMap.get("level")){//一层节点
				proMap.put("proId", projectId);
				proMap.put("pid", 0);
				proMap.put("pc_id", 0);
				dao.save("excelAddProChild", proMap);
				idMap.put(1, proMap.get("pc_id"));
			}else{//其他节点
				proMap.put("proId", projectId);
				int level=(Integer)proMap.get("level");//获取当前节点
				int pid=(Integer)idMap.get((level-1));//获取父节点
				proMap.put("pid", pid);
				proMap.put("pc_id", 0);
				dao.save("excelAddProChild", proMap);
				idMap.put(level, proMap.get("pc_id"));
			}
			//添加计划成本信息
			if(null!=proMap.get("measure")&&!"".equals(proMap.get("measure"))){
				proMap.put("pcId", proMap.get("pc_id"));
				dao.update("excelAddProgress", proMap);
			}
			//添加计划进度信息
			if(null!=proMap.get("costList")){
				List costList=(List)proMap.get("costList");
				for(int i=0;i<costList.size();i++){
					Map costMap=(Map)costList.get(i);
					costMap.put("pcId", proMap.get("pc_id"));
					dao.update("excelAddCost", costMap);
				}
			}
		}
	}
	
	/**
	 * excel导入
	 * 20200305
	 * txb
	 */
 
	/**
	 * 获取施工管理部下的用户
	 */
	public List getUser(String userId) throws Exception {
		String sql = "";
		if(userId==null||"".equals(userId)){
			sql="SELECT su.id,su.user_name FROM system_user_info su where su.role_id=5";
		}else{
			sql="SELECT su.id,su.user_name FROM system_user_info su where su.role_id=5 and su.id not in ("+userId+")";
		}
		return dao.queryForList("getUserForProject",sql);
	}

	/**
	 * 获取项目下得技术员
	 */
	public List getUserForProject(String userId) throws Exception {
		
		return dao.queryForList("getUserInProject",userId);
	}

	public List queryformenu(int role_id, String pro_id) throws Exception {

		List<ExtTreeVo> newMenuList = new ArrayList();
		// 临时保存主菜单项（一级菜单）
		Map<String, ExtTreeVo> mainMap = new LinkedHashMap<String, ExtTreeVo>();
		// 临时保存子菜单项（二级及以下有子节点的菜单）
		Map<String, ExtTreeChildrenVo> childMap = new HashMap<String, ExtTreeChildrenVo>();
		try {
			// 根据用户权限查询对应菜单，注：此处必须按菜单ID升序查询，否则将不能实现
			Map map=new HashMap();
			map.put("role_id", role_id);
			map.put("pro_id", pro_id);
			List<Menus> menuList = (List<Menus>) dao
					.queryForList("queryUserMenuById", map);
			// 对查询的菜单进行处理，处理成EXT识别的形式
			for (int i = 0; i < menuList.size(); i++) {
				Menus menu = menuList.get(i);
				// IS_CHILD为0时有子菜单
				if (0 == menu.getIs_child()) {
					if ("0".equals(menu.getMenu_pid())) {
						// 父ID为0时为一级主菜单，并放到主菜单MAP中
						ExtTreeVo mainTree = new ExtTreeVo();
						mainTree.setTitle(menu.getMenu_name());
						mainTree.setIconCls(menu.getMenu_image());
						mainTree.setMenuId(menu.getMenu_id());
						
						// 声明空的子菜单集合
						List<ExtTreeChildrenVo> childrenList = new ArrayList<ExtTreeChildrenVo>();
						mainTree.setChildren(childrenList);
						mainMap.put(menu.getMenu_id(), mainTree);
					} else {
						// 带有下级菜单的非主菜单，并存放到子菜单MAP中
						ExtTreeChildrenVo childTree = new ExtTreeChildrenVo();
						childTree.setText(menu.getMenu_name());
						childTree.setLeaf(false); // 是否为叶子节点
						childTree.setMenuId(menu.getMenu_id());
						childTree.setIconCls(menu.getMenu_image());
						
						// 声明空的子菜单集合
						List<ExtTreeChildrenVo> childrenList = new ArrayList<ExtTreeChildrenVo>();
						childTree.setChildren(childrenList);
						childMap.put(menu.getMenu_id(), childTree);
						// 获取父菜单对象,并将自己存放到父菜单的集合中
						ExtTreeVo fatherNode = mainMap.get(menu.getMenu_pid());
						fatherNode.getChildren().add(childTree);
					}
				} else {
					// 叶子节点
					ExtTreeChildrenVo childTree = new ExtTreeChildrenVo();
					childTree.setText(menu.getMenu_name());
					childTree.setLeaf(true);
					childTree.setHrefTarget("main");
					childTree.setMenuId(menu.getMenu_id());
					childTree.setIconCls(menu.getMenu_image());
					//childTree.setHref(request.getContextPath() + menu.getMenu_url());
					childTree.setHref(menu.getMenu_url());
					// 获取父菜单对象，并将自己存放到父菜单的集合中
					ExtTreeChildrenVo fatherNode = childMap.get(menu
							.getMenu_pid());
					ExtTreeVo mainNode = mainMap.get(menu.getMenu_pid());
					if (null != fatherNode)
						fatherNode.getChildren().add(childTree);
					else if (null != mainNode)
						mainNode.getChildren().add(childTree);

				}
			}
			// 将主菜单循环存放到LIST中
			Iterator it = mainMap.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					newMenuList.add(mainMap.get(key));
				}
		} catch (Exception e) {
			//////////System.out.println("获取用户权限菜单SERVICE错误：" + e);
			e.printStackTrace();
		}
		//JSONArray jsonArray = JSONArray.fromObject(newMenuList); // 得到JSON数组
		return newMenuList;
	
	}

	public String excelInsertPrjPerson(Map map, String prjId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
