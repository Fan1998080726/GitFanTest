/**
 * 通过导入excel生成  工程人员管理 人员数据20200305
 */
package com.sdkj.project.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdkj.project.service.IProjectService;

import com.sdkj.util.checkCID;//20200316

import com.opensymphony.xwork2.ActionContext;

@Results( 
        value={ 
        		@Result(name="getExcelUploadPage",type="dispatcher",location="/jsp/project/uploadExcel.jsp")
        }   
)

@Action("excelUpload")
public class ExcelUploadAction {

	public File myFile;
	public String myFileFileName;
	
	@Autowired
	private IProjectService projectService;
	
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	/**
	 * 跳转excel上传页
	 */
	public String getExcelUploadPage(){
		
		return "getExcelUploadPage";
	}
	
	/**
	 * 20200319  fcl
	 * */
	public void createProjectChild() throws IOException{
		String fileType = myFileFileName.substring(myFileFileName.lastIndexOf(".")+1,myFileFileName.length());
		PrintWriter out=response.getWriter();
		if(!"xls".equals(fileType)){
			out.print("{\"info\":\"请上传excel文件，后缀为xls！\",\"status\":\"n\"}");
			return;
		}
		Workbook book;
		try {
			book = Workbook.getWorkbook(myFile);
			// 获得第一个工作表对象
	        Sheet sheet = book.getSheet(0);
	        if(null==sheet){
	        	out.print("{\"info\":\"excel文件无数据！\",\"status\":\"n\"}");
				return;
	        }
	        System.out.println("sheet.getCell(0, 1).getContents()=="+sheet.getCell(0, 1).getContents());
	        if(!"1".equals(sheet.getCell(0, 1).getContents())){
	        	out.print("{\"info\":\"格式错误，错误在第2行第1列！\",\"status\":\"n\"}");
				return;
	        }
	        Map map=new HashMap();//excel内容存储
	        int maxLevel=1;//当前工程最高层级
	        int proChildNum=0;//子工程标示
	        Map idMap=new HashMap();
	        System.out.println("sheet.getRows()      "+ sheet.getRows() );
	        checkCID cid = new checkCID();
	        // 从第二行开始读取数据
	        for(int i=1;i<sheet.getRows();i++){
	        	Cell[] cells = sheet.getRow(i);
	        	//判断一行是否全部为空
	        	boolean empty=true; 
	        	System.out.println("cells.getRows()      "+ cells.length );
	        	for(int j=0;j<cells.length;j++){
	        		if(null!=cells[j].getContents()&&!"".equals(cells[j].getContents())){
	        			empty=false; 
	        		}
	        	}
	        	//如果一行为空则跳过处理
	        	if(empty){
	        		break;
	        	}
	        	System.out.println("如果一行为空则跳过处理   begin   " );
	        	if(cells!=null&&cells.length>0){  
	        		System.out.println("cells!=null&&cells.length>0      "+ cells.length );
	        		if(null!=cells[0].getContents()&&!"".equals(cells[0].getContents())){//第一列不为空，则表示层级
	        			int myLevel=0;
	        			try {
	        				myLevel=Integer.valueOf(cells[0].getContents());
	        				System.out.println("myLevel      "+ myLevel );
						} catch (NumberFormatException e) {
							out.print("{\"info\":\"格式错误，错误在第"+(i+1)+"行第1列！\",\"status\":\"n\"}");
							return;
						}
	        			System.out.println("cells.length      "+ cells.length );
	        			if(cells.length<3){
	        				out.print("{\"info\":\"格式错误，信息过少，错误在第"+(i+1)+"行！\",\"status\":\"n\"}");
							return;
	        			}
	        			System.out.println("  是否重新记录    begin  ");
	        			if(myLevel==1){//重新记录
	        				maxLevel=1;
	        			}else{//查看层级是否递增
	        				if(myLevel>maxLevel+1){
	        					out.print("{\"info\":\"层级没有递增，错误在第"+(i+1)+"行第1列！\",\"status\":\"n\"}");
								return;
	        				}
	        				maxLevel=myLevel+1;
	        				//查看父工程是否有计划进度、计划成本
	        			/*	Map temp=(Map)map.get(proChildNum);
	        				if(1==Integer.valueOf(temp.get("level")+"")){//上层节点即是父节点
	        					if(null!=temp.get("measure")&&!"".equals(temp.get("measure"))&&null!=temp.get("start")&&!"".equals(temp.get("start"))&&null!=temp.get("end")&&!"".equals(temp.get("end"))){
	        						out.print("{\"info\":\"有子工程的工程不可有计划进度，错误在第"+temp.get("row")+"行！\",\"status\":\"n\"}");
									return;
	        					}
	        					if(null!=temp.get("costList")){
	        						out.print("{\"info\":\"有子工程的工程不可有计划成本，错误在第"+temp.get("row")+"行！\",\"status\":\"n\"}");
									return;
	        					}
	        				}
	        				*/
	        			}
	        			System.out.println("  是否重新记录    end  ");
	        			proChildNum++;
	        			System.out.println("proChildNum="+proChildNum);
	        			
	        			idMap.put(myLevel, proChildNum);//添加当前父节点信息
	        			if(myLevel>1){//不是根节点则将其父节点is_child置为1
	        				int tempLevel=(Integer)idMap.get(myLevel-1);
	        				Map temp=(Map)map.get(tempLevel);
	        				temp.put("is_child", 1);
	        			}
	        			Map proMap=new HashMap();
	        			proMap.put("level", myLevel);//添加层级
	        			proMap.put("row", (i+1));//所在excel行
	        			proMap.put("is_child", 0);
	        			if(null==cells[1].getContents()||"".equals(cells[1].getContents())){
	        				out.print("{\"info\":\"工程名不可为空，错误在第"+(i+1)+"行第2列！\",\"status\":\"n\"}");
							return;
	        			}
	        			if(cells[1].getContents().length()>50){
	        				out.print("{\"info\":\"工程名不可多于50字符，错误在第"+(i+1)+"行第2列！\",\"status\":\"n\"}");
							return;
	        			}
	        			proMap.put("personName", cells[1].getContents());//工程名
	        			if(null==cells[2].getContents()||"".equals(cells[2].getContents())){
	        				out.print("{\"info\":\"身份证号不可为空，错误在第"+(i+1)+"行第3列！\",\"status\":\"n\"}");
							return;
	        			}
	        			if(cells[2].getContents().length()!=18){
	        				out.print("{\"info\":\"身份证号长度必须为18位，错误在第"+(i+1)+"行第3列！\",\"status\":\"n\"}");
							return;
	        			}
	        			if(null==cells[6].getContents()||"".equals(cells[6].getContents())){
	        				out.print("{\"info\":\"电话不可为空！，错误在第"+(i+1)+"行第6列！\",\"status\":\"n\"}");
	        				return;
	        			}
	        			String check =  cid.Verify(cells[2].getContents());
	        			System.out.println("cells[2].getContents()="+cells[2].getContents());
	        			if(check.equals("错误!")){
	        				out.print("{\"info\":\"身份证号格式错误！，错误在第"+(i+1)+"行第3列！\",\"status\":\"n\"}");
							return;
	        			}
	        			proMap.put("cardNum", cells[2].getContents());//身份证号

	        			System.out.println("  cells.length====  "+cells.length);
	        			if(cells.length>=6){
	        				
	        				System.out.println("  计划工程量   begin");
	        				System.out.println("cells[6].getContents()="+cells[6].getContents());
		        			if(null!=cells[6].getContents()&&!"".equals(cells[6].getContents())){//计划工程量
		        			
			        			if(!isNum(cells[6].getContents())){//判断开始时间是否是时间格式
			        				out.print("{\"info\":\"工程量格式不符，可有八位整数两位小数，错误在第"+(i+1)+"行第6列！\",\"status\":\"n\"}");
									return;
			        			}
			        			/*
			        			if(!isDate(cells[3].getContents())){//判断开始时间是否是时间格式
			        				out.print("{\"info\":\"时间格式不符，错误在第"+(i+1)+"行第4列！\",\"status\":\"n\"}");
									return;
			        			}
			        			if(!isDate(cells[4].getContents())){//判断开始时间是否是时间格式
			        				out.print("{\"info\":\"时间格式不符，错误在第"+(i+1)+"行第5列！\",\"status\":\"n\"}");
									return;
			        			}
	
			        			if(!isEarly(cells[3].getContents(),cells[4].getContents())){//开始时间是否小于结束时间
			        				out.print("{\"info\":\"开始时间不可大于结束时间，错误在第"+(i+1)+"行！\",\"status\":\"n\"}");
									return;
			        			}*/
			        			System.out.println("cells[5].getContents()="+cells[5].getContents());
			        			try {
			        				System.out.println("cells="+cells);
									System.out.println("cells[3].getContents()="+cells[3].getContents());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			        			proMap.put("personOne", cells[5].getContents());//工种
			        			proMap.put("phone", cells[6].getContents());//添加工程量
			        			proMap.put("corpName", cells[3].getContents());//添加开始时间
			        			proMap.put("personType", cells[4].getContents());//添加结束时间
		        			}
		        			System.out.println("  计划工程量   end");
	        			}
	        			map.put(proChildNum, proMap);
	        		}else{//计划成本
	        			System.out.println("  计划成本   begin   ");
	        			if(cells.length<9){
	        				out.print("{\"info\":\"格式错误，信息过少，错误在第"+(i+1)+"行！\",\"status\":\"n\"}");
							return;
	        			}
	        			
	        			String type=cells[7].getContents();//成本类型
	        			if(!"人工".equals(type)&&!"材料".equals(type)&&!"机械".equals(type)&&!"其他".equals(type)){//判断类型是否匹配
	        				out.print("{\"info\":\"成本类型只可为人工、材料、机械、其他，错误在第"+(i+1)+"行第8列！\",\"status\":\"n\"}");
							return;
	        			}
	        			try {
	        				Double value=Double.valueOf(cells[8].getContents());//判断成本值
	        			} catch (NumberFormatException e) {
							out.print("{\"info\":\"成本值格式不符，错误在第"+(i+1)+"行第9列！\",\"status\":\"n\"}");
							return;
						}
	        			Map costMap=new HashMap();
	        			if(null==cells[6].getContents()||"".equals(cells[6].getContents())){
	        				out.print("{\"info\":\"成本描述不可为空，错误在第"+(i+1)+"行第7行！\",\"status\":\"n\"}");
							return;
	        			}
	        			if(cells[6].getContents().length()>50){
	        				out.print("{\"info\":\"成本描述不可多于50字符，错误在第"+(i+1)+"行第7列！\",\"status\":\"n\"}");
							return;
	        			}
	        			costMap.put("describe", cells[6].getContents());
	        			costMap.put("type", cells[7].getContents());
	        			costMap.put("value", cells[8].getContents());
	        			
	        			Map proMap=(Map) map.get(proChildNum);
	        			List costList=(List)proMap.get("costList");//获取子工程的成本列表
	        			if(null==costList){
	        				costList=new ArrayList();
	        			}
	        			costList.add(costMap);
	        			proMap.put("costList", costList);
	        		}
	        		
	        		
					
	        	}    
	        	System.out.println("如果一行为空则跳过处理   end   " );
	        }
	        //入库
    		try {
    			Map session = ActionContext.getContext().getSession();
    			//int projectId=(Integer)session.get("projectId");
    			String prjId = (String)session.get("createUser");
    			System.out.println("prjId== "+prjId+"   map=== "+map.size() );
    			
    			String info = "";
				 info = projectService.excelInsertPrjPerson(map,prjId);
					System.out.println("info== "+info );
				 if(!info.equals("")){
					 out.print("{\"info\":\""+info+"！\",\"status\":\"n\"}");
						return;
				 }
				out.print("{\"info\":\"操作成功！\",\"status\":\"y\"}");
			} catch (Exception e) {
				e.printStackTrace();
			}
	        book.close();
	        
		} catch (BiffException e) {
			e.printStackTrace();
			out.print("{\"info\":\"文件不可用！\",\"status\":\"n\"}");
			return;
		}

	}

	/**
	 * 开始时间是否小于结束时间，小于返回true，大于返回false
	 * @param start
	 * @param end
	 * @return
	 */
	private boolean isEarly(String start, String end) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse(start);
			Date d2 = df.parse(end);
		    if(d2.getTime()>=d1.getTime()){
		    	return true;
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   
		return false;
	}


	/**
	 * 判断是否是时间格式 YYYY-MM-DD，是返回true，否返回false
	 * @param contents
	 * @return
	 */
	private boolean isDate(String contents) {
		String regEx = "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}"; //表示a或F  
		Pattern pat = Pattern.compile(regEx);  
		Matcher mat = pat.matcher(contents);  
		boolean rs = mat.find();   
		return rs;
	}

	/**
	 * 判断是否是数字格式 ，8位整数可有两位小数
	 * @param contents
	 * @return
	 */
	private boolean isNum(String contents){
		String regEx = "\\d{1,8}(?:\\.\\d{0,2})?"; //表示a或F  
		Pattern pat = Pattern.compile(regEx);  
		Matcher mat = pat.matcher(contents);  
		boolean rs = mat.find();   
		return rs;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}


	public String getMyFileFileName() {
		return myFileFileName;
	}


	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}


}
