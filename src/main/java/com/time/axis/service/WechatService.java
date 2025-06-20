package com.time.axis.service;

import com.time.axis.in.UserCode2sessionIn;
import com.time.axis.vo.WechatUserCode2sessionOut;

/**
 * @author carl
 */
public interface WechatService {

    WechatUserCode2sessionOut getWechatUserCode2session(UserCode2sessionIn in);

}
