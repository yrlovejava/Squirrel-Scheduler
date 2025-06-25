package com.squirrel.core.executor.base;

import com.squirrel.core.handler.base.ITaskHandler;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午7:49
 */
public interface Executor {

    void register();
    void start();
    void stop();
    void destroy();
}
