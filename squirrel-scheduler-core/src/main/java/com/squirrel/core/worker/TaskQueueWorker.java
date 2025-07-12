package com.squirrel.core.worker;

import com.squirrel.core.task.SquirrelTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The task queue worker is responsible for retrieving tasks from the task queue and executing them.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/7/12 11:52
 */
public class TaskQueueWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskQueueWorker.class);
    private final String taskName;
    private final BlockingQueue<SquirrelTask> taskQueue;
    private final AtomicBoolean workerRunning = new AtomicBoolean(false);

    /**
     * Construct a new task queue worker.
     *
     * @param taskName task name
     */
    public TaskQueueWorker(String taskName) {
        this.taskName = taskName;
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    /**
     * Add tasks to the task queue only when the worker is running.
     * Use the put method to ensure that the task will definitely be added to the queue.
     *
     * @param task 要添加的任务
     */
    public void addTask(SquirrelTask task) {
        if (task == null) {
            logger.warn("Attempted to add a null task to TaskQueueWorker: {}", taskName);
            return;
        }
        if (workerRunning.get()) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Interrupted while adding task to queue for TaskQueueWorker: {}", taskName, e);
            }
        } else {
            logger.warn("TaskQueueWorker {} is not running, task will not be added.", taskName);
        }
    }

    /**
     * Stop the worker, set the running status to false and clear the task queue.
     */
    public void stop() {
        workerRunning.set(false);
        taskQueue.clear();
        logger.info("TaskQueueWorker {} has been stopped and task queue cleared.", taskName);
    }

    /**
     * Start the worker, and continuously retrieve tasks from the task queue and execute them.
     */
    @Override
    public void run() {
        workerRunning.set(true);
        logger.info("TaskQueueWorker {} started.", taskName);
        try {
            while (workerRunning.get()) {
                SquirrelTask task = taskQueue.take();
                executeTask(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("TaskQueueWorker {} interrupted.", taskName);
        } finally {
            workerRunning.set(false);
            logger.info("TaskQueueWorker {} stopped.", taskName);
        }
    }

    /**
     * Perform individual tasks and capture any exceptions that may occur during the task execution process.
     *
     * @param task 要执行的任务
     */
    private void executeTask(SquirrelTask task) {
        try {
            task.execute();
        } catch (Exception e) {
            logger.error("Task execution failed for TaskQueueWorker {}: {}", taskName, e.getMessage(), e);
        }
    }
}