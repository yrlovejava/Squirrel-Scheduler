package com.squirrel.admin.common.enums;

import lombok.Getter;

/**
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/25 下午2:32
 */
@Getter
public enum TaskStatusEnum {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用"),
    RUNNING(2, "运行中"),

    ;

    private final int code;
    private final String desc;

    TaskStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
