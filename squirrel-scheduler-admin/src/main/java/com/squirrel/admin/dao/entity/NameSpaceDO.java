package com.squirrel.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.squirrel.admin.dao.entity.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NameSpaceDO
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tbl_namespace")
public class NameSpaceDO extends BaseDO {

    public static final Integer DEFAULT_NAMESPACE_ID = 1;

    private Integer id; // 主键ID
    private String name; // 命名空间名称
    private String description; // 命名空间描述
}
