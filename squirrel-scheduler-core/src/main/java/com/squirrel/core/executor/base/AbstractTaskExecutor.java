package com.squirrel.core.executor.base;

import com.squirrel.core.annotation.Schedule;
import com.squirrel.core.config.ExecutorConfig;
import com.squirrel.core.handler.base.ITaskHandler;
import com.squirrel.core.log.LogFileAppender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Abstract task registry class
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午3:37
 */
public abstract class AbstractTaskExecutor implements Executor, ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static ExecutorConfig config;

    /**
     * params
     */
    private String adminAddress;// admin web address
    private String namespace;// namespace
    private String address;// the address of the task
    private String ip;// the ip of the task
    private int port;// the port of the task

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddress = adminAddresses;
    }
    public void setNamespace(String namespace) {this.namespace = namespace;}
    public void setAddress(String address) {
        this.address = address;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void register() {
        // scan method with @Schedule annotation
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

    @Override
    public void start() {
        // init log File
        LogFileAppender.initLogPath();
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        // clean task
        if(!taskHandlerRepo.isEmpty()){
            taskHandlerRepo.clear();
        }
    }

    /**
     * task handler repository
     */
    private static ConcurrentHashMap<String, ITaskHandler> taskHandlerRepo;// the map is used to store the task
    public static ITaskHandler getTask(String name) {
        return taskHandlerRepo.get(name);
    }
    public static ITaskHandler registerTask(String name, ITaskHandler task) {
        return taskHandlerRepo.put(name, task);
    }

    public AbstractTaskExecutor(String adminAddress, String namespace, String address, String ip, int port,ExecutorConfig executorConfig) {
        this.adminAddress = adminAddress;
        this.namespace = namespace;
        this.address = address;
        this.ip = ip;
        this.port = port;
        taskHandlerRepo = new ConcurrentHashMap<>();
        config = executorConfig;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        AbstractTaskExecutor.applicationContext = applicationContext;
    }
}
