package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
           try {
               Thread.sleep(3000);
               //               Intent _mainIntent = new Intent(SplashActivity.this,MainFragActivity.class);
               Intent _mainIntent = new Intent(SplashActivity.this,SubActivity.class); //MainFragActivity.class
               startActivity(_mainIntent);
               finish();
           }
           catch (Exception ex){


           }
            }
        }).start();
    }
}