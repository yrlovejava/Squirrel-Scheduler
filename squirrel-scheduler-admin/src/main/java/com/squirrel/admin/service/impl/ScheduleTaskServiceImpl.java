package com.squirrel.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.squirrel.admin.common.constant.SystemConstant;
import com.squirrel.admin.common.enums.TaskStatusEnum;
import com.squirrel.admin.convention.exception.ClientException;
import com.squirrel.admin.convention.exception.ServiceException;
import com.squirrel.admin.dao.entity.ScheduleTaskDO;
import com.squirrel.admin.dao.mapper.ScheduleTaskMapper;
import com.squirrel.admin.dto.req.ScheduleTaskDeleteReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskSaveReqDTO;
import com.squirrel.admin.dto.req.ScheduleTaskUpdateReqDTO;
import com.squirrel.admin.service.ScheduleTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.squirrel.admin.dao.entity.ScheduleTaskDO.DEFAULT_EXECUTOR_FAIL_RETRY_COUNT;
import static com.squirrel.admin.dao.entity.ScheduleTaskDO.DEFAULT_EXECUTOR_TIMEOUT;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleTaskServiceImpl extends ServiceImpl<ScheduleTaskMapper, ScheduleTaskDO> implements ScheduleTaskService {

    @Override
    public void saveTask(ScheduleTaskSaveReqDTO requestParam) {
        ScheduleTaskDO scheduleTaskDO = new ScheduleTaskDO();
        BeanUtils.copyProperties(requestParam,scheduleTaskDO);
        // 填充默认值
        scheduleTaskDO.setStatus(TaskStatusEnum.DISABLE.getCode());
        scheduleTaskDO.setLastRunTime(System.currentTimeMillis());
        scheduleTaskDO.setExecutorTimeout(scheduleTaskDO.getExecutorTimeout() == null ? DEFAULT_EXECUTOR_TIMEOUT : scheduleTaskDO.getExecutorTimeout());
        scheduleTaskDO.setExecutorFailRetryCount(scheduleTaskDO.getExecutorFailRetryCount() == null? DEFAULT_EXECUTOR_FAIL_RETRY_COUNT : scheduleTaskDO.getExecutorFailRetryCount());

        // 查询是否存在同名任务
        LambdaQueryWrapper<ScheduleTaskDO> queryWrapper = new LambdaQueryWrapper<>(ScheduleTaskDO.class)
                .eq(ScheduleTaskDO::getName, scheduleTaskDO.getName())
                .eq(ScheduleTaskDO::getNamespace, scheduleTaskDO.getNamespace())
                .eq(ScheduleTaskDO::getDelFlag, SystemConstant.NO_DEL_FLAG);
        ScheduleTaskDO one = getOne(queryWrapper);
        if (one != null) {
            throw new ClientException("任务名称已存在");
        }

        try {
            save(scheduleTaskDO);
        } catch (Exception e) {
            log.error("[ScheduleTaskServiceImpl]saveTask fail, requestParam: {}", requestParam,e);
            throw new ServiceException("保存任务失败");
        }
    }

    @Override
    public void updateTask(ScheduleTaskUpdateReqDTO requestParam) {
        ScheduleTaskDO scheduleTaskDO = new ScheduleTaskDO();
        BeanUtils.copyProperties(requestParam, scheduleTaskDO);

        try {
            updateById(scheduleTaskDO);
        } catch (Exception e) {
            log.error("[ScheduleTaskServiceImpl]updateTask fail, requestParam: {}", requestParam, e);
            throw new ServiceException("更新任务失败");
        }
    }

    @Override
    public void deleteTask(ScheduleTaskDeleteReqDTO requestParam) {
        // 检验参数
        if (requestParam.getId() == null) {
            throw new ClientException("任务ID不能为空");
        }

        // 删除任务
        try {
            boolean success = removeById(requestParam.getId());
            if (!success) {
                log.warn("[ScheduleTaskServiceImpl]deleteTask fail, requestParam: {}", requestParam);
            }
        } catch (Exception e) {
            log.error("[ScheduleTaskServiceImpl]deleteTask fail, requestParam: {}", requestParam, e);
            throw new ServiceException("删除任务失败");
        }
    }
}
