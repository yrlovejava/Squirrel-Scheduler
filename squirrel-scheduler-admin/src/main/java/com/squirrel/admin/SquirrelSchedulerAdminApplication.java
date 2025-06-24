package com.squirrel.admin;

import com.squirrel.admin.handler.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * The main class for the SquirrelSchedulerAdminApplication.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.squirrel.admin.dao.mapper")
@Import(value = GlobalExceptionHandler.class)
public class SquirrelSchedulerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquirrelSchedulerAdminApplication.class, args);
    }
}
