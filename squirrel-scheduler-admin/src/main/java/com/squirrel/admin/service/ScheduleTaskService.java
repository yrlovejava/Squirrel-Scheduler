package com.squirrel.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.squirrel.admin.dao.entity.ScheduleTaskDO;
import com.squirrel.admin.dto.req.ScheduleTaskDeleteReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskSaveReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskUpdateReqDTO;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:20
 */
public interface ScheduleTaskService extends IService<ScheduleTaskDO> {

    void saveTask(ScheduleTaskSaveReqDTO requestParam);

    void updateTask(ScheduleTaskUpdateReqDTO requestParam);

    void deleteTask(ScheduleTaskDeleteReqDTO requestParam);
}
