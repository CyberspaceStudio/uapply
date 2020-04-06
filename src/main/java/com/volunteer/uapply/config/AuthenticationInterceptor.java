package com.volunteer.uapply.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.volunteer.uapply.annotation.MinisterLogin;
import com.volunteer.uapply.annotation.PassToken;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.ResponseException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/2 20:01
 */
public class AuthenticationInterceptor implements HandlerInterceptor {


    public AuthenticationInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
     /*   // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLogin.class)) {
            UserLogin userLogin = method.getAnnotation(UserLogin.class);
            if (userLogin.required()) {
                // 执行认证
                if (token == null) {
                    throw new ResponseException("登陆失败",ResponseResultEnum.USER_NO_TOKEN.getCode(),ResponseResultEnum.USER_NO_TOKEN.getMsg());
                }
                // 获取 token 中的userId
                Integer userId;
                try {
                    userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user =userMapper.findUserByUserId(userId);
                if (user == null) {
                    //用户不存在
                    throw  new ResponseException("用户不存在",ResponseResultEnum.USER_LOGIN_ERROR.getCode(),ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
                }
                //将请求中带有的token与数据库中的token进行比较
                String trueToken = tokenMapper.findTokenByUserId(userId);
                if (trueToken.equals(token)){
                    return true;
                }else {
                    return false;
                }
            }


        }

        //检查有没有需要部长权限的注解
        if (method.isAnnotationPresent(MinisterLogin.class)) {
            MinisterLogin ministerLogin = method.getAnnotation(MinisterLogin.class);
            if (ministerLogin.required()) {
                // 执行认证
                if (token == null) {
                    throw new ResponseException("用户token为空",ResponseResultEnum.USER_NO_TOKEN.getCode(),ResponseResultEnum.USER_NO_TOKEN.getMsg());
                }
                // 获取 token 中的userId
                Integer userId;
                try {
                    userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userMapper.findUserByUserId(userId);
                if (user == null) {
                    //用户不存在
                    throw  new ResponseException("用户不存在",ResponseResultEnum.USER_LOGIN_ERROR.getCode(),ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
                }
                //此处还需要检查用户权限
                if(user.getDepartmentId()<=2){
                    //将请求中带有的token与数据库中的token进行比较
                    String trueToken = tokenMapper.findTokenByUserId(userId);
                    if (trueToken.equals(token)) {
                        return true;
                    } else {
                        throw new ResponseException("用户权限不足",ResponseResultEnum.USER_NO_PERMISSION.getCode(),ResponseResultEnum.USER_NO_PERMISSION.getMsg());
                    }
                }else{
                    return false;
                }


            }
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
