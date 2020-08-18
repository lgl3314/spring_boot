package com.sfac.javaSpringBoot.modules.test.controller;

import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import com.sfac.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 192.168.18.62:8085/test/....
 * */
@Controller
@RequestMapping("/test")
public class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/testDese")
    @ResponseBody
    public String testDese() {
        return "good";
    }

    @Value("${server.port}")
    private int port;

    @Value("${com.name}")
    private String name;

    @Value("${com.age}")
    private int age;

    @Value("${com.desc}")
    private String desc;

    @Value("${com.random}")
    private String random;

    @Autowired
    private ApplicationTest applicationTest;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    //单个文件上传
    /*
     * 127.0.0.1/test/file
     * */
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        if (file.isEmpty()){

            redirectAttributes.addFlashAttribute("message","请选择需要上传的文件。");
            return "redirect:/test/index";
        }

        try {
            String destFilePath = "D:\\file\\"+ file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);
            redirectAttributes.addFlashAttribute("message","上传成功。");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","上传失败。");
        }


        return "redirect:/test/index";
    }

    //多个文件上传
    /**
     * 127.0.0.1/test/files ---- post
     */
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes) {
        boolean empty = true;
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String destFilePath = "D:\\file\\" + file.getOriginalFilename();
                File destFile = new File(destFilePath);
                file.transferTo(destFile);
                empty = false;
            }
            if (empty) {
                redirectAttributes.addFlashAttribute("message","请选择需要下载的文件。");
            } else {
                redirectAttributes.addFlashAttribute("message","下载成功。");
            }

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","下载失败。");
        }

        return "redirect:/test/index";
    }


    //下载
    /*
    * 127.0.0.1/test/file
    * */
    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        Resource resource = null;
        try {
            resource = new UrlResource(Paths.get("D:\\file1\\" + fileName).toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                String.format("attachment; filename=\"%s\"", resource.getFilename()))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    * 127.0.0.1/test/index
    * */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {

        int countryId = 522;
        List<City> cities=cityService.getCitiesById(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryById(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo",
                "/upload/1111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/city");
        //modelMap.addAttribute("template", "test/index");

       return "index";
   }

   @GetMapping("/index2")
    public String testIndex2Page(ModelMap modelMap){
        modelMap.addAttribute("template", "test/index2");
        return "index";
    }

    @GetMapping("/config")
    @ResponseBody
    public String configTest() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("--------------")
                .append(name).append("--------------")
                .append(age).append("--------------")
                .append(desc).append("--------------")
                .append(random).append("--------------").append("<br>");
        sb.append(applicationTest.getPort()).append("--------------")
                .append(applicationTest.getName()).append("--------------")
                .append(applicationTest.getAge()).append("--------------")
                .append(applicationTest.getDesc()).append("--------------")
                .append(applicationTest.getRandom()).append("--------------").append("<br>");
        return sb.toString();

    }


    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("this is trace log");
        LOGGER.debug("this is debug log");
        LOGGER.info("this is info log");
        LOGGER.warn("this is warn log");
        LOGGER.error("this is error log");
        return "this is log";
    }

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request,
                           @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc." + paramValue + "==" + paramValue2;
    }
}