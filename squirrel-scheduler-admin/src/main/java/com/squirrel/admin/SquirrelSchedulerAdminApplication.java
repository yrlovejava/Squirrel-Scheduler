package com.squirrel.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the SquirrelSchedulerAdminApplication.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.squirrel.admin.dao.mapper")
public class SquirrelSchedulerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquirrelSchedulerAdminApplication.class, args);
    }
}
