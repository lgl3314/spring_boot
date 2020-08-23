package com.sfac.javaSpringBoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.dao.UserDao;
import com.sfac.javaSpringBoot.modules.account.dao.UserRoleDao;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.account.service.UserService;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import com.sfac.javaSpringBoot.utils.MD5Util;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.OpenOption;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        User userTem=userDao.getUserByUserName(user.getUserName());
        if (userTem!=null){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"该用户已经存在，请重新更换用户名。");
        }

        user.setPassword(MD5Util.getMD5(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        userDao.insertUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if(roles!=null && !roles.isEmpty()){
            /*for (int i = 0; i < roles.size(); i++) {
                userRoleDao.insertUserRole(user.getUserId(),roles.get(i).getRoleId());
            }
            for (Role role:roles){
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*/
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }
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

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        //判断用户是否已经存在
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if (userTemp !=null && userTemp.getUserId()!=user.getUserId()){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"修改失败，用户名与ID不符。");
        }
        userDao.updateUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"修改成功。",user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
       userDao.deleteUser(userId);
       userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"删除成功。");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }
}
