package com.sdkj.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.project.service.IRealViewService;
import com.sdkj.project.vo.RealViewVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;

@Service
public class RealViewServiceImpl implements IRealViewService {
	@Autowired
	private Dao dao;

	/**
	 * 
	 * describe:查询三维实景list
	 * 
	 * @return 2014-5-13
	 * @author txb
	 * @throws Exception
	 */
	public List queryRealViewList(String proId,Pagination pagination) throws Exception {
		StringBuffer sql = new StringBuffer(128);
		
		sql.append(" SELECT");
		sql.append(" t1.real_id 'realId',");
		sql.append(" t1.real_name 'realName',");
		sql.append(" t1.real_url 'realUrl',");
		sql.append(" t1.real_remark 'realRemark',");
		sql.append(" cast(date_format(t1.real_date,'%Y-%m-%d %H:%i:%s') as char(19)) 'realDate',");
		sql.append(" t1.pro_id 'proId',");
		sql.append(" t2.user_name 'realUserName',");
		sql.append(" t1.real_user 'realUser'");
		sql.append(" FROM");
		sql.append(" real_view t1 , system_user_info t2 where t1.real_user = t2.id and t1.pro_id = "+proId);

		pagination.setTotalCount((Integer) dao.queryById(
				"getRealViewCount", sql.toString()));
		
		String sql2 = Util.getLimitString(sql.toString(), pagination);
		
		return dao.queryForList("queryRealViewList", sql2);
	}
	/**
	 * 
	 * describe:保存三维实景信息
	 * @param vo
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	@Transactional
	public void addRealView(RealViewVo vo) throws Exception{
		dao.update("addRealView", vo);
	}
	/**
	 * 
	 * describe:修改三维实景信息
	 * @param vo
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	@Transactional
	public void updRealView(RealViewVo vo) throws Exception{
		dao.update("updRealView", vo);
	}
	/**
	 * 
	 * describe:删除三维实景
	 * @param ids
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	@Transactional
	public void delRealView(String ids) throws Exception{
		dao.delete("delRealView", ids);
	}
	/**
	 * 
	 * describe:查询指定id的三维实景
	 * @return
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	public RealViewVo queryRealView(String id) throws Exception{
		return (RealViewVo) dao.queryById("queryRealView", id);
	}
}
