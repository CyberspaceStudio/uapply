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
    private String userQQNum;

    /**
     * 手机号
     */
    private String userTel;

    /**
     * 专业
     */
    private String userProfession;

    /**
     * 学院
     */
    private String userCollege;

    /**
     * 爱好
     */
    private String userHobby;

    /**
     * 自我介绍
     */
    private String userIntroduction;

    /**
     * 家长是否同意(1 代表同意，0 代表不同意)
     */
    private Integer question1;

    /**
     * 是否知晓被录取后不得退出(1 代表知晓，0 代表不知晓)
     */
    private Integer question2;

    /**
     * 是否服从支教队安排(1 代表服从，0 代表不服从)
     */
    private Integer question3;

    /**
     * 是否服从调剂(1 代表服从，0 代表不服从)
     */
    private Integer obeyAdjustment;
}
