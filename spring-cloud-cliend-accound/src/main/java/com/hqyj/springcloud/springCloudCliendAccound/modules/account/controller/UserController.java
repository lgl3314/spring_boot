package com.hqyj.springcloud.springCloudCliendAccound.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudCliendAccound.modules.account.entity.User;
import com.hqyj.springcloud.springCloudCliendAccound.modules.account.service.UserService;
import com.hqyj.springcloud.springCloudCliendAccound.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudCliendAccound.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    /*
     * 127.0.0.1/api/user/3--------grt
     * */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }



}
