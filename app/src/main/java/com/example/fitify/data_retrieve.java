package com.example.fitify;
import static android.content.ContentValues.TAG;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataReadRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class data_retrieve {
    ZonedDateTime endTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
    ZonedDateTime startTime = endTime.minusWeeks(1);
    private List<DataSet> lastSevenDaysDatasets = new ArrayList<>();

    private List<DataSet> getFitnessDataForLastSevenDays() {
        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
                .readData(createReadRequestForLastSevenDays())
                .addOnSuccessListener(response -> {
                    List<DataSet> datasetList = new ArrayList<>();
                    for (Bucket bucket : response.getBuckets()) {
                        for (DataSet dataSet : bucket.getDataSets()) {
                            datasetList.addAll(dumpDataSet(dataSet));
                        }
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DAY_OF_YEAR, -7);
                    Date sevenDaysAgo = calendar.getTime();

                    List<DataSet> lastSevenDaysDatasets = new ArrayList<>();
                    for (DataSet dataset : datasetList) {
                        long datasetEndTime = dataset.getEndTime(TimeUnit.MILLISECONDS);
                        if (datasetEndTime >= sevenDaysAgo.getTime()) {
                            lastSevenDaysDatasets.add(dataset);
                        }
                    }

                    // Do further processing or return the list of datasets
                    return lastSevenDaysDatasets;
                })
                .addOnFailureListener(e ->
                        Log.w(TAG, "There was an error reading data from Google Fit", e));

        // Return an empty list if the retrieval fails or hasn't finished yet
        return new ArrayList<>();
    }

    private DataReadRequest createReadRequestForLastSevenDays() {
        // Get the start and end times for the last seven days
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        long startTime = calendar.getTimeInMillis();

        // Create a DataReadRequest for the last seven days of fitness data
        return new DataReadRequest.Builder()
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .build();
    }

    private List<DataSet> dumpDataSet(DataSet dataSet) {
        List<DataSet> datasetList = new ArrayList<>();
        datasetList.add(dataSet);
        return datasetList;
    }

    private String getStartTimeString() {
        return Instant.ofEpochSecond(this.getStartTime(TimeUnit.SECONDS))
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().toString();
    }

    private String getEndTimeString() {
        return Instant.ofEpochSecond(this.getEndTime(TimeUnit.SECONDS))
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().toString();
    }
    public List<DataSet> getLastSevenDaysDatasets() {
        return lastSevenDaysDatasets;
    }
    private long getEndTime(TimeUnit timeUnit) {
        return endTime.toInstant().toEpochMilli();
    }

    private long getStartTime(TimeUnit timeUnit) {
        return startTime.toInstant().toEpochMilli();
    }





}
