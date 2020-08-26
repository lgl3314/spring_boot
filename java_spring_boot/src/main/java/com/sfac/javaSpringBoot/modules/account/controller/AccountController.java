package com.sfac.javaSpringBoot.modules.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    /*
    * 127.0.0.1/account/login
    * */
    @GetMapping("/login")
    public String loginpage(){
        return "indexSimple";
    }

    /*
    * 127.0.0.1//account/register
    * */
    @GetMapping("/register")
    public String registerpage(){
        return "indexSimple";
    }
    /*
    * 127.0.0.1/account/users
    * */
    @GetMapping("/users")
    public String userPage(){
        return "index";
    }

    /**
     * 127.0.0.1/account/roles ---- get
     */
    @GetMapping("/roles")
    public String rolesPage() {
        return "index";
    }

    /**
     * 127.0.0.1/account/resources ---- get
     */
    @GetMapping("/resources")
    public String resourcesPage() {
        return "index";
    }

    @GetMapping("/profile")
    public String profilePage(){
        return "index";
    }


    /*
    * 127.0.0.1/account/registerVue
    * */
    @GetMapping("/registerVue")
    public String refisterVuePage(){
        return "indexSimple";
    }
}
