package com.squirrel.core.handler.base;

import com.squirrel.core.config.ThreadPoolConfig;
import com.squirrel.core.handler.DedicatedThreadTaskHandler;
import com.squirrel.core.handler.ThreadPoolTaskHandler;

/**
 * Factory class for creating TaskHandler instances.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/7/12 12:07
 */
public class TaskHandlerFactory {
    public static ITaskHandler createThreadPoolHandler(ThreadPoolConfig config) {
        return new ThreadPoolTaskHandler(config);
    }

    public static ITaskHandler createDedicatedThreadHandler() {
        return new DedicatedThreadTaskHandler();
    }

    // Allow users to register custom handlers
    public static ITaskHandler createCustomHandler(ITaskHandler customHandler) {
        return customHandler;
    }
}
