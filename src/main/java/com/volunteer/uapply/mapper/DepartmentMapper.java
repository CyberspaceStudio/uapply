package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.Department;

import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 17:27
 */
public interface DepartmentMapper {

    /**
     * 通过部门账号查找部门
     * @param departmentAccount
     * @return
     */
    Department getDepartmentByAccount(String departmentAccount);

    /**
     * 根据部门名称查询部门
     *
     * @param departmentName
     * @return
     */
    Department getDepartmentByName(String departmentName);

    /**
     * 根据部门名称和部门Id查找部门
     *
     * @param departmentName
     * @param organizationId
     * @return
     */
    Department getDepartmentByNameAndId(String departmentName, Integer organizationId);


    /**
     * 根据部门id查询部门
     *
     * @param departmentId
     * @return
     */
    Department getDepartmentById(Integer departmentId);

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


    /**
     * 根据部门Id获取部门详细信息
     *
     * @param departmentId
     * @return
     */
    Department getDepartmentByDepartmentId(Integer departmentId);


    /**
     * 根据组织Id获取组织下的全部部门
     *
     * @param organizationId
     * @return
     */
    List<Department> getDepartmentsByOrganizationId(Integer organizationId);
}
