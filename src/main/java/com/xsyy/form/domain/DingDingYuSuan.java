package com.xsyy.form.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bingai
 * @create 2021-01-06 23:49
 */
@Data
public class DingDingYuSuan implements Serializable
{
    private static final long serialVersionUID = 1L;

    //年度
    private String acctYear;

    //采购类型编码
    private String typeCode;

    //采购类型名称
    private String typeName;

    //预算项目编码
    private String acctBudgCode;

    //预算项目名称
    private String acctBudgName;

    //预算科目编码
    private String budgSubjCode;

    //预算科目名称
    private String budgSubjName;

    //预算业务科室编码
    private String deptCode;

    //预算业务科室名称
    private String deptName;

    //采购申请预算余额
    private BigDecimal contractRemainAmount;

    //付款申请预算余额
    private BigDecimal remainAmount;


}
