package com.squirrel.core.handler.base;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract task registry class
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午3:37
 */
public abstract class AbstractTaskExecutor implements TaskRegistry {

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

    /**
     * task handler repository
     */
    private static ConcurrentHashMap<String,ITaskHandler> tasks;// the map is used to store the task
    public static ITaskHandler getTask(String name) {
        return tasks.get(name);
    }

    public AbstractTaskExecutor(String adminAddress, String namespace, String address, String ip, int port) {
        this.adminAddress = adminAddress;
        this.namespace = namespace;
        this.address = address;
        this.ip = ip;
        this.port = port;
        tasks = new ConcurrentHashMap<>();
    }
}
