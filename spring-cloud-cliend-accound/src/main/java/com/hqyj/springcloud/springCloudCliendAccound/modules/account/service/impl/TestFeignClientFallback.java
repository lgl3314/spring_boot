package com.hqyj.springcloud.springCloudCliendAccound.modules.account.service.impl;

import com.hqyj.springcloud.springCloudCliendAccound.modules.account.entity.City;
import com.hqyj.springcloud.springCloudCliendAccound.modules.account.service.TestFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestFeignClientFallback implements TestFeignClient {
    @Override
    public List<City> getCityByCountryId(int countryId) {
        return new ArrayList<>();
    }
}
