package com.baizhi.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component  //交由工厂管理此类
@Aspect     //声明此类是一个切面
public class spect {
    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object arround(ProceedingJoinPoint point) {
        //判断是否有缓存
        HashOperations hashOperations = redisTemplate.opsForHash();
        String nameSpace = point.getTarget().getClass().getName();//获得到nameSpace
        String name = point.getSignature().getName();//获得方法名
        Object[] args = point.getArgs();//获得所有的参数
        //进行参数拼接作为小key
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (Object arg : args) {
            sb.append(arg.toString());
        }
        //判断缓存区是否有缓存
        if (hashOperations.hasKey(nameSpace, sb)) {
            //存在 直接返回缓存区的数据 不再查询
            Object o = hashOperations.get(nameSpace, sb);
            return o;
        }
        //缓存区没有数据就从数据库中进行查询
        Object proceed = null;
        try {
            proceed = point.proceed();//获取到数据
            hashOperations.put(nameSpace, sb, proceed);//查到数据后将数据添加至缓存区
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;

    }

    //定义一个后置通知用于清除缓存
    @After("@annotation(com.baizhi.annotation.ClearCache)") //自定义清除缓存用的注解
    public void after(JoinPoint joinPoint) {
        String nameSpace = joinPoint.getTarget().getClass().getName();//获得到nameSpace
        //进行缓存的清除
        redisTemplate.delete(nameSpace);
    }
}
