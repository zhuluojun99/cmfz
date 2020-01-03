package com.baizhi.conf;

import com.baizhi.service.BannerService;
import io.goeasy.GoEasy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  //交由工厂管理此类
@Aspect     //声明此类是一个切面
public class Aop {
    @Autowired
    private BannerService bannerService;
    //定义一个切入点
    @Pointcut(value = ("execution(* com.baizhi.service.BannerService.*(..))"))
    public void pe(){}
    @Around("pe()")
    public Object arround(ProceedingJoinPoint point){
        String name = point.getSignature().getName();
        Object proceed = null;
        try {
            proceed = point.proceed();   //获得执行方法的返回值
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if(!name.contains("query")){
            List<Object> list = bannerService.queryBymonth();
            List<Integer> day = bannerService.queryByday();
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-6317969708b942c694e45ddfa7b58aec");
            goEasy.publish("bannermonth",list.toString());
            goEasy.publish("day",day.toString());
        }
        if(proceed!=null){
            return proceed;
        }else {
            return null;
        }
    }
}
