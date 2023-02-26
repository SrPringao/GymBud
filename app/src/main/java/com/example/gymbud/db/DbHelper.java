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
                "Assists INTEGER NOT NULL," +
                "DayRoutine INTEGER REFERENCES ROUTINENAME (ID)," +
                "CurrentWeight REAL NOT NULL," +
                "WeightGoal REAL NOT NULL," +
                "Height REAL NOT NULL," +
                "Gender INTEGER NOT NULL," +
                "Age INTEGER NOT NULL," +
                "Phrase TEXT REFERENCES PHRASE (Id))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ROUTINENAME + "(" + "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EXERCISE + "(" + "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
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

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_STATS + "(" + "ID_Stats INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Weight INTEGER," +
                "Reps INTEGER," +
                "Reps2 INTEGER,"+
                "Time REAL," +
                "Date TEXT," +
                "IdEjercicio INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MUSCULARGROUP + "(" + "ID_Muscle INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Muscle TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TOOLS + "(" + "ID_Tools INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Tool TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CATEGORY + "(" + "ID_Cat INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Category TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DIFFICULTY + "(" + "ID_Difficulty INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Difficulty TEXT)");

        //Grupos musculares
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "1,'Hombro')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "2,'Bicep')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "3,'Pecho')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "4,'Abs')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "5,'Oblicuos')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "6,'Antebrazo')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "7,'Cuadriceps')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "8,'Trapecios')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "9,'Dorsal')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "10,'Tricep')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "11,'Espalda media')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "12,'Espalda baja')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "13,'Gluteo')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "14,'Femoral')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MUSCULARGROUP + " VALUES " + "(" + "15,'Pantorrilla')");


        //Tablita herramientas
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "1,'Polea')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "2,'Barra')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "3,'Mancuerna')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "4,'Banco')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "5,'Maquina')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "6,'Polea/Banco')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "7,'Barra/Banco')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "8,'Mancuerna/Banco')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_TOOLS + " VALUES " + "(" + "10,'Maquina/Banco')");


        //Tablita categoria
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORY + " VALUES " + "(" + "1,'Ejercicio Cardiovascular')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORY + " VALUES " + "(" + "2,'Fuerza')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORY + " VALUES " + "(" + "3,'Hipertrofia')");


        //Tablita Dificultad
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_DIFFICULTY + " VALUES " + "(" + "1,'Facil')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_DIFFICULTY + " VALUES " + "(" + "2,'Medio')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_DIFFICULTY + " VALUES " + "(" + "3,'Dificil')");


        //Tablita Stats
      //  sqLiteDatabase.execSQL("INSERT INTO " + TABLE_STATS + "("+"Weight,Reps,Time,Date)" + " VALUES " + "(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0')");


