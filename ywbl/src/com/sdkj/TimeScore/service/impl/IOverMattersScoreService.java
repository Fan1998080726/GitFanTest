package com.sdkj.TimeScore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdkj.TimeScore.service.OverMattersScoreService;
import com.sdkj.TimeScore.vo.EvaluationTableVo;
import com.sdkj.TimeScore.vo.StandardVo;
import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;

@Service
public class IOverMattersScoreService  implements OverMattersScoreService {

	@Autowired
	private  Dao dao;
	
	public List<EvaluationTableVo> GetOverFeedbackMatters(SearchVo searchVo) throws Exception {
		 String sql =" select  c.user_id,c.matter_id as  matters_id ,"
		 		+ "(select  t.score from year_usr_score t  where t.yearvalue='"+CommonFunc.CurrentYear()+"' and  "
		 		+ " t.userid=c.user_id)  as before_score from cooperateunits c  where c.matter_id in"
		 		+ " (  select t.matters_id  from matters t  where t.end_time<'"+CommonFunc.CurrentDateJianHao()+"' and t.flag!='01'	 and  t.state!='05' and  t.state!='04'  and t.state!='01' "
		 		+ ")  and  c.state!='04'       "
		 		+ "  and   c.unitstate='01'  and c.matter_id  not  in (select  e.MATTERS_ID  from evaluationtable e   where   e.zbid='1' )";
		 
		 System.out.println("SQL====="+sql);
//		 String sql =" select  c.user_id,c.matter_id as  matters_id ,(select  t.score from year_usr_score t  where t.yearvalue='"+CommonFunc.CurrentYear()+"' and  "
//				 + " t.userid=c.user_id)  as before_score from cooperateunits c  where c.matter_id in"
//				 + " (  select t.matters_id  from matters t  where t.end_time<'"+CommonFunc.CurrentDateJianHao()+"' "
//				 + " and t.id not in (  select  f.matter_id from feedback f  where f.flag='00' ) "
//				 + ") and   c.unitstate='01'  and c.matter_id  not  in (select  e.MATTERS_ID  from evaluationtable e)";
		 
		 
		return (List<EvaluationTableVo>) dao.queryForList("GetOverFeedbackMatters",sql);
	}

	public String UpdateOverFeedBackScore(EvaluationTableVo evaluationTableVo) throws Exception {
		 	String sql =" update year_usr_score t  set t.score=t.score-"+evaluationTableVo.getMarkScore()+"	"
		 			+ " where  userid='"+evaluationTableVo.getUser_id()+"' "
		 					+ "	and  t.yearvalue='"+CommonFunc.CurrentYear()+"'  ";
		 	dao.update("UpdateOverFeedBackScore", sql);
		 	
		 	
		 	
		 String  	scoreSql  =" select   score  from  year_usr_score t 	where   userid='"+evaluationTableVo.getUser_id()+"'	and  t.yearvalue='"+CommonFunc.CurrentYear()+"'   ";
		 	 
		return (String) dao.queryById("queryUserInfoScoreByID",scoreSql);
	}

	public void UpdateEvalutionData(EvaluationTableVo evaluationTableVo) throws Exception {
			evaluationTableVo.setId(CommonFunc.getGUID());
			evaluationTableVo.setInittime(CommonFunc.CurrentTimeEn());
			evaluationTableVo.setLastupdatetime(CommonFunc.CurrentTimeEn());
			dao.update("addEvaluationTable", evaluationTableVo);
	}

	public StandardVo queryStandardById(String string) throws Exception {
			String sql ="		select t.* from standard t   where  id='"+string+"'	";
		return (StandardVo) dao.queryById("queryStandardById",sql);
	}

	public List queryEvaluation(SearchVo searchVo, Pagination pagination) throws Exception {
	String parm ="";
		if(null!=searchVo.getUsername()&&!"".equals(searchVo.getUsername())) {
			parm ="	and    user_name   like '%"+searchVo.getUsername()+"%' 	";
		}
		String sql = "select  *  from  (select t.id,t.user_name,y.score from system_user_info t  left join "
				+ " (select * from year_usr_score where yearvalue='"+CommonFunc.CurrentYear()+"') y on t.id=y.userid 	 where  1=1"
						+ " and t.role_id in	(	select t.role_id from system_role_info t  where  t.dept_id='11' or t.dept_id='10'	)						)  where  1=1  "+parm;
		pagination.setTotalCount((Integer) dao.queryById("queryEvaluationCount", sql));
		return dao.queryForList("queryEvaluation",  CommonFunc.getLimitSql(sql, pagination));
 
	}

	public List queryUserMattersList(SearchVo searchVo, Pagination pagination) throws Exception {
	String parm ="";
		
		if(null!=searchVo.getPrjname()&&!"".equals(searchVo.getPrjname())) {
			parm ="	and    supervision_matter   like '%"+searchVo.getPrjname()+"%' 	";
		}
			String sql ="select  t.id,m.supervision_matter,t.user_id,t.matter_id  as  matters_id,t.state,t.unitstate "
					+ "  from ( select  * from  cooperateunits t   "
					+ " where t.user_id='"+searchVo.getUserId()+"'  and t.flag='01'  ) t  left join  matters m on t.matter_id=m.matters_id"
							+ " where  1=1 and m.flag!='01'	 "+parm+"";
			pagination.setTotalCount((Integer) dao.queryById("queryUserMattersListCount", sql));
			return dao.queryForList("queryUserMattersList",  CommonFunc.getLimitSql(sql, pagination));
	}

