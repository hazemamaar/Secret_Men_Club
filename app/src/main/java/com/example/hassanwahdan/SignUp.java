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


public class SignUp extends Fragment {


   private TextInputEditText textInputEditTextEmail,textInputEditTextPassword,textInputEditTextMobile,textInputEditTextUsername;
   MaterialButton materialButtonSignUp, materialButtonSignIn;
    NavController navController;
    public SignUp() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        materialButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textInputEditTextEmail.getText().length()>1&&textInputEditTextPassword.getText().length()>=6){
                    String user=textInputEditTextEmail.getText().toString(),pass=textInputEditTextPassword.getText().toString();
                   firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(getContext(),"Sign Up Done",Toast.LENGTH_SHORT).show();
                          navController.navigate(R.id.action_signUp_to_logIn);
                      }else{
                          Toast.makeText(getContext(),"Please, Enter Email and Password Correct",Toast.LENGTH_SHORT).show();
                      }

                       }
                   });

                }else{
                    Toast.makeText(getContext(),"Please, Enter Email and Paswword",Toast.LENGTH_SHORT).show();
                }
            }
        });
        materialButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_signUp_to_logIn);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    void init(View view){
        textInputEditTextEmail=view.findViewById(R.id.inputTextEmail);
        textInputEditTextMobile=view.findViewById(R.id.inputTextMobilePhone);
        textInputEditTextPassword=view.findViewById(R.id.inputTextPassword);
        textInputEditTextUsername=view.findViewById(R.id.inputTextUserName);
        materialButtonSignUp=view.findViewById(R.id.signUoBtn);
        materialButtonSignIn=view.findViewById(R.id.signInBtn);
        navController = Navigation.findNavController(view);

    }
}