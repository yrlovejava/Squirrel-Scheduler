package com.squirrel.admin.controller;

import com.squirrel.admin.convention.result.Result;
import com.squirrel.admin.convention.result.Results;
import com.squirrel.admin.dto.req.UserLoginReqDTO;
import com.squirrel.admin.dto.resp.UserLoginRespDTO;
import com.squirrel.admin.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Tag(name = "用户管理", description = "用户管理")
public class UserController {

    private final UserLoginService userLoginService;

    @PostMapping("/login")
    @Operation(description = "用户登录")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam){
        return Results.success(userLoginService.login(requestParam));
    }
}
