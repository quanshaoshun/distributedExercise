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

    private String sender;

    private String message;

    private String sendtime;

    @TableField("isRead")
    private int isRead;

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", sendtime='" + sendtime + '\'' +
                ", isRead='" + isRead + '\'' +
                '}';
    }
}
