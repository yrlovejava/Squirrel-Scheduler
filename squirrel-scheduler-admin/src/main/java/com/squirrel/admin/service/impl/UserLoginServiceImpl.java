package com.squirrel.admin.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.squirrel.admin.convention.exception.ClientException;
import com.squirrel.admin.dao.entity.UserDO;
import com.squirrel.admin.dao.mapper.UserMapper;
import com.squirrel.admin.dto.req.UserLoginReqDTO;
import com.squirrel.admin.dto.resp.UserLoginRespDTO;
import com.squirrel.admin.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:46
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserMapper userMapper;

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        // 1.参数检验
        if(requestParam == null || requestParam.getUsername() == null || requestParam.getPassword() == null){
            throw new ClientException("参数错误");
        }

        // 2.查询用户
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        UserDO userDO = userMapper.selectOne(queryWrapper);
        if(userDO == null) {
            throw new ClientException("用户不存在");
        }

        // 3.密码校验
        if(!userDO.getPassword().equals(requestParam.getPassword())) {
            throw new ClientException("密码错误");
        }

        // 4.生成token TODO JWT
        String token = UUID.fastUUID().toString();

        // 5.返回结果
        return UserLoginRespDTO.builder()
                .token(token)
                .build();
    }
}
