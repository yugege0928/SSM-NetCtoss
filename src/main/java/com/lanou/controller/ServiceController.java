package com.lanou.controller;


import com.github.pagehelper.PageInfo;
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

//        System.out.println("serviceById:---"+serviceById);

        return new AjaxResult(serviceById);
    }

    @ResponseBody
    @RequestMapping(value = "/findBSID")
    public AjaxResult frontPageFindByID(HttpServletRequest request){

        Servicee findByIdService = (Servicee) request.getSession().getAttribute("findByIdService");

        return new AjaxResult(findByIdService);
    }

    @ResponseBody
    @RequestMapping(value = "/findBSID2")
    public AjaxResult frontPageFindByID2(HttpServletRequest request){

        Servicee findByIdService = (Servicee) request.getSession().getAttribute("findByIdService");

        System.out.println(findByIdService.getCostId());

        Cost cost = costServicee.findById(findByIdService.getCostId());

        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/findBSID3")
    public AjaxResult frontPageFindByID3(HttpServletRequest request){

        Servicee findByIdService = (Servicee) request.getSession().getAttribute("findByIdService");

        Account account = accountService.findById(findByIdService.getAccountId());

        return new AjaxResult(account);
    }


    /**
     * 更改(更新)
     */
    @ResponseBody
    @RequestMapping(value = "/updateService",method = RequestMethod.POST)
    public AjaxResult frontPage(@RequestParam("CType")String type,
                                HttpServletRequest request){

        Servicee findByIdService = (Servicee) request.getSession().getAttribute("findByIdService");

        findByIdService.setCostId(costServicee.findByType(type).getCostId());

        int i = service.updataService(findByIdService);

        if (i > 0){
            System.out.println("更新成功");
        }

        return new AjaxResult(findByIdService);
    }


    /**
     * 删除操作
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSe")
    public AjaxResult frontPageDeleteService(@RequestParam("delSId")Integer id){

        Servicee byId = service.findById(id);

        byId.setStatus("2");

        int i = service.updataService(byId);

        if (i > 0){
            System.out.println("标记删除成功");
        }

        return new AjaxResult();
    }

    /**
     * 更改启用/暂停状态
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/useSer")
    public AjaxResult frontPageUse(@RequestParam("userId")Integer id){

        Servicee byId = service.findById(id);

        byId.setStatus("1");

        int i = service.updataService(byId);

        if (i > 0){
            System.out.println("启用成功");
        }



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

    /**
     * 分页功能
     */
    @ResponseBody
    @RequestMapping(value = "/ServicePage")
    public List<Servicee> students(@RequestParam("no")Integer pageNo,
                                  @RequestParam("size")Integer pageSize){

//        System.out.println("pageNo:---"+pageNo+"pageSize:---"+pageSize);

        return service.findWithPageInfo(pageNo,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/ServiceAllPage")
    public PageInfo<Servicee> pageInfo(@RequestParam("pageSizes")Integer pageSize){

        return service.getPageInfo(pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/findByT",method = RequestMethod.GET)
    public AjaxResult findAllByTj(@RequestParam("osUsername") String osUsername,
                                  @RequestParam("unixHost") String unixHost,
                                  @RequestParam("idcardNo") String idcardNo){

        System.out.println(osUsername+unixHost);

        List<Servicee> byTj = service.findByTj(osUsername, unixHost,idcardNo);

        return new AjaxResult(byTj);
    }


}
