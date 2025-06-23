package com.time.axis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description t_baby
 * @author carl
 * @date 2025-06-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Baby implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;

    /**
    * 宝宝名称
    */
    private String name;

    /**
    * 性别
    */
    private Integer sex;

    /**
    * 头像
    */
    private String pic;

    /**
    * 关联的用户id
    */
    private Long userId;

    /**
    * 宝宝对用户的称谓
    */
    private String familyName;

    /**
    * create_time
    */
    private Date createTime;

    /**
    * update_time
    */
    private Date updateTime;



}