package com.volunteer.uapply.controller;

import com.aliyuncs.exceptions.ClientException;
import com.volunteer.uapply.pojo.info.AliyunEnrollParam;
import com.volunteer.uapply.pojo.info.AliyunFisrtInterviewParam;
import com.volunteer.uapply.pojo.info.AliyunSecondInterviewParam;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/8 11:05
 */
@RestController
@RequestMapping("/message")
public class MessagesController {


    /**
     * 发送一面短信
     *
     * @param aliyunInterviewParamInfo
     * @return
     * @throws ClientException
     */
    @PostMapping("/interview/first")
    public UniversalResponseBody SendFirstInterviewMessage(AliyunFisrtInterviewParam aliyunInterviewParamInfo) throws ClientException {
        return null;
    }

    /**
     * 发送二面短信
     *
     * @param aliyunSecondMsgParamInfo
     * @return
     */
    @PostMapping("/interview/second")
    public UniversalResponseBody SendSecondInterviewMessage(AliyunSecondInterviewParam aliyunSecondMsgParamInfo) throws ClientException {
        return null;
    }

    /**
     * 发送录取短信
     *
     * @param aliyunEnrollParamInfo
     * @return
     * @throws ClientException
     */
    @PostMapping("/enroll")
    public UniversalResponseBody SendFirstInterviewMessage(AliyunEnrollParam aliyunEnrollParamInfo) throws ClientException {
        return null;
    }
}
