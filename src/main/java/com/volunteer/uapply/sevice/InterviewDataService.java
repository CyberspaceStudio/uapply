package com.volunteer.uapply.sevice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.pojo.InterviewData;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/9 17:56
 */
public interface InterviewDataService {


    /**
     * 查询未一面
     *
     * @param organizationId
     * @param departmentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<Resume>> unFirstInterview(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize);

    /**
     * 获取整个组织下面的部门的面试数据
     *
     * @param organizationId
     * @return
     */
    UniversalResponseBody<List<InterviewData>> organizationCounts(Integer organizationId);

    /**
     * 获取某部门的面试数据
     *
     * @param departmentName
     * @return
     */
    UniversalResponseBody<InterviewData> departmentInterDate(String departmentName);


    /**
     * 已经一面
     *
     * @param organizationId
     * @param departmentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<InterviewScorePO>> firstInterviewEd(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize);


    /**
     * 未二面
     *
     * @param organizationId
     * @param departmentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<Resume>> unRetest(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize);

    /**
     * 已经二面
     *
     * @param organizationId
     * @param departmentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<InterviewScorePO>> Retested(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize);

    /**
     * 获取整个部门全部的淘汰名单
     *
     * @param organizationId
     * @param departmentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<InterviewScorePO>> getEliminationList(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize);

    /**
     * 获取整个组织的淘汰名单
     *
     * @param organizationId
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<InterviewScorePO>> getOrganizationEliminationList(Integer organizationId, Integer pageNum, Integer pageSize);

}
