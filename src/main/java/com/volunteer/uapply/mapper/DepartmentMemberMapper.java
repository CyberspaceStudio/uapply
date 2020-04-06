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
}
