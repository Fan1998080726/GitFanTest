package com.sdkj.util.mail;

public class sendmail {
	public static void main(String[] args){
		System.out.println("args[3]==="+args[3]);
		System.out.println("args[1]==="+args[1]);
		System.out.println("args[1]==="+args[0]);
		System.out.println("args[4]==="+args[4]);
		try{
	  MailSenderInfo mailInfo = new MailSenderInfo(); 
	  mailInfo.setMailServerHost("smtp.163.com"); 
	  mailInfo.setMailServerPort("25"); 
	  mailInfo.setValidate(true); 
	  mailInfo.setUserName("java_huasheng@163.com"); 
	  mailInfo.setPassword("shaoyan8080"); 
	  mailInfo.setFromAddress("java_huasheng@163.com"); 
	  mailInfo.setToAddress(args[3]); 
	  if(args[1].equals("审核通过")){
	  mailInfo.setSubject(args[0]+"在网上申报系统申请账号的信息   "+args[1]+"   "); 
	  mailInfo.setContent("您的用户名称为:"+args[4]+",初始密码为：123456" +
	  		""); 
	  SimpleMailSender sms = new SimpleMailSender();
         sms.sendTextMail(mailInfo);//发送文体格式
	  }else if(args[1].equals("退回")){
		  mailInfo.setSubject(args[0]+"在网上申报系统申请账号的信息   "+args[1]+"   "); 
		  mailInfo.setContent("审核意见:"+args[2]+",请按要求重新申请账号信息，如有疑问请联系审核部门045187153333-1315"); 
		  SimpleMailSender sms = new SimpleMailSender();
	         sms.sendTextMail(mailInfo);//发送文体格式
		  }
	  System.out.println("send  mail finished..........");
        // sms.sendHtmlMail(mailInfo);//发送html格式
	}catch(Exception ex){
		
	}
	}

}
