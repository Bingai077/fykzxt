package com.xsyy.form.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bingai
 * @create 2021-01-06 23:49
 */
@Data
public class DingDingYuSuanKM implements Serializable
{
    private static final long serialVersionUID = 13435656L;


    //预算科目编码
    private String budgSubjCode;

    //预算科目名称
    private String budgSubjName;


}
