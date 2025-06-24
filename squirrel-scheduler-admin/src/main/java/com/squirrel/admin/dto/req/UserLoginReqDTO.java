package com.squirrel.admin.dto.req;

import lombok.Data;

/**
 * User login request DTO
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:43
 */
@Data
public class UserLoginReqDTO {

    private String username;

    private String password;
}
