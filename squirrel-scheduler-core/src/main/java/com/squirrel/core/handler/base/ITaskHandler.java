package com.squirrel.core.handler.base;

import com.squirrel.core.task.SquirrelTask;

/**
 * Task handler interface.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午4:12
 */
public interface ITaskHandler {

    void handle(SquirrelTask task);
}
