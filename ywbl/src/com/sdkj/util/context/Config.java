package com.sdkj.util.context;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class Config {

	// 结构ID
	public static final int[] StructureID = new int[] { 1, 2 };

	/**
	 * 桥梁ID
	 */
	public static final int structureId = 1;

	public static final int WebSoftWareID = 1;

	// 一页显示的记录数
	public static final int IntPageSize = 12;

	// 日期 格式化类
	public static final SimpleDateFormat Dateformatter = new SimpleDateFormat(
			"yyyy.MM.dd");
	// 浮点数格式化类
	public static final DecimalFormat Doubleformatter = new DecimalFormat(
			"####0.###");

	// 磁钢传感器编号
	public static final int[] autoPageSensorIDS = new int[] { 477 };

	/**
	 * 位移ID
	 */
	public static final int[] displacementIDS = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	/**
	 * 位移MeasureID
	 */
	public static final int[] displacementMeasureIDS = new int[] {1,2};

	/**
	 * 倾角ID
	 */
	public static final int[]  dipIDS = new int[] {201,202,203,204};
	/**
	 * 倾角MeasureID
	 */
	public static final int[] dipMeasureIDS = new int[] {1};
	
	/**
	 * 钢筋应力ID
	 */
	public static final int[]  steelIDS = new int[] {302,303,305,306,307,308,309,310,311,312,314,315,317,318,319,320,321,323,324,325,326,327,328,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,365,366,367,368,369,370,371,372};
	/**
	 * 钢筋应力MeasureID
	 */
	public static final int[] steelMeasureIDS = new int[] {1};

	/**
	 * 水准仪ID
	 */
	public static final int[] staticleveLIDS = new int[] {101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131};
	/**
	 * 水准仪MeasureID
	 */
	public static final int[] staticleveLMeasureIDS = new int[] {1};

	
	
	//中建三局 历史数据查询表数组
	public static final String[]  tableName={"DATA_DISPLACEMENT_","DATA_LEVEL_","DATA_DIP_","DATA_STRESS_"};
	
	public static final String SOP = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
	
	// 报警信息
	public static final String[] WARNING_LEVEL = {"<font color=green>正常</font>",
												"<font color=#a19f04>一级预警</font>",
												"<font color=#ff6000>二级预警</font>",
												"<font color=#bf0000>三级报警</font>",};
	// 报警信息颜色
	public static final String[] WARNING_LEVEL_COLOR = {"black","#a19f04","#ff6000","#bf0000"};
}
