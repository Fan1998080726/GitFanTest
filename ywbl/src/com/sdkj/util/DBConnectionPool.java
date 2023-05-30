


package com.sdkj.util;

import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

// Referenced classes of package cn.com.sd.util:
//            Log4jAgent

class DBConnectionPool
{

    private int checkedOut;
    private Vector freeConnections;
    private int maxConn;
    private String name;
    private String password;
    private String URL;
    private String user;
    private static Logger logger;
     /* synthetic field */

    public DBConnectionPool(String name, String URL, String user, String password, int maxConn)
    {
        freeConnections = new Vector();
        this.name = name;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
    }

    public synchronized void freeConnection(Connection con)
    {
        freeConnections.addElement(con);
        checkedOut--;
        notifyAll();
    }

    public synchronized Connection getConnection()
    {
    	//System.out.println("aaaaaaa");
        Connection con = null;
        if(freeConnections.size() > 0)
        {
            con = (Connection)freeConnections.firstElement();
            freeConnections.removeElementAt(0);
            try
            {
                if(con.isClosed())
                    con = getConnection();
            }
            catch(SQLException e)
            {
            	
                con = getConnection();
            }
        } else
        if(maxConn == 0 || checkedOut < maxConn){
        	//System.out.println("aaaaaaa3333333333");
            con = newConnection();
            //System.out.println("aaaaaaa2222222222");
        }
        if(con != null)
            checkedOut++;
        return con;
    }

    public synchronized Connection getConnection(long timeout)
    {
        long startTime = (new java.util.Date()).getTime();
        Connection con;
        while((con = getConnection()) == null) 
        {
            try
            {
                wait(timeout);
            }
            catch(InterruptedException interruptedexception) { }
            if((new java.util.Date()).getTime() - startTime >= timeout)
                return null;
        }
        return con;
    }

    public synchronized void release()
    {
        for(Enumeration allConnections = freeConnections.elements(); allConnections.hasMoreElements();)
        {
            Connection con = (Connection)allConnections.nextElement();
            try
            {
                con.close();
              //  logger.info("�ر����ӳ�" + name + "�е�һ������;");
            }
            catch(SQLException e)
            {
              //  logger.error("�޷��ر����ӳ�" + name + "�е�����;" + "�쳣:" + e.toString());
            }
        }

        freeConnections.removeAllElements();
    }

    private Connection newConnection()
    {
        Connection con = null;
        try
        {
            if(user == null){
                con = DriverManager.getConnection(URL);
            }
            else{
            	//System.out.println("222222222");
            	//System.out.println(URL);
            	//System.out.println(user);
            	//System.out.println(password);
            	try{
                con = DriverManager.getConnection(URL, user, password);
            	} catch(SQLException e)
                {
                    //  logger.error("�޷���������URL������: " + URL + ";�쳣:" + e.toString());
                  	  //System.out.println("333333333333333aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                      return null;
                  }
                //System.out.println("222222222222222222");
            }
          //  logger.info("���ӳ�" + name + "����һ���µ�����;");
        }
        catch(SQLException e)
        {
          //  logger.error("�޷���������URL������: " + URL + ";�쳣:" + e.toString());
        	  //System.out.println("333333333333333bbbbbbba");
            return null;
        }
        return con;
    }

    static 
    {
      //  logger = Log4jAgent.getLogger(cn.com.sd.util.DBConnectionPool.class.getName());
    }
}
