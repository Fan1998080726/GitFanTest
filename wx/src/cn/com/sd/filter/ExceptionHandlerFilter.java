package cn.com.sd.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class ExceptionHandlerFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			//System.out.println(CommonFunc.CurrentTime()+"jzsxypj ExceptionHandlerFilter");
			Throwable rootCause = e;

			while (rootCause.getCause() != null) {
				rootCause = rootCause.getCause();
			}

			String message = rootCause.getMessage();

			message = message == null ? "�쳣��" + rootCause.getClass().getName()
					: message;

			request.setAttribute("message", message);
			request.setAttribute("e", e);
			//System.out.println(CommonFunc.CurrentTime()+"jzsxypj ExceptionHandlerFilter ");
			//System.out.println("message="+message);
			Writer w = new StringWriter();
		     e.printStackTrace(new PrintWriter(w));
		     String smsg = w.toString(); 
		     
			//if(!message.equals("Connection reset by peer: socket write error"))
			//e.printStackTrace();
		
		
				request.getRequestDispatcher("/exception.jsp").forward(request,
						response);
				return;
		}
	}
                                   
	public void init(FilterConfig arg0) throws ServletException {
	}
}
