package com.squirrel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Log file config.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午8:03
 */
@ConfigurationProperties(prefix = "squirrel.scheduler.log")
public class LogFileConfig {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
