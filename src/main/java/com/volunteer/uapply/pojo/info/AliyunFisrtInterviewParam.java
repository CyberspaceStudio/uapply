package com.volunteer.uapply.pojo.info;

import lombok.Data;

/**
 * 面试短信内容参数对象
 * 模板信息:
 * ${name}同学您好，${department}已收到您的报名信息，感谢你的报名。
 * ${activity}安排如下： 时间：${timeSlot} 。地点：${place} 。如因故不能及时到达，请及时与我们联系${telNo}
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/4 20:25
 */
@Data
public class AliyunFisrtInterviewParam {

    /**
     * 要发送短信的用户Id
     */
    private Integer[] userId;

    /**
     * 部门名称
     */
    private String departmentName;

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
