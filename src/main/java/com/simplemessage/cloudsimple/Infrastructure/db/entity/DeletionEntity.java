package com.simplemessage.cloudsimple.Infrastructure.db.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 删除
 * @version 1.0
 * @author: benxiong.hu
 * @createAt: 2023/09/19 23:24:46
 * @modifyAt: 2023/09/19 23:24:46
 */
@Data
public class DeletionEntity extends ModificationEntity {
    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    /**
     * 创建者Id
     */
    @TableField(fill = FieldFill.UPDATE)
    private String deletedId;

    /**
     * 创建者姓名
     */
    @TableField(fill = FieldFill.UPDATE)
    private String deletedName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date deletedTime;
}
