package manop.mytutor.com.mytutor.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import manop.mytutor.com.mytutor.R;
import manop.mytutor.com.mytutor.utility.MyModel;
import manop.mytutor.com.mytutor.utility.StudentPagerAdepter;
import manop.mytutor.com.mytutor.utility.TeacherAdepter;
import manop.mytutor.com.mytutor.utility.TeacherModel;

public class TeacherProfileFragment extends Fragment {

    private String uidString, nameString, urlAvatarString;
    private TabLayout tabLayout;


    public static TeacherProfileFragment teacherProfileInstance(String uidString) {

        TeacherProfileFragment teacherProfileFragment = new TeacherProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Uid", uidString);
        teacherProfileFragment.setArguments(bundle);
        return teacherProfileFragment;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        SignOut Controller
        signOutController();

//        Get Value From Argument
        getValueTeacher();

//        Creat Tablayout
        creatTablayout();

//        Craete Pager
        craetePager();

    }   // main method

    private void craetePager() {
        final ViewPager viewPager = getView().findViewById(R.id.viewPagerTeacher);
        TeacherAdepter teacherAdepter = new TeacherAdepter(
                getActivity()
                .getSupportFragmentManager(),
                tabLayout.getTabCount(), uidString);
        viewPager.setAdapter(teacherAdepter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    }

    private void creatTablayout() {
        tabLayout = getView().findViewById(R.id.tabLayoutTeacher);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Course"));
        tabLayout.addTab(tabLayout.newTab().setText("Comment"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    private void showView() {
        TextView textView = getView().findViewById(R.id.txtNameTeacher);
        textView.setText(nameString);
        CircleImageView circleImageView = getView().findViewById(R.id.imvTeacher);
        Picasso.get().load(urlAvatarString).into(circleImageView);

    }

    private void getValueTeacher() {
        uidString = getArguments().getString("Uid");
        Log.d("22SepV1", "uid On Teacher ==>" + uidString);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teacher").child(uidString);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                TeacherModel teacherModel = dataSnapshot.getValue(TeacherModel.class);
                nameString = teacherModel.getDisplayNameString();
                urlAvatarString = teacherModel.getAvatar();

                Log.d("22SepV1", "Name ==>" + nameString);
                Log.d("22SepV1", "Avatar ==>" + urlAvatarString);

                //        Show View
                showView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void signOutController() {
        ImageView imageView = getView().findViewById(R.id.imvSignOut);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
        return view;

    }
}