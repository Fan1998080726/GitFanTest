package com.sdkj.TimeScore.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EvaluationTableVo {
   private   String  id;//
   private   String  user_id;//操作用户
   private String user_name;
   private   String  matters_id;//
   private   String  score;//扣分分值
   private   String  before_score;//评价前分数
   private   String  alfter_score;//评价后表
   private   String  state;//1为修复
   private   String  inittime;//
   private   String  lastupdatetime;//
   private   String  zbid;//
   
   
   private   String  markScore;//扣分值
   private   String  fk_id;//反馈表ID
 
}
