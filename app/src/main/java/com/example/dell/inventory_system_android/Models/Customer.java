package com.example.dell.inventory_system_android.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Parent implements Serializable{
    private int id;
    private String name;
    private String address;
    private String phone;
    private static ArrayList<Customer> customersList = new ArrayList<>();
    private static ArrayList<Payment> paymentsList = new ArrayList<>();
    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public Customer() {

    }

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public void addPayment(Payment payment){
        paymentsList.add(payment);
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
    public String displayCustomer(){
        return "ID: " +id +
                "\n Customer Name: " +name+
                "\n Address: " +address +
                "\n Phone: " +phone ;
    }

    public String listDisplay() {

        return "Name: " + name + ", Phone: " + phone;
    }
}
