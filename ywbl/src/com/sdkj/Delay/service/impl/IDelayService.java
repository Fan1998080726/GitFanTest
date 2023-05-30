package com.sdkj.Delay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sdkj.Delay.service.DelayService;
import com.sdkj.Delay.vo.DelayVo;
import com.sdkj.util.Search.SearchVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.CommonFunc;

@Service
public class IDelayService	implements  DelayService {

	@Autowired
	private Dao dao;
	
	public void SaveDelay(DelayVo delayVo) throws Exception {
		
			Map session = ActionContext.getContext().getSession();
			int user_id=(Integer)session.get("user_id");
			delayVo.setUserid(String.valueOf(user_id));
			delayVo.setId(CommonFunc.getGUID());
			dao.update("addDelay", delayVo);
	}

	public DelayVo queryDelayById(String id, String mattersid, String state) throws Exception {
		String  sql ="";
		Map session = ActionContext.getContext().getSession();
		int user_id=(Integer)session.get("user_id");
		if(id.equals("")) {
			sql =" select  *  from  delay  where 	matter_id='"+mattersid+"'  and  state='"+state+"'	and     userid='"+user_id+"'	 	";
		}else {
			sql =" select  *  from  delay  where 	 id='"+id+"' 	";

		}
 		System.out.println("delayVo===="+sql);
		return (DelayVo) dao.queryById("queryDelayById",sql);
	}

	public List<DelayVo> queryAllDelay(SearchVo searchVo) throws Exception {
		 	String sql =" select t.*,m.supervision_matter  ,sys.user_name,role.role_name from delay t"
		 			+ " left join  matters m on t.matter_id=m.id left join system_user_info sys on t.userid=sys.id left join "
		 			+ " system_role_info role on sys.role_id=role.role_id  where  1=1	and  t.state='01'  and m.flag!='01'	 ";
		return (List<DelayVo>) dao.queryForList("queryAllDelay",sql);
	}

	public void SaveAuditDelay(DelayVo delayVo, String str) throws Exception {
		 if(str.equals("1")) {
			 delayVo.setState("03");
		 }else {
			 delayVo.setState("02");
		 }
		 delayVo.setShenhetime(CommonFunc.CurrentTimeEn());
		 
		 
		 dao.update("SaveAuditDelay", delayVo);
		 
		 
		
	}

}
