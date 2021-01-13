package com.xsyy.form.service;

import com.xsyy.form.domain.DingDingYuSuan;
import com.xsyy.form.domain.DingDingYuSuanCGLX;
import com.xsyy.form.domain.DingDingYuSuanKM;
import com.xsyy.form.domain.DingDingYuSuanXM;

import java.util.List;

/**
 * @author bingai
 * @create 2020-12-29 21:01
 */
public interface IYsyyService {


    //查询全部预算明细
    List<DingDingYuSuan> getAllYS();

    //查询全部采购类型名称
    List<DingDingYuSuanCGLX> getAllYsCglx();

    //查询所有科目名称
    List<DingDingYuSuanKM> getAllYsKm();

    //查询所有项目名称
    List<DingDingYuSuanXM> getAllYsXm();

    //查询项目名称根据采购类型
    List<DingDingYuSuanXM> getYsXmByCglx(String typeCode);

    //查询项目名称根据科目
    List<DingDingYuSuanXM> getYsXmByKm(String budgSubjCode);

    //查询科目名称根据项目
    List<DingDingYuSuanKM> getYsKmByXm(String acctBudgCode);

    //查询预算数据根据预算项目和预算科目
    DingDingYuSuan getYsByKmAndXm(String acctBudgCode,String budgSubjCode);

}

