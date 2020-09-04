package com.volunteer.uapply.mapper;

import com.github.pagehelper.Page;
import com.volunteer.uapply.pojo.InterviewScorePO;
import com.volunteer.uapply.pojo.Resume;

import java.util.List;

/**
 * 面试评价数据库
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/8 20:47
 */
public interface InterviewScoreMapper {

    /**
     * 面试评价
     *
     * @param interviewScorePO
     * @return
     */
    int insertInterviewScore(InterviewScorePO interviewScorePO);


    /**
     * 查询此人在该组织下的所有面试评价
     *
     * @param userId
     * @param organizationId
     * @return
     */
    List<InterviewScorePO> getAllScoreById(Integer userId, Integer organizationId);

    /**
     * 查询id在该组织下的所有面试简历
     *
     * @param userId
     * @param organizationId
     * @return
     */
    Page<InterviewScorePO> getInterviewScoresByUserId(List<Integer> userId, Integer organizationId);

    /**
     * 查询id在部门下的面试简历
     *
     * @param userId
     * @param organizationId
     * @param departmentName
     * @return
     */
    Page<InterviewScorePO> getDepartmentInterviewScore(List<Integer> userId, Integer organizationId, String departmentName);
}
