package com.volunteer.uapply.sevice.impl;

import com.volunteer.uapply.mapper.*;
import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.sevice.ResumeService;
import com.volunteer.uapply.utils.enums.InterviewStatusEnum;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 10:37
 */
@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    @Resource
    private ResumeMapper resumeMapper;
    @Resource
    private InterviewStatusMapper interviewStatusMapper;
    @Resource
    private UserMessageMapper userMessageMapper;
    @Resource
    private InterviewScoreMapper interviewScoreMapper;
    @Resource
    private InterviewDataMapper interviewDataMapper;


    private final Lock lock = new ReentrantLock();

    private static final String man = "男";
    private static final String woman = "女";
    private static final String emptyString = "";

    @Override
    public UniversalResponseBody apply(Resume resume, String firstChoice, String secondChoice) {
        //如果用户的二志愿为空
        if (secondChoice == null) {
            secondChoice = emptyString;
        } else {
            interviewDataMapper.plusCrossCounts(firstChoice);
            interviewDataMapper.plusCrossCounts(secondChoice);
        }
        Resume resume1 = resumeMapper.getResumeByUserTel(resume.getOrganizationId(), resume.getUserTel());
        if (resume1 != null) {
            return new UniversalResponseBody(ResponseResultEnum.USER_HAVE_APPLY.getCode(), ResponseResultEnum.USER_HAVE_APPLY.getMsg());
        }

        //简历插入失败
        if (resumeMapper.InsertResume(resume) < 1) {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        //向面试状态数据库中插入一条新数据
        InterviewStatus interviewStatus = new InterviewStatus(resume.getUserId(), resume.getOrganizationId(), resume.getOrganizationName(), firstChoice, secondChoice);
        if (interviewStatusMapper.insertInterviewStatus(interviewStatus) < 0) {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        //同时把此人的基本信息插入到user_message数据库中
        User user = userMessageMapper.getUserByUserId(resume.getUserId());
        //如果用户的名称为空，说明数据库中没有此用户的基本信息
        if (user.getUserName() == null) {
            if (userMessageMapper.updateUserMessage(resume.getUserId(), resume.getUserSex(), resume.getUserStuNum(), resume.getUserName(), resume.getUserTel(), resume.getUserQQNum(), resume.getUserCollege(), resume.getUserProfession()) < 0) {
                return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
            }
        }
        //根据性别，修改相应部门的面试数据
        if (resume.getUserSex().equals(man)) {
            interviewDataMapper.plusManCounts(firstChoice);
            interviewDataMapper.plusManCounts(secondChoice);
        } else if (resume.getUserSex().equals(woman)) {
            interviewDataMapper.plusWomanCounts(firstChoice);
            interviewDataMapper.plusWomanCounts(secondChoice);
        }

        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody<Resume> viewResume(Integer organizationId, String userTel) {
        //获取用户简历
        Resume resume = resumeMapper.getResumeByUserTel(organizationId, userTel);
        //用户简历为空
        if (resume == null) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody<Resume>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), resume);
    }

    @Override
    public UniversalResponseBody scoreResume(InterviewScorePO interviewScorePO) {
        if (interviewScoreMapper.insertInterviewScore(interviewScorePO) < 0) {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        //增加部门面试数据中的已经面试人数
        interviewDataMapper.plusInterviewCounts(interviewScorePO.getDepartmentName());
       /*
        try{
            if (lock.tryLock(60, TimeUnit.MINUTES))
            {
                try{
                    interviewDataMapper.plusInterviewCounts(interviewScorePO.getDepartmentName());
                }finally {
                    lock.unlock();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        InterviewStatus interviewStatus = interviewStatusMapper.getInterviewStatusById(interviewScorePO.getUserId(), interviewScorePO.getOrganizationId());
        if (interviewStatus == null) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        if (interviewStatus.getFirstChoice().equals(interviewScorePO.getDepartmentName())) {
            interviewStatusMapper.updateFirstInterviewStatus(interviewScorePO.getUserId(), interviewScorePO.getOrganizationId(), InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
        } else if (interviewStatus.getSecondChoice().equals(interviewScorePO.getDepartmentName())) {
            interviewStatusMapper.updateSecondInterviewStatus(interviewScorePO.getUserId(), interviewScorePO.getOrganizationId(), InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
        } else {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody<List<InterviewScorePO>> getAllResumeScores(Integer userId, Integer organizationId) {
        List<InterviewScorePO> interviewStatusList = interviewScoreMapper.getAllScoreById(userId, organizationId);
        if (interviewStatusList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody<List<InterviewScorePO>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewStatusList);
    }
}
