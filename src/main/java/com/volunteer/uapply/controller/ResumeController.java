package com.volunteer.uapply.controller;

import com.volunteer.uapply.annotation.DepartmentLogin;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.sevice.ResumeService;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 简历
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/8 11:29
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Resource
    @Qualifier("resumeServiceImpl")
    private ResumeService resumeService;

    /**
     * 报名
     * @param resume
     * @param firstChoice
     * @param secondChoice
     * @return
     */
    @UserLogin
    @PostMapping("/apply")
    public UniversalResponseBody apply(Resume resume, String firstChoice, String secondChoice) {
        return resumeService.apply(resume, firstChoice, secondChoice);
    }


    /**
     * 查看简历
     *
     * @param organizationId
     * @param userTel
     * @return
     */
    @UserLogin
    @GetMapping("/view")
    public UniversalResponseBody<Resume> viewResume(Integer organizationId, String userTel) {
        return resumeService.viewResume(organizationId, userTel);
    }

    /**
     * 简历打分
     * @param interviewScorePO
     * @return
     */
    @UserLogin
    @PostMapping("/score")
    public UniversalResponseBody resumeScore(InterviewScorePO interviewScorePO) {
        return resumeService.scoreResume(interviewScorePO);
    }

    /**
     * 获取简历评价详情
     *
     * @param userId
     * @param organizationId
     * @return
     * @apiNote 获取用户在某组织的全部简历评价
     */
    @DepartmentLogin
    @GetMapping("/scores")
    public UniversalResponseBody<List<InterviewScorePO>> getAllResumeScores(Integer userId, Integer organizationId) {
        return resumeService.getAllResumeScores(userId, organizationId);
    }
}
