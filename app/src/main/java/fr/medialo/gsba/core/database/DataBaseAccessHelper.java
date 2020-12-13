package fr.medialo.gsba.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLData;

import fr.medialo.gsba.core.database.DataBaseOpenHelper;

public final class DataBaseAccessHelper {


    private transient static SQLiteOpenHelper helper;



    private DataBaseAccessHelper(Context context) {
        helper = new DataBaseOpenHelper(context);
    }

    public static void open(Context context){
        helper = new DataBaseOpenHelper(context);
    }

    public static SQLiteOpenHelper getHelper(){

            return helper;
    }






}
