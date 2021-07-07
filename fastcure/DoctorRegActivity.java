package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DoctorRegActivity extends AppCompatActivity {

    EditText doctor_first_name, doctor_last_name, doctor_age, doctor_gender, doctor_street, doctor_city, doctor_state, doctor_pin, doctor_department, doctor_password ;
    Button btn_doctor_reg;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg);

        doctor_first_name = (EditText) findViewById(R.id.doctor_first_name);
        doctor_last_name = (EditText) findViewById(R.id.doctor_last_name);
        doctor_age = (EditText) findViewById(R.id.doctor_age);

        doctor_gender = (EditText) findViewById(R.id.doctor_gender);
        doctor_street = (EditText) findViewById(R.id.doctor_street);
        doctor_city = (EditText) findViewById(R.id.doctor_city);

        doctor_state = (EditText) findViewById(R.id.doctor_state);
        doctor_pin = (EditText) findViewById(R.id.doctor_pin);

        doctor_department = (EditText) findViewById(R.id.doctor_department);
        doctor_password = (EditText) findViewById(R.id.doctor_password);

        btn_doctor_reg = (Button) findViewById(R.id.btn_doctor_reg);

        DB = new DBHelper(this);

        btn_doctor_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = doctor_first_name.getText().toString();
                String last_name = doctor_last_name.getText().toString();
                String age = doctor_age.getText().toString();

                String gender = doctor_gender.getText().toString();
                String street = doctor_street.getText().toString();
                String city = doctor_city.getText().toString();
                String state = doctor_state.getText().toString();
                String pin = doctor_pin.getText().toString();
                String department = doctor_department.getText().toString();
                String password = doctor_password.getText().toString();

                if(first_name.equals("")||age.equals("")||gender.equals("")||state.equals("")||department.equals("")||password.equals(""))
                {
                    Toast.makeText(DoctorRegActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Person insert = DB.insertDoctorData(first_name,last_name,age,gender,street,city,state,pin,department,password);
                    if(insert.insert==true) {
                        Toast.makeText(DoctorRegActivity.this,"Your ID: "+insert.id+". Doctor Registered Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DoctorRegActivity.this,"Registration failed!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}