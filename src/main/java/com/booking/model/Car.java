package com.booking.model;

public class Car {
    private String type;
    private Integer capacity;
    private String supplier;
    private Integer price;

    public Car(String reqType, Integer reqCapacity, Integer reqPrice, String reqSupplier) {
        type = reqType;
        capacity = reqCapacity;
        price = reqPrice;
        supplier = reqSupplier;
    }
    
    //getter methods
    public String getSupplier() {
        return supplier;
    }

    public String getType() {
        return type;
    }
    
    public Integer getPrice() {
        return price;
    }

    //setter method
    public void setPrice(Integer reqPrice) {
        price = reqPrice;
    }
}