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
     * 部门
     */
    private String departmentName;


    /**
     * 组织
     */
    private String organizationName;
    /**
     * QQ群号别问为什么这样命名，问就是审核不通过
     */
    private String secret;
}
