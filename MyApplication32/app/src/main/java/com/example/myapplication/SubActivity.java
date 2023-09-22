package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class SubActivity extends AppCompatActivity {
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button _btnGuest =findViewById(R.id.btnGuest);
        Button _btnRegister = findViewById(R.id.btnRegister);
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if(sharedPreferences.contains("registered")){
            _btnRegister.setVisibility(View.INVISIBLE);
            _btnGuest.setVisibility(View.INVISIBLE);
        }
        _btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SubActivity.this);
                alertDialog.setTitle("Regsiter");
                final View customLayout = getLayoutInflater().inflate(R.layout.register_layout, null);
                alertDialog.setView(customLayout);
                alertDialog.setPositiveButton("Create User", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText _txtUserName = customLayout.findViewById(R.id.txtuserName);
                        EditText _txtPassword = customLayout.findViewById(R.id.txtpasswprd);
                        Snackbar.make(view,_txtUserName.getText(),Snackbar.LENGTH_LONG).show();
                        db = openOrCreateDatabase("nutdb.db",MODE_PRIVATE,null);
                        db.execSQL("CREATE TABLE IF NOT EXISTS users(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");
                        ContentValues _value = new ContentValues();
                        _value.put("USERNAME",_txtUserName.getText().toString());
                        _value.put("PASSWORD",_txtPassword.getText().toString());
                        db.insert("users",null, _value);
                        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("registered","registered");
                        myEdit.commit();


                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.create();
                alertDialog.show();
            }
        });

        _btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                if (sharedPreferences.contains("name")) {
                    Intent _mainAct = new Intent(SubActivity.this, DashboardActivity.class);
                    startActivity(_mainAct);
                    finish();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                Intent _mainIntent = new Intent(SubActivity.this, MainFragActivity.class);
                                _mainIntent.putExtra("Edit","false");
                                startActivity(_mainIntent);
                                finish();
                            } catch (Exception ex) {


                            }
                        }
                    }).start();
                }
            }
        });

    }
}