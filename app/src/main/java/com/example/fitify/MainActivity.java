package com.example.fitify;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000;
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //mGoogleApiClient.connect();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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