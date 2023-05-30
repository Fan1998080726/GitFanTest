package com.sdkj.check.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckVo {
	/**
	 * 名次
	 */
	private  String  rankcount;
	/**
	 * 承办单位
	 */
	private  String user_name;
	/**
	 * 承包事项
	 */
	private  String count1;
	/**
	 * 待完成
	 */
	private  String count2;
	/**
	 * 已超期
	 */
	private  String count3;
	/**
	 * 办结
	 */
	private  String count4;
	/**
	 * 分数
	 */
	private  String score;
	/**
	 * 反馈数
	 */
	private  String feedcount;
	/**
	 * 用户ID
	 */
	private  String userid;
	/**
	 * 所扣分数
	 */
	private  double kfScore;
	
	
	
	private  double  stand1;
	private  double  stand2;
	private  double  stand3;
	private  double  stand4;
	private  double  stand5;
	private  double  stand6;
	private  double  stand7;
	
	private  String  zbid;
	
	
	
	
	
	
}