//Hombro
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Remo en polea con cuerda',1,'Fortalecimiento muscular','Ajusta la altura de la polea según tu altura y coloca una cuerda en cada lado de la polea','Eleva las manos hacia los lados, manteniendo los codos ligeramente doblados y llevando las manos hacia arriba hasta la altura de los hombros\n" +
                "Baja las manos lentamente a la posición inicial y repite el ejercicio\n','Asegúrate de mantener la espalda recta y el abdomen contraído durante todo el ejercicio\n" +
                "Puedes variar la intensidad del ejercicio ajustando la resistencia de la polea\n" +
                "Puedes realizar el ejercicio con un agarre prono (palmas hacia abajo) o supino (palmas hacia arriba) para trabajar diferentes partes de los músculos del hombro\n','Image',1,2,2,1)");


        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones posteriores de pie con polea baja',1,'Fortalecimiento de los hombros','Coloca una polea baja en una máquina de cables o en un anclaje de pared.\n" +
                "Coloca una banda de resistencia alrededor de tus pies.\n" +
                "Coloca una mancuerna o disco de peso en la polea.','De pie frente a la polea, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta las manijas de la polea con las manos hacia abajo.\n" +
                "Eleva los brazos hacia atrás y hacia arriba, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar la resistencia de la banda o el peso de la mancuerna para ajustar la dificultad.','Image',1,2,3,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones posteriores de pie con polea baja',1,'Fortalecimiento de los hombros','Coloca una polea baja en una máquina de cables o en un anclaje de pared.\n" +
                "Coloca una banda de resistencia alrededor de tus pies.\n" +
                "Coloca una mancuerna o disco de peso en la polea.','De pie frente a la polea, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta las manijas de la polea con las manos hacia abajo.\n" +
                "Eleva los brazos hacia atrás y hacia arriba, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar la resistencia de la banda o el peso de la mancuerna para ajustar la dificultad.','Image',1,2,3,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Remo Vertical con polea',1,'Fortalecimiento de los hombros y la espalda superior','Coloca una polea en una máquina de cables o en un anclaje de pared a una altura que te permita sujetarla con los brazos extendidos.\n" +
                "Coloca una mancuerna o disco de peso en la polea.\n" +
                "De pie frente a la polea, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta las manijas de la polea con las manos hacia abajo.','Eleva los brazos hacia arriba y hacia atrás, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de la mancuerna para ajustar la dificultad.','Image',1,2,2,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press Hombro sentado en polea',1,'Fortalecimiento de los hombros y la espalda superior','Coloca una polea en una máquina de cables o en un anclaje de pared a una altura que te permita sujetarla con los brazos extendidos.\n" +
                "Coloca una mancuerna o disco de peso en la polea.\n" +
                "Siéntate en un banco frente a la polea, con las piernas ligeramente separadas y los pies apoyados en el suelo. Sujeta las manijas de la polea con las manos hacia abajo.','Eleva los brazos hacia arriba y hacia atrás, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de la mancuerna para ajustar la dificultad.','Image',1,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Remo vertical con barra',1,'Fortalecimiento de los hombros y la espalda superior','Coloca una barra de remo o mancuernas en el suelo frente a ti.\n" +
                "Coloca una mancuerna o disco de peso en cada extremo de la barra.\n" +
                "De pie frente a la barra, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta la barra con las manos hacia abajo.','Eleva los brazos hacia arriba y hacia atrás, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de la mancuerna para ajustar la dificultad.','Image',2,2,3,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press frontal de pie con barra',1,'Fortalecimiento de los hombros y la espalda superior','Coloca una barra de remo o mancuernas en el suelo frente a ti.\n" +
                "Coloca una mancuerna o disco de peso en cada extremo de la barra.\n" +
                "De pie frente a la barra, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta la barra con las manos hacia arriba.','Eleva los brazos hacia arriba y hacia adelante, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de la mancuerna para ajustar la dificultad.','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevación frontal con barra',1,'Fortalecimiento de los hombros y la espalda superior','Coloca una barra de remo o mancuernas en el suelo frente a ti.\n" +
                "Coloca una mancuerna o disco de peso en cada extremo de la barra.\n" +
                "De pie frente a la barra, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta la barra con las manos hacia arriba.','Eleva los brazos hacia arriba y hacia adelante, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de la mancuerna para ajustar la dificultad.','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones laterales de pie con mancuernas',1,'Fortalecimiento de los hombros','Coloca dos mancuernas de un peso adecuado para ti en el suelo a tus lados. De pie, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta cada mancuerna con una mano.','Eleva los brazos hacia los lados hasta que estén alineados con los hombros, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta. Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio. También puedes variar el peso de las mancuernas para ajustar la dificultad.','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Remo vertical con mancuernas',1,'Fortalecimiento de los hombros y la espalda superior','Coloca dos mancuernas de un peso adecuado para ti en el suelo frente a ti.\n" +
                "De pie frente a las mancuernas, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta cada mancuerna con una mano hacia abajo.','Eleva los brazos hacia arriba y hacia atrás, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "Lentamente regresa a la posición inicial y repite el movimiento.','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de las mancuernas para ajustar la dificultad.','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones frontales alternas de pie con mancuernas',1,'Fortalecimiento del hombro','Pararse derecho con las piernas ligeramente separadas y los pies paralelos al ancho de los hombros.\n" +
                "Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Elevar una de las mancuernas hacia el frente del cuerpo con el brazo extendido, manteniendo el codo pegado al costado.\n" +
                "Bajar la mancuerna a la posición inicial y repetir el movimiento con la otra mano.\n" +
                "Continuar alternando el movimiento de ambos brazos durante el tiempo o repeticiones establecidas.','Mantener el cuerpo firme y evitar balancearse durante la ejecución del ejercicio.\n" +
                "Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press frontal con mancuernas y agarre neutro',1,'Fortalecimiento del hombro y tríceps','Pararse derecho con las piernas ligeramente separadas y los pies paralelos al ancho de los hombros.\n" +
                "Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo y las palmas de las manos mirando hacia el cuerpo (agarre neutro).\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Elevar ambas mancuernas hacia el frente del cuerpo con los brazos extendidos, manteniendo el codo pegado al costado.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener el cuerpo firme y evitar balancearse durante la ejecución del ejercicio.\n" +
                "Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',8,2,2,3)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press militar sentado con mancuernas',1,'Fortalecimiento del hombro y tríceps','Sentarse en un banco de entrenamiento con la espalda recta y los pies apoyados en el suelo.\n" +
                "Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo y las palmas de las manos mirando hacia el cuerpo (agarre neutro).\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Elevar ambas mancuernas hacia el frente del cuerpo con los brazos extendidos, manteniendo el codo pegado al costado.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',8,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press Arnold en banco',1,'Fortalecimiento del hombro y tríceps','Sentarse en un banco de entrenamiento con la espalda recta y los pies apoyados en el suelo.\n" +
                "Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo y las palmas de las manos mirando hacia el cuerpo (agarre neutro).\n" +
                "Elevar las mancuernas hasta la altura del hombro con los codos pegados al costado.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Elevar las mancuernas hacia arriba y hacia atrás en dirección a las orejas, con los codos pegados al costado y las manos ligeramente inclinadas hacia afuera.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',3,2,2,2)");


        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones posteriores con banco inclinado',1,'Fortalecimiento del hombro y espalda superior','Acostarse boca abajo en un banco inclinado con la cabeza hacia abajo y los brazos colgando hacia el suelo.\n" +
                "Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo y las palmas de las manos mirando hacia el cuerpo (agarre neutro).\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Elevar ambas mancuernas hacia atrás en dirección a las nalgas con los brazos extendidos, manteniendo el codo pegado al costado.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y evitar balancearse durante la ejecución del ejercicio.\n" +
                "Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',8,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones laterales sentado con mancuernas',1,'Fortalecimiento del hombro','Sentarse en un banco de entrenamiento con la espalda recta y los pies apoyados en el suelo. Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo y las palmas de las manos mirando hacia el cuerpo (agarre neutro). Elevar las mancuernas hasta la altura del hombro con los codos pegados al costado. Mantener la postura correcta durante todo el ejercicio.','Elevar una de las mancuernas hacia el lado del cuerpo con el brazo extendido, manteniendo el codo pegado al costado. Bajar la mancuerna a la posición inicial y repetir el movimiento con la otra mano. Continuar alternando el movimiento de ambos brazos durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio. Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas. Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',8,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de hombro en máquina',1,'Fortalecimiento del hombro y tríceps','Sentarse en una máquina de press de hombro con la espalda apoyada contra el respaldo y los pies apoyados en el suelo.\n" +
                "Colocar los brazos en los soportes de la máquina con las palmas de las manos mirando hacia arriba.\n" +
                "Ajustar los soportes para que queden a la altura adecuada para el tamaño del individuo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Empujar hacia arriba con los brazos para levantar los pesos de la máquina.\n" +
                "Bajar los pesos a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada contra el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar hacia arriba y hacia adentro al bajar los pesos.\n" +
                "Ajustar el peso de la máquina según la capacidad del individuo.','Image',5,3,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevación lateral en máquina',1,'Fortalecimiento del hombro','Sentarse en una máquina de elevación lateral con la espalda apoyada contra el respaldo y los pies apoyados en el suelo.\n" +
                "Colocar los brazos en los soportes de la máquina con las palmas de las manos mirando hacia abajo.\n" +
                "Ajustar los soportes para que queden a la altura adecuada para el tamaño del individuo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Empujar hacia afuera con los brazos para levantar los pesos de la máquina.\n" +
                "Bajar los pesos a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada contra el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar hacia afuera y hacia adentro al bajar los pesos.\n" +
                "Ajustar el peso de la máquina según la capacidad del individuo.','Image',5,2,3,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Peck Fly invertido',1,'Fortalecimiento del hombro y pectoral','Colgarse de una barra de dominadas con las manos en pronación (palmas hacia abajo) y los brazos extendidos.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Abrir los brazos hacia los lados del cuerpo con las palmas de las manos hacia arriba, como si estuviera abriendo un paraguas.\n" +
                "Cerrar los brazos volviendo a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al abrir los brazos y hacia adentro al cerrarlos.\n" +
                "Ajustar la dificultad de la ejecución cambiando la inclinación del cuerpo y la distancia entre las manos.','Image',1,2,2,3)");


