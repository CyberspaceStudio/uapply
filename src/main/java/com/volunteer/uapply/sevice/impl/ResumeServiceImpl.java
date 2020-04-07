package com.volunteer.uapply.sevice.impl;

import com.volunteer.uapply.mapper.InterviewStatusMapper;
import com.volunteer.uapply.mapper.ResumeMapper;
import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.sevice.ResumeService;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 10:37
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Resource
    private ResumeMapper resumeMapper;
    @Resource
    private InterviewStatusMapper interviewStatusMapper;

    @Override
    public UniversalResponseBody apply(Resume resume, String firstChoice, String secondChoice) {
        //简历插入失败
        if (resumeMapper.InsertResume(resume) < 0) {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        //修改面试状态
        InterviewStatus interviewStatus = new InterviewStatus(resume.getUserId(), resume.getOrganizationId(), resume.getOrganizationName(), firstChoice, secondChoice);
        if (interviewStatusMapper.InsertInterviewStatus(interviewStatus) < 0) {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }
}
