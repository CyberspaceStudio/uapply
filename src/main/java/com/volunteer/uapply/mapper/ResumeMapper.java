package com.volunteer.uapply.mapper;

import com.github.pagehelper.Page;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.pojo.User;

import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 10:40
 */
public interface ResumeMapper {

    /**
     * 插入简历
     *
     * @param resume
     * @return
     */
    int InsertResume(Resume resume);


    /**
     * 获取用户简历
     *
     * @param organizationId
     * @param userTel
     * @return
     */
    Resume getResumeByUserTel(Integer organizationId, String userTel);


    /**
     * 批量获取用户简历
     *
     * @param organizationId
     * @param userId
     * @return
     */
    Page<Resume> getResumesByUserId(List<Integer> userId, Integer organizationId);

}
