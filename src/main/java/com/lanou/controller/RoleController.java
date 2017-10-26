package com.lanou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yugege on 17/10/26.
 */
@Controller
public class RoleController {

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


}
