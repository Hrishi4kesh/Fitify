package com.example.fitify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import kotlin.jvm.internal.Intrinsics;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000;
    private long backPressed;

        private  int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1;
        private BottomNavigationView bottomNavigationView;
        private  boolean runningQOrLater;
        private  FitnessOptions fitnessOptions;
        private final String TAG="MainActivity";


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInAccount getGoogleAccount;

    }
    private GoogleSignInAccount getGoogleAccount() {
        GoogleSignInAccount var10000 = GoogleSignIn.getAccountForExtension((Context)this, (GoogleSignInOptionsExtension)this.fitnessOptions);
        Intrinsics.checkNotNullExpressionValue(var10000, "GoogleSignIn.getAccountF…ion(this, fitnessOptions)");
        return var10000;
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