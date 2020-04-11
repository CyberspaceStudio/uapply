package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.InterviewStatus;

import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 11:08
 */
public interface InterviewStatusMapper {

    /**
     * 初始化面试状态
     * @param interviewStatus
     * @return
     */
    int insertInterviewStatus(InterviewStatus interviewStatus);

    /**
     * 根据userId查找用户全部面试状态
     *
     * @param userId
     * @return
     */
    List<InterviewStatus> getInterviewStatusByUserId(Integer userId);


    /**
     * 获取用户相应部门的面试状态
     *
     * @param userId
     * @param organizationId
     * @return
     */
    InterviewStatus getInterviewStatusById(Integer userId, Integer organizationId);

    /**
     * 更改复试状态
     *
     * @param userId
     * @param organizationId
     * @param retestStatus
     * @param departmentName
     * @return
     */
    int updateRetestStatus(Integer userId, Integer organizationId, String departmentName, String retestStatus);

    /**
     * 修改一面状态
     *
     * @param userId
     * @param organizationId
     * @param firstStatus
     * @return
     */
    int updateFirstInterviewStatus(Integer userId, Integer organizationId, String firstStatus);


    /**
     * 修改二面状态
     *
     * @param userId
     * @param organizationId
     * @param secondStatus
     * @return
     */
    int updateSecondInterviewStatus(Integer userId, Integer organizationId, String secondStatus);


    /**
     * 获取整个部门中对应一面面试状态的成员Id
     *
     * @param organizationId
     * @param departmentName
     * @param status
     * @return
     */
    List<Integer> getUserIdsByFirstStatus(Integer organizationId, String departmentName, String status);

    /**
     * 获取整个部门中二面对应状态的成员Id
     *
     * @param organizationId
     * @param departmentName
     * @param status
     * @return
     */
    List<Integer> getUserIdByRetestStatus(Integer organizationId, String departmentName, String status);


}
