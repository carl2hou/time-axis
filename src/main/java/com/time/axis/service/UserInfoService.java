package com.time.axis.service;

import com.time.axis.model.UserInfo;

import java.util.Map;

/**
 * @description user_info
 * @author carl
 * @date 2025-06-20
 */
public interface UserInfoService {

    /**
    * 新增
    */
    public Integer insert(UserInfo userInfo);

    /**
    * 删除
    */
    public Integer delete(int id);

    /**
    * 更新
    */
    public Integer update(UserInfo userInfo);

    /**
    * 根据主键 id 查询
    */
    public UserInfo load(int id);

    /**
     * 根据主键 openId 查询
     */
    public UserInfo loadByOpenId(String openId);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}