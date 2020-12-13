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

    private static final String INSERT = "INSERT INTO exclfeeslines(name, cost,created,file_id,statu_id) VALUES (?,?,DATE(),?,1)";


    public ExclFeeLine_DAO(int file_id) {
        super(file_id);
    }

    @Override
    public void create(Fee objToCreate) {
        SQLiteStatement statement = super.database.compileStatement(INSERT);
        statement.bindLong(3,this.file_id);
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
        return null;
    }

    @Override
    public void update(Fee objToSave) {

    }

    @Override
    public void delete(Fee objToDelete) {

    }
}
