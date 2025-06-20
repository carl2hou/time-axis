package com.time.axis.dao;

import com.time.axis.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description user_info
 * @author carl
 * @date 2025-06-20
 */
@Mapper

public interface UserInfoMapper {

    /**
    * 新增
    * @author carl
    * @date 2025/06/20
    **/
    int insert(UserInfo userInfo);

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
    int update(UserInfo userInfo);

    /**
    * 查询 根据主键 id 查询
    * @author carl
    * @date 2025/06/20
    **/
    UserInfo load(int id);

    /**
    * 查询 分页查询
    * @author carl
    * @date 2025/06/20
    **/
    List<UserInfo> pageList(int offset, int pagesize);

    /**
    * 查询 分页查询 count
    * @author carl
    * @date 2025/06/20
    **/
    int pageListCount(int offset,int pagesize);

}