package com.volunteer.uapply.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.mapper.InterviewDataMapper;
import com.volunteer.uapply.pojo.InterviewData;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.sevice.InterviewDataService;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
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
     * 未一面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @GetMapping("/unfirst")
    public UniversalResponseBody<PageInfo<Resume>> UnFirstInterview(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.unFirstInterview(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 已经一面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @GetMapping("/firsted")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> FirstedInterview(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.firstInterviewEd(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 未二面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @GetMapping("/unretest")
    public UniversalResponseBody<PageInfo<Resume>> unRetest(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.unRetest(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 已经二面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @GetMapping("/retested")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> retested(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.Retested(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 部门淘汰名单
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @param organizationId
     * @return
     */
    @GetMapping("/eliminationList")
    public UniversalResponseBody<PageInfo<InterviewScorePO>> getEliminationList(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        return interviewDataService.getEliminationList(organizationId, departmentName, pageNum, pageSize);
    }

    /**
     * 获取部门面试数据
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/department")
    public UniversalResponseBody<InterviewData> departmentInterDate(String departmentName) {
        return interviewDataService.departmentInterDate(departmentName);
    }


    /**
     * 整个组织的面试数据
     *
     * @param organizationId
     * @return
     * @apiNote 获取整个组织下面所有部门的面试数据
     */
    @GetMapping("/organization")
    public UniversalResponseBody<List<InterviewData>> organizationCounts(Integer organizationId) {
        return interviewDataService.organizationCounts(organizationId);
    }

}
