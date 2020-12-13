package fr.medialo.gsba.core.adaptater;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fr.medialo.gsba.core.tabbed.EditFragmentFeeLine;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new EditFragmentFeeLine();
            case 1:
                return null;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
