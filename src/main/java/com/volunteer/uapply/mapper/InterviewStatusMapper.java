package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.InterviewStatus;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 11:08
 */
public interface InterviewStatusMapper {

    /**
     * 初始化面试状态
     *
     * @param interviewStatus
     * @return
     */
    int InsertInterviewStatus(InterviewStatus interviewStatus);
}
