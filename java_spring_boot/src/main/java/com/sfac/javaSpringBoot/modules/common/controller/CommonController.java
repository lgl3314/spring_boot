package com.sfac.javaSpringBoot.modules.common.controller;


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

    /*
    * 127.0.0.1/common/403
    * */
     @GetMapping("/403")
    public String errorPage403(){
        return "index";
    }

}
