package com.volunteer.uapply.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


/**
 * 用户对象
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/1 13:26
 */
@Data
public class User {

    /**
     * 用户唯一id
     *
     * @required
     */
    @Excel(name = "用户Id", orderNum = "0")
    private Integer userId;

    /**
     * 微信小程序中用户的id
     *
     * @ignore
     */
    private String openid;

    /**
     * 用户姓名
     */
    @Excel(name = "姓名", orderNum = "1")
    private String userName;

    /**
     * 用户学号
     */
    @Excel(name = "学号", orderNum = "2")
    private String userStuNum;

    /**
     * 用户性别
     */
    @Excel(name = "姓名", orderNum = "3")
    private String userSex;

    /**
     * 电话
     */
    @Excel(name = "电话", orderNum = "4")
    private String userTel;

    /**
     * QQ
     */
    @Excel(name = "QQ", orderNum = "5")
    private String userQQNum;

    /**
     * 学院
     */
    @Excel(name = "学院", orderNum = "6")
    private String userCollege;

    /**
     * 专业
     */
    @Excel(name = "专业", orderNum = "7")
    private String userProfession;

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String openid) {
        this.openid = openid;
    }

    public User() {
    }
}
