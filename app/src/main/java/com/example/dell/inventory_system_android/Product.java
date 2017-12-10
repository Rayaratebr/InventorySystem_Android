package com.example.dell.inventory_system_android;

/**
 * Created by dell on 12/10/2017.
 */

class Product {

    private int id;
    private double standard_price;
    private int quantity;
    private String unit;//TODO: check what is it

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(double standard_price) {
        this.standard_price = standard_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", standard_price=" + standard_price +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
