package fr.medialo.gsba.core.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.medialo.gsba.core.FeeFile;
import fr.medialo.gsba.core.database.DataBaseAccessHelper;

public abstract class Dao<T> implements Daoi<T>{


    SQLiteDatabase database;

    public Dao() {
        this.database = DataBaseAccessHelper.getHelper().getWritableDatabase();
    }

    public void close(){
        this.database.close();
    }

    protected List<T> cursorToS(Cursor c,Supplier<T> s) {
// Si la requête ne renvoie pas de résultat
        if (c.getCount() == 0)
            return new ArrayList<>();
        ArrayList<T> list = new ArrayList<>();
        c.moveToFirst();
        do {
            list.add(s.get());
        } while (c.moveToNext());
// Ferme le curseur pour libérer les ressources
        c.close();
        return list;
    }


    protected T cursorTo(Cursor c,Supplier<T> s) {
        c.moveToFirst();
        if (c.getCount() == 0)
            return null;
        T t = s.get();
        c.close();
        return t;
    }






}
