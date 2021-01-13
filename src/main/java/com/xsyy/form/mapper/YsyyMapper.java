package com.xsyy.form.mapper;

import com.xsyy.form.domain.DingDingYuSuan;
import com.xsyy.form.domain.DingDingYuSuanCGLX;
import com.xsyy.form.domain.DingDingYuSuanKM;
import com.xsyy.form.domain.DingDingYuSuanXM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调度任务信息 数据层
 * 
 * @author bingai
 */
@Mapper
public interface YsyyMapper
{
    //查询所有预算明细
    List<DingDingYuSuan> getAllYS();

    //查询所有科目编码和名称
    List<DingDingYuSuanCGLX> getAllYsCglx();

    //查询所有科目编码和名称
    List<DingDingYuSuanKM> getAllYsKm();

    //查询所有项目编码和名称
    List<DingDingYuSuanXM> getAllYsXm();

    //查询项目编码和名称根据采购类型
    List<DingDingYuSuanXM> getYsXmByCglx(String typeCode);

    //查询项目编码和名称根据科目
    List<DingDingYuSuanXM> getYsXmByKm(String budgSubjCode);

    //查询科目编码和名称根据项目
    List<DingDingYuSuanKM> getYsKmByXm(String acctBudgCode);

    //查询预算数据根据预算项目和预算科目
    DingDingYuSuan getYsByKmAndXm(@Param("acctBudgCode") String acctBudgCode, @Param("budgSubjCode") String budgSubjCode);
}
