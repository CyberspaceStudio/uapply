package com.volunteer.uapply.pojo.info;

import lombok.Data;

/**
 * 录取短信
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/7 16:32
 */
@Data
public class AliyunEnrollParam {

    /**
     * 接受短信的用户Id
     */
    private Integer[] userId;


    /**
     * 部门Id
     */
    private Integer departmentId;
    /**
     * 别问为什么这样命名，问就是审核不通过
     * QQ群号
     */
    private String secret;
}
