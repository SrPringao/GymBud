package com.example.gymbud.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "GBLocal.db";
    public static final String TABLE_PERSONINFO = "PERSONINFO";
    public static final String TABLE_ROUTINENAME = "ROUTINENAME";
    public static final String TABLE_PHRASE = "PHRASE";
    public static final String TABLE_ROUTINE = "ROUTINE";
    public static final String TABLE_EXERCISE = "EXERCISE";
    public static final String TABLE_STATS = "STATS";
    public static final String TABLE_MUSCULARGROUP = "MUSCULARGROUP";
    public static final String TABLE_TOOLS = "TOOLS";
    public static final String TABLE_CATEGORY = "CATEGORY";
    public static final String TABLE_DIFFICULTY = "DIFFICULTY";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERSONINFO + "(" + " UserId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Assists INTEGER ," +
                "DayRoutine INTEGER REFERENCES ROUTINENAME (ID)," +
                "CurrentWeight REAL ," +
                "WeightGoal REAL ," +
                "Height REAL ," +
                "Gender INTEGER ," +
                "Age INTEGER ," +
                "Phrase TEXT REFERENCES PHRASE (Id))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ROUTINENAME +"(" + "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Routine TEXT REFERENCES ROUTINE (RoutineName))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PHRASE + "(" + "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Motivation TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ROUTINE + "(" + "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "RoutineName TEXT UNIQUE NOT NULL," +
                "EXC1 TEXT REFERENCES EXERCISE (Id)," +
                "EXC2 TEXT REFERENCES EXERCISE (Id)," +
                "EXC3 TEXT REFERENCES EXERCISE (Id)," +
                "EXC4 TEXT REFERENCES EXERCISE (Id)," +
                "EXC5 TEXT REFERENCES EXERCISE (Id)," +
                "EXC6 TEXT REFERENCES EXERCISE (Id)," +
                "EXC7 TEXT REFERENCES EXERCISE (Id)," +
                "EXC8 TEXT REFERENCES EXERCISE (Id))");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_EXERCISE + "(" + "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT," +
                "MuscularGroup INTEGER REFERENCES MUSCULARGROUP (ID_Muscle)," +
                "Focus TEXT," +
                "ForeSeeing TEXT," +
                "Execution TEXT," +
                "Details TEXT," +
                "Image BLOB," +
                "Tool INTEGER REFERENCES TOOLS (ID_Tools)," +
                "Category INTEGER REFERENCES CATEGORY (ID_Cat)," +
                "Difficulty INTEGER REFERENCES DIFFICULTY (ID_Difficulty)," +
                "Stats INTEGER REFERENCES STATS (ID_Stats))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_STATS + "("+"ID_Stats INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Weight INTEGER," +
                "Reps INTEGER," +
                "Time REAL," +
                "Date TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MUSCULARGROUP + "(" + "ID_Muscle INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Muscle TEXT)" );

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TOOLS + "("+"ID_Tools INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Tool TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CATEGORY + "("+"ID_Cat INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Category TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DIFFICULTY + "("+ "ID_Difficulty INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Difficulty TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
