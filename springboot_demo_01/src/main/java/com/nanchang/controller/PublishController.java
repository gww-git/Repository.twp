package com.nanchang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 阿伟
 * @Date: 2019/9/20 23:35
 * @@Description:
 **/
@Controller
public class PublishController {

    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }
}
