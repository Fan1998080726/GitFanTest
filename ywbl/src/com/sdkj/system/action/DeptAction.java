/**
 * 部门管理控制类
 * 20150516
 * txb
 */
package com.sdkj.system.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdkj.system.service.IDeptService;
import com.sdkj.system.service.RoleManagerService;
import com.sdkj.system.vo.DeptVo;
import com.sdkj.system.vo.Menus;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.opensymphony.xwork2.ActionContext;

@Results( 
        value={ 
        		@Result(name="queryDept",type="dispatcher",location="/jsp/system/dept/queryDept.jsp"),
        		@Result(name="queryDeptUser",type="dispatcher",location="/jsp/system/dept/queryDeptUser.jsp"),
        		@Result(name="updateDeptPage",type="dispatcher",location="/jsp/system/dept/updateDeptPage.jsp"),
        		@Result(name="reQueryDept",type="redirect",location="dept!queryDept.action")
        		
        }   
)

@Action("dept")
public class DeptAction {
	
	@Autowired
	private RoleManagerService managerService;
	
	@Autowired
	private IDeptService deptService;
	
	private DeptVo deptVo;
	
	Pagination pagination = new Pagination();
	
	//struts2中response  request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	private Pager pm;
	
	/**
	 * 部门管理
	 * 20150511
	 * txb
	 */
	public String queryDept(){
		
		try {
			
			String deptName=request.getParameter("deptName");
			deptName=Util.nullToString(deptName);
			String offset = request.getParameter("pm.offset");
			
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
			pagination.setPageSize(15);
			Map<String, String> map = new HashMap<String, String>();
			map.put("deptName", deptName);
			
			List list = deptService.queryDept(deptName,pagination);
			
			// 设置URL
			pagination.setUrl("dept!queryDept.action");
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
			request.setAttribute("deptName", deptName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "queryDept";
	}
	
	/**
	 * 跳转部门添加修改页
	 * @return
	 */
	public String updateDeptPage(){
		
		if(null!=deptVo&&0!=deptVo.getDept_id()){//跳转添加页
			try {
				deptVo=deptService.getDeptById(deptVo.getDept_id());
				
				//获取部门人员列表
				//List list=deptService.getUserByDept(deptVo.getDept_id());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			deptVo=new DeptVo();
		}
		return "updateDeptPage";
	}
	
	
	/**
	 * 部门添加修改功能
	 * 20150512
	 * txb
	 */
	public void updateDept(){
		try {
			
			PrintWriter out;
			out=response.getWriter();
			
			int count = deptService.queryDeptCount(deptVo);
			if(count == 0){
			deptService.updateDept(deptVo);
				out.print("{\"info\":\"demo info\",\"pro_id\":\"\",\"status\":\"y\"}");
			}else{
				out.print("{\"info\":\"demo info\",\"pro_id\":\"\",\"status\":\"n\"}");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获取部门下人员列表
	 * 20140505
	 * txb
	 */
	public String queryDeptUser(){
		try {
			
			String dept_id=request.getParameter("dept_id");
			String app_user=request.getParameter("app_user");
			dept_id=Util.nullToString(dept_id);
			String offset = request.getParameter("pm.offset");
			
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
			
			pagination.setPageSize(15);
			Map<String, String> map = new HashMap<String, String>();
			map.put("dept_id", dept_id);
			map.put("app_user", app_user);
			List list = deptService.selectUserByDept(dept_id,pagination);
			
			// 设置URL
			pagination.setUrl("dept!queryDeptUser.action");
			// 存储参数条件到分页
			pagination.setHiddenMap(map);

			// 所设置返回列表的记录数
			pagination.setSize(list.size());
			//pagination.setTotalCount(150);

			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
			request.setAttribute("dept_id", dept_id);
			request.setAttribute("app_user", app_user);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "queryDeptUser";
	}
	

	
	/**
	 * 修改审批员
	 * 20140505
	 * txb
	 */
	public void updateAppUser(){
		try {
			String userId=request.getParameter("userId");
			String deptId=request.getParameter("deptId");
			deptService.updateAppUser(userId,deptId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除部门操作
	 * 20150620
	 * txb
	 */
	public void delDept(){
		
		try {
			String ids=request.getParameter("ids");
			PrintWriter out;
			out=response.getWriter();
			deptService.delDept(ids);
			out.print("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public DeptVo getDeptVo() {
		return deptVo;
	}
	public void setDeptVo(DeptVo deptVo) {
		this.deptVo = deptVo;
	}
	
	public Pager getPm() {
		return pm;
	}

	public void setPm(Pager pm) {
		this.pm = pm;
	}
	
}
