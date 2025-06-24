package com.squirrel.admin.convention.result;

import com.squirrel.admin.convention.errorcode.BaseErrorCode;
import com.squirrel.admin.convention.exception.AbstractException;

import java.util.Optional;

/**
 * Result utils
 *
 * @author xiaobai
 * @version 1.0.0
 * @since 2025/6/24 下午9:34
 */
public final class Results {
    /**
     * 构造成功响应
     *
     * @return 成功的Result
     */
    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }

    /**
     * 构造带返回数据的成功响应
     *
     * @param data 需要携带的数据
     * @param <T> 数据的泛型
     * @return 成功的Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setData(data);
    }

    /**
     * 构建服务端失败响应
     *
     * @return 失败的Result
     */
    public static Result<Void> failure() {
        return new Result<Void>()
                .setCode(BaseErrorCode.SERVICE_ERROR.code())
                .setMessage(BaseErrorCode.SERVICE_ERROR.message());
    }

    /**
     * 通过 {@link AbstractException} 构建失败响应
     *
     * @param abstractException 应用内抛出的异常
     * @return 失败的Result
     */
    public static Result<Void> failure(AbstractException abstractException) {
        String errorCode = Optional.ofNullable(abstractException.getErrorCode())
                .orElse(BaseErrorCode.SERVICE_ERROR.code());
        String errorMessage = Optional.ofNullable(abstractException.getErrorMessage())
                .orElse(BaseErrorCode.SERVICE_ERROR.message());
        return new Result<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }

    /**
     * 通过 errorCode、errorMessage 构建失败响应
     *
     * @param errorCode 错误码
     * @param errorMessage 错误信息
     * @return 失败的Result
     */
    public static Result<Void> failure(String errorCode, String errorMessage) {
        return new Result<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }
}
