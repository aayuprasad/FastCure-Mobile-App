package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddPreActivity extends AppCompatActivity {

    EditText doctor_id, doctor_pass, record_id, prescription ;
    Button btn_app;
    DBHelper DB;
    private Session2 session2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pre);

        doctor_id = (EditText) findViewById(R.id.doctor_id);
        doctor_pass = (EditText) findViewById(R.id.doctor_pass);
        record_id = (EditText) findViewById(R.id.record_id);
        prescription = (EditText) findViewById(R.id.prescription);

        btn_app = (Button) findViewById(R.id.btn_app);

        DB = new DBHelper(this);
        session2 = new Session2(getApplicationContext());

        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid = doctor_id.getText().toString();
                String pass = doctor_pass.getText().toString();
                String rid = record_id.getText().toString();
                String pre = prescription.getText().toString();


                if(pid.equals("")||pass.equals("")||rid.equals("")||pre.equals(""))
                {
                    Toast.makeText(AddPreActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass = DB.checkdoctorlogin(pid, pass);
                    if(checkuserpass==true) {
                        String did = session2.getid();
                        DB.insertPre(rid,pre, did);
                        Toast.makeText(AddPreActivity.this,"Prescription Given ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DoctorHomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(AddPreActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}