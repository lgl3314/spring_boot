package com.sfac.javaSpringBoot.modules.commo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

    /*
    * 127.0.0.1/common/dashboard
    * */
    @GetMapping("/dashboard")
    public String dashboardpage(){
        return "index";
    }
}
