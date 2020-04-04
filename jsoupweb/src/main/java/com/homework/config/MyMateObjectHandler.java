package com.homework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class MyMateObjectHandler implements MetaObjectHandler {
    /**
     * setFieldValByName()既可以在insertFill，也可以在updateFill
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insertFill=================");
        setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setInsertFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("updateFill=================");
        setUpdateFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
    }
}