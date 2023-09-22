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
        _txtName = findViewById(R.id.txtName);
        _txtAge = findViewById(R.id.txtAge);
        _txtHeight = findViewById(R.id.txtHeight);
        _txtWeight = findViewById(R.id.txtWeight);
        _cobGender = findViewById(R.id.cobGender);
        _cobActitviy = findViewById(R.id.cobActitviy);
        _cobSleep = findViewById(R.id.cobSleep);
        Button _btnSave = findViewById(R.id.btnSave);
        Intent _intent = getIntent();
        String _men = _intent.getStringExtra("Edit");
        if(_men.contains("true")){
            _btnSave.setText("Update");
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            String _Name = sharedPreferences.getString("name","");
            String _Age = String.valueOf(sharedPreferences.getInt("age",0));
            String _Activityrate = String.valueOf(sharedPreferences.getFloat("activityrate",0.0f));
            String _Height = String.valueOf(sharedPreferences.getFloat("height",0.0f));
            String _Weight = String.valueOf(sharedPreferences.getFloat("weight",0.0f));
            String _Sleep = String.valueOf(sharedPreferences.getInt("sleep",0));
            String _Gender = sharedPreferences.getString("gender","");
            _txtName.setText(_Name);
            _txtAge.setText(_Age);
            _txtHeight.setText(_Height);
            _txtWeight.setText(_Weight);
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
                    myEdit.putString("name", _name);
                    myEdit.putInt("age", _age);
                    myEdit.putFloat("activityrate", Float.valueOf(String.valueOf(_activityrate)));
                    myEdit.putFloat("height", Float.valueOf(String.valueOf(_height)));
                    myEdit.putFloat("weight", Float.valueOf(String.valueOf(_weight)));
                    myEdit.putInt("sleep", _sleep);
                    myEdit.putString("gender", _cobGender.getSelectedItem().toString());
                    myEdit.commit();
                Intent _intenDashboard = new Intent(MainFragActivity.this,DashboardActivity.class);
                startActivity(_intenDashboard);
                finish();
                }
            });

        }
        else{


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

        }


    }
