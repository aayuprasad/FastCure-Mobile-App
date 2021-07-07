package com.example.fastcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HealthTrackerActivity extends AppCompatActivity {

    EditText bp,oxy,weight,height;
    Button btnaddtracker;
    DBHelper DB;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tracker);
        btnaddtracker = (Button) findViewById(R.id.btnaddtracker);
        bp = (EditText) findViewById(R.id.bp);
        oxy = (EditText) findViewById(R.id.oxy);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        DB = new DBHelper(this);
        session = new Session(getApplicationContext());

        btnaddtracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bpp = bp.getText().toString();
                String oxyy = oxy.getText().toString();
                String weightt = weight.getText().toString();
                String heightt = height.getText().toString();

                if(bpp.equals("")||oxyy.equals("")||weightt.equals("")||heightt.equals(""))
                {
                    Toast.makeText(HealthTrackerActivity.this, "Enter all fields!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Integer id = Integer.parseInt(session.getid());
                    Boolean check = DB.insertTracker(id,bpp,oxyy,weightt,heightt);
                    if(check==true) {
                        Toast.makeText(HealthTrackerActivity.this, "Added successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(HealthTrackerActivity.this, "Update failed!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}