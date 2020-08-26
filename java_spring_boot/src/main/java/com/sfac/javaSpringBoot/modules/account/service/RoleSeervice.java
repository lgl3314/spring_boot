package com.sfac.javaSpringBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;

import java.util.List;

public interface RoleSeervice {
    List<Role> getRoles();

    Result<Role> editRole(Role role);

    Result<Role> deleteRole(int roleId);

    PageInfo<Role> getRoles(SearchVo searchVo);

    List<Role> getRolesByUserId(int userId);

    List<Role> getRolesByResourceId(int resourceId);

    Role getRoleById(int roleId);
}
