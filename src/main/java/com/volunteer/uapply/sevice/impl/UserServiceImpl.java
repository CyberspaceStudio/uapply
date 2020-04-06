package com.volunteer.uapply.sevice.impl;

import com.volunteer.uapply.mapper.DepartmentMemberMapper;
import com.volunteer.uapply.mapper.UserMessageMapper;
import com.volunteer.uapply.pojo.DepartmentMember;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.TokenPO;
import com.volunteer.uapply.pojo.info.WxResponseInfo;
import com.volunteer.uapply.sevice.UserService;
import com.volunteer.uapply.utils.Tokenutil;
import com.volunteer.uapply.utils.WeChatUtil;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 14:38
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Resource
    private WeChatUtil weChatUtil;
    @Resource
    private Tokenutil tokenutil;
    @Resource
    private UserMessageMapper userMapper;
    @Resource
    private DepartmentMemberMapper departmentMemberMapper;

    @Override
    public UniversalResponseBody<TokenPO<User>> userWxLogin(String code) {
        WxResponseInfo wxResponseInfo = weChatUtil.getWeChatResponseBody(code);
        //Code无效
        if (wxResponseInfo.getErrcode() != null) {
            //将微信返回错误代码及结果记录到日志中
            log.error("微信登录出错" + code + wxResponseInfo.getErrcode() + "\t" + wxResponseInfo.getErrmsg());
            return new UniversalResponseBody(ResponseResultEnum.CODE_IS_INVALID.getCode(), ResponseResultEnum.CODE_IS_INVALID.getMsg());
        }
        String token = null;
        TokenPO<User> tokenPO = null;
        User user = userMapper.getUserByOpenid(wxResponseInfo.getOpenid());
        //数据库中已经存在该用户
        if (user != null) {
            token = tokenutil.TokenByUserId(user.getUserId());
            tokenPO = new TokenPO(user, token);
            return new UniversalResponseBody<TokenPO<User>>(ResponseResultEnum.USER_HAVE_EXIST.getCode(), ResponseResultEnum.USER_HAVE_EXIST.getMsg(), tokenPO);
        } else {
            //插入用户
            user = new User(wxResponseInfo.getOpenid());
            userMapper.insertUser(wxResponseInfo.getOpenid());

            //根据userId生成token
            token = tokenutil.TokenByUserId(user.getUserId());
            tokenPO = new TokenPO(user, token);
            return new UniversalResponseBody<TokenPO<User>>(ResponseResultEnum.USER_LOGIN_SUCCESS.getCode(), ResponseResultEnum.USER_LOGIN_SUCCESS.getMsg(), tokenPO);
        }
    }


    @Override
    public UniversalResponseBody<List<DepartmentMember>> getUserPermission(Integer userId) {
        //查部门成员数据库中
        List<DepartmentMember> departmentMemberList = departmentMemberMapper.getUserPermission(userId);
        if (departmentMemberList == null) {
            return new UniversalResponseBody<List<DepartmentMember>>(ResponseResultEnum.NORMAL_USER.getCode(), ResponseResultEnum.NORMAL_USER.getMsg());
        } else {
            return new UniversalResponseBody<List<DepartmentMember>>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg(), departmentMemberList);
        }
    }
}
