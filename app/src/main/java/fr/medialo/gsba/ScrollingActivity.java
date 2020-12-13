package fr.medialo.gsba;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.FeeFile;
import fr.medialo.gsba.core.FeeLine;
import fr.medialo.gsba.core.SubCategoryManager;
import fr.medialo.gsba.core.adaptater.FileAdapter;
import fr.medialo.gsba.core.category.SubCategory;
import fr.medialo.gsba.core.database.DataBaseAccessHelper;
import fr.medialo.gsba.core.database.DataBaseOpenHelper;
import fr.medialo.gsba.core.database.dao.ExclFeeLine_DAO;
import fr.medialo.gsba.core.database.dao.FeeFile_DAO;
import fr.medialo.gsba.core.database.dao.FeeLine_DAO;

/**
 *  MAinActivity
 */
public class ScrollingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private transient FileAdapter arrayAdapter;
    private transient List<FeeFile> feeFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseAccessHelper.open(this);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout2);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        FeeFile_DAO dao = new FeeFile_DAO();
        feeFileList = dao.getAll();


        arrayAdapter = new FileAdapter(feeFileList,this);
        RecyclerView list = findViewById(R.id.list_file);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(), layoutManager.getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setAdapter(arrayAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(this);

    }

    public void openEditActivity(int id){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openEditActivity((int) id);
    }
}