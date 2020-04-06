package com.volunteer.uapply.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 创建 dto 对象，方便接收 json 参数
 *
 * @Author 桂乙侨
 * @Date 2020/2/8 18:48
 * @Version 1.0
 */
@Data
public class EnrollMembersDTO {

    /**
     * 用户id
     */
    private Integer[] userId;

    /**
     * 部门id
     */
    private Integer departmentId;
}
