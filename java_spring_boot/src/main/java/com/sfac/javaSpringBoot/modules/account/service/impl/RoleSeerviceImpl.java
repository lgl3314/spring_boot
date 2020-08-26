package com.sfac.javaSpringBoot.modules.account.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.dao.RoleDao;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.service.RoleSeervice;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleSeerviceImpl implements RoleSeervice {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Result<Role> editRole(Role role) {
        Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());
        if (roleTemp != null && roleTemp.getRoleId() != role.getRoleId()) {
            return new Result<Role>(Result.ResultStatus.FAILD.status, "修改失败！！！");
        }

        if (role.getRoleId() > 0) {
            roleDao.updateRole(role);
        } else {
            roleDao.addRole(role);
        }

        return new Result<Role>(Result.ResultStatus.SUCCESS.status, "修改成功！！！！！！！", role);
    }

    @Override
    @Transactional
    public Result<Role> deleteRole(int roleId) {
        roleDao.deleteRole(roleId);
        return new Result<Role>(Result.ResultStatus.SUCCESS.status, "");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public PageInfo<Role> getRoles(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo(
                Optional.ofNullable(roleDao.getRolesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public List<Role> getRolesByUserId(int userId) {
        return roleDao.getRoleByUserId(userId);
    }

    @Override
    public List<Role> getRolesByResourceId(int resourceId) {
        return roleDao.getRolesByResourceId(resourceId);
    }

    @Override
    public Role getRoleById(int roleId) {
        return roleDao.getRoleById(roleId);
    }
}
