package com.sdkj.matters.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.common.vo.UploadFileVo;
import com.sdkj.matters.service.MattersService;
import com.sdkj.matters.vo.CooperateUnitsVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.matters.vo.SiginTableVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;
import com.sdkj.util.sendMsg.SendSms;

@Service
public class IMattersService implements MattersService   { 
	@Autowired
	private Dao dao;

	public List queryMattersAdmin(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		
		if(!searchVo.getPrjname().equals("")) {
			parm ="	and   supervision_matter   like '%"+searchVo.getPrjname()+"%' 	";
		}
		
		if(!searchVo.getHandover_person().equals("")) {
			parm ="	and   handover_person   like '%"+searchVo.getHandover_person()+"%' 	";
		}
		
		String sql = "select  * from  matters t  where  t.flag!='01'  "+parm+"		 order by  inittime desc 	";
		pagination.setTotalCount((Integer) dao.queryById("queryMattersAdminCount", sql));
		return dao.queryForList("queryMattersAdmin",  CommonFunc.getLimitSql(sql, pagination));
	}

	public MattersVo queryMatterById(String id) throws Exception {
		String sql = "  select  *  from  matters  where   id='" + id + "'	";
		return (MattersVo) dao.queryById("queryMatterById", sql);
	}

	public List queryPermissions(SearchVo searchVo) throws Exception {
		String sql = "select  t.id,t.login_id,t.role_id,d.dept_id from system_user_info t left join system_role_info r"
				+ "  on t.role_id  =r.role_id  left join  system_dept_info d   on "
				+ " r.dept_id=d.dept_id   where d.dept_id!='8' 	and  d.dept_id!='86' 	";
		return dao.queryForList("queryPermissions", sql);
	}

	public void deleteUnitsData(String matters_id) throws Exception {
		String sql = " delete   from  cooperateunits     where  MATTER_ID='" + matters_id + "'   ";
		dao.delete("deleteUnitsData", sql);
	}

	public void SaveUnits(CooperateUnitsVo unitsVo) throws Exception {
		dao.update("SaveUnits", unitsVo);
	}

	public void SaveUnitsStr(String source_unit, String matters_id, String str) throws Exception {

		String List[] = source_unit.split(",");
		String  phone = "";
		for (String qiantouID : List) {
			CooperateUnitsVo unitsVo = new CooperateUnitsVo();
			unitsVo.setId(CommonFunc.getGUID());
			unitsVo.setUser_id(qiantouID.trim());
			unitsVo.setMatter_id(matters_id);
			unitsVo.setUnitstate(str);
			unitsVo.setState("01");
			
			
			phone=(String) dao.queryById("queryUserInfoPhoneById","select  t.phone from system_user_info t  where  t.id='"+qiantouID.trim()+"'   ");
			System.out.println("Phone==="+phone);
			phone=Util.nullToString(phone);
			if(!phone.equals("")) {
				SendSms.sendSendMattersSmsForNotice(phone);
			}
			
			dao.update("SaveUnits", unitsVo);
			
			
			
			

		}
//		dao.update("SaveUnits", unitsVo);

	}

	public void SaveMatterData(MattersVo mattersVo) throws Exception {
		mattersVo.setSource_unit(mattersVo.getSource_unit().replace(" ",""));
		/**
		 * 配合单位可以为空
		 */
		if(null==mattersVo.getCooperate_unit()) {
			mattersVo.setCooperate_unit("");
		}
		
		/**
		 * 办结
		 */
		if(mattersVo.getState().equals("02")) {
			/**
			 * 发送日期
			 */
			mattersVo.setSendtime(CommonFunc.CurrentTimeEn());
		}
		
		mattersVo.setCooperate_unit(mattersVo.getCooperate_unit().replace(" ",""));
		mattersVo.setUpdatetime(CommonFunc.getGUID());
		if (mattersVo.getId().equals("")) {
			mattersVo.setId(CommonFunc.getGUID());
			mattersVo.setInittime(CommonFunc.CurrentTimeEn());
			dao.update("addMatterData", mattersVo);
		} else {
			dao.update("updateMatterData", mattersVo);

		}

	}

	public void delFileByReport(String fid, String ftype) throws Exception {
		String str= "";
		if(ftype.equals("file")){
			str="and cf_type='file'";
		}else if(ftype.equals("1")){
			str = "";
		}else{
			str = "and cf_type='"+ftype+"'";
		}
		if(null!=fid&&!"".equals(fid)){
			String sql = "delete from uploadFile where c_id='"+fid+"' "+str+" ";
			dao.update("deleteuploadFilels", sql);
		}
	}

