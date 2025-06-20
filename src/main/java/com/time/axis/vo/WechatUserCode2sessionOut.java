package com.time.axis.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author carl
 */
@Data
public class WechatUserCode2sessionOut implements Serializable {

    private static final long serialVersionUID = -4660010508423022892L;

    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

}
