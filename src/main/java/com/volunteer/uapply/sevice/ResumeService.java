package com.volunteer.uapply.sevice;

import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

import java.util.List;

/**
 * 简历
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 10:37
 */
public interface ResumeService {

    /**
     * 用户报名
     *
     * @param resume
     * @param firstChoice
     * @param secondChoice
     * @return
     */
    UniversalResponseBody apply(Resume resume, String firstChoice, String secondChoice);


    /**
     * 获取用户简历
     *
     * @param organizationId
     * @param userTel
     * @return
     */
    UniversalResponseBody<Resume> viewResume(Integer organizationId, String userTel);


    /**
     * 简历打分
     *
     * @param interviewScorePO
     * @return
     */
    UniversalResponseBody scoreResume(InterviewScorePO interviewScorePO);

    /**
     * 获取此用户该组织下的全部简历评价
     *
     * @param userId
     * @param organizationId
     * @return
     */
    UniversalResponseBody<List<InterviewScorePO>> getAllResumeScores(Integer userId, Integer organizationId);
}
