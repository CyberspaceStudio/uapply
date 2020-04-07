package com.volunteer.uapply.mapper;

import com.volunteer.uapply.pojo.User;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/6 14:42
 */
public interface UserMessageMapper {

    /**
     * 根据openid查找用户
     *
     * @param openid
     * @return
     */
    User getUserByOpenid(String openid);

    /**
     * 根据userId查找用户
     *
     * @param userId
     * @return
     */
    User getUserByUserId(Integer userId);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param userId
     * @param userName
     * @param userTel
     * @param userQQNum
     * @return
     */
    int updateUserMessage(Integer userId, String userSex, String userName, String userTel, String userQQNum);
}
