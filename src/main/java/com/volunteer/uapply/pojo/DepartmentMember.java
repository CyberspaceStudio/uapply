package com.volunteer.uapply.pojo;

import lombok.Data;

/**
 * 部门成员
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/5 14:19
 */
@Data
public class DepartmentMember {

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 用户id
     *
     * @ignore
     */
    private Integer userId;

    /**
     * 权限id
     */
    private Integer permissionId;
}
