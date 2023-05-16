package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Statistics extends AppCompatActivity {

    GraphView graphView;
    data_retrieve data=new data_retrieve();
    List<DataSet> lastSevenDaysDatasets = data.getLastSevenDaysDatasets();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        graphView = findViewById(R.id.idGraphView);
        List<DataPoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < lastSevenDaysDatasets.size(); i++) {
            DataSet dataset = lastSevenDaysDatasets.get(i);
            int steps = 0;
            DataPoint dataPoint = new DataPoint((double) i, steps);
           dataPoints.add(dataPoint);
        }
        DataPoint[] dataPointArray = dataPoints.toArray(new DataPoint[0]);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointArray);



        graphView.setTitle("Steps walked");
        graphView.setTitleColor(R.color.purple_200);
        graphView.setTitleTextSize(18);
        graphView.addSeries(series);
    }
}


//setContentView(R.layout.fragment_statistics);