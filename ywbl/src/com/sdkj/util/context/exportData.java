package com.sdkj.util.context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.opensymphony.xwork2.ActionContext;

public class exportData {
	@SuppressWarnings("deprecation")
	public static File export(List<String> tbProjectCensorInfoVo,String filepath,String temp[]){
		
	System.out.println("666666666666666");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle=wb.createCellStyle();   
		HSSFCellStyle cellStyle2=wb.createCellStyle();   
		HSSFCellStyle cellStyle3=wb.createCellStyle();   
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中      
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中  
		
		
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
//		cell.setCellStyle(style);
		
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cellStyle.setFillPattern(HSSFCellStyle.FINE_DOTS );
//		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
//		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
//		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
//		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 15);//设置字体大小
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		cellStyle.setFont(font);
		HSSFSheet sheet = wb.createSheet("new sheet");
		HSSFRow row = sheet.createRow(0);
		
		
		
		
	    
        String[] title = {"项目名称","建设单位","项目区域","项目组别","建设地点","建设单位联系人",
     		   "建设单位联系人手机","标段名称","检查日期","办理状态","建筑面积","建筑面积",
     		   "施工合同金额","总包单位","勘察单位","监理单位","设计单位","建造师姓名","总建造师姓名"};

		
		row.createCell(0).setCellValue("项目名称");
		row.createCell(1).setCellValue("建设单位");
		row.createCell(2).setCellValue("项目区域");
		row.createCell(3).setCellValue("项目组别");
		row.createCell(4).setCellValue("建设地点");
		row.createCell(5).setCellValue("建设单位联系人");
		row.createCell(6).setCellValue("建设单位联系人手机");
		row.createCell(7).setCellValue("标段名称");
		row.createCell(8).setCellValue("检查日期");
		row.createCell(9).setCellValue("办理状态");
		row.createCell(10).setCellValue("建筑面积");
		row.createCell(11).setCellValue("建筑面积");
		row.createCell(12).setCellValue("施工合同金额");
		row.createCell(13).setCellValue("总包单位");
		row.createCell(14).setCellValue("勘察单位");
		row.createCell(15).setCellValue("监理单位");
		row.createCell(16).setCellValue("设计单位");
		row.createCell(17).setCellValue("建造师姓名");
		row.createCell(18).setCellValue("总建造师姓名");
		
		Map session = ActionContext.getContext().getSession();
		String user = (String) session.get("username");
		System.out.println("User"+user);
		int i=1;
//		for(DuildSuperviseProject c:tbProjectCensorInfoVo){
//			
//			HSSFRow row2 = sheet.createRow(i);
//			
//			
//			row2.createCell(0).setCellValue(c.getDusuprName());
//			row2.createCell(1).setCellValue(c.getBuildCorpName());
//			row2.createCell(2).setCellValue(c.getDusuprArea());
//			row2.createCell(3).setCellValue(c.getDusuprGroup());
//			row2.createCell(4).setCellValue(c.getBuildCorpAddress());
//			row2.createCell(5).setCellValue(c.getPrjPropertyNum());
//			row2.createCell(6).setCellValue(c.getPrjApprovalLevelNum());
//			row2.createCell(7).setCellValue(c.getEngineeringname1());
//			row2.createCell(8).setCellValue(c.getConstructionplace1());
//			row2.createCell(9).setCellValue(c.getStates());
//			row2.createCell(10).setCellValue(c.getConstruction_area1());
//			row2.createCell(11).setCellValue(c.getConstruction_area1_unit());
//			row2.createCell(12).setCellValue(c.getContract_amount());
//			row2.createCell(13).setCellValue(c.getTotal_unit1());
//			row2.createCell(14).setCellValue(c.getKancha_unit());
//			row2.createCell(15).setCellValue(c.getSupervision_unit());
//			row2.createCell(16).setCellValue(c.getSheji_unit());
//			row2.createCell(17).setCellValue(c.getIschoose6name());
//			row2.createCell(18).setCellValue(c.getIschoose27name());
//			
////			for (int j = 0; j <19; j++) {
////				row2.createCell(0).setCellStyle(cellStyle);
////			}
//
//			i++;
//		}
		
//		System.out.println("tbProjectCensorInfoVo="+tbProjectCensorInfoVo);
		
//		System.out.println("temp="+temp);
		
		
		
		
		  Map<String, Integer> map = new HashMap<String, Integer>();
		 for (String str : temp) {
			   Integer num = map.get(str);
			   num = null == num ? 1 : num + 1;
			   map.put(str, num);
			  }
			 
			  if (temp.length != map.size()) {
//			   System.out.println("存在相同的元素及重复个数!");
			  }
			 
			  Set set = map.entrySet();
			  Iterator<Entry> it = set.iterator();
			  List<String> sList = new ArrayList<String>();
			  while (it.hasNext()) {
			   Entry<String, Integer> entry = (Entry<String, Integer>) it.next();
			   String key = entry.getKey();
			   Integer value = entry.getValue();
//			   System.out.println("String :" + key + " num :" + value);
			   if (value > 1)
			    sList.add(key);
			  }
			 
//			  System.out.println("============相同元素的下标======================");
			  List<ArrayList<Integer>> indexArr = new ArrayList<ArrayList<Integer>>();
			  for (String s : sList) {
			   ArrayList<Integer> aIntegers = new ArrayList<Integer>();
			   for (int j = 0;  j < temp.length;j++) {
			    if (temp[ j].equals(s)) {
			     aIntegers.add(j);
			    }
			   }
			   if (aIntegers.size() > 0)
			    indexArr.add(aIntegers);
			  }
			  System.out.println(indexArr);
			  int q=-1;
			  for (int s = 0; s< indexArr.size(); s++) {
//				  System.out.println("最大值"+indexArr.get(i).size());
//				  indexArr.get(i).size();
				  Integer a=indexArr.get(s).get(0)+2;
					if(a==0){
						a=2;
					} 
//				System.out.println("起点"+a);
				
				Integer end=indexArr.get(s).get(indexArr.get(s).size()-1);
				end=end+2;
//				System.out.println("终点"+end);
				
				 

				sheet.addMergedRegion(CellRangeAddress.valueOf("$A$"+a+":$A$"+end+"")); 
				sheet.addMergedRegion(CellRangeAddress.valueOf("$B$"+a+":$B$"+end+"")); 
				sheet.addMergedRegion(CellRangeAddress.valueOf("$C$"+a+":$C$"+end+"")); 
				sheet.addMergedRegion(CellRangeAddress.valueOf("$D$"+a+":$D$"+end+"")); 
				sheet.addMergedRegion(CellRangeAddress.valueOf("$E$"+a+":$E$"+end+"")); 
				sheet.addMergedRegion(CellRangeAddress.valueOf("$F$"+a+":$F$"+end+"")); 
				sheet.addMergedRegion(CellRangeAddress.valueOf("$G$"+a+":$G$"+end+"")); 
			}
		
		
//		 sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$A$3")); 
		sheet.setColumnWidth(0,15*2*256);
		sheet.setColumnWidth(1,10*2*256);
		sheet.setColumnWidth(2,20*2*256);
		sheet.setColumnWidth(3,20*3*256);
		sheet.setColumnWidth(4,20*3*256);
		sheet.setColumnWidth(5,20*3*256);
		sheet.setColumnWidth(6,12*2*256);
		sheet.setColumnWidth(7,17*2*256);
		sheet.setColumnWidth(8,12*2*256);
		sheet.setColumnWidth(9,20*2*256);
		sheet.setColumnWidth(10,15*2*256);
		sheet.setColumnWidth(11,20*2*256);
		sheet.setColumnWidth(12,12*2*256);
		sheet.setColumnWidth(13,12*2*256);
		sheet.setColumnWidth(14,17*2*256);
		sheet.setColumnWidth(15,12*2*256);
		sheet.setColumnWidth(16,12*2*256);
		sheet.setColumnWidth(17,17*2*256);
		sheet.setColumnWidth(18,12*2*256);
		
//		sheet.setDefaultColumnStyle(1,cellStyle);
		
		FileOutputStream fileOut = null;
		File file = new File(filepath);
		try {
			fileOut = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("7777777777");
		return file;
	}
}
