package cn.com.sd.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;


/*//********************
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
//***************************

*/

public class MyTask  extends TimerTask{
	private static final int C_SCHEDULE_HOUR = 1; 
	  private static boolean isRunning = false; 
	  private ServletContext context = null; 

	  public MyTask() { 
	  } 
	  public MyTask(ServletContext context) { 
	    this.context = context; 
	  } 
	  
	  
	    public void run() { 
		    Calendar cal = Calendar.getInstance(); 
		    
		    //******************
		      String driverClassName = "oracle.jdbc.OracleDriver";
		        String url = "jdbc:oracle:thin:@192.168.110.99:1521:orcl";
		        //   String url = "jdbc:oracle:thin:@192.168.1.4:1521:orcl";
		        
		        String password = "jzsxypj";
		        String user= "jzsxypj";
		    //*******************
		        try {
		            Class.forName(driverClassName);
		        } catch (ClassNotFoundException ex) {
		            System.out.println("加载错误！");
		        }
		        Connection conn = null;
		        
		        try {
		            conn = DriverManager.getConnection(url, user, password);
		            System.out.println("连接成功");
		            if (!isRunning) {
		  		      if (C_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY)) { 
		  		        isRunning = true; 
		  		    
		  		        context.log("jzsxypj-MyTask-开始执行验证建造师年龄是否超龄任务"); 
		  		        //TODO 添加自定义的详细任务，以下只是示例 
		  		        //系统定时接收邮件 
		  		        
		  		       System.out.println("jzsxypj-MyTask-开始执行建造师年龄是否超龄任务");
		   		       System.out.println("jzsxypj-MyTask-验证建造师年龄是否超龄任务执行结束");
		   		       
		  		       isRunning = false; 
		  		       context.log("jzsxypj-MyTask-验证建造师年龄是否超龄任务执行结束"); 
		  		      } 
		  		    } 
		  		    else { 
		  		      context.log("jzsxypj-MyTask-上一次验证建造师年龄是否超龄任务执行还未结束"); 
		  		    } 
		            
		        } catch (SQLException ex1) {
		            System.out.println(ex1);
		            System.out.println("数据库连接失败！");
		        }
	  
}
}
