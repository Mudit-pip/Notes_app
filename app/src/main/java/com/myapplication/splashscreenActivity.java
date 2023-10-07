package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class splashscreenActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        txt = findViewById(R.id.splashscreen_txt);

        txt.setVisibility(View.VISIBLE);
//        txt.animate().translationX(150).setDuration(2000).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashscreenActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
}