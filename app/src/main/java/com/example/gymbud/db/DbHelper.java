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
                "Time REAL," +
                "Date TEXT)");

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
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_STATS + "("+"Weight,Reps,Time,Date)" + " VALUES " + "(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0'),"+"(" + "'0','0','0','0')");


        //Tablita ejercicios

        //Hombro
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Remo en polea con cuerda',1,'Fortalecimiento muscular','Ajusta la altura de la polea según tu altura y coloca una cuerda en cada lado de la polea','Eleva las manos hacia los lados, manteniendo los codos ligeramente doblados y llevando las manos hacia arriba hasta la altura de los hombros\n" +
                "Baja las manos lentamente a la posición inicial y repite el ejercicio\n','Asegúrate de mantener la espalda recta y el abdomen contraído durante todo el ejercicio\n" +
                "Puedes variar la intensidad del ejercicio ajustando la resistencia de la polea\n" +
                "Puedes realizar el ejercicio con un agarre prono (palmas hacia abajo) o supino (palmas hacia arriba) para trabajar diferentes partes de los músculos del hombro\n','Image',1,2,2,1)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Remo vertical con barra',1,'Fortalecimiento de los hombros y la espalda superior\n','1. Coloca una barra de remo o mancuernas en el suelo frente a ti.\n" +
                "2. Coloca una mancuerna o disco de peso en cada extremo de la barra.\n" +
                "3. De pie frente a la barra, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta la barra con las manos hacia abajo.\n','1. Eleva los brazos hacia arriba y hacia atrás, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "2. Lentamente regresa a la posición inicial y repite el movimiento.\n','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de la mancuerna para ajustar la dificultad.\n','Image',2,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Elevaciones laterales de pie con mancuernas',1,'Fortalecimiento de los hombros','1. Coloca dos mancuernas de un peso adecuado para ti en el suelo a tus lados.\n" +
                "2. De pie, con las piernas ligeramente separadas y las rodillas ligeramente flexionadas, sujeta cada mancuerna con una mano.\n','1. Eleva los brazos hacia los lados hasta que estén alineados con los hombros, manteniendo los codos pegados al cuerpo y manteniendo la espalda recta.\n" +
                "2. Lentamente regresa a la posición inicial y repite el movimiento.\n','Asegúrate de mantener la espalda recta y los codos pegados al cuerpo durante todo el ejercicio.\n" +
                "También puedes variar el peso de las mancuernas para ajustar la dificultad.\n','Image',3,3,2,3)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press Arnold en banco',1,'Fortalecimiento del hombro y tríceps','Sentarse en un banco de entrenamiento con la espalda recta y los pies apoyados en el suelo.\n" +
                "Sujetar una mancuerna en cada mano con los brazos a los lados del cuerpo y las palmas de las manos mirando hacia el cuerpo (agarre neutro).\n" +
                "Elevar las mancuernas hasta la altura del hombro con los codos pegados al costado.\n" +
                "Mantener la postura correcta durante todo el ejercicio.\n','Elevar las mancuernas hacia arriba y hacia atrás en dirección a las orejas, con los codos pegados al costado y las manos ligeramente inclinadas hacia afuera.\n" +
                "Bajar las mancuernas a la posición inicial y repetir el movimiento.\n" +
                "Continuar durante el tiempo o repeticiones establecidas.\n','Mantener la espalda recta y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al levantar las mancuernas y hacia adentro al bajarlas.\n" +
                "Ajustar el peso de las mancuernas según la capacidad del individuo.\n','Image',8,3,2,4)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Press de hombro en máquina',1,'Fortalecimiento del hombro y tríceps','Sentarse en una máquina de press de hombro con la espalda apoyada contra el respaldo y los pies apoyados en el suelo.\n" +
                "Colocar los brazos en los soportes de la máquina con las palmas de las manos mirando hacia arriba.\n" +
                "Ajustar los soportes para que queden a la altura adecuada para el tamaño del individuo.\n','Empujar hacia arriba con los brazos para levantar los pesos de la máquina.\n" +
                "Bajar los pesos a la posición inicial y repetir el movimiento.\n','Mantener la espalda apoyada contra el respaldo y los hombros hacia abajo durante todo el ejercicio.\n" +
                "Respirar hacia fuera al empujar hacia arriba y hacia adentro al bajar los pesos.\n','Image',5,2,2,5)");
//Pecho
    /*    sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Bice
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//A
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Oblicu
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Antebra
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Cuadrice
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Trapeci
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Trice
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Dorsal
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Espalda med
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Glute
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)")        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
//Femoral
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EXERCISE + "("+"Name,MuscularGroup,Focus,ForeSeeing,Execution,Details,Image,Tool,Category,Difficulty,Stats)"+" VALUES " + "(" + "'Name','MuscularGroup','Focus','Foreseeing','Execution','Details','Image',Tool,Category,Difficulty,Stats)");
*/

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
