package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientLoginActivity extends AppCompatActivity {

    EditText patient_id, patient_password ;
    Button btn_patient_login;
    DBHelper DB;
//    SessionManager session;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
//        session = new SessionManager(getApplicationContext());
        session = new Session(getApplicationContext());

        patient_id = (EditText) findViewById(R.id.patient_id);
        patient_password = (EditText) findViewById(R.id.patient_password);
        btn_patient_login = (Button) findViewById(R.id.btn_patient_login);
        DB = new DBHelper(this);

//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        btn_patient_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = patient_id.getText().toString();
                String pass = patient_password.getText().toString();

                if(id.equals("")||pass.equals(""))
                    Toast.makeText(PatientLoginActivity.this,"Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(id, pass);
                    if(checkuserpass==true) {
                        Toast.makeText(PatientLoginActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
//                        session.createUserLoginSession(id,true);
                        session.setid(id);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(PatientLoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}