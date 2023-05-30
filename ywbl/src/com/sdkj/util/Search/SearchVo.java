package com.sdkj.util.Search;

import lombok.Data;

@Data
public class SearchVo {
	
	/**
	 * ID
	 */
	private  String  id ;
	
	/**
	 * 季度
	 */
	private  String  quarter ;
	
	/**
	 * 按周查询
	 */
	private  String worktime ;
	
	/**
	 * 用户ID
	 */
	private  Integer  userId ;
	/**
	 * 菜单状态
	 * 0  全部  1新到
	 */
	private  String  str ;
	
	/**
	 * 查询年份
	 */
	private  String year;
	
	/**
	 * 配合单位关联表(区分单位事项) ID
	 */
	private  String  cid;
	
	/**
	 * 事项主键ID
	 */
	private  String mattersId;
	
	/**
	 * 反馈人姓名
	 */
	private  String  personName;
	
	/**
	 * 事项名称
	 */
	private  String  prjname;
	
	
	/**
	 *	开始日期
	 */
	private  String  startdate;
	
	
	/**
	 *  结束日期
	 */
	private  String  enddate;
	
	/**
	 * 监督事项
	 */
	private  String  supervision_matter;
	
	/**
	 * 主要任务
	 */
	private  String  main_task;
	/**
	 * 事项来源
	 */
	private  String  matter_source;
	/**
	 *办理状态
	 */
	private  String  state;
	
	/**
	 *交办时间
	 */
	private  String  sendtime1;
	
	
	/**
	 *交办时间至
	 */
	private  String  sendtime2;
	
	/**
	 *完成时限1
	 */
	private  String  end_time1;
	
	/**
	 *完成时限2
	 */
	private  String  end_time2;
	
	/**
	 *牵头单位
	 */
	private  String  source_unit;
	
	
	/**
	 *配合单位
	 */
	private  String  cooperate_unit;
	
	
	/**
	 *分管领导
	 */
	private  String  charge_lead;
	
	
	/**
	 *批示领导
	 */
	private  String chaosong_lead;
	
	/**
	 *交办人
	 */
	private  String handover_person;
	
	/**
	 *督办文号
	 */
	private  String supervision_code;
	
	/**
	 *办结时间：1
	 */
	private  String banjie1;
	
	/**
	 *办结时间：2
	 */
	private  String banjie2;
	
	
	/**
	 *办结时间：2
	 */
	private  String username;
	
	
	/**
	 *部门状态
	 */
	private  String deptState;
	
	/**
	 * 周一日期
	 */
	private   String  oneWeekTime;
	
	/**
	 * 周五日期
	 */
	private   String   fiveWeekTime;
	
	/**
	 *  日期状态  0 按周查  1按年查
	 */
	private   String   timeType;
	
	/**
	 *   标题
	 */
	private   String   titlecontent;
	
	
	
	 
	
	
	
}
