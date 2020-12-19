package fr.medialo.gsba.core.database.dao;

public abstract class Line<T> extends Dao<T> {



    int file_id;

    protected Line(){
        super();
    }

    protected Line(int file_id) {
        super();
        this.file_id = file_id;
    }
}
