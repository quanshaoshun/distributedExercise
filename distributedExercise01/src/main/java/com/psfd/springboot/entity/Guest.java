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
@TableName("tb_guest")
@AllArgsConstructor
@NoArgsConstructor
public class Guest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "gt_id", type = IdType.AUTO)
    private Integer gtId;

    private String gtName;

    private String gtYpe;

    private String gtCadcatalog;

    private String gtCardId;

    private String gtCountry;

    private String gtAddress;

    private String gtZip;

    private String gtCompany;

    private String gtTelphone;

    private String gtMobile;

    private String gtGender;

    private String gtEmail;

    private Date gtCreatetime;


}
