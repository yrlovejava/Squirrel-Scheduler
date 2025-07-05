package com.squirrel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午8:55
 */
@ConfigurationProperties(prefix = "squirrel.scheduler.executor")
public class ExecutorConfig {

    private String namespace;
    public String getNamespace() {
        return namespace;
    }
}
