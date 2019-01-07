package project.programming.elliajah.studentknows1;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwitchFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentsList= new ArrayList<>();
    private final List<String> mListOfFragmentName = new ArrayList<>();

    public void addFragment(Fragment fragment,String name){
        mFragmentsList.add(fragment);
        mListOfFragmentName.add(name);
    }
    public SwitchFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListOfFragmentName.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return  mFragmentsList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }
}
