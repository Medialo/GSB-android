package fr.medialo.gsba;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.medialo.gsba.core.FeeFile;
import fr.medialo.gsba.core.SubCategoryManager;
import fr.medialo.gsba.core.database.DataBaseOpenHelper;

public class GsbApplication extends Application {

    private List<FeeFile> feeFileList;

    @Override
    public void onCreate() {
        super.onCreate();
        SubCategoryManager.init();

    }




}
