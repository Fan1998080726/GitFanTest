package com.wx.util;
import java.util.Date;

import com.wx.vo.TextMessage;


/*
 * 消息处理工具类
 */
public class MessageUtil {
	public static final String MSGTYPE_EVENT = "event";//消息类型--事件
	public static final String MESSAGE_SUBSCIBE = "subscribe";//消息事件类型--订阅事件
	public static final String MESSAGE_UNSUBSCIBE = "unsubscribe";//消息事件类型--取消订阅事件
	public static final String MESSAGE_TEXT = "text";//消息类型--文本消息
	
	/*
	 * 组装文本消息
	 */
	public static String textMsg(String toUserName,String fromUserName,String content){
//		System.out.println("into textMsg     组装文本消息");
		TextMessage text = new TextMessage();
		try{
//			System.out.println("aaaaaa");
		text.setFromUserName(toUserName);
//		System.out.println("bbbbb");
		text.setToUserName(fromUserName);
//		System.out.println("cccccccc");
		text.setMsgType(MESSAGE_TEXT);
//		System.out.println("dddddd");
		text.setCreateTime(new Date().getTime());
//		System.out.println("eeeeeee");
		text.setContent(content);
//		System.out.println("ffffffffff");
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		System.out.println("content==="+content);
		return XmlUtil.textMsgToxml(text);
	}
	
	/*
	 * 响应订阅事件--回复文本消息
	 */
	public static String subscribeForText(String toUserName,String fromUserName){
//		System.out.println("into subscribeForText    响应订阅事件--回复文本消息");
		return textMsg(toUserName, fromUserName, "欢迎关注哈尔滨市住房和城乡建设局公众号！！！");
	}
	
	/*
	 * 响应取消订阅事件
	 */
	public static String unsubscribe(String toUserName,String fromUserName){
		//TODO 可以进行取关后的其他后续业务处理
//		System.out.println("用户："+ fromUserName +"取消关注~");
		return "";
	}
}