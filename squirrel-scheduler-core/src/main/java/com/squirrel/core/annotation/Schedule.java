package com.squirrel.core.annotation;

import java.lang.annotation.*;

/**
 * The annotation Schedule is used to registry a task for Squirrel-Scheduler.
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午3:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Schedule {

    /**
     * Task name used to identify the task.
     * @return Task name, with a default value of an empty string.
     */
    String value() default "";

    /**
     * Task description used to describe the function or purpose of the task.
     * @return Task description, with a default value of an empty string.
     */
    String description() default "";
}
