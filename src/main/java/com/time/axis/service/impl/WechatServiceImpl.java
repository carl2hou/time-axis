package com.time.axis.service.impl;

import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.service.WechatService;
import com.time.axis.vo.WechatUserCode2sessionOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author carl
 */
public class WechatServiceImpl implements WechatService {

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;

    @Override
    public WechatUserCode2sessionOut getWechatUserCode2session(UserCode2sessionIn in) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WechatUserCode2sessionOut> response = restTemplate.getForEntity(
            url + "?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}",
            WechatUserCode2sessionOut.class, appid, secret, in.getCode(),
            "authorization_code"
        );
        return response.getBody();
    }

}
