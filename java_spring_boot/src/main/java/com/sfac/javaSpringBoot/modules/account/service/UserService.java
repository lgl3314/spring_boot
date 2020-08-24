package com.sfac.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
  Result<User> insertUser(User user);

  Result<User> login(User user);

  PageInfo<User> getUserBySearchVo(SearchVo searchVo);

  Result<User> updateUser(User user);

  Result<Object> deleteUser(int userId);

  User getUserByUserId(int userId);

  Result<String> uploadUserImg(MultipartFile file);
}
