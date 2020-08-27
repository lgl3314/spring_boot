package com.sfac.javaSpringBoot.modules.test.dao;

import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {
    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCitiesById(int countryId);

    //脚本查询
    @Select("<script>" +
            "select * from m_city "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by city_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    @Insert("insert into m_city (city_name, local_city_name, country_id, date_created) " +
            "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
    @Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
    void insertcity(City city);

    @Update("update m_city set city_name =#{cityName} where city_id=#{cityId}")
    void updatecity(City city);


    @Delete("Delete from m_city where city_id = #{cityId}")
    void delecity(int cityId);
}
