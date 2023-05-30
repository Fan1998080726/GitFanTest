package com.sdkj.feedback.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 反馈表
 * 
 * 
 * 与matters  中ID  关联
 * @author Administrator
 *
 */
@Data
@ToString
public class FeedbackVo {
   private   String  id;//
   private   String  fid;//文件id
   private   String  matter_id;// 事项id
   private   String  inittime;//反馈日期
   private   String  feedback_breaks;//反馈内容
   private   String  feedback_dept;//反馈部门
   private   String  feedback_person;//反馈人
   private   String  flag;//（00、01删除）逻辑删除 
   
   private  String  mid;
   
   /**
    * 是否符合删除条件
    */
   private  Integer isDel;
   
   private  String supervision_matter;
   
   private  String user_name;
   
   private   String  c_id;//图片ID
   
   
   /**
    * 反馈人ID
    */
   private   String  feedback_id;//
   /**
    * 退回意见
    */
   private   String  statecontent;//
   /**
    * 退回时间
    */
   private   String  statetime;//
   /**
    * 状态
    */
   private   String  state;//
   /**
    * 反馈状态
    */
   private   String  feedbacktype;//
   /**
    * 说明
    */
   private   String  pjcontent;//
   /**
    * 事项名称
    */
   private   String  mattername;//
   /**
    * 分值
    */
   private   String  score;//

 
 
   
}
