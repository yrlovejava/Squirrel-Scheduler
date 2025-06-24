package com.squirrel.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User login response DTO
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespDTO {

    private String token;
}