//pechamen
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Cruzamiento de cables',3,'Fortalecimiento del pecho','Sentarse en un banco con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo. Agarrar las asas de los cables con ambas manos y mantener los codos pegados al cuerpo. Mantener la postura correcta durante todo el ejercicio.','Cruzar las asas hacia el centro del cuerpo mientras mantiene la posición sentado. Regresar las asas a la posición inicial y repetir el movimiento. Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio. Respirar hacia fuera al cruzar las asas hacia el centro del cuerpo y hacia adentro al regresarlas a la posición inicial. Ajustar el peso de los cables según la capacidad del individuo.','Image',2,3,2,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Cruces en polea baja pecho',3,'Fortalecimiento del pecho','Sentarse en un banco con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo. Agarrar las asas de la polea baja con ambas manos y mantener los codos pegados al cuerpo. Mantener la postura correcta durante todo el ejercicio.','Cruzar las asas hacia el centro del cuerpo mientras mantiene la posición sentado. Regresar las asas a la posición inicial y repetir el movimiento. Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio. Respirar hacia fuera al cruzar las asas hacia el centro del cuerpo y hacia adentro al regresarlas a la posición inicial. Ajustar el peso de la polea según la capacidad del individuo.','Image',1,2,2,3)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Aperturas en polea en banco',3,'Fortalecimiento del pecho','Sentarse en un banco con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo. Agarrar las asas de la polea con ambas manos y mantener los codos pegados al cuerpo. Mantener la postura correcta durante todo el ejercicio.','Abrazar las asas hacia el centro del cuerpo mientras mantiene la posición sentado. Regresar las asas a la posición inicial y repetir el movimiento. Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio. Respirar hacia fuera al abrazar las asas hacia el centro del cuerpo y hacia adentro al regresarlas a la posición inicial. Ajustar el peso de la polea según la capacidad del individuo.','Image',1,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca plano con polea',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca plana con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo. Agarrar las asas de la polea con ambas manos y mantener los codos pegados al cuerpo. Mantener la postura correcta durante todo el ejercicio.','Empujar las asas hacia arriba mientras mantiene la posición tumbado. Bajar las asas a la posición inicial y repetir el movimiento. Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio. Respirar hacia fuera al empujar las asas hacia arriba y hacia adentro al bajarlas. Ajustar el peso de la polea según la capacidad del individuo.','Image',1,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca plano con barra',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca plana con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una barra con ambas manos y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.\n','Empujar la barra hacia arriba mientras mantiene la posición tumbado.\n" +
                "Bajar la barra a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.\n','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar la barra hacia arriba y hacia adentro al bajarla.\n" +
                "Ajustar el peso de la barra según la capacidad del individuo.\n','Image',2,2,3,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca inclinada con barra',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca inclinada con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una barra con ambas manos y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.\n','Empujar la barra hacia arriba mientras mantiene la posición tumbado.\n" +
                "Bajar la barra a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.\n','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar la barra hacia arriba y hacia adentro al bajarla.\n" +
                "Ajustar el peso de la barra según la capacidad del individuo.\n','Image',2,2,3,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca declinada con barra',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca declinada con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una barra con ambas manos y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.\n','Empujar la barra hacia arriba mientras mantiene la posición tumbado.\n" +
                "Bajar la barra a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.\n','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar la barra hacia arriba y hacia adentro al bajarla.\n" +
                "Ajustar el peso de la barra según la capacidad del individuo.\n','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca plano con mancuernas',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca plana con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una mancuerna con cada mano y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Empujar las mancuernas hacia arriba mientras mantiene la posición tumbado.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar las mancuernas hacia arriba y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',3,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca inclinada con mancuernas',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca inclinada con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una mancuerna con cada mano y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Empujar las mancuernas hacia arriba mientras mantiene la posición tumbado.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar las mancuernas hacia arriba y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',3,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de banca declinada con mancuernas',3,'Fortalecimiento del pecho, hombros y tríceps','Acostarse en una banca declinada con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una mancuerna con cada mano y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Empujar las mancuernas hacia arriba mientras mantiene la posición tumbado.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar las mancuernas hacia arriba y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',3,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Aperturas con mancuernas en banco plano',3,'Fortalecimiento del pecho y hombros','Acostarse en un banco plano con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo.\n" +
                "Sujetar una mancuerna con cada mano y mantener los codos pegados al cuerpo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Abrir los brazos a los lados mientras mantiene la posición tumbado.\n" +
                "Cerrar los brazos y volver a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al abrir los brazos y hacia adentro al cerrarlos.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Pull over con mancuerna en banco',3,'Fortalecimiento del pecho, espalda y hombros','Sentarse en un banco con las piernas apoyadas en el suelo y los hombros apoyados en el respaldo. Sujetar una mancuerna con ambas manos y mantener los codos pegados al cuerpo. Mantener la postura correcta durante todo el ejercicio.','Extender los brazos por encima de la cabeza mientras mantiene la posición sentada. Bajar los brazos a la posición inicial y repetir el movimiento. Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada en el respaldo y los hombros hacia abajo durante todo el ejercicio. Respirar hacia fuera al extender los brazos y hacia adentro al bajarlos. Ajustar el peso de la mancuerna según la capacidad del individuo.','Image',3,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Flexiones con pies elevados',3,'Fortalecimiento del pecho, tríceps y espalda','Colocarse en una posición de flexión con los brazos extendidos y los pies apoyados en una superficie elevada, como un banco o una mesa.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Flexionar los brazos y bajar el cuerpo hacia abajo hasta que el pecho toque el suelo.\n" +
                "Empujar hacia arriba para volver a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al bajar el cuerpo y hacia adentro al subir.\n" +
                "Ajustar la dificultad de la ejecución cambiando la altura de los pies o la distancia entre las manos.','Image',4,2,2,3)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press vertical sentado en máquina de pecho',3,'Fortalecimiento del pecho y tríceps','Sentarse en una máquina de press vertical con la espalda apoyada contra el respaldo y los pies apoyados en el suelo.\n" +
                "Colocar los brazos en los soportes de la máquina con las palmas de las manos mirando hacia abajo.\n" +
                "Ajustar los soportes para que queden a la altura adecuada para el tamaño del individuo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Empujar hacia arriba con los brazos para levantar los pesos de la máquina.\n" +
                "Bajar los pesos a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda apoyada contra el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar hacia arriba y hacia adentro al bajar los pesos.\n" +
                "Ajustar el peso de la máquina según la capacidad del individuo.','Image',5,2,2,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Aperturas en máquina contractora',3,'Fortalecimiento del pecho y tríceps','Sentarse en una máquina de aperturas con la espalda apoyada contra el respaldo y los pies apoyados en el suelo.\n" +
                "Colocar los brazos en los soportes de la máquina con las palmas de las manos mirando hacia abajo.\n" +
                "Ajustar los soportes para que queden a la altura adecuada para el tamaño del individuo.\n" +
                "Mantener la postura correcta durante todo el ejercicio.\n','Empujar hacia afuera con los brazos para levantar los pesos de la máquina.\n" +
                "Bajar los pesos a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.\n','Mantener la espalda apoyada contra el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar hacia afuera y hacia adentro al bajar los pesos.\n" +
                "Ajustar el peso de la máquina según la capacidad del individuo.\n','Image',5,3,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Fondos en paralelas para pecho',3,'Fortalecimiento del pecho, tríceps y hombro','Colocarse de pie frente a un par de barras paralelas a la altura del pecho, con los brazos extendidos y las palmas de las manos apoyadas en las barras.\n" +
                "Mantener la postura correcta durante todo el ejercicio.','Flexionar los brazos y bajar el cuerpo hacia abajo hasta que los brazos queden formando un ángulo de 90 grados o hasta que el pecho toque las barras.\n" +
                "Empujar hacia arriba para volver a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al bajar el cuerpo y hacia adentro al subir.\n" +
                "Ajustar la distancia entre las barras según la capacidad del individuo.','Image',2,2,2,2)");


