package com.psfd.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
@TableName("tb_checkinitem")
@AllArgsConstructor
@NoArgsConstructor
public class Checkinitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cim_id", type = IdType.AUTO)
    private Integer cimId;

    private Integer checkinorderCioId;

    private Integer roomRmId;

    private Double cimPrctprice;

    private Double cimDiscount;

    private Date cimIndatetime;

    private Date cimOutdatetime;

    private String cimState;


}
