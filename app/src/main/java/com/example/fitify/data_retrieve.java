package com.example.fitify;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class data_retrieve {
    private Context context;

    public data_retrieve(Context context) {
        this.context = context;
    }

    public List<Integer> getStepsWalkedLastSevenDays() {
        List<Integer> stepsList = new ArrayList<>();

        // Set the start and end times for the last 7 days
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        long startTime = calendar.getTimeInMillis();

        // Create a data read request to retrieve step count data for the specified time range
        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();

        // Retrieve the step count data from Google Fit
        Fitness.getHistoryClient(context, GoogleSignIn.getLastSignedInAccount(context))
                .readData(readRequest)
                .addOnSuccessListener(response -> {
                    if (response.getBuckets().size() > 0) {
                        // Iterate over the buckets and extract step count data
                        for (Bucket bucket : response.getBuckets()) {
                            List<DataSet> dataSets = bucket.getDataSets();
                            for (DataSet dataSet : dataSets) {
                                // Iterate over the data points in the dataset
                                for (com.google.android.gms.fitness.data.DataPoint dataPoint : dataSet.getDataPoints()) {
                                    for (Field field : dataPoint.getDataType().getFields()) {
                                        if (field.getName().equals(Field.FIELD_STEPS.getName())) {
                                            int steps = dataPoint.getValue(field).asInt();
                                            stepsList.add(steps);
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(e ->
                        Log.e("data_retrieve", "Failed to retrieve step count data", e));

        return stepsList;
    }
}
