package com.qdc.demoeurekaconsumerfeign.service;

import org.springframework.stereotype.Component;

@Component
public class FeignClientFallBack implements UserClient{
    @Override
    public String hello() {
        return "";
    }

    @Override
    public String hello(int sleep_seconds) {
        return "容错保护机制已开启";
    }
}