	public List queryUserMattersScore(SearchVo searchVo ) throws Exception {
		String  parm ="";
		if(!searchVo.getMattersId().equals("")) {
			parm = parm+"	and   matter_id ='"+searchVo.getMattersId()+"'";
		}
			String  sql =" select  c.user_id,c.matter_id as  matters_id ,(select  t.score from year_usr_score t  "
					+ "where t.yearvalue='2022' and "
					+ "  t.userid=c.user_id)  as before_score from cooperateunits c  where c.matter_id in"
					+ " (  select t.matters_id  from matters t  "
					+ "	 		) and   c.unitstate='01'  "+parm+"	";
		return dao.queryForList("queryUserMattersScore",sql);
	}

	public List queryStandList(SearchVo searchVo) throws Exception {
			String sql ="	select t.*  from standard t  where  standtype='1' 	";
		return dao.queryForList("queryStandList",sql);
	}

	public List<UserInfo> queryOverMattersUserPhone(String daysAfter, String currentTime, String str)  throws Exception{
		String  parm ="";
		if(str.equals("0")) {
			parm="	 and	t.end_time>'"+daysAfter+"' and  t.end_time>'"+currentTime+"'";
		}else if(str.equals("1")) {
			parm="  and  t.end_time<'"+currentTime+"'";

		}
		 String  sql =" select t.* from (select   s.*,m.id as mid,m.supervision_matter,m.end_time from"
		 		+ " cooperateunits c left join  system_user_info s "
		 		+ "on c.user_id= s.id left join  matters m on m.matters_id=c.matter_id	where   m.flag!='01'	)		 t   where 1=1 "+parm+"  and id is not null ";
		 
		 System.out.println("Sql====="+sql);
		return (List<UserInfo>) dao.queryForList("queryOverMattersUserPhone",sql);
	}

	public void AddSendMsg(UserInfo userInfo) throws Exception {
		 String sql =" insert into sendnotice"
		 		+ "  (id, phone, supervision_matter, mid, user_id, state, inittime)"
		 		+ " values"
		 		+ "  ('"+CommonFunc.getGUID()+"', '"+userInfo.getPhone()+"', '"+userInfo.getSupervision_matter()+"', '"+userInfo.getMid()+"',"
		 				+ " '"+userInfo.getId()+"', '"+userInfo.getState()+"', '"+CommonFunc.CurrentTimeEn()+"')";
		 dao.update("AddSendMsg", sql);
		
	}

	public String queryAfterScore(String userid) throws Exception {
		 String sql ="select t.score from year_usr_score t  where t.userid='"+userid+"'  and  yearvalue='"+CommonFunc.CurrentYear()+"'  ";
		return (String) dao.queryById("queryAfterScore",sql);
	}

	public List<EvaluationTableVo> GetweeklyNotFeedbackUserInfo(SearchVo searchVo) throws Exception {
				String sql =" select  c.user_id,m.matters_id  from cooperateunits  c "
						+ " left join  matters m  on c.matter_id=m.matters_id "
						+ "  where  unitstate='01'  and  m.state!='05' and m.state!='01' and m.flag!='01'	 and  c.user_id not in ("
						+ "  select f.feedback_id from  feedback f where  f.matter_id in(select  t.id from matters t  where t.state!='05' and m.flag!='01'	  and t.state!='01') "
						+ " and  substr(inittime,0,10)>='"+searchVo.getOneWeekTime()+"' and substr(inittime,0,10)<='"+searchVo.getFiveWeekTime()+"' )";
		return (List<EvaluationTableVo>) dao.queryForList("GetweeklyNotFeedbackUserInfo",sql);
	}

	public String  SaveEvakuaCheck(FeedbackVo feedbackVo) throws Exception {
		
		String sqlCount = " select  count(*)  from  evaluationcheck  where   fk_id='"+feedbackVo.getId()+"'  ";
		int  count =  (Integer) dao.queryById("queryFeedbackCheckCount",sqlCount);
		String  code ="0";
			if(count==0) {
				String  sql =" insert into evaluationcheck"
						+ "  (id, user_id, matters_id, inittime, fk_id)"
						+ " values"
						+ "   ('"+CommonFunc.getGUID()+"', '"+feedbackVo.getFeedback_id()+"',"
								+ "'"+feedbackVo.getMatter_id()+"','"+CommonFunc.CurrentTimeEn()+"'"
								+ ",'"+feedbackVo.getId()+"')";
				System.out.println("SQL======"+sql);
				dao.update("addEvakuaCheck", sql);
			}else {
				String sql =" update evaluationcheck"
						+ "   set "
						+ "        state = '1',"
						+ "       lastupdatetime ='"+CommonFunc.CurrentTimeEn()+"'"
						+ "  where        fk_id = '"+feedbackVo.getId()+"'";
				code="1";
				dao.update("updateEvakuaCheck", sql);
			}
		return  code;
		
	}

}
