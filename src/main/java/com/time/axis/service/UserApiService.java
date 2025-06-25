package com.time.axis.service;

import com.time.axis.controller.BabyInfoIn;
import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.in.UserInfoIn;
import com.time.axis.model.Baby;
import com.time.axis.model.UserInfo;
import com.time.axis.out.UserCode2sessionOut;

/**
 * @author carl
 */
public interface UserApiService {
    UserCode2sessionOut getToken(UserCode2sessionIn in);

    UserInfo getUserInfo(UserInfoIn in);

    Boolean babyInfoSave(BabyInfoIn in);

    Baby getBabyInfo(BabyInfoIn in);
}
