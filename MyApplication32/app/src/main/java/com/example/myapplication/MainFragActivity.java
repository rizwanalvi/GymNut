package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainFragActivity extends AppCompatActivity {

    EditText _txtName,_txtAge,_txtHeight,_txtWeight;
    Spinner _cobGender,_cobActitviy,_cobSleep;
    double _activityrate=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frag);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        if(!sharedPreferences.contains("name")){
        _txtName = findViewById(R.id.txtName);
        _txtAge = findViewById(R.id.txtAge);
        _txtHeight = findViewById(R.id.txtHeight);
        _txtWeight = findViewById(R.id.txtWeight);
        _cobGender = findViewById(R.id.cobGender);
        _cobActitviy = findViewById(R.id.cobActitviy);
        _cobSleep = findViewById(R.id.cobSleep);
        Button _btnSave = findViewById(R.id.btnSave);
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String _name = _txtName.getText().toString();
            int _age = Integer.valueOf(_txtAge.getText().toString());

            double _height = Double.valueOf(_txtHeight.getText().toString());
            double _weight = Double.valueOf(_txtWeight.getText().toString());
                int _sleep = Integer.valueOf(_cobSleep.getSelectedItem().toString().substring(0,1));

                switch (_cobActitviy.getSelectedItemPosition()){
                    case 0:
                        _activityrate = 1.2;
                        break;
                    case 1:
                        _activityrate = 1.375;
                        break;
                    case 2:
                        _activityrate = 1.55;
                        break;
                    case 3:
                        _activityrate = 1.725;
                        break;
                    case 4:
                        _activityrate = 1.9;
                        break;
                }
              UserInfo _userinfo = new UserInfo(_name,_age,_cobGender.getSelectedItem().toString(),_activityrate,_height,_weight,_sleep);
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
               SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", _name);
                myEdit.putInt("age", _age);
                myEdit.putFloat("activityrate", Float.valueOf(String.valueOf(_activityrate)));
                myEdit.putFloat("height", Float.valueOf(String.valueOf(_height)));
                myEdit.putFloat("weight", Float.valueOf(String.valueOf(_weight)));
                myEdit.putInt("sleep", _sleep);
                myEdit.putString("gender", _cobGender.getSelectedItem().toString());
                myEdit.commit();
                Intent _dasIntent = new Intent(MainFragActivity.this,DashboardActivity.class);
                startActivity(_dasIntent);
                finish();
            }
        });
        }
        else{
            Intent _dasIntent = new Intent(MainFragActivity.this,DashboardActivity.class);
            startActivity(_dasIntent);
            finish();
        }
    }
}