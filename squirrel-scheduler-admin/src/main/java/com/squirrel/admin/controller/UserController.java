package com.squirrel.admin.controller;

import com.squirrel.admin.convention.result.Result;
import com.squirrel.admin.convention.result.Results;
import com.squirrel.admin.dto.req.UserLoginReqDTO;
import com.squirrel.admin.dto.resp.UserLoginRespDTO;
import com.squirrel.admin.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:26
 */
@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public Result<UserLoginRespDTO> login(UserLoginReqDTO requestParam){
        return Results.success(userLoginService.login(requestParam));
    }
}
