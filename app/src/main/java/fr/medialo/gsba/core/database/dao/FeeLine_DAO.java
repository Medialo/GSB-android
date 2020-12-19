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

    private static final String INSERT = "INSERT INTO feeslines(sublistfee_id, quantity, created, file_id,statu_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE feeslines SET sublistfee_id = ?, quantity = ?, created = ?,statu_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM feeslines WHERE id = ?";



    public FeeLine_DAO() {
        super();
    }

    @Deprecated
    public FeeLine_DAO(int file_id) {
        super(file_id);
        if(file_id < 1)
            throw new IllegalArgumentException(" file_id < 1 ("+file_id+")");

    }

    @Override
    public void create(FeeLine objToCreate) {
        SQLiteStatement statement = super.database.compileStatement(INSERT);
        statement.bindLong(1,objToCreate.getSubCategory().getId());
        statement.bindLong(2,objToCreate.getQuantity());
        statement.bindLong(4,this.file_id);
        statement.bindLong(5,objToCreate.getStatu_id());
        statement.bindString(3,objToCreate.getDate().toString());
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
        if(id == -1)
            return new FeeLine();
        else{
            Cursor c = database.query("feeslines", null, " id = ?", new String[]{String.valueOf(id)}, null, null, null,null);
            return super.cursorTo(c,() -> {
                FeeLine fee = new FeeLine(c.getInt(0),c.getInt(5),c.getInt(6),c.getString(4),c.getInt(2),c.getInt(1));
                return fee;
            });
        }
    }

    @Override
    public void update(FeeLine objToSave) {
        SQLiteStatement statement = database.compileStatement(UPDATE);
        statement.bindLong(1,objToSave.getSubCategory().getId());
        statement.bindLong(2,objToSave.getQuantity());
        statement.bindString(3,objToSave.getDate().toString());
        statement.bindLong(4,objToSave.getStatu_id());
        statement.bindLong(5,objToSave.getId());
        statement.execute();
    }

    @Override
    public void delete(FeeLine objToDelete) {
        SQLiteStatement statement = database.compileStatement(DELETE);
        statement.bindLong(1,objToDelete.getId());
        statement.execute();
    }


}
