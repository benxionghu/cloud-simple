package com.simplemessage.cloudsimple.Infrastructure.db.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * 修改
 *
 * @author benxionghu
 * @version 1.0
 * @createAt: 2023/09/19 23:22:25
 * @modifyAt: 2023/09/19 23:22:25
 */
@Data
public class ModificationEntity extends CreationEntity {
    /**
     * 修改者Id
     */
    @LastModifiedBy
    @TableField(fill = FieldFill.UPDATE)
    private String lastModifiedId;

    /**
     * 修改者姓名
     */
    @TableField(fill = FieldFill.UPDATE)
    private String lastModifiedName;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @TableField(fill = FieldFill.UPDATE)
    private Date lastModifiedTime;
}
