package com.psfd.springboot.entity;


import java.io.Serializable;
import java.util.Date;

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
@TableName("tb_operator")
@AllArgsConstructor
@NoArgsConstructor
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "op_username")
    private String opUsername;

    private String opPassword;

    private Integer opPrivilege;

    private String opAddress;

    private String opName;

    private String opTelephone;

    private String opMobile;

    private String opZip;

    private Date opCreatetime;


}
