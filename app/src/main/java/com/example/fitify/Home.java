package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Observable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.result.DailyTotalResult;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import kotlin.jvm.internal.Intrinsics;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View rootView = inflater.inflate(R.layout.activity_home, container, false);
        View textViewSteps=rootView.findViewById(R.id.steps);
        Intrinsics.checkNotNullExpressionValue(textViewSteps, "rootView.findViewById(R.id.steps)");

        View textViewStepsBig=rootView.findViewById(R.id.steps_big);
        Intrinsics.checkNotNullExpressionValue(textViewStepsBig, "rootView.findViewById(R.id.steps_big)");

        View textViewCalories=rootView.findViewById(R.id.burned_calories);
        Intrinsics.checkNotNullExpressionValue(textViewCalories, "rootView.findViewById(R.id.burned_calories)");

        View textViewDistance=rootView.findViewById(R.id.distance);
        Intrinsics.checkNotNullExpressionValue(textViewDistance, "rootView.findViewById(R.id.distance)");

        View stepsProgressBar=rootView.findViewById(R.id.stepsProgressBar);
        Intrinsics.checkNotNullExpressionValue(stepsProgressBar, "rootView.findViewById(R.id.stepsProgressBar)");
        ProgressBar progressBar = findViewById(R.id.stepsProgressBar);
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stepsProgressBar");
        }
//        FitnessViewModel var6 = this.getFitnessViewModel();
//        Intrinsics.checkNotNullExpressionValue(rootView, "rootView");
//        Context var10002 = rootView.getContext();
//        Intrinsics.checkNotNullExpressionValue(var10002, "rootView.context");
//        var10000.setMax(var6.loadObjectiveSteps(var10002));
//        FitnessViewModel var5 = this.getFitnessViewModel();
//        Context var7 = rootView.getContext();
//        Intrinsics.checkNotNullExpressionValue(var7, "rootView.context");
//        var5.getDailyFitnessData(var7).observe(this.getViewLifecycleOwner(), (Observer)(new Observer() {
        return rootView;
    }}