package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public class settings extends AppCompatActivity {

    private TextView textView4 , textView, steps;
    private Button changeObj;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    private final void showObjectiveSteps( Context context){
        //TextView textView = this.;
        if(textView4 == null){
            Intrinsics.throwUninitializedPropertyAccessException("stepsTextView");

        }
        //textView4.setText((CharSequence)String.valueOf(this.getFitnessViewModel().loadObjectiveSteps(context)) );

    }

    /* private final void showObjectiveDialog(final Context context){
         View dialogView = LayoutInflater.from(context).inflate()

    }*/

    public settings(){
        Function0 ownerProducer$iv = (Function0)(new settings());
        Function0 extraProducer$iv = null;
        Function0 factoryProducer$iv = null;
       // int viewModels = false;

    }
}