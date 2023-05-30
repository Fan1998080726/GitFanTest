package com.sdkj.util.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings("serial")
public class LoginFile extends HttpServlet implements Filter {

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		  ////////System.out.println("doFilterdoFilterdoFilterdoFilterdoFilterdoFilter");
		HttpServletResponse response = (HttpServletResponse) arg1;      
		HttpServletRequest request=(HttpServletRequest)arg0;   
 
	    String url=request.getRequestURI();   
	    
	    System.out.println("url====="+url);
	    
	    request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8"); 
        
         
        response.setContentType("text/html;charset=UTF-8");
        if(!url.endsWith("/") && url.indexOf("/log") == -1
        		&& !url.endsWith("/login.html")&& !url.endsWith("/complaintslogin.jsp")
        		&& url.indexOf("/mobile") == -1
        && url.indexOf("/spotProspectNotifica!queryfankui_print.action") == -1 
        && url.indexOf("/safetyConstructionManageCopyAction!getSafetyConstructionManageCopyById_print.action") == -1 
        && url.indexOf("/spotCondSurveyRelaBill!fankuiprint.action") == -1 
        && url.indexOf("/spotCondSurveyRelaBillJL!getfankuiRelaBillJLById_print.action") == -1
        && url.indexOf("/prjcheckinfo!printstatistics.action") == -1
        && url.indexOf("/prjcheckinfo!wxlogin.action") == -1
        && url.indexOf("/wx!wxloginNew.action") == -1
        && url.indexOf("/wx!sysManagerNew.action") == -1
        && url.indexOf("/wx!sysManagerView.action") == -1
        && url.indexOf("/wx!updateRegisterPersonNew.action") == -1
        && url.indexOf("/wx!wxloginInNew.action") == -1
        && url.indexOf("/prjcheckinfo!queryfarmersProject.action") == -1
        && url.indexOf("/wx!updateComplaints.action") == -1
        && url.indexOf("/wx!querqgzWxPerson") == -1
        		&& url.indexOf("/prjcheckinfo!queryProject.action") == -1	
        				&& url.indexOf("/prjcheckinfo!createTwoPic.action") == -1	
        				&& url.indexOf("/prjcheckinfo!createComplaintsTwoPic.action") == -1	
        						&& url.indexOf("/wx!consCorpLogin.action") == -1	
        								&& url.indexOf("/wx!updateroadcompany.action") == -1
        										&& url.indexOf("/wx!updateuserdemand.action") == -1
        										&& url.indexOf("/wx!jianliLogin.action") == -1
        										&& url.indexOf("/wx!updatesuperviosrcompany.action") == -1
        										&& url.indexOf("/wx!projectLogin.action") == -1
        										&& url.indexOf("/wx!updateProjectCheck.action") == -1
        && url.indexOf("/wx!checkPeronRegister.action") == -1//	20200331 txb 
        		&& url.indexOf("/wx!queryProject.action") == -1//	20200405 txb 						
  /*      && url.indexOf("/prjcheckinfo!wxloginIn.action") == -1
        && url.indexOf("/prjcheckinfo!weixinApp.action") == -1
        && url.indexOf("/prjcheckinfo!updateRegisterPersonPage.action") == -1
        && url.indexOf("/prjcheckinfo!updateRegisterPerson.action") == -1
        && url.indexOf("/prjcheckinfo!sysManager.action") == -1
        && url.indexOf("/prjcheckinfo!sysManagerView.action") == -1*/
        && url.indexOf("/zww!loginZww.action") == -1 
        && url.indexOf("/wjwgTj!getWjwgTj.action") == -1 
        && url.indexOf("/wjwgTj!getWjwgTj2.action") == -1 
        && url.indexOf("/audit!addauditAAA.action") == -1 
        && url.indexOf("/sysManager!queryDayExcel.action") == -1 
        && url.indexOf("/wx!queryRegcode") == -1 
        && url.indexOf("/project!goMainPage.action") == -1 
        && url.indexOf("/wx!queryexamine") == -1 
        && url.indexOf("/wx!queryTable") == -1 
        && url.indexOf("/gascom!gasRegNumber.action") == -1 
        && url.indexOf("/wx!queryById") == -1 
        && url.indexOf("/wx!queryWxSendRcordByID.action") == -1 
        && url.indexOf("/project!selectProject.action") == -1 
        && url.indexOf("/gascom!queryBasicInfo.action") == -1 
        && url.indexOf("/gasUser!getGasUserInfo.action") == -1 
        && url.indexOf("/wx!queryWxSendRcord.action") == -1 
        && url.indexOf("/admindept!Zipper.action") == -1 
        && url.indexOf("/admindept!DBBackZipper.action") == -1 
        && url.indexOf("/creditdatajson!GetDanti.action") == -1 
        && url.indexOf("/creditdatajson!GetProjectData.action") == -1 
        && url.indexOf("/creditdatajson!GetXkzData.action") == -1 
        && url.indexOf("/audit!queryAudit.action") == -1){
        		//System.out.println("into   Url"+url);
        		boolean  istype=getContentType(url);
        		////System.out.println("istype=="+istype);
        	if(getContentType(url)){
            	HttpSession session = request.getSession();
            	
        		String user="";
				try {
					 
//					Map session1 = ActionContext.getContext().getSession();
//					String role_id = (String) session1.get("role_id");
					Object str= session.getAttribute("user");
						//System.out.println("str="+str);
					if(str!=null){
						user = String.valueOf(str);
					}else{
						user = (String) session.getAttribute("user");
						//System.out.println("1111222");
					 	PrintWriter out = response.getWriter();  
	                    StringBuilder builder = new StringBuilder();  
	                    builder.append("<script type=\"text/javascript\">");  
	                    builder.append("alert(\"页面过期，请重新登录\");");  
	                    builder.append("window.top.location.href=\"");  
	                    builder.append("login.html\";</script>");  
	                    out.print(builder.toString());  
	                    out.close();   
	                    return;    
					}
					////System.out.println("user==="+user);
				} catch (Exception e) {
					e.printStackTrace();
					////System.out.println("11");
				
				}  
				//System.out.println("user="+user);
                if(user == null || "null".equals(String.valueOf(user))){
                	//System.out.println("66666666666");
              	  	PrintWriter out = response.getWriter();  
                    StringBuilder builder = new StringBuilder();  
                    builder.append("<script type=\"text/javascript\">");  
                    builder.append("alert(\"页面过期，请重新登录\");");  
                    builder.append("window.top.location.href=\"");  
                    builder.append("login.html\";</script>");  
                    out.print(builder.toString());  
                    out.close();   
                    return;    
        	   }
        	}
        }
        filterChain.doFilter(arg0, arg1);      
	}
	/*20181202 setp one search zww_xmbj last data time  sqljdbc4.jar*/
	public void init(FilterConfig arg0) throws ServletException {
			
	}

	protected boolean getContentType(String name) {
		////System.out.println("getContentType  TYpe=="+name);
		if (name.endsWith(".js")) {
			return false;
		} else if (name.endsWith(".css")) {
			return false;
		} else if (name.endsWith(".html")) {
			return false;
		} else if (name.endsWith(".txt")) {
			return false;
		} else if (name.endsWith(".gif")) {
			return false;
		} else if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
			return false;
		} else if (name.endsWith(".png")) {
			return false;
		}else if (name.endsWith(".swf")) {
			return false;
		} else {
			return true;
		}
	}

}
