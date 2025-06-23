package com.time.axis.dao;

import com.time.axis.model.Baby;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description t_baby
 * @author carl
 * @date 2025-06-20
 */
@Mapper
public interface BabyMapper {

    /**
    * 新增
    * @author carl
    * @date 2025/06/20
    **/
    int insert(Baby tBaby);

    /**
    * 刪除
    * @author carl
    * @date 2025/06/20
    **/
    int delete(int id);

    /**
    * 更新
    * @author carl
    * @date 2025/06/20
    **/
    int update(Baby tBaby);

    /**
    * 查询 根据主键 id 查询
    * @author carl
    * @date 2025/06/20
    **/
    Baby load(int id);

    /**
    * 查询 分页查询
    * @author carl
    * @date 2025/06/20
    **/
    List<Baby> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
    * 查询 分页查询 count
    * @author carl
    * @date 2025/06/20
    **/
    int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);

}