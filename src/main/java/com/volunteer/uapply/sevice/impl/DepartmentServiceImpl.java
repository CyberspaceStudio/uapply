package com.volunteer.uapply.sevice.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volunteer.uapply.mapper.DepartmentMapper;
import com.volunteer.uapply.mapper.DepartmentMemberMapper;
import com.volunteer.uapply.mapper.InterviewDataMapper;
import com.volunteer.uapply.mapper.UserMessageMapper;
import com.volunteer.uapply.pojo.Department;
import com.volunteer.uapply.pojo.DepartmentMember;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.sevice.DepartmentService;
import com.volunteer.uapply.utils.ExcelUtil;
import com.volunteer.uapply.utils.Object2Map;
import com.volunteer.uapply.utils.RedisUtil;
import com.volunteer.uapply.utils.Tokenutil;
import com.volunteer.uapply.utils.enums.AuthorityIdEnum;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
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
    @Resource
    private InterviewDataMapper interviewDataMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd) {
        Department department = departmentMapper.getDepartmentByAccount(departmentAccount);
        //数据库返回结果为空
        if (department == null) {
            return new UniversalResponseBody(ResponseResultEnum.USER_LOGIN_ERROR.getCode(), ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
        } else if (department.getDepartmentAccount().equals(departmentAccount) && department.getDepartmentPwd().equals(departmentPwd)) {
            //将查询结果放入Redis中,key-value: 部门Id-部门详细信息的JSON格式
            redisUtil.getAndSet(String.valueOf(department.getDepartmentId()), JSON.toJSONString(department));
            //生成token，并生成返回对象
            TokenPO<Department> tokenPO = new TokenPO<Department>(department, tokenutil.tokenByDepartmentId(department.getDepartmentId()));
            return new UniversalResponseBody<TokenPO<Department>>(ResponseResultEnum.USER_LOGIN_SUCCESS.getCode(), ResponseResultEnum.USER_LOGIN_SUCCESS.getMsg(), tokenPO);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.USER_LOGIN_ERROR.getCode(), ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
        }
    }


    @Override
    public UniversalResponseBody<List<Department>> organizationDepartments(Integer organizationId) {
        //获取组织下的全部部门
        List<Department> departmentList = departmentMapper.getDepartmentsByOrganizationId(organizationId);
        if (departmentList == null) {
            return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        } else {
            return new UniversalResponseBody<List<Department>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), departmentList);
        }
    }


    @Override
    public UniversalResponseBody<Department> departmentRegister(Department department) {
        //首先查看有没有相同账号的部门
        Department department1 = departmentMapper.getDepartmentByAccount(department.getDepartmentAccount());
        if (department1 != null) {
            return new UniversalResponseBody(ResponseResultEnum.DEPARTMENT_ACCOUNT_HAVE_EXIST.getCode(), ResponseResultEnum.DEPARTMENT_ACCOUNT_HAVE_EXIST.getMsg());
        }
        //检查有没有相同名称的部门
        Department department2 = departmentMapper.getDepartmentByNameAndId(department.getDepartmentName(), department.getOrganizationId());
        if (department2 != null) {
            return new UniversalResponseBody(ResponseResultEnum.DEPARTMENT_NAME_HAVE_EXIST.getCode(), ResponseResultEnum.DEPARTMENT_NAME_HAVE_EXIST.getMsg());
        }
        if (departmentMapper.insertDepartment(department) > 0) {
            //初始化部门面试及报名数据
            interviewDataMapper.initDepartInterviewData(department.getDepartmentId(), department.getDepartmentName(), department.getOrganizationId());
            return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), department);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
    }


    @Override
    public UniversalResponseBody<Department> departmentInterviewDetail(Department department) {
        //插入部门面试参数
        if (departmentMapper.insertDepartmentInterviewDetail(department) > 0) {
            redisUtil.setValue(String.valueOf(department.getDepartmentId()), JSON.toJSONString(department));
            return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), department);
        } else {
            return new UniversalResponseBody(ResponseResultEnum.FAILED.getCode(), ResponseResultEnum.FAILED.getMsg());
        }
    }


    @Override
    public UniversalResponseBody<Department> getDepartmentDetail(Integer departmentId) {
        //获取部门详细信息
        Department department = redisUtil.getValue(String.valueOf(departmentId));
        if (department == null) {
            department = departmentMapper.getDepartmentById(departmentId);
            if (department == null) {
                return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
            }
            redisUtil.setValue(String.valueOf(departmentId), JSON.toJSONString(department));
        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), department);
    }

    @Override
    public UniversalResponseBody insertInterviewer(Integer departmentId, Integer[] userId) {
        //获取部门详细信息
        Department department = redisUtil.getValue(String.valueOf(departmentId));
        if (department == null) {
            department = departmentMapper.getDepartmentById(departmentId);
            if (department == null) {
                return new UniversalResponseBody(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
            }
            redisUtil.setValue(String.valueOf(departmentId), JSON.toJSONString(department));
        }
        for (Integer temp :
                userId) {
            DepartmentMember departmentMember = departmentMemberMapper.getUserDepartmentAuthority(temp, departmentId);
            if (departmentMember == null) {
                departmentMemberMapper.insertDepartmentMember(departmentId, department.getDepartmentName(), temp, AuthorityIdEnum.MINISTER.getAuthorityId());
            } else {
                departmentMemberMapper.updateUserAuthority(departmentId, AuthorityIdEnum.MINISTER.getAuthorityId(), temp);
            }
        }
        return new UniversalResponseBody<Department>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody<PageInfo<User>> getMembers(Integer departmentId, Integer pageNum, Integer pageSize) {
        List<DepartmentMember> departmentMemberList = departmentMemberMapper.getDepartmentMember(departmentId, AuthorityIdEnum.STAFF.getAuthorityId());
        if (departmentMemberList == null) {
            return new UniversalResponseBody<PageInfo<User>>(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg());
        }
        Integer[] userId = new Integer[departmentMemberList.size()];
        int i = 0;
        for (DepartmentMember departmentMember :
                departmentMemberList) {
            userId[i++] = departmentMember.getUserId();
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(userMessageMapper.getUsersByUserId(userId));
        return new UniversalResponseBody<PageInfo<User>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), pageInfo);
    }


    @Override
    public void getAllMembersToExcel(Integer departmentId, HttpServletResponse response) {
        List<DepartmentMember> departmentMemberList = departmentMemberMapper.getDepartmentMember(departmentId, AuthorityIdEnum.STAFF.getAuthorityId());
        //获取部门详细信息
        Department department = redisUtil.getValue(String.valueOf(departmentId));
        if (department == null) {
            department = departmentMapper.getDepartmentById(departmentId);
            if (department == null) {
                return;
            }
            redisUtil.setValue(String.valueOf(departmentId), JSON.toJSONString(department));
        }
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


    @Override
    public UniversalResponseBody<List<Integer>> getAllInterviewers(Integer departmentId) {
        List<DepartmentMember> departmentMemberList = departmentMemberMapper.getDepartmentMember(departmentId, AuthorityIdEnum.MINISTER.getAuthorityId());
        List<Integer> userId = new LinkedList<Integer>();
        for (DepartmentMember departmentMember :
                departmentMemberList) {
            userId.add(departmentMember.getUserId());
        }
        return new UniversalResponseBody<List<Integer>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), userId);
    }
}

