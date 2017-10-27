package com.lanou.controller;

import com.lanou.bean.RoleInfo;
import com.lanou.service.impl.RoleInfoServiceImpl;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/26.
 */
@Controller
public class RoleController {

    @Resource
    private RoleInfoServiceImpl Rolservice;

    @RequestMapping(value = "/ro_list")
    public String frontPageList(){
        return "role/role_list";
    }

    @RequestMapping(value = "/ro_add")
    public String frontPageAdd(){
        return "role/role_add";
    }

    @RequestMapping(value = "/ro_modi")
    public String frontPageModi(){
        return "role/role_modi";
    }


    @ResponseBody
    @RequestMapping(value = "/allRole")
    public AjaxResult frontPageAllRole(){

        List<RoleInfo> allRole = Rolservice.findAllRole();

        System.out.println("allRole:---"+allRole);

        return new AjaxResult(allRole);
    }





}
