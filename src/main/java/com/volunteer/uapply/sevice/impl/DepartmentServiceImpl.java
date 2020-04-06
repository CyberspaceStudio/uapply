package com.volunteer.uapply.sevice.impl;

import com.volunteer.uapply.mapper.DepartmentMapper;
import com.volunteer.uapply.pojo.Department;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.sevice.DepartmentService;
import com.volunteer.uapply.utils.Tokenutil;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 17:26
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private Tokenutil tokenutil;

    @Override
    public UniversalResponseBody<TokenPO<Department>> departmentLogin(String departmentAccount, String departmentPwd) {
        Department department = departmentMapper.getDepartmentByAccount(departmentAccount);
        if (department == null) {
            return new UniversalResponseBody(ResponseResultEnum.USER_LOGIN_ERROR.getCode(), ResponseResultEnum.USER_LOGIN_ERROR.getMsg());
        } else if (department.getDepartmentAccount().equals(departmentAccount) && department.getDepartmentPwd().equals(departmentPwd)) {
            TokenPO<Department> tokenPO = new TokenPO<Department>();
            tokenPO.setT(department);
            tokenPO.setToken(tokenutil.tokenByDepartmentId(department.getDepartmentId()));
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
}
