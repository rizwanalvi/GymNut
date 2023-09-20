package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;

import com.example.myapplication.ml.LiteModelAiyVisionClassifierFoodV11;
import com.google.android.material.snackbar.Snackbar;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button _btnCamera=null;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static  final  int PERMISSION_REQUEST_CODE =101;
    private ImageView _imgObject =null;
    private  Button _btnIdentify=null;
    private Bitmap imageBitmap =null;
    private TextView _txtResult = null;
    private String[] _toWishList=null;
    private TextToSpeech textToSpeech;
    private ArrayList<String> al=null;
    String _calories="";
    String _protien="";
    String _carbs="";
    String _name ="";
    TextView[] textView;
    private ListView _lstFood=null;
    private ArrayAdapter<String> _adapter =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnCamera = findViewById(R.id.btnCamera);
        _btnIdentify = findViewById(R.id.btnIdentify);
        _imgObject = findViewById(R.id.imgObject);
        _txtResult = findViewById(R.id.txtResult);
        _lstFood = findViewById(R.id.lstFood);
        al = new ArrayList<String>();
        _lstFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent _pieIntent = new Intent(MainActivity.this,ChartActivity.class);
             //   Snackbar.make(view,al.get(i).toString(),Snackbar.LENGTH_LONG).show();
                _pieIntent.putExtra("Nut",al.get(i).toString());
                startActivity(_pieIntent);
            }
        });
      //  textView = new TextView[]{findViewById(R.id.textView1), findViewById(R.id.textView2), findViewById(R.id.textView3)};
        _adapter = new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,al);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{ android.Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            ActivityCompat.requestPermissions(this, new String[]{ android.Manifest.permission.READ_EXTERNAL_STORAGE},102);
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},102);
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.INTERNET},102);

            // Permission is not granted

            Log.e( "onCreate: ", "Permission is not granted" );
        }
        _btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   dispatchTakePictureIntent();
                try{
                   // imageChooser();
                    dispatchTakePictureIntent();
                }
                catch (Exception ex){
                    Log.e( "onClick: ",ex.getMessage().toString() );
                }
            }
        });

        _btnIdentify.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {
                    LiteModelAiyVisionClassifierFoodV11 model = LiteModelAiyVisionClassifierFoodV11.newInstance(getApplicationContext());
                    Bitmap bmp =  imageBitmap.copy(Bitmap.Config.ARGB_8888,true) ;
                    Bitmap resizebmp = Bitmap.createScaledBitmap(bmp,224,224,true);

                    // Creates inputs for reference.

                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
                    Log.e( "Step1: ","Step 1");

                    TensorImage image = (TensorImage) TensorImage.fromBitmap(resizebmp);
                    // Runs model inference and gets result.
                    LiteModelAiyVisionClassifierFoodV11.Outputs outputs = model.process(image);
                    List<Category> probability = outputs.getProbabilityAsCategoryList();
                    probability.sort(Comparator.comparing(Category::getScore,Comparator.reverseOrder()));

//                    for(int j=0;j<probability.size();j++){
//                       if(probability.get(j).getScore()*100>50){
//                        String s = String.format("%s\t%f",probability.get(j).getLabel(),probability.get(j).getScore());
//                        Log.e( "onClick: ",s );
//                       }
//                    }
                    for (int i=0; i<3; i++) {
                       // Log.e("Data: ", probability.get(i).getLabel());
                        new JsonBackgroundTask().execute(probability.get(i).getLabel());
                   //     textView[i].setText(getString(R.string.result_text, probability.get(i).getLabel(), probability.get(i).getScore() * 100));


                    }

                    model.close();
                    //   Thread.sleep(1000);
                 //   textToSpeech.speak(_txtResult.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                } catch (Exception e) {
                    // TODO Handle the exception
                    Log.e( "onClick: ",e.getMessage().toString() );
                }


            }
        });
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        } catch (ActivityNotFoundException e) {
            // display error state to the user


        }

    }
    private  void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), REQUEST_IMAGE_CAPTURE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
           Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            Uri selectedImageUri = data.getData();
            _imgObject.setImageBitmap(imageBitmap);
//            try {
//                imageBitmap = MediaStore.Images.Media.getBitmap(
//                        this.getContentResolver(),
//                        selectedImageUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            _imgObject.setImageURI(selectedImageUri);
        }
    }
    private Bitmap ARGBBitmap(Bitmap img) {
        return img.copy(Bitmap.Config.ARGB_8888,true);
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
                _calories =    _jsoObj.getString("calories");
               _name =     _jsoObj.getString("name");
                _protien =    _jsoObj.getString("protein_g");
               _carbs =    _jsoObj.getString("carbohydrates_total_g");
               String _nutration= String.format("%s,%s,%s,%s",_name.toUpperCase(),_protien.toUpperCase(),_carbs.toUpperCase(),_calories.toUpperCase());
               Log.e( "onPostExecute: ",_nutration );
               al.add(_nutration);

                }
                _lstFood.setAdapter(_adapter);
            }
            catch (Exception ex){
                Log.e("onPostExecute-Error: ", ex.getMessage().toString());
            }
        }
    }
}