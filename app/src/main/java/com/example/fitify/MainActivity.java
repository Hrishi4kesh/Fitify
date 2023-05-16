package com.example.fitify;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

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
        //mGoogleApiClient.connect();
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        GoogleSignInAccount getGoogleAccount;

    }
    private void loadNav_controller(){
        View bottomNavigationView= findViewById(R.id.bottomNavigationView);
        Intrinsics.checkNotNullExpressionValue(bottomNavigationView, "findViewById<BottomNavigation.id.bottomNavigationView)");
        this.bottomNavigationView=(BottomNavigationView) bottomNavigationView;
        NavController nav_controller= ActivityKt.findNavController(this,R.id.my_nav);
        NavDestination currentDestination=nav_controller.getCurrentDestination();
        Integer currentDestinationID=nav_controller !=null?currentDestination.getId():null;
        if (currentDestinationID!=null){
            nav_controller.popBackStack(currentDestinationID,false);
            nav_controller.navigate(currentDestinationID);
        }
        bottomNavigationView.setNextFocusUpId(nav_controller.hashCode());

    }
    private void checkPermissionAndRun(int fitActionRequestCode){
        if (this.permissionApproved()){
            this.fitSignIn(fitActionRequestCode);
        }
        else {
            this.requestRuntimePermission(fitActionRequestCode);
        }
    }
    private void requestPermission(){
        GoogleSignIn.requestPermissions(this,this.GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,this.getGoogleAccount(),this.fitnessOptions);
    }
    private void fitSignIn(int requestCode){
        if (!GoogleSignIn.hasPermissions((this.getGoogleAccount()),this.fitnessOptions)) {
            this.requestPermission();
        }
        else {
            this.loadNav_controller();
        }
    }
    private GoogleSignInAccount getGoogleAccount(){
        GoogleSignInAccount fitnessOptions= GoogleSignIn.getAccountForExtension(this,this.fitnessOptions);
        return fitnessOptions;
    }
    private boolean permissionApproved(){
        boolean approved= !this.runningQOrLater || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0;
        return approved;
    }
    private void requestRuntimePermission(int requestCode){
        boolean shouldProvideRationale=ActivityCompat.shouldShowRequestPermissionRationale(this,"android.permission.ACCESS_FINE_LOCATION");
        requestCode=0;
        if(shouldProvideRationale){
            Log.i("ContentValues","Displaying permission rationale to provide additional context.");
            //Snackbar.make(this.findViewById(R.id.setting_fragment), (CharSequence)"Permission Denied", -2).setAction((CharSequence)"Settings").show();
        }
        else {
            Log.i("ContentValues", "Requesting permission");
            ActivityCompat.requestPermissions(this,new String[]{"android.permission.ACCESS_FINE_LOCATION"}, requestCode);
        }
        this.checkPermissionAndRun((requestCode));
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case -1:
                if (requestCode == this.GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                    this.loadNav_controller();
                }
                break;
            default:
                Toast.makeText((Context)this, (CharSequence)"Permission not granted", Toast.LENGTH_LONG).show();
                this.requestPermission();
        }
    }
    public MainActivity(){
        this.runningQOrLater= Build.VERSION.SDK_INT>=29;
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