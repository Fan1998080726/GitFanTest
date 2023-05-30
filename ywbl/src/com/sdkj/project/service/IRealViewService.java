package com.sdkj.project.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sdkj.project.vo.RealViewVo;
import com.sdkj.util.context.Pagination;

public interface IRealViewService {
	/**
	 * 
	 * describe:查询三维实景list
	 * 
	 * @return 2014-5-13
	 * @author txb
	 * @throws Exception
	 */
	public List queryRealViewList(String proId,Pagination pagination) throws Exception;
	/**
	 * 
	 * describe:保存三维实景信息
	 * @param vo
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	public void addRealView(RealViewVo vo) throws Exception;
	/**
	 * 
	 * describe:删除三维实景
	 * @param ids
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	public void delRealView(String ids) throws Exception;
	/**
	 * 
	 * describe:查询指定id的三维实景
	 * @return
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	public RealViewVo queryRealView(String id) throws Exception;
	/**
	 * 
	 * describe:修改三维实景信息
	 * @param vo
	 * @throws Exception
	 * 2014-5-13
	 * @author txb
	 */
	public void updRealView(RealViewVo vo) throws Exception;
}
