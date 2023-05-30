package com.sdkj.project.vo;



/**
 *  视频展示VO
 */
public class VideoViewVo {

	
	
	private int videoId;     //上传视频ID
	private String videoName;//上传视频名称
	private String videoUrl;//上传视频路径
	private String videoRemark;//上传视频备注
	private String videoDate;//上传时间
	private String proId;//项目ID
	private String videoUser;//上传视频人员ID
	private String videoUserName;//上传视频人员姓名
	
	
	
	/*20150910*/
	private String SysTime;/*录入数据的系统时间*/
	
	
	public String getSysTime(){
		return SysTime;
		}
	public void setSysTime(String sysTime){
		SysTime = sysTime;
		}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoRemark() {
		return videoRemark;
	}
	public void setVideoRemark(String videoRemark) {
		this.videoRemark = videoRemark;
	}
	public String getVideoDate() {
		return videoDate;
	}
	public void setVideoDate(String videoDate) {
		this.videoDate = videoDate;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getVideoUser() {
		return videoUser;
	}
	public void setVideoUser(String videoUser) {
		this.videoUser = videoUser;
	}
	public String getVideoUserName() {
		return videoUserName;
	}
	public void setVideoUserName(String videoUserName) {
		this.videoUserName = videoUserName;
	}

	
	
	
	
	
}
