package com.time.axis.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description user_info
 * @author carl
 * @date 2025-06-20
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * nickeName
    */
    private String nickeName;

    /**
    * openid
    */
    private String openid;

    /**
    * session_key
    */
    private String sessionKey;

    /**
    * unionid
    */
    private String unionid;

    /**
    * phone
    */
    private String phone;

    /**
    * token
    */
    private String token;

    /**
     * 用户头像
     */
    private String pic;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

}