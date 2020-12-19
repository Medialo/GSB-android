package fr.medialo.gsba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import fr.medialo.gsba.core.database.dao.ExclFeeLine_DAO;
import fr.medialo.gsba.core.database.dao.FeeLine_DAO;
import fr.medialo.gsba.core.tabbed.FragmentAdapter;
import fr.medialo.gsba.core.database.DataBaseAccessHelper;

public class EditActivity extends AppCompatActivity {

    boolean isOpen = false;
    private FragmentAdapter fragmentAdapter;
    private int fileId;

    static VoidConsumer voidConsumer;

    public static VoidConsumer getInstance(){
        return voidConsumer;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        voidConsumer = new VoidConsumer() {
            @Override
            public void execute() {
                TextView tvFee = findViewById(R.id.tv_nb_frais);
                TextView tvExclFee = findViewById(R.id.tv_nb_exclfrais);
                FeeLine_DAO feeLine_dao = new FeeLine_DAO(fileId);
                ExclFeeLine_DAO exclFeeLine_dao = new ExclFeeLine_DAO(fileId);
                tvExclFee.setText(exclFeeLine_dao.getAll().size()+"");
                tvFee.setText(feeLine_dao.getAll().size()+"");
            }
        };
        setContentView(R.layout.activity_edit_scrolling);
        Intent intent = getIntent();
        fileId = intent.getIntExtra("id",-1);

        updateNB();


        DataBaseAccessHelper.open(this);


         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbaredit);
        //setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layoutedit);
        toolBarLayout.setTitle(getTitle());


        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Frais autorisés") );
        tabLayout.addTab(tabLayout.newTab().setText("Hors frais") );

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.setFileID(fileId);
        viewPager.setAdapter(fragmentAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab_edit_scrolling);

        fab.setOnClickListener(v -> {
                anim(isOpen);
        });


        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        Intent intentLine = new Intent(this,EditLineActivity.class);
        intentLine.putExtra("id",-1);
        intentLine.putExtra("file_id",fileId);
        fab1.setOnClickListener(v -> { //hors forfais
            intentLine.putExtra("fee",true);
            startActivityForResult(intentLine,0);
        });
        fab2.setOnClickListener(v -> {
            intentLine.putExtra("fee",false);
            startActivityForResult(intentLine,0);
        });

//

//https://developer.android.com/guide/navigation/navigation-swipe-view-2

//       tabLayout.setupWithViewPager(viewPager);
//       tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }



//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        if(this.isOpen)
//            anim(true);
//        return super.dispatchTouchEvent(ev);
//    }

    private void anim(boolean isOpen){

        Animation fab_open = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        Animation fab_close = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        TextView tvfab1 = findViewById(R.id.tvfab1);
        TextView tvfab2 = findViewById(R.id.tvfab2);

        if(isOpen){
//            tvfab1.setVisibility(View.VISIBLE);
//            tvfab2.setVisibility(View.VISIBLE);
            tvfab1.startAnimation(fab_close);
            tvfab2.startAnimation(fab_close);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
//            fab2.setVisibility(View.INVISIBLE);
            fab2.setClickable(false);
//            fab1.setVisibility(View.INVISIBLE);
            fab1.setClickable(false);
            this.isOpen = false;
        } else {
            fab2.startAnimation(fab_open);
            fab1.startAnimation(fab_open);
            //fab2.animate().setDuration(200).translationY(-200).alpha(1).start();
            fab2.setClickable(true);
            fab1.setClickable(true);
            tvfab1.startAnimation(fab_open);
            tvfab2.startAnimation(fab_open);
            this.isOpen = true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        this.fragmentAdapter.getEditFragmentFeeLine().getFeeLineAdapter().setmFeeLineList(new FeeLine_DAO(fileId).getAll());
        this.fragmentAdapter.getEditFragmentFeeLine().getFeeLineAdapter().notifyDataSetChanged();

        this.fragmentAdapter.getEditFragmentExclFeeLine().getFeeLineAdapter().setmFeeLineList(new ExclFeeLine_DAO(fileId).getAll());
        this.fragmentAdapter.getEditFragmentFeeLine().getFeeLineAdapter().notifyDataSetChanged();
        updateNB();
    }

    private void updateNB(){
        voidConsumer.execute();

    }


}
