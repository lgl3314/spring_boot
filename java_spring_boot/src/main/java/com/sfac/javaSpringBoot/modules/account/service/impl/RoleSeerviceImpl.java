package com.sfac.javaSpringBoot.modules.account.service.impl;


import com.sfac.javaSpringBoot.modules.account.dao.RoleDao;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.service.RoleSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