	public void addReportFile_new(String[] fileName, String[] filePath, String[] type, String[] saomiaodate, String fid,
			String string, String string2)  throws Exception{
		for(int i=0;i<fileName.length;i++){
			////////////////////System.out.println("filePath="+filePath);
			//读取本地图片
			UploadFileVo cf=new  UploadFileVo();
			cf.setCf_id(CommonFunc.getGUID());
			cf.setC_id(fid);
			cf.setStateid(String.valueOf(i));
			cf.setCf_name(fileName[i]);
			////////////////////////////System.out.println("URL"+filePath[i]);
			cf.setCf_type(type[i]);
			//////////////////////System.out.println("category"+category[i]);
			cf.setCf_url(filePath[i]);
			dao.save("addReportFile_new", cf);
		}
		
	}

	public List getUpfile(String fid, String str) throws Exception {
		String  parm ="";
		if(!"".equals(str)){
			parm ="and cf_type='"+str+"'";
		}
		String sql = "select  * from uploadFile where c_id='"+fid+"' "+parm+"  ";
		return dao.queryForList("getfileReports",sql);
	}

	public void UpdateMattersFlag(String id, String matterId) throws Exception {
			String sql ="update   matters  set flag='01'  where   id='"+id+"'  ";
			dao.update("UpdateMattersFlag", sql);
	}
	
	public void endState(String id, String endcontent) throws Exception {
		String  sql ="update  matters  set   state='06',ENDTIME='"+CommonFunc.CurrentTimeEn()+"',ENDCONTENT='"+endcontent+"'  where  id='"+id+"'  ";
		dao.update("endState", sql);
	}

	public List queryMattersUser(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		/**
		 * 新进事项菜单  01 为未查看
		 * 
		 * 延期事项    str  7 
		 * 	配合事项    str  4
		* 	核验退回事项    str  8
		* 	办结事项    str  9
		 */
		if(searchVo.getStr().equals("0")) {
			parm =parm+"	and c.unitstate='01'  ";
		}
		if(searchVo.getStr().equals("1")) {
			parm =parm+"  	and c.state='01'   and  c.unitstate='01'		 ";
		}
		if(searchVo.getStr().equals("7")) {
			parm =parm+"and m.yqstate='02'    ";
		}
		if(searchVo.getStr().equals("4")) {
			parm =parm+"and c.unitstate='02'  ";
		}
		if(searchVo.getStr().equals("3")) {
			parm =parm+"and c.unitstate='01'     and  (m.state='02'  or m.state='03' )			";
		}
		if(searchVo.getStr().equals("6")) {
			parm =parm+"and c.unitstate='01' and    m.state='04'   ";
		}
		
		
		
		if(!searchVo.getSupervision_matter().equals("")) {
			parm = parm+"	and  supervision_matter  like '%"+searchVo.getSupervision_matter()+"%' ";
		}
		
		if(!searchVo.getState().equals("")) {
			if(searchVo.getState().equals("05")) {
				parm = parm+"	and  m.state='05' ";
			}
			if(searchVo.getState().equals("02")) {
		 
				parm = parm+"	and  m.end_time>='"+CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),7)+"'   and m.end_time>'"+CommonFunc.CurrentDateJianHao()+"' ";
			}
			if(searchVo.getState().equals("03")) {
				
				parm = parm+"	  and m.end_time>'"+CommonFunc.CurrentDateJianHao()+"' ";
			}
			if(searchVo.getState().equals("01")) {
				
				parm = parm+"	  and m.end_time<'"+CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),-7)+"'	and   m.state!='05' ";
			}
		}
		
		
		
		if(!searchVo.getHandover_person().equals("")) {
			parm = parm+"	and  handover_person  like '%"+searchVo.getHandover_person()+"%' ";
		}
		
		
		
		
		
//		if(searchVo.getStr().equals("3")) {
//			parm =parm+"and c.unitstate='01'     and  m.state='02'  and c.state!='03' 	";
//		}
		
		if(searchVo.getStr().equals("8")) {
			parm =parm+"and c.unitstate='01'     and  m.state='02'	  and  m.bjstate='2' ";
		}
		if(searchVo.getStr().equals("9")) {
			parm =parm+"and c.unitstate='01'     and  m.state='05'	  and  m.bjstate='1' ";
		}
		/**
		 * 超期
		 */
		if(searchVo.getStr().equals("5")) {
			parm =parm+"and c.unitstate='01'     and  m.end_time<='"+CommonFunc.CurrentDateJianHao()+"'	";
		}
		
		String parm1= "";
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		if(user_id!=10359) {

			parm1="	and  c.user_id='"+searchVo.getUserId()+"'	";
		}
		
		String  sql =" select m.*,c.state as cstate,c.unitstate,c.id as cid from (select  *  from matters  where  flag!='01') m  "
				+ "          left join cooperateunits  c "
						+ " on m.matters_id=c.matter_id	 where   1=1  "+parm1+" and  m.state!='01'	"+parm+"  ";
		System.out.println("SQL=="+sql);
