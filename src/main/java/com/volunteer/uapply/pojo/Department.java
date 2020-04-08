package com.volunteer.uapply.pojo;

import lombok.Data;

/**
 * 部门类
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/5 10:07
 */
@Data
public class Department {

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;


    /**
     * 组织id
     */
    private Integer organizationId;

    /**
     * 组织名称
     */
    private String organizationName;
    /**
     * 部门PC登录账号
     */
    private String departmentAccount;

    /**
     * 部门PC端登录密码
     */
    private String departmentPwd;

    /**
     * 面试轮次
     */
    private Integer interviewRounds;

    /**
     * 面试评价参数1的名称
     */
    private String param1Name;
    /**
     * 面试评价参数2
     */
    private String param2Name;
    /**
     * 面试评价参数3
     */
    private String param3Name;
    /**
     * 面试评价参数4
     */
    private String param4Name;
    /**
     * 面试评价参数5
     */
    private String param5Name;
}
