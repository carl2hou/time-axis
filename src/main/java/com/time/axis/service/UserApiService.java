package com.time.axis.service;

import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.in.UserInfoIn;
import com.time.axis.model.UserInfo;

/**
 * @author carl
 */
public interface UserApiService {
    String getToken(UserCode2sessionIn in);

    UserInfo getUserInfo(UserInfoIn in);
}
