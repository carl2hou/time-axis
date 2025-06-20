package com.time.axis.controller;

import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.service.WechatService;
import com.time.axis.vo.WechatUserCode2sessionOut;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Resource
    WechatService wechatService;

    /**
     * 根据code获取微信用户session
     * @param in
     * @return
     */
    public String userCode2session(@RequestBody UserCode2sessionIn in){
        WechatUserCode2sessionOut wechatUserCode2session = wechatService.getWechatUserCode2session(in);
        if(wechatUserCode2session != null){
            return wechatUserCode2session.getSession_key();
        }

        //mk 生成一条用户记录，用户授权后更新其昵称和头像
        throw new RuntimeException("获取session_key失败");
    }

}
