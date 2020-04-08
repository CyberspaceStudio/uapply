package com.volunteer.uapply.controller;

import com.aliyuncs.exceptions.ClientException;
import com.volunteer.uapply.pojo.info.AliyunEnrollParam;
import com.volunteer.uapply.pojo.info.AliyunFisrtInterviewParam;
import com.volunteer.uapply.pojo.info.AliyunResponseInfo;
import com.volunteer.uapply.pojo.info.AliyunSecondInterviewParam;
import com.volunteer.uapply.sevice.MessageService;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    @Qualifier("messageServiceImpl")
    private MessageService messageService;

    /**
     * 发送一面短信
     *
     * @param aliyunInterviewParamInfo
     * @return
     * @throws ClientException
     * @apiNote 只有短信发送失败时，才会返回短信发送接口的返回结果，成功只返回code和msg
     */
    @PostMapping("/interview/first")
    public UniversalResponseBody SendFirstInterviewMessage(AliyunFisrtInterviewParam aliyunInterviewParamInfo) throws ClientException {
        return messageService.sendFirstInterviewMessage(aliyunInterviewParamInfo);
    }

    /**
     * 发送二面短信
     *
     * @param aliyunSecondMsgParamInfo
     * @return
     * @apiNote 只有短信发送失败时，才会返回短信发送接口的返回结果，成功只返回code和msg
     */
    @PostMapping("/interview/second")
    public UniversalResponseBody<AliyunResponseInfo> SendSecondInterviewMessage(AliyunSecondInterviewParam aliyunSecondMsgParamInfo) throws ClientException {
        return messageService.sendSecondInterviewMessage(aliyunSecondMsgParamInfo);
    }

    /**
     * 发送录取短信
     *@apiNote 只有短信发送失败时，才会返回短信发送接口的返回结果，成功只返回code和msg
     * @param aliyunEnrollParamInfo
     * @return
     * @throws ClientException
     */
    @PostMapping("/enroll")
    public UniversalResponseBody SendFirstInterviewMessage(AliyunEnrollParam aliyunEnrollParamInfo) throws ClientException {
        return messageService.sendEnrollInterviewMessage(aliyunEnrollParamInfo);
    }
}
