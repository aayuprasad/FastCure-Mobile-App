package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ViewAppDActivity extends AppCompatActivity {
    DBHelper DB;
    private Session2 session2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session2 = new Session2(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_app_d);
        DB = new DBHelper(this);
        String listd = DB.listdapp(session2.getid());
        TextView textView = (TextView) findViewById(R.id.listd);
        textView.setText(listd);
    }
}