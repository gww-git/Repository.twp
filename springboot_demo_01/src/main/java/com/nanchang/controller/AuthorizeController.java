package com.nanchang.controller;

import com.alibaba.fastjson.JSON;
import com.nanchang.Provider.GithubProvider;
import com.nanchang.dto.AccessTokenDTO;
import com.nanchang.dto.GithubUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @GetMapping("/callback")
   public String callback(@RequestParam(name = "code") String code,
                          @RequestParam(name = "state") String state, HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("1eaff18138a754ff3a28");
        accessTokenDTO.setClient_secret("ef70b548f90af94dfd6edf3d3665bc88143d6ec7");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user != null)
            request.getSession().setAttribute("user", user);
            return "redirect:/";
    }
}





























