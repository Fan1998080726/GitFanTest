package com.sdkj.util.context;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 

import javax.servlet.http.HttpServletRequest;

public final class CommonFunc
    implements Serializable
{

    public CommonFunc()
    {
    }

    public static void main(String args1[]) throws ParseException
    {
    	int hour = getHours("2022-08-05 13:15:10");

			
			 System.out.println("hour="+hour);
    }
    
    /**
     * 日期相减取得相差小时数
     * @param worktime  参数时间
     * @return
     */
    public static int getHours(String  worktime) {
 		int  hour=0;
        try { 
        	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
    		long nh = 1000 * 60 * 60;// 一小时的毫秒数
    		long nm = 1000 * 60;

    		long diff;
    		
    		System.out.println("sd.parse(worktime).getTime()="+worktime);
    		System.out.println("sd.parse(worktime).getTime()="+sd.parse(worktime).getTime());
    		System.out.println("CommonFunc.CurrentTimeEn()).getTime()="+sd.parse(CommonFunc.CurrentTimeEn()).getTime());
    
     
    			// 获得两个时间的毫秒时间差异
    			diff =   sd.parse(CommonFunc.CurrentTimeEn()).getTime()-sd.parse(worktime).getTime();
    			if(diff>=3600000) {
    				hour=1;
    			} 
        	
        	
        } catch (Exception e) {
            //返回一个0，代表执行出现异常
            return 0;
        }
		return hour;
    }
 
    
    /**
     * 获取分页sql
     * @param str  查询语句
     * @param pagination 分页
     * @return   分页后的sql
     */
	public static  String  getLimitSql(String  str,Pagination pagination ) {
		int count = pagination.getCurrentPageNo() / 15;
		int s = count * 15 + 15;
		String  sql ="";
		sql = "  SELECT *FROM (SELECT ROWNUM AS rowno, t.* " + "      from ( " +str + ") t    "
				+ "   WHERE   1=1   and  ROWNUM <= " + s + "    " + ")table_alias WHERE table_alias.rowno >="
				+ ((pagination.getCurrentPageNo() / 15) * 15 + 1);
		return  sql;
	}
    
	//计算七天后的 fcl 20190115
	public static boolean getDates (String dates){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
	    SimpleDateFormat sdw = new SimpleDateFormat("E");  
	    Date d = null; 
		boolean falg =false;
	   try {
		d=sd.parse(dates);
//		System.out.println(d);
		String q = sdw.format(d);
//		System.out.println(q);
		Date today1 = new Date();
		// 获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = sdf.format(today1);

		Date today7 = new Date();
		//SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c7 = Calendar.getInstance();
		c7.setTime(d);
		c7.add(Calendar.DAY_OF_MONTH, +6);
		d = c7.getTime();
		String str = sdf.format(d);
//		System.out.println("获取七天后的日期: " +str );
//		System.out.println("获取当前日期: " + day1);
//		System.out.println("D"+d);
			String id = "";
				
				String[]  strs=str.split("-");
				String[]  strz=day1.split("-");
				StringBuffer sb = new StringBuffer();
				StringBuffer qq = new StringBuffer();
				
				//七天后的日期 ss
					for(int i=0,len=strs.length;i<len;i++){
//							System.out.println(strs[i].toString());
							sb.append(strs[i]);
					}
					//当前日期  zz
					for(int i=0,len=strz.length;i<len;i++){
//						System.out.println(strz[i].toString());
						qq.append(strz[i]);
					}
					Integer ss = Integer.parseInt(sb.toString());
					Integer zz = Integer.parseInt(qq.toString());
					if(zz>ss){
							falg=false;
					}else{
						falg=true;
					}
	   } catch (ParseException e) {
		e.printStackTrace();
	}
	   return falg;
	}
   
    
    
    
    
    
    
    public static String isnull(String str){
    	if(str==null){
    		str="";
    	}
    	return str;
    }
    
    //fcl  除去周六周天
    public static String getDate(int y, int m, int d) throws ParseException{
    	        if (m == 1 || m == 2) {
    	            m += 12;
    	            y--;
    	        }
    	        int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
    	        int a = 0;
    	        switch (iWeek) {
    	            case 0:
    	                a=3;
    	                break;
    	            case 1:
    	                a=3;
    	                break;
    	            case 2:
    	            	a=5;
    	                break;
    	            case 3:
    	            		a=5;
    	            		break;
    	            case 4:
    	            	a=5;
    	            	break;
    	            case 5:
    	            	a=4;
    	            case 6:
    	            	a=3;
    	                break;
    	        }
    	        String str = String.valueOf(y+"-"+m+"-"+d);
    	        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
    			 Date da = df.parse(str);
    			 Calendar cal = Calendar.getInstance();
    			 cal.setTime(da);
    			 return df.format(new Date(da.getTime() + a* 24 * 60 * 60 * 1000));
    	        
    	        
    	    }
    
    //fcl  计算多少天之后
    public static String getYear(String date, long i) throws ParseException{
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
    			 Date da = df.parse(date);
    			 Calendar cal = Calendar.getInstance();
    			 cal.setTime(da);
    			 return df.format(new Date(da.getTime() + i* 24 * 60 * 60 * 1000));
    	
    }
    
    //20181116 fcl 时间相减
    public static int getDay  (String jbdate,String jbdates) throws Exception{
//    	System.out.println("into day");
    	if(jbdate==null&&jbdates==null){
//    		System.out.println("11111");
    		return 0;
    	}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date  date = sf.parse(jbdate);
		Date  dates = sf.parse(jbdates);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		long time1= cal.getTimeInMillis();
		cal.setTime(dates);
		long time2 = cal.getTimeInMillis();
		long day = (time1-time2)/(1000*3600*24);  
		return Integer.parseInt(String.valueOf(day));
    }
    
    
    public static final int createIntRandom()
    {
        return (int)(1000000000D * Math.random());
    }
    /*20181031 获取指定日期n天后日期*/
    public static String  getDaysAfter(String inputDate,int number) throws ParseException {
    	//	public static void  getDaysAfter() throws ParseException {
   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(sdf1.parse(inputDate));
	calendar.add(Calendar.DAY_OF_MONTH, number);
	date = calendar.getTime();
//	System.out.println(sdf.format(date));
	   return sdf.format(date);
    }
    /*20181031 获取n年后日期*/
    public static String  getAfterMonth(String inputDate,int number) throws ParseException {
        Calendar c = Calendar.getInstance();//获得一个日历的实例   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        Date date = null;   
//        System.out.println("inputDate="+inputDate);
//        try{   
            date = sdf.parse(inputDate);//初始日期   
//            System.out.println("date"+date);
//        }catch(Exception e){  

//        }   
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,number);//在日历的月份上增加6个月
        String strDate = sdf.format(c.getTime());//的到你想要得6个月后的日期   
        return strDate;
}
    /*20181031 获取当前日期n天前日期*/
    public static final String getThreeDaysBefore()
    {
    	 Calendar calendar1 = Calendar.getInstance();
    	  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
    	  calendar1.add(Calendar.DATE, -3);
    	  String three_days_ago = sdf1.format(calendar1.getTime());
//    	  System.out.println(three_days_ago);
    	  
        return three_days_ago ;
    }
    
    
    
    
    
    
    
    
    /*20181031 获取当前日期n天前日期*/
    public static final String getOneDaysAfter()
    {
    	Calendar calendar1 = Calendar.getInstance();
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	calendar1.add(Calendar.DATE, -1);
    	String three_days_ago = sdf1.format(calendar1.getTime());
    	
    	return three_days_ago ;
    }
    /*20181031 获取当前日期n天后日期*/
    public static final String getThreeDaysAfter()
    {
    	 Calendar calendar1 = Calendar.getInstance();
    	  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	  calendar1.add(Calendar.DATE, 3);
    	  String three_days_ago = sdf1.format(calendar1.getTime());
    	  
        return three_days_ago ;
    }
	public static String getRemortIP(HttpServletRequest request) {   
		  if (request.getHeader("x-forwarded-for") == null) {  
		   return request.getRemoteAddr();   
		  }   
		  return request.getHeader("x-forwarded-for");   
		 }  
