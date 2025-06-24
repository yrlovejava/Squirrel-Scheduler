package com.squirrel.admin.service;

import com.squirrel.admin.dto.req.UserLoginReqDTO;
import com.squirrel.admin.dto.resp.UserLoginRespDTO;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:46
 */
public interface UserLoginService {

    UserLoginRespDTO login(UserLoginReqDTO requestParam);
}
