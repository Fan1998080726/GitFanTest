//20200305
package com.sdkj.util;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.EmptyStackException;


public class checkCID 
{ 
  final int[] wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1}; 
  final int[] vi = {1,0,'X',9,8,7,6,5,4,3,2}; 
  private int[] ai = new int[18]; 
	private DBBean db;
	public checkCID(){
		init();
	}
	public void init(){

		db = DBBean.getInstance();
		
	}
  public String Verify(String idcard)
 { 
	 // System.out.println("tidcard==="+idcard);
  try
  {
   if (idcard.length() == 15) 
   { 
	  
    idcard = uptoeighteen(idcard); 
   }else if (idcard.length() != 18) 
   {  
         return "错误!"; 
   }
//将18位的身份证号码变成15位的
//   if(idcard.length() == 18){
//	   idcard=uptofifteen(idcard);
//   }

   String verify = idcard.substring(17, 18);                   
   String birth = idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14);
  // System.out.println("birth=="+birth);
   if (verify.equals(getVerify(idcard))) 
   { 
	   /*
        if(Integer.parseInt(idcard.substring(16,17))%2==1)
             return "正确! 出生日期："+birth+"性别:女";
        else
             return "正确! 出生日期："+birth+"性别:男";
             */
	   Calendar   cal1   =   Calendar.getInstance();   
       Calendar   today   =   Calendar.getInstance();   
	   
       cal1.set(Integer.parseInt(idcard.substring(6,10)),Integer.parseInt(idcard.substring(10,12)),Integer.parseInt(idcard.substring(12,14)));   
       int age =  getYearDiff(today,cal1);
    //   System.out.println("age==="+age);
       if(age>=65)
    	   return "age";
	   return birth;
   } 
   return "错误!"; 
  }
  catch(EmptyStackException ex)
  {
	  ex.printStackTrace();
        return "错误!"; 
  }
 } 
  /*20150720新增加法验证临时建造师是否超过60周岁，Verify方法用到很多，为了不影响已经使用
  的程序，所以新增加方法*/
  public String VerifySixty(String idcard)
  { 
   try
   {
    if (idcard.length() == 15) 
    { 
 	  
     idcard = uptoeighteen(idcard); 
    }else if (idcard.length() != 18) 
    {  
          return "错误!"; 
    }
 //将18位的身份证号码变成15位的
 //   if(idcard.length() == 18){
// 	   idcard=uptofifteen(idcard);
 //   }

    String verify = idcard.substring(17, 18);                   
    String birth = idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14);
    if (verify.equals(getVerify(idcard))) 
    { 
 	   /*
         if(Integer.parseInt(idcard.substring(16,17))%2==1)
              return "正确! 出生日期："+birth+"性别:女";
         else
              return "正确! 出生日期："+birth+"性别:男";
              */
 	   Calendar   cal1   =   Calendar.getInstance();   
        Calendar   today   =   Calendar.getInstance();   
 	   
        cal1.set(Integer.parseInt(idcard.substring(6,10)),Integer.parseInt(idcard.substring(10,12)),Integer.parseInt(idcard.substring(12,14)));   
        int age =  getYearDiff(today,cal1);
        if(age>=60)
     	   return "overSixty";
 	   return birth;
    } 
    return "错误!"; 
   }
   catch(EmptyStackException ex)
   {
 	  ex.printStackTrace();
         return "错误!"; 
   }
  } 
  
  
  public String Verify_null(String idcard)
 { 
	  try
	  {
	if(idcard!=null){
	   if (idcard.length() == 15) 
	   { 
	    idcard = uptoeighteen(idcard); 
	   }else if (idcard.length() != 18) 
	   {  
	         return "错误!"; 
	   } 
	   String verify = idcard.substring(17, 18);                   
	   String birth = idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14);
	   if (verify.equals(getVerify(idcard))) 
	   { 
		   /*
	        if(Integer.parseInt(idcard.substring(16,17))%2==1)
	             return "正确! 出生日期："+birth+"性别:女";
	        else
	             return "正确! 出生日期："+birth+"性别:男";
	             */
		   Calendar   cal1   =   Calendar.getInstance();   
	       Calendar   today   =   Calendar.getInstance();   
		   
	       cal1.set(Integer.parseInt(idcard.substring(6,10)),Integer.parseInt(idcard.substring(10,12)),Integer.parseInt(idcard.substring(12,14)));   
	       int age =  getYearDiff(today,cal1);
	       if(age>65)
	    	   return "age";
		   return birth;
	   } 
	  }
	   return "错误!"; 
	  }
	  catch(EmptyStackException e)
	  {
		  e.printStackTrace();
	        return "错误!"; 
	  }catch(Exception ex){
		  ex.printStackTrace();
		  return "错误!"; 
	  }
	 } 
  public String Verify_null_jzs(String idcard)//注册建造师是超过65岁给予锁定
  { 
 	  try
 	  {
 	if(idcard!=null){
 	   if (idcard.length() == 15) 
 	   { 
 	    idcard = uptoeighteen(idcard); 
 	   }else if (idcard.length() != 18) 
 	   {  
 	         return "错误!"; 
 	   } 
 	   String verify = idcard.substring(17, 18);                   
 	   String birth = idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14);
 	   if (verify.equals(getVerify(idcard))) 
 	   { 
 		   /*
 	        if(Integer.parseInt(idcard.substring(16,17))%2==1)
 	             return "正确! 出生日期："+birth+"性别:女";
 	        else
 	             return "正确! 出生日期："+birth+"性别:男";
 	             */
 		   Calendar   cal1   =   Calendar.getInstance();   
 	       Calendar   today   =   Calendar.getInstance();   
 		   
 	       cal1.set(Integer.parseInt(idcard.substring(6,10)),Integer.parseInt(idcard.substring(10,12)),Integer.parseInt(idcard.substring(12,14)));   
 	       int age =  getYearDiff(today,cal1);
 	       if(age>65)
 	    	   return "age";
 		   return birth;
 	   } 
 	  }
 	   return "错误!"; 
 	  }
 	  catch(EmptyStackException e)
 	  {
 	        return "错误!"; 
 	  }catch(Exception ex){
 		  ex.printStackTrace();
 		  return "错误!"; 
 	  }
 	 } 
 //get verify 
 private String getVerify(String eightcardid) 
 { 
	
  int remaining = 0; 

  if (eightcardid.length() == 18) 
  { 
   eightcardid = eightcardid.substring(0, 17); 
  } 

  if (eightcardid.length() == 17) 
  { 
   int sum = 0; 
   for (int i = 0; i < 17; i++) 
   { 
    String k = eightcardid.substring(i, i + 1); 
//    try{
    ai[i] = Integer.parseInt(k);
//    }catch(Exception e){
//    	e.printStackTrace();
//    	return "错误!";
//    }
   } 

   for (int i = 0; i < 17; i++) 
   { 
    sum = sum + wi[i] * ai[i]; 
   } 
   remaining = sum % 11; 
  } 

  return remaining == 2 ? "X" : String.valueOf(vi[remaining]); 
 } 
 //15 update to 18 
 private String uptoeighteen(String fifteencardid) 
 { 
  String eightcardid = fifteencardid.substring(0,6); 
  eightcardid = eightcardid + "19"; 
  eightcardid = eightcardid + fifteencardid.substring(6,15); 
  eightcardid = eightcardid + getVerify(eightcardid); 
  return eightcardid; 
 }    
