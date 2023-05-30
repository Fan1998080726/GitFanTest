package com.wx.vo;

public class YqfkInfoVo {
	private String id;
	private String title;//标题
	private String enclosure;//附件
	private String noticeinfo;//通知内容
	private String sendtime;//发送时间
	private String sendpeople;//发送人
	private String type;//消息类型（通知 或 文件）
	private String savetype;//保存类型（保存 或 发送）
	
	private String filename;
	
	
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSavetype() {
		return savetype;
	}
	public void setSavetype(String savetype) {
		this.savetype = savetype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getNoticeinfo() {
		return noticeinfo;
	}
	public void setNoticeinfo(String noticeinfo) {
		this.noticeinfo = noticeinfo;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getSendpeople() {
		return sendpeople;
	}
	public void setSendpeople(String sendpeople) {
		this.sendpeople = sendpeople;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
