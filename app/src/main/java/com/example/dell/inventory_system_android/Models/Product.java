package com.example.dell.inventory_system_android.Models;


public class Product extends Parent{

    private int id;
    private double standard_price;
    private int quantity;
    private String unit;//TODO: check what is it

    public Product(int id, int quantity){
        this.id=id;
        this.quantity=quantity;
    }

    public Product(double standard_price, int quantity, String unit) {
        this.standard_price = standard_price;
        this.quantity = quantity;
        this.unit = unit;
    }

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

    public String listDisplay() {

        return "Standard Price: " + standard_price + ", Quantity: " + quantity + ", unit: "+unit;
    }
}
