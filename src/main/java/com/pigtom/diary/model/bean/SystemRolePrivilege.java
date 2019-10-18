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
 * 系统角色权限关联表
 * </p>
 *
 * @author pigtom
 * @since 2019-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemRolePrivilege implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * system_role表主键ID
     */
    private Long roleId;

    /**
     * system_privilege表主键ID
     */
    private Long privilegeId;

    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long createId;


}
