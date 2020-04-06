package com.volunteer.uapply.controller;

import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.annotation.MinisterLogin;
import com.volunteer.uapply.annotation.PassToken;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.pojo.*;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.pojo.dto.EnrollMembersDTO;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 部门
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/5 10:12
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    /**
     * 部门PC端登录
     *
     * @param departmentAccount
     * @param departmentPwd
     * @return
     */
    @PassToken
    @PostMapping("/login")
    public UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd) {
        return null;
    }

    /**
     * 部门激活
     *
     * @param department
     * @return
     */
    @PostMapping("/register")
    public UniversalResponseBody<TokenPO<Department>> departmentRegister(Department department) {
        return null;
    }

    /**
     * 更改部门信息
     *
     * @param department
     * @return
     */
    @PostMapping("/detail")
    public UniversalResponseBody<Department> changeDepartmentDetail(Department department) {
        return null;
    }

    /**
     * 添加面试官
     *
     * @param departmentName
     * @param departmentId
     * @param userId
     * @return
     */
    @PostMapping("/interviewer")
    public UniversalResponseBody insertInterviewer(Integer departmentId, String departmentName, Integer[] userId) {
        return null;
    }


    /**
     * 分页查询部员数据
     *
     * @param departmentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @UserLogin
    @PostMapping("/members")
    public UniversalResponseBody<PageInfo<User>> FindMembers(Integer departmentId, Integer pageNum, Integer pageSize) {
        return null;
    }


    /**
     * * 导出该部门所有部员详细信息
     *
     * @param departmentId
     * @param response
     */
    @GetMapping("/export/{departmentId}")
    public void exportMessages(@PathVariable("departmentId") Integer departmentId, HttpServletResponse response) {
        return;
    }
}
