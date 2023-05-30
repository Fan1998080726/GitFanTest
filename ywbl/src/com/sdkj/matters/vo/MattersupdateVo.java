package com.sdkj.matters.vo;

import lombok.Data;
import lombok.ToString;

/***
 *     private  String   事项变更表
 * @author Administrator
 *
 */
@Data
@ToString
public class MattersupdateVo {
   private  String   id;//
   private  String   begin_id;//变更前id
   private  String   matter_id;//事项id
   private  String   update_type;//变更记录类别
   private  String   update_matter;// 变更事项名称
   private  String   begin_matter;//变更前
   private  String   aflter_mater;//变更后
   private  String   inittme;//变更时间
 
   
}