//把字符转换成日期排序order by to_date(nowtime,'yyyy-mm-dd hh24:mi:ss') desc 
    public static final String getPrjNum(String date_,String PrjNums)
    {
      /*  SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        Date currentDate = Calendar.getInstance().getTime();
        String curTime = formatter.format(currentDate);
        PrjNum= PrjNum +curTime;*/
		date_ = (date_.replaceAll("-", ""));
		date_ = date_.substring(2, date_.length());
		PrjNums= PrjNums +date_;
        return PrjNums ;
    }
    /*20160822返回踏查人进入现场勘查反馈单模块，首先是待办信息显示出来 op是已发送或未发送,state表示待办、办理中、已办结、已反馈等状态*/
 /*   getTcrQueryParmater 踏查人有两个，当着两个踏查人的签名都不是当前用户，那么这些数据就应该是该用户应该将要办理的
    ，如果这两个签名，其中有一个是当前用户，并且另外签名的字段是空的，那么就是办理中的状态
    ，如果两个签名位置，其中有一个是当前用户，并且另外签名的字段也不是空的，那么就是已办结的状态
    ，如果两个签名的位置，其中有一个是当前用户，并且另外签名的字段也不是空的，并且是已发送的状态，那么就是已发送的状态
 */   
    public static final String getTcrQueryParmater(String tcr,String op,String state)
    {
    	String sql=" and ";
    	if(state.equals("待办"))
    		sql=" and feedtcr<>'"+tcr+"' and feedtcrtwo<>'"+tcr+"'  and feedfsztOP='未发送'";
    	else if(state.equals("办理中"))
    		sql=" and (feedtcr='"+tcr+"' or feedtcrtwo='"+tcr+"') and (feedtcr is null or feedtcrtwo is null)  and feedfsztOP='未发送'";
    	else if(state.equals("已办结"))
    		sql=" and (feedtcr='"+tcr+"' or feedtcrtwo='"+tcr+"')  and (feedtcr is not null and  feedtcrtwo is not null) and feedfsztOP='未发送'";
    	else if(state.equals("已反馈"))
    		sql=" and (feedtcr='"+tcr+"' or feedtcrtwo='"+tcr+"')  and (feedtcr is not null and  feedtcrtwo is not null) and feedfsztOP='已发送'";
        return sql ;
    }
    /* getZdzQueryParmater 中队长签名只有一个，当两个踏查人签名的位置都不是空的时候，并且中队长签名的位置是空的状态，那么对于当前用户来说就是待办状态。
    ，如果中队长签名是当前用户，那么对于该中队长来说，就是已办结的状态。
    ，如果中队长签名是当前用户，并且是已发送的状态，那么就表示该反馈的已经发送给审批办
 */
    public static final String getZdzQueryParmater(String feedzdz,String op,String state)
    {
    	String sql=" and ";
    	if(state.equals("待办"))
    		sql=" and feedtcr is not null and feedtcrtwo is not null and feedzdz is null  and feedfsztOP='未发送'";
    	else if(state.equals("已办结"))
    		sql=" and feedzdz='"+feedzdz+"' and feedfsztOP='未发送'";
    	else if(state.equals("已反馈"))
    		sql=" and feedzdz='"+feedzdz+"' and feedfsztOP='已发送'";
        return sql ;
    }
    /*
     * getDdzQueryParmater 大队长签名也只有一个，如果两个踏查人签名和中队签名都不是空的，那么就表示该数据是大队长的待办数据
     * ，如果大队长已经签名，并且反馈单没有发送，那么就表示该反馈单已经办结，但是没有发送
     * ，如果大队长已经签名，并且反馈单已经发送，那么就表示已发送。
     * */
    public static final String getDdzQueryParmater(String feedfzr,String op,String state)
    {
    	String sql=" and ";
    	if(state.equals("待办"))
    		sql=" and feedtcr is not null and feedtcrtwo is not null and feedzdz is not null  and feedfsztOP='未发送'";
    	else if(state.equals("已办结"))
    		sql=" and feedfzr is not null and feedfsztOP='未发送'";
    	else if(state.equals("已反馈"))
    		sql=" and feedfzr is not null and feedfsztOP='已发送'";
        return sql ;
    }
    
    
    public static String dateToString(Calendar cal, String mid)
    {
        String strdate = null;
        try
        {
            Date currentDate = new Date();
            SimpleDateFormat formatter;
            if(mid.equals("-"))
                formatter = new SimpleDateFormat("yyyy-MM-dd");
            else
            if(mid.equals(""))
                formatter = new SimpleDateFormat("yyyyMMdd");
            else
                formatter = new SimpleDateFormat("yyyy年MM月dd日");
            currentDate = cal.getTime();
            strdate = formatter.format(currentDate);
        }
        catch(Exception ex)
        {
        }
        return strdate;
    }

    public static final String CurrentTime()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    
    public static final String CurrentyyyyMMddhhmmssTime()
    {
    	String curTime = "";
    	Date currentDate = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	currentDate = Calendar.getInstance().getTime();
    	curTime = formatter.format(currentDate);
    	return curTime;
    }
    
    
    
    
    public static final String CurrentTimeEn()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentDate()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentDateJianHao()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentDateJianHao1()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String EnCurrentDate()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }


    public static final String CurrentTimeyyyyMMddkkmm()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmm");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentTimeyyyyMMdd()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    
    public static final String CurrentYear()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentMonth()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentDay()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentTimeyyyyMM()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentTimeyyyyMMddkkmmss()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmmss");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentTimeMMddkkmmss()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddkkmmssSSS");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    public static final String CurrentTimeMMddkkmmssSSS()
    {
    	String curTime = "";
    	Date currentDate = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	currentDate = Calendar.getInstance().getTime();
    	curTime = formatter.format(currentDate);
    	return curTime;
    }

    public static String getNowTime()
    {
        return Calendar.getInstance().getTime().toLocaleString();
    }

    public static final String CurrentDate(String curTime)
    {
        String curDate = "";
        return curDate;
    }

    public static final String CurrentTime(String curTime)
    {
        String curDate = "";
        return curDate;
    }

    public static final String getGUID()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkmmssSSS");
        Date currentDate = Calendar.getInstance().getTime();
        String curTime = formatter.format(currentDate);
        return curTime + createIntRandom();
    }
    
    public static final String BeginEndDays(String Begin,String End){//计算两天的差
    	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    	long result = 0;
    	try{
    	Date d1 = ft.parse(Begin);
    	//System.out.println(d1);
    	Date d2 = ft.parse(End);
    	//System.out.println(d2);
    	        long num = d1.getTime();
    	        long num2 = d2.getTime();
    	        long num3 = num2 - num;
    	        result = num3 / 0x5265c00L;
    	}catch(Exception ex){}
    	        return String.valueOf(result);
    }

    public static final String CreateRadom(int iLen, int iType)
    {
        String strRandom = "";
        Random rnd = new Random();
        if(iLen < 0)
            iLen = 5;
        if(iType > 2 || iType < 0)
            iType = 2;
        switch(iType)
        {
        default:
            break;

        case 0: // '\0'
            for(int iLoop = 0; iLoop < iLen; iLoop++)
                strRandom = strRandom + Integer.toString(rnd.nextInt(10));

            break;

        case 1: // '\001'
            for(int iLoop = 0; iLoop < iLen; iLoop++)
                strRandom = strRandom + Integer.toString(35 - rnd.nextInt(10), 36);

            break;

        case 2: // '\002'
            for(int iLoop = 0; iLoop < iLen; iLoop++)
                strRandom = strRandom + Integer.toString(rnd.nextInt(36), 36);

            break;
        }
        return strRandom;
    }
    
    

    public static String addSingleQuote(String inputStr)
    {
        if(inputStr != null)
            inputStr = "'" + inputStr + "'";
        return inputStr;
    }
    
    
    public static String HRB_UnitNum(String inputStr)
    {
    	  if(inputStr != null){
          	if(inputStr.indexOf("道里区")!=-1)
          			inputStr = "230102";
          	else if(inputStr.indexOf("南岗区")!=-1)
                  inputStr = "230103";
          	else if(inputStr.indexOf("道外区")!=-1)
                  inputStr = "230104";
          	else if(inputStr.indexOf("香坊区")!=-1)
                  inputStr = "230106";
          	else if(inputStr.indexOf("动力区")!=-1)
                  inputStr = "230107";
          	else if(inputStr.indexOf("平房区")!=-1)
                  inputStr = "230108";
          	else if(inputStr.indexOf("松北区")!=-1)
                  inputStr = "230109";
          	else if(inputStr.indexOf("呼兰区")!=-1)
                  inputStr = "230111";
          	else if(inputStr.indexOf("依兰县")!=-1)
                  inputStr = "230123";
          	else if(inputStr.indexOf("方正县")!=-1)
                  inputStr = "230124";
          	else if(inputStr.indexOf("宾县")!=-1)
                  inputStr = "230125";
          	else if(inputStr.indexOf("巴彦县")!=-1)
                  inputStr = "230126";
          	else if(inputStr.indexOf("木兰县")!=-1)
                  inputStr = "230127";
          	else if(inputStr.indexOf("通河县")!=-1)
                  inputStr = "230128";
          	else if(inputStr.indexOf("延寿县")!=-1)
                  inputStr = "230129";
          	else if(inputStr.indexOf("阿城市")!=-1)
                  inputStr = "230181";
          	else if(inputStr.indexOf("双城市")!=-1)
                  inputStr = "230182";
          	else if(inputStr.indexOf("尚志市")!=-1)
                  inputStr = "230183";
          	else if(inputStr.indexOf("五常市")!=-1)
                  inputStr = "230184";

          }
        return inputStr;
    }
    
    
    public static String JsjUnitCode(String inputStr)
    {
    	  if(inputStr != null){
          	if(inputStr.indexOf("道里区建设局")!=-1)
          			inputStr = "230102";
          	else if(inputStr.indexOf("南岗区建设局")!=-1)
                  inputStr = "230103";
          	else if(inputStr.indexOf("道外区建设局")!=-1)
                  inputStr = "230104";
          	else if(inputStr.indexOf("香坊区建设局")!=-1)
                  inputStr = "230106";
          	else if(inputStr.indexOf("平房区建设局")!=-1)
                  inputStr = "230108";

          }
        return inputStr;
    }
    public static String JsjUnitShortName(String inputStr)
    {
    	  if(inputStr != null){
          	if(inputStr.indexOf("230102")!=-1)
          			inputStr = "道里"+CurrentYear();
          	else if(inputStr.indexOf("230103")!=-1)
                  inputStr = "南岗"+CurrentYear();
          	else if(inputStr.indexOf("230104")!=-1)
                  inputStr = "道外"+CurrentYear();
          	else if(inputStr.indexOf("230106")!=-1)
                  inputStr = "香坊"+CurrentYear();
          	else if(inputStr.indexOf("230108")!=-1)
                  inputStr = "平房"+CurrentYear();

          }
        return inputStr;
    }
    
    /*20161112获取周一*/
    public static String getMondayOfThisWeek(){
    	Calendar cal = Calendar.getInstance();
    	int n= 0;
    	String monday;
    	cal.add(Calendar.DATE,n*7);
    	cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
    	monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    	//System.out.println(monday);
    	return monday;
    }
    /*20161114传入周一，验证一下，是否是本月的第一个周一*/
    public static boolean booleanMondayOfThisWeek(String dates){
    	Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		int i = 1;
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
			cal.set(Calendar.DAY_OF_MONTH, i++);
		}
		String monday;
		//Date firstMonday = cal.getTime();
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		if(monday.equals(dates))
			return true;
		else
			return false;
    }
    /*20191217 fcl*/
    public static final String CurrentTimes()
    {
        String curTime = "";
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmmss");
        currentDate = Calendar.getInstance().getTime();
        curTime = formatter.format(currentDate);
        return curTime;
    }
    
    
    private static void keepTomcatAlive() throws NullPointerException {}

  

    public static void stopTomcat() {

       try {

            java.lang.Process p = java.lang.Runtime.getRuntime().exec(

                  "net stop \"Apache Tomcat 6\"");

            java.io.BufferedReader in = new java.io.BufferedReader(

                  new java.io.InputStreamReader(p.getInputStream()));

            String s;

            String t = "成功停止";

           boolean restart = false;

           while ((s = in.readLine()) != null) {

              if (s.indexOf(t) != -1) {

                   restart = true;

                  break;

               }

            }

          /*  //////System.out.println("<" + new Date() + "> Tomcat is stop "

                   + (restart ? "OK" : "ERROR"));*/

        } catch (Exception e) {

            e.printStackTrace();

        }

     }

  

    public static void startTomcat() {

       try {

            Process p = java.lang.Runtime.getRuntime().exec(

                  "net stop \"Apache Tomcat 6\"");

        } catch (Exception e) {

            e.printStackTrace();

        }

       try {

            java.lang.Process p = java.lang.Runtime.getRuntime().exec(

                  "net start \"Apache Tomcat 6\"");

            java.io.BufferedReader in = new java.io.BufferedReader(

                  new java.io.InputStreamReader(p.getInputStream()));

            String s;

            String t = "启动成功";

           boolean restart = false;

           while ((s = in.readLine()) != null) {

              if (s.indexOf(t) != -1) {

                   restart = true;

                  break;

               }

            }

           // //////System.out.println("<" + new Date() + "> Tomcat is start "+ (restart ? "OK" : "ERROR"));

        } catch (Exception e) {

            e.printStackTrace();

        }

     }

    public static void main() {
    	
        while (true) {

            try {

                keepTomcatAlive();

                Thread.sleep(3000);//每隔30秒扫描一次,3000是3秒

             } catch (Exception ex) {

   

             }

         }

      }
 
    public static final long getGUID1()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddkmmss");
        Date currentDate = Calendar.getInstance().getTime();
        String curTime = formatter.format(currentDate);
        return Long.parseLong(curTime) ;
    }
    
    private void writeObject(ObjectOutputStream oos)
        throws IOException
    {
        oos.defaultWriteObject();
    }
     
    private void readObject(ObjectInputStream ois)
        throws ClassNotFoundException, IOException
    {
        ois.defaultReadObject();
    }
    

	
	
	

	
	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	
	/**
	 * 周天
	 * @param date
	 * @return
	 */
	public static Date gefirstWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE,4);
		return cal.getTime();
	}
	/**
	 * 周一
	 * @param date
	 * @return
	 */
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	
	
}
