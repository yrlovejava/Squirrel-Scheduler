package com.squirrel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午8:55
 */
@ConfigurationProperties(prefix = "squirrel.scheduler.executor")
public class ExecutorConfig {

    private int threadnum;

    private int maxsize;

    public int getThreadnum() {return threadnum;}
    public void setThreadnum(int threadnum) {this.threadnum = threadnum;}
    public int getMaxsize() {return maxsize;}
    public void setMaxsize(int maxsize) {this.maxsize = maxsize;}
}
