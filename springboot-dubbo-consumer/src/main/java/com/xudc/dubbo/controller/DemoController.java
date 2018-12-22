package com.xudc.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xudc.dubbo.api.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xudc
 * @date 2018/12/22 16:26
 */
@RestController
public class DemoController {

    @Reference
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello(String name){
        return demoService.sayHello(name);
    }
}
