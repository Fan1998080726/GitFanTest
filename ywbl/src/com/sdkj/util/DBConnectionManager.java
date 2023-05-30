
package com.sdkj.util;

import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

// Referenced classes of package cn.com.sd.util:
//            Log4jAgent, DBConnectionPool

public class DBConnectionManager
{

    private static DBConnectionManager instance;
    private static int clients;
    private Vector drivers;
    private Hashtable pools;
    private static Logger logger;
     /* synthetic field */

    public static synchronized DBConnectionManager getInstance()
    {
        if(instance == null)
            instance = new DBConnectionManager();
        clients++;
        return instance;
    }

    private DBConnectionManager()
    {
        drivers = new Vector();
        pools = new Hashtable();
        init();
    }

    public void freeConnection(String name, Connection con)
    {
        DBConnectionPool pool = (DBConnectionPool)pools.get(name);
        if(pool != null)
            pool.freeConnection(con);
    }

    public Connection getConnection(String name)
    {
    	
        DBConnectionPool pool = (DBConnectionPool)pools.get(name);
        if(pool != null){
            return pool.getConnection();
          
        }
        else{
            return null;
        }
        
    }

    public Connection getConnection(String name, long time)
    {
        DBConnectionPool pool = (DBConnectionPool)pools.get(name);
        if(pool != null)
            return pool.getConnection(time);
        else
            return null;
    }

    public synchronized void release()
    {
        if(--clients != 0)
            return;
        DBConnectionPool pool;
        for(Enumeration allPools = pools.elements(); allPools.hasMoreElements(); pool.release())
            pool = (DBConnectionPool)allPools.nextElement();

        for(Enumeration allDrivers = drivers.elements(); allDrivers.hasMoreElements();)
        {
            Driver driver = (Driver)allDrivers.nextElement();
            try
            {
                DriverManager.deregisterDriver(driver);
               // logger.info("����JDBC����� " + driver.getClass().getName() + "��ע��");
            }
            catch(SQLException e)
            {
                //logger.error("�޷���������JDBC������ע��: " + driver.getClass().getName() + ";�쳣:" + e.toString());
            }
        }

    }

    private void createPools(Properties props)
    {
        for(Enumeration propNames = props.propertyNames(); propNames.hasMoreElements();)
        {
            String name = (String)propNames.nextElement();
            if(name.endsWith(".url"))
            {
                String poolName = name.substring(0, name.lastIndexOf("."));
                String url = props.getProperty(poolName + ".url");
                if(url == null)
                {
                   // logger.info("û��Ϊ���ӳ�" + poolName + "ָ��URL");
                } else
                {
                    String user = props.getProperty(poolName + ".user");
                    String password = props.getProperty(poolName + ".password");
                    String maxconn = props.getProperty(poolName + ".maxconn", "0");
                    int max;
                    try
                    {
                        max = Integer.valueOf(maxconn).intValue();
                    }
                    catch(NumberFormatException e)
                    {
                       // logger.error("������������������: " + maxconn + " .���ӳ�: " + poolName + ";�쳣:" + e.toString());
                        max = 0;
                    }
                    DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
                    pools.put(poolName, pool);
                   // logger.info("�ɹ��������ӳ�" + poolName);
                }
            }
        }

    }

    private void init()
    {
        java.io.InputStream is = getClass().getResourceAsStream("/db.properties");
        Properties dbProps = new Properties();
        try
        {
            dbProps.load(is);
        }
        catch(Exception e)
        {
            //logger.error("���ܶ�ȡ�����ļ�. ��ȷ��db.properties��CLASSPATHָ����·����;");
            return;
        }
        try
        {
            loadDrivers(dbProps);
        }
        catch(Exception ex)
        {
            //logger.error("װ�����쳣: " + ex.toString());
        }
        try
        {
            createPools(dbProps);
        }
        catch(Exception ex)
        {
            //logger.error("�������ӳ��쳣: " + ex.toString());
        }
    }

    private void loadDrivers(Properties props)
    {
        String driverClasses = props.getProperty("drivers");
        for(StringTokenizer st = new StringTokenizer(driverClasses); st.hasMoreElements();)
        {
            String driverClassName = st.nextToken().trim();
            try
            {
                Driver driver = (Driver)Class.forName(driverClassName).newInstance();
                DriverManager.registerDriver(driver);
                drivers.addElement(driver);
               // logger.info("�ɹ�ע��JDBC�����" + driverClassName);
            }
            catch(Exception e)
            {
              //  logger.error("�޷�ע��JDBC�����: " + driverClassName + ", �쳣: " + e.toString());
            }
        }

    }

    static 
    {
       // logger = Log4jAgent.getLogger(cn.com.sd.util.DBConnectionManager.class.getName());
    }
}
