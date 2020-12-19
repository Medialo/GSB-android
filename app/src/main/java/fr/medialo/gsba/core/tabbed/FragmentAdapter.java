package fr.medialo.gsba.core.tabbed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private int fileId;
    private EditFragmentExclFeeLine editFragmentExclFeeLine;
    private EditFragmentFeeLine editFragmentFeeLine;

    public EditFragmentExclFeeLine getEditFragmentExclFeeLine() {
        return editFragmentExclFeeLine;
    }

    public EditFragmentFeeLine getEditFragmentFeeLine() {
        return editFragmentFeeLine;
    }

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",fileId);
        switch (position){
            case 0:
                 editFragmentFeeLine = new EditFragmentFeeLine();
                editFragmentFeeLine.setArguments(bundle);
                return editFragmentFeeLine;
            case 1:
                editFragmentExclFeeLine = new EditFragmentExclFeeLine();
                editFragmentExclFeeLine.setArguments(bundle);
                return editFragmentExclFeeLine;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setFileID(int fileId) {
        this.fileId = fileId;
    }


//    public FragmentAdapter(@NonNull Fragment fragment) {
//        super(fragment);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position){
//            case 0:
//                return new EditFragmentFeeLine();
//            case 1:
//                return null;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 1;
//    }
}
