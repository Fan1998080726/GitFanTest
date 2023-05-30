package com.sdkj.matters.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SiginTableVo {

   private   String  id;//
   private   String  inittime;//
   private   String  sigin_person;//签收人
   private   String  sigin_tel;//签收电话
   private   String  sigin_unit;//签收单位
   private   String  cb_unit;//承办人单位
   private   String  cb_person;//承办人
   private   String  cb_tel;//承办人电话
   private   String  matters_id;//事项id
   private   String  user_id;//用户id
   
}
