package com.time.axis.controller;

import lombok.Data;

/**
 * @author carl
 */
@Data
public class BabyInfoIn implements java.io.Serializable{
    private static final long serialVersionUID = 2973507484988376435L;

    private Integer id;

    private String babyName;

    private Integer sex;

    private String pic;

    private Long userId;

    private String familyName;
}
