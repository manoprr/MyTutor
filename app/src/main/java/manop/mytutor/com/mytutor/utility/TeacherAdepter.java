package manop.mytutor.com.mytutor.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import manop.mytutor.com.mytutor.fragment.TeacherCommentFragment;
import manop.mytutor.com.mytutor.fragment.TeacherCourseFragment;
import manop.mytutor.com.mytutor.fragment.TeacherInfoFragment;
import manop.mytutor.com.mytutor.fragment.TeacherProfileFragment;

public class TeacherAdepter extends FragmentStatePagerAdapter {

    private FragmentManager fragmentManager;
    private int anInt;
    private String uidString;

    public TeacherAdepter(FragmentManager fragmentManager, int anInt, String uidString) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.anInt = anInt;
        this.uidString = uidString;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TeacherInfoFragment.teacherInfoInstance(uidString);
            case 1:
                return TeacherCourseFragment.teacherCourseInstance(uidString);
            case 2:
                TeacherCommentFragment teacherCommentFragment = new TeacherCommentFragment();
                return teacherCommentFragment;
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return anInt;
    }
}
