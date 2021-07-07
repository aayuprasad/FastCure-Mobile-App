package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddAppActivity extends AppCompatActivity {

    EditText patient_id, patient_pass, doctor_id, symptom ;
    Button btn_app;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);

        patient_id = (EditText) findViewById(R.id.patient_id);
        patient_pass = (EditText) findViewById(R.id.patient_pass);
        doctor_id = (EditText) findViewById(R.id.doctor_id);
        symptom = (EditText) findViewById(R.id.symptom);

        btn_app = (Button) findViewById(R.id.btn_app);

        DB = new DBHelper(this);

        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid = patient_id.getText().toString();
                String pass = patient_pass.getText().toString();
                String did = doctor_id.getText().toString();
                String sym = symptom.getText().toString();


                if(pid.equals("")||pass.equals("")||did.equals("")||sym.equals(""))
                {
                    Toast.makeText(AddAppActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass = DB.checkusernamepassword(pid, pass);
                    if(checkuserpass==true) {
                        DB.insertApp(pid,did,sym);
                        Toast.makeText(AddAppActivity.this,"Booked ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(AddAppActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}