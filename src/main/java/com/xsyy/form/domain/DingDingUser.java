package com.xsyy.form.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingai
 * @create 2021-01-06 23:49
 */
@Data
public class DingDingUser implements Serializable
{
    private static final long serialVersionUID = 111116877L;

    //员工id
    private String userId;

    //员工姓名
    private String userName;

    //工号
    private String jobNumber;

    //部门编码
    private Long deptId;



}
