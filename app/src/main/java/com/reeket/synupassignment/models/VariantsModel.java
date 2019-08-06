package com.reeket.synupassignment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VariantsModel {
    @SerializedName("variant_groups")
    private List<VariantGroupsModel> variantGroups;
    @SerializedName("exclude_list")
    private List<List<ExcludeListModel>> excludeList;

    public VariantsModel(List<VariantGroupsModel> variantGroups, List<List<ExcludeListModel>> excludeList) {
        this.variantGroups = variantGroups;
        this.excludeList = excludeList;
    }

    public List<VariantGroupsModel> getVariantGroups() {
        return variantGroups;
    }


    public List<List<ExcludeListModel>> getExcludeList() {
        return excludeList;
    }

}
