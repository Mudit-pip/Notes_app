package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Inside_item extends AppCompatActivity {

    EditText title, body;
    SQLiteDatabase db;
    int lastid;
    Calendar cal;
    String timeSaved;
    LinearLayout lay;
    Button btn;

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_item);

        title = findViewById(R.id.Insideitem_txt_Title);
        body = findViewById(R.id.Insideitem_txt_body);
        lay = findViewById(R.id.insideitem_layout);
        btn = findViewById(R.id.insideitem_back_btn);

        cal = Calendar.getInstance();


        SharedPreferences pref = getSharedPreferences("pref_Background_colour", MODE_PRIVATE);
        String col = pref.getString("notes_colour", "a");

        if (col.equals("back1")) {
            lay.setBackground(ContextCompat.getDrawable(Inside_item.this, R.drawable.back1));
            btn.setBackgroundColor(getResources().getColor(R.color.md_brown_350));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn1_colour));
            }
        } else if (col.equals("back2")) {
            lay.setBackground(ContextCompat.getDrawable(Inside_item.this, R.drawable.back2));
            btn.setBackgroundColor(getResources().getColor(R.color.custom_lightblue_for_button));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn2_colour));
            }
        } else if (col.equals("back3")) {
            lay.setBackground(ContextCompat.getDrawable(Inside_item.this, R.drawable.back3));
            btn.setBackgroundColor(getResources().getColor(R.color.custom_blue_for_button));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn3_colour));
            }
        } else if (col.equals("back4")) {
            lay.setBackground(ContextCompat.getDrawable(Inside_item.this, R.drawable.back4));
            btn.setBackgroundColor(getResources().getColor(R.color.custom_grey_for_button));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn4_colour));
            }
        } else if (col.equals("back5")) {
            lay.setBackground(ContextCompat.getDrawable(Inside_item.this, R.drawable.back5));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn5_colour));
            }
        } else {
            lay.setBackground(ContextCompat.getDrawable(Inside_item.this, R.drawable.back5));
        }


        db = openOrCreateDatabase("db_todo_main", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_table (id Integer Primary KEY AUTOINCREMENT, title varchar(300), body varchar(2000), time varchar(200))");

        Intent in = getIntent();
        lastid = in.getIntExtra("id", 0);

        int k = in.getIntExtra("clickedId", 0);
        if (k != 0) {
            lastid = k;
            in.putExtra("clickedId", 0);
        }

        //Toast.makeText(this, ""+lastid, Toast.LENGTH_SHORT).show();

        String qry;
        qry = "SELECT * FROM tbl_table WHERE id = '" + lastid + "'";
        Cursor cur = db.rawQuery(qry, null);


        cur.moveToFirst();

        title.setText(cur.getString(cur.getColumnIndex("title")));
        body.setText(cur.getString(cur.getColumnIndex("body")));


        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String qry;
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                timeSaved = format.format(cal.getTime());
                qry = "UPDATE tbl_table SET title = '" + s + "', time = '"+ timeSaved +"' WHERE  id =  '" + lastid + "'";
                db.execSQL(qry);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        body.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String qry;
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                timeSaved = format.format(cal.getTime());
                qry = "UPDATE tbl_table SET body = '" + s + "', time = '"+ timeSaved +"'  WHERE  id =  '" + lastid + "'";
                db.execSQL(qry);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    public void Insideitem_back_btn(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @SuppressLint("Range")
    public void Insideitem_share_btn(View view) {
        String arbody, artitle;
        Cursor cur;
        String qry;

        qry = "SELECT * FROM tbl_table WHERE  id =  '" + lastid + "'";
        cur = db.rawQuery(qry, null);
        cur.moveToFirst();
        arbody = cur.getString(cur.getColumnIndex("body"));
        artitle = cur.getString(cur.getColumnIndex("title"));


        String toshare = "*"+artitle+"*\n\n"+ arbody +"";


        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, toshare);
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
        }
    }
}