package com.squirrel.core.handler;

import com.squirrel.core.handler.base.AbstractTaskHandler;
import com.squirrel.core.task.SquirrelTask;
import com.squirrel.core.worker.TaskQueueWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * DedicatedThreadTaskHandler assigns a dedicated thread per task type for sequential execution.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/7/12 11:52
 */
public class DedicatedThreadTaskHandler extends AbstractTaskHandler {
    private static final Logger logger = LoggerFactory.getLogger(DedicatedThreadTaskHandler.class);
    private final ConcurrentHashMap<String, TaskQueueWorker> workersByTaskType;
    private final AtomicBoolean running;

    public DedicatedThreadTaskHandler() {
        this.workersByTaskType = new ConcurrentHashMap<>();
        this.running = new AtomicBoolean(true);
    }

    @Override
    public void handle(SquirrelTask task) {
        // Check if the processor is currently running
        if (!running.get()) {
            logger.warn("DedicatedThreadTaskHandler is not running, task {} will not be processed.", task.getTaskName());
            return;
        }
        validateTask(task);
        String taskName = task.getTaskName();
        try {
            // Obtain or create a task queue worker
            TaskQueueWorker worker = workersByTaskType.computeIfAbsent(taskName, k -> {
                TaskQueueWorker newWorker = new TaskQueueWorker(taskName);
                Thread workerThread = new Thread(newWorker);
                workerThread.setName("Task-Worker-" + taskName);
                workerThread.start();
                logger.info("Started new worker thread for task type: {}", taskName);
                return newWorker;
            });
            worker.addTask(task);
        } catch (Exception e) {
            logger.error("Failed to handle task {}: {}", taskName, e.getMessage(), e);
        }
    }

    @Override
    public void shutdown() {
        if (!running.compareAndSet(true, false)) {
            logger.warn("DedicatedThreadTaskHandler is already shut down.");
            return;
        }
        logger.info("Shutting down DedicatedThreadTaskHandler...");
        workersByTaskType.values().forEach(worker -> {
            try {
                worker.stop();
            } catch (Exception e) {
                logger.error("Error stopping worker: {}", e.getMessage(), e);
            }
        });
        workersByTaskType.clear();
        logger.info("DedicatedThreadTaskHandler shut down successfully.");
    }
}