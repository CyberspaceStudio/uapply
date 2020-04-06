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
    private String parma1Name;
    /**
     * 面试评价参数2
     */
    private String parma2Name;
    /**
     * 面试评价参数3
     */
    private String parma3Name;
    /**
     * 面试评价参数4
     */
    private String parma4Name;
    /**
     * 面试评价参数5
     */
    private String parma5Name;
}
