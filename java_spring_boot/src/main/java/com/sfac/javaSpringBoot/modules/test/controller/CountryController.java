package com.sfac.javaSpringBoot.modules.test.controller;

import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private CountryService countryService;
    /*
     * 127.0.0.1/api/country/522-------get
     * */
    @GetMapping("/country/{countryId}")
    public Country getCountryById(@PathVariable int countryId) {
        return countryService.getCountryById(countryId);
    }

    /*
     * 127.0.0.1/api/country/?countryName=china
     * */
    @GetMapping("/country")
    public Country getCountryByName(@RequestParam String countryName) {
        return countryService.getCountryByName(countryName);
    }


    /*
     127.0.0.1/api/redis/country/522
* */
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId){
        return countryService.mograteCountryByRedis(countryId);
    }
}
