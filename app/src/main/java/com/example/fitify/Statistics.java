package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

//
//import com.jjoe64.graphview.GraphView;
//import com.jjoe64.graphview.series.DataPoint;
//import com.jjoe64.graphview.series.LineGraphSeries;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public class Statistics extends Fragment   {
    //private BarChart progressChart;
    private View rootView;

//    private GraphView graphView;
    private data_retrieve data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

        // Initialize data_retrieve instance
//        data = new data_retrieve(this);
//
//        // Get last seven days steps walked
//        List<Integer> lastSevenDaysDatasets = data.getStepsWalkedLastSevenDays();

//        graphView = findViewById(R.id.idGraphView);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
//
//        // Add data points to the series
//        for (int i = 0; i < lastSevenDaysDatasets.size(); i++) {
//            double x = i;
//            double y = lastSevenDaysDatasets.get(i);
//            DataPoint dataPoint = new DataPoint(x, y);
//            series.appendData(dataPoint, true, lastSevenDaysDatasets.size());
//        }
//        graphView.setTitle("Steps walked");
//        graphView.setTitleColor(R.color.purple_200);
//        graphView.setTitleTextSize(18);
//        graphView.addSeries(series);

    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View rootView=inflater.inflate(R.layout.fragment_statistics,container,false);
        Intrinsics.checkNotNullExpressionValue(rootView, "inflater.inflate(R.layou…istics, container, false)");
        if (rootView==null){
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        //ProgressBar progressChart=rootView.findViewById(R.id.stepsProgressBar);
        //FitnessViewModel fitnessViewModel=this.getF
        return rootView;
}
}




