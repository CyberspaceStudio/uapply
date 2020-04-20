package com.volunteer.uapply.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.volunteer.uapply.annotation.LxRateLimit;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 限流切面类
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/19 10:29
 */
@Aspect
@Component
@Slf4j
public class LxRateLimitAspect {

    private final static Logger logger = LoggerFactory.getLogger(LxRateLimitAspect.class);

    //初始化令牌桶
    private final RateLimiter rateLimiter = RateLimiter.create(1000);

    /**
     * 定义切点
     * 1、通过扫包切入
     * 2、带有指定注解切入
     */
    @Pointcut("@annotation(com.volunteer.uapply.annotation.LxRateLimit)")
    public void checkPointcut() {

    }

    @ResponseBody
    @Around(value = "checkPointcut()")
    public Object aroundNotice(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //日志记录
        log.info("方法签名:" + signature + "\n参数: " + Arrays.toString(args));
        //获取目标方法
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(LxRateLimit.class)) {
            //获取目标方法的@LxRateLimit注解
            LxRateLimit lxRateLimit = targetMethod.getAnnotation(LxRateLimit.class);
            //将注解的令牌数量放入到令牌桶中
            rateLimiter.setRate(lxRateLimit.perSecond());
            if (!rateLimiter.tryAcquire(lxRateLimit.timeOut(), lxRateLimit.timeOutUnit())) {
                return new UniversalResponseBody(ResponseResultEnum.SERVER_BUSY.getCode(), ResponseResultEnum.SERVER_BUSY.getMsg());
            }
        }
        return pjp.proceed();
    }
}

