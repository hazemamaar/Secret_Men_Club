package com.example.hassanwahdan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends Fragment {

    private TextInputEditText textInputEditTextEmail,textInputEditTextPassword;
    MaterialButton materialButtonSignUp, materialButtonSignIn;
    NavController navController;

    public LogIn() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         init(view);
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        materialButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(textInputEditTextEmail.getText().toString(),textInputEditTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                             navController.navigate(R.id.action_logIn_to_choice);
                        }else{
                            Toast.makeText(getContext(),"Fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        materialButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_logIn_to_signUp);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }
    void init(View view){
       textInputEditTextEmail=view.findViewById(R.id.inputTextEmail);
       textInputEditTextPassword=view.findViewById(R.id.inputTextPassword);
       materialButtonSignIn=view.findViewById(R.id.logIn_btn);
       materialButtonSignUp=view.findViewById(R.id.signUoBtn);
        navController = Navigation.findNavController(view);
    }
}