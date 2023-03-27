package com.example.xrig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class XRiG_Login extends AppCompatActivity {

    EditText lemail, lpassword;
    Button login;
    TextView registerHere;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xri_glogin);

        lemail = findViewById(R.id.lemail);
        lpassword = findViewById(R.id.lpassword);
        login = findViewById(R.id.login);
        registerHere = findViewById(R.id.registerHere);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XRiG_Login.this, XRiG_Register.class));
            }
        });
    }

    public void loginUser(){
        String email = lemail.getText().toString().trim();
        String password = lpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            lemail.setError("Email cannot be Empty");
            lemail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            lpassword.setError("Password cannot be Empty");
            lpassword.requestFocus();
        }else {
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(XRiG_Login.this, "User Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(XRiG_Login.this, MainActivity.class));
                    }else {
                        Toast.makeText(XRiG_Login.this, "User Login Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}