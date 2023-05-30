/**
 * 工程基本信息控制类
 * 2014-03-03
 * txb
 */
package com.sdkj.project.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
 import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.matters.service.MattersService;
import com.sdkj.project.service.IProjectService;
import com.sdkj.project.vo.ChangeApplyVo;
import com.sdkj.project.vo.ProjectChildVo;
import com.sdkj.project.vo.ProjectVo;
import com.sdkj.system.service.UserManagerService;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.fileupload.BaseFileUpload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Results( 
        value={ 
        		@Result(name="viewProjectPage",type="dispatcher",location="/jsp/project/viewProjectPage.jsp"),
        		@Result(name="updateProjectPage",type="dispatcher",location="/jsp/project/updateProjectPage.jsp"),
        		@Result(name="getProjectPage",type="redirect",location="project!getProjectPage.action"),
        		@Result(name="viewProChildListPage",type="dispatcher",location="/jsp/project/viewProChildListPage.jsp"),
        		@Result(name="updateProChildListPage",type="dispatcher",location="/jsp/project/updateProChildListPage.jsp"),
        		@Result(name="updateProjectChildPage",type="dispatcher",location="/jsp/project/updateProjectChildPage.jsp"),
//        		@Result(name="selectProject",type="dispatcher",location="/jsp/project/selectProject.jsp"),
        		@Result(name="selectProject",type="dispatcher",location="/welcomes.jsp"),
        		@Result(name="GasIndex",type="dispatcher",location="/gasindex.jsp"),
        		@Result(name="wisdomIndex",type="dispatcher",location="/wisdomIndex.jsp"),
        		@Result(name="mainPage",type="dispatcher",location="/main.jsp"),
        		@Result(name="selectManaPage",type="dispatcher",location="/jsp/project/selectManaPage.jsp"),
        		@Result(name="changeApply",type="dispatcher",location="/jsp/project/changeApply.jsp"),
        		@Result(name="changeApplyAddPage",type="dispatcher",location="/jsp/project/changeApplyAdd.jsp"),
        		@Result(name="changeApplyUpdPage",type="dispatcher",location="/jsp/project/changeApplyUpd.jsp"),
        		@Result(name="changeApplyDetail",type="dispatcher",location="/jsp/project/changeApplyDetail.jsp"),
        		@Result(name="success",type="redirect",location="project!goMainPage.action")        		
        }   
)

@Action("project")
public class ProjectAction extends BaseFileUpload{

	@Autowired
	private IProjectService projectService;
 
	@Autowired
	private UserManagerService managerService;
 
	 
	
    private String[] fileName; //文件名称
    
    private String[] filePath; //文件路径
    
    private String[] selectFlag; //已选择的行
    
    private String remark;
    
    private String[] selectUser;
    
	Pagination pagination = new Pagination();
	
	//struts2中response  request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	private ProjectVo project;
	private ProjectChildVo proChild;
	private String type;//操作类型  add:添加操作 update:修改操作
	private Pager pm;
	
