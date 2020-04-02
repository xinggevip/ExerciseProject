package com.mp.compont;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class MyMateObjectHandler implements MetaObjectHandler {
    /**
     * setFieldValByName()既可以在insertFill，也可以在updateFill
     * 自动填充优化
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 判断是否有此字段，有则执行填充，无则不执行
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            System.out.println("insertFill=================");
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 如果用户没设置值则填充，否则不填充
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            System.out.println("updateFill=================");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

    }
}