//bicep
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps tipo martillo con polea',2,'Hipertrofia','Colocar la altura de la polea a la altura deseada, colocar los pies entre la polea para tener una mejor estabilidad.\n" +
                "Tomar los agarres de la polea con la mano, con los brazos estirados, las muñecas en una posición neutra y los codos pegados al cuerpo.','1. Inspirar y mantener el core firme, contraer el bíceps y levantar los brazos.\n" +
                "2. Expirar al llegar a la posición superior.\n" +
                "3. Lentamente bajar los brazos hasta llegar a la posición inicial, manteniendo el core firme.','Mantener la espalda recta durante todo el ejercicio.\n" +
                "Evitar el balanceo de los brazos y el uso de la inercia.\n" +
                "Realizar el ejercicio de manera controlada.','Image',1,2,2,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps en doble polea alta',2,'Hipertrofia','Colocar las poleas en la altura deseada, colocar los pies entre las poleas para tener una mejor estabilidad. Tomar los agarres de la polea con la mano, con los brazos estirados, las muñecas en una posición neutra y los codos pegados al cuerpo.','Inspirar y mantener el core firme, contraer el bíceps y levantar los brazos. Expirar al llegar a la posición superior. Lentamente bajar los brazos hasta llegar a la posición inicial, manteniendo el core firme.','Mantener la espalda recta durante todo el ejercicio. Evitar el balanceo de los brazos y el uso de la inercia. Realizar el ejercicio de manera controlada.','Image',1,2,2,3)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps con barra en polea',2,'Hipertrofia','Colocar la altura de la polea a la altura deseada, colocar los pies entre la polea para tener una mejor estabilidad.\n" +
                "Tomar la barra de la polea con la mano, con los brazos estirados, las muñecas en una posición neutra y los codos pegados al cuerpo.','Inspirar y mantener el core firme, contraer el bíceps y levantar la barra.\n" +
                "Expirar al llegar a la posición superior.\n" +
                "Lentamente bajar la barra hasta llegar a la posición inicial, manteniendo el core firme.','Mantener la espalda recta durante todo el ejercicio.\n" +
                "Evitar el balanceo de los brazos y el uso de la inercia.\n" +
                "Realizar el ejercicio de manera controlada.','Image',2,3,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps 21s con barra en polea',2,'Hipertrofia','Colocar la altura de la polea a la altura deseada, colocar los pies entre la polea para tener una mejor estabilidad.\n" +
                "Tomar la barra de la polea con la mano, con los brazos estirados, las muñecas en una posición neutra y los codos pegados al cuerpo.','Realizar 7 repeticiones desde la parte alta del movimiento hasta la parte baja.\n" +
                "Realizar 7 repeticiones desde la parte baja del movimiento hasta la parte alta.\n" +
                "Realizar 7 repeticiones completando el recorrido completo.','Mantener la espalda recta durante todo el ejercicio.\n" +
                "Evitar el balanceo de los brazos y el uso de la inercia.\n" +
                "Realizar el ejercicio de manera controlada.','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps con barra',2,'Concentrado','Agarrar la barra con un agarre ligeramente más amplio que el ancho de los hombros, colocar los pies separados al ancho de los hombros, enderezar la espalda y contraer el abdomen.','Tomar la barra con los brazos extendidos y los codos cerca del cuerpo. Flexionar los brazos para elevar la barra hacia el hombro. Inspirar al principio de la fase de elevación. Asegurarse de que los codos queden fijos durante la fase de elevación. Expirar al final de la fase de elevación. Retornar a la posición inicial bajando la barra con suavidad y control.','Asegurarse de mantener la espalda recta y no doblarla durante el ejercicio. Mantener los codos quietos, no los mueva hacia adelante o hacia atrás. Inspirar al principio de la fase de elevación. Expirar al final de la fase de elevación. Bajar la barra con suavidad y control.','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps en banco Scott con barra',2,'Concentrado','Colocarse en el banco Scott con la espalda apoyada en un ángulo de 45 grados, agarrar la barra con un agarre ligeramente más amplio que el ancho de los hombros.','1. Comenzar con los brazos extendidos y los codos cerca del cuerpo. \n2.Flexionar los brazos para elevar la barra hacia el hombro.\n3.Inspirar al principio de la fase de elevación.\n4.Asegurarse de que los codos queden fijos durante la fase de elevación.\n5.Expirar al final de la fase de elevación.\n6.Retornar a la posición inicial bajando la barra con suavidad y control.','Asegurarse de mantener la espalda apoyada en el banco en un ángulo de 45 grados durante el ejercicio.\nMantener los codos quietos, no los mueva hacia adelante o hacia atrás.\nInspirar al principio de la fase de elevación.\nExpirar al final de la fase de elevación.\nBajar la barra con suavidad y control.','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Spider curl',2,'Concentrado','Colocar las piernas abajo del banco de spider, asegurarse de que la espalda se encuentre apoyada en el banco, agarrar la barra con un agarre ligeramente más amplio que el ancho de los hombros.','Tomar la barra con los brazos extendidos y los codos cerca del cuerpo. Flexionar los brazos para elevar la barra hacia el hombro. Inspirar al principio de la fase de elevación. Asegurarse de que los codos queden fijos durante la fase de elevación. Expirar al final de la fase de elevación. Retornar a la posición inicial bajando la barra con suavidad y control.','Asegurarse de que la espalda se encuentre apoyada en el banco. Mantener los codos quietos, no los mueva hacia adelante o hacia atrás. Inspirar al principio de la fase de elevación. Expirar al final de la fase de elevación. Bajar la barra con suavidad y control.','Image',2,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps con barra agarre cerrado',2,'Concentrado','Agarrar la barra con un agarre cerrado, colocar los pies separados al ancho de los hombros, enderezar la espalda y contraer el abdomen.','Tomar la barra con los brazos extendidos y los codos cerca del cuerpo. Flexionar los brazos para elevar la barra hacia el hombro. Inspirar al principio de la fase de elevación. Asegurarse de que los codos queden fijos durante la fase de elevación. Expirar al final de la fase de elevación. Retornar a la posición inicial bajando la barra con suavidad y control.','Asegurarse de mantener la espalda recta y no doblarla durante el ejercicio. Mantener los codos quietos, no los mueva hacia adelante o hacia atrás. Inspirar al principio de la fase de elevación. Expirar al final de la fase de elevación. Bajar la barra con suavidad y control.','Image',2,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps alterno con mancuernas',2,'Concentrado','Agarrar una mancuerna en cada mano, colocar los pies separados al ancho de los hombros, enderezar la espalda y contraer el abdomen.','Tomar la mancuerna con el brazo extendido y el codo cerca del cuerpo. Flexionar el brazo para elevar la mancuerna hacia el hombro. Inspirar al principio de la fase de elevación. Asegurarse de que el codo quede fijo durante la fase de elevación. Expirar al final de la fase de elevación. Retornar a la posición inicial bajando la mancuerna con suavidad y control. Repetir el mismo movimiento con el otro brazo.','Asegurarse de mantener la espalda recta y no doblarla durante el ejercicio. Mantener el codo quieto, no lo mueva hacia adelante o hacia atrás. Inspirar al principio de la fase de elevación. Expirar al final de la fase de elevación. Bajar la mancuerna con suavidad y control.','Image',3,2,2,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps alterno tipo martillo con mancuernas',2,'Concentrado','Agarrar una mancuerna en cada mano con un agarre en forma de martillo, colocar los pies separados al ancho de los hombros, enderezar la espalda y contraer el abdomen.','Tomar las mancuernas con los brazos extendidos y los codos cerca del cuerpo.\n" +
                "Flexionar los brazos para elevar las mancuernas hacia el hombro.\n" +
                "Inspirar al principio de la fase de elevación.\n" +
                "Asegurarse de que los codos queden fijos durante la fase de elevación.\n" +
                "Expirar al final de la fase de elevación.\n" +
                "Retornar a la posición inicial bajando las mancuernas con suavidad y control.\n" +
                "Repetir el mismo movimiento con el otro brazo.','Asegurarse de mantener la espalda recta y no doblarla durante el ejercicio.\n" +
                "Mantener los codos quietos, no los mueva hacia adelante o hacia atrás.\n" +
                "Inspirar al principio de la fase de elevación.\n" +
                "Expirar al final de la fase de elevación.\n" +
                "Bajar las mancuernas con suavidad y control.','Image',3,2,2,2)");

