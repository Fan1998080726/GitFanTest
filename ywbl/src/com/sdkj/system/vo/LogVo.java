package com.sdkj.system.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogVo {
	public String id;
	public String username;
	public String userip;
	public String doWhat;
	public String currentTime;
	public String tablename;
	public String methodName;
	public String logState;
	public String deptname;
	 
}
