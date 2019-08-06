package com.reeket.synupassignment.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.reeket.synupassignment.models.ResponseModel;
import com.reeket.synupassignment.network.ApiResponse;
import com.reeket.synupassignment.network.ApiService;
import com.reeket.synupassignment.network.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {

    /*

    Create this repository for handle all http request with retrofit

     */

    private static final String TAG = "NetworkRepository";

    //Initialize service with singleton class
    ApiService apiService = (ApiService) ServiceBuilder.buildService(ApiService.class);

    MutableLiveData<ApiResponse<ResponseModel>> responseModelMutableLiveData = new MutableLiveData<>();

    public void callApi(){

        apiService.getJsonData().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    responseModelMutableLiveData.setValue(ApiResponse.success(response.body()));
                }else {
                    responseModelMutableLiveData.setValue(ApiResponse.error(response.errorBody().toString() , (ResponseModel) null));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                responseModelMutableLiveData.setValue(ApiResponse.error(t.getMessage() , (ResponseModel) null));
            }
        });

    }

    public LiveData<ApiResponse<ResponseModel>> getJsonResponse(){
        return responseModelMutableLiveData;
    }


}
