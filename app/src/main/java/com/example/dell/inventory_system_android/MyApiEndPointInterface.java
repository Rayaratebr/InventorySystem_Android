package com.example.dell.inventory_system_android;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface MyApiEndPointInterface {

     /*
    *
    * Customer APIs
    * */

    @GET("customers")
    Call<List<Customer>> getAllCustomers();

    @POST("customers")
    Call<String> storeCustomer(@Body Customer customer);

    /*
    *
    * Payment APIs
    * */

    @GET("payments")
    Call<List<Customer>> getAllPayments();

    @POST("payments")
    Call<String> storePayment(@Body Payment payment);

    @POST("customers/{customer_id}/payments")
    Call<String> storeCustomerPayment(@Path("customer_id") int customer_id, @Body Payment payment);


    /*
    *
    * Products APIs
    * */
    @GET("products")
    Call<List<Product>> getAllProducts();

    @POST("products")
    Call<String> storeProduct(@Body Product product);

    /*
      *
      * Orders APIs
      * */
    @GET("orders")
    Call<List<Order>> getAllOrders();

    @POST("orders")
    Call<String> storeOrder(@Body Order order);

    @POST("customers/{customer_id}/orders")
    Call<String> storeCustomerOrder(@Path("customer_id") int customer_id, @Body Order order);

}
