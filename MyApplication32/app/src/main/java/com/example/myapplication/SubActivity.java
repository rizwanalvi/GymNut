package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button _btnGuest =findViewById(R.id.btnGuest);
        _btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            Intent _mainIntent = new Intent(SubActivity.this,MainFragActivity.class);
                            startActivity(_mainIntent);
                            finish();
                        }
                        catch (Exception ex){


                        }
                    }
                }).start();
            }
        });
    }
}