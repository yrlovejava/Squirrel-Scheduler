package com.squirrel.admin.dao.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * The basic system schema.
 * All other DO classes inherit from this class.
 *
 * @author xiaobai
 * @version 1.0.0
 */
@Data
public class BaseDO {

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;// 创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;// 更新时间
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;// 删除标志 0：未删除，1：已删除
}
