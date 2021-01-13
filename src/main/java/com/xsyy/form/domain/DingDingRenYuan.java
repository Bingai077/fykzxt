package com.xsyy.form.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bingai
 * @create 2021-01-06 23:49
 */
@Data
public class DingDingRenYuan implements Serializable
{
    private static final long serialVersionUID = 11111L;

    //员工工号
    private String empCode;

    //员工姓名
    private String empName;

    //部门编码
    private String deptCode;

    //部门名称
    private String deptName;

    //职称代码
    private String titleCode;

    //职称名称
    private String titleName;


}
