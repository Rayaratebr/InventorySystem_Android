package com.example.dell.inventory_system_android;

import android.app.Application;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setRetrofitConfig();
//        Log.w("%%%%once","yess");
    }


    private void setRetrofitConfig() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Config.apiService = retrofit.create(MyApiEndPointInterface.class);

    }

}
