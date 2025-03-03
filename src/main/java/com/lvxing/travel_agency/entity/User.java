package com.lvxing.travel_agency.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String openid;

    private String name;

    private String gender;

    private String phone;

    private Long idCard;

    private Integer status;
    private Integer power;


}