//		String sql = "select  * from  matters t  where  t.flag!='01'  and   matters_id  in  (select  t.matter_id from cooperateunits t  where  t.user_id='"+searchVo.getUserId()+"'		"+parm+" ) ";
		pagination.setTotalCount((Integer) dao.queryById("queryMattersUserCount", sql));
		return dao.queryForList("queryMattersUser",  CommonFunc.getLimitSql(sql, pagination));
	}

	public void UpdateCooperateState(SearchVo searchVo) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
			String  sql ="update   cooperateunits  set state='02'  where "
					+ "  matter_id='"+searchVo.getMattersId()+"' "
					+ "and user_id='"+user_id+"'  ";
			
		dao.update("UpdateCooperateState", sql);
	}

	public List getUpfiles(String id, String str) throws Exception {
		String  parm ="";
		if(!"".equals(str)){
			parm ="and cf_type='"+str+"'";
		}
		String sql = "select  * from uploadFile where c_id in (select  fid from feedback t where  t.matter_id='"+id+"')  "+parm+"  ";
		return dao.queryForList("getFeedbackfileReports",sql);
	}

	public void updateMattersState(String matter_id, String state,String strtype,String  time) throws Exception {
		System.out.println("strtype="+strtype);
		System.out.println("state="+state);
//		if(strtype.equals("01")) {
		String  parm ="";
			if(state.equals("1")) {
				state="02";
				
			}else {
				state="03";
			}
//		}
		
		String  sql =" update  matters  set YQSTATE='"+state+"'"
				+ ",yqcontent='"+strtype+"'  ,end_time='"+time+"'  where id='"+matter_id+"'    ";
		System.out.println("SQL=="+sql);
		dao.update("updateMattersState", sql);
	}

	public void SaveFeedBackCheck(MattersVo mattersVo) throws Exception {
		String  sql="update    MATTERS  set   hytjtime='"+CommonFunc.CurrentTimeEn()+"',STATE='04'	where  id='"+mattersVo.getId()+"'	";
		dao.update("SaveFeedBackCheck", sql);
	}

	public void SaveUnitsState(MattersVo mattersVo) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		String  sql =" update  cooperateunits t  set  state='03'  where"
				+ "	    t.user_id='"+user_id+"'  and t.matter_id='"+mattersVo.getMatters_id()+"'    ";
		
		System.out.println("SQL===="+sql);
		
		dao.update("SaveUnitsState", sql);
	}

	public Integer queryRequestHeyanCount(String string, String string2) throws Exception {
			String sql =" select  count(*)  from    MATTERS  where state='04' 	and  flag!='01'	 ";
		return (Integer) dao.queryById("queryRequestHeyanCount",sql);
	}

	public List queryHeyanMatters(SearchVo searchVo) throws Exception {
		String sql =" select   *  from    MATTERS m  where m.state='04'	and m.flag!='01'	  ";
		return  dao.queryForList("queryHeyanMatters",sql) ;
	}

	public void AuditMatters(MattersVo mattersVo) throws Exception {
		
		String parm ="";
		/**
		 * 1  为通过  2为退回
		 */
		if(mattersVo.getState().equals("1")){
			parm="05";
		}else {
			parm="02";
 		}
		
		String  sql =" update   MATTERS  	set    BJSTATE='"+mattersVo.getState()+"',"
				+ "ENDCONTENT='"+mattersVo.getEndcontent()+"',"
						+ "ENDTIME='"+CommonFunc.CurrentTimeEn()+"',state='"+parm+"'	  where  id='"+mattersVo.getId()+"'";
		System.out.println("SQL=="+sql);
		dao.update("AuditMatters", sql);
		String updateSql = "";
		if(mattersVo.getState().equals("1")){
			updateSql =" update  cooperateunits t   set  t.state='04'  where  t.matter_id='"+mattersVo.getMatters_id()+"'	";
		}else {
			updateSql =" update  cooperateunits t   set  t.state='05'  where  t.matter_id='"+mattersVo.getMatters_id()+"'	";
 		}
		dao.update("UpdateCooperateUnitsState", updateSql);
		
		
	}

	public List queryAllMattersData(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		
		if(!searchVo.getSupervision_matter().equals("")) {
			parm = parm+"	and  supervision_matter  like '%"+searchVo.getSupervision_matter()+"%' ";
		}
		
		if(!searchVo.getMatter_source().equals("")) {
			parm = parm+"	and  matter_source  like '%"+searchVo.getMatter_source()+"%' ";
		}
		
		if(!searchVo.getMain_task().equals("")) {
			parm = parm+"	and  main_task  like '%"+searchVo.getMain_task()+"%' ";
		}
		
//		if(!searchVo.getState().equals("")) {
//			parm = parm+"	and  state  like '%"+searchVo.getState()+"%' ";
//		}
//		
		if(!searchVo.getSendtime1().equals("")) {
			parm = parm+"	and  sendtime  >=	'"+searchVo.getSendtime1()+"' ";
		}
		if(!searchVo.getSendtime2().equals("")) {
			parm = parm+"	and  sendtime  <=	'"+searchVo.getSendtime2()+"' ";
		}
		
		if(!searchVo.getEnd_time1().equals("")) {
			parm = parm+"	and  end_time  >=	'"+searchVo.getEnd_time1()+"' ";
		}
		if(!searchVo.getEnd_time2().equals("")) {
			parm = parm+"	and  end_time  <=	'"+searchVo.getEnd_time2()+"' ";
		}
		
		if(!searchVo.getSource_unit().equals("")) {
			parm = parm+"	and  source_unit  like '%"+searchVo.getSource_unit()+"%' ";
		}
		
		if(!searchVo.getCooperate_unit().equals("")) {
			parm = parm+"	and  cooperate_unit  like '%"+searchVo.getCooperate_unit()+"%' ";
		}
		
		if(!searchVo.getCharge_lead().equals("")) {
			parm = parm+"	and  charge_lead  like '%"+searchVo.getCharge_lead()+"%' ";
		}
		
		if(!searchVo.getHandover_person().equals("")) {
			parm = parm+"	and  handover_person  like '%"+searchVo.getHandover_person()+"%' ";
		}
		
		if(!searchVo.getSupervision_code().equals("")) {
			parm = parm+"	and  supervision_code  like '%"+searchVo.getSupervision_code()+"%' ";
		}
		
		if(!searchVo.getChaosong_lead().equals("")) {
			parm = parm+"	and  chaosong_lead  like '%"+searchVo.getChaosong_lead()+"%' ";
		}
		
		if(!searchVo.getBanjie1().equals("")) {
			parm = parm+"	and  endtime  >='"+searchVo.getBanjie1()+"' ";
		}
		if(!searchVo.getBanjie2().equals("")) {
			parm = parm+"	and  endtime  <='"+searchVo.getBanjie2()+"' ";
		}
		 
		
		if(!searchVo.getState().equals("")) {
			if(searchVo.getState().equals("05")) {
				parm = parm+"	and  state='05' ";
 			}
			if(searchVo.getState().equals("02")) {
		 
				parm = parm+"	and  end_time>='"+CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),7)+"'   and end_time>'"+CommonFunc.CurrentDateJianHao()+"' ";
			}
			if(searchVo.getState().equals("03")) {
				
				parm = parm+"	  and end_time>'"+CommonFunc.CurrentDateJianHao()+"' ";
			}
			if(searchVo.getState().equals("01")) {
				
				parm = parm+"	  and end_time<'"+CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),-7)+"'	and   state!='05' ";
			}
		} 
		
		
		
		if(!searchVo.getYear().equals("")) {
			parm=parm+"	and 		sendtime like '%"+searchVo.getYear()+"%'";
		}
		
		if(!searchVo.getQuarter().equals("")) {
			
			if(searchVo.getQuarter().equals("1")) {
				parm=parm+"	and  sendtime  BETWEEN   	 '"+searchVo.getYear()+"-01-01' AND '"+searchVo.getYear()+"-03-31'  ";
			}
			if(searchVo.getQuarter().equals("2")) {
				parm=parm+"	and  sendtime  BETWEEN  	 '"+searchVo.getYear()+"-04-01' AND '"+searchVo.getYear()+"-06-30'  ";
			}
			if(searchVo.getQuarter().equals("3")) {
				parm=parm+"	and  sendtime   BETWEEN  	 '"+searchVo.getYear()+"-07-01' AND '"+searchVo.getYear()+"-09-30'  ";
			}
			if(searchVo.getQuarter().equals("4")) {
				parm=parm+"	and sendtime BETWEEN 	 '"+searchVo.getYear()+"-10-01' AND '"+searchVo.getYear()+"-12-31'  ";
			}
			
		}
		
		if(!searchVo.getWorktime().equals("")) {
			parm=parm+"  and 	substr(sendtime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
					+ " and  substr(sendtime,0,10)<='"+searchVo.getFiveWeekTime()+"'  ";
		}
		
		
		String sql = "select  * from  matters t  where  t.flag!='01'    and state!='01'   "+parm+"	";
		System.out.println("SQL===="+sql);
		pagination.setTotalCount((Integer) dao.queryById("queryMattersAdminCount", sql));
		return dao.queryForList("queryAllMattersData",  CommonFunc.getLimitSql(sql, pagination));
	}

	public int queryCount(String  str) throws Exception {
		String parm ="";
		/**
		 * 新进事项菜单  01 为未查看
		 * 
		 * 延期事项    str  7 
		 * 	配合事项    str  4
		* 	核验退回事项    str  8
		* 	办结事项    str  9
		 */
		if(str.equals("0")) {
			parm =parm+"	and c.unitstate='01'  ";
		}
		if(str.equals("1")) {
			parm =parm+"	and c.state='01'   and  c.unitstate='01'		";
		}
		if(str.equals("7")) {
			parm =parm+"and m.yqstate='02'    ";
		}
		if(str.equals("4")) {
			parm =parm+"and c.unitstate='02'  ";
		}
		if(str.equals("3")) {
			parm =parm+"and c.unitstate='01'     and  (m.state='02'  or m.state='03' )			";
		}
		if(str.equals("6")) {
			parm =parm+"and c.unitstate='01' and    m.state='04'   ";
		}
		if(str.equals("8")) {
			parm =parm+"and c.unitstate='01'     and  m.state='02'	  and  m.bjstate='2' ";
		}
		if(str.equals("9")) {
			parm =parm+"and c.unitstate='01'     and  m.state='05'	  and  m.bjstate='1' ";
		}
		if(str.equals("5")) {
			parm =parm+"and c.unitstate='01'     and  m.end_time<='"+CommonFunc.CurrentDateJianHao()+"'	";
		}
		
		String parm1= "";
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		if(user_id!=10359) {
 			parm1="	and  c.user_id='"+user_id+"'	";
		}
		String  sql =" select  count(*) from (select  *  from matters  where  flag!='01') m  "
				+ "          left join cooperateunits  c "
						+ " on m.matters_id=c.matter_id	 where   1=1  "+parm1+" and  m.state!='01'	"+parm+"  ";
	
//		String sql = "select  * from  matters t  where  t.flag!='01'  and   matters_id  in  (select  t.matter_id from cooperateunits t  where  t.user_id='"+searchVo.getUserId()+"'		"+parm+" ) ";
		if(str.equals("9")) {
			System.out.println("SQL9=="+sql);
		}
		if(str.equals("0")) {
			return  (Integer) dao.queryById("queryMattersUserCount1", sql);		}
		if(str.equals("1")) {
			return  (Integer) dao.queryById("queryMattersUserCount2", sql);		}
		if(str.equals("7")) {
			return  (Integer) dao.queryById("queryMattersUserCount3", sql);		}
		if(str.equals("4")) {
			return  (Integer) dao.queryById("queryMattersUserCount4", sql);		}
		if(str.equals("3")) {
			return  (Integer) dao.queryById("queryMattersUserCount5", sql);		}
		if(str.equals("6")) {
			return  (Integer) dao.queryById("queryMattersUserCount6", sql);		}
		if(str.equals("8")) {
			return  (Integer) dao.queryById("queryMattersUserCount7", sql);		}
		if(str.equals("9")) {
			return  (Integer) dao.queryById("queryMattersUserCount8", sql);		}
		if(str.equals("5")) {
			return  (Integer) dao.queryById("queryMattersUserCount9", sql);		}
		return 0;

		 
	}

	public int BackMatters(MattersVo mattersVo) throws Exception {
			int code = 0;
			int  count = queryMatterIsEavlaCount(mattersVo.getMatters_id());
			if(count==0) {
				String  sql =" insert into evaluationcheck"
						+ "  (id, matters_id,inittime)"
						+ " values"
						+ "  ('"+CommonFunc.getGUID()+"','"+mattersVo.getMatters_id()+"', '"+CommonFunc.CurrentTimeEn()+"')";
				dao.update("addevaluationcheck", sql);
			}else {
				String  sql =" update evaluationcheck  set state='1' ,lastupdatetime='"+CommonFunc.CurrentTimeEn()+"'  ";
				dao.update("updateevaluationcheck", sql);
				code=1;
			}
			
			return code;
	}

	private int queryMatterIsEavlaCount(String matters_id) throws Exception {
		String  sql ="select count(*) from evaluationcheck t  where t.matters_id='"+matters_id+"'";
		return (Integer) dao.queryById("queryMatterIsEavlaCount",sql);
	}

	public void SaveUserInfo(UserInfo userInfo) throws Exception {
		String sql =" update    system_user_info  set		"
				+ "user_name ='"+userInfo.getUser_name()+"',"
				+ "memo='"+userInfo.getMemo()+"',"
				+ "email='"+userInfo.getEmail()+"',"
				+ "phone='"+userInfo.getPhone()+"'	"
				+ "where   id='"+userInfo.getId()+"'  ";
		dao.update("SaveUserInfo", sql);
	}

	public void SaveSigInfo(SiginTableVo siginTableVo) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		siginTableVo.setUser_id(String.valueOf(user_id));
		siginTableVo.setInittime(CommonFunc.CurrentTimeEn());
		siginTableVo.setId(CommonFunc.getGUID());
		dao.update("addsiginTable", siginTableVo);
		
	}

	public List querySigInList(String matters_id) throws Exception {
		 String  sql ="select t.*   from sigintable t  where  t.matters_id='"+matters_id+"'";
		return dao.queryForList("querySigInList",sql);
	}

	public Integer queryFeedbackCountCount(String string, String string2) throws Exception {
		String  sql =" select   count(*)  from    feedback  where   state='0'";
		return  (Integer) dao.queryById("queryFeedbackCountCount",sql);
	}

	public List queryFeedBackMatters(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		if(!searchVo.getPrjname().equals("")) {
			parm ="	and   supervision_matter   like '%"+searchVo.getPrjname()+"%' 	";
		}
		String sql = " select m.supervision_matter,m.id  as  mid ,s.user_name,t.*"
				+ " from feedback t left join  system_user_info s on "
				+ " t.feedback_id=s.id left join matters m on t.matter_id=m.id where  t.state='0' and  t.flag='00' "+parm+"	";
		pagination.setTotalCount((Integer) dao.queryById("queryFeedBackMattersCount", sql));
		return dao.queryForList("queryFeedBackMatters",  CommonFunc.getLimitSql(sql, pagination));
	}

	public int queryFeedbackCount(String string) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		
		String  sql ="select  count(*) from feedback t  where  t.state='2'  and t.feedback_id='"+user_id+"'";
		return (Integer) dao.queryById("queryFeedbackCount",sql);
	}

	public String  queryUserInfoOrById(String userid) throws Exception {
		String  parm ="(    ";
		String  ids[] = userid.split(",");
		for (String id : ids) {
			 parm+=" id='"+id+"' or";
		}
		parm = parm.substring(0,parm.length()-2);
		parm = parm +" )";
			String sql =" select      wmsys.wm_concat(login_id) as user_name   from   system_user_info  where  1=1  and "+parm+"  ";
		return  (String) dao.queryById("queryUserInfoOrById",sql);
	}
	
	
	public UserInfo queryUserInfoById(String userid) throws Exception {
	 
		String sql =" select   *   from   system_user_info  where  id='"+userid+"'  ";
		System.out.println("userInfo="+sql);
		return  (UserInfo) dao.queryById("queryUserInfoById",sql);
	}
	
	

	public List queryUserInfo(SearchVo searchVo, Pagination pagination) throws Exception {		
		String parm ="";
	if(!searchVo.getPrjname().equals("")) {
		parm =parm + "	and   user_name like '%"+searchVo.getPrjname()+"%'	";
	}
	if(!searchVo.getUsername().equals("")) {
		parm =parm + "	and   memo like '%"+searchVo.getUsername()+"%'	";
	}
	String sql = "select  * from  system_user_info t  where 	role_id !='8' "+parm+"  ";
	pagination.setTotalCount((Integer) dao.queryById("queryUserInfoCount", sql));
	return dao.queryForList("queryUserInfo",  CommonFunc.getLimitSql(sql, pagination));
	}

	public int queryMatterCount(String str) throws Exception {
		
		/**
		 * 十五天之后日期
		 */
		String daysAfter = CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),15);
		/**
		 * 当前日期
		 */
		String  currentTime = CommonFunc.CurrentDateJianHao();
		
 		
		
		String parm ="";
		if(str.equals("4")) {
			parm=	"  and   state='05'  ";
		}else 	if(str.equals("2")) {
			
			parm=	"  and    end_time<='"+daysAfter+"'		and  	end_time  >='"+currentTime+"' ";
		}else 	if(str.equals("3")) {
			
			parm=	"   	and  	end_time <'"+currentTime+"'  and   state!='05'				";
		}else 	if(str.equals("1")) {
			
			parm=	"      and   state!='01'   and   state!='06' ";
		}
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		String  sql ="select count(*) from matters t "
				+ " where t.matters_id in (select  t.matter_id from cooperateunits t  "
				+ "where  t.unitstate='01'  and t.flag='01' and  t.user_id='"+user_id+"' )	and t.flag!='01'	 "+parm+"	";
		
		
		
		
		System.out.println("SQL========"+sql);
		
		
		
		
		return (Integer) dao.queryById("queryMatterCount",sql);
	}

	public int querySendCount(String str) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		String  sql ="select count(*) from sendtongzhinotice t  "
				+ " left join noticerelevance n on t.id=n.noticeid "
				+ "  where  n.userid='"+user_id+"'  and n.state='0'";
		
		return (Integer) dao.queryById("querySendCount",sql);
	}

	public List queryFormerYearMatters(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		String  sql ="";
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		
		if(!searchVo.getPrjname().equals("")) {
			parm= parm + "	and  supervision_matter='"+searchVo.getPrjname()+"'	";
		}
		
		if(!searchVo.getYear().equals("")) {
			parm= parm + "	and  sendtime like '%"+searchVo.getYear()+"%'	";
		}
		if(user_id!=10359) {
			parm=	parm + "	and  m.source_unit='"+user_id+"'	";
			
	
	 		   sql =" select m.*,c.state as cstate,c.unitstate,c.id as cid from (select  *  from matters  where  flag!='01') m  "
					+ "          left join cooperateunits  c "
							+ " on m.matters_id=c.matter_id	 where   1=1  "+parm+" and  m.state!='01'   ";
	 		   
	 		   System.out.println("SQL====="+sql);
	 			 
	 			pagination.setTotalCount((Integer) dao.queryById("queryFormerDeptYearMattersCount", sql));
	 			return dao.queryForList("queryFormerDeptYearMatters",  CommonFunc.getLimitSql(sql, pagination));
		}else {
	 		   sql =" select m.*  from  matters  m  where  flag!='01'  "+parm+" and  m.state!='01'   ";
	 			 
	 			pagination.setTotalCount((Integer) dao.queryById("queryFormerYearMattersCount", sql));
	 			return dao.queryForList("queryFormerYearMatters",  CommonFunc.getLimitSql(sql, pagination));

		}
	}

	public List ExcelOldYearMatters(SearchVo searchVo) throws Exception {
	 
		String parm ="";
		String  sql ="";
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		
		if(!searchVo.getPrjname().equals("")) {
			parm= parm + "	and  supervision_matter='"+searchVo.getPrjname()+"'	";
		}
		
		if(!searchVo.getYear().equals("")) {
			parm= parm + "	and  sendtime like '%"+searchVo.getYear()+"%'	";
		}
		if(user_id!=10359) {
			parm=	parm + "	and  m.source_unit='"+user_id+"'	";

	 		   sql =" select m.*,c.state as cstate,c.unitstate,c.id as cid from (select  *  from matters  where  flag!='01') m  "
					+ "          left join cooperateunits  c "
							+ " on m.matters_id=c.matter_id	 where   1=1  "+parm+" and  m.state!='01'   ";
		}else {
	 		   sql =" select m.*  from  matters  m  where  flag!='01'  "+parm+" and  m.state!='01'   ";

		}
		
	
// 		String  sql =" select m.*  from  matters  m  where  flag!='01'  "+parm+" and  m.state!='01'   ";

		
		return dao.queryForList("ExcelOldYearMatters",sql);
	}

	public List queryExcelMattersData(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		
		if(!searchVo.getSupervision_matter().equals("")) {
			parm = parm+"	and  supervision_matter  like '%"+searchVo.getSupervision_matter()+"%' ";
		}
		
		if(!searchVo.getMatter_source().equals("")) {
			parm = parm+"	and  matter_source  like '%"+searchVo.getMatter_source()+"%' ";
		}
		
		if(!searchVo.getMain_task().equals("")) {
			parm = parm+"	and  main_task  like '%"+searchVo.getMain_task()+"%' ";
		}
		
//		if(!searchVo.getState().equals("")) {
//			parm = parm+"	and  state  like '%"+searchVo.getState()+"%' ";
//		}
//		
		if(!searchVo.getSendtime1().equals("")) {
			parm = parm+"	and  sendtime  >=	'"+searchVo.getSendtime1()+"' ";
		}
		if(!searchVo.getSendtime2().equals("")) {
			parm = parm+"	and  sendtime  <=	'"+searchVo.getSendtime2()+"' ";
		}
		
		if(!searchVo.getEnd_time1().equals("")) {
			parm = parm+"	and  end_time  >=	'"+searchVo.getEnd_time1()+"' ";
		}
		if(!searchVo.getEnd_time2().equals("")) {
			parm = parm+"	and  end_time  <=	'"+searchVo.getEnd_time2()+"' ";
		}
		
		if(!searchVo.getSource_unit().equals("")) {
			parm = parm+"	and  source_unit  like '%"+searchVo.getSource_unit()+"%' ";
		}
		
		if(!searchVo.getCooperate_unit().equals("")) {
			parm = parm+"	and  cooperate_unit  like '%"+searchVo.getCooperate_unit()+"%' ";
		}
		
		if(!searchVo.getCharge_lead().equals("")) {
			parm = parm+"	and  charge_lead  like '%"+searchVo.getCharge_lead()+"%' ";
		}
		
		if(!searchVo.getHandover_person().equals("")) {
			parm = parm+"	and  handover_person  like '%"+searchVo.getHandover_person()+"%' ";
		}
		
		if(!searchVo.getChaosong_lead().equals("")) {
			parm = parm+"	and  chaosong_lead  like '%"+searchVo.getChaosong_lead()+"%' ";
		}
		
		if(!searchVo.getSupervision_code().equals("")) {
			parm = parm+"	and  supervision_code  like '%"+searchVo.getSupervision_code()+"%' ";
		}
		
		if(!searchVo.getBanjie1().equals("")) {
			parm = parm+"	and  endtime  >='"+searchVo.getBanjie1()+"' ";
		}
		if(!searchVo.getBanjie2().equals("")) {
			parm = parm+"	and  endtime  <='"+searchVo.getBanjie2()+"' ";
		}
		 
		
		if(!searchVo.getState().equals("")) {
			if(searchVo.getState().equals("05")) {
				parm = parm+"	and  state='05' ";
 			}
			if(searchVo.getState().equals("02")) {
		 
				parm = parm+"	and  end_time>='"+CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),7)+"'   and end_time>'"+CommonFunc.CurrentDateJianHao()+"' ";
			}
			if(searchVo.getState().equals("03")) {
				
				parm = parm+"	  and end_time>'"+CommonFunc.CurrentDateJianHao()+"' ";
			}
			if(searchVo.getState().equals("01")) {
				
				parm = parm+"	  and end_time<'"+CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),-7)+"'	and   state!='05' ";
			}
		}
		
	
		
		if(!searchVo.getYear().equals("")) {
			parm=parm+"	and 		sendtime like '%"+searchVo.getYear()+"%'";
		}
		
		if(!searchVo.getQuarter().equals("")) {
 			if(searchVo.getQuarter().equals("1")) {
				parm=parm+"	and  sendtime  BETWEEN   	 '"+searchVo.getYear()+"-01-01' AND '"+searchVo.getYear()+"-03-31'  ";
			}
			if(searchVo.getQuarter().equals("2")) {
				parm=parm+"	and  sendtime  BETWEEN  	 '"+searchVo.getYear()+"-04-01' AND '"+searchVo.getYear()+"-06-30'  ";
			}
			if(searchVo.getQuarter().equals("3")) {
				parm=parm+"	and  sendtime   BETWEEN  	 '"+searchVo.getYear()+"-07-01' AND '"+searchVo.getYear()+"-09-30'  ";
			}
			if(searchVo.getQuarter().equals("4")) {
				parm=parm+"	and sendtime BETWEEN 	 '"+searchVo.getYear()+"-10-01' AND '"+searchVo.getYear()+"-12-31'  ";
			}
 		}
		
		if(!searchVo.getWorktime().equals("")) {
			parm=parm+"  and 	substr(sendtime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
					+ " and  substr(sendtime,0,10)<='"+searchVo.getFiveWeekTime()+"'  ";
		}
		
		
		
		String sql = "select  * from  matters t  where  t.flag!='01'  and state!='01'  "+parm+"	";
		return dao.queryForList("queryExcelMattersData", sql);
	}

	public List queryDeleteMatters(SearchVo searchVo, Pagination pagination) throws Exception {
	String parm ="";
		if(!searchVo.getHandover_person().equals("")) {
			parm =parm + "	and   handover_person   like '%"+searchVo.getHandover_person()+"%' 	";

		}
		if(!searchVo.getPrjname().equals("")) {
			parm =parm+"	and   supervision_matter   like '%"+searchVo.getPrjname()+"%' 	";

		}
		String sql = "select  * from  matters t  where  t.flag='01'  "+parm+"		 order by  inittime desc 	";
		System.out.println("SQL==="+sql);
		pagination.setTotalCount((Integer) dao.queryById("queryMattersAdminCount", sql));
		return dao.queryForList("queryDeleteMatters",  CommonFunc.getLimitSql(sql, pagination));
	}

	public void UndoMattersFlag(String id) throws Exception {
		String  sql ="	update  matters  set flag='00',updatetime='"+CommonFunc.CurrentTimeEn()+"', state='01'  	   where  id='"+id+"'	";
		dao.update("UndoMattersFlag", sql);
	}

	public void UndoSendMattersState(String id) throws Exception {
			String  sql ="  update  matters  set  state='01'  where id ='"+id+"'  ";
			System.out.println("SQL===="+sql);
		dao.update("UndoSendMattersState", sql);
	}
	

}
