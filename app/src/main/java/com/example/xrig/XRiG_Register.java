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

public class XRiG_Register extends AppCompatActivity {

    EditText remail, rpassword;
    Button register;
    TextView loginHere;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrig_register);

        remail = findViewById(R.id.remail);
        rpassword = findViewById(R.id.rpassword);
        register = findViewById(R.id.register);
        loginHere = findViewById(R.id.loginHere);
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XRiG_Register.this,XRiG_Login.class));
            }
        });
    }
    private void createUser(){
        String email = remail.getText().toString().trim();
        String password = rpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
              remail.setError("Email cannot be Empty");
              remail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            rpassword.setError("Password cannot be Empty");
            rpassword.requestFocus();
        }else {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(XRiG_Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(XRiG_Register.this,XRiG_Login.class));
                    }else {
                        Toast.makeText(XRiG_Register.this, "User Registration Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}