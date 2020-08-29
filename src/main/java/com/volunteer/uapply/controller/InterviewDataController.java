package com.volunteer.uapply.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.annotation.DepartmentLogin;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.mapper.InterviewDataMapper;
import com.volunteer.uapply.pojo.InterviewData;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.sevice.InterviewDataService;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 面试数据
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/5 18:40
 */
@Slf4j
@RestController
@RequestMapping("/interview/data")
public class InterviewDataController {

    @Resource
    @Qualifier("interviewDataServiceImpl")
    private InterviewDataService interviewDataService;

    /**
     * 部门未一面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @GetMapping("/unfirst")
    public UniversalResponseBody<PageInfo<Resume>> unFirstInterview(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.unFirstInterview(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 部门已经一面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @GetMapping("/firsted")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> firstInterviewed(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.firstInterviewEd(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 部门未二面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    //@DepartmentLogin
    @GetMapping("/unretest")
    public UniversalResponseBody<PageInfo<Resume>> unRetest(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.unRetest(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 部门已经二面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @GetMapping("/retested")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> retested(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.Retested(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 部门淘汰名单
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @GetMapping("/eliminationList")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> getEliminationList(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.getEliminationList(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 组织淘汰名单
     *
     * @param organizationId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @DepartmentLogin
    @GetMapping("/organization/eliminationList")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> getOrganizationEliminationList(Integer organizationId, Integer pageNum, Integer pageSize) {
        return interviewDataService.getOrganizationEliminationList(organizationId, pageNum, pageSize);
    }

    /**
     * 获取部门面试数据
     *
     * @param departmentName
     * @return
     */
    @UserLogin
    @GetMapping("/department")
    public UniversalResponseBody<InterviewData> departmentInterDate(String departmentName) {
        return interviewDataService.departmentInterDate(departmentName);
    }


    /**
     * 整个组织的面试数据
     * @param organizationId
     * @return
     * @apiNote 获取整个组织下面所有部门的面试数据
     */
    @UserLogin
    @GetMapping("/organization")
    public UniversalResponseBody<List<InterviewData>> organizationCounts(Integer organizationId) {
        return interviewDataService.organizationCounts(organizationId);
    }

}
