package com.example.gymbud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.gymbud.db.Entidades.Exercises;
import com.example.gymbud.db.Entidades.PersonInfo;
import com.example.gymbud.db.Entidades.Phrase;

import java.util.ArrayList;


public class DbQuery extends DbHelper {
    Context context;

    public DbQuery(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long InsertarInfoPerson(int UserId,int Assists,int DayRoutine, Double CurrentWeight, Double WeightGoal, Double Height, int Gender, int Age,String Phrase) {
        long id = 0;
        try {


            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("UserId", UserId);
            values.put("Assists", Assists);
            values.put("DayRoutine", DayRoutine);
            values.put("CurrentWeight", CurrentWeight);
            values.put("WeightGoal", WeightGoal);
            values.put("Height", Height);
            values.put("Gender", Gender);
            values.put("Age", Age);
            values.put("Phrase",Phrase);


            id = db.insert(TABLE_PERSONINFO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public PersonInfo verinfo(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        PersonInfo personInfo = null;
        Cursor cursorInfo;
        cursorInfo = db.rawQuery("SELECT * FROM " + TABLE_PERSONINFO + " WHERE UserId = " + id, null);
        if(cursorInfo.moveToFirst()){
            personInfo = new PersonInfo();
            personInfo.setUserId(cursorInfo.getInt(0));
            personInfo.setAssists(cursorInfo.getInt(1));
            personInfo.setDayRoutine(cursorInfo.getInt(2));
            personInfo.setCurrentWeight(cursorInfo.getDouble(3));
            personInfo.setWeightGoal(cursorInfo.getDouble(4));
            personInfo.setHeight(cursorInfo.getDouble(5));
            personInfo.setGender(cursorInfo.getInt(6));
            personInfo.setAge(cursorInfo.getInt(7));
            personInfo.setPhrase(cursorInfo.getString(8));
        }
        cursorInfo.close();
        return personInfo;
    }
    public Phrase verFrase(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Phrase frase = null;
        Cursor cursorsote;
        cursorsote = db.rawQuery("SELECT * FROM " + TABLE_PHRASE + " WHERE Id = " + id,null);
        if (cursorsote.moveToFirst()) {
            frase = new Phrase();
            frase.setId(cursorsote.getInt(0));
            frase.setMotivation(cursorsote.getString(1));
        }
        cursorsote.close();
        return frase;
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

    public  ArrayList<Exercises> MostrarEjercicios(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Exercises> ListaEjercicios = new ArrayList<>();
        Exercises ejercicios = null;
        Cursor cursorejercicios = null;
        cursorejercicios = db.rawQuery("SELECT * FROM " + TABLE_EXERCISE + " WHERE MuscularGroup = " + id , null);
        if (cursorejercicios.moveToFirst()){
            do{
                ejercicios = new Exercises();
                ejercicios.setId(cursorejercicios.getInt(0));
                ejercicios.setName(cursorejercicios.getString(1));
                ejercicios.setMuscularGroup(cursorejercicios.getInt(2));
                ejercicios.setFocus(cursorejercicios.getString(3));
                ejercicios.setForeSeeing(cursorejercicios.getString(4));
                ejercicios.setExecution(cursorejercicios.getString(5));
                ejercicios.setDetails(cursorejercicios.getString(6));
                ejercicios.setImage(cursorejercicios.getBlob(7));
                ejercicios.setTool(cursorejercicios.getInt(8));
                ejercicios.setCategory(cursorejercicios.getInt(9));
                ejercicios.setDifficulty(cursorejercicios.getInt(10));
                ejercicios.setStats(cursorejercicios.getInt(11));

                ListaEjercicios.add(ejercicios);
            }while (cursorejercicios.moveToNext());
        }
        cursorejercicios.close();
        return ListaEjercicios;
    }


}