package com.sfac.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.account.service.UserService;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
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

    /*
    * 127.0.0.1/api/user---------put
    * {"userName":"lgl1","userImg":"www.com","userId":"2"}
    * */
    @PutMapping(value = "/user" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }


    /*
    * 127.0.0.1/api/user/1--------delete
    * */
    @DeleteMapping("/user/{userId}")
    public Result<Object> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    /*
     * 127.0.0.1/api/user/1--------grt
     * */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }


    /*
    * 127.0.0.1/api/userImg
    * */
    @PostMapping(value = "/userImg",consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file){

        return userService.uploadUserImg(file);
    }
}
