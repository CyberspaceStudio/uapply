package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.DepartmentMember;

import java.util.List;

/**
 * 部门成员数据库
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 15:54
 */
public interface DepartmentMemberMapper {

    /**
     * 获取用户在所有部门的权限
     *
     * @param userId
     * @return
     */
    List<DepartmentMember> getUserPermission(Integer userId);


    /**
     * 批量插入部门成员
     *
     * @param departmentId   部门ID
     * @param departmentName 部门名称
     * @param userId         用户ID的集合
     * @param authorityId    权限ID
     * @return
     */
    int insertDepartmentMember(Integer departmentId, String departmentName, Integer userId, Integer authorityId);
}
