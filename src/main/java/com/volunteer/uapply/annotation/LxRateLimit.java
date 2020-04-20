package com.volunteer.uapply.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/19 10:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LxRateLimit {

    /**
     * @return
     */
    String value() default "";

    /**
     * 每秒向桶中放入令牌的数量
     * 根据项目，所以此处暂定默认为1000
     *
     * @return
     */
    double perSecond() default 1000;

    /**
     * 获取令牌的等待时间  默认0
     *
     * @return
     */
    int timeOut() default 0;

    /**
     * 超时时间单位
     *
     * @return
     */
    TimeUnit timeOutUnit() default TimeUnit.MILLISECONDS;
}
