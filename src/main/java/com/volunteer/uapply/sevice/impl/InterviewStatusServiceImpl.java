package com.volunteer.uapply.sevice.impl;

import com.volunteer.uapply.mapper.InterviewStatusMapper;
import com.volunteer.uapply.pojo.InterviewStatus;
import com.volunteer.uapply.sevice.InterviewStatusService;
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

    @Override
    public UniversalResponseBody<List<InterviewStatus>> getInterviewStatus(Integer userId) {
        List<InterviewStatus> interviewStatusList = interviewStatusMapper.getInterviewStatusByUserId(userId);
        if (interviewStatusList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        } else {
            return new UniversalResponseBody<List<InterviewStatus>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewStatusList);
        }
    }
}
