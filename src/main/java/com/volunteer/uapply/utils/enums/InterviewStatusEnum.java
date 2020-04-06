package com.volunteer.uapply.utils.enums;

/**
 * 面试状态枚举类
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/19 10:17
 */
public enum InterviewStatusEnum {

    NO_INTERVIEW(0, "未面试"),
    INTERVIEWED(1, "已经面试"),
    INTERVIEW_ELIMINATE(2, "淘汰"),
    INTERVIEW_PASS(3, "通过");

    /**
     * 面试状态id
     */
    private Integer InterviewStatusId;

    /**
     * 面试状态
     */
    private String InterviewStatus;

    InterviewStatusEnum(Integer interviewStatusId, String interviewStatus) {
        InterviewStatusId = interviewStatusId;
        InterviewStatus = interviewStatus;
    }

    public Integer getInterviewStatusId() {
        return InterviewStatusId;
    }

    public void setInterviewStatusId(Integer interviewStatusId) {
        InterviewStatusId = interviewStatusId;
    }

    public String getInterviewStatus() {
        return InterviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        InterviewStatus = interviewStatus;
    }
}
