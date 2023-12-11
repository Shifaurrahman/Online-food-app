package com.example.myfinalproject.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String tittle;
    private String description;
    private String picUrl;
    private double price;
    private int time;
    private int energy;
    private double score;
    private int numberinCart;

    public FoodDomain(String tittle, String description, String picUrl, double price, int time, int energy, double score) {
        this.tittle = tittle;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
        this.time = time;
        this.energy = energy;
        this.score = score;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}


