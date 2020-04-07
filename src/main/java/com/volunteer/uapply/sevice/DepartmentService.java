package com.volunteer.uapply.sevice;

import com.volunteer.uapply.pojo.Department;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 17:23
 */
public interface DepartmentService {

    /**
     * 部门PC端登录
     *
     * @param departmentAccount
     * @param departmentPwd
     * @return
     */
    UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd);


    /**
     * 部门激活
     *
     * @param department
     * @return
     */
    UniversalResponseBody<Department> departmentRegister(Department department);

    /**
     * 部门面试详细数据
     *
     * @param department
     * @return
     */
    UniversalResponseBody<Department> departmentInterviewDetail(Department department);


    /**
     * 增加面试官
     *
     * @param departmentId
     * @param departmentName
     * @param userId
     * @return
     */
    UniversalResponseBody insertInterviewer(Integer departmentId, String departmentName, Integer[] userId);
}
