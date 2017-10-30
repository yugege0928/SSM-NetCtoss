package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.service.impl.AccountServiceImpl;
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
 * Created by yugege on 17/10/23.
 */
@Controller
public class AccountController {

    @Resource
    private AccountServiceImpl service;

    @RequestMapping(value = "/act_list")
    public String frontPage1(){
        return "account/account_list";
    }

    @RequestMapping(value = "/act_add")
    public String frontPage2(){
        return "account/account_add";
    }

    @RequestMapping(value = "/act_det")
    public String frontPage3(){
        return "account/account_detail";
    }

    @RequestMapping(value = "/act_modi")
    public String frontPage4(){
        return "account/account_modi";
    }

    /**
     * 增
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAct",method = RequestMethod.POST)
    public AjaxResult frontPage5(Account account){

        account.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        account.setStatus("0");

        int i = service.insertAccount(account);

        if (i > 0){
            System.out.println("增加成功");
        }
        return new AjaxResult(account);
    }

    /**
     * 删
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAct",method = RequestMethod.POST)
    public AjaxResult frontPage6(@RequestParam("AId")Integer id){

        int i = service.deleteAccountById(id);

        if (i > 0){
            System.out.println("删除成功");
        }
        return new AjaxResult(id);
    }

    /**
     * 改
     */
    @ResponseBody
    @RequestMapping(value = "/updateAct",method = RequestMethod.POST)
    public AjaxResult frontPage7(Account account){

        int i = service.updateAccount(account);

        if (i > 0){
            System.out.println("更新成功");
        }

        return new AjaxResult(account);
    }

    /**
     * 根据Id查找账号
     */
    @ResponseBody
    @RequestMapping(value = "/findBI",method = RequestMethod.GET)
    public AjaxResult frontPage9(@RequestParam("AId")Integer id, HttpServletRequest request){

//        System.out.println("id:---"+id);

        HttpSession session = request.getSession();

        Account a = service.findById(id);

        session.setAttribute("account",a);

//        System.out.println("a:---"+a);

        return new AjaxResult(a);
    }
    @ResponseBody
    @RequestMapping(value = "/find")
    public AjaxResult frontPage10(HttpServletRequest request){

        Account account = (Account) request.getSession().getAttribute("account");

        return new AjaxResult(account);
    }

    /**
     * 查找全部
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/allAccount",method = RequestMethod.POST)
    public AjaxResult frontPage10(){

        List<Account> allAccount = service.findAllAccount();

        return new AjaxResult(allAccount);
    }

    /**
     * 12.分页
     */
    @ResponseBody
    @RequestMapping(value = "/AccountPage")
    public List<Account> students(@RequestParam("no")Integer pageNo,
                               @RequestParam("size")Integer pageSize){

//        System.out.println("pageNo:---"+pageNo+"pageSize:---"+pageSize);

        return service.findWithPageInfo(pageNo,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/APage")
    public PageInfo<Account> pageInfo(@RequestParam("pageSizes")Integer pageSize){

        return service.getPageInfo(pageSize);
    }

    /**
     * 输入栏查询
     */
    @ResponseBody
    @RequestMapping(value = "/findBC",method = RequestMethod.POST)
    public AjaxResult findByCondition(Account account){

        if (account.getStatus() == null || account.getLoginName().trim().isEmpty()) {
            account.setLoginName(null);
        }
        if (account.getRealName() == null || account.getRealName().trim().isEmpty()) {
            account.setRealName(null);
        }
        if (account.getIdcardNo() == null || account.getIdcardNo().trim().isEmpty()) {
            account.setIdcardNo(null);
        }
        if (account.getStatus() == null || account.getStatus().trim().isEmpty()) {
            account.setStatus(null);
        }

        System.out.println("account:---"+account);
        List<Account> byCon = service.findByCon(account);
        return new AjaxResult(byCon);
    }

}
