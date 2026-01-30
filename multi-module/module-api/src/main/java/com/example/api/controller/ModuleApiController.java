package com.example.api.controller;

import com.example.common.MyStringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleApiController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        return MyStringUtil.greeting(name);
    }
}