package fr.medialo.gsba.core.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.FeeLine;
import fr.medialo.gsba.core.SubCategoryManager;


/**
 *
 *   EXCLFeeLine
 *
 */
public class ExclFeeLine_DAO extends Line<Fee> {

    private static final String INSERT = "INSERT INTO exclfeeslines(name, cost,created,file_id,statu_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE exclfeeslines SET name = ?, cost = ?, statu_id = ?,created = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM exclfeeslines WHERE id = ?";
    @Deprecated
    public ExclFeeLine_DAO(int file_id) {
        super(file_id);
    }

    public ExclFeeLine_DAO() {
        super();

    }
    @Override
    public void create(Fee objToCreate) {
        SQLiteStatement statement = super.database.compileStatement(INSERT);
        statement.bindLong(4,this.file_id);
        statement.bindLong(5,objToCreate.getStatu_id());
        statement.bindString(3,objToCreate.getDate().toString());
        statement.bindDouble(2,objToCreate.getCost());
        statement.bindString(1,objToCreate.getName());
        statement.execute();

    }

    //new String[] {
    //                        "id", "sublistfee_id", "quantity","created"}
    @Override
    public List<Fee> getAll() {
        Cursor c = database.query("exclfeeslines", null, " file_id = ?", new String[]{String.valueOf(this.file_id)}, null,
                null, null,null);
        return super.cursorToS(c,() -> {
            Fee fee = new Fee(c.getInt(0),c.getInt(6),c.getInt(7),c.getString(1),c.getDouble(2),c.getString(4));
            return fee;
        });
    }

    @Override
    public Fee get(int id) {
        if(id == -1)
            return new Fee();
        else{
            Cursor c = database.query("exclfeeslines", null, " id = ?", new String[]{String.valueOf(id)}, null, null, null,null);
            return super.cursorTo(c,() -> {
                Fee fee = new Fee(c.getInt(0),c.getInt(6),c.getInt(7),c.getString(1),c.getDouble(2),c.getString(4));
                return fee;
            });
        }


    }

    @Override
    public void update(Fee objToSave) {
        SQLiteStatement statement = database.compileStatement(UPDATE);
        statement.bindString(1,objToSave.getName());
        statement.bindDouble(2,objToSave.getCost());
        statement.bindLong(3,objToSave.getStatu_id());
        statement.bindString(4,objToSave.getDate().toString());
        statement.bindLong(5,objToSave.getId());
        statement.execute();
    }

    @Override
    public void delete(Fee objToDelete) {
        SQLiteStatement statement = database.compileStatement(DELETE);
        statement.bindLong(1,objToDelete.getId());
        statement.execute();
    }
}
