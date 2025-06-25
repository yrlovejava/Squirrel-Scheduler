package com.squirrel.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午9:04
 */
@Configuration
public class SquirrelSchedulerAutoConfig {

    @Bean
    public ExecutorConfig executorConfig(){
        return new ExecutorConfig();
    }

    @Bean
    public LogFileConfig logFileConfig(){
        return new LogFileConfig();
    }
}
