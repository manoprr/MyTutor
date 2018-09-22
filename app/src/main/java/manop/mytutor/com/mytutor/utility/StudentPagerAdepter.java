package manop.mytutor.com.mytutor.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import manop.mytutor.com.mytutor.tablayout.TablayoutMycourseFragment;
import manop.mytutor.com.mytutor.tablayout.TablayoutProfileFragment;
import manop.mytutor.com.mytutor.tablayout.TablayoutRecentFragment;

public class StudentPagerAdepter extends FragmentStatePagerAdapter{

    private FragmentManager fragmentManager;
    private int anInt;

    public StudentPagerAdepter(FragmentManager fragmentManager, int anInt) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.anInt = anInt;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TablayoutProfileFragment tablayoutProfileFragment = new TablayoutProfileFragment();
                return tablayoutProfileFragment;
            case 2:
                TablayoutRecentFragment tablayoutRecentFragment = new TablayoutRecentFragment();
                return tablayoutRecentFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return anInt;
    }
}
