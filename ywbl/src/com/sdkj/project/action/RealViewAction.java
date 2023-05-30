package com.sdkj.project.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdkj.project.service.IRealViewService;
import com.sdkj.project.vo.RealViewVo;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.opensymphony.xwork2.ActionContext;

@Results(value = {
		@Result(name = "realViewList", type = "dispatcher", location = "/jsp/project/realView/realViewList.jsp"),
		@Result(name = "realViewAdd", type = "dispatcher", location = "/jsp/project/realView/realViewAdd.jsp"),
		@Result(name = "realViewUpd", type = "dispatcher", location = "/jsp/project/realView/realViewUpd.jsp"),
		@Result(name = "realViewDetail", type = "dispatcher", location = "/jsp/project/realView/realViewDetail.jsp") 
		})
@Action("realView")
public class RealViewAction {
	@Autowired
	private IRealViewService realViewService;

	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	Map session = ActionContext.getContext().getSession();

	public File file;// 文件

	public String fileFileName; // 文件名称

	private String[] selectFlag; // 已选择的行
	
	Pagination pagination = new Pagination();
	private Pager pm;
	
	

	public Pager getPm() {
		return pm;
	}

	public void setPm(Pager pm) {
		this.pm = pm;
	}

	public String[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public RealViewVo rv;

	public RealViewVo getRv() {
		return rv;
	}

	public void setRv(RealViewVo rv) {
		this.rv = rv;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * 
	 * describe: 查询三维实景列表
	 * 
	 * @return 2014-5-13
	 * @author txb
	 */
	public String realViewList() {

		try {
			
			String offset = request.getParameter("pm.offset");
			
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
			
			pagination.setPageSize(15);
			Map<String, String> map = new HashMap<String, String>();
			
			Map session = ActionContext.getContext().getSession();
			
			List list = realViewService.queryRealViewList(String
					.valueOf(session.get("projectId")),pagination);
			
			// 设置URL
			pagination.setUrl("realView!realViewList.action");
			// 存储参数条件到分页
			pagination.setHiddenMap(map);

			// 所设置返回列表的记录数
			pagination.setSize(list.size());			

			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "realViewList";
	}

	/**
	 * 
	 * describe:跳转到三维实景添加页面
	 * 
	 * @return 2014-5-13
	 * @author txb
	 */
	public String realViewAdd() {
		return "realViewAdd";
	}

	/**
	 * 
	 * describe:修改三维实景
	 * 
	 * @return 2014-5-13
	 * @author txb
	 */
	public String realViewUpd() {
		String id = request.getParameter("id");
		try {
			RealViewVo vo = realViewService.queryRealView(id);
			request.setAttribute("realView", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "realViewUpd";
	}
	/**
	 * 
	 * describe:跳转三维实景详情页面
	 * @return
	 * 2014-5-14
	 * @author txb
	 */
	public String realViewDetail(){
		String id = request.getParameter("id");
		RealViewVo vo;
		try {
			vo = realViewService.queryRealView(id);
			request.setAttribute("realView", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "realViewDetail";
	}
	/**
	 * 
	 * describe:保存添加的内容 2014-5-13
	 * 
	 * @author txb
	 * @throws IOException
	 */
	public void saveRealViewAdd() throws IOException {
		if (file != null) {
			if (file.length() > 10485760) {

				Map map = new HashMap();
				map.put("flag", "error");
				map.put("message", "文件大小不得超过10M！");
				response.getWriter().write(
						JSONObject.fromObject(map).toString());

			} else {

				String dirPath = "d:/gcjg/realview/";
				String realName = System.currentTimeMillis() + "_"
						+ fileFileName;

				File savedir = new File(dirPath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				File savefile = new File(savedir, realName);
				FileUtils.copyFile(file, savefile);

				rv.setProId(String.valueOf(session.get("projectId")));
				rv.setRealUser(String.valueOf(session.get("user_id")));
				rv.setRealUrl(dirPath + realName);
				rv.setRealName(fileFileName);

				try {
					realViewService.addRealView(rv);
					Map map = new HashMap();
					map.put("flag", "success");
					map.put("message", "添加成功！");
					response.getWriter().write(
							JSONObject.fromObject(map).toString());

				} catch (Exception e) {

					Map map = new HashMap();
					map.put("flag", "error");
					map.put("message", "操作失败！");
					response.getWriter().write(
							JSONObject.fromObject(map).toString());
					e.printStackTrace();

				}
			}
		}
	}

	/**
	 * 
	 * describe:修改添加的内容 2014-5-13
	 * 
	 * @author txb
	 * @throws IOException
	 */
	public void saveRealViewUpd() throws IOException {
		try {
			if (file != null) {
				if (file.length() > 10485760) {

					Map map = new HashMap();
					map.put("flag", "error");
					map.put("message", "文件大小不得超过10M！");
					response.getWriter().write(
							JSONObject.fromObject(map).toString());

				} else {

					String dirPath = "d:/gcjg/realview/";
					String realName = System.currentTimeMillis() + "_"
							+ fileFileName;

					File savedir = new File(dirPath);
					if (!savedir.exists()) {
						savedir.mkdirs();
					}
					File savefile = new File(savedir, realName);
					FileUtils.copyFile(file, savefile);

					rv.setProId(String.valueOf(session.get("projectId")));
					rv.setRealUser(String.valueOf(session.get("user_id")));
					rv.setRealUrl(dirPath + realName);
					rv.setRealName(fileFileName);

					realViewService.updRealView(rv);
					Map map = new HashMap();
					map.put("flag", "success");
					map.put("message", "添加成功！");
					response.getWriter().write(
							JSONObject.fromObject(map).toString());

				}
			} else {
				/**
				 * 若没有上传文件 只修改其他信息
				 */
				rv.setProId(String.valueOf(session.get("projectId")));
				rv.setRealUser(String.valueOf(session.get("user_id")));
				realViewService.updRealView(rv);
				Map map = new HashMap();
				map.put("flag", "success");
				map.put("message", "添加成功！");
				response.getWriter().write(
						JSONObject.fromObject(map).toString());
			}
		} catch (Exception e) {

			Map map = new HashMap();
			map.put("flag", "error");
			map.put("message", "操作失败！");
			response.getWriter().write(JSONObject.fromObject(map).toString());
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * describe:删除选中的行 2014-5-13
	 * 
	 * @author txb
	 * @throws IOException
	 */
	public void delRealView() throws IOException {
		String selected = "";
		for (int i = 0; i < selectFlag.length; i++) {
			selected = selected + "," + selectFlag[i];
		}
		selected = selected.substring(1);

		try {
			realViewService.delRealView(selected);
			Map map = new HashMap();
			map.put("flag", "success");
			map.put("message", "删除成功！");
			response.getWriter().write(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			Map map = new HashMap();
			map.put("flag", "error");
			map.put("message", "删除失败！");
			response.getWriter().write(JSONObject.fromObject(map).toString());
			e.printStackTrace();
		}
	}
}
