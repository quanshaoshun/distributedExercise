package com.example.demo.entity;

public class Schedule {
    int id;
    String years;
    String month;
    String date;
    String arrangecontent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrangecontent() {
        return arrangecontent;
    }

    public void setArrangecontent(String arrangecontent) {
        this.arrangecontent = arrangecontent;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", years='" + years + '\'' +
                ", month='" + month + '\'' +
                ", date='" + date + '\'' +
                ", arrangecontent='" + arrangecontent + '\'' +
                '}';
    }

}
