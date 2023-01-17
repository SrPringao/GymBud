package com.example.gymbud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.gymbud.db.Entidades.PersonInfo;

import java.util.ArrayList;


public class DbQuery extends DbHelper {
    Context context;

    public DbQuery(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long InsertarInfoPerson(int Assists, Double CurrentWeight, Double WeightGoal, Double Height, int Gender, int Age) {
        long id = 0;
        try {


            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Assists", Assists);
            values.put("CurrentWeight", CurrentWeight);
            values.put("WeightGoal", WeightGoal);
            values.put("Height", Height);
            values.put("Gender", Gender);
            values.put("Age", Age);


            id = db.insert(TABLE_PERSONINFO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
    public ArrayList<PersonInfo> MostrarPersonInfo(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PersonInfo> ListaPerson = new ArrayList<>();
        PersonInfo personInfo = null;
        Cursor cursorperson = null;

        cursorperson = db.rawQuery("SELECT * FROM " + TABLE_PERSONINFO, null);
        if (cursorperson.moveToFirst()){
            do{
                personInfo = new PersonInfo();
                personInfo.setUserId(cursorperson.getInt(0));
                personInfo.setAssists(cursorperson.getInt(1));
                personInfo.setDayRoutine(cursorperson.getInt(2));
                personInfo.setCurrentWeight(cursorperson.getDouble(3));
                personInfo.setWeightGoal(cursorperson.getDouble(4));
                personInfo.setHeight(cursorperson.getDouble(5));
                personInfo.setGender(cursorperson.getInt(6));
                personInfo.setAge(cursorperson.getInt(7));
                personInfo.setPhrase(cursorperson.getString(8));

                ListaPerson.add(personInfo);
            }while (cursorperson.moveToNext());
        }
        cursorperson.close();
        return ListaPerson;
    }
}

