package com.volunteer.uapply.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.mapper.InterviewDataMapper;
import com.volunteer.uapply.mapper.InterviewScoreMapper;
import com.volunteer.uapply.mapper.InterviewStatusMapper;
import com.volunteer.uapply.mapper.ResumeMapper;
import com.volunteer.uapply.pojo.InterviewData;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.sevice.InterviewDataService;
import com.volunteer.uapply.utils.enums.InterviewStatusEnum;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/9 18:00
 */
@Service
@Slf4j
public class InterviewDataServiceImpl implements InterviewDataService {

    @Resource
    private InterviewDataMapper interviewDataMapper;
    @Resource
    private InterviewStatusMapper interviewStatusMapper;
    @Resource
    private ResumeMapper resumeMapper;
    @Resource
    private InterviewScoreMapper interviewScoreMapper;

    @Override
    public UniversalResponseBody<PageInfo<Resume>> unFirstInterview(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        //先从面试状态数据库中根据未面试以及部门名称和组织ID查找出userId
        List<Integer> userIdList = interviewStatusMapper.getUserIdsByFirstStatus(organizationId, departmentName, InterviewStatusEnum.NO_INTERVIEW.getInterviewStatus());
        if (userIdList == null || userIdList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Resume> pageInfo = new PageInfo<Resume>(resumeMapper.getResumesByUserId(userIdList, organizationId));
        return new UniversalResponseBody<PageInfo<Resume>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
    }


    @Override
    public UniversalResponseBody<PageInfo<InterviewScorePO>> firstInterviewEd(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        //先从面试状态数据库中根据未面试以及部门名称和组织ID查找出userId
        List<Integer> userIdList = interviewStatusMapper.getUserIdsByFirstStatus(organizationId, departmentName, InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
        if (userIdList == null || userIdList.isEmpty()) {
            //如果结果为空
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<InterviewScorePO> pageInfo = new PageInfo<InterviewScorePO>(interviewScoreMapper.getInterviewScoresByUserId(userIdList, organizationId));
        return new UniversalResponseBody<PageInfo<InterviewScorePO>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
    }

    @Override
    public UniversalResponseBody<List<InterviewData>> organizationCounts(Integer organizationId) {
        //获取整个组织的面试数据
        List<InterviewData> interviewDataList = interviewDataMapper.getOrganInterviewData(organizationId);
        if (interviewDataList == null || interviewDataList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewDataList);
    }

    @Override
    public UniversalResponseBody<InterviewData> departmentInterDate(String departmentName) {
        InterviewData interviewData = interviewDataMapper.getDepartInterviewData(departmentName);
        if (interviewData == null) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewData);
    }

    @Override
    public UniversalResponseBody<PageInfo<Resume>> unRetest(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        //List<Integer> userIdList = interviewStatusMapper.getUserIdByRetestStatus(organizationId, departmentName, InterviewStatusEnum.NO_INTERVIEW.getInterviewStatus());
        //如果无返回结果
        List<Integer> userIdList = interviewStatusMapper.getUnSecondInterview(organizationId, departmentName);

        if (userIdList == null || userIdList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Resume> pageInfo = new PageInfo<Resume>(resumeMapper.getResumesByUserId(userIdList, organizationId));
        return new UniversalResponseBody<PageInfo<Resume>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
    }

    @Override
    public UniversalResponseBody<PageInfo<InterviewScorePO>> Retested(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        List<Integer> userIdList = interviewStatusMapper.getUserIdByRetestStatus(organizationId, departmentName, InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
        //如果无返回结果
        if (userIdList == null || userIdList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<InterviewScorePO> pageInfo = new PageInfo<InterviewScorePO>(interviewScoreMapper.getInterviewScoresByUserId(userIdList, organizationId));
        return new UniversalResponseBody<PageInfo<InterviewScorePO>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
    }

    @Override
    public UniversalResponseBody<PageInfo<InterviewScorePO>> getEliminationList(Integer organizationId, String departmentName, Integer pageNum, Integer pageSize) {
        //查询复试的淘汰名单
        List<Integer> userIdList = interviewStatusMapper.getUserIdByRetestStatus(organizationId, departmentName, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
        //将两个集合拼接
        userIdList.addAll(interviewStatusMapper.getUserIdsByFirstStatus(organizationId, departmentName, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus()));
        if (userIdList == null || userIdList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<InterviewScorePO> pageInfo = new PageInfo<InterviewScorePO>(interviewScoreMapper.getInterviewScoresByUserId(userIdList, organizationId));
        return new UniversalResponseBody<PageInfo<InterviewScorePO>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
    }


    @Override
    public UniversalResponseBody<PageInfo<InterviewScorePO>> getOrganizationEliminationList(Integer organizationId, Integer pageNum, Integer pageSize) {
        List<Integer> userIdList = interviewStatusMapper.getUserIdByOrganizationId(organizationId, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
        if (userIdList == null || userIdList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<InterviewScorePO> pageInfo = new PageInfo<InterviewScorePO>(interviewScoreMapper.getInterviewScoresByUserId(userIdList, organizationId));
        return new UniversalResponseBody<PageInfo<InterviewScorePO>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);

    }
}
