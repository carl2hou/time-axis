package com.time.axis.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description event_info
 * @author carl
 * @date 2025-06-20
 */
@Data
public class EventInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;

    /**
    * 1_喂养、2_换尿布、3_补剂、4_提问、5_睡眠、6_身高体重、20_自定义
    */
    private Integer type;

    /**
    * 子类型
    */
    private Integer subType;

    /**
    * 主要内容_json
    */
    private String content;

    /**
    * 关联宝宝id
    */
    private Long babyId;

    /**
    * create_time
    */
    private Date createTime;

    /**
    * update_time
    */
    private Date updateTime;

}