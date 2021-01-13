package com.xsyy.form.service.impl;

import com.xsyy.form.domain.DingDingRenYuan;
import com.xsyy.form.domain.DingDingYuSuan;
import com.xsyy.form.mapper.RyMapper;
import com.xsyy.form.mapper.YsyyMapper;
import com.xsyy.form.service.IRyService;
import com.xsyy.form.service.IYsyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bingai
 * @create 2020-12-29 21:02
 */
@Service
public class RyServiceImpl implements IRyService {

    @Autowired
    private RyMapper ryMapper;

    @Override
    public DingDingRenYuan getRymx(String empCode) {
        return ryMapper.getRymx(empCode);
    }
}
