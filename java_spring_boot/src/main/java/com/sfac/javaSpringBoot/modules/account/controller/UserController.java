package com.sfac.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.account.service.UserService;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * 127.0.0.1/api/user----------post
    * {"userName":"lgl","password":"123"}
    * */
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user) {

        return userService.insertUser(user);
    }

    /*
    * 127.0.0.1/api/login------post
    * {"userName":"lgl","password":"123"}
    * */
    @PostMapping(value = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user){
        return userService.login(user);
    }

    /*
    * 127.0.0.1/api/users-------post
    * {"currentPage":"1","pageSize":"5","keyWord":"lgl"}
    * */
    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUserBySearchVo(@RequestBody SearchVo searchVo) {
        return userService.getUserBySearchVo(searchVo);
    }
}
