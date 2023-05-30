package cn.com.sd.listener;

import java.util.Timer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnLineCountListener
  implements HttpSessionListener, ServletContextListener, ServletContextAttributeListener
{
  private int count;
  private ServletContext context = null;
  private HttpServletResponse response;
  private Timer timer = null;

  public OnLineCountListener()
  {
    this.count = 0;
  }

  public void contextInitialized(ServletContextEvent event)
  {
    this.context = event.getServletContext();
    log("contextInitialized()");
    this.timer = new Timer(true);
    event.getServletContext().log( "jzsxypj-OnLineCountListener-定时器已启动");
//    this.timer.schedule(new MyTask(event.getServletContext()), 0L, 3600000L);
//    this.timer.schedule(new ProjectSecord(event.getServletContext()), 0L, 3600000L);

    event.getServletContext().log( "jzsxypj-OnLineCountListener-已经添加任务调度表");
  }

  public void contextDestroyed(ServletContextEvent event)
  {
    log("contextDestroyed()");
    this.context = null;
    this.timer.cancel();
    event.getServletContext().log( "jzsxypj-OnLineCountListener-定时器销毁");
  }

  private void log(String message)
  {
  }

  public void sessionCreated(HttpSessionEvent se)
  {
    this.count += 1;

    setContext(se);
  }

  public void sessionDestroyed(HttpSessionEvent se)
  {
    this.count -= 1;

    setContext(se);
  }

  public void setContext(HttpSessionEvent se)
  {
    se.getSession().getServletContext().setAttribute("onLine", new Integer(this.count));
  }

  public void attributeAdded(ServletContextAttributeEvent event)
  {
    log("attributeAdded('" + event.getName() + "',   '" + 
      event.getValue() + "')");
  }

  public void attributeRemoved(ServletContextAttributeEvent event)
  {
    log("attributeRemoved('" + event.getName() + "',   '" + 
      event.getValue() + "')");
  }

  public void attributeReplaced(ServletContextAttributeEvent event)
  {
    log("attributeReplaced('" + event.getName() + "',   '" + 
      event.getValue() + "')");
  }
}