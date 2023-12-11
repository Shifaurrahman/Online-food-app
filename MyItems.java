package com.example.finalsupport;


import java.io.Serializable;

public class MyItems implements Serializable {

    private String foodname;
    private String price;
    private String duration;

    public MyItems(String foodname, String price, String duration) {
        this.foodname = foodname;
        this.price = price;
        this.duration = duration;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }
}
