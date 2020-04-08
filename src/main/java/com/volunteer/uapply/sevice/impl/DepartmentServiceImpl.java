package com.volunteer.uapply.sevice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.mapper.DepartmentMapper;
import com.volunteer.uapply.mapper.DepartmentMemberMapper;
import com.volunteer.uapply.mapper.UserMessageMapper;
import com.volunteer.uapply.pojo.Department;
import com.volunteer.uapply.pojo.DepartmentMember;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.sevice.DepartmentService;
import com.volunteer.uapply.utils.ExcelUtil;
import com.volunteer.uapply.utils.Object2Map;
import com.volunteer.uapply.utils.Tokenutil;
import com.volunteer.uapply.utils.enums.AuthorityIdEnum;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 17:26
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private Tokenutil tokenutil;
    @Resource
    private DepartmentMemberMapper departmentMemberMapper;
    @Resource
    private UserMessageMapper userMessageMapper;

    @Override
    public UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd) {
        Department department = departmentMapper.getDepartmentByAccount(departmentAccount);
        //数据库返回结果为空
        if (department == null) {
            return new UniversalResponseBody(ResponseResultEnum.USER_LOGIN_ERROR.getCode(), ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
        } else if (department.getDepartmentAccount().equals(departmentAccount) && department.getDepartmentPwd().equals(departmentPwd)) {
            TokenPO<Department> tokenPO = new TokenPO<Department>(department, tokenutil.tokenByDepartmentId(department.getDepartmentId()));
            return new UniversalResponseBody<TokenPO<Department>>(ResponseResultEnum.USER_LOGIN_SUCCESS.getCode(), ResponseResultEnum.USER_LOGIN_SUCCESS.getMsg(), tokenPO);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.USER_LOGIN_ERROR.getCode(), ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
        }
    }


    @Override
    public UniversalResponseBody<Department> departmentRegister(Department department) {
        if (departmentMapper.insertDepartment(department) > 0) {
            return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), department);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
    }


    @Override
    public UniversalResponseBody<Department> departmentInterviewDetail(Department department) {
        if (departmentMapper.insertDepartmentInterviewDetail(department) > 0) {
            return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), department);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
    }

    @Override
    public UniversalResponseBody insertInterviewer(Integer departmentId, Integer[] userId) {
        Department department = departmentMapper.getDepartmentByDepartmentId(departmentId);
        for (Integer temp :
                userId) {
            List<DepartmentMember> departmentMemberList = departmentMemberMapper.getUserAuthority(temp);
            Integer flag = 0;
            for (DepartmentMember departmentMember :
                    departmentMemberList) {
                //若部门成员中已有此人
                if (departmentMember.getDepartmentId().equals(departmentId)) {
                    //更新用户权限
                    departmentMemberMapper.updateUserAuthority(departmentId, AuthorityIdEnum.MINISTER.getAuthorityId(), temp);
                    flag = 1;
                    //部门中此人只能有一个，所以更新完后，跳出循环
                    break;
                }
            }
            //部门中没有此人
            if (flag.equals(0)) {
                departmentMemberMapper.insertDepartmentMember(departmentId, department.getDepartmentName(), temp, AuthorityIdEnum.MINISTER.getAuthorityId());
            }
        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody<PageInfo<User>> getMembers(Integer departmentId, Integer pageNum, Integer pageSize) {
        List<DepartmentMember> departmentMemberList = departmentMemberMapper.getDepartmentMember(departmentId, AuthorityIdEnum.STAFF.getAuthorityId());
        Integer[] userId = new Integer[departmentMemberList.size()];
        int i = 0;
        for (DepartmentMember departmentMember :
                departmentMemberList) {
            userId[i++] = departmentMember.getUserId();
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(userMessageMapper.getUsersByUserId(userId));
        //如果查询结果不为空
        if (pageInfo.getTotal() != 0) {
            return new UniversalResponseBody<PageInfo<User>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
    }


    @Override
    public void getAllMembersToExcel(Integer departmentId, HttpServletResponse response) {
        List<DepartmentMember> departmentMemberList = departmentMemberMapper.getDepartmentMember(departmentId, AuthorityIdEnum.STAFF.getAuthorityId());
        Department department = departmentMapper.getDepartmentByDepartmentId(departmentId);
        Integer[] userId = new Integer[departmentMemberList.size()];
        int i = 0;
        for (DepartmentMember departmentMember :
                departmentMemberList) {
            userId[i++] = departmentMember.getUserId();
        }
        List<Map<String, Object>> memberList = Object2Map.object2MapList(userMessageMapper.getUsersByUserId(userId));
        ExcelUtil.templateExportExcel("/a-uapply" + "/MemberMessageTemp.xls", memberList, department.getDepartmentName() + "部员详细信息.xls", response);
        return;
    }
}

