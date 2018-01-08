package com.example.dell.inventory_system_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.inventory_system_android.Models.Parent;

public abstract class ViewActivity extends AppCompatActivity {
    public Bundle params = null;
    public static int objectID = -1;
    public static Parent object;

    public abstract void setObject(Parent object);

}
