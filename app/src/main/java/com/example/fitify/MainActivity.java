package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.Scopes;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.result.DataReadResult;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context = getApplicationContext();
    private static final String TAG = "MainActivity";

    GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
            .addApi(Fitness.HISTORY_API)
            .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
            .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {
                    // Define the data source and data type
                    DataSource dataSource = new DataSource.Builder()
                            .setAppPackageName("com.google.android.gms")
                            .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
                            .setType(DataSource.TYPE_DERIVED)
                            .setStreamName("estimated_steps")
                            .build();

                    // Define the time range for the query
                    Calendar cal = Calendar.getInstance();
                    Date now = new Date();
                    cal.setTime(now);
                    long endTime = cal.getTimeInMillis();
                    cal.add(Calendar.DAY_OF_WEEK, -7);
                    long startTime = cal.getTimeInMillis();

                    // Create a data request object
                    DataReadRequest readRequest = new DataReadRequest.Builder()
                            .aggregate(dataSource, DataType.AGGREGATE_STEP_COUNT_DELTA)
                            .bucketByTime(1, TimeUnit.DAYS)
                            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                            .build();

                    // Invoke the History API to fetch the data
                    Fitness.HistoryApi.readData(mGoogleApiClient, readRequest)
                            .setResultCallback(new ResultCallback<DataReadResult>() {
                                @Override
                                public void onResult(DataReadResult dataReadResult) {
                                    // Process the result
                                    int steps = 0;
                                    if (dataReadResult.getBuckets().size() > 0) {
                                        for (Bucket bucket : dataReadResult.getBuckets()) {
                                            List<DataSet> dataSets = bucket.getDataSets();
                                            for (DataSet dataSet : dataSets) {
                                                for (DataPoint dataPoint : dataSet.getDataPoints()) {
                                                    steps += dataPoint.getValue(Field.FIELD_STEPS).asInt();
                                                }
                                            }
                                        }
                                    } else if (dataReadResult.getDataSets().size() > 0) {
                                        for (DataSet dataSet : dataReadResult.getDataSets()) {
                                            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                                                steps += dataPoint.getValue(Field.FIELD_STEPS).asInt();
                                            }
                                        }
                                    }
                                    Log.i(TAG, "Total steps: " + steps);
                                }
                            });
                }

                @Override
                public void onConnectionSuspended(int i) {
                    // Handle connection suspension
                }
            })
            .build();
        //mGoogleApiClient.connect();



    private static final int TIME_INTERVAL = 2000;
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mGoogleApiClient.connect();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed(){
        if(backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            return;
        }
        else{
            Toast.makeText(getBaseContext(),"Press Back Again To Exit",Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }
}