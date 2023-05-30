package com.sdkj.check.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.check.service.CheckService;
import com.sdkj.check.vo.CheckVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;

@Service
public class ICheckService  implements CheckService {

	@Autowired
	private  Dao dao;

 

	public List queryCheckStatistics(SearchVo searchVo) throws Exception {
		
		String  parm = "";
		if(!searchVo.getStartdate().equals("")) {
			parm=parm+"	and 		sendtime>='"+searchVo.getStartdate()+"'";
		}
		if(!searchVo.getEnddate().equals("")) {
			parm=parm+"	and 		sendtime<='"+searchVo.getEnddate()+"'";
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
		
		
		
		
		String  sql ="	 select  t.user_name,"
				+ " 	rank() OVER(  ORDER BY to_number(t1.count1 ) desc nulls last    ) AS rankcount,   "
				+ "nvl(t1.count1,0) as count1 ,nvl(t2.count2,0) as count2 ,nvl(t3.count3,0) as count3 ,"
				+ "nvl(t4.count4,0) as count4 from system_user_info t"
				+ " left join "
				+ " ( select user_id,count(*) as count1 from cooperateunits t left join"
				+ "  matters m on t.matter_id=m.matters_id  where t.unitstate='01'  and m.flag!='01'	"+parm+" "
				+ " group by t.user_id) t1"
				+ " on t.id=t1.user_id   "
				+ "left join "
				+ " ( select user_id,count(*) as count2 from cooperateunits t left join  "
				+ "matters m on t.matter_id=m.matters_id  where t.unitstate='01'	and m.flag!='01'	  "+parm+" "
				+ " and  m.state!='05' group by t.user_id) t2"
				+ " on t.id=t2.user_id"
				+ "  left join "
				+ " (   select user_id,count(*) as count3 from cooperateunits t  left join  "
				+ "matters m on t.matter_id=m.matters_id  where  t.unitstate='01' and m.flag!='01'	 "+parm+"   "
				+ "  and  end_time<'"+CommonFunc.CurrentDateJianHao()+"'     and m.state!='05'      group by t.user_id) t3"
				+ " on t.id=t3.user_id"
				+ "  left join "
				+ " (   select user_id,count(*) as count4 from cooperateunits t  left join "
				+ " matters m on t.matter_id=m.matters_id  where"
				+ "  t.unitstate='01'   and m.flag!='01'	  "+parm+"   and m.state='05'    group by t.user_id) t4"
				+ " on t.id=t4.user_id	 where  1=1  and  t.user_name!='ADMIN'  and  t.user_name!='办公室'	";
 
		
			System.out.println("SQL==="+sql);
		
		return dao.queryForList("queryCheckStatistics",sql);
	}



	public List<CheckVo> queryMattersRank(SearchVo searchVo) throws Exception {
		
		String  parm = "";
		if(!searchVo.getStartdate().equals("")) {
			parm=parm+"	and 		sendtime>='"+searchVo.getStartdate()+"'";
		}
		if(!searchVo.getEnddate().equals("")) {
			parm=parm+"	and 		sendtime<='"+searchVo.getEnddate()+"'";
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
		
		
		
		String  sql ="	 select user_name,   rank() OVER( ORDER BY count1   desc   ) AS rankcount ,count1 from ("
				+ "  select    t.user_name,"
				+ "  nvl(t1.count1,0) as count1  from system_user_info t left join "
				+ "    ( select user_id,count(*) as count1 from cooperateunits t left join "
				+ "     matters m on t.matter_id=m.matters_id  where t.unitstate='01'   "+parm+"   group by t.user_id) t1 on t.id=t1.user_id"
				+ "	where  1=1   and  t.user_name!='ADMIN'  and  t.user_name!='办公室'		)";
		
		
		
		
		System.out.println("SQL==="+sql);
		 
		return (List<CheckVo>) dao.queryForList("queryMattersRank",sql);
	}



	public List<CheckVo> queryEndMattersRank(SearchVo searchVo) throws Exception {
		
		String  parm = "";
		if(!searchVo.getStartdate().equals("")) {
			parm=parm+"	and 		sendtime>='"+searchVo.getStartdate()+"'";
		}
		if(!searchVo.getEnddate().equals("")) {
			parm=parm+"	and 		sendtime<='"+searchVo.getEnddate()+"'";
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
		
		
		
		String  sql ="	 select  t.user_name,"
				+ " 	rank() OVER(  ORDER BY to_number(t1.count1 ) desc nulls last    ) AS rankcount,   "
				+ "nvl(t1.count1,0) as count1 ,nvl(t2.count2,0) as count2 ,nvl(t3.count3,0) as count3  from system_user_info t"
				+ " left join "
				+ " ( select user_id,nvl(count(*),0) as count1 from cooperateunits t left join "
				+ " matters m on t.matter_id=m.matters_id  where t.unitstate='01'	and m.flag!='01'	"+parm+" 	"
				+ " group by t.user_id) t1"
				+ " on t.id=t1.user_id   "
				+ "left join "
				+ " ( select user_id,count(*) as count2 from cooperateunits t left join  "
				+ "matters m on t.matter_id=m.matters_id  where t.unitstate='01'	and m.flag!='01'		"+parm+" 	 "
				+ " and  t.state!='05'	 and  end_time<'"+CommonFunc.CurrentDateJianHao()+"'  group by t.user_id	) t2"
				+ " on t.id=t2.user_id"
				+ "  left join "
				+ " (   select user_id,count(*) as count3 from cooperateunits t  left join  matters m on t.matter_id=m.matters_id  where  t.unitstate='01'	and m.flag!='01'	  "
				+ "  and     m.state='05'   group by t.user_id) t3"
				+ " on t.id=t3.user_id	where  1=1   and  t.user_name!='ADMIN'  and  t.user_name!='办公室'		";
		 
		System.out.println("SQL===="+sql);
		return (List<CheckVo>) dao.queryForList("queryEndMattersRank",sql);
	}



	public List<CheckVo> queryCheckStatisticsPhoto(SearchVo searchVo) throws Exception {
		
		String  parm ="";
		
		String  parm1 ="";
		if(!searchVo.getYear().equals("")) {
			 parm =parm +"	and  end_time like '%"+searchVo.getYear()+"%'		";
		} 
		
		if(searchVo.getDeptState().equals("1")) {
			 parm1 =parm1 +" 	and  t.id   in (select t.id from system_user_info t  where t.role_id in"
			 		+ " (select  t.role_id from system_role_info t  where t.dept_id='11'))	";

		}else {
			 parm1 =parm1 +" 	and  t.id   in (select t.id from system_user_info t  where t.role_id in "
			 		+ "(select  t.role_id from system_role_info t  where t.dept_id='10'))	";

		}
		
		
		
		String  sql ="	 select  t.user_name,"
				+ " 	   "
				+ "nvl(t1.count1,0) as count1 ,nvl(t2.count2,0) as count2 from system_user_info t"
				+ " left join "
				+ " ( select user_id,count(*) as count1 from cooperateunits t left join  "
				+ "matters m on t.matter_id=m.matters_id  where t.unitstate='01' and m.flag!='01'	 "+parm+"	"
				+ " group by t.user_id) t1"
				+ " on t.id=t1.user_id   "
				+ "left join "
				+ " ( select user_id,count(*) as count2 from cooperateunits t left join "
				+ " matters m on t.matter_id=m.matters_id  where t.unitstate='01'	 and m.flag!='01'	  "+parm+"	"
				+ " and  m.state='05'	   group by t.user_id	) t2"
				+ " on t.id=t2.user_id	where  1=1  	"+parm1+"";
		
			System.out.println("SQL==="+sql);
		
		return (List<CheckVo>) dao.queryForList("queryCheckStatisticsPhoto",sql);
	}



	public List queryWorkMassRank(SearchVo searchVo) throws Exception {
		String parm ="";
		
		if(searchVo.getYear().equals("")) {
			searchVo.setYear(CommonFunc.CurrentYear());
		}
		
		if (searchVo.getTimeType().equals("0")) {
			parm=parm+"  and 	substr(inittime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
						+ " and  substr(inittime,0,10)<='"+searchVo.getFiveWeekTime()+"'  ";
		}else {
			parm=parm+"  and 	substr(inittime,0,4) like  '%"+searchVo.getYear()+"%'  "
				 ;
		}
		
//			String  sql ="select  * from  (select rank() OVER(  ORDER BY to_number(t.score ) desc nulls last    ) AS rankcount,sys.user_name,t.score,t.userid,"
//					+ "(select  count(*) from feedback f where f.flag='00'  and f.feedback_id=t.userid)  as feedcount from year_usr_score t "
//					+ " left join  system_user_info sys on t.userid=sys.id   where  t.yearvalue='"+searchVo.getYear()+"')  order by  score desc ,feedcount   desc ";

		String  sql ="    select  t.user_name,t.score,t.userid,u.pointscore  ,feedcount,"
				+ " rank() OVER(  ORDER BY to_number( pointscore) asc nulls first    ) AS rankcount  "
				+ " from  (select sys.user_name,t.score,t.userid,"
				+ "   (select  count(*) from feedback f where f.flag='00'   "+parm+"     "
				+ "   and f.feedback_id=t.userid)  as feedcount from year_usr_score t "
				+ "      left join  system_user_info sys on t.userid=sys.id   where  t.yearvalue='"+searchVo.getYear()+"') t left join "
				+ " (select  t.user_id, nvl(sum(score) ,0) as pointscore  from evaluationtable t  where   "
				+ " 1=1  "+parm+" group by t.user_id) u on t.userid=u.user_id  "
								+ " order by pointscore asc nulls first  ";
		System.out.println("SQL===111111111111111111111"+sql);
		return  dao.queryForList("queryWorkMassRank",sql);
	}



	public List<CheckVo> queryPointScore(SearchVo searchVo) throws Exception {
		String parm ="";
		if (searchVo.getTimeType().equals("0")) {
			parm=parm+"  and 	substr(inittime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
						+ " and  substr(inittime,0,10)<='"+searchVo.getFiveWeekTime()+"'  ";
		}else {
			parm=parm+"  and 	substr(inittime,0,4) like  '%"+searchVo.getYear()+"%'  "
				 ;
		}
			String  sql ="	select  t.user_id as userid, sum(score)  as score  from evaluationtable t  where 1=1 "+parm+" group by t.user_id	";
			System.out.println("SQL===="+sql);
		return (List<CheckVo>) dao.queryForList("queryPointScore",sql);
	}


 
	public List<CheckVo> queryStandCheckList(SearchVo searchVo) throws Exception {
		
		String parm ="";
		if (searchVo.getTimeType().equals("0")) {
			parm=parm+"  and 	substr(inittime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
						+ " and  substr(inittime,0,10)<='"+searchVo.getFiveWeekTime()+"'  ";
		}else {
			parm=parm+"  and 	substr(inittime,0,4) like  '%"+searchVo.getYear()+"%'  "  ;
		}
		 	String  sql ="   select  t.user_id  as   userid,t.zbid,sum(score)	 as kfScore from evaluationtable t "
		 			+ "	where  1=1 "+parm+"   "
		 			+ "group by t.user_id,t.zbid order by t.user_id,t.zbid  ";
		 	System.out.println("SQL===="+sql);
		return (List<CheckVo>) dao.queryForList("queryStandCheckList",sql);
	}



	public List<UserInfo> queryDeptUserInfo(SearchVo searchVo) throws Exception {
			String  sql ="select  t.id,t.user_name,t.role_id from system_user_info t  "
					+ "where  t.role_id in (select  t.role_id from system_role_info t  where t.dept_id between '10' and  '11')";
		return (List<UserInfo>) dao.queryForList("queryDeptUserInfo",sql);
	}



	public List<CheckVo> queryMonthDeptCheckStatisticsPhoto(SearchVo searchVo) throws Exception {
		String  parm ="";
		
		if(searchVo.getYear().equals("")) {
			searchVo.setYear(CommonFunc.CurrentYear());
		}
		
		String  sql ="	   select  count1,count2, month1||'月' as  user_name from ("
				+ "  select nvl(t.count1,0) as count1,m.month from (select user_id,count(*) as count1,"
				+ "substr(sendtime,6,2) as month"
				+ " from cooperateunits t left join  matters m on t.matter_id=m.matters_id  where t.unitstate='01' and m.flag!='01'   and m.state!='01' "
				+ " and m.sendtime like '%"+searchVo.getYear()+"%'   and user_id='"+searchVo.getUsername()+"'"
				+ "        group by t.user_id,substr(sendtime,6,2) ) t  right join monthtable m on t.month=m.month     order by m.id asc  "
				+ ") t1 left join ("
				+ "    select nvl(t.count1,0) as count2,m.month as month1 from (select user_id,count(*) as count1,"
				+ "substr(sendtime,6,2) as month"
				+ " from cooperateunits t left join  matters m on t.matter_id=m.matters_id  where t.unitstate='01' and m.flag!='01'   and m.state='05' "
				+ " and m.sendtime like '%"+searchVo.getYear()+"%'   and user_id='"+searchVo.getUsername()+"'"
				+ "    group by t.user_id,substr(sendtime,6,2) ) t  right join monthtable m on t.month=m.month"
				+ "    order by m.id asc ) t2 on t1.month=t2.month1       ";
		return (List<CheckVo>) dao.queryForList("queryMonthDeptCheckStatisticsPhoto",sql);
	}
	
	
	
	
	
	
	
}
