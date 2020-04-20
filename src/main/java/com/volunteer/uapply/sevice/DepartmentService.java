package com.volunteer.uapply.sevice;

import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.pojo.Department;
import com.volunteer.uapply.pojo.DepartmentMember;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 17:23
 */
public interface DepartmentService {

    /**
     * 部门PC端登录
     *
     * @param departmentAccount
     * @param departmentPwd
     * @return
     */
    UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd);


    /**
     * 部门激活
     *
     * @param department
     * @return
     */
    UniversalResponseBody<Department> departmentRegister(Department department);

    /**
     * 获取组织下的全部部门
     *
     * @param organizationId
     * @return
     */
    UniversalResponseBody<List<Department>> organizationDepartments(Integer organizationId);


    /**
     * 获取该部门的信息
     *
     * @param departmentId
     * @return
     */
    UniversalResponseBody<Department> getDepartmentDetail(Integer departmentId);

    /**
     * 部门面试详细数据
     *
     * @param department
     * @return
     */
    UniversalResponseBody<Department> departmentInterviewDetail(Department department);


    /**
     * 增加面试官
     *
     * @param departmentId
     * @param userId
     * @return
     */
    UniversalResponseBody insertInterviewer(Integer departmentId, Integer[] userId);


    /**
     * 获取部门全部面试官
     *
     * @param departmentId
     * @return
     */
    UniversalResponseBody<List<Integer>> getAllInterviewers(Integer departmentId);


    /**
     * 分页获取部门成员
     *
     * @param departmentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    UniversalResponseBody<PageInfo<User>> getMembers(Integer departmentId, Integer pageNum, Integer pageSize);

    /**
     * 导出部员信息到excel
     *
     * @param departmentId
     * @param response
     * @return
     */
    void getAllMembersToExcel(Integer departmentId, HttpServletResponse response);
}
