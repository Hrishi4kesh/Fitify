package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class Statistics extends AppCompatActivity {

    private GraphView graphView;
    private data_retrieve data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_statistics);

        // Initialize data_retrieve instance
        data = new data_retrieve(this);

        // Get last seven days steps walked
        List<Integer> lastSevenDaysDatasets = data.getStepsWalkedLastSevenDays();

        graphView = findViewById(R.id.idGraphView);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        // Add data points to the series
        for (int i = 0; i < lastSevenDaysDatasets.size(); i++) {
            double x = i;
            double y = lastSevenDaysDatasets.get(i);
            DataPoint dataPoint = new DataPoint(x, y);
            series.appendData(dataPoint, true, lastSevenDaysDatasets.size());
        }
        graphView.setTitle("Steps walked");
        graphView.setTitleColor(R.color.purple_200);
        graphView.setTitleTextSize(18);
        graphView.addSeries(series);
    }
}
