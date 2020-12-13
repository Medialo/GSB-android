package fr.medialo.gsba.core.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;


public class DataBaseOpenHelper extends SQLiteOpenHelper {



    private Context context;
    public transient static final String DATABASE_NAME = "GSBANDROID";
    public transient static final int DATABASE_VERSION = 2;


    public DataBaseOpenHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Arrays.asList(Table.values()).forEach(table -> db.execSQL(table.getSql()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
            context.deleteDatabase(DATABASE_NAME);
        onCreate(db);
    }
}
