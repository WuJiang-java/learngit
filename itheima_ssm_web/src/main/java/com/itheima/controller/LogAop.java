package com.itheima.controller;

import com.itheima.pojo.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/8 20:08
 * \* Description:日志的切面
 * \
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz;    //访问的类
    private Method method;  //执行的方法

    //前置通知，主要获取开始时间，执行类是哪一个，执行的是哪一个方法
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime=new Date();   //当前时间就是访问的时间
        clazz=jp.getTarget().getClass();    //具体要访问的类
        String methodName=jp.getSignature().getName();  //获取访问方法的名称

        Object[] args = jp.getArgs();   //获取访问参数

        //获取具体执行的Method对象
        if(args==null||args.length==0){
            method=clazz.getMethod(methodName); //只能获取无参的方法
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }
    }


    //后置通知
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp){

        //获取访问时长
        long time =new Date().getTime()-visitTime.getTime();
        String url="";

        //获取url
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            //1.获取类上的注解
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();

                //2.获取方法上的value值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url=classValue[0]+methodValue[0];

                    //获取访问的ip地址
                    String ip=request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context= SecurityContextHolder.getContext();    //从上下文获取了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();

                    String username = user.getUsername();

                    //将日志相关信息封装到对象中
                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用service完成insert操作
                    sysLogService.save(sysLog);



                }
            }

        }



    }
}
