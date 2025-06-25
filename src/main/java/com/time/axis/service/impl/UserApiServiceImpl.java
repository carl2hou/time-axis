package com.time.axis.service.impl;

import com.time.axis.controller.BabyInfoIn;
import com.time.axis.emnus.CommonConstant;
import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.in.UserInfoIn;
import com.time.axis.model.Baby;
import com.time.axis.model.UserInfo;
import com.time.axis.out.UserCode2sessionOut;
import com.time.axis.service.BabyService;
import com.time.axis.service.UserApiService;
import com.time.axis.service.UserInfoService;
import com.time.axis.service.WechatService;
import com.time.axis.util.EmptyCheckUtil;
import com.time.axis.util.SM4Util;
import com.time.axis.util.StringUtils;
import com.time.axis.vo.WechatUserCode2sessionOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

    @Resource
    BabyService babyService;

    @Override
    public UserCode2sessionOut getToken(UserCode2sessionIn in) {
        boolean isInvalid = EmptyCheckUtil.isAnyBlank(in.getCode());
        if(isInvalid) {
            throw new RuntimeException("参数错误");
        }
        WechatUserCode2sessionOut wechatUserCode2session = wechatService.getWechatUserCode2session(in);
        if(wechatUserCode2session == null){
            throw new RuntimeException("获取session_key失败,code:" + in.getCode());
        }

        String openid = wechatUserCode2session.getOpenid();
        UserInfo userInfo = userInfoService.loadByOpenId(openid);
        if(userInfo != null){
            if(StringUtils.isNotBlank(in.getPic()) || StringUtils.isNotBlank(in.getNickName())){
                userInfo.setNickName(in.getNickName());
                userInfo.setPic(in.getPic());
                userInfo.setLoginTime(new Date());
                userInfoService.update(userInfo);
            }
            return UserCode2sessionOut.builder().openid(openid).token(userInfo.getToken()).userId(userInfo.getId()).build();
        }
        try {
            String token = SM4Util.encrypt(openid,CommonConstant.SM4_KEY, CommonConstant.SM4_MODE_ECB);
            UserInfo user = new UserInfo(null, in.getNickName(), openid, wechatUserCode2session.getSession_key(), wechatUserCode2session.getUnionid(),
                    null, token, in.getPic(), CommonConstant.DEL_NO, null, null, null);
            Integer insertCount = userInfoService.insert(user);
            if(insertCount > 0){
                return UserCode2sessionOut.builder().openid(openid).token(token).userId(userInfo.getId()).build();
            }
        } catch (Exception e) {
            log.error("生成token失败",e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(UserInfoIn in) {
        UserInfo userInfo = userInfoService.load(in.getUserId());
        if(userInfo == null){
            log.error("用户信息不存在");
            throw new RuntimeException("用户信息不存在");
        }
        return userInfo;
    }

    @Override
    public Boolean babyInfoSave(BabyInfoIn in) {
        boolean isInvalidBlank = EmptyCheckUtil.isAnyBlank(in.getBabyName(), in.getFamilyName());
        boolean isInvalidEmpty = EmptyCheckUtil.isAnyEmpty(in.getUserId());
        if(isInvalidBlank || isInvalidEmpty) {
            throw new RuntimeException("参数错误");
        }
        Baby baby = new Baby(null,in.getBabyName(),in.getSex(),in.getPic(),in.getUserId(),in.getFamilyName(),null,null);
        Integer insert = babyService.insert(baby);
        return insert > 0;
    }

    @Override
    public Baby getBabyInfo(BabyInfoIn in) {
        boolean isInvalid = EmptyCheckUtil.isAnyEmpty(in.getId());
        if(isInvalid) {
            throw new RuntimeException("参数错误");
        }
        Baby baby = babyService.load(in.getId());
        if(baby == null){
            log.error("宝宝信息不存在");
            throw new RuntimeException("宝宝信息不存在");
        }
        return baby;
    }
}
