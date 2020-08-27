package com.sfac.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.service.RoleSeervice;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/roles", consumes = "application/json")
    public PageInfo<Role> getRoles(@RequestBody SearchVo searchVo) {
        return roleSeervice.getRoles(searchVo);
    }

    @PostMapping(value = "/role", consumes = "application/json")
    public Result<Role> insertRole(@RequestBody Role role) {
        return roleSeervice.editRole(role);
    }

    @PutMapping(value = "/role", consumes = "application/json")
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleSeervice.editRole(role);
    }

    @RequestMapping("/role/{roleId}")
    public Role getRole(@PathVariable int roleId) {
        return roleSeervice.getRoleById(roleId);
    }

    @DeleteMapping("/role/{roleId}")
    public Result<Role> deletRole(@PathVariable int roleId) {
        return roleSeervice.deleteRole(roleId);
    }
}
