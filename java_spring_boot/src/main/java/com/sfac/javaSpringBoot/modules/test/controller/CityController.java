package com.sfac.javaSpringBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.commo.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import javafx.scene.shape.VLineTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city/{countryId}")
    public List<City> getCitiesById(@PathVariable int countryId){
        return cityService.getCitiesById(countryId);
    }


    /**
     * 127.0.0.1/api/city/522 --- post
     * {"currentPage":1,"pageSize":5}
     */
    @PostMapping(value = "/city/{countryId}", consumes = "application/json")
    public PageInfo<City> cityByVo(@PathVariable int countryId, @RequestBody SearchVo searchVo){
        return cityService.cityByVo(countryId,searchVo);
    }

     /**
     * 127.0.0.1/api/city --- post
     * {"currentPage":"1","pageSize":"5","keyWord":"Sh","orderBy":"city_name","sort":"desc"}
     *
     */
     @PostMapping(value = "/city", consumes = "application/json")
     public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo) {
         return cityService.getCitiesBySearchVo(searchVo);
     }

    /**
     * 127.0.0.1/api/city1 --- post
     * {"cityName":"test1","localCityName":"freeCity","countryId":"522"}
     *
     */
    @PostMapping(value = "/city1", consumes = "application/json")
    public Result<City> insertcity(@RequestBody City city){
         return cityService.insertcity(city);
    }


    /**
     * 127.0.0.1/api/city1 ---- put
     * "cityId"="2259",cityName"="bbbb"
            */
    @PutMapping(value = "/city1", consumes = "application/x-www-form-urlencoded")
    public Result<City> updatecity(@ModelAttribute City city){
        return cityService.updatecity(city);
    }

    /**
     * 127.0.0.1/api/city1/2259
     *
     */
    @DeleteMapping("/city1/{cityId}")
    public Result<Object> delecity(@PathVariable int cityId){
        return cityService.delecity(cityId);
    }
}

