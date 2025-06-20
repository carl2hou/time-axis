package com.time.axis.service;

import com.time.axis.model.Baby;

/**
 * @description t_baby
 * @author carl
 * @date 2025-06-20
 */
public interface BabyService {

    /**
    * 新增
    */
    public Integer insert(Baby tBaby);

    /**
    * 删除
    */
    public Integer delete(int id);

    /**
    * 更新
    */
    public Integer update(Baby tBaby);

    /**
    * 根据主键 id 查询
    */
    public Baby load(int id);


}