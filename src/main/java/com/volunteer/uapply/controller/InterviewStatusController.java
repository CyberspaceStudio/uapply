package com.volunteer.uapply.controller;

import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.sevice.InterviewStatusService;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 面试状态
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/5 14:07
 */

@RestController
@RequestMapping("/interview/status")
@Slf4j
public class InterviewStatusController {

    @Resource
    @Qualifier("interviewStatusServiceImpl")
    private InterviewStatusService interviewStatusService;

    /**
     * 获取面试状态
     *
     * @param userId
     * @return
     */
    @GetMapping("/getstatus")
    public UniversalResponseBody<List<InterviewStatus>> getInterviewStatus(Integer userId) {
        return interviewStatusService.getInterviewStatus(userId);
    }

    /**
     * 一面通过
     *
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @PostMapping("/first/pass")
    public UniversalResponseBody FirstInterviewPass(Integer userId, String departmentName, Integer organizationId) {
        return null;
    }

    /**
     * 一面淘汰
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @PostMapping("/first/eliminate")
    public UniversalResponseBody FirstInterviewEliminate(Integer userId, String departmentName, Integer organizationId) {
        return interviewStatusService.retestCheck(userId, organizationId, departmentName);
    }

    /**
     * 二面签到
     *
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @PostMapping("/second/check")
    public UniversalResponseBody RetestCheck(Integer userId, String departmentName, Integer organizationId) {
        return interviewStatusService.retestCheck(userId, organizationId, departmentName);
    }

    /**
     * 二面淘汰
     *
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @PostMapping("/retest/eliminate")
    public UniversalResponseBody RetestEliminate(Integer userId, String departmentName, Integer organizationId) {
        return null;
    }

    /**
     * 录取为部员
     * @param userId
     * @param departmentName
     * @param departmentId
     * @param organizationId
     * @return
     */
    @PostMapping("/enroll")
    public UniversalResponseBody EnrollMember(Integer[] userId, String departmentName, Integer departmentId, Integer organizationId) {
        return interviewStatusService.enrollMembers(userId, departmentId, departmentName, organizationId);
    }
}
