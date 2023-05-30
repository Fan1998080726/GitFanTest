package com.sdkj.toWork.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SendNoticeVo {
	 private  String  id;//
	 private  String  state;// private  String  0 发送  1 已查看
	 private  String  userid;//
	 
	 private  String   fid	;//
	 private  String   noticeid	;//关联表id
	 private  String   noctioncontent;//发送内容
	 private  String  inittime	;//
	 private  String   lasttime	;//
	 
	 private  String   sendtime;//
	 /**
	  * 标题
	  */
	 private  String   titlecontent;//
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
