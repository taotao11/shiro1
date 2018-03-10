package com.shiro.controller;

import com.shiro.util.ShiroMd5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShiroController {
    /**
     * 登录跳转页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        System.out.println("进入登录页面");
        return "login";
    }

    /**
     * 登录跳转页面
     * @return
     */
    @RequestMapping("/other")
    public String goOther(){
        System.out.println("进入登录页面");
        return "logout";
    }
    @RequestMapping("/md5")
    public  String md5(){
        String pass = ShiroMd5.getMd5("123456",
                "admin");
       System.out.println(pass);
       return "/index";
    }
    /**
     * 主页跳转页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        System.out.println("进入主页面");
        return "index";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping("/formLogin")
    public String doLogin(@RequestParam("name") String name,
                          @RequestParam("password") String password){
        //拿到当前subject对象
        Subject subject = SecurityUtils.getSubject();
        //判断是否认证
        if ( !subject.isAuthenticated()){
            //封装 UsernamePasswordToken  对象
            UsernamePasswordToken token = new UsernamePasswordToken(name,password);

            token.setRememberMe(true);
            try {
                //登录
                subject.login(token);
                //AuthenticationException所有认证异常的父类
            }catch (AuthenticationException e){
                System.out.println("登录失败：" + e.getMessage());
                return "login";
            }

        }
        System.out.println("经过这里来");
        return "index";
    }
}
