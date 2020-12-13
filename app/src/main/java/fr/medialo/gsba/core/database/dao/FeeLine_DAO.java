package fr.medialo.gsba.core.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.sql.PreparedStatement;
import java.util.List;

import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.FeeLine;
import fr.medialo.gsba.core.SubCategoryManager;

/**
 *
 *   FeeLine
 *
 */
public class FeeLine_DAO extends Line<FeeLine> {

    private static final String INSERT = "INSERT INTO feeslines(sublistfee_id, quantity, created, file_id,statu_id) VALUES (?,?,DATE(),?,1)";




    private FeeLine_DAO() {
        super(0);
    }

    public FeeLine_DAO(int file_id) {
        super(file_id);
        if(file_id < 1)
            throw new IllegalArgumentException(" file_id < 1 ("+file_id+")");

    }

    @Override
    public void create(FeeLine objToCreate) {
        SQLiteStatement statement = super.database.compileStatement(INSERT);
        statement.bindLong(1,this.file_id);
        statement.bindLong(2,objToCreate.getQuantity());
        statement.bindLong(3,objToCreate.getFile_id());
        statement.execute();

    }

    //new String[] {
    //                        "id", "sublistfee_id", "quantity","created"}
    @Override
    public List<FeeLine> getAll() {
        Cursor c = database.query("feeslines", null, " file_id = ?", new String[]{String.valueOf(this.file_id)}, null,
                null, null,null);
        return super.cursorToS(c,() -> {
            FeeLine feeLine = new FeeLine(
                    c.getInt(0),
                    c.getInt(5),
                    c.getInt(6),
                    c.getString(4),  //date
                    c.getInt(2), //qtt
                    SubCategoryManager.map.get(c.getInt(1)));
            return feeLine;
        });
    }

    @Override
    public FeeLine get(int id) {
        return null;
    }

    @Override
    public void update(FeeLine objToSave) {

    }

    @Override
    public void delete(FeeLine objToDelete) {

    }


}
