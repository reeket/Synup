package com.reeket.synupassignment.models;

import com.google.gson.annotations.SerializedName;

public class ExcludeListModel {

    @SerializedName("group_id")
    private String groupId;
    @SerializedName("variation_id")
    private String variationId;

    public ExcludeListModel(String groupId, String variationId) {
        this.groupId = groupId;
        this.variationId = variationId;
    }

    public String getGroupId() {
        return groupId;
    }


    public String getVariationId() {
        return variationId;
    }

}
