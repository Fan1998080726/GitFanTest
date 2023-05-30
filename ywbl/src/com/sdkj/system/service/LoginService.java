package com.sdkj.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sdkj.system.vo.LogVo;
import com.sdkj.system.vo.Role;
import com.sdkj.system.vo.UserInfo;

public interface LoginService {

	/**
	 * 登陆
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<?> queryforlog(UserInfo user) throws Exception;
	// fcl 20200511
	void adds(String username,String deptname,String userip,String doWhat,String op) throws Exception;
	void add(String username,String deptname,String userip,String doWhat) throws Exception;
	void add(String username,String deptname,String userip,String doWhat,String userAgent) throws Exception;//20180912
	/*20181114 state:表示保存、添加、修改、提交、办结等状态，op表示是哪张数据库表的数据，opid表示表中某条数据的主键ID*/
	void add(String username,String deptname,String userip,String doWhat,String userAgent,String state,String op,String opid) throws Exception;
 	void addlog(String TbBuilderLicenceManageId,String state,String AuditOpinion,String Person) throws Exception;
 	/*20190516 txb*/
 	void addBigCheckLog(String dept_name,String user_name, String logmanoperationtype, String logmanoperationcontent) throws Exception;
 	
 	void gasaddlog(String id,String username,String deptname,String dowhat,String currenttime) throws Exception;
 	/**
 	 * 超级密码  fcl   2021年3月4日09:42:40
 	 * */
	public List<?> queryforAdminlog(UserInfo user) throws Exception;
	public void addLog(LogVo logtable) throws Exception;

}
