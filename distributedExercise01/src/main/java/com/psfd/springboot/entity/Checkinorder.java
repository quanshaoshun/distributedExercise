package com.psfd.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@TableName("tb_checkinorder")
@AllArgsConstructor
@NoArgsConstructor
public class Checkinorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cio_id", type = IdType.AUTO)
    private Integer cioId;

    private Integer cioRmId;

    private String cioGuestname;

    private Integer cioMannumber;

    private String cioGuestcardcatalog;

    private String cioGuesttype;

    private String cioGroupname;

    private String cioGuestcardid;

    private String cioCause;

    private String cioState;

    private Date cioIndatetime;

    private Date cioPreoutdatetime;

    private Date cioPrctoutdatetime;

    private String cioPaymentmodel;

    private Double cioPaidmoney;

    private String cioIsreservid;

    private String cioOperator;

    private String cioGuestgender;

    private Double cioTotalrate;

    private Double cioBedrate;


}
