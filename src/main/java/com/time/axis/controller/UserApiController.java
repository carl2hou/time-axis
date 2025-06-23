package com.time.axis.controller;

import com.time.axis.config.ApiResponse;
import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.in.UserInfoIn;
import com.time.axis.model.UserInfo;
import com.time.axis.service.UserApiService;
import com.time.axis.service.WechatService;
import com.time.axis.vo.WechatUserCode2sessionOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    @Resource
    UserApiService userApiService;

    /**
     * 根据code获取token
     * @param in
     * @return
     */
    @PostMapping("/token")
    public ApiResponse userToken(@RequestBody UserCode2sessionIn in){
        String token = userApiService.getToken(in);
        return ApiResponse.ok(token);
    }

    @PostMapping("/info")
    public ApiResponse getUserInfo(@RequestBody UserInfoIn in){
        UserInfo userInfo = userApiService.getUserInfo(in);
        if(userInfo != null){
            return ApiResponse.ok(userInfo);
        }
        throw new RuntimeException("用户信息不存在");
    }

    @PostMapping("/baby/save")
    public ApiResponse babyInfoSave(@RequestBody BabyInfoIn in){
        Boolean isSave = userApiService.babyInfoSave(in);
        return ApiResponse.ok(isSave);
    }



}
