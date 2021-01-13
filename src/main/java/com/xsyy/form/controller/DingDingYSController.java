package com.xsyy.form.controller;

import com.xsyy.form.service.IYsyyService;
import com.xsyy.form.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author bingai
 * @create 2020-12-29 20:34
 * 获取ERP预算数据
 */
@Controller
public class DingDingYSController {

    @Autowired
    private IYsyyService ysyyService;

    /**
     * 欢迎页面,通过url访问，判断后端服务是否启动
     */
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String welcome() {
//        return "hello";
//    }


    /**
     * 查询所有预算数据
     * @return
     */
    @RequestMapping(value = "/dingdingyusuan", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getAllYS() {
        return ServiceResult.success(ysyyService.getAllYS());
    }


    /**
     * 查询所有采购类型名称
     * @return
     */
    @RequestMapping(value = "/dingdingyusuancglx", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getAllYsCglx() {

        return ServiceResult.success(ysyyService.getAllYsCglx());

    }


    /**
     * 查询所有预算科目名称
     * @return
     */
    @RequestMapping(value = "/dingdingyusuankm", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getAllYsKm() {

        return ServiceResult.success(ysyyService.getAllYsKm());

    }


    /**
     * 查询所有预算项目名称
     * @return
     */
    @RequestMapping(value = "/dingdingyusuanxm", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult getAllYsXm() {
        return ServiceResult.success(ysyyService.getAllYsXm());

    }




    /**
     * 查询预算项目名称根据采购类型
     * @return
     */
    @RequestMapping(value = "/dingdingyusuanxmBycglx", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ServiceResult getYsXmByCglx(@RequestParam String typeCode) {

        return ServiceResult.success(ysyyService.getYsXmByCglx(typeCode));

    }

    /**
     * 查询预算项目名称根据科目
     * @return
     */
    @RequestMapping(value = "/dingdingyusuanxmBykm", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ServiceResult getYsXmByKm(@RequestParam String budgSubjCode) {

        return ServiceResult.success(ysyyService.getYsXmByKm(budgSubjCode));

    }

    /**
     * 查询预算科目名称根据项目
     * @return
     */
    @RequestMapping(value = "/dingdingyusuankmByxm", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ServiceResult getYsKmByXm(@RequestParam String acctBudgCode) {

        return ServiceResult.success(ysyyService.getYsKmByXm(acctBudgCode));

    }


    /**
     * 查询预算数据根据预算项目和预算科目
     * @return
     */
    @PostMapping("/dingdingyusuanByKmAndXm")
    @ResponseBody
    public ServiceResult getYsByKmAndXm(String acctBudgCode,String budgSubjCode) {

        return ServiceResult.success(ysyyService.getYsByKmAndXm(acctBudgCode,budgSubjCode));
    }




}
