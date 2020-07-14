package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@EqualsAndHashCode(callSuper = false)
@TableName("sms")
public class Sms implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private Integer sender;

    private Integer message;

    private Integer sendtime;

    @TableField("isRead")
    private String isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getSendtime() {
        return sendtime;
    }

    public void setSendtime(Integer sendtime) {
        this.sendtime = sendtime;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sender=" + sender +
                ", message=" + message +
                ", sendtime=" + sendtime +
                ", isRead='" + isRead + '\'' +
                '}';
    }
}
