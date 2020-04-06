package com.volunteer.uapply.pojo;

import lombok.Data;


/**
 * 用户报名信息对应的数据库对象
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/3 16:04
 */
@Data
public class Resume {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 组织id
     */
    private Integer organizationId;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 性别
     */
    private String userSex;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 学号
     */
    private String userStuNum;

    /**
     * QQ
     */
    private String userQq;

    /**
     * 手机号
     */
    private String userTel;

    /**
     * 专业
     */
    private String profession;

    /**
     * 学院
     */
    private String college;
}
