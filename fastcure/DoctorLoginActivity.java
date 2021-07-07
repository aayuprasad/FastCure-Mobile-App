package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLoginActivity extends AppCompatActivity {

    EditText doctor_id, doctor_password ;
    Button btn_doctor_login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        doctor_id = (EditText) findViewById(R.id.doctor_id);
        doctor_password = (EditText) findViewById(R.id.doctor_password);
        btn_doctor_login = (Button) findViewById(R.id.btn_doctor_login);
        DB = new DBHelper(this);

        btn_doctor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = doctor_id.getText().toString();
                String pass = doctor_password.getText().toString();

                if(id.equals("")||pass.equals(""))
                    Toast.makeText(DoctorLoginActivity.this,"Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkdoctorlogin(id, pass);
                    if(checkuserpass==true) {
                        Toast.makeText(DoctorLoginActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DoctorHomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DoctorLoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}