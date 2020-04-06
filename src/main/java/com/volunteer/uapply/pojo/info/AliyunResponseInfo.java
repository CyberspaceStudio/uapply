package com.volunteer.uapply.pojo.info;

import lombok.Data;

/**
 * 阿里云短信服务返回结果对象
 *
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/5 18:51
 */
@Data
public class AliyunResponseInfo {

    private String Message;

    private String RequestId;

    private String Code;
}
