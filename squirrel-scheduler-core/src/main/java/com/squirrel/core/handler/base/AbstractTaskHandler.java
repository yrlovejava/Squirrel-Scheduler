package com.squirrel.core.handler.base;

import com.squirrel.core.task.SquirrelTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract task handler class
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午8:17
 */
public abstract class AbstractTaskHandler implements ITaskHandler{

    private static final Logger logger = LoggerFactory.getLogger(AbstractTaskHandler.class);

    /**
     * Common validation logic for tasks
     * @param task task
     */
    protected void validateTask(SquirrelTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
    }

    @Override
    public void handle(SquirrelTask task) {
        logger.warn("handle task");
    }

    /**
     * Abstract shutdown method to be implemented by subclasses
     */
    public abstract void shutdown();
}
