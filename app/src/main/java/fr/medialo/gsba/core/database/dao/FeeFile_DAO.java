package fr.medialo.gsba.core.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import fr.medialo.gsba.core.FeeFile;
import fr.medialo.gsba.core.database.DataBaseAccessHelper;

public class FeeFile_DAO extends Dao<FeeFile> {


    @Override
    public void create(FeeFile objToCreate) {
        String sql = " INSERT INTO `files` (`created`, `modified`, `statu_id`) VALUES (DATE(), DATE(), '1') ";
        database.execSQL(sql);
    }

    @Override
    public List<FeeFile> getAll() {

        Cursor c = database.query("files", new String[] {
                        "id", "created", "modified"}, null, null, null,
                null, null);
    return cursorToS(c,() -> {
            FeeFile feeFile = new FeeFile();
            feeFile.setId(c.getInt(0));
            feeFile.setDateCreatedWithString(c.getString(1));
          feeFile.setDateModifiedWithString(c.getString(2));
          return feeFile;
    });

    }

    @Override
    public FeeFile get(int id) {
        return null;
    }

    @Override
    public void update(FeeFile objToSave) {

    }

    @Override
    public void delete(FeeFile objToDelete) {

    }

    public void updateDate(int id){
        String sql = "UPDATE files SET modified = DATE() WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindLong(1,id);
        statement.execute();
    }


//    private List<FeeFile> cursorToS(Cursor c) {
//        if (c.getCount() == 0)
//            return new ArrayList<>(0);
//        ArrayList<FeeFile> list = new ArrayList<>();
//        c.moveToFirst();
//        do {
//            FeeFile feeFile = new FeeFile();
//            String str = c.getString(1);
//
//            feeFile.setDateCreatedWithString(c.getString(1));
//          feeFile.setDateModifiedWithString(c.getString(2));
//
//
//            list.add(feeFile);
//        } while (c.moveToNext());
//        c.close();
//        return list;
//    }



}
