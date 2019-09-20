package com.nanchang.controller;

import com.alibaba.fastjson.JSON;
import com.nanchang.Provider.GithubProvider;
import com.nanchang.dto.AccessTokenDTO;
import com.nanchang.dto.GithubUser;
import com.nanchang.mapper.UserMapper;
import com.nanchang.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: 阿伟
 * @Date: 2019/9/19 17:47
 * @@Description:
 **/
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${github.client.secret}")
    private String clientsecret;

    @Value("${github.client.uri}")
    private String redirecturi;

    @Autowired
    private UserMapper userMapper;    //实体类的接口注入

    @GetMapping("/callback")
   public String callback(@RequestParam(name = "code") String code,
                          @RequestParam(name = "state") String state, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("1eaff18138a754ff3a28");
        accessTokenDTO.setClient_secret("ef70b548f90af94dfd6edf3d3665bc88143d6ec7");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubuser = githubProvider.getUser(accessToken);
        if (githubuser != null) {
            User user=new User();
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setName(githubuser.getName());
            String token=UUID.randomUUID().toString();    //生成一个唯一的标识，来表示用户登陆状态的cookie
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubuser.getBio());
            userMapper.InsertUser(user);
            response.addCookie(new Cookie("token",token));   //将成功登陆的用户，生成的cookie保存在浏览器里
            return "redirect:/";
        }
        else {
            return "redirect:/";
        }
    }
}





























