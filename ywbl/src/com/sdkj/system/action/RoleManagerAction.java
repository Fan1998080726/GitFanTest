package com.sdkj.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdkj.system.service.IDeptService;
import com.sdkj.system.service.RoleManagerService;
import com.sdkj.system.vo.Menus;
import com.sdkj.system.vo.Role;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.opensymphony.xwork2.ActionContext;




@Results( 
        value={ 
        		@Result(name="query",type="dispatcher",location="/jsp/system/role/queryrole.jsp"),
        		@Result(name="addpage",type="dispatcher",location="/jsp/system/role/addrolepage.jsp"),
        		@Result(name="editpage",type="dispatcher",location="/jsp/system/role/editrolepage.jsp"),
        		@Result(name="roleMenuList",type="dispatcher",location="/jsp/system/role/roleMenuList.jsp"),
        		@Result(name="requery",type="redirect",location="/rolemanager!query.action")
        }   
)
@Action("rolemanager")
public class RoleManagerAction {

		@Autowired
		private RoleManagerService managerService;
		
		@Autowired
		private IDeptService deptService;
		
		/**
		 * 角色信息VO
		 */
		private Role role;
		
		private String[] selectFlag;
		
		private List<Menus> menuList;
		Pagination pagination = new Pagination();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		/**
		 * 显示角色列表
		 * @return
		 * @throws Exception
		 */
		public String query() throws Exception{
			//////////System.out.println("RoleManagerAction.java.sss...query()...bengin");
			
			try {
				
				String user_code=request.getParameter("user_code");
				user_code=Util.nullToString(user_code);
				System.out.println("user_code="+user_code);
				String offset = request.getParameter("pm.offset");
				
					if (null == offset)
						pagination.setCurrentPageNo(0);
					else
						pagination.setCurrentPageNo(Integer.parseInt(offset));
				
				pagination.setPageSize(15);
				Map<String, String> map = new HashMap<String, String>();
				map.put("user_code", user_code);
				List list = managerService.findRole(user_code,pagination);
				
				//////////System.out.println("list.size()="+list.size());

				
				// 设置URL
				pagination.setUrl("rolemanager!query.action");
				// 存储参数条件到分页
				pagination.setHiddenMap(map);

				// 所设置返回列表的记录数
				pagination.setSize(list.size());
				//pagination.setTotalCount(150);
				Map session = ActionContext.getContext().getSession();
				//获取用户权限
				String url=request.getServletPath();  
				int role_id=(Integer)session.get("role_id");
				
				Menus menu=managerService.getUserRight(url.substring(1),role_id);
				request.setAttribute("menu", menu);
				request.setAttribute("pm", pagination);
				request.setAttribute("list", list);
				request.setAttribute("user_code", user_code);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			//////////System.out.println("RoleManagerAction.java....query()...end");
			return "query";
		}
		
		
		/**
		 * 进入添加页面
		 * @return
		 * @throws Exception
		 */
		public String addPage() throws Exception{
			List deptList=deptService.getDeptList();
			request.setAttribute("deptList", deptList);
			return "addpage";
			
		}	
		
		/**
		 * 修改页面
		 * @return
		 * @throws Exception
		 */
		public String editPage() throws Exception{
			
			try {
				String id = request.getParameter("id");
				//////////System.out.println("id==="+id);
				role = managerService.queryforrole(id);
				//////////System.out.println("editPage()..."); 
				List deptList=deptService.getDeptList();
				//////////System.out.println("editPage()..sss."); 
				request.setAttribute("deptList", deptList);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return "editpage";
			
		}	
		
		/**
		 * 修改角色
		 * @return
		 * @throws Exception
		 */
		public void edit() throws Exception{
			
			try {
				/**
				 * 角色名称重复判断
				 */
				//20150609 txb update
				//List userList=managerService.queryforid(role.getRole_name(),role.getRole_id());
				List userList=null;
				if(null==userList||0==userList.size()){
					managerService.edit(role);
					response.getWriter().print("{\"info\":\"保存成功！\",\"status\":\"y\"}");
				}else{
					
					response.getWriter().print("{\"info\":\"y\",\"status\":\"n\"}");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		/**
		 * 增加角色
		 * @return
		 * @throws Exception
		 */
		public void add() throws Exception{
			
			try {
				/**
				 * 角色名称重复判断
				 */
				List userList=managerService.queryforid(role.getRole_name());
				if(null==userList||0==userList.size()){
					managerService.add(role);
					response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");
				}else{
					
					response.getWriter().print("{\"info\":\"y\",\"status\":\"n\"}");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//删除角色
		public void delete(){
			
			try {
				String ids=request.getParameter("ids");
				PrintWriter out=response.getWriter();
				managerService.delete(ids);
				out.print("ok");
			} catch (Exception e) {
				//e.printStackTrace();
			}
						
		}
			
		
		/**
		 * 根据权限ID查询权限菜单
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws MySecurityException
		 */
		public String getRoleMenuList() throws Exception {
//			String roleId = request.getParameter("roleId"); // 权限ID
//			List list = managerService.findRoleMenuList(roleId,"0"); // 获取权限菜单列表
//			if(null!=list){
//				for(int i=0;i<list.size();i++){
//					Menus menu=(Menus)list.get(i);
//					menu.setChild_menus(managerService.findRoleMenuList(roleId,menu.getMenu_id()));
//				}
//			}
//			request.setAttribute("list", list);
//			request.setAttribute("roleId", roleId);
//			return "roleMenuList";
			////////System.out.println("public String getRoleMenuList() throws Exception {...");
			String roleId = request.getParameter("roleId"); // 权限ID
			String user_code = request.getParameter("user_code"); // 权限ID
			System.out.println("user_code="+user_code);
			////////System.out.println("roleId==="+roleId);
			//System.out.println("roleId==="+roleId);
			List list = managerService.findRoleMenuList(roleId); // 获取权限菜单列表
			request.setAttribute("list", list);
			request.setAttribute("roleId", roleId);
			request.setAttribute("user_code", user_code);
			return "roleMenuList";
		}
		
		
		/**
		 * 保存权限菜单
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @throws MySecurityException
		 * @throws IOException 
		 */
		public void addRoleMenu() {
			try {
				String roleId = request.getParameter("roleId");
				managerService.addRoleMenu(menuList,roleId);
				PrintWriter out=response.getWriter();
				out.print("{\"info\":\"demo info\",\"pro_id\":\"\",\"status\":\"n\"}");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			try {
//				String ids = request.getParameter("ids");
//				String role_id = request.getParameter("role_id");
//				ids = Util.nullToString(ids);
//				managerService.insertOrDeleteRoleMenu(ids, role_id);
//				response.getWriter().print("{success}");
//			} catch (IOException e) {
//				e.printStackTrace();
//				response.getWriter().print("{failure}");
//			}
		}
		
		/**
		 * 用户添加修改页调用获取角色列表方法
		 * 20140505
		 * txb
		 */
		public void getRoleList(){
			String dept_id=request.getParameter("dept_id");
			//////////System.out.println("用户添加修改页调用获取角色列表方法");
			//////////System.out.println("dept_id="+dept_id);
			try {
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out=response.getWriter();
				List roleList=managerService.getRoleListByDept(dept_id);
				String result=JSONArray.fromObject(roleList).toString();
				//////////System.out.println("result="+result);
				
				out.print(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 获取用户权限
		 * @return
		 */
		public void getUserRight(){
			//////System.out.println("getUserRight......begin");
			String url=request.getParameter("url");
			//////System.out.println(url);
			if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManageZb!queryTBContractRecordManageZb.action"))
				url = "tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManageZb!queryTBContractRecordManageZb.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManagefb!queryTBContractRecordManagefb.action"))
					url = "tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManagefb!queryTBContractRecordManagefb.action&baseop=true";
			else 	if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManagelw!queryTBContractRecordManagelw.action"))
						url = "tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManagelw!queryTBContractRecordManagelw.action&baseop=true";
			else 	if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoKc!queryTBTenderInfoKc.action"))
							url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoKc!queryTBTenderInfoKc.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoSj!queryTBTenderInfoSj.action"))
						url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoSj!queryTBTenderInfoSj.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoSg!queryTBTenderInfoSg.action"))
								url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoSg!queryTBTenderInfoSg.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoJl!queryTBTenderInfoJl.action"))
								url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoJl!queryTBTenderInfoJl.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoHw!queryTBTenderInfoHw.action"))
								url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoHw!queryTBTenderInfoHw.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoZx!queryTBTenderInfoZx.action"))
								url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoZx!queryTBTenderInfoZx.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoLw!queryTBTenderInfoLw.action"))
								url = "tBProjectInfo!queryTBProjectInfo.action?op=tBTenderInfoLw!queryTBTenderInfoLw.action&baseop=true";												
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManage!queryTBContractRecordManage.action"))
					url = "tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManage!queryTBContractRecordManage.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBProjectCensorInfo!queryTBProjectCensorInfo.action"))
					url = "tBProjectInfo!queryTBProjectInfo.action?op=tBProjectCensorInfo!queryTBProjectCensorInfo.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManageKC!queryTBContractRecordManageKC.action"))
					url = "tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManageKC!queryTBContractRecordManageKC.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=feeBasedContent!queryFeeBasedContent.action"))
				url = "tBProjectInfo!queryTBProjectInfo.action?op=feeBasedContent!queryFeeBasedContent.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManagejl!queryTBContractRecordManagejl.action"))
				url = "tBProjectInfo!queryTBProjectInfo.action?op=tBContractRecordManagejl!queryTBContractRecordManagejl.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=qualitySupervision!queryQualitySupervision.action"))
				url = "tBProjectInfo!queryTBProjectInfo.action?op=qualitySupervision!queryQualitySupervision.action&baseop=true";
			else if(url.equals("tBProjectInfo!queryTBProjectInfo.action?op=safetyConstructionManage!querySafetyConstructionManage.action"))
				url = "tBProjectInfo!queryTBProjectInfo.action?op=safetyConstructionManage!querySafetyConstructionManage.action&baseop=true";
			try {
				Map session = ActionContext.getContext().getSession();
				
//				String role_ids = (String) session.get("role_id");
				int role_id=0;
//				if(!role_ids.equals("")||role_ids!=null){
//					role_id = Integer.parseInt(role_ids);
//				}else{
					role_id = (Integer)session.get("role_id");
//				}
				
//				System.out.println("role_id="+role_id);
				PrintWriter out=response.getWriter();
				Menus menu=managerService.getUserRight(url,role_id);
				String result=JSONArray.fromObject(menu).toString();
				//////////System.out.println("result="+result);
				out.print(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//////System.out.println("getUserRight......end");
		}
		
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}



		public String[] getSelectFlag() {
			return selectFlag;
		}



		public void setSelectFlag(String[] selectFlag) {
			this.selectFlag = selectFlag;
		}


		public List<Menus> getMenuList() {
			return menuList;
		}


		public void setMenuList(List<Menus> menuList) {
			this.menuList = menuList;
		}

		
		
}
