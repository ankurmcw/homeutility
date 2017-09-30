package com.mcw.homeutility.dto;

import com.mcw.homeutility.entity.Milk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by renuka on 30/9/17.
 */

public class MilkDto {

    private int price;

    private String date;

    private int quantity;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

    public MilkDto() {}

    public MilkDto(Milk milk) {
        this.date = dateFormat.format(milk.getDate());
        this.price = milk.getPrice();
        this.quantity = milk.getQuantity();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
