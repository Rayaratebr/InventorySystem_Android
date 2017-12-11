package com.example.dell.inventory_system_android.Models;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String phone;
    private static ArrayList<Customer> customersList = new ArrayList<>();

    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public Customer(){

    }

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.phone= phone;
        this.address=address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
