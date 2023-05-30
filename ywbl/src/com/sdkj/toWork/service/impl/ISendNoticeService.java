package com.sdkj.toWork.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.system.vo.UserInfo;
import com.sdkj.toWork.service.SendNoticeService;
import com.sdkj.toWork.vo.SendNoticeVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Pagination;

@Service
public class ISendNoticeService  implements SendNoticeService	{
	@Autowired
	private Dao dao;
	
	public List querySendNotices(SearchVo searchVo, Pagination pagination) throws Exception {
		String parm ="";
		if(!searchVo.getTitlecontent().equals("")) {
			parm ="	and  TITLECONTENT   like '%"+searchVo.getTitlecontent()+"%' 	";
		}
		String sql = "select  * from  SENDtongzhiNOTICE t  where   1=1  "+parm+"	";
		pagination.setTotalCount((Integer) dao.queryById("querySendNoticesCount", sql));
		return dao.queryForList("querySendNotices",  CommonFunc.getLimitSql(sql, pagination));
	}

	public void SaveSendNotice(SendNoticeVo noticeVo) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		if(noticeVo.getState().equals("02")) {
			noticeVo.setSendtime(CommonFunc.CurrentTimeEn());
		}
		noticeVo.setLasttime(CommonFunc.CurrentTimeEn());
		if(noticeVo.getId().equals("")) {
			noticeVo.setInittime(CommonFunc.CurrentTimeEn());
			noticeVo.setId(CommonFunc.getGUID());
			noticeVo.setUserid(String.valueOf(user_id));
			
			dao.update("addSendNotice", noticeVo);
		}else {
			dao.update("updateSendNotice", noticeVo);
		}
		
		if(noticeVo.getState().equals("02")) {
			
			List<SendNoticeVo> queryUserInfoList = queryUserInfoList();
			for (SendNoticeVo sendNoticeVo : queryUserInfoList) {
				sendNoticeVo.setId(CommonFunc.getGUID());
				sendNoticeVo.setNoticeid(noticeVo.getId());
				dao.update("addnoticerelevance", sendNoticeVo);
			}
 		}
		
	}

	public  List<SendNoticeVo>  queryUserInfoList() throws Exception{
			String  sql ="	select  t.userid   from year_usr_score t	";
		return (List<SendNoticeVo>) dao.queryForList("queryUserInfoList",sql);
	}
	
	public SendNoticeVo queryNoticeById(String id) throws Exception {
			String  sql ="	select   *  from  SENDtongzhiNOTICE  where  id='"+id+"'	";
		return (SendNoticeVo) dao.queryById("queryNoticeById",sql);
	}

	public List querySendUserNotices(SearchVo searchVo, Pagination pagination) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		String parm ="";
		if(!searchVo.getTitlecontent().equals("")) {
			parm ="	and  TITLECONTENT   like '%"+searchVo.getTitlecontent()+"%' 	";
		}
		String  sql ="select t.id, t.fid,t.titlecontent, t.noctioncontent, t.inittime, t.lasttime, t.sendtime ,"
				+ "n.userid,n.state from sendtongzhinotice t"
				+ "   left join noticerelevance n on t.id=n.noticeid"
				+ "   where  n.userid='"+user_id+"'	"+parm+"	";
		System.out.println("SQL===="+sql);
		pagination.setTotalCount((Integer) dao.queryById("querySendUserNoticesCount", sql));
		return dao.queryForList("querySendUserNotices",  CommonFunc.getLimitSql(sql, pagination));
	}

	public void updateNoticeState(String id) throws Exception {
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		String sql ="	update  noticerelevance  set  state='1'  where  noticeid='"+id+"'  and  userid='"+user_id+"'	";
		dao.update("updateNoticeState", sql);
	}

	public void SendNoticeDel(String id) throws Exception {
	 String  sql ="	delete  sendtongzhinotice  where   id='"+id+"'   ";
	 System.out.println("SQL==="+sql);
		dao.delete("SendNoticeDel",sql);

	}

	public void UndoNoticesState(String id) throws Exception {
		String sql ="  update  sendtongzhinotice set   state='01'  where  id ='"+id+"'   ";
		dao.update("UndoNoticesState", sql);
		String  sql1 ="delete    noticerelevance t  where  t.noticeid='"+id+"'";
		dao.delete("DelNoticesStateLevance", sql1);
	}

}
