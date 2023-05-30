package com.sdkj.project.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.project.service.IVideoView;
import com.sdkj.project.vo.RealViewVo;
import com.sdkj.project.vo.VideoViewVo;
import com.sdkj.util.basic.dao.Dao;
import com.sdkj.util.context.ConvertVideo;
import com.sdkj.util.context.Pagination;
import com.sdkj.util.context.Util;

@Service
public class VideoViewImpl implements IVideoView{

	@Autowired
	private Dao dao;
	
	
	/**
	 * 查询列表
	 */
	public List queryRealViewList(String proid, Pagination pagination)throws Exception {
		
	    StringBuffer sql = new StringBuffer(128);
		
		sql.append("  SELECT ");
		sql.append("  t1.video_id 'videoId', ");
		sql.append(" 	t1.video_name 'videoName', ");
		sql.append(" 	t1.video_url 'videoUrl', ");
		sql.append(" 	t1.video_remark 'videoRemark', ");
		sql.append(" 	cast(date_format(t1.video_date,'%Y-%m-%d %H:%i:%s') as char(19)) 'videoDate', ");
		sql.append(" 	t1.pro_id 'proId', ");
		sql.append(" 	t2.user_name 'videoUserName', ");
		sql.append(" 	t1.video_user 'videoUser' ");
		sql.append(" 	FROM ");
		sql.append(" video_file t1 , system_user_info t2 where t1.video_user = t2.id  and t1.pro_id = " + proid);
		

		pagination.setTotalCount((Integer) dao.queryById("getVideoViewCount", sql.toString()));
		
		String sqlstr = Util.getLimitString(sql.toString(), pagination);
		
		return dao.queryForList("queryVideoViewList", sqlstr);
		
	}

	/**
	 * 添加视频信息
	 */
	@Transactional
	public void addVideoView(VideoViewVo videoViewVo , String profilename ,  String realName) throws Exception {
		
		String url = ConvertVideo.fileConvert(videoViewVo.getVideoUrl() , profilename);
		
		videoViewVo.setVideoUrl(realName.substring(0,realName.lastIndexOf(".")+1) + "flv");
		
		
		dao.save("addVideoView", videoViewVo);
		
	}

	
	
	/**
	 * 
	 * describe:查看视频信息
	 * 
	 */
	public VideoViewVo queryVideoView(String id) throws Exception {
		
		return (VideoViewVo) dao.queryById("queryVideoView", id);
		
	}

	
	
	/**
	 * 
	 * describe:修改添加的内容 
	 * 
	 */
	@Transactional
	public void updVideoView(VideoViewVo videoViewVo, String profilename ,int flag ,  String realName) throws Exception {
		
		if(flag == 0){
     		ConvertVideo.fileConvert(videoViewVo.getVideoUrl() , profilename);
			
			videoViewVo.setVideoUrl(realName.substring(0,realName.lastIndexOf(".")+1) + "flv");
		}
		
		dao.update("updateVideoView", videoViewVo);
		
	}

	
	
	/**
	 * 
	 * describe:删除选中的行 
	 * 
	 * @throws IOException
	 */
	public void delVideoView(String[] selectFlag) throws Exception {
		
		
		for(String i : selectFlag){
			
			dao.delete("delvideoview", i);
			
		}
		
	}

	
	
}
