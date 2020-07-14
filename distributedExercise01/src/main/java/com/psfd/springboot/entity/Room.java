package com.psfd.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
@TableName("tb_room")
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int STAE_VACANT=0;      //空闲状态
    public static final int STAE_RESERVED=1;    //预定状态
    public static final int STAE_RENTED=2;      //租用状态
    public static final int STAE_CHECKOUT=3;    //结账状态
    public static final int STAE_CLEANED=4;     //清洁状态
    public static final int STAE_BLOCKED=5;     //锁房状态

    @TableId(value = "rm_id", type = IdType.AUTO)
    private Integer rmId;

    private String rmArea;

    private String rmLoor;

    private Double rmPrctprice;

    private String rmTelphone;

    private Integer rmState;

    private Integer rmAvailable;

    private String rmCatalog;

    private String rmPicture;

    private Double rmPrctdiscount;


}
