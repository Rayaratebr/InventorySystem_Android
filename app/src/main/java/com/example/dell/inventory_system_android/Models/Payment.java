package com.example.dell.inventory_system_android.Models;

import java.util.Date;

public class Payment extends Parent{
    private int id;
    private double amount;
    private String payment_date;//TODO: check date type
    private long customer_id;
    private long order_id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String  payment_date) {
        this.payment_date = payment_date;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String listDisplay() {

        return "Amount: " + amount + ", Customer: " + customer_id + ", PaymentDate: "+payment_date;
    }

    @Override
    public String toString() {
        return "Payment ID: " + id
                +"\n Amount: " +amount
                +"\n Payment Due Date: "+payment_date
                +"\n Customer ID: "+customer_id;
    }
}
