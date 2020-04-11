package com.volunteer.uapply.controller;

import com.volunteer.uapply.annotation.DepartmentLogin;
import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.sevice.InterviewStatusService;
import com.volunteer.uapply.utils.enums.InterviewStatusEnum;
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
     * @param userId
     * @return
     */
    @DepartmentLogin
    @GetMapping("/getstatus")
    public UniversalResponseBody<List<InterviewStatus>> getInterviewStatus(Integer userId) {
        return interviewStatusService.getInterviewStatus(userId);
    }

    /**
     * 一面通过
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @PostMapping("/first/pass")
    public UniversalResponseBody FirstInterviewPass(Integer[] userId, String departmentName, Integer organizationId) {
        return interviewStatusService.changeFirstInterviewStatus(userId, organizationId, departmentName, InterviewStatusEnum.INTERVIEW_PASS.getInterviewStatus());
    }

    /**
     * 一面淘汰
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @PostMapping("/first/eliminate")
    public UniversalResponseBody FirstInterviewEliminate(Integer[] userId, String departmentName, Integer organizationId) {
        return interviewStatusService.changeFirstInterviewStatus(userId, organizationId, departmentName, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
    }

    /**
     * 二面签到
     *
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     * @apiNote 将复试状态设置为已面试
     */
    @DepartmentLogin
    @PostMapping("/retest/check")
    public UniversalResponseBody retestCheck(Integer userId, String departmentName, Integer organizationId) {
        //把userId转换为数组类型
        Integer[] array = new Integer[1];
        array[0] = userId;
        return interviewStatusService.changeRetestStatus(array, organizationId, departmentName, InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
    }

    /**
     * 二面淘汰
     *
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @PostMapping("/retest/eliminate")
    public UniversalResponseBody retestEliminate(Integer[] userId, String departmentName, Integer organizationId) {
        return interviewStatusService.changeRetestStatus(userId, organizationId, departmentName, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
    }

    /**
     * 二面未面试
     * @param userId
     * @param departmentName
     * @param organizationId
     * @return
     * @apiNote 将成员的二面状态设置未面试
     */
    @DepartmentLogin
    @PostMapping("/retest/cancel")
    public UniversalResponseBody cancelRetest(Integer[] userId, String departmentName, Integer organizationId) {
        return interviewStatusService.changeRetestStatus(userId, organizationId, departmentName, InterviewStatusEnum.NO_INTERVIEW.getInterviewStatus());
    }


    /**
     * 录取为部员
     * @param userId
     * @param departmentName
     * @param departmentId
     * @param organizationId
     * @return
     */
    @DepartmentLogin
    @PostMapping("/enroll")
    public UniversalResponseBody enrollMember(Integer[] userId, String departmentName, Integer departmentId, Integer organizationId) {
        return interviewStatusService.enrollMembers(userId, departmentId, departmentName, organizationId);
    }


    /**
     * 取消录取为部员
     * @param userId
     * @param departmentName
     * @param departmentId
     * @param organizationId
     * @return
     * @apiNote 将此成员从部员名单移动至淘汰名单
     */
    @DepartmentLogin
    @PostMapping("/cancel/enroll")
    public UniversalResponseBody cancelEnroll(Integer[] userId, String departmentName, Integer departmentId, Integer organizationId) {
        return interviewStatusService.cancelEnroll(userId, departmentName, departmentId, organizationId);
    }
}
