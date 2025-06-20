package com.time.axis.service;

import com.time.axis.model.EventInfo;

/**
 * @description event_info
 * @author carl
 * @date 2025-06-20
 */
public interface EventInfoService {

    /**
    * 新增
    */
    public Integer insert(EventInfo eventInfo);

    /**
    * 删除
    */
    public Integer delete(int id);

    /**
    * 更新
    */
    public Integer update(EventInfo eventInfo);

    /**
    * 根据主键 id 查询
    */
    public EventInfo load(int id);


}