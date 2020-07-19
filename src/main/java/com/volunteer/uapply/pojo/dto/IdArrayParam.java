package com.volunteer.uapply.pojo.dto;

import lombok.Data;

/**
 * 此类是为了将数组类型的参数封装成对象，以方便进行序列化
 *
 * @program: uapply
 * @author: GuoShuSong
 * @create: 2020-07-18 22:33
 **/
@Data
public class IdArrayParam {
    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 用户Id的数组
     */
    private Integer[] userId;


    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 组织Id
     */
    private Integer organizationId;
}
