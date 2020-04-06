package com.volunteer.uapply.pojo.info;

import lombok.Data;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/8 11:53
 */
@Data
public class AliyunSecondInterviewParam {

    /**
     * 接受短信的用户Id
     */
    private Integer[] userId;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 时间
     */
    private String timeSlot;

    /**
     * 地点
     */
    private String place;

    /**
     * 联系电话
     */
    private String telNo;
}
