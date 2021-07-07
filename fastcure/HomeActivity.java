package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button addtracker;
    Button viewtracker,addapp,viewapp,viewdoc,btn_patient_logout;
    private Session session;
    DBHelper DB;

//    Button btnLogout;
//    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addtracker = (Button) findViewById(R.id.addtracker);
        viewtracker = (Button) findViewById(R.id.viewtracker);
        addapp = (Button) findViewById(R.id.addapp);
        viewapp = (Button) findViewById(R.id.viewapp);
        viewdoc = (Button) findViewById(R.id.viewdoc);
        btn_patient_logout = (Button) findViewById(R.id.btn_patient_logout);
//        btnLogout = (Button) findViewById(R.id.btn_logout);
        session = new Session(getApplicationContext());
        DB = new DBHelper(this);
//        session = new SessionManager(getApplicationContext());

//        if(session.checkLogin())
//            finish();

        addtracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HealthTrackerActivity.class);
                startActivity(intent);
            }
        });
        viewtracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewTrackerActivity.class);
                startActivity(intent);
            }
        });
        viewdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewDocActivity.class);
                startActivity(intent);
            }
        });
        addapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddAppActivity.class);
                startActivity(intent);
            }
        });
        viewapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewAppActivity.class);
                startActivity(intent);
            }
        });

        btn_patient_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                session.setid("");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                // Clear the User session data
//                // and redirect user to LoginActivity
//                session.logoutUser();
//            }
//        });
    }
}