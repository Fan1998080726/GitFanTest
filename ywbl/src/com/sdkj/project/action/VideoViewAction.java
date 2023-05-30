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
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.project.service.IVideoView;
import com.sdkj.project.vo.RealViewVo;
import com.sdkj.project.vo.VideoViewVo;
import com.sdkj.util.context.ConvertVideo;
import com.sdkj.util.context.Pager;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.context.UtilConfig;
import com.opensymphony.xwork2.ActionContext;



@Results(value = {
		@Result(name = "videoViewList", type = "dispatcher", location = "/jsp/project/videoView/videoViewList.jsp"),
		@Result(name = "videoViewAdd", type = "dispatcher", location = "/jsp/project/videoView/videoViewAdd.jsp"),
		@Result(name = "videoViewDetail", type = "dispatcher", location = "/jsp/project/videoView/videoViewDetail.jsp"),
		@Result(name = "videoViewUpd", type = "dispatcher", location = "/jsp/project/videoView/videoViewUpd.jsp") })
@Action("videoview")
public class VideoViewAction {

	
	
	@Autowired
	private IVideoView iVideoView;
	
	
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	Map session = ActionContext.getContext().getSession();

	public File file;// 文件

	public String fileFileName; // 文件名称

	private String[] selectFlag; // 已选择的行
	
	Pagination pagination = new Pagination();
	
	private Pager pm;
	
	
	//视频实体
	private VideoViewVo videoViewVo;
	
	
	/**
	 * 
	 * describe: 查询视频列表
	 * 
	 * @return 2014-5-13
	 */
	public String videoViewList() {

		try {
			
			String offset = request.getParameter("pm.offset");
			
			if (null == offset)
				pagination.setCurrentPageNo(0);
			else
				pagination.setCurrentPageNo(Integer.parseInt(offset));
			
			pagination.setPageSize(15);
			Map<String, String> map = new HashMap<String, String>();
			
			Map session = ActionContext.getContext().getSession();
			
			List list = iVideoView.queryRealViewList(String.valueOf(session.get("projectId")),pagination);
			
			// 设置URL
			pagination.setUrl("videoview!videoViewList.action");
			// 存储参数条件到分页
			pagination.setHiddenMap(map);

			// 所设置返回列表的记录数
			pagination.setSize(list.size());			

			request.setAttribute("pm", pagination);
			request.setAttribute("list", list);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "videoViewList";
	}
	
	
	/**
	 * 
	 * describe:跳转到视频添加页面
	 * 
	 */
	public String videoViewAdd() {
		return "videoViewAdd";
	}
	
	
	
	/**
	 * 
	 * describe:保存添加的内容
	 * 
	 */
	public void saveVideoViewAdd() throws IOException {
		if (file != null) {
			if (file.length() > 10485760 * 10) {

				Map map = new HashMap();
				map.put("flag", "error");
				map.put("message", "文件大小不得超过100M！");
				response.getWriter().write(
						JSONObject.fromObject(map).toString());

			} else {

				String dirPath = "d:/gcjg/videoview/";
				String realName = System.currentTimeMillis() + "_"
						+ fileFileName;

				File savedir = new File(dirPath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				File savefile = new File(savedir, realName);
				FileUtils.copyFile(file, savefile);

				videoViewVo.setProId(String.valueOf(session.get("projectId")));
				videoViewVo.setVideoUser(String.valueOf(session.get("user_id")));
				
				videoViewVo.setVideoName(fileFileName);
				
				videoViewVo.setVideoUrl(dirPath + realName);
				try {
					
					String profilename = request.getSession().getServletContext().getRealPath("/");
					
					iVideoView.addVideoView(videoViewVo , profilename , realName);
					
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
	 * describe:修改添加的内容 
	 * 
	 */
	public void saveVideoViewUpd() throws IOException {
		try {
			
			  String profilename = request.getSession().getServletContext().getRealPath("/");
			  
			if (file != null) {
				if (file.length() > 10485760 * 10) {

					Map map = new HashMap();
					map.put("flag", "error");
					map.put("message", "文件大小不得超过100M！");
					response.getWriter().write(
							JSONObject.fromObject(map).toString());

				} else {

					String dirPath = "d:/gcjg/videoview/";
					String realName = System.currentTimeMillis() + "_"
							+ fileFileName;

					File savedir = new File(dirPath);
					if (!savedir.exists()) {
						savedir.mkdirs();
					}
					File savefile = new File(savedir, realName);
					FileUtils.copyFile(file, savefile);

					videoViewVo.setProId(String.valueOf(session.get("projectId")));
					videoViewVo.setVideoUser(String.valueOf(session.get("user_id")));
					
					videoViewVo.setVideoName(fileFileName);
					
					videoViewVo.setVideoUrl(dirPath + realName);
					
					iVideoView.updVideoView(videoViewVo ,  profilename , 0 , realName);
					
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
				videoViewVo.setProId(String.valueOf(session.get("projectId")));
				videoViewVo.setVideoUser(String.valueOf(session.get("user_id")));
				
				iVideoView.updVideoView(videoViewVo , profilename , 1 ,"");
				
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
	 * describe:修改视频跳转
	 * 
	 */
	public String videoViewUpd() {
		String id = request.getParameter("id");
		try {
			VideoViewVo vo = iVideoView.queryVideoView(id);
			request.setAttribute("videoView", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "videoViewUpd";
	}

 
	/**
	 * 
	 * describe:删除选中的行 
	 * 
	 * @throws IOException
	 */
	public void delVideoView() throws IOException {

		try {
			iVideoView.delVideoView(selectFlag);
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
	
	
	
	/**
	 * 
	 * describe:跳转视频详细
	 */
	public String videoViewDetail(){
		String id = request.getParameter("id");
		
		try {
			
			videoViewVo = iVideoView.queryVideoView(id);
			
			String httpurl = UtilConfig.getValue("url");
			
			request.setAttribute("httpurl", httpurl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "videoViewDetail";
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
	public String[] getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}
	public Pager getPm() {
		return pm;
	}
	public void setPm(Pager pm) {
		this.pm = pm;
	}


	public VideoViewVo getVideoViewVo() {
		return videoViewVo;
	}


	public void setVideoViewVo(VideoViewVo videoViewVo) {
		this.videoViewVo = videoViewVo;
	}
	
	
	
	
	
	
	
	
	
}
