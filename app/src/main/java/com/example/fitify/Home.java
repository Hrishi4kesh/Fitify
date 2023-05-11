package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Observable;
import android.os.Bundle;

import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.result.DailyTotalResult;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class Home extends AppCompatActivity {

    //cmt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}