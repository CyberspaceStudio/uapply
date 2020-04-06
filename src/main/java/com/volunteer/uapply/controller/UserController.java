package com.volunteer.uapply.controller;

import com.volunteer.uapply.annotation.PassToken;
import com.volunteer.uapply.annotation.UserLogin;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.pojo.Resume;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.web.bind.annotation.*;


/**
 * 普通用户
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/8 11:20
 */
@RestController
@RequestMapping("/login")
public class UserController {

    /**
     * 用户微信小程序登录
     *
     * @param code
     * @return
     */
    @PassToken
    @PostMapping("/wx")
    public UniversalResponseBody<TokenPO<User>> userWxLogin(String code) {
        return null;
    }

}
