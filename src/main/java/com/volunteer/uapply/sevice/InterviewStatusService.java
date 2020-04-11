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

    /**
     * 取消录取该部员
     *
     * @param userId
     * @param departmentName
     * @param departmentId
     * @param organizationId
     * @return
     */
    UniversalResponseBody cancelEnroll(Integer[] userId, String departmentName, Integer departmentId, Integer organizationId);


    /**
     * 更改一面状态
     *
     * @param userId
     * @param organizationId
     * @param departmentName
     * @param status
     * @return
     */
    UniversalResponseBody changeFirstInterviewStatus(Integer[] userId, Integer organizationId, String departmentName, String status);


    /**
     * 更改复试状态
     *
     * @param userId
     * @param organizationId
     * @param departmentName
     * @param status
     * @return
     */
    UniversalResponseBody changeRetestStatus(Integer[] userId, Integer organizationId, String departmentName, String status);
}
