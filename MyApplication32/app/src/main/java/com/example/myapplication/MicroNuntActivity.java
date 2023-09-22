package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MicroNuntActivity extends AppCompatActivity {
    TextView tvR, tvPython, tvCPP, tvJava,_txtNut,_tvfat1;
    PieChart pieChart;
    String[] _content =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_nunt);
        pieChart = findViewById(R.id.piechart1);
        tvR = findViewById(R.id.tvR1);
        tvPython = findViewById(R.id.tvPython1);
        tvCPP = findViewById(R.id.tvCPP1);
        _txtNut = findViewById(R.id.txtNut1);
        _tvfat1 = findViewById(R.id.tvfat1);
        _content =new String[5];


        SearchView _QuerySearch = findViewById(R.id.QuerySearch);
        _QuerySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                    new JsonBackgroundTask().execute(s.toString());
                setData(s.toString(),35,35,35);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
    private void setData(String name,int _Pro,int _carb,int _cal)
    {

        int total = _Pro +  _carb + _cal;
        int _ProSlice1 = (int) Math.floor(Double.valueOf(_Pro)/total*100);
        int _CarbSlice1 = (int) Math.floor(Double.valueOf(_carb)/total*100);
        int _CalSlice1 = (int) Math.floor(Double.valueOf(_cal)/total*100);
        //  int _total = Integer.parseInt(_Pro) + Integer.parseInt(_carb + Integer.parseInt(_Pro));
        int proSlice = _Pro;
        int carbSlice = _carb;
        int calSlice = _cal;
        // Set the percentage of language used
        _txtNut.setText("NUTRITION FACTS : " + name.toUpperCase());
     //   tvR.setText(Integer.toString(proSlice));
       // tvPython.setText(Integer.toString(carbSlice));
        //tvCPP.setText(Integer.toString(calSlice));
        //tvJava.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Protien",
                        Integer.parseInt("30"),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Carbs",
                        Integer.parseInt("40"),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Calories",
                        Integer.parseInt("30"),
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

    class JsonBackgroundTask extends AsyncTask<String,String,String> {
        // String _movieUrl = "http://www.omdbapi.com/?t=harry&apikey=6ff571af";
        HttpURLConnection _conn = null;
        StringBuilder dBuilder=null;
        @Override
        protected String doInBackground(String... strings) {
            dBuilder = new StringBuilder();
            try{
                URL _url = new URL("https://api.api-ninjas.com/v1/nutrition?query="+ strings[0].toString());
                _conn = (HttpURLConnection) _url.openConnection();
                _conn.setRequestProperty("X-Api-Key","boXtBloHowvFuSpk5yZTKQ==F8dzoGV1CRYj4bXg");
                _conn.connect();
                InputStream _strean =  _conn.getInputStream();
                InputStreamReader _sReader = new InputStreamReader(_strean);
                BufferedReader _dReader = new BufferedReader(_sReader);
                String _line = _dReader.readLine();
                Log.e( "OK: ",_line );
                dBuilder.append(_line);
            }
            catch (Exception ex){
                Log.e( "onClickBg: ",ex.getMessage().toString() );
            }

            return dBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                JSONArray _jsoAry = new JSONArray(s.toString());
                if(_jsoAry.length()>0) {
                    JSONObject _jsoObj = _jsoAry.getJSONObject(0);
                    // Log.e("onPostExecute: dfd", _jsoObj.toString());

                    //  JSONArray _js = _jsoObj.getJSONArray("");
                    //JSONObject _j= _js.getJSONObject(0);
                    String    _calories =    _jsoObj.getString("calories");
                    String    _name =     _jsoObj.getString("name");
                    String   _protien =    _jsoObj.getString("protein_g");
                    String  _carbs =    _jsoObj.getString("carbohydrates_total_g");
                    String  _fiber =    _jsoObj.getString("fiber_g");
                    String    _fat =    _jsoObj.getString("fat_total_g");
                    String  _sugar =    _jsoObj.getString("sugar_g");
                    String _serving =    _jsoObj.getString("serving_size_g");
                    _content[0]=_name;
                    _content[1]=_protien;
                    _content[2]=_carbs;
                    _content[3]=_calories;
                    tvCPP.setText(_calories.toUpperCase());
                    tvR.setText(_protien.toUpperCase());
                    tvPython.setText(_carbs.toUpperCase());
                    _tvfat1.setText(_fat.toUpperCase());


                }

            }
            catch (Exception ex){
                Log.e("onPostExecute-Error: ", ex.getMessage().toString());
            }
        }
    }
}