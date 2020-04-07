package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.Department;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 17:27
 */
public interface DepartmentMapper {

    /**
     * 通过部门账号查找部门
     *
     * @param departmentAccount
     * @return
     */
    Department getDepartmentByAccount(String departmentAccount);

    /**
     * 插入部门
     *
     * @param department
     * @return
     */
    int insertDepartment(Department department);


    /**
     * 插入部门面试详细信息
     *
     * @param department
     * @return
     */
    int insertDepartmentInterviewDetail(Department department);

}
