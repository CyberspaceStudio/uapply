package com.volunteer.uapply.sevice;

import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

import java.util.List;

/**
 * 面试状态
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 14:16
 */
public interface InterviewStatusService {


    /**
     * 根据用户id获取用户面试状态
     *
     * @param userId
     * @return
     */
    UniversalResponseBody<List<InterviewStatus>> getInterviewStatus(Integer userId);


    /**
     * 录取部员
     *
     * @param userId
     * @param departmentId
     * @param departmentName
     * @param organizationId
     * @return
     */
    UniversalResponseBody enrollMembers(Integer[] userId, Integer departmentId, String departmentName, Integer organizationId);
}
