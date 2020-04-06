package com.volunteer.uapply.utils.enums;

/**
 * 权限Id枚举类
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/2 13:42
 */
public enum PermissionIdEnum {

    SUP_MANGER(0, "超级管理员"),
    MINISTER(1, "部长"),
    DEPUTY_MINISTER(2, "副部长"),
    STAFF(3, "部员"),
    NORMAL_USER(4, "普通用户");

    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 权限
     */
    private String permissionName;

    PermissionIdEnum(Integer permissionId, String permissionName) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
