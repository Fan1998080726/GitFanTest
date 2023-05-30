package com.sdkj.util.basic.dao;

import java.util.List;


public interface Dao {
	/**
     * 根据hql查询列表数据
     * @param key			xml中的id值
     * @return
     * @throws Exception
     */
	public List<?> queryForList(String key) throws Exception ;
	
	/**
     * 根据主键，查询一个对象
     * @param key			xml中的id值
     * @return		Object
     * @throws 		Exception
     */
	public Object queryById(String key ) throws Exception ;
	/**
     * 根据hql查询列表数据
     * @param key			xml中的id值
     * @param object		查询参数中的?对应的值	
     * @return
     * @throws Exception
     */
	public List<?> queryForList(String key , Object object) throws Exception ;
	/**
     * 根据主键，查询一个对象
     * @param key			xml中的id值
     * @param object		查询参数中的?对应的值	
     * @return		Object
     * @throws 		Exception
     */
	public Object queryById(String key , Object object) throws Exception ;
	/**
	 * 保存对象
	 * @param obj
	 */
	public void save(String key , Object object)  throws Exception;
	/**
	 * 更新对象
	 * @param obj
	 */
	public void update(String key , Object object)  throws Exception;
	/**
	 * 删除对象
	 * @param obj
	 */
	public void delete(String key , Object object)  throws Exception;
	
	/**
	 * 保存对象  (并且返回ID) 必须为int 类型
	 * @param key     xml中的id值
	 * @param object  查询参数中的?对应的值
	 * @param id      实体id名称
	 * @return        返回插入的id值
	 * @throws Exception
	 */
	public int save(String key , Object object , String id) throws Exception;
	
	
	/**
	 * 保存对象  (并且返回字符串类型ID) 
	 * @param key     xml中的id值
	 * @param object  查询参数中的?对应的值
	 * @param id      实体id名称
	 * @return        返回插入的id值
	 * @throws Exception
	 */
	public String savestr(String key , Object object , String id) throws Exception;
	
	
	/**
	 * 删除对象  
	 * @param key     xml中的id值
	 * @param object  查询参数中的?对应的值
	 * @param id      实体id名称
	 * @return        返回插入的id值
	 * @throws Exception
	 */
	public void deleteforlist(String key , Object object) throws Exception;
	
}
