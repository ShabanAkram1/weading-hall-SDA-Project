package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HallOwnerLogin extends AppCompatActivity {
    EditText name ,pass;
    TextView signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_owner_login);
        name = findViewById(R.id.Hallownerloginemail);
        pass = findViewById(R.id.Hallownerloginpassword);
        signup = findViewById(R.id.hallownersignup);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HallOwnerLogin.this,HallOwnerSignup.class);
                startActivity(i);
            }
        });
    }

    public void HallOwnerLogin(View view) {
        String email = name.getText().toString();
        String password = pass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(HallOwnerLogin.this,HallOwnerMainPage.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(HallOwnerLogin.this, "User doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(HallOwnerLogin.this,HallOwnerMainPage.class);
            startActivity(i);
            finish();

        }
    }
}