package com.sdkj.project.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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

import com.sdkj.project.service.ICompletionService;
import com.sdkj.project.service.IProjectService;
import com.sdkj.project.vo.CompleteApply;
import com.sdkj.project.vo.ProjectVo;
import com.sdkj.util.fileupload.BaseFileUpload;
import com.opensymphony.xwork2.ActionContext;

@Results( 
        value={ 
        		@Result(name="viewProjectCompletionPage",type="dispatcher",location="/jsp/project/completion/viewProjectCompletionPage.jsp"),
        		@Result(name="viewProjectCompletionPageFinish",type="dispatcher",location="/jsp/project/completion/viewProjectCompletionPageFinish.jsp"),
        		@Result(name="requery",type="redirect",location="/constructlog!queryConstructLog.action")
        }   
)
@Action("completion")
public class CompletionAction  extends BaseFileUpload{

	
	
	//struts2中response  request 属性
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	
	//前台交互实体
	private CompleteApply apply;
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private ICompletionService completionService;
	
	//项目实体
	private ProjectVo project;
	
	
	
	//文件名称
    private String[] fileName;
	//文件路径 
    private String[] filePath;
	
	/**
	 * 工程基本信息查看页
	 * 2014-03-04
	 */
	public String getProjectPage(){
		Map session = ActionContext.getContext().getSession();
		
		int projectId = (Integer)session.get("projectId");
		
		try {
			project=projectService.getProjectById(projectId);
			
			if(completionService.showCompletionCount(projectId) == 1){
				apply = completionService.showCompletion(projectId);
				List listfile = completionService.showCompletionFile(apply.getCa_id());
				request.setAttribute("fileList", listfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewProjectCompletionPage";
	}
	
	
	/**
	 * 添加竣工申请
	 * @throws IOException 
	 */
	public void addCompletionPro() throws IOException{
		try {
			Map session = ActionContext.getContext().getSession();
			int projectId = (Integer)session.get("projectId");
			int user_id = (Integer)session.get("user_id");
			apply.setPro_id(projectId);
			apply.setCa_user(user_id);
			
			completionService.delete(apply);
			completionService.addcompletionpro(fileName,filePath,apply);
			
			response.getWriter().print("{\"info\":\"y\",\"status\":\"y\"}");
		} catch (Exception e) {
			response.getWriter().print("{\"info\":\"n\",\"status\":\"n\"}");
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加保存功能
	 * @return
	 * @throws IOException
	 */
	public String addCompletionProFinish() throws IOException{
		try {
			Map session = ActionContext.getContext().getSession();
			int projectId = (Integer)session.get("projectId");
			int user_id = (Integer)session.get("user_id");
			apply.setPro_id(projectId);
			apply.setCa_user(user_id);
			
			completionService.delete(apply);
			completionService.addcompletionpro(fileName,filePath,apply);
			
			project=projectService.getProjectById(projectId);
			
			if(completionService.showCompletionCount(projectId) == 1){
				apply = completionService.showCompletion(projectId);
				List listfile = completionService.showCompletionFile(apply.getCa_id());
				request.setAttribute("fileList", listfile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "viewProjectCompletionPageFinish";
	}
	
	/**
	 * 下载文件功能
	 * @throws IOException
	 */
	public void fileDownload() throws IOException{
		request.setCharacterEncoding("utf-8");
		
		String sc_file_url = request.getParameter("sc_file_url");
		String sc_file_name = request.getParameter("sc_file_name");
		downLoadFile(request, response,sc_file_name,sc_file_url,"D:/fileUpload");
	}

	public void downLoadFile(HttpServletRequest request,HttpServletResponse response,String fileName,String realFileName,String dirPath) throws IOException {

		FileInputStream in = null;
		OutputStream out = null;
		try {
			
			 if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
				 fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
			 }
			 else {
				 fileName = URLEncoder.encode(fileName, "UTF-8");//IE浏览器
			 }
			 	
			 //头信息
			 	response.reset();
			    response.setContentType("application/octet-stream");
			    response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			    response.setHeader("Connection", "close");
			    
			    File file = new File(realFileName); //文件路径
			    
			if (file.exists()) {
				in = new FileInputStream(realFileName);
				out = response.getOutputStream();
				byte[] b = new byte[1024];
				for (int len = 0; (len = in.read(b)) != -1;) {
					out.write(b, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
		}
		
	}
	
	
	
	
	@Override
	public void saveFilePath(String myFileFileName, Object obj)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ProjectVo getProject() {
		return project;
	}

	public void setProject(ProjectVo project) {
		this.project = project;
	}


	public CompleteApply getApply() {
		return apply;
	}


	public void setApply(CompleteApply apply) {
		this.apply = apply;
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
	
	
	
	
	

}
