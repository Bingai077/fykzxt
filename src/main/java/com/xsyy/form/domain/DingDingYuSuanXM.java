package com.xsyy.form.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bingai
 * @create 2021-01-06 23:49
 */
@Data
public class DingDingYuSuanXM implements Serializable
{
    private static final long serialVersionUID = 16778L;

    //预算项目编码
    private String acctBudgCode;

    //预算项目名称
    private String acctBudgName;




}
