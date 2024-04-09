package com.simplemessage.cloudsimple.Infrastructure.db.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

/**
 * 创建
 * @Author: benxiong.hu
 * @CreateAt: 2023/09/19 22:59:18
 * @ModifyAt: 2023/09/19 22:59:18
 * @Version 1.0
 */
@Data
public class CreationEntity {

    /**
     * 创建者Id
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdId;

    /**
     * 创建者名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdName;

    /**
     * 创建时间
     */
    @CreatedDate
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdTime;
}
