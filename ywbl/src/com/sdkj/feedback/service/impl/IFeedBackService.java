package com.sdkj.feedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.feedback.service.FeedBackService;
import com.sdkj.feedback.vo.FeedbackVo;
import com.sdkj.matters.vo.MattersVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;

@Service
public class IFeedBackService  implements FeedBackService {

	@Autowired
	private Dao dao;
	
	public void SaveFeedBack(FeedbackVo feedbackVo) throws Exception {
			Map session = ActionContext.getContext().getSession();
//			System.out.println(((String)session.get("role_name")));
			int role_id=(Integer)session.get("role_id");
			int user_id=(Integer)session.get("user_id");
			System.out.println("user_id="+user_id);
			if(feedbackVo.getId().equals("")) {
				feedbackVo.setFeedback_id(String.valueOf(user_id));
				feedbackVo.setFeedback_dept(String.valueOf(role_id));
				feedbackVo.setFeedback_person(((String)session.get("username")));
				feedbackVo.setId(CommonFunc.getGUID());
				feedbackVo.setInittime(CommonFunc.CurrentTimeEn());
				System.out.println("feedbackVo="+feedbackVo);
				dao.update("SaveFeedBack", feedbackVo);
			}else {
				feedbackVo.setState("0");
				dao.update("updateFeedBack", feedbackVo);
			}
		
			
			
	}

	public List<FeedbackVo> queryFeedBackList(String id) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		
		
		
		String sql ="select  * from (select  * from   feedback t  where   t.matter_id	='"+id+"'	and feedback_id='"+user_id+"'		and  FLAG!='01'	order by  inittime desc )  where  rownum=1	";	
		return (List<FeedbackVo>) dao.queryForList("queryFeedBackList",sql);
	}

	public void delFeedBack(String ids) throws Exception {
			String[] id = ids.split(",");
			String sql ="";
			for (String strId : id) {
				sql =" update  feedback  set 	flag='01'	 where  id='"+strId+"'	  ";
				dao.update("delFeedBack", sql);
			} 
	}

	public List queryAllFeedBack(SearchVo searchVo, Pagination pagination) throws Exception {
		
		String  parm ="";
		
		if(!searchVo.getPersonName().equals("")) {
			parm ="	and  feedback_person  like '%"+searchVo.getPersonName()+"%'	";
		}
		
		String sql ="select  t.*,u.c_id from   feedback t 	left  join  (Select  c_id From uploadfile Where "
				+ "cf_id In (Select Max(cf_id) From uploadfile Group By c_id))  u  on  t.fid=u.c_id  where"
				+ "   t.matter_id	='"+searchVo.getMattersId()+"'	"+parm+"	and  FLAG!='01'   order by  inittime desc  		";	
		
		System.out.println("SQL==="+sql);
		pagination.setTotalCount((Integer) dao.queryById("queryAllFeedBackCount", sql));
		Map session = ActionContext.getContext().getSession();

		int user_id=(Integer)session.get("user_id");

		List<FeedbackVo> queryForList = (List<FeedbackVo>) dao.queryForList("queryAllFeedBack",  CommonFunc.getLimitSql(sql, pagination));
		for (FeedbackVo feedbackVo : queryForList) {
			/**
			 * 当前用户下的反馈信息
			 */
			if(String.valueOf(user_id).equals(feedbackVo.getFeedback_id())) {
				/**
				 * 0为未超过一小时
				 */
				feedbackVo.setIsDel(CommonFunc.getHours(feedbackVo.getInittime()));
			}
			
		}
		
		return queryForList;
	}

	public Integer queryRequestDelayCount(String id,String state) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		String parm ="";
		if(!id.equals("")) {
			parm=parm+" and  matter_id='"+id+"'";
		}
		/**
		 * 不是管理部门时
		 */
		if(user_id!=10359) {
			parm=parm+"   and 	   userid='"+user_id+"'  ";
		}
		
		String sql =" select   count(*)  from delay  where   1=1   and  state='"+state+"'  "+parm+"	";
		
		
		return (Integer) dao.queryById("queryRequestDelayCount",sql);
	}

	public int queryHeyanCount(MattersVo mattersVo) throws Exception {
		 String  sql ="select  count(*) from cooperateunits t  where t.matter_id='"+mattersVo.getMatters_id()+"' and t.state!='03'	 and t.unitstate='01' ";
		 System.out.println("SQL==="+sql);
		return (Integer) dao.queryById("queryHeyanCount",sql);
	}

	public FeedbackVo queryFeedbackById(String id) throws Exception {
			String  sql="	select   * from  feedback  where  id='"+id+"'	";
		return (FeedbackVo) dao.queryById("queryFeedbackById",sql);
	}

	public void UpdateFeedBackState(String id,String  str, String content) throws Exception {
		String  parm ="";
		if(null!=content) {
			parm=	",STATECONTENT='"+content+"'";
		}
		
		
		String  sql="update  feedback  set state='"+str+"',STATETIME='"+CommonFunc.CurrentTimeEn()+"' 	"+parm+"	 where  id='"+id+"'    ";
		dao.update("UpdateFeedBackState", sql);
		
	}

	public List queryFeedback(SearchVo searchVo) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		
			String sql ="	select  m.supervision_matter,t.* from feedback t left join"
					+ "  matters m  on t.matter_id=m.id  where  t.flag='00' and t.state='2'	   and  feedback_id='"+user_id+"'   ";
		return dao.queryForList("queryFeedback",sql);
	}

	public List QueryAllFeedback(SearchVo searchVo) throws Exception {
			String sql ="select  * from  ("
					+ "select t.id, t.inittime,t.feedback_person,'正常' as feedbacktype,'0' as score,'及时报录'  as pjcontent,(select m.supervision_matter "
					+ " from  matters  m where m.id=t.matter_id  and m.flag!='01'	 )  as mattername from feedback t  where  t.feedback_id='"+searchVo.getId()+"'"
							+ " and    substr(t.inittime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
						+ " and  substr(t.inittime,0,10)<='"+searchVo.getFiveWeekTime()+"'        "
					+ "union    "
					+ "select t.id,t.inittime,'系统' as feedback_person ,'扣分' as feedbacktype,t.score,"
					+ "(     select  s.zbname from  standard s where s.id=t.zbid     )  as pjcontent ,"
					+ "(select    supervision_matter  from matters m where  m.matters_id=t.matters_id and m.flag!='01'	 )     as mattername  "
					+ "    from evaluationtable t where  t.user_id='"+searchVo.getId()+"'	"
							+ "and    substr(t.inittime,0,10)>='"+searchVo.getOneWeekTime()+"'  "
						+ " and  substr(t.inittime,0,10)<='"+searchVo.getFiveWeekTime()+"'      )  order by mattername,score	 ";
			
			System.out.println("SQL===="+sql);
			
			
			
		return dao.queryForList("QueryAllFeedback",sql);
	}

	public String queryUserName(String id) throws Exception {
		 	String  sql =" select   user_name   from   system_user_info  where  id='"+id+"'";
		return (String) dao.queryById("queryUserName",sql);
	}

	public String  queryFeedBackContent(String matters_id) throws Exception {
		String  parm ="";
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		
//		if(user_id!=10359) {
//			parm ="	and  t.feedback_id='"+user_id+"'	";
//		}
		
		String  sql =" select *  from"
				+ " (select  t.feedback_breaks  from feedback t	   where 		t.matter_id='"+matters_id+"'  "+parm+"  "
				+ "  order by t.inittime desc  )  where rownum=1";
		return  (String) dao.queryById("queryFeedBackContent",sql);
	}

}
