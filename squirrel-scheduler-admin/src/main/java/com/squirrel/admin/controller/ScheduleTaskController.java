package com.squirrel.admin.controller;

import com.squirrel.admin.convention.result.Result;
import com.squirrel.admin.convention.result.Results;
import com.squirrel.admin.dto.req.ScheduleTaskDeleteReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskSaveReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskUpdateReqDTO;
import com.squirrel.admin.service.ScheduleTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:23
 */
@RestController
@RequestMapping("/task/v1")
@RequiredArgsConstructor
@Tag(name = "定时任务管理", description = "定时任务管理")
public class ScheduleTaskController {

    private final ScheduleTaskService scheduleTaskService;

    @PostMapping("/")
    @Operation(description = "保存任务")
    public Result<Void> saveTask(@RequestBody ScheduleTaskSaveReqDTO requestParam) {
        scheduleTaskService.saveTask(requestParam);
        return Results.success();
    }

    @PutMapping("/")
    @Operation(description = "更新任务")
    public Result<Void> updateTask(@RequestBody ScheduleTaskUpdateReqDTO requestParam) {
        scheduleTaskService.updateTask(requestParam);
        return Results.success();
    }

    @DeleteMapping("/")
    @Operation(description = "删除任务")
    public Result<Void> deleteTask(ScheduleTaskDeleteReqDTO requestParam) {
        scheduleTaskService.deleteTask(requestParam);
        return Results.success();
    }
}
