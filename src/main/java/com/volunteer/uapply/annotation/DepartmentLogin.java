package com.volunteer.uapply.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要部门登录的操作
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/9 20:14
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentLogin {
    boolean required() default true;
}
