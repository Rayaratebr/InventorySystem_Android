package com.example.dell.inventory_system_android.Models;

import java.io.Serializable;
import java.util.ArrayList;


public class Order extends Parent implements Serializable {
    private long id;
    private String order_date;
    private String order_due_date;
    private long customer_id;
    private ArrayList<Product> products = new ArrayList<Product>();
    Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order() {
    }

    public Order(String order_date, String order_due_date) {
        this.order_date = order_date;
        this.order_due_date = order_due_date;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrderDate() {
        return order_date;
    }

    public void setOrderDate(String order_date) {
        this.order_date = order_date;
    }

    public String getOrderDueDate() {
        return order_due_date;
    }

    public void setOrderDueDate(String order_due_date) {
        this.order_due_date = order_due_date;
    }


  public ArrayList<Product> getProducts() {
       return products;
   }
//
   public void setProducts(ArrayList<Product> products) {
      this.products = products;
   }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + id +
                ", orderDate=" + order_date +
                ", orderDueDate=" + order_due_date +
                ", customer=" + customer_id +
//                ", products=" + products +
                '}';
    }
    public String displayOrder(){
        return "Order ID: " +id +
                "\n Order Date: " +order_date+
                "\n Order Due Date: " +order_due_date +
                "\n Customer ID: " +customer_id ;
    }

    public String listDisplay() {

        return "Order Date: " + order_date + ", Customer: " + customer_id;
    }


}
