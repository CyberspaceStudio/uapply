package com.volunteer.uapply.sevice.impl;

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
import com.volunteer.uapply.pojo.info.AliyunFisrtInterviewParam;
import com.volunteer.uapply.pojo.info.AliyunResponseInfo;
import com.volunteer.uapply.pojo.info.AliyunSecondInterviewParam;
import com.volunteer.uapply.sevice.MessageService;
import com.volunteer.uapply.utils.enums.ResponseResultEnum;
import com.volunteer.uapply.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/4/8 13:41
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

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
    private static String activity = "面试";

    private static String Domain = "dysmsapi.aliyuncs.com";

    private static String Version = "2017-05-25";

    private static String Action = "SendSms";


    @Override
    public UniversalResponseBody<AliyunResponseInfo> sendFirstInterviewMessage(AliyunFisrtInterviewParam aliyunFisrtInterviewParam) throws ClientException {
        List<User> userList = userMessageMapper.getUsersByUserId(aliyunFisrtInterviewParam.getUserId());
        for (User user :
                userList) {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain(Domain);
            request.setVersion(Version);
            request.setAction(Action);
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", user.getUserTel());
            request.putQueryParameter("SignName", SignName);
            request.putQueryParameter("TemplateCode", firstInterviewTemplateCode);
            request.putQueryParameter("TemplateParam", "{\"name\":\"" + user.getUserName() + "\","
                    + "\"timeSlot\":\"" + aliyunFisrtInterviewParam.getTimeSlot() + "\","
                    + "\"department\":\"" + aliyunFisrtInterviewParam.getDepartmentName() + "\","
                    + "\"telNo\":\"" + aliyunFisrtInterviewParam.getTelNo() + "\","
                    + "\"place\":\"" + aliyunFisrtInterviewParam.getPlace() + "\","
                    + "\"activity\":\"" + activity + "\","
                    + "}");
            CommonResponse commonResponse = client.getCommonResponse(request);
            log.info(commonResponse.getData());
            AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
            if (aliyunResponseInfo.getCode() == "OK") {
            } else {
                log.error(aliyunResponseInfo.toString() + "一面短信发送失败" + user.toString());
                return new UniversalResponseBody<AliyunResponseInfo>(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg(), aliyunResponseInfo);
            }
        }
        return new UniversalResponseBody<AliyunResponseInfo>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }


    @Override
    public UniversalResponseBody<AliyunResponseInfo> sendSecondInterviewMessage(AliyunSecondInterviewParam aliyunSecondInterviewParam) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        List<User> userList = userMessageMapper.getUsersByUserId(aliyunSecondInterviewParam.getUserId());
        for (User user :
                userList) {
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain(Domain);
            request.setVersion(Version);
            request.setAction(Action);
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", user.getUserTel());
            request.putQueryParameter("SignName", SignName);
            request.putQueryParameter("TemplateCode", secondInterviewTemplateCode);
            request.putQueryParameter("TemplateParam", "{\"name\":\"" + user.getUserName() + "\","
                    + "\"timeSlot\":\"" + aliyunSecondInterviewParam.getTimeSlot() + "\","
                    + "\"department\":\"" + aliyunSecondInterviewParam.getDepartmentName() + "\","
                    + "\"telNo\":\"" + aliyunSecondInterviewParam.getTelNo() + "\","
                    + "\"place\":\"" + aliyunSecondInterviewParam.getPlace() + "\","
                    + "\"activity\":\"" + activity + "\","
                    + "}");
            CommonResponse commonResponse = client.getCommonResponse(request);
            AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
            if (aliyunResponseInfo.getCode() == "OK") {
            } else {
                log.error(aliyunResponseInfo.toString() + "二面短信发送失败" + user.toString());
                return new UniversalResponseBody<AliyunResponseInfo>(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg(), aliyunResponseInfo);
            }
        }
        return new UniversalResponseBody<AliyunResponseInfo>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }

    @Override
    public UniversalResponseBody<AliyunResponseInfo> sendEnrollInterviewMessage(AliyunEnrollParam aliyunEnrollParam) throws ClientException {

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        List<User> userList = userMessageMapper.getUsersByUserId(aliyunEnrollParam.getUserId());
        for (User user :
                userList) {
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain(Domain);
            request.setVersion(Version);
            request.setAction(Action);
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", user.getUserTel());
            request.putQueryParameter("SignName", SignName);
            request.putQueryParameter("TemplateCode", secondInterviewTemplateCode);
            request.putQueryParameter("TemplateParam", "{\"name\":\"" + user.getUserName() + "\","
                    + "\"organizationName\":\"" + aliyunEnrollParam.getOrganizationName() + "\","
                    + "\"department\":\"" + aliyunEnrollParam.getDepartmentName() + "\","
                    + "\"secret\":\"" + "群号" + aliyunEnrollParam.getSecret() + "\","
                    + "}");
            CommonResponse commonResponse = client.getCommonResponse(request);
            AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
            if (aliyunResponseInfo.getCode() == "OK") {
            } else {
                log.error(aliyunResponseInfo.toString() + "录取短信发送失败" + user.toString());
                return new UniversalResponseBody<AliyunResponseInfo>(ResponseResultEnum.PARAM_IS_INVALID.getCode(), ResponseResultEnum.PARAM_IS_INVALID.getMsg(), aliyunResponseInfo);
            }
        }
        return new UniversalResponseBody<AliyunResponseInfo>(ResponseResultEnum.SUCCESS.getCode(), ResponseResultEnum.SUCCESS.getMsg());
    }
}
