package com.xsyy.form.controller;

import com.xsyy.form.service.IRyService;
import com.xsyy.form.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author bingai
 * @create 2020-12-29 20:34
 * 获取ERP人员信息
 */
@Controller
public class DingDingRYController {

    @Autowired
    private IRyService ryService;


    @RequestMapping(value = "/dingdingRymx", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ServiceResult getRymx(@RequestParam String empCode) {

        return ServiceResult.success(ryService.getRymx(empCode));
    }





}
