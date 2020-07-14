package com.example.demo.entity;

import java.io.Serializable;

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
@TableName("worklog")
public class Worklog implements Serializable {


    private Integer id;

    private String username;

    private Integer year;

    private Integer month;

    private Integer day;

    private String title;

    private String description;

    private String logtime;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    @Override
    public String toString() {
        return "Worklog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", logtime='" + logtime + '\'' +
                '}';
    }
}
