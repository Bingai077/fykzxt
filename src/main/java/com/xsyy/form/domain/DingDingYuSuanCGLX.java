package com.xsyy.form.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingai
 * @create 2021-01-13 14:23
 */
@Data
public class DingDingYuSuanCGLX implements Serializable
{
    private static final long serialVersionUID = 1456L;

    //采购类型编码
    private String typeCode;

    //采购类型名称
    private String typeName;

}
