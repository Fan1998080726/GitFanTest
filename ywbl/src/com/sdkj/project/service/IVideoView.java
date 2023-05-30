package com.sdkj.project.service;

import java.io.IOException;
import java.util.List;

import com.sdkj.project.vo.VideoViewVo;
import com.sdkj.util.context.Pagination;

public interface IVideoView {

	
	/**
	 * 查询视频列表
	 * @param valueOf
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	List queryRealViewList(String valueOf, Pagination pagination) throws Exception;

	/**
	 *   添加视频信息 
	 */
	void addVideoView(VideoViewVo videoViewVo , String profilename , String realName) throws Exception;

	
	/**
	 * 修改视频信息跳转
	 * @param id
	 * @return
	 * @throws Exception
	 */
	VideoViewVo queryVideoView(String id)  throws Exception;

	
	
	/**
	 * 
	 * describe:修改添加的内容 
	 * 
	 */
	void updVideoView(VideoViewVo videoViewVo, String profilename , int flag , String realName)  throws Exception;

	
	
	/**
	 * 
	 * describe:删除选中的行 
	 * 
	 * @throws IOException
	 */
	void delVideoView(String[] selectFlag) throws Exception;

}
