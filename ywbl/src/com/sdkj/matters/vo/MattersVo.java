package com.sdkj.matters.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 事项表
 * @author Administrator
 *
 */
@Data
@ToString
public class MattersVo {
   private  String  id;//
   private  String  inittime;//
   private  String  updatetime;//
   /**
    * 事项类型
    */
   private  String  matter_type;//
   private  String  supervision_matter;//监督事项
   private  String  main_task;//主要任务
   private  String  supervision_code;//督办文号
   private  String  matter_source;//事项来源
   private  String  charge_lead;//分管领导
   private  String  chaosong_lead;//抄送领导
   private  String  source_unit;//牵头单位
   private  String  cooperate_unit;// 配合单位
   private  String 	matters_id;//关联配合单位关联表的id
   private  String  end_time;// 完成实现
   private  String  fankui_time;//反馈时限
   private  String  fid;//附件id
   private  String  handover_person;//交接人
   private  String  handover_tel ;//交办人联系电话
   private  String  breaks;//备注
   private  String  state;//（01 保存、02发送、03已延期延期、04 核验申请、 05办结）
   private  String  flag;//(00、01 逻辑删除)
   private  String  yqstate;//延期状态(01申请延期、02延期通过、03延期退回、)
   
   
   
   private  String  endtime;//办结日期
   private  String  hytime;//核验日期
   private  String  sendtime;//发送日期
   private  String  endcontent;// 办结说明

   /**
    * 最新进展情况
    */
   private String feedBackContent;
   
   
   /**
    * 配合单位关联表(区分单位事项)字段
    */
   private  String  unitstate;// 办结说明
   private  String  cstate;// 办结说明
   private  String  cid;// 办结说明
   
   /**
    * 延期审核备注
    */
   private  String  yqcontent;
   /**
    * 核验审核备注
    */
   private  String  hecontent;
   /**
    * 核验提交日期
    */
   private  String  hytjtime;
   /**
    * 办结状态  1通过 2退回
    */
   private  String  bjstate;
   /**
    * 分数
    */
   private  String  score;
   /**
    * 预警时间
    * @return
    */
   private  String  warn_time;
   /**
    * 反馈类型  1自定义  2按周  3 按月
    */
   private  String  feed_type;
   
   
    
   
   
}
