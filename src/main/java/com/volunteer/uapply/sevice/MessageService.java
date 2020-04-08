package com.volunteer.uapply.sevice;

import com.aliyuncs.exceptions.ClientException;
import com.volunteer.uapply.pojo.info.AliyunEnrollParam;
import com.volunteer.uapply.pojo.info.AliyunFisrtInterviewParam;
import com.volunteer.uapply.pojo.info.AliyunResponseInfo;
import com.volunteer.uapply.pojo.info.AliyunSecondInterviewParam;
import com.volunteer.uapply.utils.response.UniversalResponseBody;

/**
 * 短息发送
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/8 13:40
 */
public interface MessageService {

    /**
     * 发送一面短信
     *
     * @param aliyunFisrtInterviewParam
     * @return
     * @throws ClientException
     */
    UniversalResponseBody<AliyunResponseInfo> sendFirstInterviewMessage(AliyunFisrtInterviewParam aliyunFisrtInterviewParam) throws ClientException;

    /**
     * 发送二面短信
     *
     * @param aliyunSecondInterviewParam
     * @return
     * @throws ClientException
     */
    UniversalResponseBody<AliyunResponseInfo> sendSecondInterviewMessage(AliyunSecondInterviewParam aliyunSecondInterviewParam) throws ClientException;


    /**
     * 发送录取短信
     *
     * @param aliyunEnrollParam
     * @return
     */
    UniversalResponseBody<AliyunResponseInfo> sendEnrollInterviewMessage(AliyunEnrollParam aliyunEnrollParam) throws ClientException;
}
