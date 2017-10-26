package com.lanou.controller;


import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.bean.Servicee;
import com.lanou.service.impl.AccountServiceImpl;
import com.lanou.service.impl.CostServiceImpl;
import com.lanou.service.impl.ServicesServiceImpl;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yugege on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServicesServiceImpl service;

    @Resource
    private AccountServiceImpl accountService;

    @Resource
    private CostServiceImpl costServicee;

    @RequestMapping(value = "/se_list")
    public String frontPageList(){
        return "service/service_list";
    }

    @RequestMapping(value = "/se_add")
    public String frontPageAdd(){
        return "service/service_add";
    }

    @RequestMapping(value = "/se_modi")
    public String frontPageModi(){
        return "service/service_modi";
    }

    @RequestMapping(value = "/se_detail")
    public String frontPageDetail(){
        return "service/service_detail";
    }


    /**
     * 显示全部
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAllService",method = RequestMethod.POST)
    public AjaxResult frontPageAllService(){

        List<Servicee> allServices = service.findAllServices();

//        System.out.println("allServices:---"+allServices);

        return new AjaxResult(allServices);
    }

    /**
     * 增加
     */
    @ResponseBody
    @RequestMapping(value = "/addSe")
    public AjaxResult frontPageAddService(Servicee servicee,
                                          @RequestParam("CType")String costType){

        servicee.setStatus("0");
        servicee.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));


        Cost byType = costServicee.findByType(costType);

        Integer costId = byType.getCostId();

        servicee.setCostId(costId);

//        System.out.println("servicee:---"+servicee);

        int i = service.addService(servicee);

        if (i > 0){
            System.out.println("插入成功");
        }
        return new AjaxResult(servicee);
    }

    /**
     * 根据ID查找业务
     */
    @ResponseBody
    @RequestMapping(value = "/findBSI")
    public AjaxResult frontPageFindById(@RequestParam("SId")Integer id, HttpServletRequest request){

        HttpSession session = request.getSession();

        Servicee serviceById = service.findById(id);

        session.setAttribute("findByIdService",serviceById);

        System.out.println("serviceById:---"+serviceById);

        return new AjaxResult(serviceById);
    }

    @ResponseBody
    @RequestMapping(value = "/findBSID")
    public AjaxResult frontPageFindByID(HttpServletRequest request){

        Servicee findByIdService = (Servicee) request.getSession().getAttribute("findByIdService");

        return new AjaxResult(findByIdService);
    }

    /**
     * 更改(更新)
     */
    @ResponseBody
    @RequestMapping(value = "/updateService",method = RequestMethod.POST)
    public AjaxResult frontPage(Servicee servicee){

        int i = service.updataService(servicee);

        if (i > 0){
            System.out.println("更新成功");
        }

        return new AjaxResult(servicee);
    }


    /**
     * 删除操作
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSe")
    public AjaxResult frontPageDeleteService(@RequestParam("delSId")Integer id){

        int i = service.deleteServicee(id);

        if (i > 0){
            System.out.println("删除成功");
        }

        return new AjaxResult();
    }

    /**
     * 更改启用状态
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/useSer")
    public AjaxResult frontPageUse(){
        return new AjaxResult();
    }


    /**
     * 输入身份证查出ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findByIDCard")
    public AjaxResult frontPageAccount(@RequestParam("IDCard")String id){
        Account idCard = accountService.findByIDCard(id);

        return new AjaxResult(idCard);
    }

    /**
     * 查询资费类型
     */
    @ResponseBody
    @RequestMapping(value = "/findAllCost")
    public AjaxResult frontPageCost(){

        List<Cost> cost = costServicee.findAllCost();

        return new AjaxResult(cost);
    }


}
