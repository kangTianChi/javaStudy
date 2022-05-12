package com.qdc.demoeurekaconsumer;

import com.qdc.demoeurekaconsumer.utils.HttpClientWithBasicAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.util.HashMap;
import java.util.Map;
@EnableHystrix//开启熔断器
@EnableEurekaClient
@SpringBootApplication
public class DemoEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaConsumerApplication.class, args);
        HttpClientWithBasicAuth auth=new HttpClientWithBasicAuth();
        String url="http://localhost:8080/oauth/token";
        Map<String,String> formData=new HashMap<String,String>();
        formData.put("grant_type","client_credentials");
        formData.put("scope","all");
        String result=auth.send(url,"test","123456",formData);
        System.out.println(result);
    }

}
