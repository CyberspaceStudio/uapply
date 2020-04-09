package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.InterviewData;

import java.util.List;

/**
 * 面试数据
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/9 16:27
 */
public interface InterviewDataMapper {

    /**
     * 初始化部门面试数据
     *
     * @param departmentId
     * @param departmentName
     * @param organizationId
     * @return
     */
    int initDepartInterviewData(Integer departmentId, String departmentName, Integer organizationId);


    /**
     * 增加报名该部门的男人数以及报名人数
     *
     * @param departmentName
     * @return
     */
    int plusManCounts(String departmentName);


    /**
     * 增加报名该部门的女人数以及报名人数
     *
     * @param departmentName
     * @return
     */
    int plusWomanCounts(String departmentName);

    /**
     * 增加跨部人数
     *
     * @param departmentName
     * @return
     */
    int plusCrossCounts(String departmentName);


    /**
     * 增加已经面试人数
     *
     * @param departmentName
     * @return
     */
    int plusInterviewCounts(String departmentName);

    /**
     * 获取部门的面试数据
     *
     * @param departmentName
     * @return
     */
    InterviewData getDepartInterviewData(String departmentName);


    /**
     * 获取组织的面试数据
     *
     * @param organizationId
     * @return
     */
    List<InterviewData> getOrganInterviewData(Integer organizationId);
}
