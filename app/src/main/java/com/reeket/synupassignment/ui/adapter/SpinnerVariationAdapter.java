package com.reeket.synupassignment.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.reeket.synupassignment.models.VariationsModel;

import java.util.List;


public class SpinnerVariationAdapter extends ArrayAdapter<VariationsModel> {

    List<VariationsModel> variationsModels;

    public SpinnerVariationAdapter(@NonNull Context context, int resource, @NonNull List<VariationsModel> variationsModels) {
        super(context, resource, variationsModels);
        this.variationsModels = variationsModels;
    }

    @Override
    public int getCount(){
        return variationsModels.size();
    }

    @Override
    public VariationsModel getItem(int position){
        return variationsModels.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(variationsModels.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(variationsModels.get(position).getName());

        return label;
    }
}
