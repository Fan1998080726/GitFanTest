
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   InitLog4j.java

package com.wx.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class InitLog4j extends HttpServlet
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InitLog4j()
    {
    }

    public void init()
        throws ServletException
    {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j");
        System.out.println("file="+file);
        System.out.println("prefix="+prefix);
        
        if(file != null)
            PropertyConfigurator.configure(prefix + file);
    }
}
