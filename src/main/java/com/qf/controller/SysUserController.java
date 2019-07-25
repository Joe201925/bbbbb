package com.qf.controller;

import com.qf.service.SysUserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 登录功能
     * @param
     * @param
     * @param
     */
    @RequestMapping("/tologin")
    public String loginerror(HttpServletRequest request) throws Exception{
     //1.先得到错误信息
        String shiroLoginFailure=(String)request.getAttribute("shiroLoginFailure");
        System.out.println("shiroLoginFailure"+shiroLoginFailure);

        if (shiroLoginFailure!=null){
            if (shiroLoginFailure.equals(UnknownAccountException.class.getName())){
                throw new Exception("用户名有误");
            }else if (shiroLoginFailure.equals(IncorrectCredentialsException.class.getName())){
                throw new Exception("密码有误");
            }else {
                throw new Exception("其它异常");
            }
        }
           return "login";
    }

    /**
     * 新增用户
     */
    @RequestMapping("/add")
    public String add(){
        System.out.println("add-----------");
        return "addsuccess";
    }

    /**
     *退出
     */
    @RequestMapping("/loginout")
    public String loginout(){
        System.out.println("loginout-----------");
        return "login";
    }

}
