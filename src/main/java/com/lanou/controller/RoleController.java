package com.lanou.controller;

import com.fasterxml.jackson.databind.Module;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.impl.RoleInfoServiceImpl;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    /**
     * 分页
     */
    @ResponseBody
    @RequestMapping(value = "/findAllRole")
    public AjaxResult findAllRole(@RequestParam("no")Integer no,
                                  @RequestParam("size")Integer pagesize){
        PageInfo<RoleInfo> pageInfo = Rolservice.findRolePageInfo(no,pagesize);
        return new AjaxResult(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/saveRoleId")
    public AjaxResult savaRoleId(HttpServletRequest request,
                                 RoleInfo roleInfo){
        request.getSession().setAttribute("roleId",roleInfo.getRoleId());
        return new AjaxResult();
    }

    @ResponseBody
    @RequestMapping(value = "/findRole",method = RequestMethod.POST)
    public AjaxResult findRole(HttpServletRequest request){
        Integer roleId = (Integer) request.getSession().getAttribute("roleId");
        System.out.println("roleId:---"+roleId);
        RoleInfo roleInfoId = Rolservice.findRoleByRoleID(roleId);
        System.out.println("roleInfoId:---"+roleInfoId);
        return new AjaxResult(roleInfoId);
    }

    @ResponseBody
    @RequestMapping(value = "/changeRole")
    public AjaxResult changeeRole(@RequestParam(value = "moduleId",required = false)String Moduleid,
                                 @RequestParam(value = "roleName",required = false)String roleName,
                                 HttpServletRequest request){

        String[] split = Moduleid.split(",");
        List<ModuleInfo> moduleList = new ArrayList<>();
        for (String s : split) {
            ModuleInfo module = new ModuleInfo();
            module.setModuleId(Integer.valueOf(s));
            moduleList.add(module);
        }
        Integer roleId = (Integer) request.getSession().getAttribute("roleId");
        RoleInfo role = new RoleInfo();
        role.setRoleId(roleId);
        role.setName(roleName);
        role.setModuleInfoList(moduleList);
        Rolservice.updateRole(role);
        return new AjaxResult(role);
    }

    @ResponseBody
    @RequestMapping(value = "/addRole")
    public AjaxResult adRole(@RequestParam(value = "moduleId",required = false)String Moduleid,
                                 @RequestParam(value = "roleName",required = false)String roleName){
        System.out.println("Moduleid:---"+Moduleid);
        System.out.println("roleName:---"+roleName);
        String[] split = Moduleid.split(",");
        List<ModuleInfo> moduleList = new ArrayList<>();
        for (String s : split) {
            ModuleInfo module = new ModuleInfo();
            module.setModuleId(Integer.valueOf(s));
            moduleList.add(module);
        }
        RoleInfo role = new RoleInfo();
        role.setName(roleName);
        role.setModuleInfoList(moduleList);
        Rolservice.addRole(role);
        return new AjaxResult(role);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteR")
    public AjaxResult deleteRole(RoleInfo roleInfo){
        Rolservice.deleteRole(roleInfo);
        return new AjaxResult(roleInfo);
    }

}
