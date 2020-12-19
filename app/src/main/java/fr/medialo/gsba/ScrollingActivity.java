package fr.medialo.gsba;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import fr.medialo.gsba.core.ExclFee;
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
    private transient FeeFile_DAO dao;

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

        dao = new FeeFile_DAO();
        feeFileList = dao.getAll();

//        dao.create(new FeeFile());
//        dao.create(new FeeFile());
//        dao.create(new FeeFile());
//        FeeLine_DAO feeLine_dao = new FeeLine_DAO(1);
//        ExclFeeLine_DAO exclFeeLine_dao = new ExclFeeLine_DAO(1);
//        FeeLine feeLine = new FeeLine(12,12,0,LocalDate.now().toString(),2,2);
//        feeLine_dao.create(feeLine);
//        exclFeeLine_dao.create(new Fee(0,0,0,"Ordinateur",458.25,LocalDate.now().toString()));

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
           //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                FeeFile feeFile = new FeeFile();
                dao.create(feeFile);
                arrayAdapter.setmFeeFiles(dao.getAll());
                arrayAdapter.notifyDataSetChanged();
                updateNB();
            }
        });
        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(this);



        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //         Toast.makeText(view.getContext(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(list.getContext(), "Supprimé", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                FeeFile feeFile = arrayAdapter.getmFeeFiles().get(position);
                dao.delete(feeFile);
                arrayAdapter.setmFeeFiles(dao.getAll());
                arrayAdapter.notifyDataSetChanged();
                updateNB();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(list);

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

    @Override
    public void onResume(){
        super.onResume();
        arrayAdapter.setmFeeFiles(dao.getAll());
        arrayAdapter.notifyDataSetChanged();
        updateNB();
    }


    private void updateNB(){
        TextView tvFee = findViewById(R.id.tv_nb_notes);
        FeeFile_DAO feeFile = new FeeFile_DAO();
        tvFee.setText(feeFile.getAll().size()+"");

    }

}