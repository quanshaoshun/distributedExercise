package com.psfd.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Data
@TableName("tb_balancement")
@AllArgsConstructor
@NoArgsConstructor
public class Balancement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bm_id", type = IdType.AUTO)
    private Integer bmId;

    private Integer bmCheckinorderId;

    private Integer bmGuestId;

    private String bmType;

    private Double bmTotalrate;

    private Double bmPaidmoney;

    private Double bmReceivmoney;

    private Date bmCreatetime;

    private String bmOperator;

    private String bmPaymentmodel;

    private String bmRemark;


}
