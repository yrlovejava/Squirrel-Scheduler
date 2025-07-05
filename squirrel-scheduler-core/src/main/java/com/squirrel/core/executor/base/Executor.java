package com.squirrel.core.executor.base;

/**
 * A container performing a task can receive scheduling tasks and report its status to the scheduler.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午7:49
 */
public interface Executor {

    /**
     * receive task from the scheduler to execute
     */
    void receiveTask();

    /**
     * report task status to the scheduler
     */
    void reportStatus();
}
