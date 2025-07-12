package com.squirrel.core.handler;

import com.squirrel.core.config.ThreadPoolConfig;
import com.squirrel.core.handler.base.AbstractTaskHandler;
import com.squirrel.core.task.SquirrelTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/7/12 11:40
 */
public class ThreadPoolTaskHandler extends AbstractTaskHandler {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTaskHandler.class);
    private static ThreadPoolExecutor threadPoolExecutor;
    private final ThreadPoolConfig config;

    // default values
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 10;
    private static final long DEFAULT_KEEP_ALIVE_TIME = 60;
    private static final int DEFAULT_QUEUE_CAPACITY = 100;

    public ThreadPoolTaskHandler(ThreadPoolConfig config) {
        this.config = config;
        // initialize thread pool
        initThreadPool();
    }

    private void initThreadPool() {
        int corePoolSize = config.getCorePoolSize() > 0 ? config.getCorePoolSize() : DEFAULT_CORE_POOL_SIZE;
        int maximumPoolSize = config.getMaximumPoolSize() > 0 ? config.getMaximumPoolSize() : DEFAULT_MAXIMUM_POOL_SIZE;
        long keepAliveTime = config.getKeepAliveTime() > 0 ? config.getKeepAliveTime() : DEFAULT_KEEP_ALIVE_TIME;

        ThreadFactory threadFactory = config.getThreadFactory();
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
            logger.info("Using default thread factory");
        }

        RejectedExecutionHandler rejectedExecutionHandler = config.getRejectedExecutionHandler();
        if (rejectedExecutionHandler == null) {
            rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
            logger.info("Using default rejected execution handler: CallerRunsPolicy");
        }

        try {
            threadPoolExecutor = new ThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(DEFAULT_QUEUE_CAPACITY),
                    threadFactory,
                    rejectedExecutionHandler
            );
            logger.info("ThreadPoolExecutor initialized with corePoolSize: {}, maximumPoolSize: {}, keepAliveTime: {}",
                    corePoolSize, maximumPoolSize, keepAliveTime);
        } catch (Exception e) {
            logger.error("Failed to initialize ThreadPoolExecutor", e);
            throw new RuntimeException("Failed to initialize ThreadPoolExecutor", e);
        }
    }

    @Override
    public void handle(SquirrelTask task) {
        try {
            validateTask(task);
            threadPoolExecutor.submit(task::execute);
            logger.debug("Task submitted to thread pool: {}", task.getClass().getSimpleName());
        } catch (RejectedExecutionException e) {
            logger.error("Task submission rejected: {}", task.getClass().getSimpleName(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error submitting task: {}", task.getClass().getSimpleName(), e);
            throw new RuntimeException("Error submitting task", e);
        }
    }

    @Override
    public void shutdown() {
        try {
            if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPoolExecutor.shutdownNow();
                    logger.warn("ThreadPoolExecutor did not terminate within 60 seconds, forcing shutdown");
                }
                logger.info("ThreadPoolExecutor shut down successfully");
            }
        } catch (InterruptedException e) {
            threadPoolExecutor.shutdownNow();
            Thread.currentThread().interrupt();
            logger.error("Interrupted while waiting for ThreadPoolExecutor to shut down", e);
        } catch (Exception e) {
            logger.error("Error shutting down ThreadPoolExecutor", e);
        }
    }
}

