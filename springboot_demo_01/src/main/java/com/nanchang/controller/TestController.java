package com.nanchang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 阿伟
 * @Date: 2019/9/19 9:49
 * @@Description:
 **/
@Controller
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "index";
    }

}
