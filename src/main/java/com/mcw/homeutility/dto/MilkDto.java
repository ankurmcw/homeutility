package com.mcw.homeutility.dto;

import com.mcw.homeutility.entity.Milk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.mcw.homeutility.utility.AppConstants.NO;
import static com.mcw.homeutility.utility.AppConstants.YES;

/**
 * Created by renuka on 30/9/17.
 */

public class MilkDto {

    private Integer price;

    private String date;

    private Integer quantity;

    private String delivered;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

    public MilkDto() {}

    public MilkDto(Milk milk) {
        this.date = dateFormat.format(milk.getDate());
        this.price = milk.getPrice();
        this.quantity = milk.getQuantity();
        this.delivered = milk.getDelivered()? YES: NO ;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }
}
