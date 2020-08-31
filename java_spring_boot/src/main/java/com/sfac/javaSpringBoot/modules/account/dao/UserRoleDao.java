package com.sfac.javaSpringBoot.modules.account.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRoleDao {

    @Delete("delete from user_role where user_id = #{userId}")
    void deleteUserRoleByUserId(int userId);

    @Insert("insert user_role(role_id, user_id) value(#{roleId}, #{userId})")
    void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
