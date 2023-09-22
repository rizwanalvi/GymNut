package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapterList extends RecyclerView.Adapter<RecycleViewAdapterList.ViewHolder> {
    @NonNull
    @Override
    public RecycleViewAdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterList.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView _foodName;
        public TextView _protien;
        public TextView _carbs;
        public TextView _fat;
        public TextView _calories;
        public TextView _serving;
        public TextView _fiber;
        public TextView _sugar;
        public TextView _date;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.layNutAnalysisView);
        }
    }
}
