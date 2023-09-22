package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DashboardActivity extends AppCompatActivity {
    PieChart pieChart;
    TextView _txtMaintance,_txtName;
    double _BMR =0.0;
    double _BMI =0.0;
    CardView _menuMealLog,_menuNutAnalysis,_menuProfile,_menuNuMac = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SQLiteDatabase db = openOrCreateDatabase("nutdb.db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Nutrition(NUTID INTEGER PRIMARY KEY AUTOINCREMENT,FOODNAME TEXT,PROTIEN FLOAT,CARB FLOAT,FAT FLOAT,CAL FLOAT,SERVING FLOAT,FIBER FLOAT,SUGAR FLOAT,DT TEXT,MEALTYPE TEXT)");
        _menuMealLog = findViewById(R.id.menuMealLog);
        _menuProfile = findViewById(R.id.menuProfile);
        _menuNuMac = findViewById(R.id.menuNuMac);
        _menuNuMac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _mainAct = new Intent(DashboardActivity.this,MicroNuntActivity.class);
                startActivity(_mainAct);
            }
        });
        _menuNutAnalysis = findViewById(R.id.menuNutAnalysis);
        _menuProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _mainAct = new Intent(DashboardActivity.this,MainFragActivity.class);
                _mainAct.putExtra("Edit","true");
                startActivity(_mainAct);
            }
        });
        _menuNutAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _mainAct = new Intent(DashboardActivity.this,NutritionAnalysisActivity.class);
                startActivity(_mainAct);
            }
        });
        _menuMealLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _mainAct = new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(_mainAct);

            }
        });
        _txtMaintance = findViewById(R.id.txtMaintance);
        _txtName = findViewById(R.id.txtName);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String _name = sh.getString("name", "");
        String _gender = sh.getString("gender","");
        float _height = sh.getFloat("height",0.0f);
        float _weight = sh.getFloat("weight",0.0f);
        float _activityrate = sh.getFloat("activityrate",0.0f);
        int _age = sh.getInt("age",0);
        _txtName.setText(_name.toUpperCase());

        switch (_gender){
            case "Male":
                _BMR = (66+(6.23*_weight)+(12.7*_height)-(6.8*_age))*_activityrate;
                _BMR = Math.round(_BMR);
                _BMI = Math.round((_weight/(_height*_height)*703));
                //(weight/height2)*703
                Log.e( "onCreate: ", String.valueOf(_BMR));
                break;
            case "Female":
                _BMR = (665+(4.35*_weight)+(4.7*_height)-(4.7*_age))*_activityrate;
                _BMR = Math.round(_BMR);
                _BMI = Math.round((_weight/(_height*_height)*703));
                break;
        }
        _txtMaintance.setText(String.valueOf(_BMR));
        pieChart = findViewById(R.id.piechart);
        setData(String.valueOf(_BMI));

    }
    private void setData(String bmi)
    {

        pieChart.setInnerValueString(bmi);
        float _b=Float.parseFloat(bmi);
        if(_b>0 && _b<=18){
            pieChart.addPieSlice(new PieModel("UnderWeight",Integer.parseInt("18"),Color.parseColor("#29B6F6")));

        }
        else if(_b>18 && _b<=24){
            pieChart.addPieSlice(new PieModel("Normal", Integer.parseInt("24"),Color.parseColor("#66BB6A")));

        }
        else  if(_b>24 && _b<=29){
            pieChart.addPieSlice(new PieModel("OverWeight", Integer.parseInt("29"),Color.parseColor("#FFA726")));

        }
        else  if(_b>30){
            pieChart.addPieSlice(new PieModel("Obesity", Integer.parseInt("30"),Color.parseColor("#EF5350")));

        }
        // Set t
        // he data and color to the pie chart
        // To animate the pie chart

        //pieChart.setOpenClockwise(true);
        pieChart.startAnimation();
    }
}