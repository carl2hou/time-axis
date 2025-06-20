package com.time.axis.in;

import lombok.Data;

import java.io.Serializable;

/**
 * @author carl
 */
@Data
public class UserCode2sessionIn implements Serializable {

    private static final long serialVersionUID = 1326424274428972875L;

    private String code;
}
