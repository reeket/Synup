package com.reeket.synupassignment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VariantGroupsModel {


    @SerializedName("group_id")
    private String groupId;
    @SerializedName("name")
    private String name;
    @SerializedName("variations")
    private List<VariationsModel> variations;

    public VariantGroupsModel(String groupId, String name, List<VariationsModel> variations) {
        this.groupId = groupId;
        this.name = name;
        this.variations = variations;
    }

    public String getGroupId() {
        return groupId;
    }


    public String getName() {
        return name;
    }


    public List<VariationsModel> getVariations() {
        return variations;
    }

}
