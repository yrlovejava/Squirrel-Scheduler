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

    public static final Long DEFAULT_EXECUTOR_TIMEOUT = 10000L;// 默认超时时间（单位：毫秒）
    public static final int DEFAULT_EXECUTOR_FAIL_RETRY_COUNT = 3;// 默认失败重试次数


    @TableId(type = IdType.AUTO)
    private Integer id; // 主键ID

    private Integer schedulerId;// 调度器ID
    private String name;// 定时任务名称
    private String description;// 定时任务的描述

    private String author;// 负责人
    private String emailList;// 报警邮箱列表，使用 "," 分隔

    private String cron;// cron表达式
    private Integer status;// 定时任务状态，0：禁用，1：启用 2: 运行中
    private Long lastRunTime;// 上次运行时间
    private Integer namespace;// 命名空间Id

    private String executorParam;// 执行器参数(json 格式)
    private Long executorTimeout;// 执行器超时时间（单位：ms）默认10000ms
    private Integer executorFailRetryCount;// 失败重试次数(默认为3次)
}
