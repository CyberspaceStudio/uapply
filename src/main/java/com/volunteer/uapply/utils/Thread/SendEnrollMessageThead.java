package com.volunteer.uapply.utils.Thread;

import com.volunteer.uapply.utils.MessageUtil;
import lombok.Data;

import javax.annotation.Resource;

/**
 * 发送短信的线程
 *
 * @program: uapply
 * @author: GuoShuSong
 * @create: 2020-06-05 14:49
 **/
@Data
public class SendEnrollMessageThead implements Runnable {

    @Resource
    private MessageUtil messageUtil;

    private String userTel;
    private String userName;
    private String organizationName;
    private String departmentName;
    /**
     * 群号
     */
    private String secret;


    public SendEnrollMessageThead(String userTel, String userName, String organizationName, String departmentName, String secret) {
        this.userTel = userTel;
        this.userName = userName;
        this.organizationName = organizationName;
        this.departmentName = departmentName;
        this.secret = secret;
    }

    @Override
    public void run() {
        try {
            messageUtil.sendEnrollInterviewMessage(userTel, userName, organizationName, departmentName, secret);
            System.out.println("当前线程Id" + Thread.currentThread() + userName + "\t" + userTel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
