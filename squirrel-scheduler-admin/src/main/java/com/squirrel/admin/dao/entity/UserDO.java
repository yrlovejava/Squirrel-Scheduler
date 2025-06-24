package com.squirrel.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.squirrel.admin.dao.entity.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 * @author xiaobai
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tbl_user")
public class UserDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private int id;
    private String username;		// 账号
    private String password;		// 密码
}
