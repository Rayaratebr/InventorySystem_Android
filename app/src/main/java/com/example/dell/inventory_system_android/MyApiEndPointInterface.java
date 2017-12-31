package com.example.dell.inventory_system_android;


import com.example.dell.inventory_system_android.Models.Customer;
import com.example.dell.inventory_system_android.Models.Order;
import com.example.dell.inventory_system_android.Models.Payment;
import com.example.dell.inventory_system_android.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiEndPointInterface {

     /*
    *
    * Customer APIs
    * */

    @GET("customers")
    Call<List<Customer>> getAllCustomers(
            @Query("name") String name,
            @Query("phone") String phone

            );

    @GET("customers/{customer_id}")
    Call<Customer> getCustomer(@Path("customer_id") int customer_id);

    @POST("customers")
    Call<String> storeCustomer(@Body Customer customer);

    @DELETE("customers/{customer_id}")
    Call<String> deleteCustomer(@Path("customer_id") int customer_id);

    /*
    *
    * Payment APIs
    * */

    @GET("payments")
    Call<List<Payment>> getAllPayments();

    @GET("payments/{payment_id}")
    Call<Payment> getPayment(@Path("payment_id") int payment_id);

    @POST("payments")
    Call<String> storePayment(@Body Payment payment);

    @POST("customers/{customer_id}/payments")
    Call<String> storeCustomerPayment(@Path("customer_id") int customer_id, @Body Payment payment);

    @DELETE("payments/{payment_id}")
    Call<String> deletePayment(@Path("payment_id") int payment_id);


    /*
    *
    * Products APIs
    * */
    @GET("products")
    Call<List<Product>> getAllProducts(
            @Query("unit") String unit,
            @Query("price") String price

    );

    @GET("products/{product_id}")
    Call<Product> getProduct(@Path("product_id") int product_id);

    @POST("products")
    Call<String> storeProduct(@Body Product product);

    @DELETE("products/{product_id}")
    Call<String> deleteProduct(@Path("product_id") int product_id);

    /*
      *
      * Orders APIs
      * */
    @GET("orders")
    Call<List<Order>> getAllOrders();

    @GET("orders/{order_id}")
    Call<Order> getOrder(@Path("order_id") int order_id);

    @POST("orders")
    Call<String> storeOrder(@Body Order order);

    @POST("customers/{customer_id}/orders")
    Call<String> storeCustomerOrder(@Path("customer_id") int customer_id, @Body Order order);

    @GET("customers/{customer_id}/orders")
    Call<List<Order>> getCustomerOrders(@Path("customer_id") int customer_id);

    @DELETE("orders/{order_id}")
    Call<String> deleteOrder(@Path("order_id") int order_id);


}
