package com.xsyy.form.service.impl;

import com.xsyy.form.domain.DingDingYuSuan;
import com.xsyy.form.domain.DingDingYuSuanCGLX;
import com.xsyy.form.domain.DingDingYuSuanKM;
import com.xsyy.form.domain.DingDingYuSuanXM;
import com.xsyy.form.mapper.YsyyMapper;
import com.xsyy.form.service.IYsyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bingai
 * @create 2020-12-29 21:02
 */
@Service
public class YsyyServiceImpl implements IYsyyService {

    @Autowired
    private YsyyMapper ysyyMapper;


    @Override
    public List<DingDingYuSuan> getAllYS() {
        return ysyyMapper.getAllYS();
    }

    @Override
    public List<DingDingYuSuanCGLX> getAllYsCglx() {
        return ysyyMapper.getAllYsCglx();
    }

    @Override
    public List<DingDingYuSuanKM> getAllYsKm() {
        return ysyyMapper.getAllYsKm();
    }

    @Override
    public List<DingDingYuSuanXM> getAllYsXm() {
        return ysyyMapper.getAllYsXm();
    }

    @Override
    public List<DingDingYuSuanXM> getYsXmByCglx(String typeCode) {
        return ysyyMapper.getYsXmByCglx(typeCode);
    }


    @Override
    public List<DingDingYuSuanXM> getYsXmByKm(String budgSubjCode) {
        return ysyyMapper.getYsXmByKm(budgSubjCode);
    }

    @Override
    public List<DingDingYuSuanKM> getYsKmByXm(String acctBudgCode) {
        return ysyyMapper.getYsKmByXm(acctBudgCode);
    }

    @Override
    public DingDingYuSuan getYsByKmAndXm(String acctBudgCode, String budgSubjCode) {
        return ysyyMapper.getYsByKmAndXm(acctBudgCode,budgSubjCode);
    }
}
