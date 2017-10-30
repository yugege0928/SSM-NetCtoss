package com.lanou.controller;

import com.lanou.bean.ModuleInfo;
import com.lanou.service.impl.ModuleInfoServiceImpl;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/29.
 */
@Controller
public class ModuleInfoController {

    @Resource
    private ModuleInfoServiceImpl service;

    @ResponseBody
    @RequestMapping(value = "/findAllModule")
    public AjaxResult findAllModule(){
        List<ModuleInfo> allModule = service.findAllModule();
//        System.out.println("allModule:---"+allModule);
        return new AjaxResult(allModule);
    }
}
