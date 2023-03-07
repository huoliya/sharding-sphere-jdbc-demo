package com.sinocontrol.sharding.sphere.jdbc.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @create: 2021-12-09 00:35
 * @description: mybatis-plus赋值插件
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Object createTime = this.getFieldValByName("createTime", metaObject);
        if (null == createTime) {
            this.setFieldValByName("createTime", localDateTime, metaObject);
        }
        Object updateTime = this.getFieldValByName("updateTime", metaObject);
        if (null == updateTime) {
            this.setFieldValByName("updateTime", localDateTime, metaObject);
        }
        Object isDelete = this.getFieldValByName("isDelete", metaObject);
        if (null == isDelete) {
            this.setFieldValByName("isDelete", 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}