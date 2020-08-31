package com.hqyj.springcloud.springCloudClientCest.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.springcloud.springCloudClientCest.modules.common.vo.Result;
import com.hqyj.springcloud.springCloudClientCest.modules.common.vo.SearchVo;
import com.hqyj.springcloud.springCloudClientCest.modules.test.dao.CityDao;
import com.hqyj.springcloud.springCloudClientCest.modules.test.entity.City;
import com.hqyj.springcloud.springCloudClientCest.modules.test.service.CityService;
import com.sun.xml.internal.ws.wsdl.writer.document.Port;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Value("${server.port}")
    private int port;

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesById(int countryId) {
        System.out.println("Port = " + port);
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
