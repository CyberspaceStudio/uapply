package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.InterviewScorePO;

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
}
