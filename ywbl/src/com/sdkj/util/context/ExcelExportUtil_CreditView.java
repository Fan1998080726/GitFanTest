package com.sdkj.util.context;
/*20200305*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sdkj.check.vo.CheckVo;
import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.matters.vo.MattersVo;

public class ExcelExportUtil_CreditView {
	public static File export(List<CheckVo> tbProjectInfoVo,String filepath ){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("名次");
		row.createCell(2).setCellValue("承办单位");
		row.createCell(3).setCellValue("督办任务主办总数");
		row.createCell(4).setCellValue("待完成");
		row.createCell(5).setCellValue("超期");
		row.createCell(6).setCellValue("已完成事项数");
			
		int i=1;
		for(CheckVo c:tbProjectInfoVo){
			String parm ="";
			HSSFRow row2 = sheet.createRow(i);
			row2.createCell(0).setCellValue(i);
			row2.createCell(1).setCellValue(c.getRankcount());
			row2.createCell(2).setCellValue(c.getUser_name());
			row2.createCell(3).setCellValue(c.getCount1());
			row2.createCell(4).setCellValue(c.getCount2());
			row2.createCell(5).setCellValue(c.getCount3());
			row2.createCell(6).setCellValue(c.getCount4());
			i++;
		}
		
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
		return file;
	}

	public static File exportFeedBack(List<FeedbackVo> list, String filepath) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("时间");
		row.createCell(2).setCellValue("操作人");
		row.createCell(3).setCellValue("类别");
		row.createCell(4).setCellValue("计分");
		row.createCell(5).setCellValue("原因");
		row.createCell(6).setCellValue("事项名称");
			
		int i=1;
		for(FeedbackVo c:list){
			String parm ="";
			HSSFRow row2 = sheet.createRow(i);
			row2.createCell(0).setCellValue(i);
			row2.createCell(1).setCellValue(c.getInittime());
			row2.createCell(2).setCellValue(c.getFeedback_person());
			row2.createCell(3).setCellValue(c.getFeedbacktype());
			row2.createCell(4).setCellValue(c.getScore());
			row2.createCell(5).setCellValue(c.getPjcontent());
			row2.createCell(6).setCellValue(c.getMattername());
			i++;
		}
		
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
		return file;
	}

	public static File ExcelWorkMassRank(List<CheckVo> list, String filepath) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("new sheet");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("名次");
		row.createCell(2).setCellValue("单位");
		row.createCell(3).setCellValue("得分");
		row.createCell(4).setCellValue("未及时录报");
		row.createCell(5).setCellValue("单项督办任务反馈报告第一次审核不合格被退回不扣分，第二次审核不合格被退回重办且重办后仍不合格的");
		row.createCell(6).setCellValue("责任（配合）部门应当按照牵头部门要求按时反馈。责任（配合）部门已及时反馈，牵头部门未按时反馈	");
		row.createCell(7).setCellValue("牵头部门对督办事项落实不力、推诿扯皮或弄虚作假的");
		row.createCell(8).setCellValue("责任（配合）部门未及时反馈导致牵头部门未按时反馈的，由牵头部门提出申请，"
				+ "经交办部门审核认定，牵头部门不扣分，责任（配合）部门扣0.2分");
		row.createCell(9).setCellValue("每周五晚24时之前，牵头事项未进行工作进展更新反馈的");
		row.createCell(10).setCellValue("局督办部门对各督办事项进展情况进行查看，牵头部门反馈信息不符合标准的，"
				+ "局督办部门将所反馈信息进行退回，牵头部门需对退回事项进行重新填报工作进展，如反馈仍不合格将被扣分");
		row.createCell(11).setCellValue("反馈次数");
	 
		 
		
		
		
	
			
		int i=1;
		for(CheckVo c:list){
			HSSFRow row2 = sheet.createRow(i);
			row2.createCell(0).setCellValue(i);
			row2.createCell(1).setCellValue(c.getRankcount());
			row2.createCell(2).setCellValue(c.getUser_name());
			row2.createCell(3).setCellValue(c.getScore());
			row2.createCell(4).setCellValue("-"+c.getStand1());
			row2.createCell(5).setCellValue("-"+c.getStand2());
			row2.createCell(6).setCellValue("-"+c.getStand3());
			row2.createCell(7).setCellValue("-"+c.getStand4());
			row2.createCell(8).setCellValue("-"+c.getStand5());
			row2.createCell(9).setCellValue("-"+c.getStand6());
			row2.createCell(10).setCellValue("-"+c.getStand7());
			row2.createCell(11).setCellValue(c.getFeedcount());
		
			i++;
		}
		
		sheet.setColumnWidth(0,10*256);
		sheet.setColumnWidth(1,10*256);
		sheet.setColumnWidth(2,10*256);
		sheet.setColumnWidth(3,20*256);
		sheet.setColumnWidth(4,10*3*256);
		sheet.setColumnWidth(5,10*3*256);
		sheet.setColumnWidth(6,12*3*256);
		sheet.setColumnWidth(7,17*3*256);
		sheet.setColumnWidth(8,17*3*256);
		sheet.setColumnWidth(9,17*3*256);
		sheet.setColumnWidth(10,17*3*256);
		sheet.setColumnWidth(11,17*3*256);
		FileOutputStream fileOut = null;
		System.out.println("filepath==="+filepath);
		File file = new File(filepath);
		try {
			fileOut = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public static File ExcelOldYearMatters(List<MattersVo> list, String filepath) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("督办事项");
		row.createCell(2).setCellValue("主要任务");
		row.createCell(3).setCellValue("事项来源");
		row.createCell(4).setCellValue("完成时限");
		row.createCell(5).setCellValue("状态");
		row.createCell(6).setCellValue("分管市领导");
		row.createCell(7).setCellValue("牵头单位");
			
		int i=1;
		for(MattersVo c:list){
			String parm ="";
			HSSFRow row2 = sheet.createRow(i);
			row2.createCell(0).setCellValue(i);
			row2.createCell(1).setCellValue(c.getSupervision_matter());
			row2.createCell(2).setCellValue(c.getMain_task());
			row2.createCell(3).setCellValue(c.getMatter_source());
			row2.createCell(4).setCellValue(c.getEnd_time());
			
			
			if(c.getState().equals("02")) {
				parm="发送";
			}
			
			if(c.getState().equals("03")) {
				parm="已延期";
			}
			if(c.getState().equals("04")) {
				parm="提交核验申请";
			}
			if(c.getState().equals("05")) {
				parm="办结";
			}
			if(c.getState().equals("06")) {
				parm="提前办结";
			}
			
			row2.createCell(5).setCellValue(parm);
			row2.createCell(6).setCellValue(c.getCharge_lead());
			row2.createCell(7).setCellValue(c.getSource_unit());
			i++;
		}
		
		sheet.setColumnWidth(0,10*256);
		sheet.setColumnWidth(1,10*2*256);
		sheet.setColumnWidth(2,20*2*256);
		sheet.setColumnWidth(3,20*3*256);
		sheet.setColumnWidth(4,10*256);
		sheet.setColumnWidth(5,10*256);
		sheet.setColumnWidth(6,12*2*256);
		sheet.setColumnWidth(7,17*2*256);
 
		
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
		return file;
	}

	public static File ExcelMatters(List<MattersVo> list, String filepath) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("监督事项");
		row.createCell(2).setCellValue("督办事项");
		row.createCell(3).setCellValue("主要任务");
		row.createCell(4).setCellValue("督办文号");
		row.createCell(5).setCellValue("事项来源");
		row.createCell(6).setCellValue("分管领导");
		row.createCell(7).setCellValue("抄送领导");
		row.createCell(8).setCellValue("牵头单位");
		row.createCell(9).setCellValue("配合单位");
		row.createCell(10).setCellValue("完成时限");
		row.createCell(11).setCellValue("反馈时限");
		row.createCell(12).setCellValue("交接人");
		row.createCell(13).setCellValue("交办人联系电话");
		row.createCell(14).setCellValue("进展情况");
		row.createCell(15).setCellValue("状态");
 		int i=1;
		for(MattersVo c:list){
			String parm ="";
			HSSFRow row2 = sheet.createRow(i);
			row2.createCell(0).setCellValue(i);
			row2.createCell(1).setCellValue(c.getMatter_type());
			row2.createCell(2).setCellValue(c.getSupervision_matter());
			row2.createCell(3).setCellValue(c.getMain_task());
			row2.createCell(4).setCellValue(c.getSupervision_code());
			row2.createCell(5).setCellValue(c.getMatter_source());
			row2.createCell(6).setCellValue(c.getCharge_lead());
			row2.createCell(7).setCellValue(c.getChaosong_lead());
			row2.createCell(8).setCellValue(c.getSource_unit());
			row2.createCell(9).setCellValue(c.getCooperate_unit());
			row2.createCell(10).setCellValue(c.getEnd_time());
			row2.createCell(11).setCellValue(c.getFankui_time());
			row2.createCell(12).setCellValue(c.getHandover_person());
			row2.createCell(13).setCellValue(c.getHandover_tel());
			System.out.println("c.getState()===="+c.getState());
			if(c.getState().equals("02")) {
				parm="发送";
			}
			if(c.getState().equals("03")) {
				parm="已延期";
			}
			if(c.getState().equals("04")) {
				parm="提交核验申请";
			}
			if(c.getState().equals("05")) {
				parm="办结";
			}
			if(c.getState().equals("06")) {
				parm="提前办结";
			}
		 
			row2.createCell(14).setCellValue(c.getFeedBackContent());
			row2.createCell(15).setCellValue(parm);
	 
			i++;
		}
		
		sheet.setColumnWidth(0,10*256);
		sheet.setColumnWidth(1,10*2*256);
		sheet.setColumnWidth(2,20*2*256);
		sheet.setColumnWidth(3,20*3*256);
		sheet.setColumnWidth(4,10*256);
		sheet.setColumnWidth(5,10*256);
		sheet.setColumnWidth(6,12*2*256);
		sheet.setColumnWidth(7,17*2*256);
		sheet.setColumnWidth(8,17*2*256);
		sheet.setColumnWidth(9,17*2*256);
		sheet.setColumnWidth(10,17*2*256);
		sheet.setColumnWidth(11,17*2*256);
		sheet.setColumnWidth(12,17*2*256);
		sheet.setColumnWidth(13,17*2*256);
		sheet.setColumnWidth(14,17*2*256);
		sheet.setColumnWidth(15,17*2*256);
		sheet.setColumnWidth(16,17*2*256);
		sheet.setColumnWidth(17,17*2*256);
 
		
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
		return file;
	} 
	 
}
