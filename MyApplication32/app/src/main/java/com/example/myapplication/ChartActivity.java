package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ChartActivity extends AppCompatActivity {
    TextView tvR, tvPython, tvCPP, tvJava,_txtNut;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent _getNut = getIntent();
        String _Nut = _getNut.getStringExtra("Nut");
        String[] _data = _Nut.split(",");
        String _name = _data[0];
        String _Protien = _data[1];
        String _Carbs = _data[2];
        String _Calories = _data[3];
        int _ProSlice = (int) Math.floor(Double.valueOf(_data[1]));
        int _CarbSlice = (int) Math.floor(Double.valueOf(_data[2]));
        int _CalSlice = (int) Math.floor(Double.valueOf(_data[3]));
        int total = _ProSlice +  _CarbSlice + _CalSlice;
        int _ProSlice1 = (int) Math.floor(Double.valueOf(_data[1])/total*100);
        int _CarbSlice1 = (int) Math.floor(Double.valueOf(_data[2])/total*100);
        int _CalSlice1 = (int) Math.floor(Double.valueOf(_data[3])/total*100);
        Log.e("onCreate: pro",String.valueOf(_ProSlice1) );
        Log.e("onCreate: Carb",String.valueOf(_CarbSlice1) );
        Log.e( "onCreate: Cal",String.valueOf(_CalSlice1) );


        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        _txtNut = findViewById(R.id.txtNut);
        //tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);
       setData(_name,_ProSlice1,_CarbSlice1,_CalSlice1);


       // int proSlice = Integer.parseInt(_Protien);
        //int carbSlice = Integer.parseInt(_Carbs)/;
        //int calSlice = Integer.parseInt(_Calories)/3;

     //   setData(_Protien,_Carbs,_Calories);
///String name,String pro,String carbs,String cal
    }
    private void setData(String name,int _Pro,int _carb,int _cal)
    {
      //  int _total = Integer.parseInt(_Pro) + Integer.parseInt(_carb + Integer.parseInt(_Pro));
        int proSlice = _Pro;
        int carbSlice = _carb;
        int calSlice = _cal;
            // Set the percentage of language used
        _txtNut.append(" : " + name);
        tvR.setText(Integer.toString(proSlice));
        tvPython.setText(Integer.toString(carbSlice));
        tvCPP.setText(Integer.toString(calSlice));
        //tvJava.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Protien",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Carbs",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Calories",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}