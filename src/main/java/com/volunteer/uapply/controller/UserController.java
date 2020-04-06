package com.volunteer.uapply.controller;

import com.volunteer.uapply.annotation.PassToken;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.pojo.DepartmentMember;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.sevice.UserService;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 普通用户
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/8 11:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;


    /**
     * 用户微信小程序登录
     *
     * @param code
     * @return
     */
    @PassToken
    @PostMapping("/login/wx")
    public UniversalResponseBody<TokenPO<User>> userWxLogin(String code) {
        return userService.userWxLogin(code);
    }

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    @GetMapping("/profession")
    public UniversalResponseBody<List<DepartmentMember>> getUserProfession(Integer userId) {
        return userService.getUserPermission(userId);
    }
}
