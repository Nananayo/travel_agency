package com.lvxing.travel_agency.entity;

import java.math.BigDecimal;
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
@TableName("route")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线路id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 路线名
     */
    private String name;

    /**
     * 图片
     */
    private Long img;

    /**
     * 路线
     */
    private String line;

    /**
     * 价钱
     */
    private BigDecimal price;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    /**
     * 地区
     */
    private String provinceName;
    private String cityName;
    private String districtName;


}
