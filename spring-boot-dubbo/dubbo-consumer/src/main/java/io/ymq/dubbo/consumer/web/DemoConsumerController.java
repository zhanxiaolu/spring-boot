package io.ymq.dubbo.consumer.web;

/**
 * 描述:
 *
 * @author yanpenglei
 * @create 2018-07-25 17:25
 **/
import com.alibaba.dubbo.config.annotation.Reference;

import io.ymq.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoConsumerController {

    @Resource
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }
}