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
@TableName("branch_route")
public class BranchRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分店-路线id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long branchId;

    private Long routeId;

    private String branchName;
    
    private String routeName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
