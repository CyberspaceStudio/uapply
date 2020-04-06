package com.volunteer.uapply.controller;


import com.volunteer.uapply.pojo.InterviewData;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 未一面
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/unfisrt")
    public UniversalResponseBody<List<Resume>> UnFirstInterview(String departmentName) {
        return null;
    }

    /**
     * 已经一面
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/fisrted")
    public UniversalResponseBody<List<Resume>> FirstedInterview(String departmentName) {
        return null;
    }

    /**
     * 未二面
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/unsecond")
    public UniversalResponseBody<List<Resume>> UnSecondInterview(String departmentName) {
        return null;
    }

    /**
     * 已经二面
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/seconded")
    public UniversalResponseBody<List<Resume>> SecondedInterview(String departmentName) {
        return null;
    }

    /**
     * 部门淘汰名单
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/eliminationList")
    public UniversalResponseBody<List<Resume>> getEliminationList(String departmentName) {
        return null;
    }

    /**
     * 部门报名人数以及男女人数
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/applyCount")
    public UniversalResponseBody<InterviewData> ApplyAndSexCount(String departmentName) {
        return null;
    }


    /**
     * 整个组织的报名人数
     *
     * @param organizationId
     * @param departmentName
     * @return
     */
    @GetMapping("/applyCounts")
    public UniversalResponseBody<List<InterviewData>> AllCounts(Integer organizationId, String departmentName) {
        return null;
    }

    /**
     * 整个组织一面以及没有一面的人数
     *
     * @param organizationId
     * @param departmentName
     * @return
     */
    @GetMapping("/firstInterviewData")
    public UniversalResponseBody<List<InterviewData>> CountDetail(Integer organizationId, String departmentName) {
        return null;
    }
}
