package com.sfac.javaSpringBoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.dao.UserDao;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.account.service.UserService;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import com.sfac.javaSpringBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> insertUser(User user) {
        User userTem=userDao.getUserByUserName(user.getUserName());
        if (userTem!=null){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"该用户已经存在，请重新更换用户名。");
        }
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        userDao.insertUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"注册成功。",user);
    }

    @Override
    public Result<User> login(User user) {
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if (userTemp!=null && userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"成功。");
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,"用户名或密码错误。" , userTemp);
    }

    @Override
    public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());

        return new PageInfo<User>(Optional.ofNullable(userDao
                .getUserBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }
}
