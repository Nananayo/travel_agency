package com.lvxing.travel_agency.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 高权限用户id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private String gender;
    /**
     * 用户名
     */
    private String username;

    /**
     * 登录用密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 所属分社/总店
     */
    private String belong;

    /**
     * 权限
     */
    private String power;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 身份证号
     */
    private Long idCart;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


}
