

package com.sdkj.util;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.*;
import java.util.Vector;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.sdkj.util.context.CommonFunc;



public class DBBean
    implements Serializable
{

	    private static DBBean instance0;
	    private static DBBean instance1;
	    private static DBBean instance2;
	    private static DBBean instance3;
	    private static DBBean instance4;
	    private static DBBean instance5;
	    private static DBBean instance6;
	    private static DBBean instance7;
	    private static DBBean instance8;
	    private static DBBean instance9;
	    private static DBBean instance10;
	    private static DBBean instance11;
	    private static DBBean instance12;
	    private static DBBean instance13;
	    private static DBBean instance14;
	    private static DBBean instance15;
	    private static DBBean instance16;
	    private static DBBean instance17;
	    private static DBBean instance18;
	    private static DBBean instance19;
	   
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet result;
    private DataSource ds;
    private DBConnectionManager dbConnectionManager;
    private static int dbbean_i = 0;
    private static Logger logger;
    int i;
     /* synthetic field */

    private DBBean()
    {
        conn = null;
        stmt = null;
        result = null;
        ds = null;
        dbConnectionManager = null;
        i = 0;
        init();
    }

    public static synchronized DBBean getInstance()
    {
        DBBean instance = null;
        if(19 < dbbean_i)
            dbbean_i = 0;
        switch(dbbean_i)
        {
        default:
            break;

        case 0: // '\0'
            if(instance0 == null)
                instance0 = new DBBean();
            dbbean_i++;
            instance = instance0;
            break;

        case 1: // '\001'
            if(instance1 == null)
                instance1 = new DBBean();
            dbbean_i++;
            instance = instance1;
            break;

        case 2: // '\002'
            if(instance2 == null)
                instance2 = new DBBean();
            dbbean_i++;
            instance = instance2;
            break;

        case 3: // '\003'
            if(instance3 == null)
                instance3 = new DBBean();
            dbbean_i++;
            instance = instance3;
            break;

        case 4: // '\004'
            if(instance4 == null)
                instance4 = new DBBean();
            dbbean_i++;
            instance = instance4;
            break;

        case 5: // '\005'
            if(instance5 == null)
                instance5 = new DBBean();
            dbbean_i++;
            instance = instance5;
            break;

        case 6: // '\006'
            if(instance6 == null)
                instance6 = new DBBean();
            dbbean_i++;
            instance = instance6;
            break;

        case 7: // '\007'
            if(instance7 == null)
                instance7 = new DBBean();
            dbbean_i++;
            instance = instance7;
            break;

        case 8: // '\b'
            if(instance8 == null)
                instance8 = new DBBean();
            dbbean_i++;
            instance = instance8;
            break;

        case 9: // '\t'
            if(instance9 == null)
                instance9 = new DBBean();
            dbbean_i++;
            instance = instance9;
            break;

        case 10: // '\n'
            if(instance10 == null)
                instance10 = new DBBean();
            dbbean_i++;
            instance = instance10;
            break;

        case 11: // '\013'
            if(instance11 == null)
                instance11 = new DBBean();
            dbbean_i++;
            instance = instance11;
            break;

        case 12: // '\f'
            if(instance12 == null)
                instance12 = new DBBean();
            dbbean_i++;
            instance = instance12;
            break;

        case 13: // '\r'
            if(instance13 == null)
                instance13 = new DBBean();
            dbbean_i++;
            instance = instance13;
            break;

        case 14: // '\016'
            if(instance14 == null)
                instance14 = new DBBean();
            dbbean_i++;
            instance = instance14;
            break;

        case 15: // '\017'
            if(instance15 == null)
                instance15 = new DBBean();
            dbbean_i++;
            instance = instance15;
            break;

        case 16: // '\020'
            if(instance16 == null)
                instance16 = new DBBean();
            dbbean_i++;
            instance = instance16;
            break;

        case 17: // '\021'
            if(instance17 == null)
                instance17 = new DBBean();
            dbbean_i++;
            instance = instance17;
            break;

        case 18: // '\022'
            if(instance18 == null)
                instance18 = new DBBean();
            dbbean_i++;
            instance = instance18;
            break;

        case 19: // '\023'
            if(instance19 == null)
                instance19 = new DBBean();
            dbbean_i++;
            instance = instance19;
            break;
      
        }
        return instance;
    }


    private void init()
    {
        dbConnectionManager = DBConnectionManager.getInstance();
        try
        {
        	
            conn = dbConnectionManager.getConnection("POOL");
           
        }
        catch(Exception ex)
        {
           // logger.error("锟斤拷l锟接池伙拷取l锟接达拷锟斤拷:" + ex.toString());
        }
    }

    public void closeAll()
    {
        try
        {
            if(result != null)
                result.close();
            if(stmt != null)
                stmt.close();
            dbConnectionManager.freeConnection("POOL", conn);
        }
        catch(Exception ex)
        {
            logger.error("锟酵凤拷锟斤拷锟皆达拷锟皆达拷斐�" + ex.toString());
        }
    }

    public Connection getConn()
    {
        return conn;
    }

    public void CloseConn()
    {
        try
        {
            closeAll();
        }
        catch(Exception exception) { }
    }

    public void CloseStmt()
    {
        try
        {
            closeAll();
        }
        catch(Exception exception) { }
    }

    public Connection getConn(boolean parameter)
    {
        return conn;
    }

    public ResultSet getResults(String SQLText)
        throws Exception
    {
    	////System.out.println("jzsxypj"+CommonFunc.CurrentTime()+" "+SQLText);
        result = null;
        try
        {
            if(SQLText != null && !SQLText.equals(""))
            {
                stmt = conn.prepareStatement(SQLText);
                result = stmt.executeQuery();
            }
        }
        catch(Exception ex)
        {
            logger.error(ex.toString());
        }
        return result;
    }
	public void prepareStatement(String SQLText) throws SQLException {
		try{
			
			  if(SQLText != null && !SQLText.equals(""))
	            {
				  stmt = conn.prepareStatement(SQLText);
	            }
		} catch(Exception ex)
        {
          
            logger.error(ex.toString());
        }
	}
    public boolean execute(String SQLText)
       
    {
    	////System.out.println(CommonFunc.CurrentTime()+" "+SQLText);
        boolean isfinished;
        isfinished = false;
        int iType = 1005;
        int iConcur = 1008;
        try
        {
            if(SQLText != null && !SQLText.equals(""))
            {
                stmt = conn.prepareStatement(SQLText, iType, iConcur);
                int i = stmt.executeUpdate();
                if(i == 0)
                    isfinished = false;
                else
                    isfinished = true;
            }
        }
        catch(Exception ex)
        {
        	//ex.printStackTrace();
            isfinished = false;
           // logger.error(ex.toString());
        }
        finally
        {
            closeAll();
        }
        return isfinished;
    }
    
    /**@todo add at 2007-9-28**/
    /**
     * 锟斤拷锟斤拷锟斤拷执锟斤拷SQL锟斤拷浞斤拷锟斤拷锟�
     * 实锟街斤拷Vector 锟叫碉拷要么锟斤拷SQL锟斤拷锟饺拷锟街达拷校锟揭匆伙拷锟揭诧拷锟街达拷小锟�
     * 执锟叫成癸拷锟斤拷锟斤拷1
     * vect锟侥筹拷锟街匡拷SQL锟斤拷锟�锟斤拷锟斤拷锟斤拷失锟杰ｏ拷锟斤拷锟斤拷-1
     */
    public boolean execute(Vector vect)  throws Exception {
      int iType = ResultSet.TYPE_SCROLL_SENSITIVE;
      int iConcur = ResultSet.CONCUR_UPDATABLE;
      int result;
      result = 0;
      String sql="";
      this.getConn(false); //锟斤拷锟斤拷锟斤拷锟结交
      try
      {
        for (int i = 0; i < vect.size(); i++)
        {
          sql=(vect.get(i)).toString();
          ////System.out.println(i+"jzsxypj"+CommonFunc.CurrentTime()+" "+sql);

          if (sql.equals(""))
          {
        	  //System.out.println("000000000000");
            this.getConn().rollback();//锟结交失锟斤拷,锟斤拷l锟接憋拷为锟皆讹拷锟结交锟斤拷
            return false;
          }
          logger.debug("锟斤拷执锟叫碉拷SQL锟斤拷锟�*******"+sql);
          //System.out.println("bugsql="+sql);
          stmt=conn.prepareStatement(sql,iType,iConcur);
        
          result=stmt.executeUpdate();
          this.closeAll();
        }
        conn.commit();
        conn.setAutoCommit(true);
        this.closeAll();
      }
      catch(Exception ex)
      {
    	  ex.printStackTrace();
        logger.debug("锟斤拷锟斤拷执锟斤拷锟斤拷锟斤拷失锟杰ｏ拷"+ex.toString());
        this.getConn().rollback();//锟结交失锟斤拷,锟斤拷l锟接憋拷为锟皆讹拷锟结交锟斤拷
        this.getConn(true);
        this.closeAll();
        return false;
      }
      finally{
        this.closeAll();
      }
      return true;
    }
	/**
	 * 执锟斤拷SQL锟斤拷锟�
	 * @param sql SQL锟斤拷锟�
	 */
	public void executeUpdate() throws SQLException {
		try{
		if (stmt != null)
			stmt.executeUpdate();
		}catch(Exception ex)
	     {
		    logger.error(ex.toString());
            ex.printStackTrace();
	      }
	      finally{
	        this.closeAll();
	      }
	}
    /**
	 * 锟斤拷锟矫讹拷应值
     *
	 * @param index 锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param value 锟斤拷应值
	 */
	public void setString(int index,String value) throws SQLException {
		
		//
		stmt.setString(index, value);
	}
	public void setInt(int index,int value) throws SQLException {
		stmt.setInt(index,value);
	}
	public void setBoolean(int index,boolean value) throws SQLException {
		stmt.setBoolean(index,value);
	}
	public void setDate(int index,Date value) throws SQLException {
		stmt.setDate(index,value);
	}
	public void setLong(int index,long value) throws SQLException {
		stmt.setLong(index,value);
	}
	public void setFloat(int index,float value) throws SQLException {
		stmt.setFloat(index,value);
	}
	public void setBytes(int index,byte[] value) throws SQLException{
		stmt.setBytes(index,value);
	}
	public void setBlob(int index,Blob value) throws SQLException{
		stmt.setBlob(index,value);
	}
	public void setClob(int index,Clob value) throws SQLException{
		stmt.setClob(index,value);
	}
    static 
    {
        //logger = Log4jAgent.getLogger(cn.com.sd.util.DBBean.class.getName());
    }
}
