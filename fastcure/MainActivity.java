package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button patlogin, patreg, doclogin, docreg;
    DBHelper DB;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patlogin = (Button) findViewById(R.id.patlogin);
        patreg = (Button) findViewById(R.id.patreg);
        docreg = (Button) findViewById(R.id.docreg);
        doclogin = (Button) findViewById(R.id.doclogin);
        DB = new DBHelper(this);

        patlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientLoginActivity.class);
                startActivity(intent);
            }
        });

        patreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientRegActivity.class);
                startActivity(intent);
            }
        });

        doclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorLoginActivity.class);
                startActivity(intent);
            }
        });
        docreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorRegActivity.class);
                startActivity(intent);
            }
        });
    }
}