package com.sfac.javaSpringBoot.modules.account.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.dao.ResourceDao;
import com.sfac.javaSpringBoot.modules.account.dao.RoleResourceDao;
import com.sfac.javaSpringBoot.modules.account.entity.Resource;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.service.ResourceService;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    @Transactional
    public Result<Resource> editResource(Resource resource) {
        Resource resourceTwmp=resourceDao.getResourceByPermission(resource.getPermission());
        if (resourceTwmp != null && resourceTwmp.getResourceId() != resource.getResourceId()) {
            return new Result<Resource>(Result.ResultStatus.FAILD.status, "不允许重复。");
        }

        // 添加 resource
        if (resource.getResourceId() > 0) {
            resourceDao.updateResource(resource);
        } else {
            resourceDao.addResource(resource);
        }

        // 添加 roleResource
        roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());
        if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
            for (Role role : resource.getRoles()) {
                roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
            }
        }

        return new Result<Resource>(Result.ResultStatus.SUCCESS.status, "添加成功", resource);
    }

    @Override
    @Transactional
    public Result<Resource> deleteResource(int resourceId) {
        roleResourceDao.deletRoleResourceByResourceId(resourceId);
        resourceDao.deleteResource(resourceId);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status, "删除成功");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public PageInfo<Resource> getResources(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo(
                Optional.ofNullable(resourceDao.getResourcesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }

    @Override
    public Resource getResourceById(int resourceId) {
        return resourceDao.getResourceById(resourceId);
    }
}
