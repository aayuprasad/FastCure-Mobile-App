package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PatientRegActivity extends AppCompatActivity {

    EditText patient_first_name, patient_last_name, patient_age, patient_gender, patient_city, patient_password, patient_street, patient_state, patient_pin ;
    Button btn_patient_reg;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reg);

        patient_first_name = (EditText) findViewById(R.id.patient_first_name);
        patient_last_name = (EditText) findViewById(R.id.patient_last_name);
        patient_age = (EditText) findViewById(R.id.patient_age);

        patient_gender = (EditText) findViewById(R.id.patient_gender);
        patient_street = (EditText) findViewById(R.id.patient_street);
        patient_city = (EditText) findViewById(R.id.patient_city);

        patient_state = (EditText) findViewById(R.id.patient_state);
        patient_pin = (EditText) findViewById(R.id.patient_pin);
        patient_password = (EditText) findViewById(R.id.patient_password);

        btn_patient_reg = (Button) findViewById(R.id.btn_patient_reg);

        DB = new DBHelper(this);

        btn_patient_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = patient_first_name.getText().toString();
                String last_name = patient_last_name.getText().toString();
                String age = patient_age.getText().toString();

                String gender = patient_gender.getText().toString();
                String street = patient_street.getText().toString();
                String city = patient_city.getText().toString();

                String state = patient_state.getText().toString();
                String pin = patient_pin.getText().toString();
                String password = patient_password.getText().toString();

                if(first_name.equals("")||age.equals("")||gender.equals("")||city.equals("")||state.equals("")||pin.equals("")||password.equals(""))
                {
                    Toast.makeText(PatientRegActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    Person insert = DB.insertPatientData(first_name,last_name,age,street,city,state,pin,gender,password);
                    if(insert.insert==true) {
                        Toast.makeText(PatientRegActivity.this,"Your ID: "+insert.id+". Patient Registered Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(PatientRegActivity.this,"Registration failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}