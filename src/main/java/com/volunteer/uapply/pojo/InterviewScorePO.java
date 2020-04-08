package com.volunteer.uapply.pojo;

import lombok.Data;

/**
 * 面试相关信息
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/4 12:00
 */
@Data
public class InterviewScorePO {

    /**
     * 用户id
     */
    private Integer userId;


    /**
     * 组织id
     */
    private Integer organizationId;

    /**
     * 部门
     */
    private String departmentName;

    //产品说还没有定好，参数名称就先这么写吧,居然有5个参数
    /**
     * 参数1的分数
     */
    private String param1Score;

    /**
     * 参数2的分数
     */
    private String param2Score;

    /**
     * 参数3的分数
     */
    private String param3Score;

    /**
     * 参数4的分数
     */
    private String param4Score;

    /**
     * 参数5的分数
     */
    private String param5Score;

    /**
     * 备注
     */
    private String detail;

    /**
     * 综合评价
     *
     * @mock ABCD
     */
    private String overview;


    /**
     * 评价人
     */
    private String userName;
}
