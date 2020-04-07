package com.volunteer.uapply.pojo;

import lombok.Data;

/**
 * 面试状态
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/5 13:30
 */
@Data
public class InterviewStatus {

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
     * 第一志愿
     */
    private String firstChoice;

    /**
     * 第一志愿状态
     */
    private String firstStatus;

    /**
     * 第二志愿
     */
    private String secondChoice;

    /**
     * 第二志愿状态
     */
    private String secondStatus;

    /**
     * 复试部门
     */
    private String retestChoice;

    /**
     * 复试状态
     */
    private String retestStatus;

    public InterviewStatus(Integer userId, Integer organizationId, String organizationName, String firstChoice, String secondChoice) {
        this.userId = userId;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
    }
}
