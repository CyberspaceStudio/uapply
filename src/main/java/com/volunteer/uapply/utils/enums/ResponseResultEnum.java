package com.volunteer.uapply.utils.enums;

/**
 * 返回结果数据字典
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/1/31 11:12
 */
public enum ResponseResultEnum {
    FAILED(0, "失败"),
    SUCCESS(1, "成功"),
    /**
     * 参数错误 1001-1999
     */
    PARAM_IS_INVALID(1002, "参数无效"),
    PARAM_IS_BLANK(1003, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1004, "参数类型错误"),
    CODE_IS_INVALID(1005, "code无效"),
    TAG_IS_EMPTY(1006, "没有与此标签相关的模板"),
    /**
     * 用户错误 2000-2999
     */
    USER_LOGIN_SECCESS(2000, "登录成功"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_NO_TOKEN(2001, "用户token为空"),
    USER_HAVE_EXIST(2000, "用户登录成功,非首次登录"),
    USER_NO_PERMISSION(2003, "用户权限不足"),
    /**
     * 面试状态 3000-3999
     */
    NOT_APPLY(3000, "还未报名"),
    SUCCEED_APPLY(3001, "已经报名成功"),
    FINISH_FIRST_INTERVIEW(3002, "完成一面"),
    FINISH_SECOND_INTERVIEW(3003, "完成二面");
    /**
     * 返回结果代码
     */
    private Integer code;
    /**
     * 返回具体信息
     */
    private String msg;

    ResponseResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
