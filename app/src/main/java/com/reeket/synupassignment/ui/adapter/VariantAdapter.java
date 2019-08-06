package com.reeket.synupassignment.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reeket.synupassignment.R;
import com.reeket.synupassignment.models.VariantGroupsModel;
import com.reeket.synupassignment.models.VariationsModel;

import java.util.List;
import java.util.Map;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.VariantViewHolder>{

    private static final String TAG = "VariantAdapter";

    List<VariantGroupsModel> variantGroupsModels;
    Context context;
    Map<String , String> excludedMap;

    @NonNull
    @Override
    public VariantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_variant , parent , false);
        this.context = parent.getContext();
        VariantViewHolder variantViewHolder = new VariantViewHolder(view);

        return variantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VariantViewHolder holder, int position) {
        if(variantGroupsModels != null){

            VariantGroupsModel variantGroupsModel = variantGroupsModels.get(position);

            holder.setData(variantGroupsModel);
        }
    }

    @Override
    public int getItemCount() {
        if (variantGroupsModels != null){
            Log.d(TAG , ""+variantGroupsModels.size());
            return variantGroupsModels.size();
        }else {
            return 0;
        }
    }

    public void setData(List<VariantGroupsModel> variantGroupsModels , Map<String , String> excludedMap){
        this.variantGroupsModels = variantGroupsModels;
        this.excludedMap = excludedMap;
        notifyDataSetChanged();
    }

    class VariantViewHolder extends RecyclerView.ViewHolder{

        TextView tvVariantName;
        Spinner spinner;
        TextView tvVariationName;
        TextView tvVariationPrice;
        TextView tvVariationInStock;

        public VariantViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVariantName = itemView.findViewById(R.id.tv_item_variant_name);
            spinner = itemView.findViewById(R.id.spinner_item_variant);
            tvVariationName = itemView.findViewById(R.id.tv_item_variation_name);
            tvVariationPrice = itemView.findViewById(R.id.tv_item_variation_price);
            tvVariationInStock = itemView.findViewById(R.id.tv_item_variation_instock);

        }

        public void setData(final VariantGroupsModel variantGroupsModel){

            tvVariantName.setText(context.getResources().getString(R.string.variant_name , variantGroupsModel.getName()));
            SpinnerVariationAdapter spinnerVariationAdapter = new SpinnerVariationAdapter(context,
                    android.R.layout.simple_spinner_item,
                    variantGroupsModel.getVariations());

            spinner.setAdapter(spinnerVariationAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    VariationsModel variationsModel = (VariationsModel) parent.getItemAtPosition(position);
                    
                    if (!excludedMap.get(variantGroupsModel.getGroupId()).equals(variationsModel.getId())) {

                        tvVariationName.setText(context.getResources().getString(R.string.variation_name, variationsModel.getName()));
                        tvVariationPrice.setText(context.getResources().getString(R.string.variation_price, variationsModel.getPrice()));
                        tvVariationInStock.setText(context.getResources().getString(R.string.variation_in_stock, variationsModel.getInStock()));
                    }else {
                        Toast.makeText(context, context.getResources().getString(R.string.toast_can_not_select , variationsModel.getName() , variantGroupsModel.getName()), Toast.LENGTH_SHORT).show();
                        tvVariationName.setText("");
                        tvVariationPrice.setText("");
                        tvVariationInStock.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

}
