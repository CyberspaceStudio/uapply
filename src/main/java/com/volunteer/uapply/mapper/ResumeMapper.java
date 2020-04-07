package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.Resume;

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
}
