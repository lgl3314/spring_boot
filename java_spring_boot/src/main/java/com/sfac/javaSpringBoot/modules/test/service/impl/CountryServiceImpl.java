package com.sfac.javaSpringBoot.modules.test.service.impl;

import com.sfac.javaSpringBoot.modules.test.dao.CountryDao;
import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import com.sfac.javaSpringBoot.utls.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Autowired
    private RedisUtils redisUtils;

     @Override
    public Country getCountryById(int countryId) {

        return countryDao.getCountryById(countryId);
    }
    @Override
    public Country getCountryByName(String countryName) {
        return countryDao.getCountryByName(countryName);
    }


    @Override
    public Country migrateCountryByRedis(int countryId) {
        Country country=countryDao.getCountryById(countryId);
        String countryKey = String.format("country%d" ,countryId);
       // redisTemplate.opsForValue().set(countryKey,country);
        redisUtils.set(countryKey,country);
        //return (Country) redisTemplate.opsForValue().get(countryKey);
        return (Country) redisUtils.get(countryKey);
    }
}
