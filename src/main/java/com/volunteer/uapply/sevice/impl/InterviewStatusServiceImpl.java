package com.volunteer.uapply.sevice.impl;

import com.volunteer.uapply.mapper.DepartmentMemberMapper;
import com.volunteer.uapply.mapper.InterviewStatusMapper;
import com.volunteer.uapply.pojo.Department;
import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.sevice.InterviewStatusService;
import com.volunteer.uapply.utils.enums.AuthorityIdEnum;
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
 * @date 2020/4/7 14:18
 */
@Service
@Slf4j
public class InterviewStatusServiceImpl implements InterviewStatusService {

    @Resource
    private InterviewStatusMapper interviewStatusMapper;
    @Resource
    private DepartmentMemberMapper departmentMemberMapper;

    @Override
    public UniversalResponseBody<List<InterviewStatus>> getInterviewStatus(Integer userId) {
        List<InterviewStatus> interviewStatusList = interviewStatusMapper.getInterviewStatusByUserId(userId);
        if (interviewStatusList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        } else {
            return new UniversalResponseBody<List<InterviewStatus>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewStatusList);
        }
    }

    @Override
    public UniversalResponseBody enrollMembers(Integer[] userId, Integer departmentId, String departmentName, Integer organizationId) {
        for (Integer temp :
                userId) {
            //将复试状态改为通过
            interviewStatusMapper.updateRetestStatus(temp, organizationId, departmentName, InterviewStatusEnum.INTERVIEW_PASS.getInterviewStatus());
            //将此成员插入部门成员表中,其次在录取为部员之前，部门成员数据库中不应该存在该成员
            departmentMemberMapper.insertDepartmentMember(departmentId, departmentName, temp, AuthorityIdEnum.STAFF.getAuthorityId());
        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody retestCheck(Integer userId, Integer organizationId, String departmentName) {
        InterviewStatus interviewStatus = interviewStatusMapper.getInterviewStatusById(userId, organizationId);
        //一志愿为该部门，且一面通过
        if (interviewStatus.getFirstChoice().equals(departmentName)) {
            interviewStatusMapper.updateRetestStatus(userId, organizationId, departmentName, InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
        } else if (interviewStatus.getSecondChoice().equals(departmentName)) {
            //二志愿为该部门，且一面通过
            interviewStatusMapper.updateRetestStatus(userId, organizationId, departmentName, InterviewStatusEnum.INTERVIEWED.getInterviewStatus());
        } else {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody FirstInterviewPass(Integer[] userId, Integer organizationId, String departmentName) {
        for (Integer temp :
                userId) {
            InterviewStatus interviewStatus = interviewStatusMapper.getInterviewStatusById(temp, organizationId);
            //一志愿为该部门
            if (interviewStatus.getFirstChoice().equals(departmentName)) {
                interviewStatusMapper.updateFirstInterviewStatus(temp, organizationId, InterviewStatusEnum.INTERVIEW_PASS.getInterviewStatus());
            } else if (interviewStatus.getSecondChoice().equals(departmentName)) {
                //二志愿为该部门
                interviewStatusMapper.updateSecondInterviewStatus(temp, organizationId, InterviewStatusEnum.INTERVIEW_PASS.getInterviewStatus());
            } else {
                return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
            }
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody FirstInterviewEliminate(Integer[] userId, Integer organizationId, String departmentName) {
        for (Integer temp :
                userId) {
            InterviewStatus interviewStatus = interviewStatusMapper.getInterviewStatusById(temp, organizationId);
            //一志愿为该部门
            if (interviewStatus.getFirstChoice().equals(departmentName)) {
                interviewStatusMapper.updateFirstInterviewStatus(temp, organizationId, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
            } else if (interviewStatus.getSecondChoice().equals(departmentName)) {
                //二志愿为该部门
                interviewStatusMapper.updateSecondInterviewStatus(temp, organizationId, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
            } else {
                return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
            }
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody RetestEliminate(Integer[] userId, Integer organizationId, String departmentName) {
        for (Integer temp :
                userId) {
            InterviewStatus interviewStatus = interviewStatusMapper.getInterviewStatusById(temp, organizationId);
            //一志愿为该部门
            if (interviewStatus.getRetestChoice().equals(departmentName)) {
                interviewStatusMapper.updateRetestStatus(temp, organizationId, departmentName, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
            } else {
                return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
            }

        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody cancelEnroll(Integer[] userId, String departmentName, Integer departmentId, Integer organizationId) {

        for (Integer temp :
                userId) {
            //将复试状态改为淘汰
            interviewStatusMapper.updateRetestStatus(temp, organizationId, departmentName, InterviewStatusEnum.INTERVIEW_ELIMINATE.getInterviewStatus());
            //删除该成员在department_member中的数据
            departmentMemberMapper.deleteDepartmentMember(departmentId, temp);
        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }


    @Override
    public UniversalResponseBody cancelRetest(Integer[] userId, Integer organizationId, String departmentName) {
        for (Integer temp :
                userId) {
            InterviewStatus interviewStatus = interviewStatusMapper.getInterviewStatusById(temp, organizationId);
            //一志愿为该部门
            if (interviewStatus.getRetestChoice().equals(departmentName)) {
                interviewStatusMapper.updateRetestStatus(temp, organizationId, departmentName, InterviewStatusEnum.NO_INTERVIEW.getInterviewStatus());
            } else {
                return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
            }

        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }
}
