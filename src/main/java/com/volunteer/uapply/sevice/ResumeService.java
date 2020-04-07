package com.volunteer.uapply.sevice;

import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

/**
 * 简历
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/7 10:37
 */
public interface ResumeService {

    /**
     * 用户报名
     *
     * @param resume
     * @param firstChoice
     * @param secondChoice
     * @return
     */
    UniversalResponseBody apply(Resume resume, String firstChoice, String secondChoice);
}
