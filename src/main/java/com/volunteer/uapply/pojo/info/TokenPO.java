package com.volunteer.uapply.pojo.info;

import lombok.Data;

/**
 * token数据库实体类
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/2 20:53
 */
@Data
public class TokenPO<T> {


    private T t;

    private String token;

    public TokenPO(T t, String token) {
        this.t = t;
        this.token = token;
    }
}
