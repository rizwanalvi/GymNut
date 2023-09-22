package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NutritionAnalysisActivity extends AppCompatActivity {
    SQLiteDatabase db =null;
    ListView _lstNutAnay = null;
    ArrayList<Nutrition> _al =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_analysis);
        _lstNutAnay = findViewById(R.id.lstNutAnay);
        _al = new ArrayList<Nutrition>();
        CustomListView _Adapter = new CustomListView(this, R.layout.custom_list_nutrition_analysis,_al);
      db = openOrCreateDatabase("nutdb.db",MODE_PRIVATE,null);
       // db.execSQL("CREATE TABLE IF NOT EXISTS Nutrition(NUTID INTEGER PRIMARY KEY AUTOINCREMENT,FOODNAME TEXT,PROTIEN FLOAT,CARB FLOAT,FAT FLOAT,CAL FLOAT,SERVING FLOAT,FIBER FLOAT,SUGAR FLOAT,DT TEXT,MEALTYPE TEXT)");
     try {
         Cursor resultset = db.rawQuery("SELECT * FROM Nutrition ORDER BY DT", null);
         while (resultset.moveToNext()) {
             String _foodName = resultset.getString(1);
             String _Cal = resultset.getString(5);
             String _MealTp = resultset.getString(10);
             String _DT = resultset.getString(9);
             _al.add(new Nutrition(_foodName,_MealTp,_Cal,_DT));
         }
         _lstNutAnay.setAdapter(_Adapter);
     }
     catch (Exception ex){
         Log.e( "onCreate - Analysis: ", ex.getMessage().toString());
     }
    }

    private void ReadData(){

    }
}