package com.squirrel.admin.controller;

import com.squirrel.admin.convention.result.Result;
import com.squirrel.admin.convention.result.Results;
import com.squirrel.admin.dto.req.ScheduleTaskSaveReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskUpdateReqDTO;
import com.squirrel.admin.service.ScheduleTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:23
 */
@RestController
@RequestMapping("/task/v1")
@RequiredArgsConstructor
public class ScheduleTaskController {

    private final ScheduleTaskService scheduleTaskService;

    @PostMapping("/")
    public Result<Void> saveTask(ScheduleTaskSaveReqDTO requestParam) {
        scheduleTaskService.saveTask(requestParam);
        return Results.success();
    }

    @PutMapping("/")
    public Result<Void> updateTask(ScheduleTaskUpdateReqDTO requestParam) {
        scheduleTaskService.updateTask(requestParam);
        return Results.success();
    }
}
