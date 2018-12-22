package com.xudc.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xudc.dubbo.api.service.DemoService;

/**
 * @author xudc
 * @date 2018/12/22 15:36
 */

@Service
public class DemoServiceImpl implements DemoService {
    /**
     * say Hello.
     *
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
