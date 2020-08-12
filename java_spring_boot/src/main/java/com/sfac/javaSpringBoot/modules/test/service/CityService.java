package com.sfac.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesById(int countryId);

    PageInfo<City> cityByVo(int countryId, SearchVo searchVo);

    PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);

    //添加
    Result<City> insertcity(City city);

    //修改
    Result<City> updatecity(City city);

    //删除
   Result<Object> delecity(int cityId);
}

