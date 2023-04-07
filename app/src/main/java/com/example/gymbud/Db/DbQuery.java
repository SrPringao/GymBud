package com.example.gymbud.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gymbud.Entidades.Exercises;
import com.example.gymbud.Entidades.PersonInfo;
import com.example.gymbud.Entidades.Phrase;
import com.example.gymbud.Entidades.Stats;

import java.util.ArrayList;


public class DbQuery extends DbHelper {
    Context context;

    public DbQuery(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //Esta funcion lo que hace es de tipo long y lo que hace es insertar informacion en la base de datos de sqlite
    //todos los datos que recibe son las diferentes columnas de la tabla, y lo que hacemos es instanciar un objeto DbHelper
    //Para despues usar el metodo get writable database que lo que hace es preparar la bd para realizar cambios, despues
    //Declaramos content values y de ahi lo vamos llenando con los datos que recibe de cuando se invoca la funcion, para despues insertarlo en
    // la tabla person info
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
    //Esta funcion lo que hace es mostrar la informacion almacenada en la bd, instanciamos un objeto dbHelper, y preparamos la bd para escribir,
    //despues con el pojo ya creado de PersonInfo que tiene las variables de la tabla con sus respectivos getters and setters, hacemos una query a la bd
    //con el id del usuario que lo recibe cuando se invoca la funcion, para despues ir guardando la informacion en este objeto person info y retornar el objeto.
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
    //En esta funcion creamos un objeto dbHelper que es la bd, para despues prepararla para escribir y hacer la query de la tabla phrase buscando el id que recibe la
    //funcion, para despues guardar los datos en el pojo Phrase y retornar este mismo objeto
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
    //Esta funcion crea un arraylist con los datos de exercises que se va a usar para el recyclerview,
    //Creamos el arreglo y lo inicializamos, como ya fue mencionado anteriormente creamos el dbHelper y lo inicializamos con el contexto de la view,
    //Despues hacemos una query de la tabla exercise a la bd buscando el id e iteramos todos los datos hasta tener todos los que tengan ese id en especifico,
    //despues retornamos la lista
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
    //Esta funcion tambien como fue mencionado anteriormente realiza lo mismo con la tabla ejercicio, creando un pojo y guardando los datos para poder
    //mostrarlos haciendo una query a la tabla ejercicios con su respectivo id y retornando el objeto exercises

    public Exercises EjerciciosVER(int id){
        Log.d("Exercises", id+"");
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Exercises exercises = null;
        Cursor cursorejercicios;
        cursorejercicios = db.rawQuery("SELECT * FROM " + TABLE_EXERCISE + " WHERE Id = " + id, null);
        if(cursorejercicios.moveToFirst()){
            exercises = new Exercises();
            exercises.setId(cursorejercicios.getInt(0));
            exercises.setName(cursorejercicios.getString(1));
            exercises.setMuscularGroup(cursorejercicios.getInt(2));
            exercises.setFocus(cursorejercicios.getString(3));
            exercises.setForeSeeing(cursorejercicios.getString(4));
            exercises.setExecution(cursorejercicios.getString(5));
            exercises.setDetails(cursorejercicios.getString(6));
            exercises.setImage(cursorejercicios.getBlob(7));
            exercises.setTool(cursorejercicios.getInt(8));
            exercises.setCategory(cursorejercicios.getInt(9));
            exercises.setDifficulty(cursorejercicios.getInt(10));
            exercises.setStats(cursorejercicios.getInt(11));
        }

        cursorejercicios.close();
        return exercises;
    }

    public int[] EjerciciosID(int MuscularGroup,int CantEjercicios,int tool,int dificultad){
        Log.d("Exercises", MuscularGroup+"");
        DbHelper dbHelper = new DbHelper(context);
        int[] ejercicios = new int[30];
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Exercises exercises = null;
        Cursor cursorejercicios;
        try {
            cursorejercicios = db.rawQuery("SELECT Id FROM " + TABLE_EXERCISE + " WHERE MuscularGroup = " + MuscularGroup + " AND Tool = " + tool + " AND Difficulty =" + dificultad+ " ORDER BY  RANDOM() LIMIT " + CantEjercicios, null);
            int i = 0;
            if (cursorejercicios.moveToFirst()) {
                do{
                ejercicios[i] = cursorejercicios.getInt(0);
                i++;
                }while (cursorejercicios.moveToNext());
                if (i == CantEjercicios) {
                    return ejercicios;
                }
            }
            cursorejercicios.close();
        }catch(Exception e){
            Log.d("Exercises", e.getMessage());
        }

        return ejercicios;

    }
    //Esta funcion inserta los valores recibidos en la tabla Stats, donde retorna un long, primero creamos el objeto Dbhelper y preparamos
    //la bd para escritura, despues insertamos los valores recibidos en values que es un content values y hacemos la query que es un insert con
    //los valores guardados en values, para retornar la query
    public long StatsInsert(int weight, int reps,int reps2,float time, String Date,int IdEjercicio){
        long query=0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Weight", weight);
            values.put("Reps", reps);
            values.put("Reps2", reps2);
            values.put("Time", time);
            values.put("Date", Date);
            values.put("IdEjercicio", IdEjercicio);

            query = db.insert(TABLE_STATS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return query;
    }

    //Esta funcion sirve para mostrar las stats, como ya fue mencionado antes se crea un pojo llamado stats y creamos el objeto dbhelper para preparar la bd
    //para escritura, despues realizar un selectr a la tabla stats con el id recibido y se almacenan los datos en el pojo, para despues retornar stats.
    public Stats verStats(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Stats stats = null;
        Cursor cursorsote;
        try {
            cursorsote = db.rawQuery("SELECT * FROM " + TABLE_STATS + " WHERE IdEjercicio = " + id ,null);
            if (cursorsote.moveToFirst()) {
                stats = new Stats();
                stats.setID_Stats(cursorsote.getInt(0));
                stats.setWeight(cursorsote.getInt(1));
                stats.setReps(cursorsote.getInt(2));
                stats.setReps2(cursorsote.getInt(3));
                stats.setTime(cursorsote.getFloat(4));
                stats.setDate(cursorsote.getString(5));
                cursorsote.close();

            }
            Log.d("FIN", "FINIQUITO");
            return stats;
        }catch (Exception ex){
            Log.d("Exception", String.valueOf(ex));
        }
        Log.d("FIN", "NO FINIQUITO");
        return stats;
}

    public ArrayList<Exercises> MostrarEjercicios(ArrayList<Integer> ids) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Exercises> listaEjercicios = new ArrayList<>();
        Exercises ejercicios = null;
        String commaSeparatedIds = ids.toString().replace("[", "").replace("]", ""); // Convertir la lista en una cadena separada por comas para usar en la cláusula IN
        Cursor cursorEjercicios = null;
        cursorEjercicios = db.rawQuery("SELECT * FROM " + TABLE_EXERCISE + " WHERE id IN (" + commaSeparatedIds + ")", null);
        if (cursorEjercicios.moveToFirst()) {
            do {
                ejercicios = new Exercises();
                ejercicios.setId(cursorEjercicios.getInt(0));
                ejercicios.setName(cursorEjercicios.getString(1));
                ejercicios.setMuscularGroup(cursorEjercicios.getInt(2));
                ejercicios.setFocus(cursorEjercicios.getString(3));
                ejercicios.setForeSeeing(cursorEjercicios.getString(4));
                ejercicios.setExecution(cursorEjercicios.getString(5));
                ejercicios.setDetails(cursorEjercicios.getString(6));
                ejercicios.setImage(cursorEjercicios.getBlob(7));
                ejercicios.setTool(cursorEjercicios.getInt(8));
                ejercicios.setCategory(cursorEjercicios.getInt(9));
                ejercicios.setDifficulty(cursorEjercicios.getInt(10));
                ejercicios.setStats(cursorEjercicios.getInt(11));

                listaEjercicios.add(ejercicios);
            } while (cursorEjercicios.moveToNext());
        }
        cursorEjercicios.close();
        return listaEjercicios;
    }

}