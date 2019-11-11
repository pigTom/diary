package com.pigtom.diary.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pigtom.diary.util.ExcelColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author pigtom
 * @since 2019-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    @ExcelColumn(value = "用户名称", columnIndex = 0)
    private String name;

    /**
     * 用户密码
     */
    @ExcelColumn(value = "用户密码", columnIndex = 1, columnWidth = 5500)
    private String authenticationString;

    /**
     * 密码是否被锁
     */
    @ExcelColumn(value = "密码是否被锁", columnIndex = 2, columnWidth = 4500)
    private boolean passwordLocked;

    /**
     * 账号是否被锁
     */
    @ExcelColumn(value = "账号是否被锁", columnIndex = 3, columnWidth = 4500)
    private boolean accountLocked;

    /**
     * 用户描述
     */
    @ExcelColumn(value = "用户描述", columnIndex = 4, columnWidth = 6500)
    private String description;

    /**
     * 密码是否过期
     */
    @ExcelColumn(value = "密码是否过期", columnIndex = 5, columnWidth = 4500)
    private boolean passwordExpired;

    /**
     * 密码生存周期, 单位是天，最长31天
     */
    @ExcelColumn(value = "密码生存周期", columnIndex = 6, columnWidth = 4500)
    private Integer passwordLifetime;

    /**
     * 创建时间
     */
    @ExcelColumn(value = "创建时间", columnIndex = 7, columnWidth = 4500)
    private Date createTime;

    /**
     * 创建者ID
     */
    private Long createId;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 最后更新人ID
     */
    private Long updateId;
}
