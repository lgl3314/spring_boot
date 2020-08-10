package com.sfac.javaSpringBoot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    //111
    @GetMapping("/testDese")
    @ResponseBody
    public String testDese(){
        return "这是一个很牛的软件。";
    }
}
