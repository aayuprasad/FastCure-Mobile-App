package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorHomeActivity extends AppCompatActivity {

    Button viewtracker,viewapp,addpre,btn_doctor_logout;
    EditText patid;
    DBHelper DB;
    private Session session;
    private Session2 session2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        viewtracker = (Button) findViewById(R.id.viewtracker);
        viewapp = (Button) findViewById(R.id.viewapp);
        addpre = (Button) findViewById(R.id.addpre);
        btn_doctor_logout = (Button) findViewById(R.id.btn_doctor_logout);
        patid = (EditText) findViewById(R.id.patient_id2);
        DB = new DBHelper(this);
        session = new Session(getApplicationContext());
        session2 = new Session2(getApplicationContext());
        viewtracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Boolean checkpatdoct = DB.checkpatdoc(patid.getText().toString(),session2.getid());
                if(checkpatdoct == false) {
                    Toast.makeText(DoctorHomeActivity.this, "Patient Data not accessible!", Toast.LENGTH_SHORT).show();
                } else {
                    session.setid(patid.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), ViewTrackerActivity.class);
                    startActivity(intent);
                }
            }
        });

        viewapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewAppDActivity.class);
                startActivity(intent);
            }
        });
        addpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddPreActivity.class);
                startActivity(intent);
            }
        });
        btn_doctor_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"Update details!", Toast.LENGTH_SHORT).show();
                session2.setid("");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}