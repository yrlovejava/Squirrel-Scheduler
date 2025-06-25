package com.squirrel.admin.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Common return result
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:29
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    public static final String SUCCESS_CODE = "0";

    private String code;

    private String message;

    private T data;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
