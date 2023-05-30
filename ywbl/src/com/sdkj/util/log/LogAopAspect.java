package com.sdkj.util.log;


import java.lang.reflect.Method;
 import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.opensymphony.xwork2.ActionContext;
import com.sdkj.system.service.impl.LoginServiceImpl;
import com.sdkj.system.vo.LogVo;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Util;


/**
 * AOP实现日志
 * 
 * @author fcl  2021年4月13日14:34:02
 *
 */
@Aspect
@Configuration
public class LogAopAspect {
	
 

//	
	@Autowired
	private  LoginServiceImpl logService;
//	@Around("within(com.sdkj.util.LogAnno+) && "+"@annotation(org.springframework.web.bind.annotation.RequestMapping)") 
//	public ModelAndView handleError(ProceedingJoinPoint joinPoint) {

//	@Pointcut("@annotation(com.sdkj.util.LogAnno)")
//	public void myPointcut(){
//		
//		
//	}

	
//	@Around("within(com.sdkj.util.LogAnno+) && "+"@annotation(org.springframework.web.bind.annotation.RequestMapping)") 
	@Around("@annotation(com.sdkj.util.log.LogAnno)")
    public Object aroundAdvice(ProceedingJoinPoint pjp)   {
		System.out.println("!11111111111111111");
		   Object result = null;
		     LogVo logtable = new LogVo();
		    	Map session = ActionContext.getContext().getSession();
				String dept_name = (String) session.get("dept_name");
				String username = (String) session.get("username");
		    	HttpServletRequest request = ServletActionContext.getRequest();
		     
				String  Prjname = request.getParameter("prjname");
				Prjname = Util.nullToString(Prjname);
				String ids = request.getParameter("ids");
				ids = Util.nullToString(ids);
				
				String PrjId = request.getParameter("PrjId");
				PrjId = Util.nullToString(PrjId);
				String dname = request.getParameter("dname");
				dname = Util.nullToString(dname);
				
				String str = request.getParameter("str");
				str = Util.nullToString(str);
	
				
//				if(!"".equals(Prjname)){
//					str="查询项目名为："+Prjname;
//				}
//				
//				if(!"".equals(PrjId)){
//					str="项目ID为："+PrjId;
//				}
//				
//				if(!"".equals(ids)){
//					str="删除ID为："+ids;
//				}
//				
//				if(!"".equals(dname)){
//					str="："+dname;
//				}
		   try {
			   
		   // 1.方法执行前的处理，相当于前置通知
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        LogAnno logAnno = method.getAnnotation(LogAnno.class);
        // 获取操作描述的属性值
        String operateType = logAnno.operateType();
        // 创建一个日志对象(准备记录日志)
        
        
		
		if(!str.equals("")) {
			if(str.equals("0")) {
				operateType="全部"+operateType;
			}
			if(str.equals("1")) {
				operateType="新到"+operateType;
			}
			if(str.equals("4")) {
				operateType="配合"+operateType;
			}
			
			if(str.equals("3")) {
				operateType="待配合"+operateType;
			}
			if(str.equals("5")) {
				operateType="超期"+operateType;
			}
			
			if(str.equals("6")) {
				operateType="待核验"+operateType;
			}
			
			if(str.equals("7")) {
				operateType="延期"+operateType;
			}
			
			if(str.equals("8")) {
				operateType="核验退回"+operateType;
			}
			
			
			if(str.equals("9")) {
				operateType="办结"+operateType;
			}
		}
        
        
        
        logtable.setDoWhat(operateType);// 操作说明
        logtable.setId(CommonFunc.getGUID());
        
        // 整合了Struts，所有用这种方式获取session中属性(亲测有效)
        logtable.setUsername(username);;// 设置操作人
        logtable.setDeptname(dept_name);
            //让代理方法执行
            try {
				result = pjp.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
            // 2.相当于后置通知(方法成功执行之后走这里)
            logtable.setMethodName(String.valueOf(methodSignature));
            // 设置操作结果
            logtable.setLogState("正常");
        } catch ( Exception e) {
            // 3.相当于异常通知部分
        	// 设置操作结果
            logtable.setLogState("失败");
            
        } finally {
            // 4.相当于最终通知
            try {
				logtable.setCurrentTime(CommonFunc.CurrentTimeEn());// 设置操作日期
				logtable.setUserip(CommonFunc.getRemortIP(request));
				logService.addLog(logtable);// 添加日志记录
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return result;
    }
	
	
	
	
	
	
	
	
	
	
	

}
