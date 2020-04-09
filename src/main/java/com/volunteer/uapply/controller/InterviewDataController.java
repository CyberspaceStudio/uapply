package com.volunteer.uapply.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.mapper.InterviewDataMapper;
import com.volunteer.uapply.pojo.InterviewData;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 面试数据
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/5 18:40
 */
@Slf4j
@RestController
@RequestMapping("/interview/data")
public class InterviewDataController {


    @Resource
    private InterviewDataMapper interviewDataMapper;

    /**
     * 未一面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @return
     */
    @GetMapping("/unfisrt")
    public UniversalResponseBody<PageInfo<Resume>> UnFirstInterview(String departmentName, Integer pageNum, Integer pageSize) {
        return null;
    }

    /**
     * 已经一面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @return
     */
    @GetMapping("/fisrted")
    public UniversalResponseBody<PageInfo<Resume>> FirstedInterview(String departmentName, Integer pageNum, Integer pageSize) {
        return null;
    }

    /**
     * 未二面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @return
     */
    @GetMapping("/unsecond")
    public UniversalResponseBody<PageInfo<Resume>> UnSecondInterview(String departmentName, Integer pageNum, Integer pageSize) {
        return null;
    }

    /**
     * 已经二面
     *
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @return
     */
    @GetMapping("/seconded")
    public UniversalResponseBody<PageInfo<Resume>> SecondedInterview(String departmentName, Integer pageNum, Integer pageSize) {
        return null;
    }

    /**
     * 部门淘汰名单
     * @param pageSize
     * @param pageNum
     * @param departmentName
     * @return
     */
    @GetMapping("/eliminationList")
    public UniversalResponseBody<PageInfo<Resume>> getEliminationList(String departmentName, Integer pageNum, Integer pageSize) {
        return null;
    }

    /**
     * 获取部门面试数据
     *
     * @param departmentName
     * @return
     */
    @GetMapping("/applyCount")
    public UniversalResponseBody<InterviewData> ApplyAndSexCount(String departmentName) {
        InterviewData interviewData = interviewDataMapper.getDepartInterviewData(departmentName);
        if (interviewData == null) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewData);
    }


    /**
     * 整个组织的报名人数
     *
     * @param organizationId
     * @return
     */
    @GetMapping("/applyCounts")
    public UniversalResponseBody<List<InterviewData>> AllCounts(Integer organizationId) {
        List<InterviewData> interviewDataList = interviewDataMapper.getOrganInterviewData(organizationId);
        if (interviewDataList.isEmpty()) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        return new UniversalResponseBody(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), interviewDataList);
    }

}
