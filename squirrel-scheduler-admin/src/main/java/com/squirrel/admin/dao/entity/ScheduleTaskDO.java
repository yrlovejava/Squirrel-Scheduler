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
 * ScheduleTask
 *
 * @author xiaobai
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tbl_schedule_task")
public class ScheduleTaskDO extends BaseDO {
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键ID

    private Integer schedulerId;// 执行器ID
    private String name;// 定时任务名称
    private String description;// 定时任务的描述

    private String author;// 负责人
    private String emailList;// 报警邮箱列表，使用 "," 分隔

    private String corn;// cron表达式
    private Integer status;// 定时任务状态，0：禁用，1：启用
}