	@Autowired
	private MattersService mattersService;

 
	/*20181105
	@Autowired
	private DaoRuService daoRuService;
	private PiCimessageVo piCimessageVo;
	
	20181105*/
 
	
	public String[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getFileName() {
		return fileName;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public String[] getFilePath() {
		return filePath;
	}

	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}

	
	
	
	
	/**
	 * 工程选择页
	 * 20150512
	 * txb
	 */
	public String selectProject(){
		System.out.println("into selectproject...");
		Map session = ActionContext.getContext().getSession();
		int dept_id=(Integer)session.get("dept_id");
		int role_id=(Integer)session.get("role_id");
		////System.out.println("role_id="+role_id);
		int user_id=(Integer)session.get("user_id");
 

			String pro_name=request.getParameter("pro_name");
			String complaintsState=(String)session.get("complaintsState");
			complaintsState = Util.nullToString(complaintsState);
			
			
			
			
			
			////System.out.println("pro_name="+pro_name);
 
		String dept_name=(String)session.get("dept_name");
		String role_name=(String)session.get("role_name");
		
		String username=(String)session.get("username");
		System.out.println("dept_name="+dept_name);
		
	 
			System.out.println("2222222222222222222");
			return "success";
	 
	}
	
	
	
	
	
	
//	public String selectProject(){
//		
//		Map session = ActionContext.getContext().getSession();
//		int dept_id=(Integer)session.get("dept_id");
//		int user_id=(Integer)session.get("user_id");
//		try {
//
//			String pro_name=request.getParameter("pro_name");
//			////////////////System.out.println("pro_name="+pro_name);
//			pro_name=Util.nullToString(pro_name);
//			////////////////System.out.println("pro_name="+pro_name);
//			String pro_state=request.getParameter("pro_state");
//			////////////////System.out.println("pro_state="+pro_state);
//			pro_state=Util.nullToString(pro_state);
//			////////////////System.out.println("pro_state="+pro_state);
//			String offset = request.getParameter("pm.offset");
//			////////////////System.out.println("offset="+offset);
//			if (null == offset)
//				pagination.setCurrentPageNo(0);
//			else
//				pagination.setCurrentPageNo(Integer.parseInt(offset));
//			
//			pagination.setPageSize(15);
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("pro_name", pro_name);
//			map.put("pro_state", pro_state);
//			
//			List projectList = projectService.getProListByRole(user_id,dept_id, pro_name,pro_state,pagination);
//			// 设置URL
//			pagination.setUrl("project!selectProject.action");
//			// 存储参数条件到分页
//			pagination.setHiddenMap(map);
//
//			// 所设置返回列表的记录数
//			pagination.setSize(projectList.size());			
//
//			request.setAttribute("pm", pagination);
//			request.setAttribute("projectList", projectList);
//			request.setAttribute("pro_name", pro_name);
//			request.setAttribute("pro_state", pro_state);
//			
//			
//			Map qualityMap = projectService.queryWarning(String.valueOf(user_id), String.valueOf(dept_id));
//			////////////////System.out.println("qualityMap.size()="+qualityMap.size());
//			request.setAttribute("qualityMap", qualityMap);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	 
//		return "selectProject";
//	}
	
	/**
	 * 选择工程后进入主页面
	 * 20150512
	 * txb
	 */
	public String goMainPage(){
		try {
			String pro_id=request.getParameter("pro_id");
			System.out.println("pro_id="+pro_id);
			Map<String, Object> session = ActionContext.getContext().getSession();
			String dept_name=(String)session.get("dept_name");
			String password = (String)session.get("password");
			
			
			
			String strstate = request.getParameter("strstate");
			strstate=Util.nullToString(strstate);
			
			String complaintsState=(String)session.get("complaintsState");
			complaintsState = Util.nullToString(complaintsState);
			String s_id=request.getParameter("s_id");
			s_id=Util.nullToString(s_id);
			
			String  deptname =(String) session.get("dept_name");
 
	
			if(session.get("role_id")==null){
				PrintWriter out = response.getWriter();  
                StringBuilder builder = new StringBuilder();  
                builder.append("<script type=\"text/javascript\">");  
                builder.append("alert(\"页面过期，请重新登录\");");  
                builder.append("window.top.location.href=\"");  
                builder.append("login.html\";</script>");  
                out.print(builder.toString());  
                out.close();   
//                return;    
			}else{
				int role_id=(Integer) session.get("role_id");
				/**
				 * 用户权限菜单列表
				 */
				List usermenu = projectService.queryformenu(role_id,pro_id,strstate,s_id);
				session.put("usermenu", usermenu);
				////////////////System.out.println("用户权限菜单列表......");
				 
				/**
				 * 全部
				 */
				int count0 = mattersService.queryCount("0");
				/**
				 * 新到
				 */
				int count1 = mattersService.queryCount("1");
				/**
				 * 配合
				 */
				int count4 = mattersService.queryCount("4");
				/**
				 * 待反馈
				 */
				int count3 = mattersService.queryCount("3");
				/**
				 * 超期
				 */
				int count5 = mattersService.queryCount("5");
				/**
				 * 带核验
				 */
				int count6 = mattersService.queryCount("6");
				/**
				 * 带核验
				 */
				int count7 = mattersService.queryCount("7");
				
				/**
				 * 核验退回
				 */
				int count8 = mattersService.queryCount("8");
				/**
				 * 办结
				 */
				int count9 = mattersService.queryCount("9");
				/**
				 * 反馈退回
				 */
				int count10 = mattersService.queryFeedbackCount("9");
				
				/**
				 * 全部事项
				 */
				int matterCount1 = mattersService.queryMatterCount("1");
				
				/**
				 * 即将到期事项
				 */
				int matterCount2 = mattersService.queryMatterCount("2");
				
				/**
				 * 超期
				 */
				int matterCount3 = mattersService.queryMatterCount("3");
 
				/**
				 * 办结事项
				 */
				int matterCount4 = mattersService.queryMatterCount("4");
				
				
				/**
				 * 通知公告
				 */
				int sendCount=mattersService.querySendCount("");
				
				
				
				
				request.setAttribute("sendCount", sendCount);
				request.setAttribute("matterCount1", matterCount1);
				request.setAttribute("matterCount2", matterCount2);
				request.setAttribute("matterCount3", matterCount3);
				request.setAttribute("matterCount4", matterCount4);
				
				
				request.setAttribute("count0", count0);
				request.setAttribute("count1", count1);
				request.setAttribute("count3", count3);
				request.setAttribute("count4", count4);
				request.setAttribute("count5", count5);
				request.setAttribute("count6", count6);
				request.setAttribute("count7", count7);
				request.setAttribute("count8", count8);
				request.setAttribute("count9", count9);
				request.setAttribute("count10", count10);
 				if(null!=pro_id&&!"".equals(pro_id)){
					////////////////System.out.println("用户权限菜单列表......111111");
					project=projectService.getProjectById(Integer.valueOf(pro_id));
//					ContractVo contract=contractService.getContractById(Integer.valueOf(pro_id));
					session.put("projectId", Integer.valueOf(pro_id));
					session.put("projectName", project.getPro_name());
					session.put("projectFlag", project.getPro_flag());
//					session.put("contractFlag", contract.getC_flag());
					
				}else{//跳过选择
					////////////////System.out.println("用户权限菜单列表......2222222");
					session.put("projectId", null);
					session.put("projectName",null);
					session.put("projectFlag", null);
					session.put("contractFlag", null);
					request.setAttribute("type", "skip");
					request.setAttribute("password", password);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mainPage";
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 工程基本信息查看页
	 * 2014-03-04
	 * txb
	 */
	public String getProjectPage(){
		Map session = ActionContext.getContext().getSession();
		
		int projectId;
		
		String proId = request.getParameter("projectId");
		
		if(StringUtils.isNotEmpty(proId)){//审批用工程信息
			projectId=Integer.valueOf(proId);
			request.setAttribute("flag", "0");
		}else{//工程管理模块查看
			projectId = (Integer)session.get("projectId");
		}
		
		try {
			project=projectService.getProjectById(projectId);
			List fileList=projectService.getFileByProject(projectId);
			String pro_assistants = "";
			if(project.getPro_assistants()!=null){
				String userId=project.getPro_assistants().substring( 1,project.getPro_assistants().length()-1);
				List userForProject = projectService.getUserForProject(userId);
				if(userForProject!=null){
					for(int i=0 ;i<userForProject.size();i++){
						Map map = (Map) userForProject.get(i);
						if("".equals(pro_assistants)){
							pro_assistants =(String) map.get("user_name"); //(String)userForProject.get(1);
						}else{
							pro_assistants = (String)pro_assistants+","+ (String) map.get("user_name");
						}
					}
				}
			}
			request.setAttribute("pro_assistants", pro_assistants);
			request.setAttribute("fileList", fileList);
			request.setAttribute("projectFlag", project.getPro_flag());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewProjectPage";
	}
	/**
	 * 子工程列表信息查看页
	 * 2014-03-06
	 * txb
	 */
	public String getProChildListPage(){
		
		try {
			Map session = ActionContext.getContext().getSession();
			int projectId=(Integer)session.get("projectId");
			ProjectVo project=projectService.getProjectById(projectId);
			request.setAttribute("projectFlag", project.getPro_flag());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "viewProChildListPage";
	}
	/**
	 * 子工程列表信息修改页
	 * 2014-03-06
	 * txb
	 */
	public String updateProChildListPage(){
		
		return "updateProChildListPage";
	}
	/**
	 * 工程基本信息添加修改页
	 * 2014-03-04
	 * txb
	 */
	public String updateProjectPage(){
		
		Map session = ActionContext.getContext().getSession();
		
		if("update".equals(type)){//修改页面
			int projectId=(Integer)session.get("projectId");
			try {
				List userForProject = null;
				List userList=null;
				project=projectService.getProjectById(projectId);
				List fileList=projectService.getFileByProject(projectId);
				if(project.getPro_assistants()==null||"".equals(project.getPro_assistants())){
					userForProject=null;
					userList=projectService.getUser("");
				}else{
					String userName=project.getPro_assistants();
					String userId=userName.substring( 1,userName.length()-1);
					//获取项目下得技术员
					userForProject = projectService.getUserForProject(userId);
					//获取施工管理部下得技术员
					userList = projectService.getUser(userId);
				}
				
				request.setAttribute("fileList", fileList);
				request.setAttribute("userList", userList);
				request.setAttribute("userForProject", userForProject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			project=new ProjectVo();
		}
		
		return "updateProjectPage"; 
	}
	
	/**
	 * 文件下载
	 * 20150512
	 * txb
	 * @throws IOException 
	 */
	public void fileDownload() throws IOException{
		String fileUrl=request.getParameter("fileUrl");
		String fileName=request.getParameter("fileName");
		downLoadFile(request, response,fileName,fileUrl);
	}
	
	/**
	 * 工程修改、添加功能
	 * 2014-03-06
	 * txb
	 */
	public void updateProject(){
		String delFileId=request.getParameter("delFileId");//删除文件id
		String[] fileName=request.getParameterValues("fileName");
		String[] filePath=request.getParameterValues("filePath");
		String selectUser = request.getParameter("selectUser");
		project.setPro_assistants(selectUser);
		project.setPro_state("4");
		
		try {			
			//校验工程名称是否重复
			int count=projectService.getProjectByName(project.getPro_name(),project.getPro_id());
			PrintWriter out=response.getWriter();
			if(count>0){
				out.print("{\"info\":\"demo info\",\"pro_id\":\"\",\"status\":\"n\"}");
				return;
			}
			Map session = ActionContext.getContext().getSession();
			if(0==project.getPro_id()){//工程添加
				int pro_id=projectService.addProject(project,String.valueOf(session.get("user_id")));
				
				project.setPro_id(pro_id);
				session.put("projectId", pro_id);
				session.put("projectName", project.getPro_name());
				session.put("projectFlag", project.getPro_flag());
				out.print("{\"info\":\"demo info\",\"pro_id\":\""+pro_id+"\",\"status\":\"y\"}");
				
			}else{//修改
				projectService.updateProject(project,String.valueOf(session.get("user_id")));
				projectService.delProjectFile(delFileId);
				out.print("{\"info\":\"demo info\",\"pro_id\":\""+project.getPro_id()+"\",\"status\":\"y\"}");
			}
			//附件添加
			if(null!=fileName&&0!=fileName.length){
				projectService.addProjectFile(fileName,filePath, project.getPro_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ajaxFileUpload() throws IOException{
		String id = request.getParameter("id");
		try {
			fileUploadAjax("d:/projectFile",id,30,1048576);
			response.getWriter().print("success");
		} catch (Exception e) {
			response.getWriter().print("error");
			e.printStackTrace();
		}
	}
	/**
	 * 实现父类文件信息存储功能
	 * 20150512
	 * txb
	 * @throws Exception 
	 */
	public void saveFilePath(String myFileFileName,Object obj) throws Exception {
		//projectService.addProjectFile(myFileFileName,Integer.valueOf(obj.toString()));
	}
	/**
	 * 获取工程基本信息子工程json串
	 * 2014-03-04
	 * txb
	 */
	public void getProjectChildListJson(){
		try {
			Map session = ActionContext.getContext().getSession();			
			int projectId=(Integer)session.get("projectId");
			PrintWriter out=response.getWriter();
			
			List list=projectService.queryProjectChildList(projectId);//获取tree节点信息
			
			ProjectChildVo pc=new ProjectChildVo();//添加根节点
			pc.setPc_id(0);
			pc.setPc_name("子工程列表");
			list.add(pc);
			
			int projectChildCount=projectService.getProjectChildCount(projectId);//获取tree节点总数
			Map map=new HashMap();
			map.put("total", projectChildCount);
			map.put("rows", list);
			String result=JSONArray.fromObject(map).toString();
			out.print(result.substring(1,result.length()-1));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转子工程添加、修改页
	 * @param project
	 */
	public String updateProjectChildPage(){
		//修改功能
		String pc_id=request.getParameter("pc_id");
		if(null!=pc_id&&!"".equals(pc_id)){
			
			try {
				proChild=projectService.getProjectChildById(pc_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//添加功能
		String pc_pid=request.getParameter("pc_pid");
		request.setAttribute("pc_pid", pc_pid);
		return "updateProjectChildPage";
	}
	/**
	 * 子工程删除方法
	 */
	public void delProjectChild(){
		String pc_ids=request.getParameter("pc_id");
		
		Map result = new HashMap();
		
		PrintWriter out=null;
		try {
			String mess="";
			
			//int progressCount=projectService.queryProgressByProChild(pc_id);
			//int costCount=projectService.queryCostByProChild(pc_id);
			
//			if(0!=progressCount||0!=costCount){
//				mess+="子工程下有计划进度或计划成本，请删除计划后再删除子工程！";
//			}else{
//				mess="ok";
//			}
			out=response.getWriter();
			
			/**
			 * 判断此节点是否录入了 实际成本 与 实际进度 如果录入 则此节点不能删除
			 */
			
			if(projectService.queryChildActual(pc_ids)){//可以删除
				projectService.delProjectChild(pc_ids);//删除子工程及其计划进度 计划成本
				
				result.put("flag", "1");//删除成功
			}else{//不可以删除
				result.put("flag", "2");//不能删除
			}
			out.print(JSONObject.fromObject(result).toString());
		} catch (Exception e) {
			result.put("flag", "3");
			out.print(JSONObject.fromObject(result).toString());
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	/**
	 * 工程提交
	 */
	public String changeProjectFlag(){
		Map session = ActionContext.getContext().getSession();			
		int projectId=(Integer)session.get("projectId");
		try {
			projectService.changeProjectFlag(projectId,String.valueOf(session.get("user_id")));
			session.put("projectFlag", "8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getProjectPage";
	}
	/**
	 * 
	 * describe:查询合同状态
	 * 2014-5-19
	 * @author txb
	 */
	public void queryContractStatus(){
		Map session = ActionContext.getContext().getSession();			
		int projectId=(Integer)session.get("projectId");
		try {
			String status = projectService.queryContractStatus(String.valueOf(projectId));
			
			Map map = new HashMap();
			map.put("status", status);
			response.getWriter().write(JSONObject.fromObject(map).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * describe:工程状态
	 * 2014-5-19
	 * @author txb
	 */
	public void queryProjectStatus(){
		Map session = ActionContext.getContext().getSession();			
		int projectId=(Integer)session.get("projectId");
		try {
			Map map = new HashMap();
			
			map = projectService.queryProjectStatus(String.valueOf(projectId));
			
			response.getWriter().write(JSONObject.fromObject(map).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 子工程添加、修改方法
	 * @param project
	 */
	public void updateProjectChild(){
		PrintWriter out=null;
		String pc_id=request.getParameter("pc_id");
		String pc_pid=request.getParameter("pc_pid");
		try {
			if(null!=pc_id&&!"".equals(pc_id)){//修改功能
				proChild.setPc_id(Integer.valueOf(pc_id));
				projectService.updateProChild(proChild);
				
			}else{//添加功能
				//添加单位工程
				if(null==pc_pid||"".equals(pc_pid)||"0".equals(pc_pid)){
					Map session = ActionContext.getContext().getSession();			
					int projectId=(Integer)session.get("projectId");
					proChild.setPro_id(projectId);
					projectService.addProChildDw(proChild);
				}else{//添加其他工程
					proChild.set_parentId(pc_pid);
					projectService.addProChild(proChild);
				}
				
			}
		
			out=response.getWriter();
			out.print("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	/**
	 * 跳转注册建造师选择页
	 * 20150512
	 * txb
	 */
	public String selectManaPage(){
		try {
			
			String user_code=request.getParameter("user_code");
			user_code=Util.nullToString(user_code);
			String offset = request.getParameter("pm.offset");
			
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
			
			pagination.setPageSize(15);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_code", user_code);
			
			List list = managerService.findUser(user_code,pagination, "");
			
			// 设置URL
			pagination.setUrl("project!selectManaPage.action");
			// 存储参数条件到分页
			pagination.setHiddenMap(map);

			// 所设置返回列表的记录数
			pagination.setSize(list.size());			

			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("user_code", user_code);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectManaPage";
	}
	/**
	 * 
	 * describe: 跳转到变更申请列表
	 * @return
	 * 2014-5-4
	 * @author txb
	 */
	public String changeApply(){
		
		try {
			String offset = request.getParameter("pm.offset");
			
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
			
			pagination.setPageSize(15);
			Map<String, String> map = new HashMap<String, String>();
			
			Map session = ActionContext.getContext().getSession();
			
			List list =  projectService.queryChangeApply(pagination,String.valueOf(session.get("user_id")),String.valueOf(session.get("projectId")));
			
			// 设置URL
			pagination.setUrl("project!changeApply.action");
			// 存储参数条件到分页
			pagination.setHiddenMap(map);

			// 所设置返回列表的记录数
			pagination.setSize(list.size());			

			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "changeApply";
		
		
	}
	/**
	 * 
	 * describe:跳转添加变更申请
	 * @return
	 * 2014-5-4
	 * @author txb
	 */
	public String changeApplyAddPage(){
		return "changeApplyAddPage";
	}
	/**
	 * 
	 * describe:跳转修改变更申请
	 * @return
	 * 2014-5-5
	 * @author txb
	 */
	public String changeApplyUpdPage(){
		String id = request.getParameter("id");
		ChangeApplyVo changeApply;
		try {
			changeApply = projectService.queryChangeApplyDetail(id);
			request.setAttribute("changeApply", changeApply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "changeApplyUpdPage";
	}
	/**
	 * 
	 * describe:保存修改的变更申请
	 * 2014-5-6
	 * @author txb
	 * @throws IOException 
	 */
	public void changeApplyUpd() throws IOException{
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		String aaFlag;
		if("1".equals(flag)){//暂存
			aaFlag="29";
		}else{//提交
			aaFlag="30";
		}
		try {
			Map session = ActionContext.getContext().getSession();
			ChangeApplyVo vo = new ChangeApplyVo();
			vo.setAaId(Integer.valueOf(id));
			vo.setAaRemark(remark);
			vo.setAaFlag(aaFlag);
			vo.setProId(String.valueOf(session.get("projectId")));
			vo.setAaUser(String.valueOf(session.get("user_id")));
			projectService.updChangeApply(vo, fileName, filePath);
			
			response.getWriter().write("yes"); //添加成功
		} catch (Exception e) {
			response.getWriter().write("no"); //添加失败
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * describe:查询变更状态
	 * @return
	 * 2014-5-5
	 * @author txb
	 * @throws IOException 
	 */
	public void changeApplyStatus() throws IOException{
		String id = request.getParameter("id");
		try {
			if(projectService.queryStatus(id)){ //可以删除
				response.getWriter().write("1");
			}else{//不可以删除
				response.getWriter().write("2");
			}
		} catch (Exception e) {
			response.getWriter().write("0");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * describe:保存新增的变更需求
	 * @return
	 * 2014-5-4
	 * @author txb
	 * @throws IOException 
	 */
	public void changeApplyAdd() throws IOException{
		String flag = request.getParameter("flag");
		String aaFlag;
		if("1".equals(flag)){//暂存
			aaFlag="29";
		}else{//提交
			aaFlag="30";
		}
		try {
			Map session = ActionContext.getContext().getSession();
			ChangeApplyVo vo = new ChangeApplyVo();
			vo.setAaRemark(remark);
			vo.setAaFlag(aaFlag);
			vo.setProId(String.valueOf(session.get("projectId")));
			vo.setAaUser(String.valueOf(session.get("user_id")));
			projectService.addChangeApply(vo, fileName, filePath);
			
			response.getWriter().write("yes"); //添加成功
		} catch (Exception e) {
			response.getWriter().write("no"); //添加失败
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * describe:提交暂存的变更申请
	 * @throws IOException
	 * 2014-5-6
	 * @author txb
	 */
	public void changeApplySubmit() throws IOException{
		Map session = ActionContext.getContext().getSession();
		String id = request.getParameter("id");
		String remark = request.getParameter("remark");
		
		ChangeApplyVo vo = new ChangeApplyVo();
		vo.setAaRemark(remark);
		vo.setProId(String.valueOf(session.get("projectId")));
		vo.setAaUser(String.valueOf(session.get("user_id")));
		try {
			projectService.changeApplySubmit(id,vo);
			response.getWriter().write("yes"); //提交成功
		} catch (Exception e) {
			response.getWriter().write("no"); //提交失败
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * describe:删除变更请求
	 * 2014-5-5
	 * @author txb
	 * @throws IOException 
	 */
	public void deleteChangeApply() throws IOException{
		String selected="";
		for(int i=0;i<selectFlag.length;i++){
			selected =  selected + "," + selectFlag[i];
		}
		selected = selected.substring(1);
		try {
			if(projectService.queryStatus(selected)){
				projectService.deleteChangeApply(selected);
				response.getWriter().write("2"); //删除成功
				return;
			}else{
				response.getWriter().write("1"); //有审批中的 不能删除
				return;
			}
		} catch (Exception e) {
			response.getWriter().write("0"); //删除失败
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * describe:跳转详情页面
	 * @return
	 * 2014-5-6
	 * @author txb
	 */
	public String changeApplyDetail(){
		String id = request.getParameter("id");//变更id
		try {
		ChangeApplyVo changeApply = projectService.queryChangeApplyDetail(id);
		request.setAttribute("changeApply", changeApply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "changeApplyDetail";
	}
	/**
	 * 
	 * describe:查询消息 报警 数量统计
	 * 2014-5-16
	 * @author txb
	 */
//	public void showCount(){
//			//////System.out.println("showCount............");
//		Map session = ActionContext.getContext().getSession();
//		String receiveDept=(String)session.get("dept_name");
//		//当前用户id
//		try {
//			List list = projectService.queryCountNum(receiveDept);
//		//////System.out.println("JSONArray.fromObject(list).toString()="+JSONArray.fromObject(list).toString().toLowerCase());
//			response.getWriter().write(JSONArray.fromObject(list).toString().toLowerCase());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * 
	 * describe:查询子工程下 是否可以被添加子工程
	 * 2014-5-26
	 * @author txb
	 * @throws IOException 
	 */
	public void queryChildStatus() throws IOException{
		String id = request.getParameter("pc_pid");
		Map result = new HashMap();
		try {
			
			int progressCount=projectService.queryProgressByProChild(id);
			int costCount=projectService.queryCostByProChild(id);
			
			if(0==progressCount && costCount==0){
				result.put("flag", "1");//可以添加
			}else{
				result.put("flag", "2");//不可以添加
			}
			response.getWriter().print(JSONObject.fromObject(result));
		} catch (Exception e) {
			result.put("flag", "3");
			response.getWriter().print(JSONObject.fromObject(result));
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 剪切粘贴子工程节点
	 * 2014-05-28
	 * txb
	 */
	public void cutPaste() throws IOException{
		String from_id=request.getParameter("from_id");//剪切节点
		String to_id=request.getParameter("to_id");//粘贴节点
		Map result = new HashMap();
		try {
			if("0".equals(to_id)){//粘贴到跟节点
				result.put("flag", "1");//可以添加
				projectService.cutPaste(from_id,to_id);
				response.getWriter().print(JSONObject.fromObject(result));
				return;
			}
			//判断粘贴节点是否有计划成本和计划进度
			int progressCount=projectService.queryProgressByProChild(to_id);
			int costCount=projectService.queryCostByProChild(to_id);
			
			if(0==progressCount && costCount==0){
				result.put("flag", "1");//可以添加
				projectService.cutPaste(from_id,to_id);
			}else{
				result.put("flag", "2");//不可以添加
			}
			response.getWriter().print(JSONObject.fromObject(result));
		} catch (Exception e) {
			result.put("flag", "3");
			response.getWriter().print(JSONObject.fromObject(result));
			e.printStackTrace();
		}

	}
	public void setProject(ProjectVo project) {
		this.project = project;
	}

	public ProjectVo getProject() {
		return project;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public ProjectChildVo getProChild() {
		return proChild;
	}
	public void setProChild(ProjectChildVo proChild) {
		this.proChild = proChild;
	}

	public Pager getPm() {
		return pm;
	}

	public void setPm(Pager pm) {
		this.pm = pm;
	}

	public String[] getSelectUser() {
		return selectUser;
	}

	public void setSelectUser(String[] selectUser) {
		this.selectUser = selectUser;
	}

	/*public PiCimessageVo getPiCimessageVo() {
		return piCimessageVo;
	}

	public void setPiCimessageVo(PiCimessageVo piCimessageVo) {
		this.piCimessageVo = piCimessageVo;
	}
*/
 
	
}
