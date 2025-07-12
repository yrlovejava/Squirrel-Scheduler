package com.squirrel.core.task;

import java.lang.reflect.Method;

/**
 * Methods annotated with @Schedule are registered as a task.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午8:25
 */
public class MethodTask implements SquirrelTask{

    private final String taskName;
    private final Method method;
    private final Object target;

    public MethodTask(Method method,Object target,String taskName) {
        this.method = method;
        this.target = target;
        this.taskName = taskName;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void execute() {
        try {
            method.invoke(target);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute method task", e);
        }
    }
}
