package com.nanchang.controller;

import com.nanchang.mapper.UserMapper;
import com.nanchang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 阿伟
 * @Date: 2019/9/20 21:42
 * @@Description:
 **/
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();   //获取cookies
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("token")){    //找到token的cookie
                String token=cookie.getValue();     //获取它的值
                User user=userMapper.findByToKen(token);   //调用数据库，比较这个值是否在数据库中存在
                if(user!=null){
                    request.getSession().setAttribute("user",user);   //如果存在，则保存该token的用户的登陆信息
                }
                break;
            }
        }
        return "index";
    }
}
