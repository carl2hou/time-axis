package com.time.axis.out;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author carl
 */
@Data
@Builder
public class UserCode2sessionOut implements Serializable {

    private static final long serialVersionUID = 1326424274428972875L;

    private String token;

    private String openid;

    /**
     * id
     */
    private Integer userId;
}
