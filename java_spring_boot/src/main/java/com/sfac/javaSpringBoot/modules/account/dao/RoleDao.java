package com.sfac.javaSpringBoot.modules.account.dao;

import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
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

    @Insert("insert into role (role_name) values (#{roleName})")
    @Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
    void insertRole(Role role);

    @Insert("insert role(role_name) value(#{roleName})")
    @Options(useGeneratedKeys=true, keyProperty="roleId", keyColumn="role_id")
    void addRole(Role role);

    @Update("update role set role_name = #{roleName} where role_id = #{roleId}")
    void updateRole(Role role);

    @Delete("delete from role where role_id = #{roleId}")
    void deleteRole(int roleId);

    @Select("<script>" +
            "select * from role "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + "and role_name like '%${keyWord}%' "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + "order by role_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Role> getRolesBySearchVo(SearchVo searchVo);

    @Select("select * from role role left join role_resource roleResource "
            + "on role.role_id = roleResource.role_id where roleResource.resource_id = #{resourceId}")
    List<Role> getRolesByResourceId(int resourceId);

    @Select("select * from role where role_id=#{roleId}")
    Role getRoleById(int roleId);

    @Select("select * from role where role_name = #{roleName}  limit 1")
    Role getRoleByRoleName(String roleName);


}

