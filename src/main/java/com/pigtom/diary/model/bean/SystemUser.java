package com.pigtom.diary.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private String name;

    /**
     * 用户密码
     */
    private String authenticationString;

    /**
     * 密码是否被锁
     */
    private Boolean passwordLocked;

    /**
     * 账号是否被锁
     */
    private Boolean accountLocked;

    /**
     * 用户描述
     */
    private String description;

    /**
     * 密码是否过期
     */
    private Boolean passwordExpired;

    /**
     * 密码生存周期, 单位是天，最长31天
     */
    private Integer passwordLifetime;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 创建者ID
     */
    private Long creatorId;

    /**
     * 最后更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 最后更新人ID
     */
    private Long updatedId;
}
