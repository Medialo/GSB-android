package fr.medialo.gsba.core.database.dao;


import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public interface Daoi<T> {



    void create(T objToCreate);

    List<T> getAll();


    T get(int id);

    void update(T objToSave);

    void delete(T objToDelete);



}
