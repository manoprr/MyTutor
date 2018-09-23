package manop.mytutor.com.mytutor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import manop.mytutor.com.mytutor.R;
import manop.mytutor.com.mytutor.utility.TeacherModel;

public class TeacherInfoFragment extends Fragment {

    private String uidString, nameString, genderString, idCardString, urlAvatarString;

    public static TeacherInfoFragment teacherInfoInstance(String uidString) {
        TeacherInfoFragment teacherInfoFragment = new TeacherInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Uid", uidString);
        teacherInfoFragment.setArguments(bundle);
        return teacherInfoFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        uidString = getArguments().getString("Uid");
        Log.d("22SepV1", "uid at info ==>" + uidString);


//        Show View
        showView();

    }   //Main Method

    private void showView() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("Teacher").child(uidString);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TeacherModel teacherModel = dataSnapshot.getValue(TeacherModel.class);
                nameString = teacherModel.getDisplayNameString();
                genderString = teacherModel.getGender();
                idCardString = teacherModel.getIdCardString();
                urlAvatarString = teacherModel.getAvatar();

                ImageView imageView = getView().findViewById(R.id.imvTeacher);
                Picasso.get().load(urlAvatarString).into(imageView);

                TextView nametextView = getView().findViewById(R.id.txtNameTeacher);
                TextView gendertextView = getView().findViewById(R.id.txtGender);
                TextView idCardtextView = getView().findViewById(R.id.txtIDcard);

                nametextView.setText(nameString);
                gendertextView.setText("Gender ==>" + genderString);
                idCardtextView.setText("ID Card ==>" + idCardString);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);
        return view;
    }
}
