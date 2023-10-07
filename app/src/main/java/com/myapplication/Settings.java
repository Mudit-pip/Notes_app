package com.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Settings extends AppCompatActivity {

    LinearLayout lay;
    ImageView back1_main, back2_main, back3_main, back4_main, back5_main, back1_notes, back2_notes, back3_notes, back4_notes, back5_notes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lay = findViewById(R.id.settings_layout);
        back1_main = findViewById(R.id.setting_back1_main);
        back2_main = findViewById(R.id.setting_back2_main);
        back3_main = findViewById(R.id.setting_back3_main);
        back4_main = findViewById(R.id.setting_back4_main);
        back5_main = findViewById(R.id.setting_back5_main);

        back1_notes = findViewById(R.id.setting_back1_notes);
        back2_notes = findViewById(R.id.setting_back2_notes);
        back3_notes = findViewById(R.id.setting_back3_notes);
        back4_notes = findViewById(R.id.setting_back4_notes);
        back5_notes = findViewById(R.id.setting_back5_notes);

        SharedPreferences pref = getSharedPreferences("pref_Background_colour", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String col = pref.getString("main_colour", "a");

        if (col.equals("back1")) {
            back1_main.setImageResource(R.drawable.back1_btn_clicked);
            back2_main.setImageResource(R.drawable.back2_btn);
            back3_main.setImageResource(R.drawable.back3_btn);
            back4_main.setImageResource(R.drawable.back4_btn);
            back5_main.setImageResource(R.drawable.back5_btn);
            lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back1));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn1_colour));
            }
        } else if (col.equals("back2")) {
            back1_main.setImageResource(R.drawable.back1_btn);
            back2_main.setImageResource(R.drawable.back2_btn_clicked);
            back3_main.setImageResource(R.drawable.back3_btn);
            back4_main.setImageResource(R.drawable.back4_btn);
            back5_main.setImageResource(R.drawable.back5_btn);
            lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back2));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn2_colour));
            }
        } else if (col.equals("back3")) {
            back1_main.setImageResource(R.drawable.back1_btn);
            back2_main.setImageResource(R.drawable.back2_btn);
            back3_main.setImageResource(R.drawable.back3_btn_clicked);
            back4_main.setImageResource(R.drawable.back4_btn);
            back5_main.setImageResource(R.drawable.back5_btn);
            lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back3));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn3_colour));
            }
        } else if (col.equals("back4")) {
            back1_main.setImageResource(R.drawable.back1_btn);
            back2_main.setImageResource(R.drawable.back2_btn);
            back3_main.setImageResource(R.drawable.back3_btn);
            back4_main.setImageResource(R.drawable.back4_btn_clicked);
            back5_main.setImageResource(R.drawable.back5_btn);
            lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back4));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn4_colour));
            }
        } else if (col.equals("back5")) {
            back1_main.setImageResource(R.drawable.back1_btn);
            back2_main.setImageResource(R.drawable.back2_btn);
            back3_main.setImageResource(R.drawable.back3_btn);
            back4_main.setImageResource(R.drawable.back4_btn);
            back5_main.setImageResource(R.drawable.back5_btn_clicked);
            lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back5));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn5_colour));
            }
        } else {
            lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back5));
        }

        back1_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back1_main.setImageResource(R.drawable.back1_btn_clicked);
                back2_main.setImageResource(R.drawable.back2_btn);
                back3_main.setImageResource(R.drawable.back3_btn);
                back4_main.setImageResource(R.drawable.back4_btn);
                back5_main.setImageResource(R.drawable.back5_btn);
                lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back1));
                editor.putString("main_colour", "back1");
                editor.commit();
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.btn1_colour));
                }
            }
        });

        back2_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back1_main.setImageResource(R.drawable.back1_btn);
                back2_main.setImageResource(R.drawable.back2_btn_clicked);
                back3_main.setImageResource(R.drawable.back3_btn);
                back4_main.setImageResource(R.drawable.back4_btn);
                back5_main.setImageResource(R.drawable.back5_btn);
                lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back2));
                editor.putString("main_colour", "back2");
                editor.commit();
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.btn2_colour));
                }
            }
        });

        back3_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back1_main.setImageResource(R.drawable.back1_btn);
                back2_main.setImageResource(R.drawable.back2_btn);
                back3_main.setImageResource(R.drawable.back3_btn_clicked);
                back4_main.setImageResource(R.drawable.back4_btn);
                back5_main.setImageResource(R.drawable.back5_btn);
                lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back3));
                editor.putString("main_colour", "back3");
                editor.commit();
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.btn3_colour));
                }
            }
        });

        back4_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back1_main.setImageResource(R.drawable.back1_btn);
                back2_main.setImageResource(R.drawable.back2_btn);
                back3_main.setImageResource(R.drawable.back3_btn);
                back4_main.setImageResource(R.drawable.back4_btn_clicked);
                back5_main.setImageResource(R.drawable.back5_btn);
                lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back4));
                editor.putString("main_colour", "back4");
                editor.commit();
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.btn4_colour));
                }
            }
        });

        back5_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back1_main.setImageResource(R.drawable.back1_btn);
                back2_main.setImageResource(R.drawable.back2_btn);
                back3_main.setImageResource(R.drawable.back3_btn);
                back4_main.setImageResource(R.drawable.back4_btn);
                back5_main.setImageResource(R.drawable.back5_btn_clicked);
                lay.setBackground(ContextCompat.getDrawable(Settings.this, R.drawable.back5));
                editor.putString("main_colour", "back5");
                editor.commit();
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.btn5_colour));
                }
            }
        });


        //////////////////////////////////



        col = pref.getString("notes_colour", "a");

        if (col.equals("back1")) {
            back1_notes.setImageResource(R.drawable.back1_btn_clicked);
            back2_notes.setImageResource(R.drawable.back2_btn);
            back3_notes.setImageResource(R.drawable.back3_btn);
            back4_notes.setImageResource(R.drawable.back4_btn);
            back5_notes.setImageResource(R.drawable.back5_btn);
        } else if (col.equals("back2")) {
            back1_notes.setImageResource(R.drawable.back1_btn);
            back2_notes.setImageResource(R.drawable.back2_btn_clicked);
            back3_notes.setImageResource(R.drawable.back3_btn);
            back4_notes.setImageResource(R.drawable.back4_btn);
            back5_notes.setImageResource(R.drawable.back5_btn);
        } else if (col.equals("back3")) {
            back1_notes.setImageResource(R.drawable.back1_btn);
            back2_notes.setImageResource(R.drawable.back2_btn);
            back3_notes.setImageResource(R.drawable.back3_btn_clicked);
            back4_notes.setImageResource(R.drawable.back4_btn);
            back5_notes.setImageResource(R.drawable.back5_btn);
        } else if (col.equals("back4")) {
            back1_notes.setImageResource(R.drawable.back1_btn);
            back2_notes.setImageResource(R.drawable.back2_btn);
            back3_notes.setImageResource(R.drawable.back3_btn);
            back4_notes.setImageResource(R.drawable.back4_btn_clicked);
            back5_notes.setImageResource(R.drawable.back5_btn);
        } else if (col.equals("back5")) {
            back1_notes.setImageResource(R.drawable.back1_btn);
            back2_notes.setImageResource(R.drawable.back2_btn);
            back3_notes.setImageResource(R.drawable.back3_btn);
            back4_notes.setImageResource(R.drawable.back4_btn);
            back5_notes.setImageResource(R.drawable.back5_btn_clicked);
        }


        back1_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("notes_colour", "back1");
                editor.commit();
                back1_notes.setImageResource(R.drawable.back1_btn_clicked);
                back2_notes.setImageResource(R.drawable.back2_btn);
                back3_notes.setImageResource(R.drawable.back3_btn);
                back4_notes.setImageResource(R.drawable.back4_btn);
                back5_notes.setImageResource(R.drawable.back5_btn);
            }
        });

        back2_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("notes_colour", "back2");
                editor.commit();
                back1_notes.setImageResource(R.drawable.back1_btn);
                back2_notes.setImageResource(R.drawable.back2_btn_clicked);
                back3_notes.setImageResource(R.drawable.back3_btn);
                back4_notes.setImageResource(R.drawable.back4_btn);
                back5_notes.setImageResource(R.drawable.back5_btn);
            }
        });

        back3_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("notes_colour", "back3");
                editor.commit();
                back1_notes.setImageResource(R.drawable.back1_btn);
                back2_notes.setImageResource(R.drawable.back2_btn);
                back3_notes.setImageResource(R.drawable.back3_btn_clicked);
                back4_notes.setImageResource(R.drawable.back4_btn);
                back5_notes.setImageResource(R.drawable.back5_btn);
            }
        });

        back4_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("notes_colour", "back4");
                editor.commit();
                back1_notes.setImageResource(R.drawable.back1_btn);
                back2_notes.setImageResource(R.drawable.back2_btn);
                back3_notes.setImageResource(R.drawable.back3_btn);
                back4_notes.setImageResource(R.drawable.back4_btn_clicked);
                back5_notes.setImageResource(R.drawable.back5_btn);
            }
        });

        back5_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("notes_colour", "back5");
                editor.commit();
                back1_notes.setImageResource(R.drawable.back1_btn);
                back2_notes.setImageResource(R.drawable.back2_btn);
                back3_notes.setImageResource(R.drawable.back3_btn);
                back4_notes.setImageResource(R.drawable.back4_btn);
                back5_notes.setImageResource(R.drawable.back5_btn_clicked);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}