package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.impl.CostServiceImpl;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yugege on 17/10/20.
 */
@Controller
public class CostController {

    @Resource
    private CostServiceImpl service;

    //首页
    @RequestMapping(value = "/index")
    public String frontPage(){

        return "index";
    }

    /**
     * 1.资费页面的显示全部的列表界面
     */
    @RequestMapping(value = "/cost_list")
    public String frontPage2(){
        return "fee/fee_list";
    }

    /**
     * 2.增加资费页面
     */
    @RequestMapping(value = "/cost_add")
    public String frontPage3(){
        return "fee/fee_add";
    }

    /**
     * 3.修改资费页面
     */
    @RequestMapping(value = "/cost_modi")
    public String frontPage4(){


        return "fee/fee_modi";
    }

    /**
     * 3.2修改资费页面获取session中的值
     */
    @ResponseBody
    @RequestMapping(value = "/modi")
    public AjaxResult frontPagee(HttpServletRequest request){
        HttpSession session = request.getSession();

        Cost cost = (Cost)session.getAttribute("findById");

        System.out.println("要修改的对象cost:---"+cost);

        return new AjaxResult(cost);
    }

    /**
     * 4.资费说明页面
     */
    @RequestMapping(value = "/cost_detail")
    public String frontPage5(){
        return "fee/fee_detail";
    }

    /**
     * 5.显示所有资费信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/allCost",method = RequestMethod.POST)
    public AjaxResult frontPage6(HttpServletRequest request){

        HttpSession session = request.getSession();

        List<Cost> allCost = service.findAllCost();

//        System.out.println("Controller中的-->allCost:---"+allCost);

        return new AjaxResult(allCost);
    }

    /**
     * 6.删除资费信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCost",method = RequestMethod.POST)
    public AjaxResult frontPage7(@RequestParam("Cid")Integer id){

        System.out.println(id);

        int i = service.deleteCostById(id);

        System.out.println(i);
        System.out.println(id);
        if (i > 0){
            System.out.println("删除成功");
        }

        return new AjaxResult(id);
    }

    /**
     * 7.插入资费信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertCost",method = RequestMethod.GET)
    public AjaxResult frontPage8(Cost cost) throws UnsupportedEncodingException {


        cost.setCreatime((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        cost.setStatus("0");

        int i = service.insertCost(cost);

        if (i > 0){
            System.out.println("插入成功");
        }

        return new AjaxResult(cost);
    }

    /**
     * 8.更新资费信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCost",method = RequestMethod.POST)
    public AjaxResult frontPage9(Cost cost){

        System.out.println(cost);

        int i = service.updateCost(cost);

        if (i > 0){
            System.out.println("更新成功");
        }

        return new AjaxResult(cost);
    }

    /**
     * 9.根据id查找资费信息
     */
    @ResponseBody
    @RequestMapping(value = "/findById")
    public AjaxResult frontPage10(@RequestParam("CId")Integer id,HttpServletRequest request){

        System.out.println("修改中的id:---"+id);

        HttpSession session = request.getSession();

        Cost byId = service.findById(id);

        session.setAttribute("findById",byId);

        return new AjaxResult(byId);
    }

    /**
     * 10.查看详情(同上)
     */

    /**
     * 11.更改启用状态
     */
    @ResponseBody
    @RequestMapping(value = "/updatetwo",method = RequestMethod.POST)
    public AjaxResult frontPage11(@RequestParam("CId")Integer id){

        Cost cost = service.findById(id);

        System.out.println("修改启用状态的cost:---"+cost);

        cost.setStatus("1");

        cost.setStartime((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        int i = service.updateCost(cost);

        if (i > 0){
            System.out.println("更改成功");
        }

        return new AjaxResult(cost);
    }

    /**
     * 12.分页
     */
    @ResponseBody
    @RequestMapping(value = "/stu",method = RequestMethod.POST)
    public List<Cost> students(@RequestParam("no")Integer pageNo,
                                  @RequestParam("size")Integer pagaSize){

//        System.out.println("pageNo:---"+pageNo+"pagaSize:---"+pagaSize);

        return service.findWithPageInfo(pageNo,pagaSize);
    }

    @ResponseBody
    @RequestMapping(value = "/page1",method = RequestMethod.POST)
    public PageInfo<Cost> pageInfo(@RequestParam("pageSize")Integer pageSize){

        return service.getPageInfo(pageSize);
    }

}
