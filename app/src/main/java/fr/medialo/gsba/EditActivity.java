package fr.medialo.gsba;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import fr.medialo.gsba.core.Fee;
import fr.medialo.gsba.core.FeeLine;
import fr.medialo.gsba.core.database.dao.FeeLine_DAO;
import fr.medialo.gsba.core.tabbed.FeeLineAdapter;
import fr.medialo.gsba.core.tabbed.FragmentAdapter;
import fr.medialo.gsba.core.database.DataBaseAccessHelper;

public class EditActivity extends AppCompatActivity {

    boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int fileId = intent.getIntExtra("id",-1);



        DataBaseAccessHelper.open(this);
        setContentView(R.layout.activity_edit_scrolling);

         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbaredit);
        //setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layoutedit);
        toolBarLayout.setTitle(getTitle());


        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Frais autorisés") );
        tabLayout.addTab(tabLayout.newTab().setText("Hors frais") );

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
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
            if(this.isOpen == false)
                anim(false);
        });








//

//https://developer.android.com/guide/navigation/navigation-swipe-view-2

//       tabLayout.setupWithViewPager(viewPager);
//       tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if(this.isOpen)
            anim(true);
        return super.dispatchTouchEvent(ev);
    }

    private void anim(boolean isOpen){

        Animation fab_open = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        Animation fab_close = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        if(isOpen){
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab2.setVisibility(View.INVISIBLE);
            fab2.setClickable(false);
            fab1.setVisibility(View.INVISIBLE);
            fab1.setClickable(false);
            this.isOpen = false;
        } else {
            fab2.startAnimation(fab_open);
            fab1.startAnimation(fab_open);
            //fab2.animate().setDuration(200).translationY(-200).alpha(1).start();
            fab2.setClickable(true);
            fab1.setClickable(true);
            this.isOpen = true;
        }
    }


}