//        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps en banco Scott con mancuernas',2,'Concentrado','Colocarse en el banco Scott con la espalda apoyada en un ángulo de 45 grados, agarrar una mancuerna en cada mano, con los brazos extendidos y los codos cerca del cuerpo.','Flexionar los brazos para elevar las mancuernas hacia el hombro.\n" +
//                "Inspirar al principio de la fase de elevación.\n" +
//                "Asegurarse de que los codos queden fijos durante la fase de elevación.\n" +
//                "Expirar al final de la fase de elevación.\n" +
//                "Retornar a la posición inicial bajando las mancuernas con suavidad y control.\n" +
//                "Repetir el mismo movimiento con el otro brazo.\n','Image',3,2,3,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps concentrado con mancuernas',2,'Isométrico','Sentado con una mancuerna en cada mano y con los brazos apoyados sobre la pierna','1. Sostén la mancuerna con ambas manos, con los brazos apoyados sobre la pierna.\n" +
                "2. Dobla los brazos para elevar la mancuerna por encima de la rodilla.\n" +
                "3. Mantén la posición por unos segundos.\n" +
                "4. Luego regresa a la posición inicial.\n','Respira profundamente antes de comenzar cada repetición.\n" +
                "No uses el impulso de tu cuerpo para levantar la mancuerna.\n" +
                "Tómate tu tiempo entre cada repetición.\n','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps inclinado alterno con mancuernas sentado',2,'Isométrico','Sentado con una mancuerna en cada mano, brazos extendidos a los lados y con una ligera inclinación hacia atrás.','Sostén la mancuerna con ambas manos, con los brazos extendidos a los lados y con una ligera inclinación hacia atrás.\n" +
                "Dobla los brazos para elevar la mancuerna por encima de los hombros.\n" +
                "Mantén la posición por unos segundos.\n" +
                "Luego regresa a la posición inicial.\n','Respira profundamente antes de comenzar cada repetición.\n" +
                "Estira los brazos al máximo al subir y bajar la mancuerna.\n" +
                "No uses el impulso de tu cuerpo para levantar la mancuerna.\n" +
                "Tómate tu tiempo entre cada repetición.\n','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bicep en banco con codos atrás',2,'Isométrico','Acostado sobre un banco con los codos atrás y una mancuerna en cada mano.','Sostén la mancuerna con ambas manos, con los codos atrás.\n" +
                "Dobla los brazos para elevar la mancuerna por encima de la cabeza.\n" +
                "Mantén la posición por unos segundos.\n" +
                "Luego regresa a la posición inicial.\n','Respira profundamente antes de comenzar cada repetición.\n" +
                "Estira los brazos al máximo al subir y bajar la mancuerna.\n" +
                "No uses el impulso de tu cuerpo para levantar la mancuerna.\n" +
                "Tómate tu tiempo entre cada repetición.\n','Image',8,3,2,1)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps alterno con mancuernas sentado',2,'Isométrico','Sentado con una mancuerna en cada mano, brazos extendidos a los lados y con una ligera inclinación hacia adelante.','Sostén la mancuerna con ambas manos, con los brazos extendidos a los lados y con una ligera inclinación hacia adelante.\n" +
                "Dobla los brazos para elevar la mancuerna por encima de los hombros.\n" +
                "Mantén la posición por unos segundos.\n" +
                "Luego regresa a la posición inicial.\n','Respira profundamente antes de comenzar cada repetición.\n" +
                "Estira los brazos al máximo al subir y bajar la mancuerna.\n" +
                "No uses el impulso de tu cuerpo para levantar la mancuerna.\n" +
                "Tómate tu tiempo entre cada repetición.\n','Image',3,2,2,2)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Curl de bíceps en maquina en banco Scott',2,'Isométrico','Acostado en un banco Scott con los brazos extendidos a los lados.','Sostén la barra de la maquina con ambas manos, con los brazos extendidos a los lados.\n" +
                "Dobla los brazos para elevar la barra por encima de los hombros.\n" +
                "Mantén la posición por unos segundos.\n" +
                "Luego regresa a la posición inicial.\n','Respira profundamente antes de comenzar cada repetición.\n" +
                "Estira los brazos al máximo al subir y bajar la barra.\n" +
                "No uses el impulso de tu cuerpo para levantar la barra.\n" +
                "Tómate tu tiempo entre cada repetición.\n','Image',5,2,2,1)");


        //Por si queremos rellenar mas
       /*
       sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")
       sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")
       sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")
       sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")
        */

        //Tablita frases
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PHRASE + "("+"Motivation)" + "VALUES "+ "("+"'“Muchos de nosotros no estamos viviendo nuestros sueños porque tememos vivir nuestros miedos” -Les Brown'),"+"("+"'“Nunca dejes que tus recuerdos sean más grandes que tus sueños” – Doug Ivester'),"+"("+"'“Si puedes soñarlo, puedes hacerlo” – Walt Disney'),"+"("+"'“Solo se vive una vez. Pero si lo haces bien, una vez es suficiente” –Mae West'),"+"("+"'“Si no sueltas el pasado, ¿con qué mano agarras el futuro?”'),"+"("+"'“Prefiero morir de pasión que de aburrimiento”'),"+"("+"'“El gran placer de la vida es hacer lo que la gente dice que no puedes”  – Walter Bagehot'),"+"("+"'“Es duro fracasar, pero es todavía peor nunca haber intentado triunfar” -Theodore Roosevel'),"+"("+"'“No hay atajos a cualquier lugar que merezca la pena ir” – Beverly Sills.'),"+"("+"'“Una persona que nunca cometió un error nunca intentó nada nuevo” – Albert Einstein.'),"+"("+"'“Vayas a donde vayas, ve con todo tu corazón” –Confucio.'),"+"("+"'“Siempre parece imposible, hasta que se hace” –Nelson Mandela'),"+"("+"'“No esperes. El momento perfecto nunca llegará.”– Napoleon Hill.'),"+"("+"'“Nunca sabes lo fuerte que eres, hasta que ser fuerte es la única opción que te queda” – Bob Marley'),"+"("+"'“Si haces lo que siempre has hecho, solo llegarás donde siempre has llegado” -Tony Robbins'),"+"("+"'“La lógica te llevará desde A hasta B. La imaginación te llevará a cualquier parte” -Albert Einstein.'),"+"("+"'“En 20 años desde hoy, estarás más decepcionado de las cosas que no hiciste que de las que hiciste” –Mark Twain.'),"+"("+"'“Todos nuestros sueños se pueden hacer realidad si tenemos el coraje de perseguirlos” – Walt Disney.'),"+"("+"'“Todo lo que siempre has querido tener esta en el otro lado del miedo” – George Addair'),"+"("+"'“Un camino de mil millas comienza con un solo paso” – Lao Tzu'),"+"("+"'“El éxito consiste en ir de fracaso en fracaso sin perder el entusiasmo” -Winston Churchill'),"+"("+"'“Hay fracasos de gente que se rindió cuando ya estaba acariciando el éxito” -Thomas Edison'),"+"("+"'“Para poder triunfar, tu deseo de tener éxito debe ser mayor que tu miedo a fracasar” -Bill Cosby'),"+"("+"'“El éxito depende de la preparación previa, y sin ella seguro que llega el fracaso” -Confucio'),"+"("+"'“Todo éxito tiene lugar fuera de la zona de confort” -Michael John Bobak'),"+"("+"'“Para alcanzar el éxito, lo primero que necesitas es confiar en ti mismo”'),"+"("+"'“Los problemas no son señales de alto, sino guías en el camino” – Robert H. Schuller'),"+"("+"'Todos tus sueños se pueden convertir en realidad si tienes el coraje de perseguirlos” -Walt Disney'),"+"("+"'“He fallado una y otra vez en mi vida. Esa es la razón principal de mi éxito” – Michael Jordan'),"+"("+"'“La vida comienza al final de tu zona de confort” –Neale Donald Walsch.'),"+"("+"'“El éxito es caer siete veces y levantarte ocho” –  Proverbio japonés'),"+"("+"'“Siempre da más de que lo esperan de ti” Larry Page'),"+"("+"'“El problema no reside en cometer en un error, sino en no aceptarlo”'),"+"("+"'“Intenta y falla, pero nunca falles en intentarlo”'),"+"("+"'“No cuentes los días, haz que los días cuenten” – Muhammad Ali.'),"+"("+"'“Recuerda que eres tan bueno como lo mejor que hayas hecho en tu vida” – Billy Wilder'),"+"("+"'“Cree y actúa como si fuese imposible fallar” – Charles Kettering.'),"+"("+"'“Sólo le falta el tiempo a quien no sabe aprovecharlo” – Jovellanos'),"+"("+"'“Nunca es demasiado tarde para ser la persona que siempre has querido ser” -George Eliot'),"+"("+"'“Uno de los mayores errores en la vida es ser lo que los demás quieren que seas, en lugar de ser uno mismo” -Shannon L. Alder'),"+"("+"'“Ser grande no es una cuestión de tamaño, sino de actitud”'),"+"("+"'El rechazo es una oportunidad para su selección” -Bernard Branson'),"+"("+"'“Donde una puerta se cierra, otra se abre” -Cervantes'),"+"("+"'“No te aferres a lo que fue, lo que viene será mucho mejor”'),"+"("+"'“Nada le ocurre al hombre que no pueda soportarlo” -Marco Aurelio'),"+"("+"'“Una actitud positiva te da poder sobre tus circunstancias, en lugar de que tus circunstancias tengan poder sobre ti”'),"+"("+"'“La fuerza no consiste en ganar. La fuerza consiste en elegir las dificultades y decidir no rendirte”'),"+"("+"'“El 80% del éxito es iniciar” -Woody Allen.'),"+"("+"'“Para de pensar y termina tus problemas”'),"+"("+"'“Si lo imaginas, es real” -Pablo Picasso'),"+"("+"'“Los que tienen prisa, tropiezan”'),"+"("+"'“No tengas sueños, ten objetivos”'),"+"("+"'“Haz algo que valga la pena, las oportunidades no vuelven”'),"+"("+"'“Las cosas buenas toman tiempo”'),"+"("+"'“Ganar no lo es todo, sino querer ganar” – Vince Lombardi.'),"+"("+"'“Si no hay lucha, no hay progreso” – Frederick Douglass.'),"+"("+"'“Las palabras “nunca”, “siempre”, “todo” o “nada” son peligrosas porque no dejan opciones” -Walter Riso'),"+"("+"'“La inteligencia no es no cometer errores, sino descubrir el modo de sacarles provecho” Bertolt Brecht'),"+"("+"'“Pase lo que pase, o lo malo que parece haber hoy en día, la vida continúa, y será mejor mañana” -Maya Angelou'),"+"("+"'“Un hombre con una idea nueva es un loco hasta que la idea triunfa” -Mark Twain'),"+"("+"'“Afortunadamente siempre existe otro día, otros sueños, otras risas, otras personas y otras cosas”'),"+"("+"'“La verdadera ignorancia no es la ausencia de conocimientos, sino el hecho de negarse a adquirirlos” -Karl Popper'),"+"("+"'“Toda persona tiene capacidad para cambiarse a sí misma” -Albert Ellis'),"+"("+"'“El problema no es el problema, el problema es tu actitud hacia el problema” -Jack Sparrow'),"+"("+"'“Haz de cada día tu pieza maestra” – John Wooden'),"+"("+"'“Nada es más valioso que este día” -Johann Wolfgang von Goethe'),"+"("+"'“Cuando se mira el mundo solo se puede ser pesimista, pero cuando se pasa a la acción solo se puede ser optimista” -Antonio Gramsci'),"+"("+"'“Cuando te encuentras con un bloqueo en la carretera, toma un desvío” – Mary Kay Ash'),"+"("+"'“Una vez que elijes la esperanza, todo es posible” – Christopher Reeve.'),"+"("+"'Vamooohhhh'),"+"("+"'El dolor que sientes hoy es la fuerza que sentirás mañana.'),"+"("+"'El talento depende de la inspiración, pero el esfuerzo depende de cada uno. Pep Guardiola'),"+"("+"'Querer es poder'),"+"("+"'Si no estás dispuesto a sacrificar lo cotidiano, debes conformarte con lo ordinario. Jim Rohn'),"+"("+"'Nuestra tarea en la vida no es vencer a los demás, sino superarnos a nosotros mismos.'),"+"("+"'Los desafíos son lo que hacen la vida interesante…. vencerlos es lo que nos da sentido. Joshua J. Marine')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
