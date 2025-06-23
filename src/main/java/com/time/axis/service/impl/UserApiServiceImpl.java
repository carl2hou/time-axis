package com.time.axis.service.impl;

import com.time.axis.emnus.CommonConstant;
import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.model.UserInfo;
import com.time.axis.service.UserApiService;
import com.time.axis.service.UserInfoService;
import com.time.axis.service.WechatService;
import com.time.axis.util.SM4Util;
import com.time.axis.util.StringUtils;
import com.time.axis.vo.WechatUserCode2sessionOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author carl
 */
@Service
@Slf4j
public class UserApiServiceImpl implements UserApiService {

    @Resource
    WechatService wechatService;

    @Resource
    UserInfoService userInfoService;

    @Override
    public String getToken(UserCode2sessionIn in) {
        WechatUserCode2sessionOut wechatUserCode2session = wechatService.getWechatUserCode2session(in);
        if(wechatUserCode2session == null){
            log.error("获取session_key失败");
        }

        String openid = wechatUserCode2session.getOpenid();
        UserInfo userInfo = userInfoService.loadByOpenId(openid);
        if(userInfo != null){
            if(StringUtils.isNotBlank(in.getPic()) || StringUtils.isNotBlank(in.getNickName())){
                userInfo.setNickName(in.getNickName());
                userInfo.setPic(in.getPic());
                userInfoService.update(userInfo);
            }
            return userInfo.getToken();
        }
        String sm4Key = null;
        try {
            sm4Key = SM4Util.generateKey();
            String token = SM4Util.encrypt(openid,sm4Key, CommonConstant.SM4_MODE_CBC);
            UserInfo user = new UserInfo(null, in.getNickName(), openid, wechatUserCode2session.getSession_key(), wechatUserCode2session.getUnionid(),
                    null, token, in.getPic(), CommonConstant.DEL_NO, null, null, null);
            Integer insertCount = userInfoService.insert(user);
            if(insertCount > 0){
                return token;
            }
        } catch (Exception e) {
            log.error("生成token失败",e);
            throw new RuntimeException(e);
        }
        return null;
    }

}
