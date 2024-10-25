package com.simplemessage.cloudsimple.Infrastructure.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.simplemessage.cloudsimple.Infrastructure.db.entity.CreationEntity;
import com.simplemessage.cloudsimple.Infrastructure.db.entity.ModificationEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 初始化语句
 *
 * @version 1.0
 * @author: benxiong.hu
 * @createAt: 2023/09/19 23:26:43
 * @modifyAt: 2023/09/19 23:26:43
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 添加初始化
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Assert.notNull(metaObject, "metaObject must not null");
        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof CreationEntity) {
            this.strictInsertFill(metaObject, "createdTime", () -> new Date(), Date.class);
            this.strictInsertFill(metaObject, "createdName", () -> "systemInsert", String.class);
            this.strictInsertFill(metaObject, "createdId", () -> 1L, Long.class);
        }
        initUpdatedOrDeleted(metaObject, originalObject);

    }

    /**
     * 修改初始化
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Assert.notNull(metaObject, "metaObject must not null");
        Object originalObject = metaObject.getOriginalObject();
        initUpdatedOrDeleted(metaObject, originalObject);
    }

    /**
     * 初始化修改or删除
     *
     * @param metaObject
     * @param originalObject
     */
    private void initUpdatedOrDeleted(MetaObject metaObject, Object originalObject) {
        if (originalObject instanceof ModificationEntity) {
            this.setFieldValByName("lastModifiedTime", new Date(), metaObject);
            this.setFieldValByName("lastModifiedName", "systemUpdate", metaObject);
            this.setFieldValByName("lastModifiedId", 1L, metaObject);
        }

    }

}
