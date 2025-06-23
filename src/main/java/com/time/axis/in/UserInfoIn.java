package com.time.axis.in;

import lombok.Data;

import java.io.Serializable;

/**
 * @author carl
 */
@Data
public class UserInfoIn implements Serializable {
    private static final long serialVersionUID = 9034377114292749432L;

    private Integer userId;
}
