package com.example.fastcure;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

final class Person
{
    public Integer id;
    public Boolean insert;

    public Person(Integer id, Boolean insert)
    {
        this.id = id;
        this.insert = insert;
    }
}

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "DB8.db";
    public DBHelper(Context context) {
        super(context, "DB8.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table patient(patient_id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT NOT NULL, last_name TEXT, age INTEGER CHECK(age >= 0 and age <= 120), gender TEXT CHECK(gender in ('M','F','O')), street TEXT, city TEXT NOT NULL, state TEXT NOT NULL, pin INTEGER NOT NULL, password TEXT)");
        MyDB.execSQL("create Table health_tracker(time_stamp TIMESTAMP PRIMARY KEY DEFAULT CURRENT_TIMESTAMP, patient_id INTEGER NOT NULL, bp INTEGER, oxy INTEGER CHECK(oxy>=0 and oxy<=100), weight INTEGER, height INTEGER, FOREIGN KEY(patient_id) REFERENCES patient(patient_id))");
        MyDB.execSQL("create Table record(record_id INTEGER PRIMARY KEY AUTOINCREMENT, doctor_id INTEGER, patient_id INTEGER, symptom TEXT, prescription TEXT, FOREIGN KEY(patient_id) REFERENCES patient(patient_id), FOREIGN KEY(doctor_id) REFERENCES doctor(doctor_id) )");
        MyDB.execSQL("create Table doctor(doctor_id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT NOT NULL, last_name TEXT, age INTEGER CHECK(age >= 0 and age <= 120), gender TEXT CHECK(gender in ('M','F','O')),  street TEXT, city TEXT NOT NULL, state TEXT NOT NULL, pin INTEGER NOT NULL, department TEXT NOT NULL, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists patient");
        MyDB.execSQL("drop Table if exists health_tracker");
        MyDB.execSQL("drop Table if exists record");
        MyDB.execSQL("drop Table if exists doctor");
    }
    //(first_name,last_name,age,street,city,state,pin,gender,password)
    public Person insertPatientData(String first_name, String last_name, String age,String street, String city,String state,String pin, String gender,  String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("age", Integer.parseInt(age));
        contentValues.put("gender", gender);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("pin", pin);
        contentValues.put("password", password);
        long result = MyDB.insert("patient", null, contentValues);
        SQLiteDatabase MyDB2 = this.getReadableDatabase();
        Cursor c = MyDB2.rawQuery("SELECT last_insert_rowid()", null);
        if(c.moveToFirst()) {
            Integer id = c.getInt(0);
            return new Person(id,true);
        }
        else
            return new Person(0,false);
    }

    public Person insertDoctorData(String first_name, String last_name, String age, String gender, String street, String city, String state, String pin, String department,  String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("age", Integer.parseInt(age));
        contentValues.put("gender", gender);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("pin", Integer.parseInt(pin));
        contentValues.put("department", department);
        contentValues.put("password", password);
        long result = MyDB.insert("doctor", null, contentValues);
        SQLiteDatabase MyDB2 = this.getReadableDatabase();
        Cursor c = MyDB2.rawQuery("SELECT last_insert_rowid()", null);
        if(c.moveToFirst()) {
            Integer id = c.getInt(0);
            return new Person(id,true);
        }
        else
            return new Person(0,false);
    }

    public void insertApp(String patient_id, String doctor_id, String symptom){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("doctor_id", Integer.parseInt(doctor_id));
        contentValues.put("patient_id", Integer.parseInt(patient_id));
        contentValues.put("symptom", symptom);
        long result = MyDB.insert("record", null, contentValues);
    }

    public Boolean insertTracker(Integer id, String bp, String oxy, String weight, String height)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("patient_id",id);
        contentValues.put("bp", Integer.parseInt(bp));
        contentValues.put("oxy", Integer.parseInt(oxy));
        contentValues.put("weight", Integer.parseInt(weight));
        contentValues.put("height", Integer.parseInt(height));
        long result = MyDB.insert("health_tracker", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else return true;
    }

    public void insertPre(String record_id, String prescription, String doctor_id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from record where record_id = " +Integer.parseInt(record_id)+" and doctor_id = "+Integer.parseInt(doctor_id), null);
        cursor.moveToFirst();
        ContentValues cv = new ContentValues();
        cv.put("record_id", Integer.parseInt(record_id));
        cv.put("doctor_id", Integer.parseInt(cursor.getString(cursor.getColumnIndex("doctor_id"))) );
        cv.put("patient_id", Integer.parseInt(cursor.getString(cursor.getColumnIndex("patient_id"))) );
        cv.put("symptom", cursor.getString(cursor.getColumnIndex("symptom")));
        cv.put("prescription",prescription);
        MyDB.update("record", cv, record_id + " = " + Integer.parseInt(record_id), null);
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String id, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from patient where patient_id = " + Integer.parseInt(id) + " and password = " + "'"+password+"'", null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkdoctorlogin(String id, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from doctor where doctor_id = " + Integer.parseInt(id) + " and password = " + "'"+password+"'", null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkpatdoc(String pid, String did) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from record where doctor_id = " + Integer.parseInt(did) + " and patient_id = " + Integer.parseInt(pid), null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String listdoctor(){
        String listd = "";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from doctor", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(cursor.getColumnIndex("doctor_id"))!=null)
            {
                listd+= cursor.getString(cursor.getColumnIndex("doctor_id"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("first_name"));
                listd+= " ";
                listd+= cursor.getString(cursor.getColumnIndex("last_name"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("department"));
                listd+="\n";
            }
            cursor.moveToNext();
        }
        MyDB.close();
        return listd;
    }

    public String listtracker(String id){
        String listd = "";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from health_tracker where patient_id = " + Integer.parseInt(id), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(cursor.getColumnIndex("patient_id"))!=null)
            {
                listd+= cursor.getString(cursor.getColumnIndex("patient_id"));
                listd+= "  | ";
                listd+= cursor.getString(cursor.getColumnIndex("time_stamp"));
                listd+= " | ";
                listd+= cursor.getString(cursor.getColumnIndex("bp"));
                listd+= " | ";
                listd+= cursor.getString(cursor.getColumnIndex("oxy"));
                listd+= " | ";
                listd+= cursor.getString(cursor.getColumnIndex("height"));
                listd+= " | ";
                listd+= cursor.getString(cursor.getColumnIndex("weight"));
                listd+="\n";
            }
            cursor.moveToNext();
        }
        MyDB.close();
        return listd;
    }

    public String listpapp(String id){
        String listd = "";
        SQLiteDatabase MyDB = this.getReadableDatabase();
//        Cursor cur = MyDB.rawQuery("Select * from session",null);
//        cur.moveToFirst();
        int pid = Integer.parseInt(id);
        Cursor cursor = MyDB.rawQuery("Select * from record where patient_id = " + pid, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(cursor.getColumnIndex("patient_id"))!=null)
            {
                listd+= cursor.getString(cursor.getColumnIndex("record_id"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("doctor_id"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("symptom"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("prescription"));
                listd+="\n";
            }
            cursor.moveToNext();
        }
        MyDB.close();
        return listd;
    }

    public String listdapp(String id){
        String listd = "";
        SQLiteDatabase MyDB = this.getReadableDatabase();
//        Cursor cur = MyDB.rawQuery("Select * from session",null);
//        cur.moveToFirst();
        Cursor cursor = MyDB.rawQuery("Select * from record where doctor_id = " + Integer.parseInt(id), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(cursor.getColumnIndex("prescription"))==null)
            {
                listd+= cursor.getString(cursor.getColumnIndex("record_id"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("patient_id"));
                listd+= "   |   ";
                listd+= cursor.getString(cursor.getColumnIndex("symptom"));
                listd+="\n";
            }
            cursor.moveToNext();
        }
        MyDB.close();
        return listd;
    }
}

