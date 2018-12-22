package com.xudc.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xudc.dubbo.api.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDubboConsumerApplicationTests {

    @Reference
    private DemoService demoService;

    @Test
    public void contextLoads() {
        System.err.println(demoService.sayHello("andy"));
    }

}

