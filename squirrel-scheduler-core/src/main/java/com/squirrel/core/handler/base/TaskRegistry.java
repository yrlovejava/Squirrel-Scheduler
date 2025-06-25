package com.squirrel.core.handler.base;

import com.squirrel.core.annotation.Schedule;

import java.lang.reflect.Method;

/**
 * Task register interface.
 * Register tasks to the scheduled task scheduling platform
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午3:34
 */
public interface TaskRegistry {

    /**
     * register task
     */
    void register();

    /**
     * Destroy the task registry.
     * This method should be called when the application context is closed to release resources.
     */
    void destroy();
}
