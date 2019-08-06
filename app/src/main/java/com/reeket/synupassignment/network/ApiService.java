package com.reeket.synupassignment.network;

import com.reeket.synupassignment.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("bins/19u0sf")
    Call<ResponseModel> getJsonData();
}
