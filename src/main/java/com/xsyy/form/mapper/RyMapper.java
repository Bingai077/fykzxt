package com.xsyy.form.mapper;

import com.xsyy.form.domain.DingDingRenYuan;
import com.xsyy.form.domain.DingDingYuSuan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 调度任务信息 数据层
 * 
 * @author bingai
 */
@Mapper
public interface RyMapper
{
    DingDingRenYuan getRymx(String empCode);
}
