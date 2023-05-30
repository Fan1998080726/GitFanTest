package com.sdkj.Delay.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 延期表
 * @author Administrator
 *
 */
@Data
@ToString
public class DelayVo {
   private  String  id;//
   private  String  fid;//文件id
   private  String  inittime;//申请时间
   private  String  delaybreak;//延期备注
   private  String  matter_id;// 事项id
   private  String  userid;// 申请延期用户
   private  String  state;//状态(01 上报  02  退回 03 通过)
   private  String  statecontent;//审核意见
   /**
    * 督办事项
    */
   private  String supervision_matter;
   /**
    * 申请用户
    */
   private  String user_name;
   /**
    * 申请用户角色
    */
   private  String role_name;
   
   /**
    * 审核时间
    */
   private  String shenhetime;
   
}
