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
@TableName("attractions")
public class Attractions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 景点id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String img;

    private String name;

    private String phone;

    private BigDecimal price;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 景点类型
     */
    private String type;

    private String desces;
    private String provinceName;
    private String cityName;
    private String districtName;

}