// 18 update to 15
 public String uptofifteen(String eightteencardid) 
 { 
  String fifcardid = eightteencardid.substring(0,6); 
  fifcardid = fifcardid + eightteencardid.substring(8, 17); 
  return fifcardid; 
 }    
 private int getYearDiff(Calendar   cal,   Calendar   cal1){   
	  int   m   =   (cal.get(cal.MONTH)+1)   -   (cal1.get(cal1.MONTH));   
	  int   y   =   (cal.get(cal.YEAR))   -   (cal1.get(cal1.YEAR));   

	  return   (y   *   12   +   m)/12;   
	  }   
	public int VerifyData(String FAZHENGJIGUAN){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
        int count_=0;
        sql = "select count(*) as count_ from PRJCHECKPERSONINFO  where CARDNUM='"+FAZHENGJIGUAN+"' ";
        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   count_ = rs.getInt("count_");
            }
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        finally
        {
            db.closeAll();
        }
        return count_;
}
	public int VerifyUserData(String FAZHENGJIGUAN){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
        int count_=0;
        sql = "select count(*) as count_ from system_user_info  where memo='"+FAZHENGJIGUAN+"' ";
        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   count_ = rs.getInt("count_");
            }
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        finally
        {
            db.closeAll();
        }
        return count_;
}
	public boolean VerifyDataMain(String tablename,String FAZHENGJIGUAN){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
        sql = "select count(*) from " + tablename ;
        sql = sql + " where FAZHENGJIGUAN='"+FAZHENGJIGUAN+"' having  count(*)>1";
        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   isFinished = false;
    	
            }
        	
        }
        catch(Exception ex)
        {
            isFinished = false;
        }
        finally
        {
            db.closeAll();
        }
        return isFinished;
    
}
	public boolean VerifyData(String tablename,String FAZHENGJIGUAN,String ziduan){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
     
        sql = "select * from " + tablename ;
        sql = sql + " where "+ziduan+"='"+FAZHENGJIGUAN+"'";


        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   isFinished = false;
    	
            }
        	
        }
        catch(Exception ex)
        {
            isFinished = false;
        }
        finally
        {
            db.closeAll();
        }
        return isFinished;
    
}
	public boolean VerifyData(String tablename,String FAZHENGJIGUAN,String ziduan,String typename){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
     
        sql = "select * from " + tablename ;
        sql = sql + " where "+ziduan+"='"+FAZHENGJIGUAN+"' and TYPE_NAME='"+typename+"'";

   

        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   isFinished = false;
    	
            }
        	
        }
        catch(Exception ex)
        {
            isFinished = false;
        }
        finally
        {
            db.closeAll();
        }
        return isFinished;
    
}
	public boolean VerifyData(String tablename,String FAZHENGJIGUAN,String ziduan,String typename,String title,String value){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
     
        sql = "select * from " + tablename ;
        sql = sql + " where "+ziduan+"='"+FAZHENGJIGUAN+"' and TYPE_NAME='"+typename+"' and "+title+"<>'"+value+"'";

      

        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   isFinished = false;
    	
            }
        	
        }
        catch(Exception ex)
        {
            isFinished = false;
        }
        finally
        {
            db.closeAll();
        }
        return isFinished;
    
}
	public boolean VerifyData_uqdate(String tablename,String FAZHENGJIGUAN,String ziduan,String myselftitle,String myselfvalue){
        boolean isFinished;
        isFinished = true;
        String sql = "";
        ResultSet rs = null;
     
        sql = "select * from " + tablename ;
        sql = sql + " where "+ziduan+"='"+FAZHENGJIGUAN+"' and "+myselftitle+"<>'"+myselfvalue+"'";

      

        try
        {
        	rs = db.getResults(sql);
           if(rs.next())
            {
        	   isFinished = false;
    	
            }
        	
        }
        catch(Exception ex)
        {
            isFinished = false;
        }
        finally
        {
            db.closeAll();
        }
        return isFinished;
    
}
}

