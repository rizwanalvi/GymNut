package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomListView extends ArrayAdapter<Nutrition> {
    private Nutrition _nut=null;
    private  Context aContext=null;
    private int aResource=0;
    public CustomListView(@NonNull Context context, int resource, ArrayList<Nutrition> objects) {
        super(context, resource, objects);
        aContext =context;
        aResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String _name = getItem(position).get_foodName();
        String _MealType = getItem(position).get_MealType();
        String _Calories = getItem(position).get_calories();
        String _Date = getItem(position).get_date();
        LayoutInflater inflater = LayoutInflater.from(aContext);
        convertView = inflater.inflate(aResource,parent,false);
        TextView _txtFoodName = convertView.findViewById(R.id.txtFoodName);
        TextView _txtMealType = convertView.findViewById(R.id.txtMealType);
        TextView _txtTotalCal = convertView.findViewById(R.id.txtTotalCal);
        TextView _txtDate = convertView.findViewById(R.id.txtDate);
        _txtFoodName.setText(_name.toUpperCase());
        _txtMealType.setText(_MealType.toUpperCase());
        _txtTotalCal.setText(_Calories+"Cal");
        _txtDate.setText(_Date);
        return convertView;
    }
}
