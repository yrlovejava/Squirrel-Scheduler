package com.squirrel.core.executor.base;

import com.squirrel.core.annotation.Schedule;
import com.squirrel.core.config.ExecutorConfig;
import com.squirrel.core.handler.base.ITaskHandler;
import com.squirrel.core.log.LogFileAppender;
import com.squirrel.core.task.SquirrelTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Abstract task Executor
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午3:37
 */
public abstract class AbstractTaskExecutor implements Executor, ApplicationContextAware {

    private static final String SEPARATOR = ".";

    private static ApplicationContext applicationContext;
    private static ExecutorConfig config;

    /**
     * params
     */
    private String adminAddress;// admin web address
    private String address;// the address of the task
    private String ip;// the ip of the task
    private int port;// the port of the task

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddress = adminAddresses;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public static String getTaskName(String name){
        return config.getNamespace() + SEPARATOR + name;
    }

    /**
     * task handler repository
     * key -> namespace + task name
     * value -> namespace + task handler
     */
    private static ConcurrentHashMap<String, ITaskHandler> taskHandlerRepo;// the map is used to store the task
    public static ITaskHandler getHandler(String name) {
        String taskName = getTaskName(name);
        return taskHandlerRepo.get(taskName);
    }
    public static ITaskHandler registerTask(String name, ITaskHandler task) {
        String taskName = getTaskName(name);
        return taskHandlerRepo.put(taskName, task);
    }

    /**
     * task repository
     * key -> task name
     * value -> task
     */
    private static ConcurrentHashMap<String, SquirrelTask> taskRepo;// the map is used to store the task
    public static SquirrelTask getTask(String name) {
        return taskRepo.get(name);
    }
    public static SquirrelTask registerTask(String name, SquirrelTask task) {
        return taskRepo.put(name, task);
    }

    public AbstractTaskExecutor(String adminAddress, String address, String ip, int port,ExecutorConfig executorConfig) {
        this.adminAddress = adminAddress;
        this.address = address;
        this.ip = ip;
        this.port = port;
        taskHandlerRepo = new ConcurrentHashMap<>();
        taskRepo = new ConcurrentHashMap<>();
        config = executorConfig;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        AbstractTaskExecutor.applicationContext = applicationContext;
    }

    public void init() {
        // scan method with @Schedule annotation
        // todo register task
        Map<String, Object> beans = applicationContext.getBeansOfType(Object.class);
        for (Object bean : beans.values()) {
            Class<?> beanClass = bean.getClass();
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Schedule.class)) {
                    Schedule schedule = method.getAnnotation(Schedule.class);
                    // form IOC container to get the task handler
                    Class<?> handlerClass = schedule.handler();
                    Object handler = applicationContext.getBean(handlerClass);
                    if (handler instanceof ITaskHandler taskHandler) {
                        // register task
                        registerTask(schedule.value(), taskHandler);
                    }
                }
            }
        }
    }
}
