package com.volunteer.uapply.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此方法执行时会被日志记录
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/1 13:43
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToLog {
    String value();
}
