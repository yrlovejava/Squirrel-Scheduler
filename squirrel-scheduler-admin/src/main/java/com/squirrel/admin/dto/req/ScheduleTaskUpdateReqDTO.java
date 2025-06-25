package com.squirrel.admin.dto.req;

import lombok.Data;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:40
 */
@Data
public class ScheduleTaskUpdateReqDTO {

    private Integer id;// 任务id
    private String name;// 任务名称
    private String cron;// 任务cron表达式
    private String description;// 任务描述
    private Integer namespace;// 任务命名空间
    private Integer schedulerId;// 调度器id
    private String executorParam;// 执行器参数(json 格式)
    private int executorTimeout;// 执行器超时时间（单位：秒）默认为3次
    private int executorFailRetryCount;// 失败重试次数
    private String author;// 负责人
    private String emailList;// 报警邮箱列表，使用 "," 分隔
    private Integer Status;// 任务状态
}
