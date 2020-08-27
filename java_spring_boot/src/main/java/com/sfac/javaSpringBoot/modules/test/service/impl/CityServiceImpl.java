package com.sfac.javaSpringBoot.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.aspect.ServiceAnnotation;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.dao.CityDao;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    @ServiceAnnotation(value = "aaa")
    public List<City> getCitiesById(int countryId) {
        //链式表达
        return Optional.ofNullable(cityDao.getCitiesById(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> cityByVo(int countryId, SearchVo searchVo) {
       searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(
                Optional.ofNullable(cityDao.getCitiesById(countryId))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City>  getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(
                Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }


    @Override
    @Transactional
    public Result<City> insertcity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertcity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,
                "Insert success.",city);
    }


    @Override
    @Transactional(noRollbackFor = ArithmeticException.class)
    public Result<City> updatecity(City city) {
        cityDao.updatecity(city);
        //int i=1/0;
        return new Result<>(Result.ResultStatus
                .SUCCESS.status, "Update success",city);
    }

    @Override
    @Transactional
    public Result<Object> delecity(int cityId) {
        cityDao.delecity(cityId);
        return new Result<Object>(Result.ResultStatus
                .SUCCESS.status,"Delete success");
    }


}
