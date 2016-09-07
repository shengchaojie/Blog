package com.scj.hello.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by shengcj on 2016/8/26.
 */
@Aspect
public class HelloWorldAspect {
    @Pointcut(value ="execution(* com.scj.hello..*.*(..)) && args(param)",argNames = "param")
    public void pointCutWithParam(String param){}

    @Pointcut(value ="execution(* com.scj.hello..*.*(..))")
    public void pointCut(){}

    @Before(value = "pointCutWithParam(param)",argNames = "param")
    public void beforeAdvice(String param) {
        System.out.println("-----------before-------------");
        System.out.println("param=" + param);
    }

    @AfterReturning(value = "pointCutWithParam(param)", returning = "retVal",argNames = "retVal,param")
    public void afterAdvice(Object retVal,String param)
    {
        System.out.println("-----------after-------------");
        System.out.println("retVal="+retVal);
        System.out.println("param="+param);
    }

    @Around(value = "pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
        Object[] params =point.getArgs();

        System.out.println("around before");
        Object retVal= point.proceed(params);
        System.out.println("around after");
        return retVal;
    }

    @DeclareParents(value = "com.scj.hello.service.HelloService+",defaultImpl = com.scj.hello.aspect.ByeServiceImpl.class)
    private ByeService byeService;
}
