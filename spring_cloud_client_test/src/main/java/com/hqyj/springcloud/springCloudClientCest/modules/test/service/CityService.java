package com.hqyj.springcloud.springCloudClientCest.modules.test.service;


import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientCest.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientCest.modules.common.vo.SearchVo;
import com.hqyj.springcloud.springCloudClientCest.modules.test.entity.City;

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

