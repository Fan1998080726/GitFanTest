package com.sdkj.util.basic.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sdkj.util.context.CommonFunc;

/**
 * 数据库连接对象
 */
public class DaoImpl extends SqlMapClientDaoSupport implements Dao{
	
	/**
     * 查询列表数据
     * @param key			xml中的id值
     * @return
     * @throws Exception
     */
	public List<?> queryForList(String key) throws Exception {
		
		return super.getSqlMapClientTemplate().queryForList(key);
	}
	
	/**
     * 根据主键，查询一个对象
     * @param key			xml中的id值
     * @return		Object
     * @throws 		Exception
     */
	public Object queryById(String key ) throws Exception {
		
		return super.getSqlMapClientTemplate().queryForObject(key);
	}
	/**
     * 查询列表数据
     * @param key			xml中的id值
     * @param object		查询参数中的?对应的值	
     * @return
     * @throws Exception
     */
	public List<?> queryForList(String key , Object object) throws Exception {
//System.out.println("queryForList()======"+object.toString());
		return  super.getSqlMapClientTemplate().queryForList(key, object);
		
	}
	/**
     * 根据主键，查询一个对象
     * @param key			xml中的id值
     * @param object		查询参数中的?对应的值	
     * @return		Object
     * @throws 		Exception
     */
	public Object queryById(String key , Object object) throws Exception {
//	//System.out.println("object.toString()="+key+"      "+object.toString());
		return super.getSqlMapClientTemplate().queryForObject(key, object);
	}
	/**
	 * 保存对象
	 * @param obj
	 */
	public void save(String key , Object object)  throws Exception{
		////////////System.out.println(object.toString());
		super.getSqlMapClientTemplate().insert(key, object);
	}
	/**
	 * 更新对象
	 * @param obj
	 */
	public void update(String key , Object object)  throws Exception{
//		////System.out.println("object.toString()="+object.toString());
//		////System.out.println("key.toString()="+key.toString());
		super.getSqlMapClientTemplate().update(key, object);
	}
	/**
	 * 删除对象
	 * @param obj
	 */
	public void delete(String key , Object object)  throws Exception{
		////////System.out.println(object.toString());
		super.getSqlMapClientTemplate().delete(key, object);
	}
	
	
	/**
	 * 保存对象 并返回ID值
	 */
	public int save(String key, Object object, String id) throws Exception {
		
		////////////System.out.println(object.toString());

		super.getSqlMapClientTemplate().insert(key, object);
		
		Field tempField = object.getClass().getDeclaredField(id);  
		tempField.setAccessible(true); 
	
		return Integer.parseInt(String.valueOf(tempField.get(object)));
		
	}
	/**
	 * 保存对象 并返回ID值
	 */
	public String savestr(String key, Object object, String id) throws Exception {
		
		
		////////////System.out.println(object.toString());
		super.getSqlMapClientTemplate().insert(key, object);
		
		Field tempField = object.getClass().getDeclaredField(id);  
		tempField.setAccessible(true); 
		
		return String.valueOf(tempField.get(object));
		
	}
	
	
	/**
	 * 批量删除对象
	 * @param obj
	 */
	public void deleteforlist(String key, Object object) throws Exception {
		
		////////////System.out.println(object.toString());
		
		if( object.getClass().isArray()) {  
			
			Object[] o = (Object[])object;
			
			List list = Arrays.asList(o);
			
			super.getSqlMapClientTemplate().delete(key, list);
		
			
		}else{
			
			super.getSqlMapClientTemplate().delete(key, object);
			
			
		}
		
	}

}

