package com.sfac.javaSpringBoot.modules.account.controller;

import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.service.RoleSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleSeervice roleSeervice;

    /*
    * 127.0.0.1/api/roles
    * */
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleSeervice.getRoles();
    }
}
