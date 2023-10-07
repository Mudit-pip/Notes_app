package com.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    ListView list;
    SQLiteDatabase db;
    SearchView sv;
    String svtextchange = "";

    LinearLayout mainlay;
    ImageView settingbtn;
    Button addbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.main_grid);
        sv = findViewById(R.id.main_todo_searchview);
        mainlay = findViewById(R.id.mainscreen_layout);
        settingbtn = findViewById(R.id.main_setting_btn);
        addbtn = findViewById(R.id.main_addbtn);


        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
                finish();
            }
        });

        SharedPreferences pref = getSharedPreferences("pref_Background_colour", MODE_PRIVATE);
        String col = pref.getString("main_colour", "a");


        if (col.equals("back1")) {
            mainlay.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back1));
            addbtn.setBackgroundColor(getResources().getColor(R.color.md_brown_350));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn1_colour));
            }

        } else if (col.equals("back2")) {
            mainlay.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back2));
            addbtn.setBackgroundColor(getResources().getColor(R.color.custom_lightblue_for_button));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn2_colour));
            }

        } else if (col.equals("back3")) {
            mainlay.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back3));
            addbtn.setBackgroundColor(getResources().getColor(R.color.custom_blue_for_button));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn3_colour));
            }
        } else if (col.equals("back4")) {
            mainlay.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back4));
            addbtn.setBackgroundColor(getResources().getColor(R.color.custom_grey_for_button));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn4_colour));
            }
        } else if (col.equals("back5")) {
            mainlay.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back5));
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.btn5_colour));
            }
        } else {
            mainlay.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back5));
        }


        db = openOrCreateDatabase("db_todo_main", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_table (id Integer Primary KEY AUTOINCREMENT, title varchar(300), body varchar(2000), time varchar(200))");


        //FOR deleting empty notes
        String qy = "delete from tbl_table where title='' and body =''";
        String ab = "SELECT * FROM tbl_table WHERE title='' AND body=''";
        Cursor cur = db.rawQuery(ab, null);
        cur.moveToLast();
        int a = cur.getCount();
        cur.moveToFirst();
        if (a >= 1) {
            Toast.makeText(this, "Empty Notes Discarded..", Toast.LENGTH_SHORT).show();
        }
        db.execSQL(qy);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                svtextchange = newText;
                showlist();
                return false;
            }
        });
        //////////


        showlist();
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, view, info);
        menu.add("Delete");
        menu.add("Edit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Delete")) {
            SharedPreferences pref = getSharedPreferences("idForCode", MODE_PRIVATE);
            String s = pref.getString("id", "a");
            String qry = "DELETE FROM tbl_table WHERE id ='" + s + "'";
            db.execSQL(qry);
            showlist();

            return true;
        }
        if (item.getTitle().equals("Edit")) {

            SharedPreferences pref = getSharedPreferences("idForCode", MODE_PRIVATE);
            String s = pref.getString("id", "a");
            int a = Integer.parseInt(s);

            Intent intent = new Intent(MainActivity.this, Inside_item.class);
            intent.putExtra("clickedId", a);
            startActivity(intent);
            finish();


            return true;
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("Range")
    public void main_todo_btn(View view) {
        String qry = "INSERT INTO tbl_table (title, body) VALUES ('', '')";
        db.execSQL(qry);

        Cursor cur;
        qry = "SELECT * FROM tbl_table";

        cur = db.rawQuery(qry, null);
        cur.moveToLast();
        int n = cur.getCount();
        cur.moveToFirst();

        String[] id = new String[n];
        for (int i = 0; i < n; i++) {
            id[i] = cur.getString(cur.getColumnIndex("id"));
            cur.moveToNext();
        }
        int lastid = Integer.parseInt(id[(n - 1)]);

        Intent intent = new Intent(this, Inside_item.class);
        intent.putExtra("id", lastid);
        startActivity(intent);
        finish();
    }


    @SuppressLint("Range")
    public void showlist() {
        String[] title, body, ids, timeSaved;
        Cursor cur;
        String qry;
        int n;

        qry = "SELECT * FROM tbl_table WHERE title like '%" + svtextchange + "%' OR body like '%" + svtextchange + "%'";
        cur = db.rawQuery(qry, null);

        cur.moveToLast();
        n = cur.getCount();
        cur.moveToFirst();

        title = new String[n];
        body = new String[n];
        ids = new String[n];
        timeSaved = new String[n];


        for (int i = 0; i < n; i++) {
            timeSaved[i] = cur.getString(cur.getColumnIndex("time"));
            ids[i] = cur.getString(cur.getColumnIndex("id"));


            String b = cur.getString(cur.getColumnIndex("title"));
            if (b.length() > 30) {
                b = b.substring(0, 30) + "...";
            }
            title[i] = b;


            String c = cur.getString(cur.getColumnIndex("body"));
            if (c.length() > 60) {
                c = c.substring(0, 60) + "...";
            }
            body[i] = c;


            cur.moveToNext();


        }


        myadapter adapter = new myadapter(this, title, body, ids, timeSaved);
        list.setAdapter(adapter);


    }


    public class myadapter extends ArrayAdapter<String> {
        Context context;
        String[] title, body, ids, timesaved;

        public myadapter(Context context, String[] title, String[] body, String[] ids, String[] timesaved) {
            super(context, R.layout.layout_main_items, R.id.layout_main_for_ids, ids);
            this.context = context;
            this.title = title;
            this.body = body;
            this.ids = ids;
            this.timesaved = timesaved;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            vholder vh;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.layout_main_items, parent, false);

                vh = new vholder(row);
                row.setTag(vh);
            } else {
                vh = (vholder) row.getTag();
            }

            vh.ids.setText(ids[position]);
            vh.title.setText(title[position]);
            vh.body.setText(body[position]);
            vh.timesaved.setText(timesaved[position]);

            registerForContextMenu(vh.lay);


            vh.lay.setTag(ids[position] + "~" + title[position]);
            vh.lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a = ((LinearLayout) view).getTag().toString();
                    String[] b = a.split("~");
                    int i = Integer.parseInt(b[0]);


                    Intent intent = new Intent(MainActivity.this, Inside_item.class);
                    intent.putExtra("clickedId", i);
                    startActivity(intent);
                    finish();
                }
            });


            vh.lay.isLongClickable();
            vh.lay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String a = ((LinearLayout) v).getTag().toString();
                    String[] b = a.split("~");
                    String s = b[0];

                    SharedPreferences pref = getSharedPreferences("idForCode", MODE_PRIVATE);
                    SharedPreferences.Editor ed = pref.edit();
                    ed.putString("id", b[0]);
                    ed.commit();


                    return false;
                }
            });


            if (vh.title.getText().toString().trim().length() == 0) {
                vh.title.setVisibility(View.GONE);
            } else {
                vh.title.setVisibility(View.VISIBLE);
            }

            if (vh.body.getText().toString().trim().length() == 0) {
                vh.body.setVisibility(View.GONE);
            } else {
                vh.body.setVisibility(View.VISIBLE);
            }

            return row;
        }
    }

    public class vholder {
        TextView ids, title, body, timesaved;
        LinearLayout lay;

        public vholder(View r) {
            this.ids = r.findViewById(R.id.layout_main_for_ids);
            this.title = r.findViewById(R.id.layout_main_for_title);
            this.body = r.findViewById(R.id.layout_main_for_body);
            this.lay = r.findViewById(R.id.layout_main_lay);
            this.timesaved = r.findViewById(R.id.layout_main_timesaved);
        }
    }
}