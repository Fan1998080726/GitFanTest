package com.sdkj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JiaMi {
    private String userId;
    private String userName;
    private String pwd;
    private String name;
    private String userActivation;
    private String userState;
    private String userInherit;
    private String userRemark;
    private String userTime;
    private String userTaxis;
    private String groupId;
    private String groupParentId;
    private String groupName;
    private String groupActivation;
    private String groupState;
    private String groupInherit;
    private String groupRemark;
    private String groupTime;
    private String groupTaxis;
    private String groupRecordIds;
    private String qianmingpass;
    private String username_tj;
    private DBBean db;
	public JiaMi(){
		init();
	}
	public void init(){
		   userId="";
		    userName="";
		    pwd="";
		    name="";
		    userActivation="";
		    userState="";
		    userInherit="";
		    userRemark="";
		    userTime="";
		    userTaxis="";
		    groupId="";
		    groupParentId="";
		    groupName="";
		    groupActivation="";
		    groupState="";
		    groupInherit="";
		    groupRemark="";
		    groupTime="";
		    groupTaxis="";
		    groupRecordIds="";
		    qianmingpass="";
		    username_tj="";
		    db=DBBean.getInstance();
	}
	
	 public static String encrypt(String input)
	    {
	        if(input == null || input.length() == 0)
	            return "";
	        byte buf[] = input.getBytes();
	        MessageDigest algorithm = null;
	        try
	        {
	            algorithm = MessageDigest.getInstance("MD5");
	        }
	        catch(NoSuchAlgorithmException e)
	        {
	        	e.printStackTrace();
	            return null;
	        }
	        algorithm.reset();
	        algorithm.update(buf);
	        byte digest[] = algorithm.digest();
	        String output = bintoascii(digest);
	        return output;
	    }
	    public static String bintoascii(byte bySourceByte[])
	    {
	        String result = new String();
	        int len = bySourceByte.length;
	        for(int i = 0; i < len; i++)
	        {
	            byte tb = bySourceByte[i];
	            char tmp = (char)(tb >>> 4 & 0xf);
	            char high;
	            if(tmp >= '\n')
	                high = (char)((97 + tmp) - 10);
	            else
	                high = (char)(48 + tmp);
	            result = result + high;
	            tmp = (char)(tb & 0xf);
	            char low;
	            if(tmp >= '\n')
	                low = (char)((97 + tmp) - 10);
	            else
	                low = (char)(48 + tmp);
	            result = result + low;
	        }
	        return result;
	    }
	    public ArrayList QueryUserList(){
	    	ArrayList al = new ArrayList();
	    	JiaMi tb = null;
	        ResultSet rs = null;
	        String sql = "";
	        sql = "select * from mis_sec_user";
	        try{
	        	rs = db.getResults(sql);
		     while(rs.next())
		      {
	        	tb = new JiaMi();
	        	tb.setUserId(rs.getString("userId"));
	        	tb.setUserName(rs.getString("userName"));
	        	tb.setPwd(rs.getString("pwd"));
	        	tb.setName(rs.getString("name"));
			    tb.setUserActivation(rs.getString("userActivation"));
			    tb.setUserState(rs.getString("userState"));
			    tb.setUserInherit(rs.getString("userInherit"));
			    tb.setUserRemark(rs.getString("userRemark"));
			    tb.setUserTime(rs.getString("userTime"));
			    tb.setUserTaxis(rs.getString("userTaxis"));
			    al.add(tb);
			    tb = null;
		      }
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	        finally
	        {
	            db.closeAll();
	        }
	    	return al;
	    }
	    public boolean updatePwd(){
	    	boolean isFinished ;
	    	isFinished = false;
	    	String sql = "";
	    	sql = "update mis_sec_user set pwd='"+encrypt(pwd)+"' where userid='"+userId+"'";
	    	try{
	    	  isFinished = db.execute(sql);	
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
	    	finally{
	    		db.closeAll();
	    	}
	    	return isFinished;
	    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserActivation() {
		return userActivation;
	}
	public void setUserActivation(String userActivation) {
		this.userActivation = userActivation;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserInherit() {
		return userInherit;
	}
	public void setUserInherit(String userInherit) {
		this.userInherit = userInherit;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getUserTime() {
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	public String getUserTaxis() {
		return userTaxis;
	}
	public void setUserTaxis(String userTaxis) {
		this.userTaxis = userTaxis;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupParentId() {
		return groupParentId;
	}
	public void setGroupParentId(String groupParentId) {
		this.groupParentId = groupParentId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupActivation() {
		return groupActivation;
	}
	public void setGroupActivation(String groupActivation) {
		this.groupActivation = groupActivation;
	}
	public String getGroupState() {
		return groupState;
	}
	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}
	public String getGroupInherit() {
		return groupInherit;
	}
	public void setGroupInherit(String groupInherit) {
		this.groupInherit = groupInherit;
	}
	public String getGroupRemark() {
		return groupRemark;
	}
	public void setGroupRemark(String groupRemark) {
		this.groupRemark = groupRemark;
	}
	public String getGroupTime() {
		return groupTime;
	}
	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}
	public String getGroupTaxis() {
		return groupTaxis;
	}
	public void setGroupTaxis(String groupTaxis) {
		this.groupTaxis = groupTaxis;
	}
	public String getGroupRecordIds() {
		return groupRecordIds;
	}
	public void setGroupRecordIds(String groupRecordIds) {
		this.groupRecordIds = groupRecordIds;
	}
	public String getQianmingpass() {
		return qianmingpass;
	}
	public void setQianmingpass(String qianmingpass) {
		this.qianmingpass = qianmingpass;
	}
	public String getUsername_tj() {
		return username_tj;
	}
	public void setUsername_tj(String username_tj) {
		this.username_tj = username_tj;
	}
	public DBBean getDb() {
		return db;
	}
	public void setDb(DBBean db) {
		this.db = db;
	}
	
}
