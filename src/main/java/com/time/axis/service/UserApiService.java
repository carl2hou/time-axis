package com.time.axis.service;

import com.time.axis.in.UserCode2sessionIn;

/**
 * @author carl
 */
public interface UserApiService {
    String getToken(UserCode2sessionIn in);
}
