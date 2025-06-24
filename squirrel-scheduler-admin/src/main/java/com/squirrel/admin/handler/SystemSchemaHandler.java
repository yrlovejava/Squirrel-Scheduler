package com.squirrel.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.squirrel.admin.constant.SystemConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The System schema fill interceptor.
 *
 * @author xiaobai
 * @version 1.0.0
 */
@Component
public class SystemSchemaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        // 插入时自动设置 delFlag 为 0
        this.strictInsertFill(metaObject, "delFlag", Integer.class, SystemConstant.NO_DEL_FLAG);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

}
