package com.sfac.javaSpringBoot.modules.account.dao;

import com.sfac.javaSpringBoot.modules.account.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleDao {
    @Select("select * from role r left join user_role us " +
            "on r.role_id = us.role_id where us.user_id=#{userId}")
    List<Role> getRoleByUserId(int userId);

    @Select("select * from role")
    List<Role> getRoles();
}

