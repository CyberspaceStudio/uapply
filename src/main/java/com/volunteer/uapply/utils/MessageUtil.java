package com.volunteer.uapply.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.volunteer.uapply.mapper.UserMessageMapper;
import com.volunteer.uapply.pojo.User;
import com.volunteer.uapply.pojo.info.AliyunEnrollParam;
import com.volunteer.uapply.pojo.info.AliyunResponseInfo;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 短信发送工具类
 *
 * @program: uapply
 * @author: GuoShuSong
 * @create: 2020-06-05 14:55
 **/
@Component
@Slf4j
public class MessageUtil {

    @Resource
    private UserMessageMapper userMessageMapper;

    @Value("${aliyun.firstInterviewTemplateCode}")
    private String firstInterviewTemplateCode;

    @Value("${aliyun.SignName}")
    private String SignName;

    @Value("${aliyun.secondInterviewTemplateCode}")
    private String secondInterviewTemplateCode;

    @Value("${aliyun.enrollTemplateCode}")
    private String enrollTemplateCode;

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    /**
     * 此处在方法里面写死，因为阿里云规范不让出现面试、群号
     */
    private static final String activity = "面试";

    private static final String Domain = "dysmsapi.aliyuncs.com";

    private static final String Version = "2017-05-25";

    private static final String Action = "SendSms";


    private static final String SuccessCode = "OK";

    public boolean sendEnrollInterviewMessage(String userTel, String userName, String organizationName, String departmentName, String secret) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(Domain);
        request.setVersion(Version);
        request.setAction(Action);
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", userTel);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", enrollTemplateCode);
        request.putQueryParameter("TemplateParam", "{\"name\":\"" + userName + "\","
                + "\"organizationName\":\"" + organizationName + "\","
                + "\"department\":\"" + departmentName + "\","
                + "\"secret\":\"" + "群号" + secret + "\","
                + "}");
        CommonResponse commonResponse = client.getCommonResponse(request);
        AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
        if (aliyunResponseInfo.getCode().equals(SuccessCode)) {
        } else {
            log.error(aliyunResponseInfo.toString() + "录取短信发送失败 电话:" + userTel + " 姓名: " + userName + "  部门:" + departmentName);
            return false;
        }
        return true;
    }

}
