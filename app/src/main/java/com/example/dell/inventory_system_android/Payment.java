package com.example.dell.inventory_system_android;

import java.util.Date;

/**
 * Created by dell on 12/10/2017.
 */

class Payment {
    private int id;
    private double amount;
    private Date payment_date;//TODO: check date type
    private long customer_id;



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

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }
}
