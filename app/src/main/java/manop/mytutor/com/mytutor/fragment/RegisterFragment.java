package manop.mytutor.com.mytutor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import manop.mytutor.com.mytutor.R;
import manop.mytutor.com.mytutor.utility.MyAlert;
import manop.mytutor.com.mytutor.utility.MyModel;

public class RegisterFragment extends Fragment{

    private String nameString, emailString, passwordString, rePasswaordString, idcardString;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        Student Controller
        studentController();

//        Teacher Controller
        teacherController();


    }   //Main Method

    private void teacherController() {
        Button button = getView().findViewById(R.id.btnTeacher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckData("Teacher");
            }
        });
    }

    private void studentController() {
        Button button = getView().findViewById(R.id.btnStudent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckData("Student");
            }
        });
    }

    private void CheckData(final String childString) {

//        Get Value From Edittext to String
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText idcardEditText = getView().findViewById(R.id.edtIDcard);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);
        EditText rePasswordEditText = getView().findViewById(R.id.edtRePassword);

        nameString = nameEditText.getText().toString().trim();
        idcardString = idcardEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        rePasswaordString = rePasswordEditText.getText().toString().trim();

        Log.d("2SepV1", "Pass=" + passwordString);
        Log.d("2SepV1", "rePass=" + rePasswaordString);

        final MyAlert myAlert = new MyAlert(getActivity());

        if (nameString.isEmpty()|| idcardString.isEmpty() ||
                emailString.isEmpty() || passwordString.isEmpty() ||
                rePasswaordString.isEmpty()) {
//            Have space
            myAlert.normalDialog(getString(R.string.have_space),getString(R.string.message_space));
        } else if (idcardString.length() != 13) {
            myAlert.normalDialog("Id Card False",
                    "Please Fill ID Card 13 Digi");
        } else if (!passwordString.equals(rePasswaordString)) {
//            Password False
            myAlert.normalDialog("Password Not Match",
                    "Please Fill Password Marching");
        } else {
//            Password True

            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest
                                        .Builder().setDisplayName(nameString).build();
                                firebaseUser.updateProfile(userProfileChangeRequest)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                Log.d("2SepV1", "DisplayName ==>" + firebaseUser.getDisplayName());

                                                insertValueDatabase(firebaseUser.getUid(), childString);
                                            }
                                        });

                            } else {
                                myAlert.normalDialog("Cannot Register",
                                        task.getException().getMessage().toString());
                            }
                        }
                    });




        }   //if



    }   // CheckData

    private void insertValueDatabase(String uidString, String childString) {

        MyModel myModel = new MyModel(nameString, idcardString,uidString);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child(childString);
        databaseReference.child(uidString).setValue(myModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentMainFragment, new MainFragment())
                                .commit();
                    }
                });



    }   //insert

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}   //main class
