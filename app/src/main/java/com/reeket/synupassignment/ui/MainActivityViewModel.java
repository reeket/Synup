package com.reeket.synupassignment.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.reeket.synupassignment.models.ResponseModel;
import com.reeket.synupassignment.network.ApiResponse;
import com.reeket.synupassignment.repositories.NetworkRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private static final String TAG = "MainActivityViewModel";

    // this repository handles all network transaction
    NetworkRepository networkRepository;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        networkRepository = new NetworkRepository();
    }

    public void callApi(){

        networkRepository.callApi();

    }

    public LiveData<ApiResponse<ResponseModel>> subscribeResponseObserver(){
        return networkRepository.getJsonResponse();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG , "On Cleared");
    }
}
