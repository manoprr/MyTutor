package manop.mytutor.com.mytutor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import manop.mytutor.com.mytutor.R;
import manop.mytutor.com.mytutor.utility.CourseAdapter;
import manop.mytutor.com.mytutor.utility.CourseModel;
import manop.mytutor.com.mytutor.utility.OnClickItem;
import manop.mytutor.com.mytutor.utility.TeacherModel;

public class TeacherCourseFragment extends Fragment {

    private String uidString;

    public static TeacherCourseFragment teacherCourseInstance(String uidString) {
        TeacherCourseFragment teacherCourseFragment = new TeacherCourseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Uid", uidString);
        teacherCourseFragment.setArguments(bundle);
        return teacherCourseFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getValueFromFirebase();




    }   //main method

    private void getValueFromFirebase() {
        uidString = getArguments().getString("Uid");
        Log.d("22SepV2", "Uid at Course ==>" + uidString);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("Teacher").child(uidString);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TeacherModel teacherModel = dataSnapshot.getValue(TeacherModel.class);
                String courseString = teacherModel.getCourse();
                createCourseRecyclerView(courseString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void createCourseRecyclerView(String courseString) {
        final ArrayList<String> nameCourseStringArrayList = new ArrayList<>();
        final ArrayList<String> periodStringArrayList = new ArrayList<>();
        final ArrayList<String> contentStringArrayList = new ArrayList<>();
        final ArrayList<String> sourceStringArrayList = new ArrayList<>();


        Log.d("22SepV2", "Cousre ==>" + courseString);
        final int amountCourse = Integer.parseInt(courseString.trim());
        String[] childCourseString = new String[amountCourse];
        for (int i=0; i<childCourseString.length; i+=1) {
            childCourseString[i] = uidString + "_" + Integer.toString(i + 1);
            Log.d("22SepV2", "course("+ i +") ==>" + childCourseString);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference()
                    .child("Course").child(childCourseString[i]);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Log.d("22SepV2", "dataSnapShot ==>" + dataSnapshot.toString());
                    CourseModel courseModel = dataSnapshot.getValue(CourseModel.class);
                    nameCourseStringArrayList.add(courseModel.getSubject());
                    periodStringArrayList.add(courseModel.getPeriod());
                    contentStringArrayList.add(courseModel.getContent());
                    sourceStringArrayList.add(courseModel.getSource());

                    if (nameCourseStringArrayList.size() >= amountCourse) {
                        showRecyclerView(nameCourseStringArrayList,
                                periodStringArrayList,
                                contentStringArrayList,
                                sourceStringArrayList);

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });

        }   //for


    }

    private void showRecyclerView(ArrayList<String> nameCourseStringArrayList,
                                  ArrayList<String> periodStringArrayList,
                                  ArrayList<String> contentStringArrayList,
                                  final ArrayList<String> sourceStringArrayList) {

        Log.d("22SepV2", "Subject ==>" + nameCourseStringArrayList.toString());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewCourseTeacher);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        CourseAdapter courseAdapter = new CourseAdapter(getActivity(), nameCourseStringArrayList,
                periodStringArrayList,
                contentStringArrayList,
                new OnClickItem() {
            @Override
            public void onClickItem(View view, int i) {

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, ShowVideoFragment.showVideoFragment(sourceStringArrayList.get(i)))
                        .addToBackStack(null)
                        .commit();

            }
        });
        recyclerView.setAdapter(courseAdapter);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_course, container, false);
        return view;
    }
}
