package com.reeket.synupassignment.models;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {


    @SerializedName("variants")
    private VariantsModel variants;

    public ResponseModel(VariantsModel variants) {
        this.variants = variants;
    }

    public VariantsModel getVariants() {
        return variants;
    }
}
