package com.time.axis.dao;

import com.time.axis.model.EventInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description event_info
 * @author carl
 * @date 2025-06-20
 */
@Mapper
public interface EventInfoMapper {

    /**
    * 新增
    * @author carl
    * @date 2025/06/20
    **/
    int insert(EventInfo eventInfo);

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
    int update(EventInfo eventInfo);

    /**
    * 查询 根据主键 id 查询
    * @author carl
    * @date 2025/06/20
    **/
    EventInfo load(int id);

    /**
    * 查询 分页查询
    * @author carl
    * @date 2025/06/20
    **/
    List<EventInfo> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize);

    /**
    * 查询 分页查询 count
    * @author carl
    * @date 2025/06/20
    **/
    int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize);

}