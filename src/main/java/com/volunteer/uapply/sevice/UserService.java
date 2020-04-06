package com.volunteer.uapply.sevice;

import com.volunteer.uapply.pojo.DepartmentMember;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 14:36
 */
public interface UserService {
    /**
     * 用户微信登录
     *
     * @param code
     * @return
     */
    UniversalResponseBody<TokenPO<User>> userWxLogin(String code);


    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    UniversalResponseBody<List<DepartmentMember>> getUserPermission(Integer userId);
}
