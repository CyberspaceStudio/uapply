package com.volunteer.uapply.controller;

import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.annotation.DepartmentLogin;
import com.volunteer.uapply.annotation.PassToken;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.pojo.*;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.sevice.DepartmentService;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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


    @Resource
    @Qualifier("departmentServiceImpl")
    private DepartmentService departmentService;


    /**
     * 部门PC端登录
     * @param departmentAccount
     * @param departmentPwd
     * @return
     */
    @PassToken
    @PostMapping("/login")
    public UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd) {
        return departmentService.departmentLogin(departmentAccount, departmentPwd);
    }

    /**
     * 部门激活
     * @param department
     * @return
     */
    @PassToken
    @PostMapping("/register")
    public UniversalResponseBody<Department> departmentRegister(Department department) {
        return departmentService.departmentRegister(department);
    }


    /**
     * 获取组织下的全部部门
     *
     * @param organizationId
     * @return
     */
    @GetMapping("/organization")
    public UniversalResponseBody<List<Department>> organizationDepartments(Integer organizationId) {
        return departmentService.organizationDepartments(organizationId);
    }


    /**
     * 获取该部门的信息
     *
     * @param departmentId
     * @return
     */
    @GetMapping("/detail")
    public UniversalResponseBody<Department> getDepartmentDetail(Integer departmentId) {
        return departmentService.getDepartmentDetail(departmentId);
    }

    /**
     * 增加部门面试信息
     *
     * @param department
     * @return
     */
    @DepartmentLogin
    @PostMapping("/detail")
    public UniversalResponseBody<Department> changeDepartmentDetail(Department department) {
        return departmentService.departmentInterviewDetail(department);
    }

    /**
     * 添加面试官
     * @param departmentId
     * @param userId
     * @return
     */
    @DepartmentLogin
    @PostMapping("/interviewer")
    public UniversalResponseBody insertInterviewer(Integer departmentId, Integer[] userId) {
        return departmentService.insertInterviewer(departmentId, userId);
    }


    /**
     * 获取部门面试官Id
     * @param departmentId
     * @return
     */
    @DepartmentLogin
    @GetMapping("/interviewer")
    public UniversalResponseBody<List<Integer>> getInterviewers(Integer departmentId) {
        return departmentService.getAllInterviewers(departmentId);
    }

    /**
     * 分页查询部员数据
     * @param departmentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @DepartmentLogin
    @GetMapping("/members")
    public UniversalResponseBody<PageInfo<User>> FindMembers(Integer departmentId, Integer pageNum, Integer pageSize) {
        return departmentService.getMembers(departmentId, pageNum, pageSize);
    }


    /**
     * 导出该部门所有部员详细信息为Excel形式
     * @param departmentId
     * @param response
     */
    @DepartmentLogin
    @GetMapping("/export")
    public void exportMessages(Integer departmentId, HttpServletResponse response) {
        departmentService.getAllMembersToExcel(departmentId, response);
    }



}
