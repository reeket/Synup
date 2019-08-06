package com.reeket.synupassignment.ui;

import android.os.Bundle;

import com.reeket.synupassignment.R;
import com.reeket.synupassignment.ui.adapter.VariantAdapter;
import com.reeket.synupassignment.models.ExcludeListModel;
import com.reeket.synupassignment.models.ResponseModel;
import com.reeket.synupassignment.network.ApiResponse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MainActivityViewModel mainActivityViewModel;

    RecyclerView recyclerView;

    VariantAdapter variantAdapter;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize adapter for RecyclerView
        variantAdapter = new VariantAdapter();

        recyclerView = findViewById(R.id.rv_main_activity);

        progressBar = findViewById(R.id.pb_main_activity);

        initRecyclerView();

        //Initial ViewModel
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        //http request for get json data
        mainActivityViewModel.callApi();

        //subscribe observer for live data from api
        subscribeResponseObserver();
    }

    private void subscribeResponseObserver(){

        //subscribe observer for live data from api
        mainActivityViewModel.subscribeResponseObserver().observe(this, new Observer<ApiResponse<ResponseModel>>() {
            @Override
            public void onChanged(ApiResponse<ResponseModel> responseModelApiResponse) {
                if (responseModelApiResponse != null) {
                    switch (responseModelApiResponse.status) {
                        case LOADING: {

                            //Show loading
                            showLoading(true);
                            Log.i(TAG, " Loading");
                            break;
                        }

                        case SUCCESS: {

                            showLoading(false);

                            Map<String , String> excludedMap = new HashMap<>();

                            List<List<ExcludeListModel>> listOfList = responseModelApiResponse.data.getVariants().getExcludeList();

                            for (int i = 0; i < listOfList.size() ; i++) {

                                List<ExcludeListModel> list = listOfList.get(i);

                                for(int j = 0 ; j < list.size() ; j++){

                                    ExcludeListModel excludeListModel = list.get(j);

                                    excludedMap.put(excludeListModel.getGroupId() , excludeListModel.getVariationId());
                                }

                            }

                            variantAdapter.setData(responseModelApiResponse.data.getVariants().getVariantGroups() , excludedMap);
                            Log.i(TAG , responseModelApiResponse.data.getVariants().getVariantGroups().get(1).getName());
                            break;
                        }

                        case ERROR: {
                            // goto RetryFragment
                            showLoading(false);
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                            Log.e(TAG, responseModelApiResponse.message);
                            break;
                        }

                    }
                }
            }
        });

    }

    private void showLoading(boolean b){
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }


    private void initRecyclerView(){
        recyclerView.setAdapter(variantAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
