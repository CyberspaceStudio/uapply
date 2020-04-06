package com.volunteer.uapply.aspect;

import com.volunteer.uapply.annotation.ToLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志记录的切面
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/1 17:22
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    /**
     * 将含有ToLog注解的方法作为PointCut
     */
    @Pointcut("@annotation(com.volunteer.uapply.annotation.ToLog)")
    public void logPointCut() {
    }


    @Around(value = "logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取执行方法签名，这里强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        // 通过反射来获取注解内容
        ToLog toLog = signature.getMethod().getAnnotation(ToLog.class);
        String description = toLog.value();
        Object result = null;

        long start = System.currentTimeMillis();
        try {
        } catch (Throwable throwable) {
            // 调用原来的方法
            result = joinPoint.proceed();
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("注解变量值: " + description + "\n方法签名: " + signature + "\n参数: " + Arrays.toString(args) + "\n返回结果: " + result + "\n耗时:  " + (end - start) + " ms");
        return result;
    }
}
