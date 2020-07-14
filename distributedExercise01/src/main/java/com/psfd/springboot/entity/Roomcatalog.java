package com.psfd.springboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Data
@TableName("tb_roomcatalog")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Roomcatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rc_id",type = IdType.AUTO)
    private Integer rcId;

    private String rcName;

    private Integer rcBednumber;

    private Double rcPreprice;

    private Double rcPrediscount;

    private Double rcHourbaseprice;

    private Double rcPerhourprice;


}
