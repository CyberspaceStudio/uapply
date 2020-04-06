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
import com.volunteer.uapply.pojo.info.AliyunResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信服务
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/7 21:23
 */
@Slf4j
@Component
public class AliyunMessageUtil {

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


//    /**
//     * 一面短信
//     * @param PhoneNumbers
//     * @param name
//     * @param timeSlot
//     * @param departmentId
//     * @param telNo
//     * @param place
//     * @return
//     * @throws ClientException
//     */
//    public AliyunResponseInfo SendMessage(String PhoneNumbers, String name, String timeSlot, Integer departmentId, String telNo, String place) throws ClientException {
//       IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain(Domain);
//        request.setVersion(Version);
//        request.setAction(Action);
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers",PhoneNumbers);
//        request.putQueryParameter("SignName",SignName);
//        request.putQueryParameter("TemplateCode",firstInterviewTemplateCode);
//        request.putQueryParameter("TemplateParam", "{\"name\":\""+name+"\","
//                +"\"timeSlot\":\""+ timeSlot+"\","
//                +"\"department\":\""+ DepartmentEnum.getDepartmentNameById(departmentId)+"\","
//                +"\"telNo\":\""+telNo+"\","
//                +"\"place\":\""+place+"\","
//                +"\"activity\":\""+activity+"\","
//                +"}");
//        CommonResponse commonResponse = client.getCommonResponse(request);
//        log.info(commonResponse.getData());
//        AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
//        return aliyunResponseInfo;
//    }
//
//    /**
//     * 二面短信
//     * ${name}同学您好，恭喜你成功了进入了${department}的第二轮${activity}。安排如下： 时间：${timeSlot} 。
//     * 地点：${place} 。如因故不能及时到达，请及时与我们联系${telNo}!注意第二轮只能参加一个部门的哦!参加了其他部门，就无法参加我们部门的了哦!
//     * @param PhoneNumbers
//     * @param name
//     * @param timeSlot
//     * @param departmentId
//     * @param telNo
//     * @param place
//     * @return
//     * @throws ClientException
//     */
//    public AliyunResponseInfo SendSecondMessage(String PhoneNumbers, String name, String timeSlot, Integer departmentId, String telNo, String place) throws ClientException {
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain(Domain);
//        request.setVersion(Version);
//        request.setAction(Action);
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers",PhoneNumbers);
//        request.putQueryParameter("SignName",SignName);
//        request.putQueryParameter("TemplateCode",secondInterviewTemplateCode);
//        request.putQueryParameter("TemplateParam", "{\"name\":\""+name+"\","
//                +"\"timeSlot\":\""+ timeSlot+"\","
//                +"\"department\":\""+ DepartmentEnum.getDepartmentNameById(departmentId)+"\","
//                +"\"telNo\":\""+telNo+"\","
//                +"\"place\":\""+place+"\","
//                +"\"activity\":\""+activity+"\","
//                +"}");
//        CommonResponse commonResponse = client.getCommonResponse(request);
//        log.info(commonResponse.getData());
//        AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
//        return aliyunResponseInfo;
//    }
//
//
//    /**
//     * ${name}同学，你好！非常高兴地通知你:你成为了西电青年志愿者协会${department}的一员!快来加入我们的大家庭吧!密钥是${secret})
//     * @param PhoneNumbers
//     * @param name
//     * @param departmentId
//     * @return
//     */
//    public AliyunResponseInfo SendEnrollMessage(String PhoneNumbers, String name,Integer departmentId,String secret) throws ClientException {
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain(Domain);
//        request.setVersion(Version);
//        request.setAction(Action);
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers",PhoneNumbers);
//        request.putQueryParameter("SignName",SignName);
//        request.putQueryParameter("TemplateCode",secondInterviewTemplateCode);
//        request.putQueryParameter("TemplateParam", "{\"name\":\""+name+"\","
//                +"\"department\":\""+ DepartmentEnum.getDepartmentNameById(departmentId)+"\","
//                +"\"secret\":\""+"群号"+secret+"\","
//                +"}");
//        CommonResponse commonResponse = client.getCommonResponse(request);
//        log.info(commonResponse.getData());
//        AliyunResponseInfo aliyunResponseInfo = JSON.parseObject(commonResponse.getData(), AliyunResponseInfo.class);
//        return aliyunResponseInfo;
//    }
//
//
}
