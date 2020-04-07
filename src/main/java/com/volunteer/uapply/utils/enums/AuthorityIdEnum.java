package com.volunteer.uapply.utils.enums;

/**
 * 权限Id枚举类
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/2 13:42
 */
public enum AuthorityIdEnum {

    SUP_MANGER(0, "超级管理员"),
    MINISTER(1, "部长"),
    STAFF(2, "部员"),
    NORMAL_USER(3, "普通用户");

    /**
     * 权限id
     */
    private Integer authorityId;

    /**
     * 权限
     */
    private String authorityName;

    AuthorityIdEnum(Integer authorityId, String authorityName) {
        this.authorityId = authorityId;
        this.authorityName = authorityName;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
